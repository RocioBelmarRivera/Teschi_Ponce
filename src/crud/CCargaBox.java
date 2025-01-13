/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import java.sql.SQLException;
import java.util.ArrayList;

public class CCargaBox {
    
     private final CConsultas cnslt = new CConsultas();
    private String consulta;
    
    
      public ArrayList<String> cargaEstado() throws SQLException {
        consulta = "SELECT nombreEstado FROM estado;";
        return cnslt.buscarValoresCombos(consulta);
    }
      
      
       public ArrayList<String> cargaNombreAsignatura() throws SQLException {
        consulta = "SELECT nombreAsignatura \n" +
        "FROM asignatura;";
        return cnslt.buscarValoresCombos(consulta);
    }
       
       
         public ArrayList<String> cargaNombreCarrera() throws SQLException {
        consulta = "SELECT nombreCarrera FROM carrera";
        return cnslt.buscarValoresCombos(consulta);
    }
         
         
        public ArrayList<String> cargaNombreAsignaturaCurricular() throws SQLException {
        consulta = "SELECT a.nombreAsignatura\n" +
        "FROM asignatura a\n" +
        "JOIN asignatura_curricular ac ON a.id_asignatura = ac.id_asignatura\n" +
        "ORDER BY ac.id_asignatura; ";
        return cnslt.buscarValoresCombos(consulta);
    }
        
        
        
         public ArrayList<String> cargaNombreTema() throws SQLException {
        consulta = "SELECT nombreTema FROM tema";
        return cnslt.buscarValoresCombos(consulta);
    }
         
        
             
         public ArrayList<String> cargaNombreSubtema() throws SQLException {
        consulta = "CALL ObtenerSubtemasOrdenados();";
        return cnslt.buscarValoresCombos(consulta);
    }
         
         
                 
//        public ArrayList<String> cargaNombreGrupo() throws SQLException {
//        consulta = "CALL ObtenerSubtemasOrdenados();";
//        return cnslt.buscarValoresCombos(consulta);
//    }
//        
        public ArrayList<String> cargaNombreGrupo() throws SQLException {
        String consulta = "CALL ObtenerGrupos();";
        return cnslt.buscarValoresCombos(consulta);
}


    public ArrayList<String> cargaNombreCompletoPersona() throws SQLException {
    // Cambiar la consulta para llamar al procedimiento almacenado 'ObtenerNombreCompleto'
    String consulta = "CALL ObtenerNombreCompleto();";
    return cnslt.buscarValoresCombos(consulta);
}
    
    public ArrayList<String> cargaDetallesCompletosVersion() throws SQLException {
    // Cambiar la consulta para llamar al procedimiento almacenado 'ObtenerDetallesCompletos'
    String consulta = "CALL ObtenerDetallesCompletos();";
    return cnslt.buscarValoresCombos(consulta);
}
    
    


public ArrayList<String> cargaNombreCompletosDocente() throws SQLException {
    // Cambiar la consulta para llamar al procedimiento almacenado 'ObtenerDocentesOrdenados'
    String consulta = "CALL ObtenerDocentesOrdenados();";
    return cnslt.buscarValoresCombos(consulta);
}

    
    
    

    
    
}
