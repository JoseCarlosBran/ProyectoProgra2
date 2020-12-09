/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {
    private static Connection con;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String usuario = "root";
    private static final String contra = "";
    private static final String url = "jdbc:mysql://localhost:3306/datos";

    public Conectar() {
        con = null;
        try{
           Class.forName(driver);
           con = DriverManager.getConnection(url, usuario, contra);
           if(con != null){
               System.out.println("Conexion establecida...");
           }
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error al conectar"+e);
        }
    }
    // este metodo nos retorna la conexion
    public Connection getConnection(){
        return con;
    }
// este metodo nos desconectamos de la base de datos
    public void desconectar(){
        con = null;
        if(con == null){
            System.out.println("Conexion terminada");
        }
    }
}

