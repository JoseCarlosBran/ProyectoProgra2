
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Ajcalon
 */
public class Conectar {
    
    private static java.sql.Connection con;
    private static final String Driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/proyecto2018";
    
    
    
     public Connection Conectar(){
        //declaramos variable de modo conexion
        Connection conect = null;
        try{
            
            Class.forName(Driver);
            conect = (Connection) DriverManager.getConnection(url, user, password);
            
            if(conect != null){
            JOptionPane.showMessageDialog(null,"Conexion exitosa");
            }
        }catch(Exception e){
            
        }
        
        return conect;
    }
     public Connection getConnection(){
        return  con;
    }

    
    
}
