/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventanas;

import Utilitarios.CUtilitario;
import crud.CBusca;
import java.sql.SQLException;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class JFPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form JFPrincipal
     */
    public JFPrincipal() {
        initComponents();
                   // Ajustar el tamaño automáticamente
        pack();
        
        // Centrar el JFrame en la pantalla
        setLocationRelativeTo(null);
    }
    
    
     private final CBusca queryBusca1 = new CBusca();
     private final CBusca queryBusca2 = new CBusca();
     private final CBusca queryBusca3 = new CBusca();
    
    
    String regexmatricula = "^[A-Za-z0-9-]{1,10}$";
    String regexcorreo = "^[A-Za-z0-9-]+@teschi\\.edu\\.mx$";


    
    
    String matricula;
    String correo;
    
    
    public void limpiar(){
    jTMatricula.setText("");
    jTCorreo.setText("");
    
    }
       
       
                   
    public void asignaValores(){    
    matricula=(String) jTMatricula.getText().trim();
    // Obtener el valor seleccionado en el JComboBox
    correo = (String) jTCorreo.getText().trim();// Asignamos el valor seleccionado a la variable estado
 
    }
    
    
    
     
        public String devuelveCadena(JTextField campo, String regex) {
        String cadena = null;
        cadena = campo.getText();
        if (cadena.isEmpty()) {
            cadena = null;
        } else if (cadena.matches(regex)) {
            return cadena;
        } else {
            cadena = "NoValido";
        }
        return cadena;
    }
        
        
             public boolean validaCampo(String campoTexto, JTextField campo, String regex, String mensajeVacio, String mensajeInvalido) {
        boolean valida = true;
        campoTexto = devuelveCadena(campo, regex);
        if (campoTexto == null) {
            CUtilitario.msg_adver(mensajeVacio, "Registro terminal");
            valida = false;
        } else if (campoTexto.equals("NoValido")) {
            CUtilitario.msg_error(mensajeInvalido, "Registro terminal");
            valida = false;
        } else {
            valida = true;
        }
        return valida;
    }
          
          
    public boolean validaCampos() {
    return validaCampo(matricula, jTMatricula, regexmatricula, "Ingrese la matricula", "Solo puede agregar como maximo 10 carcateres sin espacios")
    && validaCampo(correo, jTCorreo, regexcorreo, "Ingrese el correo", "el formato del correo es incorrecto yla extension de este es @teschi.edu.mx");
    }
    
    
    
    
    
    
    
         
     public boolean verificaCorreoDocente(String correo){
        
        try {
           CBusca queryBusca5 = new CBusca();   
         String resultado= queryBusca5.obtenerIdDocentePorCorreo(correo);
         
            if (resultado==null) {
                
            return false;
                
            }else{
            return true;
            }
            
            
         } catch (Exception e) {
        // Registrar el error o manejarlo de alguna forma
      //  e.printStackTrace();  // Esto imprimirá el stack trace para ayudar en el diagnóstico del error.
        CUtilitario.msg_error("Hubo un problema en la el nombre del subtema. Por favor intente nuevamente.", "Error de conexión");
        return false;  // Retornamos false en caso de error
    }
    } 
     
         
         
     public boolean verificaMatriculaDocente(String matricula ,String correo ){
        
        try {
           CBusca queryBusca5 = new CBusca();   
         String resultado= queryBusca5.obtenerMatriculaDocentePorMatriculaYCorreo(matricula, correo);
          
         
            if (resultado==null) {
                
            return false;
                
            }else{
            return true;
            }
            
            
         } catch (Exception e) {
        // Registrar el error o manejarlo de alguna forma
      //  e.printStackTrace();  // Esto imprimirá el stack trace para ayudar en el diagnóstico del error.
        CUtilitario.msg_error("Hubo un problema verifica matricula docfente. Por favor intente nuevamente.", "Error de conexión");
        return false;  // Retornamos false en caso de error
    }
    } 
     
     
     
      public boolean verificaCorreoAlumno(String correo){
        
        try {
           CBusca queryBusca5 = new CBusca();   
         String resultado= queryBusca5.obtenerMatriculaAlumnoPorCorreo(correo);
         
            if (resultado==null) {
                
            return false;
                
            }else{
            return true;
            }
            
            
         } catch (Exception e) {
        // Registrar el error o manejarlo de alguna forma
      //  e.printStackTrace();  // Esto imprimirá el stack trace para ayudar en el diagnóstico del error.
        CUtilitario.msg_error("Hubo un problema en la el nombre del subtema. Por favor intente nuevamente.", "Error de conexión");
        return false;  // Retornamos false en caso de error
    }
    }
      
      
      
      
      
      
       public boolean verificaMatriculaAlumno(String matricula, String correo){
        
        try {
           CBusca queryBusca5 = new CBusca();   
         String resultado= queryBusca5.obtenerMatriculaAlumnoPorMatriculaYCorreo(matricula, correo);
         
            if (resultado==null) {
                
            return false;
                
            }else{
            return true;
            }
            
            
         } catch (Exception e) {
        // Registrar el error o manejarlo de alguna forma
      //  e.printStackTrace();  // Esto imprimirá el stack trace para ayudar en el diagnóstico del error.
        CUtilitario.msg_error("Hubo un problema en la el nombre del subtema. Por favor intente nuevamente.", "Error de conexión");
        return false;  // Retornamos false en caso de error
    }
    }
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
        public boolean verificaCorreoJefeDivision(String correo){
        
        try {
           CBusca queryBusca5 = new CBusca();   
         String resultado= queryBusca5.obtenerMatriculaAdministradorPorCorreo(correo);
            if (resultado==null) {
                
            return false;
                
            }else{
            return true;
            }
            
            
         } catch (Exception e) {
        // Registrar el error o manejarlo de alguna forma
      //  e.printStackTrace();  // Esto imprimirá el stack trace para ayudar en el diagnóstico del error.
        CUtilitario.msg_error("Hubo un problema en la el nombre del subtema. Por favor intente nuevamente.", "Error de conexión");
        return false;  // Retornamos false en caso de error
    }
    } 
        
        
        
            
      
        public boolean verificaMatriculaJefeDivision(String matricula ,String correo){
        
        try {
           CBusca queryBusca5 = new CBusca();   
         String resultado= queryBusca5.obtenerMatriculaAdministradorPorMatriculaYCorreo(matricula, correo);
            if (resultado==null) {
                
            return false;
                
            }else{
            return true;
            }
            
            
         } catch (Exception e) {
        // Registrar el error o manejarlo de alguna forma
      //  e.printStackTrace();  // Esto imprimirá el stack trace para ayudar en el diagnóstico del error.
        CUtilitario.msg_error("Hubo un problema en la el nombre del subtema. Por favor intente nuevamente.", "Error de conexión");
        return false;  // Retornamos false en caso de error
    }
    } 
     
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
       
        public boolean BuscaCuenta(String correo, String matricula) throws SQLException {
        try {
        // Llamamos al método para buscar la cuenta con los parámetros curp y contrasena
      
        //String resultado = queryBusca1.buscaCuenta(curp, contrasena);        
        String resultado1 = queryBusca2.obtenerCorreoPorCorreo(correo);
            if (resultado1 == null) {
                
                CUtilitario.msg_adver("El correo no existe, ingrese correctamente el correo", "Inicio sesión");
                    // CUtilitario.msg("Se inserto correctamente la direccion", "inserta direccion");
                return false;
                
                
            }else{
                
               // String correoUsuario=correo;
                
                
                
                
                
                if (verificaCorreoAlumno(correo)) {
                    
                     //CUtilitario.msg("La cuenta es de un alumno", "Inicio sesión");
                     
                     if (verificaMatriculaAlumno(matricula, correo)) {
                        // CUtilitario.msg("La matricula del alumno es correcta", "Inicio sesión");
                            CUtilitario.msg("Inicio de sesion exitoso", "Inicio sesión");
                             JFMenu menu = new JFMenu();
                             menu.setVisible(true);
                         
                    }else{
                         
                        CUtilitario.msg_adver("La matricula del alumno es incorrecta", "Inicio sesión");  
                          
                     
                     }
                     
                     
                    
                }else{
                    
                    
                    if (verificaCorreoDocente(correo)) {
                        
                          //CUtilitario.msg("La cuenta es de un docente", "Inicio sesión");
                          
                          if (verificaMatriculaDocente(matricula, correo)) {
                          //  CUtilitario.msg("La matricula del docente es correcta", "Inicio sesión");
                            CUtilitario.msg("Inicio de sesion exitoso", "Inicio sesión");
                         
                        }else{
                          
                           CUtilitario.msg_adver("La matricula del docente es incorrecta", "Inicio sesión"); 
                          }
                          
                          
                          
                        
                    }else{
                        
                        
                        if (verificaCorreoJefeDivision(correo)) {
                            // CUtilitario.msg("La cuenta es de un jefe de divsión", "Inicio sesión");
                             
                             if (verificaMatriculaJefeDivision(matricula, correo)) {
                                 // CUtilitario.msg("La matricula del jefe de division es correcta", "Inicio sesión");
                                     CUtilitario.msg("Inisio de sesion exitoso", "Inicio sesión");
                                
                            }else{
                                
                           CUtilitario.msg_adver("La matricula del jefe de division es incorrecta", "Inicio sesión"); 
                             
                             }
                             
                             
                             
                        }else{
                          CUtilitario.msg_adver("La cuenta no existe", "Inicio sesión");
                             
                        
                        }
                    
                    
                    
                    
                    }
                
                
                }
   
                
            }  
        }catch (Exception e) {
        // Registrar el error o manejarlo de alguna forma
      //  e.printStackTrace();  // Esto imprimirá el stack trace para ayudar en el diagnóstico del error.
        CUtilitario.msg_error("Hubo un problema en la el nombre del subtema. Por favor intente nuevamente.", "Error de conexión");
       // return false;  // Retornamos false en caso de error
    }
        
        
        return false;
        }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTMatricula = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jTCorreo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));

        jLabel3.setFont(new java.awt.Font("Stencil", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("INICIO DE SESIóN");

        jTMatricula.setToolTipText("Ingrese su contraseña con un maximo de 8 caracteres");
        jTMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTMatriculaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("INICIA SESIÓN");

        jPanel6.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );

        jButton3.setBackground(new java.awt.Color(204, 204, 255));
        jButton3.setText("INICIA SESIÓN");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTCorreo.setToolTipText("Ingrese el CURP con maximo de 18 caracteres");
        jTCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTCorreoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("MATRÍCULA");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("CORREO");

        jButton5.setBackground(new java.awt.Color(204, 204, 255));
        jButton5.setText("LIMPIAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jButton5)
                        .addGap(56, 56, 56)
                        .addComponent(jButton3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(169, 169, 169))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel4)
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addGap(36, 36, 36)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTMatriculaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            
            if (validaCampos()) {
               asignaValores();
        BuscaCuenta(correo, matricula); 
            }else{
            
            }
            
            
            
          
   
        } catch (Exception e) {
        }
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTCorreoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jTCorreo;
    private javax.swing.JTextField jTMatricula;
    // End of variables declaration//GEN-END:variables
}
