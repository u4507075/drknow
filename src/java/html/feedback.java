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
public class feedback {
    public void write(PrintWriter out, String feedbackid)
    {
        /*
        //out.println("<div id=\""+feedbackid+"\" style=\"position:relative; display: none\">");
        out.println("<div id=\"feedbackimage1\" style=\"position:relative; display: none\">");
        out.println("<img src=\"img/dog.png\"");
           out.println("style=\"position:absolute; top:15px; left:65px; z-index:10; width:99px; height:97px; border:none;\"");
           out.println("alt=\"Dr Know's feedback\"");
           out.println("title=\"Dr Know's feedback\" />");
        out.println("<img src=\"img/appendicitis.png\"");
           out.println("style=\"position:absolute; bottom:-10px; left:-100px; z-index:3; width:197px; height:158px; border:none;\"");
           out.println("alt=\"appendicitis\"");
           out.println("title=\"appendicitis 75%\" />");
        out.println("<img src=\"img/ep.png\" id='epimage'");
           out.println("style=\"position:absolute; bottom:-15px; left:80px; z-index:4; width:50px; height:50px; border:none;\"");
           out.println("alt=\"fixed position Willmaster logo\"");
           out.println("title=\"Willmaster logo in fixed position\" />");
        out.println("<img src=\"img/ge.png\"");
           out.println("style=\"position:absolute; bottom:-10px; left:150px; z-index:2; width:50px; height:50px; border:none;\"");
           out.println("alt=\"fixed position Willmaster logo\"");
           out.println("title=\"Willmaster logo in fixed position\" />");
        out.println("</div>");
        

        out.println("<script>");
        out.println("$(document).ready(function(){");
                
                out.println("var screenwidth = 760;");

                out.println("var scale1 = 0.9*2;");
                out.println("var scale2 = 0.3*2;");
                out.println("var scale3 = 0.2*2;");
                out.println("var width = 197;");
                out.println("var height = 158;");
                
                out.println("$(\"#"+feedbackid+"\").children().eq(0).css({left: screenwidth-150});");
                out.println("var left = $(\"#"+feedbackid+"\").children().eq(0).position().left;");

                out.println("$(\"#"+feedbackid+"\").children().eq(1).width(width*scale1);");
                out.println("$(\"#"+feedbackid+"\").children().eq(1).height(height*scale1);");
                out.println("$(\"#"+feedbackid+"\").children().eq(1).css({bottom: -50, left: left-(width*scale1*3/4)});");

                out.println("$(\"#"+feedbackid+"\").children().eq(2).width(width*scale2);");
                out.println("$(\"#"+feedbackid+"\").children().eq(2).height(height*scale2);");
                out.println("$(\"#"+feedbackid+"\").children().eq(2).css({bottom: -15, left: left});");

                out.println("$(\"#"+feedbackid+"\").children().eq(3).width(width*scale3);");
                out.println("$(\"#"+feedbackid+"\").children().eq(3).height(height*scale3);");
                out.println("$(\"#"+feedbackid+"\").children().eq(3).css({bottom: -50, left: left+$(\"#"+feedbackid+"\").children().eq(0).width()});");

        out.println("});");
        out.println("</script>");
        */
    }
}
