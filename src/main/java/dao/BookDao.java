/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.BookBean;
import beans.IssueBookBean;
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
public class BookDao {
    
    public static int returnBook(String callno,int studentid){
	int status=0;
	try{
            
            Connection con=DBHelper.getCon();
            PreparedStatement ps=con.prepareStatement("update e_issuebook set returnstatus='yes' where callno=? and studentid=?");
            ps.setString(1,callno);
            ps.setInt(2,studentid);
            
            status=ps.executeUpdate();
                if(status>0){
                    PreparedStatement ps2=con.prepareStatement("update e_book set issued=? where callno=?");
                    ps2.setInt(1,getIssued(callno)-1);
                    ps2.setString(2,callno);
                    status=ps2.executeUpdate();
                }
		con.close();
	}catch(SQLException e){
            System.out.println(e);
        }
	return status;
    }
    
    public static List<IssueBookBean> viewIssuedBooks(){
	List<IssueBookBean> list=new ArrayList<IssueBookBean>();
	try{
            Connection con=DBHelper.getCon();
            
            PreparedStatement ps=con.prepareStatement("select * from e_issuebook order by issueddate desc");
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                IssueBookBean bean=new IssueBookBean();
                bean.setCellNo(rs.getString("callno"));
		bean.setStudentId(rs.getString("studentId"));
		bean.setStudentName(rs.getString("studentName"));
		bean.setStudentMobile(rs.getInt("studentMobile"));
		bean.setIssuedDate(rs.getDate("issueDate"));
		bean.setReturnStatus(rs.getString("returnStatus"));
		list.add(bean);
            }
            con.close();	
        }catch(SQLException e){
            System.out.println(e);
        }
	return list;
    }
    
    public static int save(BookBean bean){
        int status=0;
        try{
            
            Connection con=DBHelper.getCon();
            PreparedStatement ps=con.prepareStatement("insert into e_book values(?,?,?,?,?,?)");
            ps.setString(1, bean.getCallNo());
            ps.setString(2, bean.getName());
            ps.setString(3, bean.getAuthor());
            ps.setString(4, bean.getPublisher());
            ps.setInt(5,0);
            ps.setInt(6,bean.getQuantity());
      //      JOptionPane.showMessageDialog(null, bean.getCallNo()+" "+bean.getName()+" "+bean.getAuthor()+" "+bean.getPublisher()+" "+bean.getQuantity());
            status=ps.executeUpdate();
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return status;
    }
    
    public static List<BookBean> view(){
        
        List<BookBean> list=new ArrayList<BookBean>();
	try{
            Connection con=DBHelper.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_book");
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                
                BookBean bean=new BookBean();
                bean.setCallNo(rs.getString("callno"));
		bean.setName(rs.getString("name"));
		bean.setAuthor(rs.getString("author"));
		bean.setPublisher(rs.getString("publisher"));
		bean.setQuantity(rs.getInt("quantity"));
		bean.setIssued(rs.getInt("issued"));
		
		list.add(bean);
            }
            con.close();
	}catch(SQLException e){
            System.out.println(e);
        }
	return list;
    }
    
    public static int delete(String callno){
	int status=0;
        try{
            Connection con=DBHelper.getCon();
            PreparedStatement ps=con.prepareStatement("delete from e_book where callno=?");
            ps.setString(1,callno);
            status=ps.executeUpdate();
            con.close();		
	}catch(SQLException e){
            System.out.println(e);
        }
	return status;
    }
    
    public static boolean checkIssue(String callno){
	boolean status=false;
	try{
            Connection con=DBHelper.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_book where callno=? and quantity>issued");
            ps.setString(1,callno);
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
                status=true;
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e);
        }
	return status;
    }
    
    public static int getIssued(String callno){
        int issued=0;
	try{
            Connection con=DBHelper.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_book where callno=?");
            ps.setString(1,callno);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                issued=rs.getInt("issued");
            }
            con.close();
	}catch(SQLException e){
            System.out.println(e);
        }
	return issued;
    }
    
    public static int issueBook(IssueBookBean bean){
	
        String callno=bean.getCellNo();
        
	boolean checkstatus=checkIssue(callno);
	System.out.println("Check status: "+checkstatus);
	
        if(checkstatus){
            int status=0;
            try{
                Connection con=DBHelper.getCon();
                PreparedStatement ps=con.prepareStatement("insert into e_issuebook values(?,?,?,?,?,?)");
		ps.setString(1,bean.getCellNo());
		ps.setString(2,bean.getStudentId());
		ps.setString(3,bean.getStudentName());
		ps.setLong(4,bean.getStudentMobile());
                java.sql.Date currentDate=new java.sql.Date(System.currentTimeMillis());
                ps.setDate(5,currentDate);
		ps.setString(6,"no");
				
		status=ps.executeUpdate();
		if(status>0){
                    PreparedStatement ps2=con.prepareStatement("update e_book set issued=? where callno=?");
                    ps2.setInt(1,getIssued(callno)+1);
                    ps2.setString(2,callno);
                    status=ps2.executeUpdate();
                }
		con.close();
            }catch(SQLException e){
                System.out.println(e);
            }
            return status;
        }else{
            return 0;
	}
    }
    
}
