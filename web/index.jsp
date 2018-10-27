<%-- 
    Document   : index
    Created on : 26/07/2016, 2:06:41 AM
    Author     : Piyapong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="en" ng-app="angular-dragula-demo">
<head>
<meta charset="utf-8">

        <!-- Use the .htaccess and remove these lines to avoid edge case issues.
             More info: h5bp.com/b/378 -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <title></title>
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
        
        <!-- jqcloud -->
        <script src="js/jqcloud/jqcloud.min.js"></script>
        <link rel="stylesheet" href="js/jqcloud/jqcloud.min.css">
        
        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Tangerine" rel="stylesheet">
        
</head>
<body ng-controller="MainCtrl">
    
    <div id='drknowlogo' style='width: 100%; height: 300px; padding: 0px;'></div>
    <!--login page-->
    
    <div id="signin">
        <!--
    <div width="100%" style="padding: 50px"><img src="img/drknowlogo.png" alt="Dr Know project" style="width:458px;height:61px;display:block;text-align:center;margin: 0 auto;"></div>
    -->
        <div class="g-signin2" data-onsuccess="onSignIn" align="center" onclick="signin()"></div>
    
    </div>
    
    <div id="loading" style="display: none">
        <h1 style="display: block; color: #5b6178; text-align: center; padding-top: 50px" width="100%">Loading...</h1>
        <p style="text-align:center; padding-top: 30px"><img src="img/loading.gif"></p>
    </div>
    <br>
    
    <!--center><a class ="mylink" href="" style='color: black' onclick="demo();">demo</a></center-->
    <br>
<br>

<right>
    <div class='context' style='font-size: 70%; text-align: center;'>
        Dr Know project is a part of PhD study created by <a href="mailto:pkhumrin@student.unimelb.edu.au">Piyapong Khumrin</a>
        at the Department of Computing and Information Systems, 
        School of Engineering, 
        University of Melbourne
        under Dr Anna Ryan, Dr Terry Judd, and Professor Karin Verspoor supervision.
    </div>
</right>
<br>
<center><a class="mylink" href="https://docs.google.com/a/student.unimelb.edu.au/forms/d/e/1FAIpQLScaZX3W2bGRMWFj8AutU63ZTcTI8l0B4GOggYtocCCMm6LWOw/viewform" target="_blank">feedback</a></center>
<script>
        
    function signin(){
        $("#signin").hide();
        $("#loading").show();

    };
    window.onbeforeunload = function(e){
        gapi.auth2.getAuthInstance().signOut();
      };
    function onSignIn(googleUser) {
            //var profile = googleUser.getBasicProfile();
            //record('login',[profile.getId()]);
            //window.location = 'page/caselist.jsp';
            var tokenid = googleUser.getAuthResponse().id_token;
            
            
        
        $.ajax({
		type:'POST',
		data: {tokenid: tokenid},
                dataType: 'text',
		url:'Signin',
		success: function(result){
                    $("#info").html("");
                    if($.trim(result)==="firstvisit")
                    {
                        //document.location.href = 'Yearlevel';
                        //document.location.href = 'Caselist';
                        document.location.href = 'PLS';
                    }
                    else if($.trim(result)==="visited")
                    {
                        //document.location.href = 'Yearlevel';
                        //document.location.href = 'Caselist';
                        document.location.href = 'PLS';
                    }
		},
		error:function(){
                    alert("Error");

	          //$("#viewfeedback").html('there is error while submit');
	      }  
		
	});
    };
          
          function demo()
          {
              $.ajax({
		type:'POST',
		data: {id: 'demo'},
                dataType: 'text',
		url:'Caselist',
		success: function(result){
                    document.location.href = 'Caselist';
		},
		error:function(){
                    alert("Error");

	          //$("#viewfeedback").html('there is error while submit');
	      }  
		
            });
              
          };
          $( document ).ready(function() {
            //init logo
            var v =['Dr Know project','appendicitis','gastroenteritis','urinary tract infection','ectopic pregnancy','pelvic inflammatory disease','appendicitis','gastroenteritis','urinary tract infection','ectopic pregnancy','pelvic inflammatory disease','appendicitis','gastroenteritis','urinary tract infection','ectopic pregnancy','pelvic inflammatory disease'];
            var words = JSON.parse(JSON.stringify(finalwordstemplate));
            for(var i=0;i<v.length;i++){
                    words[i].text = v[i];
              };

            $('#drknowlogo').jQCloud(words,{classPattern: null, removeOverflowing:false,colors: ["#1b5a7a", "#362391","#362391","#362391", "#5853bc", "#7098da","#7098da", "#6eb6ff", "#90f2ff", "#e0fcff"],fontSize: {from: 0.05,to: 0.01}});

        });
    </script>

</body>
</html>