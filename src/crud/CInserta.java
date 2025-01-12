/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import java.sql.SQLException;


public class CInserta {
    
    
    private final CConsultas cnslt = new CConsultas();
    private String consulta;
    
    
//    public boolean insertaCarrera1(int id_carrera, String nombreCarrera) throws SQLException {
//    consulta = "INSERT INTO carrera(id_carrera, nombreCarrera) VALUES ('"+id_carrera+"','"+nombreCarrera+"')";
//    return cnslt.inserta(consulta);
//}
    
    public boolean insertaCarrera(int id_carrera, String nombreCarrera) throws SQLException {
    consulta = "CALL InsertarCarrera(" + id_carrera + ", '" + nombreCarrera + "');";
    return cnslt.inserta(consulta);
}
//    
//    public boolean insertaAsignatura(String nombreAsignatura) throws SQLException {
//    consulta = "INSERT INTO asignatura (nombreAsignatura) \n" +
//    "VALUES ('"+nombreAsignatura+"');";
//    return cnslt.inserta(consulta);
//}
    public boolean insertaAsignatura(String nombreAsignatura) throws SQLException {
    consulta = "CALL InsertarAsignatura('" + nombreAsignatura + "');";
    return cnslt.inserta(consulta);
}

    
    
//      public boolean insertaTema(String id_tema, String nombreTema) throws SQLException {
//    consulta = "INSERT INTO tema(id_tema, nombreTema) VALUES ('"+id_tema+"','"+nombreTema+"');";
//    return cnslt.inserta(consulta);
//}
//      
      public boolean insertaTema(String id_tema, String nombreTema) throws SQLException {
    consulta = "CALL InsertarTema('" + id_tema + "', '" + nombreTema + "');";
    return cnslt.inserta(consulta);
}


      
       
//      public boolean insertaSubtema(String nombreSubtema) throws SQLException {
//    consulta = "INSERT INTO subtema(nombreSubtema) VALUES ('"+nombreSubtema+"');";
//    return cnslt.inserta(consulta);
//}
//      
      
      
      public boolean insertaSubtema(String nombreSubtema) throws SQLException {
    consulta = "CALL InsertarSubtema('" + nombreSubtema + "');";
    return cnslt.inserta(consulta);
}

      
      
      
      
      
    public boolean insertaGrupo(String grupo) throws SQLException {
    consulta = "CALL InsertarGrupo('"+grupo+"');";
    return cnslt.inserta(consulta);
}
//    public boolean insertaGrupo1(String grupo) throws SQLException {
//        consulta = "INSERT INTO grupo(grupo) VALUES ('" + grupo + "');";
//        return cnslt.inserta(consulta);
//    }
  
        
//    public boolean insertaCiclo(String claveCiclo, String ciclo, String fechaInicio, String fechaTermino) throws SQLException {
//    consulta = "INSERT INTO ciclo(id_ciclo, ciclo, fechaInicio, fechaTermino) VALUES ('"+claveCiclo+"','"+ciclo+"','"+fechaInicio+"','"+fechaTermino+"');";
//    return cnslt.inserta(consulta);
//}
    
    
    public boolean insertaCiclo(String claveCiclo, String ciclo, String fechaInicio, String fechaTermino) throws SQLException {
    consulta = "CALL InsertarCiclo('" + claveCiclo + "', '" + ciclo + "', '" + fechaInicio + "', '" + fechaTermino + "');";
    return cnslt.inserta(consulta);
}

    
    
      public boolean insertaDireccion(String id_estado, String colonia, String codigoPostal, String calle,String numInterior,String numExterior) throws SQLException {
    consulta = "INSERT INTO direccion( id_estado, colonia, codigoPostal, calle, numInterior, numExterior) VALUES ("+id_estado+",'"+colonia+"',"+codigoPostal+",'"+calle+"','"+numInterior+"','"+numExterior+"');";
    return cnslt.inserta(consulta);
}
    
//      public boolean insertaPersona(String apellidoPaterno,String apellidoMaterno, String nombres, int id_direccion) throws SQLException {
//    consulta = "INSERT INTO persona (A_paterno, A_materno, nombres, id_direccion)\n" +
//"VALUES ('"+apellidoPaterno+"', '"+apellidoMaterno+"', '"+nombres+"', "+id_direccion+");";
//    return cnslt.inserta(consulta);
//}
//      
      
      
      public boolean insertaPersona(String apellidoPaterno, String apellidoMaterno, String nombres, int id_direccion) throws SQLException {
    consulta = "CALL InsertarPersona('" + apellidoPaterno + "', '" + apellidoMaterno + "', '" + nombres + "', " + id_direccion + ");";
    return cnslt.inserta(consulta);
}

      
//           public boolean insertaAlumno(String matricula, int id_persona) throws SQLException {
//    consulta = "INSERT INTO alumno(matriculaAlumno, id_persona) VALUES ('"+matricula+"',"+id_persona+");";
//    return cnslt.inserta(consulta);
//}
           
