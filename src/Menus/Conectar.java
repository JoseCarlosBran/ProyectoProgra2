/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author José Carlos Bran
 */
public class Conectar {

    /**
     * @return the precioHabitacion
     */
    public String getPrecioHabitacion() {
        return precioHabitacion;
    }

    /**
     * @param precioHabitacion the precioHabitacion to set
     */
    public void setPrecioHabitacion(String precioHabitacion) {
        this.precioHabitacion = precioHabitacion;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() { //Obtener el Nombre del Usuario Ingresado
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {    //Metodo usado para guardar el Nombre de Usuario al Iniciar sesion
        this.nombreUsuario = nombreUsuario;
    }


    /**
     * @return the estadoReserva
     */
    public String getEstadoReserva() { //Obtiene el estado de la Reserva Seleccionada
        return estadoReserva;
    }

    /**
     * @param estadoReserva the estadoReserva to set
     */
    public void setEstadoReserva(String estadoReserva) { //Guarda el estado de la Reserva Seleccionada
        this.estadoReserva = estadoReserva;
    }

    /**
     * @return the clienteId
     */
    public int getClienteId() {//Obtiene el Id del cliente que tiene la sesion activa
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(int clienteId) {//Guarda el Id del cliente al Iniciar Sesion
        this.clienteId = clienteId;
    }

    /**
     * @return the codReserva
     */
    public  int getCodReserva() {//Obtiene el Codigo de la Reserva seleccionada para pagar
        return codReserva;
    }

    /**
     * @param aCodReserva the codReserva to set
     */
    public void setCodReserva(int aCodReserva) {//Guarda el Codigo de la Reserva seleccionada para pagar
        codReserva = aCodReserva;
    }
    
    
    private static Connection conecxion;//Devuelve la Conecxion a Nuestra Base de Datos
    private static final String driver="com.mysql.jdbc.Driver";//Es el Controlador que Conecta nuestro Programa con la Base de Datos
    private static final String user="root";//Usuario de Nuestra Base de Datos
    private static final String pasword="";//Contraseña para Iniciar sesion en la Base de Datos
    private static final String url="jdbc:mysql://localhost:3306/proyecto2018";//Es la Dirreccion de Nuestra Base de Datos
    DefaultTableModel model;// Objeto tipo Tabla Utilizado en Nuestro metodos para Listar los Datos de Vuelta
    private static int codReserva;//Guarda el Codigo de la Reserva Seleccionada en Nuestras Busquedas y a la Hora de Pagar.
    private static int clienteId;//Nos sirve a lo largo de Programa para cambios de Contraseña, Busqueda de Pagos, pago de Reservas, etc.
    private static String nombreUsuario;//Guarda el Nombre del Usuario que Inicio Sesion y Nos sirve para las Busquedas del Programa.
    private static String estadoReserva;//Guarda el Estado de la Reserva Selecionada para ser Cancelada
    private static String precioHabitacion;//Sirve para guardar el Precio de la Habitacion a Cnacelar
    private static String password;
    
        
 
    /**
     * 
     * @return Metodo que realiza la Conecxion a Nuestra Base de DATOS
     */
    public Connection ConectarBase (){
        
            conecxion=null;
        try {
            Class.forName(driver);//Crea una Asociacion entre Nuestra Aplicacion y la Clase Driver que esta empaquetada, en el jar de Conecxion.
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conecxion=(Connection) DriverManager.getConnection(url, user, pasword);//Obtiene la Conecxion con Nuestra Base de Datos.
        } catch (SQLException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }

            
       return conecxion;//Regresa la Conecxion Obtenida
        
    }
    /**
     *  Desconecta Nuestra aplicacion de la Base de Datos
     */
    public void DesconectarBase(){
        try{
            conecxion.close();
            conecxion=null;
            if(conecxion==null){
                System.out.println("conexion terminada");
            }
        
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null,ex.getMessage(),"error al cerrar  la conexion de datos ",JOptionPane.ERROR_MESSAGE);
             }  
}
    
    public void crearHabitacion(String tiphab,String tipocam,String desc,float precio ){//Metodo que Crea una Habitacion en Nuestra Base de Datos
     try {
         String SQLINSERT =("INSERT INTO habitacion (tipo_habitacion,tipo_cama,descripción,precio)values(?,?,?,?)");//Sentecia Sql que da las Instrucciones a ejecutar, Guarda los datos Ingresados.
         Conectar con= new Conectar();
         java.sql.Connection cn= con.ConectarBase();//Relaiza la Conecxion 
         java.sql.PreparedStatement PS = cn.prepareStatement(SQLINSERT);//Alamacena los datos de la Consulta sql
         /*Manda los datos de cada Campo*/
         //PS.setInt(1, numhab);
         PS.setString(1, tiphab);
         PS.setString(2, tipocam);
         PS.setString(3, desc);
         PS.setFloat(4, precio);
         PS.executeUpdate();//Actualiza Nuestra Tabla en la Base de Datos
             
     } catch (SQLException ex) {
         Logger.getLogger(CrearUsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);//Captura alguna Ruptura del Programa en TIEMPO de ejecucuion.
         JOptionPane.showMessageDialog(null, "Datos No Guardados");
     }
 }
   
    public void modificarHabitacion(int numhab,String tiphab,String tipocam,String desc,float precio ){//Metodo que Modifica las Habitaciones de Nuestra Base de Datos
     try {/*Se Realizo por separado la Actualizacion de cada Columna de Nuestra Tabla debido a que por la Version de Nuestro Servidor, No nos permitia Realizarlo en una sola Consulta */
         String sql = ("UPDATE habitacion SET precio= '"+precio+"' WHERE id_habitacion LIKE '%"+numhab+"%'");//Sentecia sql de la Consulta a Nuestra Base de Datos
         Conectar con= new Conectar();
         java.sql.Connection cn= con.ConectarBase();
         java.sql.PreparedStatement PS = cn.prepareStatement(sql);//Realiza la Ejecucion de Nuestra Sentencia SQL
         PS.executeUpdate();
        
        sql = ("UPDATE habitacion SET tipo_habitacion= '"+tiphab+"' WHERE id_habitacion LIKE '%"+numhab+"%'");//sentencia sql a nestra base de datos para modificar tipo de habitacion
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE habitacion SET tipo_cama= '"+tipocam+"' WHERE id_habitacion LIKE '%"+numhab+"%'");//sentencia sql para modificar tipo de cama
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE habitacion SET descripción= '"+desc+"' WHERE id_habitacion LIKE '%"+numhab+"%'");//sentencua sql para modificar descripcion o estaddo de habitacion
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
         
         
         
     } catch (SQLException ex) {
         //captura de errores sql y exceptiones  en tiempo de ejecucion
         Logger.getLogger(CrearUsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, "Datos No Guardados");
     }
 }
    
    public void modificarUsuarioCliente(String nombre,String apellidos,String direccion,String nacionalidad,String correoelectronico,String telefono){//Metodo Modifica  la Informacion del Cliente en Nuestra Base de Datos
     try {
         int telefonos=Integer.parseInt(telefono);
         Conectar con= new Conectar();//objeto de la clase conectar 
         String sql = ("UPDATE cliente SET nombre= '"+nombre+"' WHERE id_cliente LIKE '%"+con.getClienteId()+"%'");//sentencia sql para modificar nombre de usuario cliente 
         java.sql.Connection cn= con.ConectarBase();
         java.sql.PreparedStatement PS = cn.prepareStatement(sql);//Realiza la Ejecucion de Nuestra Sentencia SQL
         PS.executeUpdate();//Actualiza la Informacion en Nuestra Base de Datos
         /* Y asi de esa Forma vamos Actualizando cada uno de los Datos de Nuestro Cliente en la Base de Datos esto Debido
         a que la Version de Nuestro Servidor, No permitia Hacerla en una sola Sentencia
         */
         sql = ("UPDATE cliente SET apellido= '"+apellidos+"' WHERE id_cliente LIKE '%"+con.getClienteId()+"%'");//Sentencia sql que da las Intruciones a Nuestra Base de Datos
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE cliente SET pais= '"+nacionalidad+"' WHERE id_cliente LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE cliente SET direccion= '"+direccion+"' WHERE id_cliente LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE cliente SET telefono= '"+telefonos+"' WHERE id_cliente LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE cliente SET email= '"+correoelectronico+"' WHERE id_cliente LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
         
         
     } catch (SQLException ex) {
         Logger.getLogger(CrearUsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, "Datos No Guardados");
     }
 }
    
    public void modificarUsuarioAdministrador(String nombre,String apellidos,String direccion,String telefono){
     try {
         int telefonos=Integer.parseInt(telefono);
         Conectar con= new Conectar();
         String sql = ("UPDATE administrador SET nombre= '"+nombre+"' WHERE id_administrador LIKE '%"+con.getClienteId()+"%'");
         java.sql.Connection cn= con.ConectarBase();
         java.sql.PreparedStatement PS = cn.prepareStatement(sql);
         PS.executeUpdate();
        
        sql = ("UPDATE administrador SET apellido= '"+apellidos+"' WHERE id_administrador LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
       
        sql = ("UPDATE administrador SET direccion= '"+direccion+"' WHERE id_administrador LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE administrador SET telefono= '"+telefonos+"' WHERE id_administrador LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();

     } catch (SQLException ex) {
         Logger.getLogger(CrearUsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, "Datos No Guardados");
     }
 }
    
     void buscarHabitacion(javax.swing.JTable tabla, String Habitacion, String Cama){
    String [] titulos={"Habitación", "Tipo Hab.", "Tipo Cama", "Descripción","Precio"};
    String[] registros= new String[5];//vector donde se almacenara el resultado de la consulta ,,, crea una fila
    String Reservada= "S/Reservar";
    
    String sql="SELECT * FROM habitacion where (tipo_habitacion LIKE '%"+Habitacion+"%' AND descripcion = '"+Reservada+"') || (tipo_cama LIKE '%"+Cama+"%' AND descripcion = '"+Reservada+"')  ";
    model=new DefaultTableModel(null, titulos);
    Conectar cc=new Conectar();
    Connection cn=cc.ConectarBase();
    
        try {
            Statement st=(Statement) cn.createStatement();
            ResultSet rs= st.executeQuery(sql);//se almacenan los resultados de la consulta 
            while(rs.next()){//navega en cada resultado de la tabla ,cada consulta una fila 
            registros[0]=rs.getString("id_habitacion");
            registros[1]=rs.getString("tipo_habitacion");
            registros[2]=rs.getString("tipo_cama");
            registros[3]=rs.getString("descripcion");
            registros[4]=rs.getString("precio");
            model.addRow(registros);//agrega una fila , con los datos , y los almacena en el vector registros
            
            
            
            }
            tabla.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
     
    void buscarHabitacion(javax.swing.JTable tabla, String Habitacion){
    String [] titulos={"Habitación", "Tipo Hab.", "Tipo Cama", "Descripción","Precio"};//muestra los titulos de nuestra tabla
    String[] registros= new String[5];//vector donde se almacenaran la consulta buscada
    String Reservada= "S/Reservar";
    
    String sql="SELECT * FROM habitacion where id_habitacion LIKE '%"+Habitacion+"%' ";
    model=new DefaultTableModel(null, titulos);
    Conectar cc=new Conectar();
    Connection cn=cc.ConectarBase();
    
        try {
            Statement st=(Statement) cn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
            registros[0]=rs.getString("id_habitacion");
            registros[1]=rs.getString("tipo_habitacion");
            registros[2]=rs.getString("tipo_cama");
            registros[3]=rs.getString("descripcion");
            registros[4]=rs.getString("precio");
            cc.setPrecioHabitacion(rs.getString("precio"));
            
           
            model.addRow(registros);
            }
            tabla.setModel(model);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    
    void buscarHabitacion( String Habitacion){
    String [] titulos={"Habitación", "Tipo Hab.", "Tipo Cama", "Descripción","Precio"};//muestra los titulos de nuestra tabla
    String[] registros= new String[5];//vector donde se almacenaran la consulta buscada
    String Reservada= "S/Reservar";
    
    String sql="SELECT * FROM habitacion where id_habitacion LIKE '%"+Habitacion+"%' ";
    model=new DefaultTableModel(null, titulos);
    Conectar cc=new Conectar();
    Connection cn=cc.ConectarBase();
    
        try {
            Statement st=(Statement) cn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
            registros[0]=rs.getString("id_habitacion");
            registros[1]=rs.getString("tipo_habitacion");
            registros[2]=rs.getString("tipo_cama");
            registros[3]=rs.getString("descripcion");
            registros[4]=rs.getString("precio");
            cc.setPrecioHabitacion(rs.getString("precio"));
            
           
            model.addRow(registros);
            }
           
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    void buscarReserva(javax.swing.JTable tabla, String Codigo){
    String [] titulos={"Reserva", "Cliente Id", "Habitacion", "Fecha de Reserva","Fecha de LLegada", "Fecha de Salida", "Estado Reserva"};
    String[] registros= new String[7];
    Conectar cc=new Conectar();
    Connection cn=cc.ConectarBase();
    String id_habitacion="0";
    String sql="SELECT * FROM reserva where id_reserva LIKE '%"+Codigo+"%' AND id_cliente Like '%"+cc.getClienteId()+"%'";
    model=new DefaultTableModel(null, titulos);
    
    
        try {
            Statement st=(Statement) cn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
            registros[0]=rs.getString("id_reserva");
            registros[1]=rs.getString("id_cliente");
            registros[2]=rs.getString("id_habitacion");
            id_habitacion=rs.getString("id_habitacion");
            registros[3]=rs.getString("fechaReserva");
            registros[4]=rs.getString("fechaLlegada");
            registros[5]=rs.getString("fechaSalida");
            registros[6]=rs.getString("estadoReserva");
            cc.setEstadoReserva(rs.getString("estadoReserva"));
           
            model.addRow(registros);
            }
            cc.buscarHabitacion(id_habitacion);
            tabla.setModel(model);
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    void buscarPagos(javax.swing.JTable tabla){
    String [] titulos={"Id_Pago","Codigo de Reserva", "Cliente Id", "Fecha de Pago", "Monto"};
    String[] registros= new String[5];
    Conectar cc=new Conectar();
    Connection cn=cc.ConectarBase();
    String sql="SELECT * FROM pago where id_cliente Like '%"+cc.getClienteId()+"%'";
    model=new DefaultTableModel(null, titulos);
    
    
        try {
            Statement st=(Statement) cn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
            registros[0]=rs.getString("id_pago");
            registros[1]=rs.getString("id_reserva");
            registros[2]=rs.getString("id_cliente");
            registros[3]=rs.getString("fechaPago");
            registros[4]=rs.getString("Monto");          
            model.addRow(registros);
            }
            tabla.setModel(model);  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }

    void buscarEliminarHabitacion(javax.swing.JTable tabla, String Codigo){
    String [] titulos={"Habitación", "Tipo Hab.", "Tipo Cama", "Descripción","Precio"};
    String[] registros= new String[5];
   
    model=new DefaultTableModel(null, titulos);
    Conectar cc=new Conectar();
    Connection cn=cc.ConectarBase();
    
        try {
            PreparedStatement pps = (PreparedStatement) cn.prepareStatement("DELETE  FROM habitacion where id_habitacion LIKE '%"+Codigo+"%'  ");
            pps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    
    
    }
    
    public void crearReserva(String numhab,String fechaLlegada,String fechaSalida ){
     /**
      * esta Variable Sirve para colocar por defecto una Reserva recien creada
      * estara S/Cancelar
      */
     String Reserva= "S/Cancelar";
     String fecReserva = null;
     Calendar c1=new GregorianCalendar();
     String dia=Integer.toString(c1.get(Calendar.DATE));
     String mes=Integer.toString(c1.get(Calendar.MONTH));
     String anio=Integer.toString(c1.get(Calendar.YEAR));
     /**
      * obtengo en una Sola cadena de Texto la fecha del Sistema
      * que es la fecha de la Reserva, dato que No se le pide al Cliente
      */
     fecReserva=(anio+"/"+mes+"/"+dia);
     try {//Sentencia Sql que especifica los Datos que seran enviados a Nuestra Base de Datos
         String SQLINSERT =("INSERT INTO reserva (id_cliente,id_habitacion,fechaReserva,fechaLlegada,fechaSalida, estadoReserva)values(?,?,?,?,?,?)");//Sentencia sql que Crea la Reserva
         Conectar con= new Conectar();
         java.sql.Connection cn= con.ConectarBase();
         java.sql.PreparedStatement PS = cn.prepareStatement(SQLINSERT);
         /*Envio de datos en el orden establecido por la sentencia Sql*/
         PS.setInt(1, con.getClienteId());
         PS.setString(2, numhab);
         PS.setString(3, fecReserva);
         PS.setString(4, fechaLlegada);
         PS.setString(5, fechaSalida);
         PS.setString(6, Reserva);
         PS.executeUpdate();//Actualiza los Datos de Nuestra Tabla en la Base de Datos
         String descripcionHabitacion="Reservada";
         SQLINSERT = ("UPDATE habitacion SET descripción= '"+descripcionHabitacion+"' WHERE id_habitacion LIKE '%"+numhab+"%'");//Sentencia sql que cambia el estado de la Habitacion a Reservada
        PS = (java.sql.PreparedStatement) cn.prepareStatement(SQLINSERT);
        PS.executeUpdate();//Actuliza los Datos de Nuestra Tabla en la Base de Datos
             
     } catch (SQLException ex) {
         Logger.getLogger(CrearUsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, "Datos No Guardados");
     }
 }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
       
  
    
}
