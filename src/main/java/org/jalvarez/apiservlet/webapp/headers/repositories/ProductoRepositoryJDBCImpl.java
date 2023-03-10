package org.jalvarez.apiservlet.webapp.headers.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jalvarez.apiservlet.webapp.headers.annotations.MySqlConn;
import org.jalvarez.apiservlet.webapp.headers.annotations.RepositoryAnnotation;
import org.jalvarez.apiservlet.webapp.headers.models.Categoria;
import org.jalvarez.apiservlet.webapp.headers.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RepositoryAnnotation
public class ProductoRepositoryJDBCImpl implements Repository<Producto> {

    @Inject
    private Logger log;
    @Inject
    @MySqlConn
    private Connection conn;
//    public ProductoRepositoryJDBCImpl(Connection conn) {
//        this.conn = conn;
//    }

    @PostConstruct
    public void inicializar(){
        log.info("Inicializando el beans: " + this.getClass().getName());
    }

    @PreDestroy
    public void destruir(){
        log.info("Destruyendo el beans: " + this.getClass().getName());
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p " +
                    "INNER JOIN categorias as c ON p.categoria_id = c.id ORDER BY p.id ASC")) {
            while (rs.next()) {
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement smt = conn.prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p " +
                "INNER JOIN categorias as c ON p.categoria_id = c.id WHERE p.id = ?")) {
            smt.setLong(1, id);

            try (ResultSet rs = smt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }


    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if(producto.getId() != null && producto.getId() > 0) {
            sql = "UPDATE productos SET nombre = ?, precio = ?, sku = ?, categoria_id = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO productos (nombre, precio, sku, categoria_id, fecha_registro) VALUES (?, ?, ?, ?, ?)";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setString(3, producto.getSku());
            stmt.setLong(4, producto.getCategoria().getId());
            if(producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(5, producto.getId());
            } else {
                stmt.setDate(5, Date.valueOf(producto.getFechaRegistro()));
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setSku(rs.getString("sku"));
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        Categoria c = new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categoria"));
        p.setCategoria(c);
        return p;
    }

}
