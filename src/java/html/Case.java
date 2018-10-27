/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package html;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Piyapong
 */
public class Case {
    questionlist q;
    public Case()
    {
        q = new questionlist();
        q.getQuestionfromdb();
    }
    public void write(HttpServletRequest request, PrintWriter out, int num) throws SQLException
    {
        /*
        out.println("<div id=\"wrapper\">");
        out.println("<div id=\"container\">");
            String[][] testcase = new Casecontent().getCasefromdb(num);
            writeChiefcomplaint(out, testcase);
            writePresentillness(out, testcase);
            writePhysicalexam(out, testcase);
            writeLab(out, testcase);
            writeFinaldiagnosis(out);
            initscript(out, testcase);
        out.println("</div>");
        out.println("</div>");
        */
        
        //out.println("<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>");
        out.println("<!DOCTYPE html>");
        out.println("<html class=\"no-js\" lang=\"en\" ng-app=\"angular-dragula-demo\">");
                header h = new header();
                h.writeheader(out, "Case "+(num+1));
                
                out.println("<body ng-controller=\"MainCtrl\" onmouseup='checkddx()'>");
                out.println("<div id=\"load\"><img src='img/loading.gif'></div>");
                out.println("<div style=\"padding: 50px; width: 960px; background-color: #093145;\"><img src=\"img/drknowlogo.png\" alt=\"Dr Know project\" style=\"width:458px;height:61px;display:block;text-align:center;margin: 0 auto;\"></div>");
                out.println("<div style='background-color: gray; padding: 5px; width: 960px;'>"
                            + "<a class =\"mylink\" href=\"\" onclick=\"signOut();\">sign out</a>"
                            + "<a class=\"mylink\" href=\"https://docs.google.com/a/student.unimelb.edu.au/forms/d/e/1FAIpQLScaZX3W2bGRMWFj8AutU63ZTcTI8l0B4GOggYtocCCMm6LWOw/viewform\" target=\"_blank\">feedback</a>"
                            + "<a class=\"mylink\" href=\"https://drive.google.com/open?id=0BxDG3QCg9o5GeS0yenh1a1FQYlE\" target=\"_blank\">manual</a>"    
                            + "</div>");
                
                
                out.println("<div id=\"wrapper\">");
                out.println("<div id=\"container\">");
                
                    out.println("<div id=\"manual1\" style='position:absolute; display:none; z-index:9999; cursor:pointer;' onclick=\"$(this).fadeOut(300);\"><img src='img/speech2.png'/><div id='description1' style='position:absolute; left:0px; top:0px; width:273px; padding:20px; padding-top:10px; color:#1e4347;'></div></div>");
                    out.println("<div id=\"manual2\" style='position:absolute; display:none; z-index:9999; cursor:pointer;' onclick=\"$(this).fadeOut(300);\"><img src='img/speech2.png'/><div id='description2' style='position:absolute; left:0px; top:0px; width:273px; padding:20px; padding-top:10px; color:#1e4347;'></div></div>");
                    out.println("<div id=\"manual3\" style='position:absolute; display:none; z-index:9999; cursor:pointer;' onclick=\"$(this).fadeOut(300);\"><img src='img/speech2.png'/><div id='description3' style='position:absolute; left:0px; top:0px; width:273px; padding:20px; padding-top:10px; color:#1e4347;'></div></div>");
                    out.println("<div class='title' style='text-align: center;' width='100%'><b>"+"Case "+(num+1)+"</b></div>");
                    
                    String[][] testcase = new Casecontent().getCasefromdb(request, num);
                    
                    writeChiefcomplaint(out, testcase);
                    writePresentillness(out, testcase);
                    writePhysicalexam(out, testcase);
                    writeLab(out, testcase);
                    writeFinaldiagnosis(out);
                    writesurveyFinaldiagnosis(out);

                out.println("</div>");
                out.println("</div>");
                
                initscript(out, testcase);
                out.println("</body>");
        out.println("</html>");
        
    }
    private void writeChiefcomplaint(PrintWriter out, String[][] testcase)
    {
        writeTitle(out, "Chief complaint");
        String cc = "A <div id='item0' style='display: inline'>"+testcase[0][1]+"</div> year old <div id='item1' style='display: inline'>"+testcase[1][1]+"</div> presents to the emergency department with abdominal pain.";
        writeContent(out, cc);
        ddx d = new ddx();
        d.writeHistoryddx0(out);
        viewFeedback(out, "feedback0", "total0", "feedbackimage0");
        //out.println("<button id=\"pistep\" class=\"myButton\" style=\"display: none\" onclick=\"showPIstep(this)\">Go to patient history step >>></button>");
    }
    private void writePresentillness(PrintWriter out, String[][] testcase)
    {
        out.println("<div id= 'pipart' style=\"display: none\">");
        writeTitle(out, "Present illness");
                    
        out.println("<div id= 'pi' class='presentillness'></div>");
        
        q.writeHistorylist(out, testcase);

        ddx d = new ddx();
        d.writeHistoryddx1(out);
        viewFeedback(out,"feedback1","total1","feedbackimage1");
        out.println("</div>");
    }
    private void writePhysicalexam(PrintWriter out, String[][] testcase)
    {
        out.println("<div id= 'pepart' style='display: none'>");
        writeTitle(out, "Physical examination");
        out.println("<div id= 'pe' class='physicalexamination'></div>");
        //questionlist q = new questionlist();
        q.writePElist(out, testcase);
        
        ddx d = new ddx();
        d.writeHistoryddx2(out);
        viewFeedback(out,"feedback2","total2","feedbackimage2");
        out.println("</div>");
    }
    private void writeLab(PrintWriter out, String[][] testcase)
    {
        out.println("<div id= 'labpart' style='display: none'>");
        writeTitle(out, "Laboratory procedures and investigations");
        out.println("<div id= 'lab' class='laboratory'></div>");
        //questionlist q = new questionlist();
        q.writeLablist(out, testcase);
        
        ddx d = new ddx();
        d.writeHistoryddx3(out);
        viewFeedback(out,"feedback3","total3","feedbackimage3");
        out.println("</div>");
    }
    private void writeFinaldiagnosis(PrintWriter out)
    {
        out.println("<div id= 'finaldx' style='display: none'>");
        writeTitle(out, "Final diagnosis");
            out.println("<div id=\"finaldiagnosis\">");
                out.println("<div class='finaldx' onclick=\"selectfinaldiagnosis(this)\" onmouseover='mouseoverfinaldiagnosis(this)' onmouseout='mouseoutfinaldiagnosis(this)'>Appendicitis</div>");
                out.println("<div class='finaldx' onclick=\"selectfinaldiagnosis(this)\" onmouseover='mouseoverfinaldiagnosis(this)' onmouseout='mouseoutfinaldiagnosis(this)'>Gastroenteritis</div>");
                out.println("<div class='finaldx' onclick=\"selectfinaldiagnosis(this)\" onmouseover='mouseoverfinaldiagnosis(this)' onmouseout='mouseoutfinaldiagnosis(this)'>Urinary tract infection</div>");
                out.println("<div class='finaldx' onclick=\"selectfinaldiagnosis(this)\" onmouseover='mouseoverfinaldiagnosis(this)' onmouseout='mouseoutfinaldiagnosis(this)'>Ectopic pregnancy</div>");
                out.println("<div class='finaldx' onclick=\"selectfinaldiagnosis(this)\" onmouseover='mouseoverfinaldiagnosis(this)' onmouseout='mouseoutfinaldiagnosis(this)'>Pelvic inflammatory disease</div>");
            out.println("</div>");
            out.println("<button id=\"viewfeedback4\" class=\"myButton\" style=\"display: none\" onclick=\"showratefinaldiagnosis()\">Confirm</button>");
            //out.println("<div id=\"finalfeedback\" class = 'context' style=\"display: none\" onclick=\"getfeedback4()\"></div>");
        out.println("</div>");
    }
    private void writesurveyFinaldiagnosis(PrintWriter out)
    {
        out.println("<div id= 'surveyfinaldx' style='display: none'>");
        //writeTitle(out, "Final diagnosis");
        out.println("<div id ='selectfinaldiagnosis' class='title'></div>");
        out.println("<div id='dxsurveypart'>");
            out.println("<div class='surveytitle'>How confident are you that you will receive a correct diagnosis in this case?</div>");
                out.println("<div class='context' style='display: inline-block; padding: 0px; margin-top: 0px; height: 70px; line-height: 70px; vertical-align: top;'>Not confident</div>");
                out.println("<div class=\"stars\">\n" +

                                "    <input class=\"star star-5\" id=\"star-5\" type=\"radio\" name=\"confident\" value = '5' onclick='showbuttonafterrating()'/>\n" +
                                "    <label class=\"star star-5\" for=\"star-5\"></label>\n" +
                                "    <input class=\"star star-4\" id=\"star-4\" type=\"radio\" name=\"confident\" value = '4' onclick='showbuttonafterrating()'/>\n" +
                                "    <label class=\"star star-4\" for=\"star-4\"></label>\n" +
                                "    <input class=\"star star-3\" id=\"star-3\" type=\"radio\" name=\"confident\" value = '3' onclick='showbuttonafterrating()'/>\n" +
                                "    <label class=\"star star-3\" for=\"star-3\"></label>\n" +
                                "    <input class=\"star star-2\" id=\"star-2\" type=\"radio\" name=\"confident\" value = '2' onclick='showbuttonafterrating()'/>\n" +
                                "    <label class=\"star star-2\" for=\"star-2\"></label>\n" +
                                "    <input class=\"star star-1\" id=\"star-1\" type=\"radio\" name=\"confident\" value = '1' onclick='showbuttonafterrating()'/>\n" +
                                "    <label class=\"star star-1\" for=\"star-1\"></label>\n" +

                                "</div>");
                out.println("<div class='context' style='display: inline-block; padding: 0px; margin-top: 0px; height: 70px; line-height: 70px; vertical-align: top;'>Very confident</div>");
                out.println("<button id=\"viewfeedback5\" class=\"myButton\" style=\"display: none\" onclick=\"getfeedback4()\">View final feedback</button>");
        out.println("</div>");
        out.println("<div id=\"finalfeedback\" class = 'context' style=\"display: none\"></div>");
        out.println("<div id=\"loadingfinalfeedback\" class = 'context' style=\"display: none\">loading...</div>");
        out.println("</div>");
    }
    private void writeTitle(PrintWriter out, String text)
    {
        out.println("<div class='title' style='cursor:pointer;'>"+text+"</div>");
    }
    private void writeContent(PrintWriter out, String text)
    {
        out.println("<div class='context'>"+text+"</div>");
    }
    private void viewFeedback(PrintWriter out, String title, String content, String feedbackimage)
    {
        
            /*
            out.println("<div class=\"title\">Dr Know feedback (binary)</div>");
            out.println("<div>");
                out.println("<div id='feedback1' class=\"context\"></div>");
                out.println("<div id='feedback2' class=\"context\"></div>");
                out.println("<div id='feedback3' class=\"context\"></div>");
                out.println("<div id='feedback4' class=\"context\"></div>");
                out.println("<div id='feedback5' class=\"context\"></div>");
            out.println("</div>");
            */
        /*
            out.println("<div id = 'feedback1' style=\"display: none\">");
            out.println("<div class=\"title\">Dr Know feedback (multiclass)</div>");
            out.println("<div id='total'></div>");
            out.println("</div>");
        */
            out.println("<div id = '"+title+"' style=\"display: none;\">");
            out.println("<div class=\"feedbacktitle\">Dr Know feedback</div>");
            out.println("<div id='"+content+"' class=\"context\"></div>");
            out.println("</div>");
            
            feedback f = new feedback();
            f.write(out,feedbackimage);
    }
    private void initscript(PrintWriter out, String[][] testcase)
    {
        out.println("<script>");
        out.println("$(document).ready(function(){");
                out.println("$(\"#titlehistory\").html(\"Please select patient information one at a time (\"+maxhistory+\" remaining questions)\");");
                out.println("$(\"#titlephysicalexam\").html(\"Please select patient information one at a time (\"+maxpe+\" remaining questions)\");");
                out.println("$(\"#titlelab\").html(\"Please select patient information one at a time (\"+maxlab+\" remaining questions)\");");
                out.println("for(i=0;i<"+Casecontent.NUM_OF_FEATURE+";i++)");
                out.println("{");
                    out.println("history1[i] = \"?\";");
                out.println("}");
                out.println("history1[0] = \""+testcase[0][0]+"\";");
                out.println("history1[1] = \""+testcase[1][0]+"\";");
        out.println("});");
        out.println("</script>");
        /*
    out.println("<script>");    
    out.println("$(document).ready(function(){");
        out.println("var app = angular.module('angular-dragula-demo', [angularDragula(angular)]);");

out.println("app.controller('MainCtrl', function($scope, dragulaService) {");
  out.println("dragulaService.options($scope, 'first-bag', {");
      out.println("copy: false,");
      out.println("revertOnSpill: true");
    out.println("});");
out.println("});");

//work in chrome not in IE
out.println("app.controller('MainCtrl', ['$scope', function ($scope) {");
	  out.println("$scope.$on('first-bag.drop', function (e, el) {");
		  out.println("if(document.getElementById(\"left\").childNodes.length===3)");
		  out.println("{");
			  out.println("$(\"#viewfeedback\").show(100);");
		  out.println("}");
		  out.println("else if(document.getElementById(\"left\").childNodes.length>3)");
		  out.println("{");
			  out.println("document.getElementById('right').appendChild(document.getElementById('left').childNodes[3]);");
			  out.println("$(\"#viewfeedback\").show(100);");
		  out.println("}");
		  out.println("else");
		  out.println("{");
			  out.println("$(\"#viewfeedback\").hide(100);");
		  out.println("}");
	  out.println("});");
	out.println("}]);");
        out.println("});");
        out.println("</script>");
*/
    }
}
