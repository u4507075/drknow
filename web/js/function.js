
document.onreadystatechange = function () {
            var state = document.readyState;
            if (state === 'complete') {
                   document.getElementById('interactive');
                   document.getElementById('load').style.visibility="hidden";
            }
          };
$( document ).ready(function() {
    showmanual([600,900,400],[240,340,440],["1. Based on the patient information in the chief complaint (i.e. age and gender) select the three most likely diagnoses.","2. Select a diagnosis by dragging it from this list into the area on the left.","3. Prioritise you selections by dragging them into your preferred order."]);
    //showmanual([600,900,400],[240,340,440],["1. Use patient information form the chief complaint to select three from five diagnoses.","2. Use your mouse to drag a dianosis from your right side and drop in a box on your left.","3. Drop a selected diagnosis in this box."]);
    //showmanual("#left0","Select differential diagnosis","Use patient information form the chief complaint to select three from five diagnoses. Use your mouse to drag a dianosis box from your right side and drop in a box on your left.");
});
function signOut() {
    //window.location = 'index.jsp';

        
          $.ajax({
              type:'POST',
              dataType: 'text',
              url:'Signout',
              success: function(result){
                        //alert("on sign in"+result);
                        window.location = 'index.jsp';
              },
              error:function(){
                  alert("Error");
                //$("#viewfeedback").html('there is error while submit');
            }  

            });
      

};

function redirecttoyearlevel()
{
    document.location.href = 'Yearlevel';
}
function redirecttocaselist()
{
    document.location.href = 'Caselist';
}

function selectcase(num){

    sessionStorage.setItem("caseid", num); 
    $.ajax({
		type:'POST',
		data: {name: 'caseid', value: num},
                dataType: 'text',
		url:'setAttribute',
		success: function(result){
                    //alert(result);
                    document.location.href = 'Selectcase';
		},
		error:function(){
	      }  
		
	});

}

function gethistory1(element,answer,id,code){

        //alert(caseid);
	element.style.display='none';
	document.getElementById("pilist").style.display='none';
	//$("#pilist").hide(200);
	var ans = document.createElement("div"); 
	ans.style.display = "block";
	ans.className = "context";
        ans.id = "item"+id;
  	//ans.innerHTML = answer;

	$('#pi').append(ans);
        //window.scroll(0,findPos(document.getElementById("pi")));
  	//$('.presentillness').innerHTML = answer;
  	
  	changeText(answer,ans,30,"#titlehistory","#pilist","#ddx1");
  	
        
        history1[id] = code;
        
        recordselectfeature('hx',id);
        
        //console.log($('#pipart').position().top);
        /*
        $('html, body').animate({
            scrollTop: $('#pipart').position().top+'px'
        }, 'slow');
        */
        //$('#titlehistory').goTo();
        //record('selectfeature',['hx',id]);
	/*
	 * typeWriter("#pi","true",20);
	 * $('.presentillness').textillate();
	$('.patienthistory').textillate(
	{in: {effect:'fadeInRight', delay: delay, sync: true
	,callback: function() {

			}

		}}
		);
		*/
}

function gethistory2(element,answer,id,code){
    
	element.style.display='none';
	document.getElementById("pelist").style.display='none';
	//$("#pilist").hide(200);
	var ans = document.createElement("div"); 
	//ans.style.display = "inline";
	ans.className = "context";
        ans.id = "item"+id;
  	//ans.innerHTML = answer;

	$('.physicalexamination').append(ans);
  	//$('.presentillness').innerHTML = answer;
  	
  	changeText(answer,ans,30,"#titlephysicalexam","#pelist","#ddx2");
  	
        
        history1[id] = code;
        recordselectfeature('pe',id);
        
        //record('selectfeature',['pe',id]);

}

