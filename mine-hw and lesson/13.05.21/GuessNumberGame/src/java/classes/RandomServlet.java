/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huser
 */
@WebServlet(name = "RandomServlet", urlPatterns = {"/RandomServlet"})
public class RandomServlet extends HttpServlet {

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
        int randomNum = 0;//the number that has been random
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            
            Random rnd = new Random();
            Cookie c = new Cookie("randomNum", rnd.nextInt(10) + "");
            response.addCookie(c);
        } else {
            for (Cookie c : cookies) {
                if (c.getName().equals("randomNum")) {
                    randomNum = Integer.parseInt(c.getValue());
                }
            }
        }

        int guessNum = Integer.parseInt(request.getParameter("num"));
        int numberGuesses = 0;
        if (request.getSession().getAttribute("numberGuesses") != null) {
            numberGuesses = Integer.parseInt(request.getSession().getAttribute("numberGuesses").toString());
        }

        PrintWriter out = response.getWriter();
        numberGuesses++;
        if (guessNum == randomNum) {
            
            out.append("<h1>You Win!!! after " + numberGuesses + "<h1/>");
        } else {

            request.getSession().setAttribute("numberGuesses", numberGuesses);
            request.getServletContext().getRequestDispatcher("/index.html").include(request, response);
            out.append("<h1>You Failed!!! number guesses " + numberGuesses + "<h1/>");
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
