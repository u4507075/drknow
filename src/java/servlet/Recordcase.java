/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import html.Casecontent;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import machinelearning.Machinelearning;

/**
 *
 * @author Piyapong
 */
@WebServlet(name = "Recordcase", urlPatterns = {"/Recordcase"})
public class Recordcase extends HttpServlet {

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
        
        String htmlcontent = request.getParameter("htmlcontent");
        String totalscore = request.getParameter("totalscore");
        Database db = new Database();
        try {
            db.init();
        } catch (ServletException ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
            db.insertfinishedcase(request.getSession().getAttribute("userid").toString(), 
                                request.getSession().getAttribute("sessionid").toString(), 
                                request.getSession().getAttribute("caseid").toString(), 
                                new Casecontent().getCorrectdiagnosis(request, Integer.parseInt(request.getSession().getAttribute("caseid").toString())), 
                                totalscore,
                                htmlcontent);
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
