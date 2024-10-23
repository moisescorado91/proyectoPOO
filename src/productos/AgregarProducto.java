/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import proveedor.Proveedor;
import java.sql.ResultSet;

/**
 *
 * @author Moises Molina
 */
public class AgregarProducto {

    private String codigo;
    private String nombre;
    private String codigo_barra;
    private String fecha_creacion;
    private double precio;
    private String fecha_vencimiento;
    private int stock;
    private String ubicacion;
    private int id_proveedor;

    public AgregarProducto(String codigo, String nombre, String codigo_barra, String fecha_creacion, String fecha_vencimiento, double precio, int stock, String ubicacion, int id_proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.codigo_barra = codigo_barra;
        this.fecha_creacion = fecha_creacion;
        this.precio = precio;
        this.fecha_vencimiento = fecha_vencimiento;
        this.stock = stock;
        this.ubicacion = ubicacion;
        this.id_proveedor = id_proveedor;
    }

    public String agregar_producto() throws SQLException {
        Conexion conexionBD = new Conexion();
        conexionBD.conectar();
        Connection conexion = conexionBD.getConexion();

        try {
            conexion.setAutoCommit(false);
            /**
             * REGISTRAMOS EL PRODUCTO
             */
            String producto_query = "INSERT INTO medicamento (codigo, nombre, codigo_barra, fecha_creacion, precio, fecha_vencimiento, stock, ubicacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            // le pasamos el insert y retornamos el id generado para este registro
            PreparedStatement ejecutar_producto = conexion.prepareStatement(producto_query, PreparedStatement.RETURN_GENERATED_KEYS);
            ejecutar_producto.setString(1, this.codigo);
            ejecutar_producto.setString(2, this.nombre);
            ejecutar_producto.setString(3, this.codigo_barra);
            ejecutar_producto.setString(4, this.fecha_creacion);
            ejecutar_producto.setDouble(5, this.precio);
            ejecutar_producto.setString(6, this.fecha_vencimiento);
            ejecutar_producto.setInt(7, this.stock);
            ejecutar_producto.setString(8, this.ubicacion);
            ejecutar_producto.executeUpdate();

            //obtengo el id del medicamento
            ResultSet generatedKeys = ejecutar_producto.getGeneratedKeys();
            int id_medicamento = generatedKeys.next() ? generatedKeys.getInt(1) : 0;

            generatedKeys.close();
            ejecutar_producto.close();
            if (id_medicamento != 0) {
                /**
                 * REGISTRAMOS LA RELACION ENTRE PRODUCTO Y PROVEEDOR
                 */
                String prov_medicamento_query = "INSERT INTO medicamento_proveedor(id_medicamento, id_proveedor) VALUES (?, ?)";
                PreparedStatement ejecutar_prov_medic = conexion.prepareStatement(prov_medicamento_query);
                ejecutar_prov_medic.setInt(1, id_medicamento);
                ejecutar_prov_medic.setInt(2, this.id_proveedor);
                ejecutar_prov_medic.executeUpdate();

                conexion.commit();
                return "Producto Registrado";

            } else {
                throw new Exception("Ocurrió un error con el indice de medicamento");
            }
        } catch (Exception e) {
            conexion.rollback();
            return e.getMessage();
        } finally {
            conexion.setAutoCommit(true);
            conexionBD.desconectar();
        }
    }
}
