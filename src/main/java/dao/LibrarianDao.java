/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.LibrarianBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class LibrarianDao {
    
    public static List<LibrarianBean> view(){
        List<LibrarianBean> list = new ArrayList<LibrarianBean>();
        
        try{
            Connection con = DBHelper.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_librarian");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                LibrarianBean bean=new LibrarianBean();
		
                bean.setId(rs.getInt("libraryId"));
		bean.setName(rs.getString("name"));
		bean.setEmail(rs.getString("email"));
                bean.setPassword(rs.getString("password"));
		bean.setMobile(rs.getInt("mobile"));
		list.add(bean); 
            }
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    } 
    
    public static int delete(int id){
	int status=0;
	try{
            
            Connection con=DBHelper.getCon();
            PreparedStatement ps=con.prepareStatement("delete from e_librarian where libraryId=?");
            ps.setInt(1,id);
            status=ps.executeUpdate();
            con.close();
        }catch(SQLException e){
            System.out.println(e);
        }
	return status;
    }
    
    public static LibrarianBean viewById(int id){
        
        LibrarianBean bean = new LibrarianBean();
        try{
            Connection con=DBHelper.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_librarian where libraryId=?");
            ps.setInt(1, id);
            
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                bean.setId(rs.getInt(1));
		bean.setName(rs.getString(2));
		bean.setPassword(rs.getString(3));
		bean.setEmail(rs.getString(4));
		bean.setMobile(rs.getInt(5));
            }
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return bean;
    }
    
    public static int update(LibrarianBean bean){
	int status=0;
	
        try{
            Connection con=DBHelper.getCon();
            PreparedStatement ps=con.prepareStatement("update e_librarian set name=?,email=?,password=?,mobile=? where libraryId=?");
            ps.setString(1,bean.getName());
            ps.setString(2,bean.getEmail());
            ps.setString(3,bean.getPassword());
            ps.setLong(4,bean.getMobile());
            ps.setInt(5,bean.getId());
            
            status=ps.executeUpdate();
            con.close();		
	}catch(SQLException e){
            System.out.println(e);
        }
	return status;
    }
    
    public static int save(LibrarianBean bean){
        int status=0;
        try{
            Connection  con=DBHelper.getCon();
            PreparedStatement ps=con.prepareStatement("insert into e_librarian(name,email,password,mobile)values(?,?,?,?)");
            
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getEmail());
            ps.setString(3, bean.getPassword());
            ps.setInt(4, bean.getMobile());
           
           status=ps.executeUpdate();
           con.close();
        }catch(SQLException e){
           e.getMessage();
        }
        return status;
    }
    
    public static boolean authenticate(String email,String password){
        boolean status=false;
        try{
            Connection  con=DBHelper.getCon();
            PreparedStatement ps = con.prepareStatement("Select * from e_librarian where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
                status=true;
            }
            con.close();
        }catch(SQLException e){
            e.getMessage();
        }
        return status;
    }
    
}
