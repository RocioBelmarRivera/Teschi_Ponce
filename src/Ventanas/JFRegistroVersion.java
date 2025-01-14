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


public class JFRegistroVersion extends javax.swing.JFrame {

  
    public JFRegistroVersion() {
        initComponents();
        
        cargaComboBox(JCBAsignatura, 1);
        cargaComboBox(JCCiclo, 2);
        cargaComboBox(JCOrigen, 3);
        
           // Ajustar el tamaño automáticamente
        pack();
        
        // Centrar el JFrame en la pantalla
        setLocationRelativeTo(null);
        
    }

    
         private DefaultComboBoxModel listas;
        private ArrayList<String> datosListas = new ArrayList<>();
        private final CCargaBox queryCarga = new CCargaBox();
        private final CCargaBox queryCarga1 = new CCargaBox();
        private final CInserta queryInserta1= new CInserta();
      
        private CBusca queryBusca2 = new CBusca();
        private CBusca queryBusca3 = new CBusca();
        private CBusca queryBusca4 = new CBusca();
        
       
        
          String regexhorasTotales = "^(10|[1-9][0-9]|100)$";
         // String regexClaveVersion = "^V[1-9]([0-9]{1,2})?$";
          String regexClaveVersion = "^V([1-9]|[1-9][0-9])$";


                
        String claveVersion;
        String asignatura;
        String ciclo;
        String horasTotales;
        String origen;
        
      
      public void limpiar(){
    
    JCOrigen.setSelectedIndex(0); 
    // Limpiar el JComboBox, restaurándolo a la selección inicial o vacía
    JCBAsignatura.setSelectedIndex(0);  // 0 selecciona el primer ítem de la lista, o puedes usar -1 para deseleccionar
    JCCiclo.setSelectedIndex(0);
    jTHorasTotales.setText("");
    jTClaveVersion.setText("");
    }
       
             
  public void asignaValores(){
    
  asignatura=(String) JCBAsignatura.getSelectedItem(); 
  origen = (String) JCOrigen.getSelectedItem();  // Asignamos el valor seleccionado a la variable estado
  ciclo=(String) JCCiclo.getSelectedItem(); 
  horasTotales=jTHorasTotales.getText();
 // horasTotales=jTHorasTotales.getText();
  claveVersion=jTClaveVersion.getText();
  
    
    }
   
  
                 
                  //asignamos valores al combo box
      public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    datosListas = queryCarga1.cargaNombreAsignaturaCurricular();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                    
                     case 2:
                    datosListas = queryCarga.cargaCiclosEscolares();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                  case 3:
                    datosListas = queryCarga.cargaDescripcionesOrigen();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                
            }
        } catch (SQLException e) {
        }
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
            CUtilitario.msg_adver(mensajeVacio, "Registro datos");
            valida = false;
        } else if (campoTexto.equals("NoValido")) {
            CUtilitario.msg_error(mensajeInvalido, "Registro datos");
            valida = false;
        } else {
            valida = true;
        }
        return valida;
    }
        
         public boolean validaCampos() {
        return validaCampo(horasTotales, jTHorasTotales, regexhorasTotales, "Ingrese las horas totales", "Las horas totales solo acepta numeros del 10 como minimo al 100")
        && validaCampo(ciclo, jTClaveVersion, regexClaveVersion, "Ingrese la clave de la versión como ejemplo V1", "El formato de la clave es incorrecto")
   ;
    }
        
         
                 
     public boolean verificaVersion(String claveVersion){
        
        try {
           CBusca queryBusca1 = new CBusca();   
         String resultado= queryBusca1.BuscaIdVersionPorId(claveVersion);
         
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
     
      
    
      
       
       
        
                     
        public boolean insertaVersion( String claveVersion,  String asignatura, String ciclo, String horasTotales,String origen){ 
        boolean bandera=false;
        
        try {
             
            
            String id_asignatura_curricular=queryBusca2.BuscaIDAsignaturaCurricular(asignatura);
            String id_ciclo=queryBusca3.obtenerIdCicloPorCiclo(ciclo);
            String id_origen=queryBusca4.obtenerIdOrigenPorDescripcion(origen);
            
            
            queryInserta1.insertarVersion(claveVersion, id_asignatura_curricular, id_ciclo, horasTotales, id_origen);
             CUtilitario.msg("Se enviaron los datos exitosamente de la version", "inserta version");
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
        JCBAsignatura = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTHorasTotales = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        JCOrigen = new javax.swing.JComboBox<>();
        JCCiclo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTClaveVersion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel3.setText("Registro versión");

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
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        jLabel1.setText("Asignatura");

        jLabel2.setText("Ciclo");

        jLabel4.setText("Horas totales");

        jLabel5.setText("Origen");

        jLabel6.setText("Clave version");

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JCBAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JCOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JCCiclo, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTClaveVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTHorasTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jLabel3)))
                        .addGap(0, 40, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTClaveVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(JCBAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(JCCiclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTHorasTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(JCOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (validaCampos()) {
        asignaValores();
        
            if (verificaVersion(claveVersion)) {
             CUtilitario.msg_adver("La clave de la version ya existe cambiela", "inserta version");   
            }else{
            insertaVersion(claveVersion, asignatura, ciclo, horasTotales, origen);
                limpiar();
          
            
            }
        
        
        
        }else{
        
        
        }
     
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(JFRegistroVersion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFRegistroVersion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFRegistroVersion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFRegistroVersion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFRegistroVersion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JCBAsignatura;
    private javax.swing.JComboBox<String> JCCiclo;
    private javax.swing.JComboBox<String> JCOrigen;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTClaveVersion;
    private javax.swing.JTextField jTHorasTotales;
    // End of variables declaration//GEN-END:variables
}