function gethistory3(element,answer,id,code){
    
	element.style.display='none';
	document.getElementById("lablist").style.display='none';
	//$("#pilist").hide(200);
	var ans = document.createElement("div"); 
	//ans.style.display = "inline";
	ans.className = "context";
        ans.id = "item"+id;
  	//ans.innerHTML = answer;

	$('.laboratory').append(ans);
  	//$('.presentillness').innerHTML = answer;
  	
  	changeText(answer,ans,30,"#titlelab","#lablist","#ddx3");
  	
        
        history1[id] = code;
        recordselectfeature('lab',id);
        
        //record('selectfeature',['lab',id]);

}
//Finds y value of given object

/*
(function($) {
    $.fn.goTo = function() {
        $('html, body').animate({
            scrollTop: $(this).offset().top + 'px'
        }, 'fast');
        return this; // for chaining...
    }
})(jQuery);
*/
(function($) {
    $.fn.goTo = function() {
        $('html, body').animate({
            scrollTop: $(this).offset().top + 'px'
        }, 'smooth');
        return this; // for chaining...
    }
})(jQuery);

function changeText(text,cont,speed,title,list,ddx){

        var max;
        var selected;
        if(ddx === '#ddx1')
        {
            max = maxhistory;
            selectedhistory++;
            selected = selectedhistory;
        }
        else if (ddx === '#ddx2')
        {
            max = maxpe;
            selectedpe++;
            selected = selectedpe;
        }
        else if (ddx === '#ddx3')
        {
            max = maxlab;
            selectedlab++;
            selected = selectedlab;
        }
        text = selected+". "+text;
	var Ocontent=text.split("");

	var i=0;
	function show(){
		if(i<Ocontent.length)
		{		
			//cont.append(Ocontent[i]);
			cont.innerHTML = cont.innerHTML + Ocontent[i];
			i=i+1;
		}
		else
		{
			clearInterval(Otimer);
			//document.getElementById("pilist").style.display='inline';
			//selected++;
			if (selected === max-1)
			{
				$(title).html("Please select patient information one at a time (1 remaining question)");
				//$(list).show(1000);
                                $(list).fadeIn(1000);
			}
			else if (selected < max)
			{
				$(title).html("Please select patient information one at a time ("+(max-selected)+" remaining question)");
				//$(list).show(1000);
                                $(list).fadeIn(1000);
			}
			else
			{
                                if (ddx === '#ddx1')
                                {
                                    $("#left0").children('.ddx').clone().appendTo("#left1");
                                    $("#right0").children('.ddx').clone().appendTo("#right1");
                                }
                                else if (ddx === '#ddx2')
                                {
                                    $("#left1").children('.ddx').clone().appendTo("#left2");
                                    $("#right1").children('.ddx').clone().appendTo("#right2");
                                }
                                else if (ddx === '#ddx3')
                                {
                                    $("#left2").children('.ddx').clone().appendTo("#left3");
                                    $("#right2").children('.ddx').clone().appendTo("#right3");
                                }
                                if(ddx ==='#ddx1')
                                {
                                    $(ddx).fadeIn(1000, 'swing', function() {
                                        // Animation complete.
                                        //showmanual("#left1","Reprioritise diagnoses","Drag and swap diagnoses to reorder a list of differential diagnosis.");
                                        //showmanual([$(ddx).offset().left,0,0],[$(ddx).offset().top,0,0],["Reorder the differential diagnosis.","",""]);
                                        showmanual([$(ddx).offset().left,0,0],[$(ddx).offset().top,0,0],["Modify and/or re-prioritise you differential diagnosis by dragging items in and out of your list and/or reordering them.","",""]);
                                    });
                                }
                                else
                                {
                                    $(ddx).fadeIn(1000);
                                }
				
			}
			//$("#pilist").fadeIn("slow");
		};
	};
		var Otimer=setInterval(show,speed);	
                
}
function recordselectfeature(step,featureid)
{
    
    $.ajax({
		type:'POST',
		data: {step: step, featureid: featureid},
                dataType: 'text',
		url:'Selectfeature',
		success: function(result){
                    //alert(result);
		},
		error:function(){
                    //alert("error feature");
	      }  
		
	});
}
function displayfeedback(predictions)
{
    alert("display feedback"+predictions);
}
function showPIstep(element)
{
    element.style.display = "none";
    $("#pipart").fadeIn(1000, 'swing', function() {
        showmanual([990,630,0],[850,820,0],["Click a topic to reveal/hide the available items in a category.","Select 10 appropriate items of patient information from within the five categories below.",""]);
        //showmanual([990,630,0],[850,820,0],["Click \"show\" to expand options.","Select patient information.",""]);
    });
}
function getfeedback0(){
        //alert(document.getElementById("selectedcase").innerHTML);
        document.getElementById("ddx0").style.display='none';
        //$("#pepart").show(1000);
        //$("#pelist").show(1000);
        //document.getElementById("pelist").style.display='inline';
        
        var c = document.getElementById("left0").childNodes;
        var dx = [];

        for (i = 0; i < c.length; i++) {
            dx[i] = c[i].innerHTML;
        }
        
        //getAttributerelevance(dx[0]);
        //getAttributerelevance2('#total0');

        $.ajax({
		type:'POST',
		data: {step: 'cc', historypart1: history1.toString(), feedbackid: 'total0', selectedddx: dx.toString()},
		url:'Feedback',
		success: function(result){
                    
                        $('#total0').html(result);
                        //$("#feedback0").show(1000);
                        
                        //addcloudfeedback('#cloud1cc','#cloud2cc',dx,'#drknowideacc');
                        
                        $("#feedback0").fadeIn(1000, 'swing', function() {
                            // Animation complete.
                            //showmanual("#total0","Feedback for selected diagnoses","Dr Know helps you to measure the percentage of likelihood on each diagnosis using selected information. You will use Dr Know's feedback to support your decision on what furtther information should be selected and what diagnosis should be in the list.");
                            //showmanual([880,480,940],[330,500,550],["\"+\" and \"-\" means patient information (on each row) are more or less predictive to the diagnoses.","Your differential diagnosis","Dr Know differential diagnosis (ranked by a number of positive predictive values on each diagnosis."]);
                            showmanual([880,480,940],[330,500,550],["A plus (+) in this table means that the feature is more predictive for this diagnosis. A minus (-) indicates that the feature is less predictive.","Your differential diagnosis. The relative size of the diagnosis graphic reflects the ranking you applied at the previous step.","Dr Know's diferential diagnosis. The relative size of these graphics represents the ranking of positive predictive values for the selected features of Dr Know's top three diagnoses."]);
                        });
                        
                    
                        
			var button = document.createElement("div"); 
                        //button.style.display = "block";
                        button.className = "myButton";
                        button.textContent = "Go to history taking step >>>";
                        button.onclick = function(){showPIstep(button);};
                        $("#container").append(button);
                        //getimagefeedback("#feedbackimage1",0.2,0.3,0.5);
                        //alert("get feedback");
                        //document.getElementById("pepart").style.display='inline';
                        //document.getElementById("pelist").style.display='inline';
                        //alert("result");
                        },
		error:function(){
	          alert('there is error while submit');
	      }  
		
	});
        }
