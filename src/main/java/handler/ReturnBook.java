/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import dao.BookDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ReturnBook", urlPatterns = {"/ReturnBook"})
public class ReturnBook extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.println("<head>");
            out.println("<title>Return Book</title>");
            out.println("<link rel='stylesheet' href='assests/bootstrap.min.css'/>");
            out.println("</head>");
            out.println("<body>");
            
            request.getRequestDispatcher("navlibrarian.html").include(request, response);
		
            out.println("<div class='container'>");
            String callno=request.getParameter("callno");
            String sstudentid=request.getParameter("studentid");
            int studentid=Integer.parseInt(sstudentid);
		
            int i=BookDao.returnBook(callno,studentid);
            
            if(i>0){
                out.println("<h3>Book returned successfully</h3>");
            }else{
                out.println("<h3>Sorry, unable to return book.</h3><p>We may have sortage of books. Kindly visit later.</p>");
            }
            out.println("</div>");
		
            request.getRequestDispatcher("footer.html").include(request, response);
            out.close();
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
