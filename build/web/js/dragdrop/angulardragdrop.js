
var app = angular.module('angular-dragula-demo', [angularDragula(angular)]);

app.controller('MainCtrl', function($scope, dragulaService) {
  dragulaService.options($scope, 'first-bag', {
      copy: false,
      revertOnSpill: true
    });
});
/*
//work in html not in jsp
app.controller('MainCtrl', ['$scope', function ($scope) {
	  $scope.$on('first-bag.drop', function (e, el) {
		  if(document.getElementById("left").childNodes.length===3)
		  {
			  $("#viewfeedback").show(100);
		  }
		  else if(document.getElementById("left").childNodes.length>3)
		  {
			  document.getElementById('right').appendChild(document.getElementById('left').childNodes[3]);
			  $("#viewfeedback").show(100);
		  }
		  else
		  {
			  $("#viewfeedback").hide(100);
		  }
	  });
	}]);
*/
//work for jsp
// insertion-query v1.0.3 (2016-01-20) 
// license:MIT 
// Zbyszek Tenerowicz <naugtur@gmail.com> (http://naugtur.pl/) 
/*
var insertionQ=function(){"use strict";function a(a,b){var d,e="insQ_"+g++,f=function(a){(a.animationName===e||a[i]===e)&&(c(a.target)||b(a.target))};d=document.createElement("style"),d.innerHTML="@"+j+"keyframes "+e+" {  from {  outline: 1px solid transparent  } to {  outline: 0px solid transparent }  }\n"+a+" { animation-duration: 0.001s; animation-name: "+e+"; "+j+"animation-duration: 0.001s; "+j+"animation-name: "+e+";  } ",document.head.appendChild(d);var h=setTimeout(function(){document.addEventListener("animationstart",f,!1),document.addEventListener("MSAnimationStart",f,!1),document.addEventListener("webkitAnimationStart",f,!1)},n.timeout);return{destroy:function(){clearTimeout(h),d&&(document.head.removeChild(d),d=null),document.removeEventListener("animationstart",f),document.removeEventListener("MSAnimationStart",f),document.removeEventListener("webkitAnimationStart",f)}}}function b(a){a.QinsQ=!0}function c(a){return n.strictlyNew&&a.QinsQ===!0}function d(a){return c(a.parentNode)?a:d(a.parentNode)}function e(a){for(b(a),a=a.firstChild;a;a=a.nextSibling)void 0!==a&&1===a.nodeType&&e(a)}function f(f,g){var h=[],i=function(){var a;return function(){clearTimeout(a),a=setTimeout(function(){h.forEach(e),g(h),h=[]},10)}}();return a(f,function(a){if(!c(a)){b(a);var e=d(a);h.indexOf(e)<0&&h.push(e),i()}})}var g=100,h=!1,i="animationName",j="",k="Webkit Moz O ms Khtml".split(" "),l="",m=document.createElement("div"),n={strictlyNew:!0,timeout:20};if(m.style.animationName&&(h=!0),h===!1)for(var o=0;o<k.length;o++)if(void 0!==m.style[k[o]+"AnimationName"]){l=k[o],i=l+"AnimationName",j="-"+l.toLowerCase()+"-",h=!0;break}var p=function(b){return h&&b.match(/[^{}]/)?(n.strictlyNew&&e(document.body),{every:function(c){return a(b,c)},summary:function(a){return f(b,a)}}):!1};return p.config=function(a){for(var b in a)a.hasOwnProperty(b)&&(n[b]=a[b])},p}();"undefined"!=typeof module&&"undefined"!=typeof module.exports&&(module.exports=insertionQ);


insertionQ('.container div').every(function(element){
    //callback on every new div element inside .content
    alert("drop");
    if(document.getElementById("left1").offsetHeight > 0)
    {
    if(document.getElementById("left1").childNodes.length===3)
		  {
			  $("#viewfeedback1").show(100);
		  }
		  else if(document.getElementById("left1").childNodes.length>3)
		  {
			  document.getElementById('right1').appendChild(document.getElementById('left1').childNodes[3]);
			  $("#viewfeedback1").show(100);
		  }
		  else
		  {
			  $("#viewfeedback1").hide(100);
		  }
    }
    
    if(document.getElementById("left2").offsetHeight > 0)
    {
    if(document.getElementById("left2").childNodes.length===3)
		  {
			  $("#viewfeedback2").show(100);
		  }
		  else if(document.getElementById("left2").childNodes.length>3)
		  {
			  document.getElementById('right2').appendChild(document.getElementById('left2').childNodes[3]);
			  $("#viewfeedback2").show(100);
		  }
		  else
		  {
			  $("#viewfeedback2").hide(100);
		  }
    }
    
    if(document.getElementById("left3").offsetHeight > 0)
    {
        //$("#viewfeedback3").show(100);
    if(document.getElementById("left3").childNodes.length===3)
		  {
			  $("#viewfeedback3").show(100);
		  }
		  else if(document.getElementById("left3").childNodes.length>3)
		  {
			  document.getElementById('right3').appendChild(document.getElementById('left3').childNodes[3]);
			  $("#viewfeedback3").show(100);
		  }
		  else
		  {
			  $("#viewfeedback3").hide(100);
		  }
    }

});
*/