function getfeedback1(){
        //alert(document.getElementById("selectedcase").innerHTML);
        document.getElementById("ddx1").style.display='none';
        //$("#pepart").show(1000);
        //$("#pelist").show(1000);
        //document.getElementById("pelist").style.display='inline';
        
        var c = document.getElementById("left1").childNodes;
        var dx = [];

        for (i = 0; i < c.length; i++) {
            dx[i] = c[i].innerHTML;
        }
        
        //getAttributerelevance(dx[0]);

        $.ajax({
		type:'POST',
		data: {step: 'hx', historypart1: history1.toString(), feedbackid: 'total1', selectedddx: dx.toString()},
		url:'Feedback',
		success: function(result){
                        $('#pi').hide(1000);
                        $('#total1').html(result);
                        
                        //addcloudfeedback('#cloud1hx','#cloud2hx',dx,'#drknowideahx');
                        
                        $("#feedback1").fadeIn(1000);
                        $("#feedbackimage1").fadeIn(1000);
                    
			var button = document.createElement("div"); 
                        //button.style.display = "block";
                        button.className = "myButton";
                        button.textContent = "Go to physical examination step >>>";
                        button.onclick = function(){showPEstep(button);};
                        $("#container").append(button);
                        //getimagefeedback("#feedbackimage1",0.2,0.3,0.5);
                        //alert("get feedback");
                        //document.getElementById("pepart").style.display='inline';
                        //document.getElementById("pelist").style.display='inline';
                        //alert("result");
                        //showmanual("#pi","Relevant information","Dr Know helps you to highlight which selected information is relevant to your first differential diagnosis. You will use Dr Know's feedback to support your decision on what furtther information should be selected and what diagnosis should be in the list.");

		},
		error:function(){
	          alert('there is error while submit');
	      }  
		
	});
        }
