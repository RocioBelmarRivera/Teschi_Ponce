/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import crud.CConsultas;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rocio
 */


public class CConsultasI {
    private final CConsultas cnslt = new CConsultas();
    private String consulta;
     
    
    public boolean busca_consulta1(String credencial) throws SQLException {
        consulta = "CALL ObtenerCalificacionesPorAlumno(" + credencial+");";
        return cnslt.actualiza_Asig_Curri(consulta);
    }
}
