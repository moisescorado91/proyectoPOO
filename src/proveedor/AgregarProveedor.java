/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proveedor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import conexion.Conexion;

/**
 *
 * @author Moises Molina
 */
public class AgregarProveedor {

    private String nombres;
    private String apellidos;

    public boolean validar_existencia() {
        return true;
    }

    public String agregar_proveedor() throws SQLException {

        Conexion conexionBD = new Conexion();
        conexionBD.conectar();
        Connection conexion = conexionBD.getConexion();

        try {
            /**
             * ***************************************************************
             * UTILIZAMOS TRANSACCIONES PARA HACER ROOLLBACK POR ALGUN ERROR *
             * *************************************************************
             */
            conexion.setAutoCommit(false);

            String insert_proveedor = "INSERT INTO proveedor (nombre, apellido) VALUES (?, ?)";
            PreparedStatement ejecutar = conexion.prepareStatement(insert_proveedor); 
            ejecutar.setString(1, this.nombres);
            ejecutar.setString(2, this.apellidos);
            ejecutar.executeUpdate();
            conexion.commit();

            return "Proveedor Agregado";
        } catch (SQLException e) {
            conexion.rollback();
            return "Error Inesperado " + e.getMessage();
        } finally {
            conexion.setAutoCommit(true);
            conexionBD.desconectar();
        }
    }

}