function showPEstep(element)
{
    element.style.display = "none";
    $("#pepart").fadeIn(1000);
    $("#pelist").fadeIn(1000);
}        
function getfeedback2(){
	
        document.getElementById("ddx2").style.display='none';
        //$("#pepart").show(1000);
        //$("#pelist").show(1000);
        //document.getElementById("pelist").style.display='inline';
        
        var c = document.getElementById("left2").childNodes;
        var dx = [];

        for (i = 0; i < c.length; i++) {
            dx[i] = c[i].innerHTML;
        }
        
        //getAttributerelevance(dx[0]);
        
        $.ajax({
		type:'POST',
		data: {step: 'pe', historypart1: history1.toString(), feedbackid: 'total2', selectedddx: dx.toString()},
		url:'Feedback',
		success: function(result){
                    
                        $('.physicalexamination').hide(1000);
			$('#total2').html(result);
                        
                        //addcloudfeedback('#cloud1pe','#cloud2pe',dx,'#drknowideape');
                        
                        $("#feedback2").fadeIn(1000);
                        $("#feedbackimage2").fadeIn(1000);
                        
                        var button = document.createElement("div"); 
                        button.className = "myButton";
                        button.textContent = "Go to lab and investigation step >>>";
                        button.onclick = function(){showLABstep(button);};
                        $("#container").append(button);
		},
		error:function(){
	          alert('there is error while submit');
	      }  
		
	});        
        }
function showLABstep(element)
{
    element.style.display = "none";
    $("#labpart").fadeIn(1000);
    $("#lablist").fadeIn(1000);
}
function getfeedback3(){

        document.getElementById("ddx3").style.display='none';
        //$("#pepart").show(1000);
        //$("#pelist").show(1000);
        //document.getElementById("pelist").style.display='inline';
        var c = document.getElementById("left3").childNodes;
        var dx = [];

        for (i = 0; i < c.length; i++) {
            dx[i] = c[i].innerHTML;
        }
        
        //getAttributerelevance(dx[0]);
        
        $.ajax({
		type:'POST',
		data: {step: 'lab', historypart1: history1.toString(), feedbackid: 'total2', selectedddx: dx.toString()},
		url:'Feedback',
		success: function(result){
                        $('.laboratory').hide(1000);
			$('#total3').html(result);
                        
                        //addcloudfeedback('#cloud1lab','#cloud2lab',dx,'#drknowidealab');
                        
                        $("#feedback3").fadeIn(1000);
                        $("#feedbackimage3").fadeIn(1000);
                        
                        var button = document.createElement("div"); 
                        button.className = "myButton";
                        button.textContent = "Go to final diagnosis step >>>";
                        button.onclick = function(){showfinaldxstep(button);};
                        $("#container").append(button);
                        
                        
		},
		error:function(){
	          alert('there is error while submit');
	      }  
		
	});        
        }
