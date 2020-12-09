/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicahab;

import clases.conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/**
 *
 * @author wilber chitic
 */
public class crearHab {
    
    private final String SQL_INSERT =("INSERT INTO habitacion (numeroHabitacion,tipoHabitacion,tipoCama,descripción,precio)values(?,?,?,?,?)");
    private final conectar con;
    private PreparedStatement PS;
 public crearHab(){
     
     PS=null;
     con=new conectar();
     
 
 
 
 
 }   
 
 
 public int crearHabitacion(int numhab,String tiphab,String tipocam,String desc,float precio ){
     int res=0;
     try {
         Conectar con= new Conectar();
         Connection cn= con.ConectarBase();
         
         PS=con.conectarBase().prepareStatement(SQL_INSERT);
         PS.setInt(0, numhab);
         PS.setString(1, tiphab);
         PS.setString(2, tipocam);
         PS.setString(3, desc);
         PS.setFloat(4, precio);
         res=PS.executeUpdate();
         if (res>0){
             JOptionPane.showMessageDialog(null, " HABITACION CREADA ");
         
         
         }
         
         
         
     } catch (SQLException e) {
         
         System.err.println("error al guardar en la base de datos " + e.getMessage());
            
     }finally{
         
          PS=null;
          
     
     
     
     }
     
     
     
     
     return res;
 
 
 
 
 }
 
 
 
 
 
 
 
    
    
}
