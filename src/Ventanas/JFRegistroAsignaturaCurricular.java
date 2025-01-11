/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventanas;

import Utilitarios.CUtilitario;
import crud.CBusca;
import crud.CCargaBox;
import crud.CInserta;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class JFRegistroAsignaturaCurricular extends javax.swing.JFrame {

    /**
     * Creates new form JFRegistroAsignaturaCurricular
     */
    public JFRegistroAsignaturaCurricular() {
        initComponents();
        
        cargaComboBox(JCBNombreAsignatura,1);
    }
    
    private DefaultComboBoxModel listas;
    private ArrayList<String> datosListas = new ArrayList<>();
    private final CCargaBox queryCarga = new CCargaBox();
    private final CInserta queryInserta1= new CInserta();
    private CBusca queryBusca2 = new CBusca();
    
    String regexclaveAsignaturaCurricular="^[A-Z]{3}-\\d{4}$";
    String regexhorasTeoricas="^[0-9]$";
    String regexhorasPractica="^[0-9]$";
    String regexcreditos="^[0-9]$";
    String regexnumeroUnidades="^[0-9]$";
 
    
    
    //este incluye numeros negativos
    //"^-?[0-9]$";

    String claveAsignaturaCurricular;
    String horasTeoricas;
    String horasPracticas;
    String creditos;
    String numeroUnidades;
    String nombreAsignatura;
    
    
    
  public void limpiar(){
    
    jTHorasTeoricas.setText("");
    jTHorasPracticas.setText("");
    jTNumeroUnidades.setText("");
    jTCreditos.setText("");
    jTClaveAsignaturaCurricular.setText("");
    // Limpiar el JComboBox, restaurándolo a la selección inicial o vacía
    JCBNombreAsignatura.setSelectedIndex(0);  // 0 selecciona el primer ítem de la lista, o puedes usar -1 para deseleccionar
    
    }
  
    public void asignaValores(){
    
  claveAsignaturaCurricular=jTClaveAsignaturaCurricular.getText().trim();
  horasPracticas=jTHorasPracticas.getText().trim();
  horasTeoricas=jTHorasTeoricas.getText().trim();
  numeroUnidades=jTNumeroUnidades.getText().trim();
  creditos=jTCreditos.getText().trim();

  
  // Obtener el valor seleccionado en el JComboBox
    nombreAsignatura = (String) JCBNombreAsignatura.getSelectedItem();  // Asignamos el valor seleccionado a la variable estado
    
     // Eliminar espacios al principio, al final y en medio de las cadenas
     //claveAsignaturaCurricular = jTClaveAsignaturaCurricular.getText().trim().replaceAll("\\s+", "");
    //horasPracticas = jTHorasPracticas.getText().trim().replaceAll("\\s+", "");
    //horasTeoricas = jTHorasTeoricas.getText().trim().replaceAll("\\s+", "");
    //numeroUnidades = jTNumeroUnidades.getText().trim().replaceAll("\\s+", "");
    //creditos = jTCreditos.getText().trim().replaceAll("\\s+", "");
    
    }
    
    
    
       public boolean validaCampos() {
    return validaCampo(claveAsignaturaCurricular, jTClaveAsignaturaCurricular, regexclaveAsignaturaCurricular, "Ingrese la clave de la asignatura", "EL formato o extension de la clave es incorrecta")
    && validaCampo(horasPracticas, jTHorasPracticas, regexhorasPractica, "Ingrese las horas practicas", "Solo puede agregar numeros en las horas practicas")
    && validaCampo(horasTeoricas, jTHorasTeoricas, regexhorasTeoricas, "Ingrese las horas teoricas", "Solo puede agregar un numeros en las horas teoricas")
    && validaCampo(numeroUnidades, jTNumeroUnidades, regexnumeroUnidades, "Ingrese el numero de unidades", "Solo puede agregar un numeros en las unidades")
    && validaCampo(creditos, jTCreditos, regexcreditos, "Ingrese el numero de rceditos", "Solo puede agregar un numeros en los creditos");
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
             
                     
 
    
    
       //asignamos valores al combo box
      public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    datosListas = queryCarga.cargaNombreAsignatura();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
            }
        } catch (SQLException e) {
        }
    }
      
      
      
       
         
     public boolean verificaClaveCurricular(String claveAsignaturaCurricular){
        
        try {
           CBusca queryBusca1 = new CBusca();   
         String resultado= queryBusca1.buscaClaveAsignaturaCurricular(claveAsignaturaCurricular);
         
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
     
     
      
        public boolean insertaAsignaturaCurricular(String claveAsignaturaCurricular,String horasTeoricas,String horasPracticas, String creditos,String numeroUnidades,String nombreAsignatura){ 
        boolean bandera=false;
        
        try {
             int id_asignatura=queryBusca2.buscaIDAsignatura(nombreAsignatura);
            
            
            
            
             queryInserta1.insertaAsignaturaCurricular(claveAsignaturaCurricular, horasTeoricas, horasPracticas, creditos, numeroUnidades, id_asignatura);
             CUtilitario.msg("Se enviaron los datos exitosamente de la asignatura curricular", "inserta asignatura curricular");
           bandera=true;
             
             
            // return ultimoID;
        } catch (Exception e) {
        // Registrar el error o manejarlo de alguna forma
      //  e.printStackTrace();  // Esto imprimirá el stack trace para ayudar en el diagnóstico del error.
        CUtilitario.msg_error("problema al insertar el tema. Por favor intente nuevamente.", "tema");
         return false;
    }
    return bandera;
    
   }
      
        
    
    
    
     
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        JCBNombreAsignatura = new javax.swing.JComboBox<>();
        jTNumeroUnidades = new javax.swing.JTextField();
        jTCreditos = new javax.swing.JTextField();
        jTHorasPracticas = new javax.swing.JTextField();
        jTHorasTeoricas = new javax.swing.JTextField();
        jTClaveAsignaturaCurricular = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel3.setText("Registro asignatura curricular");

        jButton1.setText("REGRESAR");

        jButton2.setText("LIMPIAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("ENVIAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(33, 33, 33)
                .addComponent(jButton3)
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        jLabel1.setText("Clave de la asignatura curricular");

        jLabel2.setText("Horas teoricas");

        jLabel4.setText("Horas practicas");

        jLabel5.setText("Creditos");

        jLabel6.setText("Numero de unidades");

        jLabel7.setText("Nombre de la asignatura");

        jTNumeroUnidades.setToolTipText("Solo puede agregar un numero");

        jTCreditos.setToolTipText("Solo puede agregar un numeros");

        jTHorasPracticas.setToolTipText("Solo puede agregar numeros");

        jTHorasTeoricas.setToolTipText("Solo puede agregar un numeros");

        jTClaveAsignaturaCurricular.setToolTipText("Escriba la clave siguiendo el siguiente formato \"Tres letras mayusculas-cuatro numeros\" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTClaveAsignaturaCurricular, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTHorasTeoricas, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(138, 138, 138)
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTHorasPracticas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(168, 168, 168))
                                            .addComponent(JCBNombreAsignatura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTNumeroUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTClaveAsignaturaCurricular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTHorasTeoricas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTHorasPracticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTNumeroUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(JCBNombreAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        if (validaCampos()) {
        
            asignaValores();
            if (verificaClaveCurricular(claveAsignaturaCurricular)) {
                CUtilitario.msg_error("La clave de la asignatura curricular ya esta registrada cambiela", "Clave asignatura curricular");
            }else{
                
                insertaAsignaturaCurricular(claveAsignaturaCurricular, horasTeoricas, horasPracticas, creditos, numeroUnidades, nombreAsignatura);
            
            
            
            }
            
            
            
        }else{
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(JFRegistroAsignaturaCurricular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFRegistroAsignaturaCurricular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFRegistroAsignaturaCurricular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFRegistroAsignaturaCurricular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFRegistroAsignaturaCurricular().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JCBNombreAsignatura;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTClaveAsignaturaCurricular;
    private javax.swing.JTextField jTCreditos;
    private javax.swing.JTextField jTHorasPracticas;
    private javax.swing.JTextField jTHorasTeoricas;
    private javax.swing.JTextField jTNumeroUnidades;
    // End of variables declaration//GEN-END:variables
}
