/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import machinelearning.Variable;

/**
 *
 * @author Piyapong
 */
@WebServlet(name = "Myrecord", urlPatterns = {"/Myrecord"})
public class Myrecord extends HttpServlet {

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
                Database db = new Database();
                db.init();
                HashMap map = db.getmyrecord(userid);
                    out.println("<table class=\"finalfeedback\">\n" +
                                "        <tr>\n" +
                                "            <th>Case</th>\n" +
                                "            <th>Score</th>\n" +
                                "            <th>Rank</th>\n" +
                                "        </tr>\n");
                    for(int i=0;i<Variable.TOTALCASE;i++)
                    {
                        String totalscore = (String) map.get(""+i);
                        if(totalscore!=null)
                        {
                            out.println("<tr>");
                            out.println("<td><a href='' onclick='return getPerformancehistory(\""+i+"\")'>Case "+(i+1)+"</a></td>");
                            out.println("<td>Overall performance: "+getResult(totalscore)+"</td>");
                            /*
                            out.println("<td>"+totalscore+"</td>");
                            out.println("<td>");
                            try{
                            int n = Integer.parseInt(totalscore)/500;
                            for(int j=0;j<n;j++)
                            {
                                out.println("<div style='margin-right: 5px; display: inline;'><img src='img/star.png'></div>");
                            }
                            }
                            catch(Exception e)
                            {
                                
                            }*/
                            out.println("</td>");
                            out.println("</tr>");
                        }
                        else
                        {
                            out.println("<tr>");
                            out.println("<td><div class='context' style='font-size: 17px;'>Case "+(i+1)+"</div></td>");
                            out.println("<td></td>");
                            out.println("<td></td>");
                            out.println("</tr>");
                        }
                        
                    }
                    out.println("    </table>");
            }
        }
    }
    private String getResult(String totalscore)
    {
       try{
        int n = Integer.parseInt(totalscore);
        
        if(n>90)
        {
            return "Excellent";
        }
        else if(n>40)
        {
            return "Good";
        }
        else if(n>20)
        {
            return "Fair";
        }
        else
        {
            return "Limited";
        }
       }
        catch(Exception e)
        {
            return "";
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
