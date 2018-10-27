/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import html.header;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import machinelearning.Machinelearning;
import machinelearning.Variable;

/**
 *
 * @author Piyapong
 */
@WebServlet(name = "Caselist", urlPatterns = {"/Caselist"})
public class Caselist extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            
            String id = request.getParameter("id");
            if(id!=null && id.equals("demo"))
            {
                request.getSession().setAttribute("userid", "demo");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sessionid = df.format(new Date());
                request.getSession().setAttribute("sessionid", sessionid);
            }
            String userid = request.getSession().getAttribute("userid").toString();
            out.println("<html class=\"no-js\" lang=\"en\" ng-app=\"angular-dragula-demo\">");
            header h = new header();
                h.writeheader(out, "Dr Know");
                //out.println("<a href=\"index.jsp\">Home</a>");
                out.println("<body ng-controller=\"MainCtrl\">");
                out.println("<div id=\"load\"><img src='img/loading.gif'></div>");
                out.println("<div id=\"wrapper\">");
                
                out.println("<div id=\"container\">");
                
                

                    out.println("<div class=\"title\" align=\"center\">Welcome to Dr Know's Hospital</div>");
                    
                    out.println("<div style='background-color: gray; padding: 5px; border-radius: 2px;'>"
                            + "<a class =\"mylink\" href=\"\" onclick=\"signOut();\">sign out</a>"
                            //+ "<a class =\"mylink\" href=\"\" onclick=\"getMyrecord()\">your records</a>"
                            //+ "<a class =\"mylink\" href=\"\" onclick=\"\">group records</a>"
                            + "<a class=\"mylink\" href=\"https://docs.google.com/a/student.unimelb.edu.au/forms/d/e/1FAIpQLScaZX3W2bGRMWFj8AutU63ZTcTI8l0B4GOggYtocCCMm6LWOw/viewform\" target=\"_blank\">feedback</a>"
                            + "<a class=\"mylink\" href=\"https://drive.google.com/open?id=0BxDG3QCg9o5GeS0yenh1a1FQYlE\" target=\"_blank\">manual</a>"  
                            + "</div>");
                    
            if(userid.equals("demo"))
            {

                    for(int i=0;i<Variable.TOTALCASE;i++)
                    {
                        out.println("<button id=\"viewfeedback"+i+"\" class=\"myButton\" style=\"display: inline; width: 100%; background:#FF420E\" onclick='selectcase("+i+")'>Case "+(i+1)+"</button>");
                    }

            }
            else if(!userid.equals(""))
            {

                    /*
                    for(int i=0;i<5;i++)
                    {
                        out.println("<button id=\"viewfeedback"+i+"\" class=\"myButton\" style=\"display: inline; width: 100%; background:#FF420E\" onclick='selectcase("+i+")'>Case "+(i+1)+"</button>");
                    }
                    */

                    Database db = new Database();
                    try {
                        db.init();
                    } catch (ServletException ex) {
                        Logger.getLogger(Caselist.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String caselist = db.getfinisedcaselist(userid);
                    out.println(caselist);
            }
            
            out.println("</div>");
            out.println("</div>");

            out.println("</body>");
        out.println("</html>");
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
