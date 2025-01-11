
package Conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Utilitarios.CUtilitario;


public class CConector {
    
    private Connection conn;
    private final String DB = "tecnologico";
    private final String USER = "root";
    private final String PSW = "";
    private final String URL = "jdbc:mysql://localhost:3306/" + DB;

      //************* Metodos **********
    public Connection conectar() {
        conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PSW);
            if (conn == null) {
               CUtilitario.msg_error("ERROR: CONEXION NO ESTABLECIDA", "Conexion");
               
            }
            else{
                 System.out.println("Conexion establecida con exito");
           
            }
            
        } catch (SQLException ex) {
            String cadena = "SQLException: " + ex.getMessage() + "\n"
                    + "SQLState: " + ex.getSQLState() + "\n"
                    + "VendorError: " + ex.getErrorCode();
            //CUtilitarios.msg_error(cadena, "conexion");
            
        }
        return conn;
    }

    public void cerrar_conexion(Connection conn) throws SQLException {
        conn.close();
    }

    
    
}
