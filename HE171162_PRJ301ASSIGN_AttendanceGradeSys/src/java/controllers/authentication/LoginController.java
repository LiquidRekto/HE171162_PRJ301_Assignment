/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.authentication;

import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import cfg.*;

/**
 *
 * @author sonnt
 */
public class LoginController extends HttpServlet {
   
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("home");
            return;
        }
        if (request.getAttribute("targetUrl") != null) {
            request.setAttribute("targetUrl",request.getAttribute("targetUrl"));
        } else {
            request.setAttribute("targetUrl","home");
        }
        request.setAttribute("state","normal");
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        UserDBContext db = new UserDBContext(MyDBConfig.getConfig());
        User user = db.get(username, password);
        if(user != null)
        {
            request.getSession().setAttribute("user", user);
            String redir = "";
            if (request.getParameter("targetUrl") == null) {
                redir = "main";
            } else {
                redir = request.getParameter("targetUrl");
            }
            response.sendRedirect(redir);
            //request.getRequestDispatcher(request.getAttribute("targetUrl").toString()).forward(request, response);
        }
        else
        {
            if (request.getParameter("targetUrl") == null) {
                request.setAttribute("targetUrl","home");
            } else {
                request.setAttribute("targetUrl",request.getParameter("targetUrl"));
            }
            request.setAttribute("state","failed");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
