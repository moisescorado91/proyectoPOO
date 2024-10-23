/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import productos.AgregarProducto;
import proveedor.AgregarProveedor;
import proveedor.Proveedor;
/**
 *
 * @author Moises Molina
 */
public class Main {

    public static void registrar_proveedor() throws SQLException {
        String p_nombre = "Moises Isaac";
        String p_apellido = "Molina Corado";
        String p_direccion = "Colonia de algun lugar";
        String p_correo = "moises@gmail.com";

        AgregarProveedor objeto_proveedor = new AgregarProveedor(p_nombre, p_apellido, p_direccion, p_correo);
        String respuesta = objeto_proveedor.registrar_proveedor();
        System.out.println(respuesta);
    }

    public static void agregar_producto() throws SQLException {
        Random random = new Random();
        Proveedor obj_proveedor = new Proveedor();
        Scanner entrada = new Scanner(System.in);
        int codigo = random.nextInt(9999);
        String nombre = "Acetaminofen";
        int codigo_barra = random.nextInt(9999);
        String fecha_creacion = "2024-10-23";
        double precio = 10.20;
        String fecha_vencimiento = "2025-10-23";
        int stock = 20;
        String ubicacion = "Tercer pasillo";
        
        System.out.println("Ingresa el ID del codeudor");
        obj_proveedor.lista_proveedores();
        int id_proveedor = entrada.nextInt();
        
        AgregarProducto producto = new AgregarProducto(String.valueOf(codigo), nombre, String.valueOf(codigo_barra), fecha_creacion, fecha_vencimiento, precio, stock, ubicacion, id_proveedor);
        String respuesta = producto.agregar_producto();
        System.out.println(respuesta);
    }

    public static void main(String[] args) {

        try {
            agregar_producto();
            //registrar_proveedor();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
