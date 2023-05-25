package org.aguzman.apiservlet.webapp.headers.repositories;

import org.aguzman.apiservlet.webapp.headers.models.Producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImp implements Repository<Producto>{
    private Connection conn;

    public ProductoRepositoryJdbcImp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try(Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p inner join categorias as c " +
                                                       " ON (p.categoria_id = c.id)")){
            while(rs.next()){
                getProducto(productos, rs);
            }
        }

        return productos;
    }

    private static void getProducto(List<Producto> productos, ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setTipo(rs.getString("categoria"));
        productos.add(p);
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
}
