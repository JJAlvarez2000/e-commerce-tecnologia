package org.jalvarez.apiservlet.webapp.headers.services;

import org.jalvarez.apiservlet.webapp.headers.exceptions.ServiceJDBCException;
import org.jalvarez.apiservlet.webapp.headers.models.Categoria;
import org.jalvarez.apiservlet.webapp.headers.models.Producto;
import org.jalvarez.apiservlet.webapp.headers.repositories.CategoriaRepositoryJDBCImpl;
import org.jalvarez.apiservlet.webapp.headers.repositories.ProductoRepositoryJDBCImpl;
import org.jalvarez.apiservlet.webapp.headers.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJDBCImpl implements ProductoService {
    private Repository<Producto> repositoryJdbc;
    private Repository<Categoria> repositoryJdbcCategoria;

    public ProductoServiceJDBCImpl(Connection connection) {
        this.repositoryJdbc = new ProductoRepositoryJDBCImpl(connection);
        this.repositoryJdbcCategoria = new CategoriaRepositoryJDBCImpl(connection);
    }

    @Override
    public List<Producto> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryJdbcCategoria.listar();
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbcCategoria.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(), throwables.getCause());
        }
    }
}
