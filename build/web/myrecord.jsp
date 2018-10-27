<%-- 
    Document   : finishedcase
    Created on : 28/12/2016, 11:48:05 PM
    Author     : Piyapong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">

        <!-- Use the .htaccess and remove these lines to avoid edge case issues.
             More info: h5bp.com/b/378 -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <title>My records</title>
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Mobile viewport optimized: j.mp/bplateviewport -->
        <meta name="viewport" content="width=device-width,initial-scale=1">

        <meta name="google-signin-client_id" content="1067307234695-psqm5pesgl0tmfc0cc17sgekjcgaodn0.apps.googleusercontent.com">

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory: mathiasbynens.be/notes/touch-icons -->

        <!-- CSS: implied media=all -->
        <!-- CSS concatenated and minified via ant build script-->
        <link rel="stylesheet" href="css/style.css">
        <link href='https://fonts.googleapis.com/css?family=Crimson+Text' rel='stylesheet' type='text/css'>
        <!-- end CSS-->

        <!-- More ideas for your <head> here: h5bp.com/d/head-Tips -->
              <!-- drag drop -->
              <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.7/angular.min.js"></script>
          <!--script src="https://rawgit.com/bevacqua/angular-dragula/master/dist/angular-dragula.min.js"></script-->
              <script src="js/dragdrop/angularjs-dragula.min.js"></script>
              <script src="js/dragdrop/angulardragdrop.js"></script>

        <!-- All JavaScript at the bottom, except for Modernizr / Respond.
             Modernizr enables HTML5 elements & feature detects; Respond is a polyfill for min/max-width CSS3 Media Queries
             For optimal performance, use a custom Modernizr build: www.modernizr.com/download/ -->
        <script src="js/modernizr-2.0.6.min.js"></script>
        <script src="js/jquery-3.1.0.js"></script>

        <!-- text animation -->	

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>

        <!-- google login -->
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
        
        <!--my function-->
        <script src="js/function.js"></script>
        <script src="js/yearlevel.js"></script>
        <script src="js/variable.js"></script>
        
        <!-- rating scale -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    </head>
    <body ng-controller="MainCtrl">
        <div id="record"></div>
        <script>
            $( document ).ready(function() {
                //document.title = $( "div.title" )[0].text();
                
                $("#record").append(sessionStorage.getItem("myrecord"));
            });
        </script>
    </body>
</html>