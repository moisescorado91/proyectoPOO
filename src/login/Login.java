package login;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Login {
    private String usuario;
    private String password;
    public Login(String usuario, String password){
        this.password = password;
        this.usuario = usuario;
    }

    public String acceso_sistema(){
    
        Conexion conexionBD = new Conexion();     // Creo una instancia de la clase Conexion
        conexionBD.conectar(); // ejecuto la conexion
        Connection conexion = conexionBD.getConexion();  // obtengo la conexion

        if (conexion != null) {
            try {
                Statement stmt = conexion.createStatement(); 
                ResultSet resultado = stmt.executeQuery("SELECT campo1 FROM tabla WHERE campo_usuario = "+this.usuario + " AND campo_password = " + this.password);
                
                //devuelve true o false
                if(resultado.next()){
                    
                    // iniciar algun nombre de usuario de sesion
                    // nombre_sesion = resultado.getString("usuario");
                    
                    //devolver acceso
                }else{
                    return "Usuario o contraseña incorrecta";
                }

               
                // Cerrar el ResultSet y el Statement
                resultado.close();
                stmt.close();

            } catch (SQLException e) {
                return "Error Inesperado " + e.getMessage();
            } finally {
                conexionBD.desconectar(); // cierro la conexion tenga exito o no
            }
        } else {
            return "Error al conectar al servidor";
        }
        return null;
    }
  
}
