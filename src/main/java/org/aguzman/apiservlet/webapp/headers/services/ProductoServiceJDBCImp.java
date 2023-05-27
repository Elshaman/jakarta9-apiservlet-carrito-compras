package org.aguzman.apiservlet.webapp.headers.services;

import org.aguzman.apiservlet.webapp.headers.models.Producto;
import org.aguzman.apiservlet.webapp.headers.repositories.ProductoRepositoryJdbcImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJDBCImp implements ProductoService{

    private ProductoRepositoryJdbcImp repositoryJdbcImp;

    public ProductoServiceJDBCImp(Connection connection) {
        this.repositoryJdbcImp = new ProductoRepositoryJdbcImp(connection);

    }
    @Override
    public List<Producto> listar() {
        try {
            return repositoryJdbcImp.listar();
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), new Throwable().getCause());
        }
    }



    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbcImp.porId(id));
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), new Throwable().getCause());
        }
    }
}
