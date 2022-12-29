package org.jalvarez.apiservlet.webapp.headers.services;

import org.jalvarez.apiservlet.webapp.headers.models.Categoria;
import org.jalvarez.apiservlet.webapp.headers.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();
    List<Categoria> listarCategoria();
    Optional<Producto> porId(Long id);
    Optional<Categoria> porIdCategoria(Long id);
    void guardar(Producto producto);
    void eliminar(Long id);

}
