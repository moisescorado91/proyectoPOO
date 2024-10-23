/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proveedor;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Moises Molina
 */
public class Proveedor {

    public void lista_proveedores() {
        Conexion objetoConexion = new Conexion();
        objetoConexion.conectar();
        Connection conexion = objetoConexion.getConexion();

        try {
            //consulta a ejecutar
            String query = "SELECT id_proveedor, nombres, apellidos FROM proveedor";
            PreparedStatement stmt = conexion.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();
            
            //valido si obtuvo registros
            if(!resultado.isBeforeFirst()){
                System.out.println("No se encontraron proveedores");
            }
            //iteramos el resultado
            while (resultado.next()) {
                System.out.println("ID: " + resultado.getInt("id_proveedor") + ", Nombres: " + resultado.getString("nombres") + ", Apellidos: " + resultado.getString("apellidos"));
            }
            resultado.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Error al mostrar listado de proveedores");
            System.out.println(e.getMessage());
        } finally {
            objetoConexion.desconectar();
        }
    }
}
