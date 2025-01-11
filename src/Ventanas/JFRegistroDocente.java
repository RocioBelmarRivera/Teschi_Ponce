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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author HP
 */
public class JFRegistroDocente extends javax.swing.JFrame {

    /**
     * Creates new form JFRegistroDocente
     */
    public JFRegistroDocente() {
        initComponents();
        cargaComboBox(JCBEstado, 1);
        
        
        
           
             // Añadimos un DocumentListener al JTextField jTMatricula
        jTMatricula.getDocument().addDocumentListener(new DocumentListener() {
            // Este método se ejecuta cada vez que el texto cambia en jTMatricula
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarCorreo();  // Llamamos al método que actualiza el correo
            }

            // Este método se ejecuta si el texto se elimina en jTMatricula
            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarCorreo();  // Llamamos al método que actualiza el correo
            }

            // Este método se ejecuta si el texto cambia de alguna otra manera (por ejemplo, por formato)
            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarCorreo();  // Llamamos al método que actualiza el correo
            }
            
            // Este método concatena lo que hay en jTMatricula con la extensión en jLExtensionCorreo
            private void actualizarCorreo() {
                // Tomamos el texto de jTMatricula y lo concatenamos con la parte fija del correo
                String correo = jTMatricula.getText() + "@teschi.edu.mx"; // Aquí el dominio es fijo
                jLExtensionCorreo.setText(correo); // Actualizamos el texto de jLExtensionCorreo
            }
        });
    }

   
    
     private DefaultComboBoxModel listas;
    private ArrayList<String> datosListas = new ArrayList<>();
    private final CCargaBox queryCarga = new CCargaBox();
    
      private final CInserta queryInserta1= new CInserta();
      private final CInserta queryInserta2= new CInserta();
      private final CInserta queryInserta3= new CInserta();
      private final CInserta queryInserta4= new CInserta();
      private final CInserta queryInserta5= new CInserta();
      
      
      private CBusca queryBusca1 = new CBusca();
      private CBusca queryBusca2 = new CBusca();
      private CBusca queryBusca3 = new CBusca();
    
String regexcolonia = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s]{1,60}$";
String regexcalle = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s]{1,100}$";
String regexcodigoPostal = "^\\d{5}$";
String regexnumeroInterior = "^(\\d{1,5}|N/A)$";
String regexnumeroExterior = "^(\\d{1,5}|N/A)$";

String regexnombre= "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{1,50}$";
String regexapellidoPaterno= "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{1,50}$";
String regexapellidoMaterno= "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{1,50}$";
String regexmatricula  = "^D[0-9]{1,4}$"; 
String regextelefono = "^\\d{10}$";
    
    
    
    
String colonia;
String estado;
String calle;
String codigoPostal;
String numeroInterior;
String numeroExterior;

