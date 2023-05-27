package org.aguzman.apiservlet.webapp.headers.repositories;

import org.aguzman.apiservlet.webapp.headers.models.Producto;

import java.sql.*;
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
                getProductos(productos, rs);
            }
        }

        return productos;
    }

    private static void getProductos(List<Producto> productos, ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setTipo(rs.getString("categoria"));
        productos.add(p);
    }

    private Producto getProducto(ResultSet rs) throws SQLException{
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setTipo(rs.getString("categoria"));
        return p;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        try(PreparedStatement statement = conn.prepareStatement("select p.*, c.nombre as categoria from productos as p " +
                "inner join categorias as c ON (p.categoria_id = c.id) where p.id = ?")){
            statement.setLong(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    producto = getProducto(resultSet);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
}
