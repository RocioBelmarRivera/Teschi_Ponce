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
public class CActualiza {

    private final CConsultas cnslt = new CConsultas();
    private String consulta;

    public boolean actualiza_Asig_curri(String id, int h_t, int h_p, int creditos, int unidades, int id_curricular) throws SQLException {
        consulta = "CALL actualizar_asignatura_curricular(" + id + "," + h_t + "," + h_p + "," + creditos + "," + unidades + "," + id_curricular + ");";
        return cnslt.actualiza_Asig_Curri(consulta);
    }

    public boolean ActualizaAsignaturaCurricular(String claveAsignaturaCurricular, String horasTeoricas, String horasPracticas, String creditos, String numeroUnidades, String id_asignatura) throws SQLException {
        consulta = "CALL actualizar_asignatura_curricular('" + claveAsignaturaCurricular + "'," + horasTeoricas + "," + horasPracticas + "," + creditos + "," + numeroUnidades + "," + id_asignatura + ");";
        return cnslt.actualiza_Asig_Curri(consulta);
    }

    public ArrayList<String[]> busca_objetos_model() throws SQLException {
        consulta = "SELECT * FROM asignatura_curricular WHERE 1";
        return cnslt.buscar_objetos1(consulta);
    }

}
