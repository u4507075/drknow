/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import html.header;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Piyapong
 */
@WebServlet(name = "PLS", urlPatterns = {"/PLS"})
public class PLS extends HttpServlet {

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
            String userid = request.getSession().getAttribute("userid").toString();
            if(!userid.equals(""))
            {
                out.println("<html class=\"no-js\" lang=\"en\" ng-app=\"angular-dragula-demo\">");
                    header h = new header();
                        h.writeheader(out, "Dr Know");
                        //out.println("<a href=\"index.jsp\">Home</a>");
                        out.println("<body ng-controller=\"MainCtrl\">");

                        out.println("<center><div class=\"title\">Plain Language Statement</div></center>\n" +
                                "    <div width=\"100%\" style=\"display: block; height: 500px; overflow-y: scroll; overflow-x: hidden\">\n" +
                                "        <center><img src=\"img/pls1.png\" alt=\"plain language statement\" width=\"100%\"></center>\n" +
                                "        <center><img src=\"img/pls2.png\" alt=\"plain language statement\" width=\"100%\"></center>\n" +
                                "        <center><img src=\"img/pls3.png\" alt=\"plain language statement\" width=\"100%\"></center>\n" +
                                "    </div>\n");

                        Database db = new Database();
                        db.init();

                        boolean firstvisit = db.checkYearlevelrecord(request.getSession().getAttribute("userid").toString());
                        if(firstvisit)
                        {
                             out.println("<button id=\"pls\" class=\"myButton\" onclick=\"redirecttoyearlevel()\" style=\"width: 100%\">Accept</button>");
                        }
                        else
                        {
                            //out.println("<button id=\"pls\" class=\"myButton\" onclick=\"redirecttoyearlevel()\" style=\"width: 100%\">Accept</button>");
                            out.println("<button id=\"pls\" class=\"myButton\" onclick=\"redirecttocaselist()\" style=\"width: 100%\">Accept</button>");
                        }

                        out.println("</body>");
                out.println("</html>");
            }
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
