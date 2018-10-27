/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package html;

import java.io.PrintWriter;

/**
 *
 * @author Piyapong
 */
public class ddx {
    public void writeHistoryddx0(PrintWriter out)
    {
        
        out.println("<div id = 'ddx0' style='display: inline'>");
		out.println("<div class='title'>Differential diagnosis</div>");
			out.println("<div class='wrapper'>");
                            out.println("<div class='container' dragula=\"first-bag\" id = 'left0'></div>");
                            out.println("<div class='container' dragula=\"first-bag\" id = 'right0'>");
                                out.println("<div class='ddx' style='background-color: #DE7A22'>Appendicitis</div>");
                                out.println("<div class='ddx' style='background-color: #6AB187'>Gastroenteritis</div>");
                                out.println("<div class='ddx' style='background-color: #F52549'>Urinary tract infection</div>");
                                out.println("<div class='ddx' style='background-color: #FFD64D'>Ectopic pregnancy</div>");
                                out.println("<div class='ddx' style='background-color: #283655'>Pelvic inflammatory disease</div>");
                            out.println("</div>");
                        out.println("</div>");
                out.println("<button id=\"viewfeedback0\" class=\"myButton\" style=\"display: none\" onclick=\"getfeedback0()\">View feedback</button>");
        out.println("</div>");

    }
    public void writeHistoryddx1(PrintWriter out)
    {
        
        out.println("<div id = 'ddx1' style='display: none'>");
        out.println("<div class='title'>Differential diagnosis 2</div>");
                        out.println("<div class='wrapper'>");
                            out.println("<div class='container' dragula=\"first-bag\" id = 'left1'></div>");
                            out.println("<div class='container' dragula=\"first-bag\" id = 'right1'></div>");
                        out.println("</div>");
                out.println("<button id=\"viewfeedback1\" class=\"myButton\" style=\"display: none\" onclick=\"getfeedback1()\">View feedback</button>");
        out.println("</div>");

    }
    public void writeHistoryddx2(PrintWriter out)
    {
        
        out.println("<div id = 'ddx2' style='display: none'>");
        out.println("<div class='title'>Differential diagnosis 3</div>");
                        out.println("<div class='wrapper'>");
                            out.println("<div class='container' dragula=\"first-bag\" id = 'left2'></div>");
                            out.println("<div class='container' dragula=\"first-bag\" id = 'right2'></div>");
                        out.println("</div>");
                out.println("<button id=\"viewfeedback2\" class=\"myButton\" style=\"display: none\" onclick=\"getfeedback2()\">View feedback</button>");
        out.println("</div>");

    }
    public void writeHistoryddx3(PrintWriter out)
    {
        
        out.println("<div id = 'ddx3' style='display: none'>");
        out.println("<div class='title'>Differential diagnosis 4</div>");
                        out.println("<div class='wrapper'>");
                            out.println("<div class='container' dragula=\"first-bag\" id = 'left3'></div>");
                            out.println("<div class='container' dragula=\"first-bag\" id = 'right3'></div>");
                        out.println("</div>");
                out.println("<button id=\"viewfeedback3\" class=\"myButton\" style=\"display: none\" onclick=\"getfeedback3()\">View feedback</button>");
        out.println("</div>");

    }
}
