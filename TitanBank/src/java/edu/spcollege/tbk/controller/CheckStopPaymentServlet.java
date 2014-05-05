/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.controller;

import edu.spcollege.tbk.domain.Customer;
import edu.spcollege.tbk.domain.bankaccount.BankAccount;
import edu.spcollege.tbk.domain.bankaccount.BankAccountRepository;
import edu.spcollege.tbk.domain.check.Check;
import edu.spcollege.tbk.domain.check.CheckRepository;
import edu.spcollege.tbk.domain.user.UserRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class CheckStopPaymentServlet extends HttpServlet {

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
        
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        Customer customer = new UserRepository().findByUsername(username).getCustomer();
        
        // Bank Accounts
//        BankAccountRepository bankAcctRepo = new BankAccountRepository();
//        List<BankAccount> bankAccounts = bankAcctRepo.findByCustomer(customer);
        
        // Checks
        CheckRepository checkRepo = new CheckRepository();
        
        String[] pendingCheck = request.getParameterValues("pendingCheck");
        for(String checkId : pendingCheck) {
            Long id = Long.valueOf(checkId);
            Check ck = checkRepo.findById(id);
            if (ck != null) {
                ck.stopPayment();
                checkRepo.save(ck);
            }
        }

        List<Check> checks = checkRepo.findByCustomer(customer);
        
//        request.setAttribute("bankAccounts", bankAccounts);
//        request.setAttribute("checks", checks);
        // Then display accounts information
        String url = "/checkStopPaymentConfirm.htm";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
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
