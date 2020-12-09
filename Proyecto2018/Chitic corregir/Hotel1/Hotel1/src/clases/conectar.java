/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author wilber chitic
 */
public class conectar {
     private static final String Driver  ="com.mysql.jdbc.Driver";//variable que contiene el driver que hemos importado para hacer la conexion
    private static final String USER = "root"; // usuario por defecto de mysql 
    private static final String PASSWORD = "";//sin contrase;a 
    private static final String URL="jdbc:mysql://localhost:3306/proyecto2018?autoReconnect=true&useSSL=false";
    //direccion  donde va alojado mi base de datos
    
    
    private Connection con;
     
    public conectar(){
    
    con=null;
    
    }
    
    
    
    public Connection conectarBase(){     
        try {
            
            Class.forName(Driver);
           con= DriverManager.getConnection(URL, USER, PASSWORD);
         
            
        } catch (ClassNotFoundException |SQLException ex) {
             JOptionPane.showMessageDialog(null,ex.getMessage(),"error al conectar con la base de datos ",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            
            
            
            
        }
        return con;
        
        
        
        
    
    
    
    
    
    
    
    
    }
     public void close(){
        try{
            con.close();
            con=null;
            if(con==null){
                System.out.println("conexion terminada");
            }
        
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null,ex.getMessage(),"error al cerrar  la conexion de datos ",JOptionPane.ERROR_MESSAGE);
             }  
}
}