           public boolean insertaAlumno(String matricula, int id_persona) throws SQLException {
    consulta = "CALL InsertarAlumno('" + matricula + "', " + id_persona + ");";
    return cnslt.inserta(consulta);
}

           
            public boolean insertaTelefono(String telefono, int id_persona) throws SQLException {
    consulta = "INSERT INTO telefono(telefono,id_persona) VALUES ('"+telefono+"',"+id_persona+");";
    return cnslt.inserta(consulta);
}
            
            public boolean insertaCorreo(String correo, int id_persona) throws SQLException {
    consulta = "INSERT INTO correo(correo,id_persona) VALUES ('"+correo+"',"+id_persona+");";
    return cnslt.inserta(consulta);
}
           
           
//    public boolean insertaDocente(String matricula, int id_persona) throws SQLException {
//    consulta = "INSERT INTO docente(matriculaDocente, id_persona) VALUES ('"+matricula+"',"+id_persona+");";
//    return cnslt.inserta(consulta);
//}
    
    
    public boolean insertaDocente(String matricula, int id_persona) throws SQLException {
    consulta = "CALL InsertarDocente('" + matricula + "', " + id_persona + ");";
    return cnslt.inserta(consulta);
}

    
//     public boolean insertaAsignaturaCurricular(String claveAsignaturaCurricular,String horasTeoricas,String horasPracticas, String creditos,String numeroUnidades,int id_asignatura) throws SQLException {
//    consulta = "INSERT INTO asignatura_curricular(id_asignatura_curricular, horas_T, horas_P, creditos,numUnidades, id_asignatura) "
//            + "VALUES ('"+claveAsignaturaCurricular+"',"+horasTeoricas+","+horasPracticas+","+creditos+","+numeroUnidades+","+id_asignatura+");";
//    return cnslt.inserta(consulta);
//}
     public boolean insertaAsignaturaCurricular(String claveAsignaturaCurricular, String horasTeoricas, String horasPracticas, String creditos, String numeroUnidades, int id_asignatura) throws SQLException {
    consulta = "CALL InsertarAsignaturaCurricular('" + claveAsignaturaCurricular + "', " + horasTeoricas + ", " + horasPracticas + ", " + creditos + ", " + numeroUnidades + ", " + id_asignatura + ");";
    return cnslt.inserta(consulta);
}

     
     
//        public boolean insertaAsignaturaCocurricular(String claveAsignaturaCocurricular, int id_asignatura) throws SQLException {
//    consulta = "INSERT INTO asignatura_cocurricular(clave_cocurricular, id_asignatura) VALUES ('"+claveAsignaturaCocurricular+"',"+id_asignatura+");";
//    return cnslt.inserta(consulta);
//}
        
        public boolean insertaAsignaturaCocurricular(String claveAsignaturaCocurricular, int id_asignatura) throws SQLException {
    consulta = "CALL InsertarAsignaturaCocurricular('" + claveAsignaturaCocurricular + "', " + id_asignatura + ");";
    return cnslt.inserta(consulta);
}

        
        
         
        public boolean insertaCarreraAsignatura(String id_asignatura_curricular,int id_carrera) throws SQLException {
    consulta = "INSERT INTO carrera_asignatura(id_asignatura_curricular, id_carrera) VALUES ('"+id_asignatura_curricular+"',"+id_carrera+");";
    return cnslt.inserta(consulta);
}
        
        
      public boolean insertaAsignaturaTema(String id_asignatura_curricular, String id_tema) throws SQLException {
    consulta = "CALL InsertarAsignaturaTema('" + id_asignatura_curricular + "', '" + id_tema + "');";
    return cnslt.inserta(consulta);
}
      
  

    public boolean insertaTemaSubtema(String id_tema, String id_subtema) throws SQLException {
    consulta = "CALL InsertarTemaSubtema('" + id_tema + "', " + id_subtema + ");";
    return cnslt.inserta(consulta);
}

        
    
      
}
