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
public class header {
    public void writeheader(PrintWriter out, String title)
    {
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");

        //<!-- Use the .htaccess and remove these lines to avoid edge case issues.
        //More info: h5bp.com/b/378 -->
        out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">");

        out.println("<title>"+title+"</title>");
        out.println("<meta name=\"description\" content=\"\">");
        out.println("<meta name=\"author\" content=\"\">");

        //<!-- Mobile viewport optimized: j.mp/bplateviewport -->
        out.println("<meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">");

        //<!-- Place favicon.ico and apple-touch-icon.png in the root directory: mathiasbynens.be/notes/touch-icons -->

        //<!-- CSS: implied media=all -->
        //<!-- CSS concatenated and minified via ant build script-->
        out.println("<link rel=\"stylesheet\" href=\"css/style.css\">");
        out.println("<link href='https://fonts.googleapis.com/css?family=Crimson+Text' rel='stylesheet' type='text/css'>");
        //<!-- end CSS-->

        //<!-- More ideas for your <head> here: h5bp.com/d/head-Tips -->
              //<!-- drag drop -->
        //out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.7/angular.min.js\"></script>");
        out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js\"></script>");
        //out.println("<script src=\"https://rawgit.com/bevacqua/angular-dragula/master/dist/angular-dragula.min.js\"></script>");
        out.println("<script src=\"js/dragdrop/angularjs-dragula.min.js\"></script>");
        out.println("<script src=\"js/dragdrop/angulardragdrop.js\"></script>");

        //<!-- All JavaScript at the bottom, except for Modernizr / Respond.
             //Modernizr enables HTML5 elements & feature detects; Respond is a polyfill for min/max-width CSS3 Media Queries
             //For optimal performance, use a custom Modernizr build: www.modernizr.com/download/ -->
        out.println("<script src=\"js/modernizr-2.0.6.min.js\"></script>");
        out.println("<script src=\"js/jquery-3.1.0.js\"></script>");

        //out.println("<script src=\"js/variable.js\"></script>");
        

        //<!-- my function -->
        out.println("<script src=\"js/function.js\"></script>");
        out.println("<script src=\"js/yearlevel.js\"></script>");
        out.println("<script src=\"js/variable.js\"></script>");
        
        //<!-- rating scale -->
        out.println("<link rel=\"stylesheet\" href=\"//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css\">");
        
        //<!-- text animation -->	

        out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\" type=\"text/javascript\"></script>");
        
        out.println("<link rel=\"stylesheet\" href=\"//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css\">");
        
        //<!-- jqcloud -->
        out.println("<script src=\"js/jqcloud/jqcloud.min.js\"></script>");
        out.println("<link rel=\"stylesheet\" href=\"js/jqcloud/jqcloud.min.css\">");
        
        //<!-- Google font -->
        out.println("<link href=\"https://fonts.googleapis.com/css?family=Tangerine\" rel=\"stylesheet\">");
        
        out.println("</head>");
        
        //<!-- database -->
        //out.println("<script src=\"js/db/database.js\"></script>");
    }
}