function showfinaldxstep(element)
{
    element.style.display = "none";
    $("#finaldx").fadeIn(1000, 'swing', function() {
        // Animation complete.
        //showmanual("#finaldx","Select a final diagnosis","Select one diagnosis that you think it is correct.");
    });
}
function showratefinaldiagnosis()
{
    //element.style.display = "none";
    $("#finaldx").hide(0);
    $("#surveyfinaldx").fadeIn(1000);
}
function showbuttonafterrating()
{
    document.getElementById("viewfeedback5").style.display='block';
}
function getfeedback4(){
    //document.getElementById("viewfeedback5").style.display='none';
    document.getElementById("dxsurveypart").style.display='none';
    document.getElementById("loadingfinalfeedback").style.display='inline';
    //document.getElementById("finalfeedback").innerHTML = "A correct diagnosis is Appendicitis. Your diagnosis is "+finaldiagnosis+".";
    $.ajax({
		type:'POST',
		data: {step: 'final', historypart1: history1.toString(), feedbackid: 'finalfeedback', selectedddx: finaldiagnosis, confident: $("input[name=confident]:checked").val()},
		url:'Feedback',
		success: function(result){
                    document.getElementById("loadingfinalfeedback").style.display='none';
                    document.getElementById("finalfeedback").style.display='inline';
                    $('#finalfeedback').html(result);
                    addfinalcloudfeedback();
                    //showmanual("#finalfeedbacktitle","Final feedback","In the final feedback, Dr Know provides you the correct dianosis, relevant clinical information for this case, and other relevant information that you miss to select on this case. Dr Know also provide you a score of your performance. If you get a high score, it means you select relevant information to support the correct diagnosis.");

		},
		error:function(){
	          alert('there is error while submit');
	      }  
		
	});   
}
function selectfinaldiagnosis(element)
{
    //alert(element.innerHTML);
    document.getElementById("viewfeedback4").style.display='inline';
    var c = document.getElementById("finaldiagnosis").getElementsByTagName('div');
    finaldiagnosis = element.innerHTML;
    $("#selectfinaldiagnosis").html('Provisional diagnosis: '+finaldiagnosis);
    //document.getElementById("selectfinaldiagnosis").style.background = ddxcolor[num];

    for(i=0;i<c.length;i++)
    {
        //alert(document.getElementById("finaldiagnosis").childNodes[0].text);
        if(c[i].innerHTML === element.innerHTML)
        {
            c[i].style.background = ddxcolor[i];
            c[i].style.color = "white";
        }
        else
        {
            c[i].style.background = "inherit";
            c[i].style.color = "black";
        }
    }

}
function getAttributerelevance2(feedbackid)
{
    $.ajax({
		type:'POST',
		data: {historypart1: history1.toString(), casenumber: dxnum},
		url:'Attributerelevance',
		success: function(result){
                        $(feedbackid).append(result);
		},
		error:function(){
	          //$("#viewfeedback").html('there is error while submit');
	      }  
		
	});
}
function getAttributerelevance(firstdx)
{
    var dxnum = 0;
    if(firstdx==='Appendicitis')
    {
        dxnum = 0;
    }
    else if(firstdx==='Gastroenteritis')
    {
        dxnum = 1;
    }
    else if(firstdx==='Urinary tract infection')
    {
        dxnum = 2;
    }
    else if(firstdx==='Ectopic pregnancy')
    {
        dxnum = 3;
    }
    else if(firstdx==='Pelvic inflammatory disease')
    {
        dxnum = 4;
    }
    $.ajax({
		type:'POST',
		data: {historypart1: history1.toString(), casenumber: dxnum},
		url:'Attributerelevance',
		success: function(result){
                        //alert(result.split(",")[4]);
                        //alert(result);

                        var prob = result.split(",");
                        
                        for(i=0;i<prob.length;i++)
                        {
                            if(document.getElementById("item"+i))//if exists
                            {
                                if(prob[i]==='3' || prob[i]==='2')
                                //if(prob[i]>0.2)
                                {
                                    var color = "000000";
                                    if(firstdx==='Appendicitis')
                                    {
                                        color = "DE7A22";
                                    }
                                    else if(firstdx==='Gastroenteritis')
                                    {
                                        color = "6AB187";
                                    }
                                    else if(firstdx==='Urinary tract infection')
                                    {
                                        color = "F52549";
                                    }
                                    else if(firstdx==='Ectopic pregnancy')
                                    {
                                        color = "FFD64D";
                                    }
                                    else if(firstdx==='Pelvic inflammatory disease')
                                    {
                                        color = "283655";
                                    }
                                    //document.getElementById("item"+i).style.background = "#"+color;
                                    document.getElementById("item"+i).style.color = "#"+color;
                                }
                                /*
                                else if(prob[i]==='2')
                                //else if(prob[i]>0.05)
                                {
                                    var color = "000000";
                                    if(firstdx==='Appendicitis')
                                    {
                                        color = "F2C9A6";
                                    }
                                    else if(firstdx==='Gastroenteritis')
                                    {
                                        color = "6AB187";
                                    }
                                    else if(firstdx==='Urinary track infection')
                                    {
                                        color = "fa9ead";
                                    }
                                    else if(firstdx==='Ectopic pregnancy')
                                    {
                                        color = "ffe799";
                                    }
                                    else if(firstdx==='Pelvic inflammatory disease')
                                    {
                                        color = "bac5de";
                                    }
                                    //document.getElementById("item"+i).style.background = "#"+color;
                                    document.getElementById("item"+i).style.color = "#"+color;
                                }
                                else if(prob[i]==='1')
                                //else if(prob[i]>0)
                                {
                                    //document.getElementById("item"+i).style.background = "#d2d2c6";
                                    document.getElementById("item"+i).style.color = "#c7c7b8";
                                }*/
                                else
                                {
                                    //document.getElementById("item"+i).style.color = "#ddddd4";
                                    document.getElementById("item"+i).style.color = "#d2d2c6";
                                }
                            }
                        }
		},
		error:function(){
	          //$("#viewfeedback").html('there is error while submit');
	      }  
		
	});
}

