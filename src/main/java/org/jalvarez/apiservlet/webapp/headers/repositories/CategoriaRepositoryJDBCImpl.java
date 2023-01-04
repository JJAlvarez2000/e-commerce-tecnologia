package org.jalvarez.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import org.jalvarez.apiservlet.webapp.headers.annotations.MySqlConn;
import org.jalvarez.apiservlet.webapp.headers.annotations.RepositoryAnnotation;
import org.jalvarez.apiservlet.webapp.headers.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RepositoryAnnotation
public class CategoriaRepositoryJDBCImpl implements Repository<Categoria> {
    private Connection conn;

    // en vez de atributos como por ejemplo el de producto lo haremos por
    // constructor

    @Inject
    public CategoriaRepositoryJDBCImpl(@MySqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias")) {
            while (rs.next()) {
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement smt = conn.prepareStatement("SELECT * FROM categorias WHERE id = ?")) {
            smt.setLong(1, id);
            try (ResultSet rs = smt.executeQuery()) {
                if (rs.next()) {
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("id"));
        categoria.setNombre(rs.getString("nombre"));
        return categoria;
    }
}
