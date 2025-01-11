/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import Conector.CConector;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import Utilitarios.CUtilitario;


public class CConsultas {
    
     //************ Atributos ************
    private Connection conn = null;
    private Statement stmt = null; //Capacidad para traducir las query
    private ResultSet rs = null;
    private final CConector conector = new CConector();
    private ArrayList<String[]> resultados;
    private ArrayList<String> resultadosCombos;
    private String valorObtenido = null;

    //************ Metodos ************
    public String buscarValor(String consulta) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conectar();
        //2. Ejecutar la query(consulta)
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(consulta);
            if (rs == null) {
                CUtilitario.msg_adver("Elementos no encontrados", "buscar objetos");
            } else {
                while (rs.next()) {
                    valorObtenido = rs.getString(1);
                }
            }
        } catch (SQLException ex) {
            String cadena = "SQLException: " + ex.getMessage() + "\n"
                    + "SQLState: " + ex.getSQLState() + "\n"
                    + "VendorError: " + ex.getErrorCode();
            CUtilitario.msg_error(cadena, "Conexion");
        } //3. 
        finally {
            //Cerrar los resultados
            try {
                rs.close();
            } catch (SQLException e) {
            }
            //Cerrar el statement
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            //cerrar conexion
            conector.cerrar_conexion(conn);
        }
        return valorObtenido;
    }
    
    public ArrayList<String> buscarValoresCombos(String consulta) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conectar();
        //2. Ejecutar la query(consulta)
        try {
            resultadosCombos = new ArrayList<>();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(consulta);
            if (rs == null) {
                CUtilitario.msg_adver("Elementos no encontrados", "buscar objetos");
            } else {
                while (rs.next()) {
                    resultadosCombos.add(rs.getString(1));
                }
            }
        } catch (SQLException ex) {
            String cadena = "SQLException: " + ex.getMessage() + "\n"
                    + "SQLState: " + ex.getSQLState() + "\n"
                    + "VendorError: " + ex.getErrorCode();
              CUtilitario.msg_error(cadena, "Conexion");
        } //3. 
        finally {
            //Cerrar los resultados
            try {
                rs.close();
            } catch (SQLException e) {
            }
            //Cerrar el statement
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            //cerrar conexion
            conector.cerrar_conexion(conn);
        }
        return resultadosCombos;
    }
    
    public ArrayList<String[]> buscarValores(String consulta, int numCampos) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conectar();
        //2. Ejecutar la query(consulta)
        try {
            resultados = new ArrayList<>();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(consulta);
            if (rs == null) {
                CUtilitario.msg_adver("Elementos no encontrados", "buscar objetos");
            } else {
                while (rs.next()) {
                    String[] arregloResultados = new String[numCampos];
                    for (int i = 0; i < numCampos; i++) {
                        arregloResultados[i] = rs.getString(i + 1);
                    }
                    resultados.add(arregloResultados);
                }
            }
        } catch (SQLException ex) {
            String cadena = "SQLException: " + ex.getMessage() + "\n"
                    + "SQLState: " + ex.getSQLState() + "\n"
                    + "VendorError: " + ex.getErrorCode();
            CUtilitario.msg_error(cadena, "Conexion");
        } //3. 
        finally {
            //Cerrar los resultados
            try {
                rs.close();
            } catch (SQLException e) {
            }
            //Cerrar el statement
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            //cerrar conexion
            conector.cerrar_conexion(conn);
        }
        return resultados;
    }
    
    public boolean inserta(String consulta) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conectar();
        //2, Ejecutar la query
        try {
            PreparedStatement pstmt = conn.prepareStatement(consulta);
//            PreparedStatement pstmt = conn.prepareStatement();
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
          
              CUtilitario.msg_error("Error: \n" + e.getMessage(), "Inserta ");
        } finally {
            //3. Cerrar conex
            conector.cerrar_conexion(conn);
        }
        return false;
    }
    
    public boolean elimina(String consulta) throws SQLException {
        //1. Abrir la conexion
        conn = conector.conectar();
        //2. Correr la query
        try {
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
           CUtilitario.msg_error("Error: " + e.getMessage(), "Elimina");
        } finally {
            //3. Cerrarla conexion
            conector.cerrar_conexion(conn);
        }
        return false;
    }
    
    public boolean actualiza(String consulta) throws SQLException {
        conn = conector.conectar();
        try {
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
             CUtilitario.msg_error("Error: " + e.getMessage(), "Actualiza Objeto");
        } finally {
            //3. Cerrarla conexion
            conector.cerrar_conexion(conn);
        }
        return false;
    }
    
    public boolean buscar(String consulta) throws SQLException {
        conn = conector.conectar();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(consulta);
            if (rs == null) {
                return false;
            } else {
                while (rs.next()) {
                    if (rs.getString(1) == null) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            
        } catch (SQLException e) {
              CUtilitario.msg_error("Error: " + e.getMessage(), "Buscar objeto");
        } finally {
            //3. Cerrarla conexion
            conector.cerrar_conexion(conn);
        }
        return false;
    }
    
    
}