function mouseoverfinaldiagnosis(element)
{
    element.className = "finaldxover";
}
function mouseoutfinaldiagnosis(element)
{
    element.className = "finaldx";
}


/*case list*/
function getPerformancehistory(id){
    sessionStorage.setItem("finishedcaseid", id); 
    window.open('finishedcase.jsp','_blank');
/*
                $.ajax({      
                    type:'POST',
                    url: 'Finishedcase',                      
                    data: {caseid: id},
                    dataType: 'text',     
                    beforeSend: function() {
                        //$('#finishedcasecontent').append("loading..");
                        },
                    success: function(result){
                        sessionStorage.setItem("htmlcontent", result); 
                        window.open('finishedcase.jsp','_blank');
                        },
                    error:function(e){
                        alert("The record is not ready yet. Please click the same link again to view the summary.");
                    } 
                 });
                 */
};

function getMyrecord(){

                $.ajax({      
                    type:'POST',
                    url: 'Myrecord',                      
                    data: {},
                    dataType: 'text',     
                    beforeSend: function() {
                        //$('#finishedcasecontent').append("loading..");
                        },
                    success: function(result){
                            sessionStorage.setItem("myrecord", result); 
                            window.open('myrecord.jsp','_blank');
                        },
                    error:function(){''
                        alert("The record is not ready yet. Please click the same link again to view the summary.");
                    } 
                 });
};
function checkddx()
{
    if(document.getElementById("left0").offsetHeight > 0)
    {
            if(document.getElementById("left0").childNodes.length===3)
		  {
			  $("#viewfeedback0").show(100);
		  }
		  else if(document.getElementById("left0").childNodes.length>3)
		  {
			  document.getElementById('right0').appendChild(document.getElementById('left0').childNodes[3]);
			  $("#viewfeedback0").show(100);
		  }
		  else
		  {
			  $("#viewfeedback0").hide(100);
		  }
    }
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
}
//document.addEventListener("DOMNodeInserted", function (event) {
   //alert($(this).attr('id'));
//}, false);

