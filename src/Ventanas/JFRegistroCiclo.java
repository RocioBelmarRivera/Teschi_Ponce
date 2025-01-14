/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventanas;

import Utilitarios.CUtilitario;
import crud.CBusca;
import crud.CInserta;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class JFRegistroCiclo extends javax.swing.JFrame {

    /**
     * Creates new form JFRegistroCiclo
     */
    public JFRegistroCiclo() {
        initComponents();
    }

    
     private final CInserta queryInserta1= new CInserta();
    
    
     
     
     
    String regexClaveCiclo= "^[A-Z][0-9]$";
    String regexCiclo= "^(20[0-9]{2}|2[1-9][0-9]{3})-[12]$";
    String regexFechaInicio = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
    String regexFechaTermino = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
    
    
    
    
    String claveCiclo;
    String ciclo;
    String fechaInicio;
    String fechatermino;
    
      public void limpiar(){
    
    jTClaveCiclo.setText("");
    jTCiclo.setText("");
    jTFechaInicio .setText("");
    jTFechaTermino .setText("");
   
    }
     
    public void asignaValores(){
    
    claveCiclo=jTClaveCiclo.getText();
    ciclo=jTCiclo.getText();
    fechaInicio=jTFechaInicio.getText();
    fechatermino=jTFechaTermino.getText();

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
        return validaCampo(claveCiclo, jTClaveCiclo, regexClaveCiclo, "Ingrese la clave del ciclo", "El formato de la clave del ciclo es incorrecto")
    && validaCampo(ciclo, jTCiclo, regexCiclo, "Ingrese el ciclo escolar", "El formato del ciclo es incorrecto")
    && validaCampo(fechaInicio, jTFechaInicio, regexFechaInicio, "Ingrese la fecha de inicio", "El formato de la fecha de inicio es incorrecto")
    && validaCampo(fechatermino, jTFechaTermino, regexFechaTermino, "Ingrese la fecha de termino", "El formato de la fecha de termino es incorrecto");
    }
         
         
         
         
         
     public boolean verificaCiclo(String claveCiclo){
        
        try {
           CBusca queryBusca1 = new CBusca();   
         String resultado= queryBusca1.buscaClaveCiclo(claveCiclo);
         
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
     
     
     
     
        public boolean verificaCicloFecha(String ciclo,String fechaInicio, String fechaTermino){
        
        try {
           CBusca queryBusca1 = new CBusca();   
         String resultado= queryBusca1.buscaCiclo(ciclo, fechaInicio, fechaTermino);
         
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
     
     
     
      public boolean insertaCiclo(String claveCiclo,String ciclo, String fechaInicio, String fechaTermino){ 
        boolean bandera=false;
       
        try {
           
             queryInserta1.insertaCiclo(claveCiclo, ciclo, fechaInicio, fechaTermino);
             CUtilitario.msg("Se enviaron los datos exitosamente", "inserta datos ciclo");
           bandera=true;
             
             
            // return ultimoID;
        } catch (Exception e) {
        // Registrar el error o manejarlo de alguna forma
      //  e.printStackTrace();  // Esto imprimirá el stack trace para ayudar en el diagnóstico del error.
        CUtilitario.msg_error("problema al insertar ciclo. Por favor intente nuevamente.", "ciclo");
         return false;
    }
    return bandera;
    
   }
      
      
       // Método para validar las fechas
    public boolean validaFechas(String fechaInicio, String fechaTermino) {
        // Validar el formato de las fechas usando regex
        boolean bandera=false;

        // Convertir las fechas a LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate fechaInicioLocal = LocalDate.parse(fechaInicio, formatter);
            LocalDate fechaTerminoLocal = LocalDate.parse(fechaTermino, formatter);

            // Comparar las fechas
            if (fechaInicioLocal.isAfter(fechaTerminoLocal) || fechaInicioLocal.isEqual(fechaTerminoLocal)) {
               
                return true;
            }

        } catch (DateTimeParseException e) {
            System.out.println("Error al parsear las fechas.");
            return true;
        }

        return false;
    }
    
    
        
       // Método para validar las fechas
    public boolean validaCiclo(String ciclo) {
        // Validar el formato de las fechas usando regex
   
       try {
           CBusca queryBusca1 = new CBusca();   
         String resultado= queryBusca1.obtenerIdCicloPorCiclo(ciclo);
         
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
    
    
    
    
        public static boolean validaCicloYFecha(String cicloEscolar, String fechaInicio) {
            
            boolean bandera=true;
        // Definir el formato de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Extraer el año del ciclo escolar
        String añoCiclo = cicloEscolar.split("-")[0];  // Obtiene los primeros 4 caracteres del ciclo escolar

        // Convertir la fecha de inicio en un objeto LocalDate
        LocalDate fecha = LocalDate.parse(fechaInicio, formatter);
        
        // Obtener el año de la fecha de inicio
        int añoFechaInicio = fecha.getYear();

        // Comparar los primeros 4 números del ciclo escolar con el año de la fecha de inicio
        if (Integer.parseInt(añoCiclo) == añoFechaInicio) {
            // Si los años coinciden, devuelve true
            return false;
        } else {
            // Si no coinciden, devuelve false
            return true;
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTClaveCiclo = new javax.swing.JTextField();
        jTCiclo = new javax.swing.JTextField();
        jTFechaInicio = new javax.swing.JTextField();
        jTFechaTermino = new javax.swing.JTextField();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

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
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel3.setText("Registro ciclo escolar");

        jLabel1.setText("Clave del ciclo");

        jLabel2.setText("Ciclo escolar");

        jLabel4.setText("Fecha de inicio");

        jLabel5.setText("Fecha de termino");

        jTClaveCiclo.setToolTipText("Formato de clave \"Letra mayuscula seguido de un numero ejemplo C1\"");

        jTCiclo.setToolTipText("Formato \"AÑO-1/2\" el sño debe ser del 2000 en adelamte");

        jTFechaInicio.setToolTipText("Siga el siguiente formato \"Año-Mes-Dia\"");

        jTFechaTermino.setToolTipText("Siga el siguiente formato \"Año-Mes-Dia\"");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 44, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTFechaTermino, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addGap(18, 18, 18)
                                        .addComponent(jTClaveCiclo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(45, 45, 45))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTCiclo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(159, 159, 159))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTClaveCiclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTCiclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTFechaTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
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
            if (verificaCiclo(claveCiclo)) {
                  System.out.println("la clave del ciclo ya existe");
                   CUtilitario.msg_adver("La clave del ciclo ya existe", "verifica Clave Ciclo");
            }else{
                
                if (verificaCicloFecha(ciclo, fechaInicio, fechatermino)) {
                     System.out.println("El ciclo ya existe");
                   CUtilitario.msg_adver("El ciclo ya esta ya existe", "verifica Ciclo");
                }else{
                    
                    if (validaCicloYFecha(ciclo, fechaInicio)) {
                          System.out.println("El año del ciclo con el año de la fecha de inicio debenm de coicidir");
                   CUtilitario.msg_adver("El año del ciclo con el año de la fecha de inicio debenm de coicidir", "verifica Ciclo");
                    }else{
                    
                      
                if (validaFechas(fechaInicio, fechatermino)) {
                 System.out.println("La fecha de inicio no puede ser posterior a la fecha de término.");
                 CUtilitario.msg_adver("La fecha de inicio no puede ser posterior o igual a la fecha de término.", "Registro fechas"); 
                }else{
                    
                    if (validaCiclo(ciclo)) {
                         System.out.println("El ciclo ya existe");
                          CUtilitario.msg_adver("El ciclo ya esta ya existe", "verifica Ciclo");
                    }else{
                     insertaCiclo(claveCiclo, ciclo, fechaInicio, fechatermino);
                 limpiar();
                    }
                    
                    
                
                }
                        
                        
                    }
 
                
                }
                  
            
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
            java.util.logging.Logger.getLogger(JFRegistroCiclo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFRegistroCiclo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFRegistroCiclo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFRegistroCiclo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFRegistroCiclo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTCiclo;
    private javax.swing.JTextField jTClaveCiclo;
    private javax.swing.JTextField jTFechaInicio;
    private javax.swing.JTextField jTFechaTermino;
    // End of variables declaration//GEN-END:variables
}