String nombre;
String apellidoPaterno;
String apellidoMaterno;
String matricula;
String telefono;
String correo;


  public void limpiar(){
    
    jTApellidoMaterno.setText("");
    jTApellidoPaterno.setText("");
    jTNombre.setText("");
    jTMatricula.setText("");
    jTCalle.setText("");
    jTCodigoPostal.setText("");
    jTColonia.setText("");
    jTNumeroExterior.setText("");
    jTNumeroInterior.setText("");
    
    
    // Limpiar el JComboBox, restaurándolo a la selección inicial o vacía
    JCBEstado.setSelectedIndex(0);  // 0 selecciona el primer ítem de la lista, o puedes usar -1 para deseleccionar
    
    }
  
    public void asignaValores(){
    
  colonia=jTColonia.getText();
  calle=jTCalle.getText();
  codigoPostal=jTCodigoPostal.getText();
  numeroExterior=jTNumeroExterior.getText();
  numeroInterior=jTNumeroInterior.getText();
  nombre=jTNombre.getText();
  apellidoPaterno=jTApellidoPaterno.getText();
  apellidoMaterno=jTApellidoMaterno.getText();
  matricula=jTMatricula.getText();
  telefono=jTNumeroTelefonico.getText();
  correo=jLExtensionCorreo.getText().trim().replaceAll("\\s+", "");
  
  // Obtener el valor seleccionado en el JComboBox
    estado = (String) JCBEstado.getSelectedItem();  // Asignamos el valor seleccionado a la variable estado
    
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
    return validaCampo(colonia, jTColonia, regexcolonia, "Ingrese la colonia", "La extencion de la colonia es invalida")
    && validaCampo(calle, jTCalle, regexcalle, "Ingrese la calle", "La extension de la calle es invalida")
    && validaCampo(codigoPostal, jTCodigoPostal, regexcodigoPostal, "Ingrese el codigo postal", "EL codigo solo acepta 5 numeros")
    && validaCampo(numeroExterior, jTNumeroExterior, regexnumeroExterior, "Ingrese el numero exterior", "EL numero exterior solo acepta hasta 5 numeros o en su defecto N/A")
    && validaCampo(numeroInterior, jTNumeroInterior, regexnumeroInterior, "Ingrese el numero Interior", "EL numero Interior solo acepta hasta 5 numeros o en su defecto N/A")
    && validaCampo(nombre, jTNombre, regexnombre, "Ingrese el nombre(s)", "EL formato o extension del nombre es incorrecto")
    && validaCampo(apellidoPaterno, jTApellidoPaterno, regexapellidoPaterno, "Ingrese el apellido paterno", "EL formato o extension del apellido paterno es incorrecto")
    && validaCampo(apellidoMaterno, jTApellidoMaterno, regexapellidoMaterno, "Ingrese el apellido materno", "EL formato o extension del apellido materno es incorrecto")
    && validaCampo(matricula, jTMatricula, regexmatricula, "Ingrese la matricula", "La matricula solo acpta 10 numeros")
    && validaCampo(telefono, jTNumeroTelefonico, regextelefono, "Ingrese el numero telefonico", "EL numero telefonico solo debe de ser 10 numeros");
    }
    
    
       //asignamos valores al combo box
      public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    datosListas = queryCarga.cargaEstado();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
            }
        } catch (SQLException e) {
        }
    }
      
        



     public int insertaDireccion(String estado, String colonia, String codigoPostal, String calle,String numInterior,String numExterior){ 
        boolean bandera=false;
       
            
        int ultimoID;
       
        try {
            
              String id_estado=queryBusca1.buscaIDEstado(estado);
              
              System.out.println("id de estado="+id_estado);
           
             queryInserta1.insertaDireccion(id_estado, colonia, codigoPostal, calle, numInterior, numExterior);
             //CUtilitario.msg("Se inserto correctamente la direccion", "inserta direccion");
            ultimoID=queryBusca2.buscaUltimoIDDireccion();
             
             
            // return ultimoID;
        } catch (Exception e) {
        // Registrar el error o manejarlo de alguna forma
      //  e.printStackTrace();  // Esto imprimirá el stack trace para ayudar en el diagnóstico del error.
        CUtilitario.msg_error("problema al insertar direccion. Por favor intente nuevamente.", "direccion");
          return 0;
    }
    return  ultimoID;
    
   }
     
     
     
     
       public int insertaPersona(String apellidoPaterno,String apellidoMaterno, String nombres){ 
       
           int id_direccion;
           int ultimoID=0;
        try {
            
           id_direccion=insertaDireccion(estado, colonia, codigoPostal, calle, numeroInterior, numeroExterior);
           
            if (id_direccion==0) {//si el ultimo id es 0
                System.out.println("id ultimo direccion:"+id_direccion);
            }else{
            
             System.out.println("direccion id ultimo:"+id_direccion);
             //String idNacionalidad=queryBusca3.buscaNacionalidad(nacionalidad);  
             //int id_nacionalidad=Integer.parseInt(idNacionalidad);
             
            // String idCivil=queryBusca4.buscaCivil(estadoCivil);
             //int id_civil=Integer.parseInt(idCivil);
             
                try {
                    queryInserta2.insertaPersona(apellidoPaterno, apellidoMaterno, nombres, id_direccion);
                      CUtilitario.msg("Se inserto correctamente la persona", "inserta persona");
                } catch (Exception e) {
                    System.out.println("no se inserto persona por problamas");
                }
           
            //ultimoID=queryBusca6.obtenerUltimoId();
            
            
            ultimoID=queryBusca3.buscaUltimoIDPersona();
            System.out.println("PERSONA id ultimo:"+ultimoID);
            }
            
     
             
        } catch (Exception e) {
        // Registrar el error o manejarlo de alguna forma
      //  e.printStackTrace();  // Esto imprimirá el stack trace para ayudar en el diagnóstico del error.
        CUtilitario.msg_error("Hubo un problema al cargar a la persona. Por favor intente nuevamente.", "Error de conexión");
        return 0;
    }
   
    return ultimoID;
   }
       
       
       
       public int insertaDocente(String matricula,String telefono,String correo){ 
       
           int id_persona;
           int ultimoID=0;
        try {
            
           id_persona=insertaPersona(apellidoPaterno, apellidoMaterno, nombre);
           
            if (id_persona==0) {//si el ultimo id es 0
                System.out.println("id ultimo persona:"+id_persona);
            }else{
            
             System.out.println("direccion id ultimo:"+id_persona);
             
             
                try {
                    queryInserta3.insertaDocente(matricula, id_persona);
                    queryInserta4.insertaTelefono(telefono, id_persona);
                    queryInserta5.insertaCorreo(correo, id_persona);
                    
                    
                    
                      CUtilitario.msg("Se inserto correctamente EL docente", "inserta persona");
                } catch (Exception e) {
                    System.out.println("no se inserto persona por problamas");
                }
           
            //ultimoID=queryBusca6.obtenerUltimoId();
            
            
            ultimoID=queryBusca3.buscaUltimoIDPersona();
            System.out.println("PERSONA id ultimo:"+ultimoID);
            }
            
     
             
        } catch (Exception e) {
        // Registrar el error o manejarlo de alguna forma
      //  e.printStackTrace();  // Esto imprimirá el stack trace para ayudar en el diagnóstico del error.
        CUtilitario.msg_error("Hubo un problema al cargar a la persona. Por favor intente nuevamente.", "Error de conexión");
        return 0;
    }
   
    return ultimoID;
   }
     
     
     
         
     public boolean verificaMatricula(String matricula){
        
        try {
           CBusca queryBusca5 = new CBusca();   
         String resultado= queryBusca5.buscaMatriculaDocente(matricula);
         
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
     
     
    
     
        public boolean verificaTelefono(String telefono){
        
        try {
           CBusca queryBusca6 = new CBusca();   
         String resultado= queryBusca6.buscaTelefono(telefono);
         
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
          
        
        
        
                
        public boolean verificaCorreo(String correo){
        
        try {
           CBusca queryBusca7 = new CBusca();   
         String resultado= queryBusca7.buscaCorreo(correo);
         
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
     
     
     
     
      

    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTColonia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTCalle = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        JCBEstado = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTCodigoPostal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTNumeroInterior = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTNumeroExterior = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTNombre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTApellidoPaterno = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTApellidoMaterno = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTNumeroTelefonico = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTMatricula = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLExtensionCorreo = new javax.swing.JLabel();

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
                .addGap(15, 15, 15)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(27, 27, 27)
                .addComponent(jButton3)
                .addGap(20, 20, 20))
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
        jLabel3.setText("Registro Docente");

        jLabel1.setText("DATOS DE LA DIRECCIÓN");

        jLabel2.setText(" Colonia ");

        jLabel4.setText(" Calle ");

        jLabel5.setText("Estado");

        jLabel6.setText("Código postal");

        jLabel7.setText("Numero Interior");

        jLabel8.setText("Numero exterior");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(JCBEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jTNumeroInterior, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNumeroExterior, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(JCBEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTNumeroInterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTNumeroExterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel9.setText("DATOS PERSONALES");

        jLabel10.setText("Nombres");

        jLabel11.setText("Apellido paterno");

        jLabel12.setText("Apellido materno");

        jLabel15.setText("Numero telefonico");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(335, 335, 335)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTNumeroTelefonico, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTNumeroTelefonico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel13.setText("Matricula del profesor");

        jLabel14.setText("Correo electronico:");

        jLExtensionCorreo.setText("@teschi.edu.mx");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLExtensionCorreo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLExtensionCorreo))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
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
            if (verificaMatricula(matricula)) {
                CUtilitario.msg_adver("Esa matricula ya existe y aest aregistrada", "verifica matricula");
            }else{
                
                
                if (verificaTelefono(telefono)) {
                      CUtilitario.msg_adver("El telefono ya existe ya esta aregistrada", "verifica telefono");
                }else{
                    
                    
                    if (verificaCorreo(correo)) {
                          CUtilitario.msg_adver("El telefono ya existe ya esta aregistrada", "verifica telefono");
                    }else{
                        
                         insertaDocente(matricula, telefono, correo);
                         limpiar();
                    
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
            java.util.logging.Logger.getLogger(JFRegistroDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFRegistroDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFRegistroDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFRegistroDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFRegistroDocente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JCBEstado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLExtensionCorreo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTApellidoMaterno;
    private javax.swing.JTextField jTApellidoPaterno;
    private javax.swing.JTextField jTCalle;
    private javax.swing.JTextField jTCodigoPostal;
    private javax.swing.JTextField jTColonia;
    private javax.swing.JTextField jTMatricula;
    private javax.swing.JTextField jTNombre;
    private javax.swing.JTextField jTNumeroExterior;
    private javax.swing.JTextField jTNumeroInterior;
    private javax.swing.JTextField jTNumeroTelefonico;
    // End of variables declaration//GEN-END:variables
}
