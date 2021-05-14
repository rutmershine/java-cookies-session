/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author teacher
 */
@WebServlet(name = "WelcomeServlet", urlPatterns = {"/Welcome", "/Home"},loadOnStartup = 1)
public class WelcomeServlet extends HttpServlet {



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
        System.out.println("-------IN DOGET");
        String name = request.getParameter("user");
        String number = request.getParameter("num");
        
        response.sendRedirect("https://www.google.co.il/search?q="+name);
        
        int num;
        try{
            num=Integer.parseInt(number);
        }
        catch(Exception e){
            num=10;
        }        
        
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        Date today = (Date)request.getServletContext().getAttribute("today");
        out.append("<h1>today is: "+today+"</h1>");
        out.append("<h1>HELLO "+name+"!</h1>");
        for(int i=1;i<=num;i++){
            out.append("<span>"+i+"</span></br>");
        }
        
        
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
        doGet(request, response);
        
    }

    @Override
    public void init() throws ServletException {
        System.out.println("-----IN INIT");
    }

    @Override
    public void destroy() {
        System.out.println("--------IN Destroy");
    }

    

}
