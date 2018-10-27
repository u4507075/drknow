/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import html.header;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Piyapong
 */
@WebServlet(name = "Survey", urlPatterns = {"/Survey"})
public class Survey extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            header h = new header();
            h.writeheader(out, "Dr Know");
            out.println("<body>");
            
            
            //out.println("<div id='casesurveypart'>");
            
                //out.println("<button id=\"viewfeedback5\" class=\"myButton\" style=\"display: none\" onclick=\"getfeedback4()\">Next case</button>");
            
            int caseid = Integer.parseInt(request.getSession().getAttribute("caseid").toString())+1;
            
            out.println("<div class='title' style='text-align: center;'>Case survey</div>");
            out.println("<a href='' onclick='getPerformancehistory(\""+caseid+"\"); return false;'><div class='context'>click here to review your work on this case</div></a><br>");

            String[] topic = {  "It is easy to select the likely diagnosis in this case.",
                                "The interim feedback on each diagnosis at the end of history taking, physical examination, laboratory tests, and investigations was helpful in enhancing my diagnostic reasoning in this case.",
                                "The final feedback was helpful in enhancing my diagnostic reasoning in this case.",
                                "The tool is easy to use.",
                                "Dr Know’s feedback has an influence on my diagnostic reasoning.",
                                "While working on previous cases, I felt I was making the same decisions a doctor world make in real life.",
                                "After completing these cases, I feel better prepared to confirm a diagnosis and exclude differential diagnoses in a real patient presenting with abdominal pain.",
                                "Overall, working through the cases was a worthwhile learning experience."};
            //out.println("<button id='submityearlevel' class='myButton' style='display: none' onclick='submityearlevel()'>submit</button>");
            int num = topic.length;
            if((caseid+1)%5>0)
            {
                num = 3;
            }
            for(int i=0;i<num;i++)
            {
                //int num = i+1;
                addTopic(out,topic[i],num,i);
            }

            out.println("<div class='surveytitle' style='text-align: justify;'>Comment</div>");
            out.println("<div style='width:100%'><textarea id='surveytextarea' rows=\"4\" cols=\"100\" style='width:100%'></textarea><div>");
            
            out.println("<button id='submitsurvey' class='myButton' style='display: none' onclick='submitsurvey()'>submit</button>");
        
            out.println("</body>");
            out.println("</html>");
        }
    }
    private void addTopic(PrintWriter out, String question, int num, int i)
    {

        out.println("<div class='surveytitle' style='text-align: justify; background-color: lightgray; padding: 10px;'>"+question+"</div>");

                out.println("<div class='context' style='display: inline-block; padding-left: 25px; margin-top: 0px; height: 70px; line-height: 70px; vertical-align: top;'>Strongly disagree</div>");
                out.println("<div class=\"stars\">\n" +

                                "    <input class=\"star star-5\" id=\"star-"+i+"5\" type=\"radio\" name=\"survey"+i+"\" value = '5' onclick='selectsurvey("+num+")'/>\n" +
                                "    <label class=\"star star-5\" for=\"star-"+i+"5\"></label>\n" +
                                "    <input class=\"star star-4\" id=\"star-"+i+"4\" type=\"radio\" name=\"survey"+i+"\" value = '4' onclick='selectsurvey("+num+")'/>\n" +
                                "    <label class=\"star star-4\" for=\"star-"+i+"4\"></label>\n" +
                                "    <input class=\"star star-3\" id=\"star-"+i+"3\" type=\"radio\" name=\"survey"+i+"\" value = '3' onclick='selectsurvey("+num+")'/>\n" +
                                "    <label class=\"star star-3\" for=\"star-"+i+"3\"></label>\n" +
                                "    <input class=\"star star-2\" id=\"star-"+i+"2\" type=\"radio\" name=\"survey"+i+"\" value = '2' onclick='selectsurvey("+num+")'/>\n" +
                                "    <label class=\"star star-2\" for=\"star-"+i+"2\"></label>\n" +
                                "    <input class=\"star star-1\" id=\"star-"+i+"1\" type=\"radio\" name=\"survey"+i+"\" value = '1' onclick='selectsurvey("+num+")'/>\n" +
                                "    <label class=\"star star-1\" for=\"star-"+i+"1\"></label>\n" +

                                "</div>");
                out.println("<div class='context' style='display: inline-block; padding: 0px; margin-top: 0px; height: 70px; line-height: 70px; vertical-align: top;'>Strongly agree</div>");


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
