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
     
     
      public ArrayList<String[]> buscaAlumno() throws SQLException {
        consulta = "SELECT * FROM auditoria_alumno WHERE 1";
        return cons.busca(consulta);
    }
      
       public ArrayList<String[]> buscaDocente() throws SQLException {
        consulta = "SELECT * FROM auditoria_docente WHERE 1";
        return cons.busca(consulta);
    }
       
        public ArrayList<String[]> buscaCiclo() throws SQLException {
        consulta = "SELECT * FROM auditoria_ciclo WHERE 1";
        return cons.busca(consulta);
    }
        
         public ArrayList<String[]> buscaTema() throws SQLException {
        consulta = "SELECT * FROM auditoria_tema WHERE 1";
        return cons.busca(consulta);
    }
         
        public ArrayList<String[]> buscaGrupo() throws SQLException {
        consulta = "SELECT * FROM auditoria_grupo WHERE 1";
        return cons.busca(consulta);
    }
        
        public ArrayList<String[]> buscaAsig() throws SQLException {
        consulta = "SELECT * FROM auditoria_asignatura WHERE 1";
        return cons.busca(consulta);
    }
        
         public ArrayList<String[]> buscaSub() throws SQLException {
        consulta = "SELECT * FROM auditoria_subtema WHERE 1";
        return cons.busca(consulta);
    }


    
}
