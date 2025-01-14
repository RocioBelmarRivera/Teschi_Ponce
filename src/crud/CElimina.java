/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CElimina {
    
     private String consulta;
    private final CConsultas cons = new CConsultas();

   
    
     public ArrayList<String[]> buscaCarrera() throws SQLException {
        consulta = "SELECT * FROM audita_carrera WHERE 1";
        return cons.busca(consulta);
    }

    
}
