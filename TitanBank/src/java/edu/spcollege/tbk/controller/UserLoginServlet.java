/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.controller;


import edu.spcollege.tbk.domain.InvalidPasswordException;
import edu.spcollege.tbk.domain.UserNotFoundException;
import edu.spcollege.tbk.domain.user.UserRepository;
import edu.spcollege.tbk.domain.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zhou
 */
public class UserLoginServlet extends HttpServlet {

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
        
        String option = request.getParameter("option");
        
        HttpSession session = request.getSession(true);
        
        String nextUrl = null;
        
        if (option.equals("login")) {
            // Login
            AuthenticationService AuthServ = new AuthenticationService(new UserRepository());

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Customer loginCustomer = null;
            try {
                loginCustomer = AuthServ.login(username, password);
            } catch (UserNotFoundException | InvalidPasswordException ex) {
                Logger.getLogger(UserLoginServlet.class.getName()).log(Level.SEVERE, null, ex.getMessage());
                loginCustomer = null;
            }

            if (loginCustomer != null) {
                session.setAttribute("loginStatus", true);
                session.setAttribute("username", username);
                session.setAttribute("customerName", loginCustomer.getFirstName());
                nextUrl = "/index.htm";
                //response.sendRedirect("index.htm");
            } else {
                nextUrl = "/loginError.htm";
            }
        }
        else {
            //Logout
            session.removeAttribute("loginStatus");
            session.removeAttribute("username");
            session.removeAttribute("customerName");
            nextUrl = "/login.htm";
        }
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextUrl);
        dispatcher.forward(request, response);
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
