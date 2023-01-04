package org.jalvarez.apiservlet.webapp.headers.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.jalvarez.apiservlet.webapp.headers.annotations.CarroCompra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

// Asi se define un bean de sesion en CDI
// Se puede acceder a el desde cualquier parte de la aplicacion
// siempre se implementa a la clase el interface Serializable para que pueda ser serializado

@CarroCompra
public class Carro implements Serializable {
    private List<ItemCarro> items;
//    public Carro() {
//        this.items = new ArrayList<>();
//    }

    // transient es dado que no se puede serializar un objeto de tipo Logger
    // y se debe de inicializar en el metodo @PostConstruct
    @Inject
    private transient Logger log;
    @PostConstruct
    public void inicializar() {
        this.items = new ArrayList<>();
        log.info("Inicializando carro de compra");
    }

    @PreDestroy
    public void destruir() {
        log.info("Destruyendo carro de compra");
    }

    public void addItem(ItemCarro itemCarro) {
        if(items.contains(itemCarro)) {
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(item -> item.equals(itemCarro))
                    .findFirst();
            if(optionalItemCarro.isPresent()) {
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad() + 1);
            }
        } else {
            items.add(itemCarro);
        }
    }

    public List<ItemCarro> getItems() {
        return items;
    }

    public int getTotal() {
        return items.stream()
                .mapToInt(ItemCarro::getImporte)
                .sum();
    }

    public void removeProductos(List<String> productoIds) {
        if (productoIds != null) {
            productoIds.forEach(this::removeProducto);
            // que es lo mismo a:
            // productoIds.forEach(productoId -> removeProducto(productoId));
        }
    }

    public void removeProducto(String productoId) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    public void updateCantidad(String productoId, int cantidad) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    private Optional<ItemCarro> findProducto(String productoId) {
        return  items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }


}