$(document).bind('DOMNodeInserted', function(e) {
    //if(document.getElementById("left1").offsetHeight > 0)
    if(document.getElementById("left0")!==null)
    {
    if(document.getElementById("left0").childNodes.length===3)
		  {
			  $("#viewfeedback0").show(100);
		  }
		  else if(document.getElementById("left0").childNodes.length>3)
		  {
			  document.getElementById('right0').appendChild(document.getElementById('left0').childNodes[3]);
			  $("#viewfeedback0").show(100);
		  }
		  else
		  {
			  $("#viewfeedback0").hide(100);
		  }
    }
    if(document.getElementById("left1")!==null)
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
    //if(document.getElementById("left2").offsetHeight > 0)
    if(document.getElementById("left2")!==null)
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
    
    //if(document.getElementById("left3").offsetHeight > 0)
    if(document.getElementById("left3")!==null)
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
function showmanual(x,y,description)
{
    if(sessionStorage.getItem("caseid") <= 1)
    {
    for(var i=0;i<description.length;i++)
    {
        if(description[i]==='')
        {
            $("#manual"+(i+1)).hide();
        }
        else
        {
            $("#description"+(i+1)).html(description[i]);
            $("#manual"+(i+1)).fadeIn(600);
            $("#manual"+(i+1)).css("top",y[i]+"px").css("left",x[i]+"px");
        }
    }
    }
}
/*
function showmanual(element,title,description)
{
    var ddd = sessionStorage.getItem("caseid");
    if(sessionStorage.getItem("caseid") === '0'  && $(element).position()!==undefined)
    {
        $("#manualtitle").text(title);
        $("#manualdescription").text(description);
        $("#manual").fadeIn(600);
        $("#manual").css('top',$(element).position().top+$(element).height()+10);
    }
}
function hidemanual()
{
    $("#manual").fadeOut(600);
    //$("#manualdescription").text('');
}*/
function togglelist(id,group)
{
    var elem = document.getElementById('togglelist'+group);
    if(elem.innerHTML==='hide')
    {
        $("#"+id).find(".list."+group).hide(400);
        elem.innerHTML='show';
    }
    else
    {
        $("#"+id).find(".list."+group).show(400);
        elem.innerHTML='hide';
    }
}

function addcloudfeedback(youridea,drknowidea,selectedddx,predictedddxid)
{
    var words1 = JSON.parse(JSON.stringify(wordstemplate));
    for(var i=0;i<selectedddx.length;i++)
    {
        words1[i].text = selectedddx[i];
    }
    var words2 = JSON.parse(JSON.stringify(wordstemplate));
    $(predictedddxid).children().each(function( i ) {
        words2[i].text = $( this ).text();
      });

    $(youridea).jQCloud(words1,{classPattern: null, removeOverflowing:false,colors: ["#1b5a7a", "#362391","#362391","#362391", "#5853bc", "#7098da","#7098da", "#6eb6ff", "#90f2ff", "#e0fcff"],fontSize: {from: 0.1,to: 0.02}});
    $(drknowidea).jQCloud(words2, {classPattern: null, removeOverflowing:false, colors: ["#2B3856", "#151B54", "#000080", "#342D7E", "#0000A0", "#0020C2","#2554C7", "#3090C7", "#95B9C7", "#82CAFF"],fontSize: {from: 0.1,to: 0.02}});

}
function addfinalcloudfeedback()
{
    var words = JSON.parse(JSON.stringify(finalwordstemplate));
    $('#relevantfeature').children().each(function( i ) {
            words[i].text = $( this ).text();
      });

    //$('#relevantfeaturechart').jQCloud(words,{classPattern: null, removeOverflowing:false,colors: ["#1b5a7a", "#362391","#362391","#362391", "#5853bc", "#7098da","#7098da", "#6eb6ff", "#90f2ff", "#e0fcff"],fontSize: {from: 0.05,to: 0.01}});
    $('#relevantfeaturechart').jQCloud(words,{classPattern: null, removeOverflowing:false,colors: ["#2B3856", "#151B54", "#000080", "#342D7E", "#0000A0", "#0020C2","#2554C7", "#3090C7", "#95B9C7", "#82CAFF"],fontSize: {from: 0.05,to: 0.015}});
}