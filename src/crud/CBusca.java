/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import java.sql.SQLException;


public class CBusca {
    private final CConsultas cnslt = new CConsultas();
    private String consulta;
    
    
    public String ultimoIdCarrera(String claveCarrera) throws SQLException {
    consulta = "SELECT nombreCarrera \n" +
"FROM carrera \n" +
"WHERE id_carrera = " + claveCarrera + ";";    // Esto obtiene el último id insertado de la sesión.
    return cnslt.buscarValor(consulta);  // Devuelve el último id insertado.
    }
    
    
      public String buscaCarrera(String nombreCarrera) throws SQLException {
    consulta = "SELECT nombreCarrera " +
               "FROM carrera " +
               "WHERE nombreCarrera LIKE '%" + nombreCarrera + "%';"; 
    return cnslt.buscarValor(consulta);
}
      
        public String buscaAsignatura(String nombreAsignatura) throws SQLException {
    consulta = "SELECT nombreAsignatura \n" +
"FROM asignatura \n" +
"WHERE nombreAsignatura LIKE '%" + nombreAsignatura + "%';"; 
    return cnslt.buscarValor(consulta);
}
        
     public String buscaClaveTema(String id_tema) throws SQLException {
    consulta = "SELECT id_tema\n" +
"FROM tema\n" +
"WHERE id_tema =  '" + id_tema + "';";  
    return cnslt.buscarValor(consulta);
}
     
     
       public String buscaTema(String nombreTema) throws SQLException {
    consulta = "SELECT nombreTema\n" +
"FROM tema\n" +
"WHERE nombreTema LIKE  '%" + nombreTema + "%';"; 
    return cnslt.buscarValor(consulta);
}
       
          public String buscaSubtema(String nombreSubtema) throws SQLException {
    consulta = "SELECT nombreSubtema\n" +
"FROM subtema\n" +
"WHERE nombreSubtema LIKE  '%" + nombreSubtema + "%';";  
    return cnslt.buscarValor(consulta);
}
          
     public String buscaGrupo(String grupo) throws SQLException {
    consulta = "SELECT grupo \n" +
"FROM grupo \n" +
"WHERE grupo LIKE '%" + grupo + "%';";  
    return cnslt.buscarValor(consulta);
}
     
     
      public String buscaClaveCiclo(String claveCiclo) throws SQLException {
    consulta = "SELECT id_ciclo\n" +
"FROM ciclo\n" +
"WHERE id_ciclo = '" + claveCiclo + "';";  
    return cnslt.buscarValor(consulta);
}
    
      
          public String buscaCiclo(String ciclo, String fechaInicio,String fechaTermino) throws SQLException {
    consulta = consulta = "SELECT id_ciclo FROM ciclo WHERE ciclo = '" + ciclo + "' AND fechaInicio = '" + fechaInicio + "' AND fechaTermino = '" + fechaTermino + "';";
    return cnslt.buscarValor(consulta);
}
          
                 public String buscaIDEstado(String estado) throws SQLException {
    consulta = consulta = "SELECT id_estado\n" +
"FROM estado\n" +
"WHERE nombreEstado LIKE '%" + estado + "%';";  
    return cnslt.buscarValor(consulta);
}
                 
    public int buscaUltimoIDEstado() throws SQLException {
    consulta = consulta = "SELECT MAX(id_estado) AS ultimo_id\n" +
"FROM estado;";  
    return  Integer.parseInt(cnslt.buscarValor(consulta));
}
    
       public int buscaUltimoIDDireccion() throws SQLException {
    consulta = consulta = "SELECT MAX(id_direccion) AS ultimo_id_direccion\n" +
"FROM direccion;";  
    return  Integer.parseInt(cnslt.buscarValor(consulta));
}
       
            public int buscaUltimoIDPersona() throws SQLException {
    consulta = consulta = "SELECT MAX(id_persona) AS ultimo_id_persona\n" +
"FROM persona;";  
    return  Integer.parseInt(cnslt.buscarValor(consulta));
}
    
                  
            public String buscaMatricula(String matricula) throws SQLException {
    consulta = consulta = "SELECT matriculaAlumno\n" +
"FROM alumno\n" +
"WHERE matriculaAlumno = '" + matricula + "';";  
    return  cnslt.buscarValor(consulta);
}
            
                             
      public String buscaMatriculaDocente(String matricula) throws SQLException {
    consulta = consulta = "SELECT matriculaDocente\n" +
"FROM docente\n" +
"WHERE matriculaDocente = '" + matricula + "';";  
    return  cnslt.buscarValor(consulta);
}
      
      
    public String buscaClaveAsignaturaCurricular(String claveAsignaturaCurricular) throws SQLException {
    consulta = consulta = "SELECT id_asignatura_curricular \n" +
"FROM asignatura_curricular \n" +
"WHERE id_asignatura_curricular = '" + claveAsignaturaCurricular + "';";  
    return  cnslt.buscarValor(consulta);
}
    
    
         
    public int buscaIDAsignatura(String nombreAsignatura) throws SQLException {
    consulta = consulta = "SELECT id_asignatura \n" +
"FROM asignatura \n" +
"WHERE nombreAsignatura LIKE '%" + nombreAsignatura + "%';";   
    return  Integer.parseInt(cnslt.buscarValor(consulta));
}
    
    
    
          
    public String buscaClaveAsignaturaCocurricular(String claveAsignaturaCocurricular) throws SQLException {
    consulta = consulta = "SELECT clave_cocurricular\n" +
"FROM asignatura_cocurricular\n" +
"WHERE clave_cocurricular = '" + claveAsignaturaCocurricular + "';";  
    return  cnslt.buscarValor(consulta);
}
    
       public String buscaTelefono(String telefono) throws SQLException {
    consulta = consulta = "SELECT telefono \n" +
"FROM telefono \n" +
"WHERE telefono =  '" + telefono + "';";  
    return  cnslt.buscarValor(consulta);
}
       
       
         
       public String buscaCorreo(String correo) throws SQLException {
    consulta = consulta = "SELECT correo \n" +
"FROM correo \n" +
"WHERE correo = '" + correo + "';";  
    return  cnslt.buscarValor(consulta);
}
       
              public String BuscaIDAsignaturaCurricular(String asignatura) throws SQLException {
    consulta = consulta = "SELECT asignatura_curricular.id_asignatura_curricular\n" +
"FROM asignatura_curricular\n" +
"JOIN asignatura\n" +
"ON asignatura_curricular.id_asignatura = asignatura.id_asignatura\n" +
"WHERE asignatura.nombreAsignatura LIKE '%" + asignatura + "%';";   
    return  cnslt.buscarValor(consulta);
}
              
              
                     
              public int BuscaIdCarrera(String carrera) throws SQLException {
    consulta = consulta = "SELECT id_carrera\n" +
"FROM carrera\n" +
"WHERE nombreCarrera = '" + carrera + "';";   
    return   Integer.parseInt(cnslt.buscarValor(consulta));
}
 
              
//                                
//              public String BuscaIdtema(String tema) throws SQLException {
//    consulta = consulta = "SELECT id_tema\n" +
//"FROM tema\n" +
//"WHERE nombreTema = '" + tema + "';";   
//    return   (cnslt.buscarValor(consulta));
//}
      
 public String BuscaIdtema(String tema) throws SQLException {
    consulta = "CALL ObtenerIdTema('" + tema + "');";   
    return (cnslt.buscarValor(consulta));
}

   
     
     
    
    
    
}
