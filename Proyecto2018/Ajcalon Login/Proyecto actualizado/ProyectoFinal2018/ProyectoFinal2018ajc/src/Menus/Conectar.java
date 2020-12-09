/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
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
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the clientePassword
     */
    public String getClientePassword() {
        return clientePassword;
    }

    /**
     * @param clientePassword the clientePassword to set
     */
    public void setClientePassword(String clientePassword) {
        this.clientePassword = clientePassword;
    }



    /**
     * @return the estadoReserva
     */
    public String getEstadoReserva() {
        return estadoReserva;
    }

    /**
     * @param estadoReserva the estadoReserva to set
     */
    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    /**
     * @return the clienteId
     */
    public int getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return the codReserva
     */
    public  int getCodReserva() {
        return codReserva;
    }

    /**
     * @param aCodReserva the codReserva to set
     */
    public void setCodReserva(int aCodReserva) {
        codReserva = aCodReserva;
    }
    
    //creamos variables para la coneccion
    private static Connection conecxion;
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="root";
    private static final String pasword="";
    private static final String url="jdbc:mysql://localhost:3306/proyecto2018";
    
    DefaultTableModel model;
    private static int codReserva;
    private static int clienteId;
    private static String clientePassword;
    private static String nombreUsuario;
    private static String estadoReserva;
    
        
 
    //metodo coneccion 
    public Connection ConectarBase (){
        //igualamos nuestra variable de conexion a nulo
        //para observar si podemos conecta ejecutamos el try para llamar
        //la variable driver que contiene la direccion de la bd
        
            conecxion=null;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conecxion=(Connection) DriverManager.getConnection(url, user, pasword);
        } catch (SQLException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }

            
       return conecxion;
        
    }
    
    public void DesconectarBase(){
        try{
            conecxion.close();
            conecxion=null;
            if(conecxion==null){
                System.out.println("conexion terminada");
            }
        
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null,ex.getMessage(),"error al cerrar  la conexion de datos ",JOptionPane.ERROR_MESSAGE);
             }  
}
    
    public void crearHabitacion(int numhab,String tiphab,String tipocam,String desc,float precio ){
     try {
         String SQLINSERT =("INSERT INTO habitacion (numeroHabitacion,tipoHabitacion,tipoCama,descripción,precio)values(?,?,?,?,?)");
         Conectar con= new Conectar();
         java.sql.Connection cn= con.ConectarBase();
         java.sql.PreparedStatement PS = cn.prepareStatement(SQLINSERT);
         PS.setInt(1, numhab);
         PS.setString(2, tiphab);
         PS.setString(3, tipocam);
         PS.setString(4, desc);
         PS.setFloat(5, precio);
         PS.executeUpdate();
             
     } catch (SQLException ex) {
         Logger.getLogger(CrearUsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, "Datos No Guardados");
     }
 }
   
    public void modificarHabitacion(int numhab,String tiphab,String tipocam,String desc,float precio ){
     try {
         String sql = ("UPDATE habitacion SET precio= '"+precio+"' WHERE numeroHabitacion LIKE '%"+numhab+"%'");
         Conectar con= new Conectar();
         java.sql.Connection cn= con.ConectarBase();
         java.sql.PreparedStatement PS = cn.prepareStatement(sql);
         PS.executeUpdate();
        
        sql = ("UPDATE habitacion SET tipoHabitacion= '"+tiphab+"' WHERE numeroHabitacion LIKE '%"+numhab+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE habitacion SET tipoCama= '"+tipocam+"' WHERE numeroHabitacion LIKE '%"+numhab+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE habitacion SET descripción= '"+desc+"' WHERE numeroHabitacion LIKE '%"+numhab+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
         
         
         
     } catch (SQLException ex) {
         Logger.getLogger(CrearUsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, "Datos No Guardados");
     }
 }
    
    public void modificarUsuarioCliente(String nombre,String apellidos,String contraseña,String direccion,String nacionalidad,String correoelectronico,String telefono){
     try {
         int telefonos=Integer.parseInt(telefono);
         Conectar con= new Conectar();
         String sql = ("UPDATE cliente SET nombres= '"+nombre+"' WHERE id LIKE '%"+con.getClienteId()+"%'");
         java.sql.Connection cn= con.ConectarBase();
         java.sql.PreparedStatement PS = cn.prepareStatement(sql);
         PS.executeUpdate();
        
        sql = ("UPDATE cliente SET apellidos= '"+apellidos+"' WHERE id LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE cliente SET pais= '"+nacionalidad+"' WHERE id LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE cliente SET direccion= '"+direccion+"' WHERE id LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE cliente SET telefono= '"+telefonos+"' WHERE id LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE cliente SET email= '"+correoelectronico+"' WHERE id LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE cliente SET password= '"+contraseña+"' WHERE id LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
         
         
         
     } catch (SQLException ex) {
         Logger.getLogger(CrearUsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, "Datos No Guardados");
     }
 }
    
    public void modificarUsuarioAdministrador(String nombre,String apellidos,String contraseña,String direccion,String telefono){
     try {
         int telefonos=Integer.parseInt(telefono);
         Conectar con= new Conectar();
         String sql = ("UPDATE usuario SET nombres= '"+nombre+"' WHERE idUsuario LIKE '%"+con.getClienteId()+"%'");
         java.sql.Connection cn= con.ConectarBase();
         java.sql.PreparedStatement PS = cn.prepareStatement(sql);
         PS.executeUpdate();
        
        sql = ("UPDATE usuario SET apellidos= '"+apellidos+"' WHERE idUsuario LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
       
        sql = ("UPDATE usuario SET direccion= '"+direccion+"' WHERE idUsuario LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE usuario SET telefono= '"+telefonos+"' WHERE idUsuario LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
        sql = ("UPDATE usuario SET password= '"+contraseña+"' WHERE idUsuario LIKE '%"+con.getClienteId()+"%'");
        PS = (java.sql.PreparedStatement) cn.prepareStatement(sql);
        PS.executeUpdate();
         
         
         
     } catch (SQLException ex) {
         Logger.getLogger(CrearUsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, "Datos No Guardados");
     }
 }
    
     void buscarHabitacion(javax.swing.JTable tabla, String Habitacion, String Cama){
    String [] titulos={"Habitación", "Tipo Hab.", "Tipo Cama", "Descripción","Precio"};
    String[] registros= new String[5];
    String Reservada= "S/Reservar";
    
    String sql="SELECT * FROM habitacion where tipoHabitacion LIKE '%"+Habitacion+"%' || tipoCama LIKE '%"+Cama+"%' AND descripción LIKE '%"+Reservada+"%'  ";
    model=new DefaultTableModel(null, titulos);
    Conectar cc=new Conectar();
    Connection cn=cc.ConectarBase();
    
        try {
            Statement st=(Statement) cn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
            registros[0]=rs.getString("numeroHabitacion");
            registros[1]=rs.getString("tipoHabitacion");
            registros[2]=rs.getString("tipoCama");
            registros[3]=rs.getString("descripción");
            registros[4]=rs.getString("precio");
            model.addRow(registros);
            
            
            
            }
            tabla.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    void buscarHabitacion(javax.swing.JTable tabla, String Habitacion){
    String [] titulos={"Habitación", "Tipo Hab.", "Tipo Cama", "Descripción","Precio"};
    String[] registros= new String[5];
    String Reservada= "S/Reservar";
    
    String sql="SELECT * FROM habitacion where numeroHabitacion LIKE '%"+Habitacion+"%' ";
    model=new DefaultTableModel(null, titulos);
    Conectar cc=new Conectar();
    Connection cn=cc.ConectarBase();
    
        try {
            Statement st=(Statement) cn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
            registros[0]=rs.getString("numeroHabitacion");
            registros[1]=rs.getString("tipoHabitacion");
            registros[2]=rs.getString("tipoCama");
            registros[3]=rs.getString("descripción");
            registros[4]=rs.getString("precio");
            
           
            model.addRow(registros);
            }
            tabla.setModel(model);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    void buscarReserva(javax.swing.JTable tabla, String Codigo){
    String [] titulos={"Reserva", "Cliente Id", "Habitacion", "Fecha de Reserva","Fecha de LLegada", "Fecha de Salida", "Estado Reserva"};
    String[] registros= new String[7];
    Conectar cc=new Conectar();
    Connection cn=cc.ConectarBase();
    String sql="SELECT * FROM reserva where codigo LIKE '%"+Codigo+"%' AND cliente_id Like '%"+cc.getClienteId()+"%'";
    model=new DefaultTableModel(null, titulos);
    
    
        try {
            Statement st=(Statement) cn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
            registros[0]=rs.getString("codigo");
            registros[1]=rs.getString("cliente_id");
            registros[2]=rs.getString("numeroHabitacion");
            registros[3]=rs.getString("fechaReserva");
            registros[4]=rs.getString("fechaLlegada");
            registros[5]=rs.getString("fechaSalida");
            registros[6]=rs.getString("estadoReserva");
            cc.setEstadoReserva(rs.getString("estadoReserva"));
           
            model.addRow(registros);
            }
            tabla.setModel(model);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    void buscarPagos(javax.swing.JTable tabla){
    String [] titulos={"Codigo de Reserva", "Cliente Id", "Fecha de Pago", "Monto"};
    String[] registros= new String[4];
    Conectar cc=new Conectar();
    Connection cn=cc.ConectarBase();
    String sql="SELECT * FROM pago where cliente_id Like '%"+cc.getClienteId()+"%'";
    model=new DefaultTableModel(null, titulos);
    
    
        try {
            Statement st=(Statement) cn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
            registros[0]=rs.getString("reserva_codigo");
            registros[1]=rs.getString("cliente_id");
            registros[2]=rs.getString("fechaPago");
            registros[3]=rs.getString("Monto");          
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
            PreparedStatement pps = (PreparedStatement) cn.prepareStatement("DELETE  FROM habitacion where numeroHabitacion LIKE '%"+Codigo+"%'  ");
            pps.executeUpdate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    
    
    }
    
    public void crearReserva(String numhab,String fechaLlegada,String fechaSalida ){
     
     String Reserva= "S/Cancelar";
     String fecReserva = null;
     Calendar c1=new GregorianCalendar();
     String dia=Integer.toString(c1.get(Calendar.DATE));
     String mes=Integer.toString(c1.get(Calendar.MONTH));
     String anio=Integer.toString(c1.get(Calendar.YEAR));
     fecReserva=(anio+"/"+mes+"/"+dia);
     try {
         String SQLINSERT =("INSERT INTO reserva (cliente_id,numeroHabitacion,fechaReserva,fechaLlegada,fechaSalida, estadoReserva)values(?,?,?,?,?,?)");
         Conectar con= new Conectar();
         java.sql.Connection cn= con.ConectarBase();
         java.sql.PreparedStatement PS = cn.prepareStatement(SQLINSERT);
         PS.setInt(1, con.getClienteId());
         PS.setString(2, numhab);
         PS.setString(3, fecReserva);
         PS.setString(4, fechaLlegada);
         PS.setString(5, fechaSalida);
         PS.setString(6, Reserva);
         PS.executeUpdate();
             
     } catch (SQLException ex) {
         Logger.getLogger(CrearUsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, "Datos No Guardados");
     }
 }
    
    
    
       
  
    
}
