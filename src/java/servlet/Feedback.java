/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Feedback", urlPatterns = {"/Feedback"})
public class Feedback extends HttpServlet {

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

        String instance = request.getParameter("historypart1");
        
        String feedbackid = request.getParameter("feedbackid");

        String step = request.getParameter("step");
        
        String[] s = instance.split(",");
        
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        
        if(step.equals("cc")||step.equals("hx")||step.equals("pe")||step.equals("lab"))
        {
            
            
            String[] ddx = request.getParameter("selectedddx").split(",");
            Machinelearning ml = new Machinelearning();
            ml.getTotalfeedback(request, step, s, out, feedbackid, ddx);
            //String feedback = ml.getTotalfeedback(s,out);
            
            //out.print(ddx);
            
            

        }
        else if(step.equals("final"))
        {
            response.setContentType("text/html;charset=UTF-8");
            Machinelearning ml = new Machinelearning();
            //ml.addFinalfeedback(request,s,out,feedbackid, request.getParameter("selectedddx"), request.getSession().getAttribute("caseid").toString());
            ml.addFinalfeedback2(request,s,out,feedbackid, request.getParameter("selectedddx"), request.getSession().getAttribute("caseid").toString());
            String confident = request.getParameter("confident");
            Database db = new Database();
            db.init();
            db.selectlevelofconfidence(request.getSession().getAttribute("userid").toString(), 
                                        request.getSession().getAttribute("sessionid").toString(), 
                                        request.getSession().getAttribute("caseid").toString(), confident);

        }
        
        
        
        
        
        
        /*
        response.setContentType("text/plain");
        //response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(instance);
        */
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
