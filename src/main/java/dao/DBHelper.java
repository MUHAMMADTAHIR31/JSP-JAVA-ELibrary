/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dell
 */
public class DBHelper {
    
    public static Connection getCon() throws SQLException{
	Connection con=null;
        String user = "root";
        String pass ="";
	try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/elibrary",user,pass);
	}catch(ClassNotFoundException e){System.out.println(e);}
        return con;
    }   
}
