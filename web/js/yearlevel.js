function selectyearlevel()
{
    //yearlevel = $("input[name=yearlevel]:checked").val();
    //alert(yearlevel);
    $("#submityearlevel").show();
}
function submityearlevel()
{
    $.ajax({
		type:'POST',
		data: {action: 'yearlevel', yearlevel: $("input[name=yearlevel]:checked").val()},
                dataType: 'text',
		url:'Database',
		success: function(result){
                    //alert(result);
                    document.location.href = 'Caselist';
		},
		error:function(){
                    alert("error db");
	      }  
		
	});
}









//Survey
var surveyresult = ['-','-','-','-','-','-','-','-','-'];
function selectsurvey(num)
{
    //yearlevel = $("input[name=yearlevel]:checked").val();
    //alert(yearlevel);
    //$("#submityearlevel").show();
    
    var full = true;
    for(i=0;i<num;i++)
    {
        surveyresult[i] = $("input[name=survey"+i+"]:checked").val();
        if(surveyresult[i]=== undefined)
        {
            full = false;
        }
    }

    if(full)
    {
        $("#submitsurvey").show();
    }
    
    //alert(surveyresult[8]);
}
function redirectsurvey(elem)
{
    elem.style.display = 'none';
    document.getElementById('load').style.visibility="visible";
    $.ajax({
		type:'POST',
		data: {totalscore: document.getElementById('totalscore').innerHTML,htmlcontent: document.getElementById('wrapper').innerHTML},
                dataType: 'text',
		url:'Recordcase',
		success: function(result){
                    document.getElementById('load').style.visibility="hidden";
                    document.location.href = 'Survey';
		},
		error:function(){
                    alert("Recording error. Try to click \"Next\" again.");
                    document.getElementById('load').style.visibility="hidden";
	      }  
		
	});
}
function submitsurvey()
{
    //alert(surveyresult);
    //surveyresult[8] = '-';
    if($.trim($('#surveytextarea').val()).length>0)
    {
        surveyresult[8] = $('#surveytextarea').val();
    }
    
    $.ajax({
		type:'POST',
		data: {action: 'survey', data: surveyresult.toString()},
                dataType: 'text',
		url:'Database',
		success: function(result){
                    document.location.href = 'Caselist';
                    //alert('success');
                    //alert(result);
                    //document.location.href = 'Caselist';
		},
		error:function(){
                    alert("error db");
	      }  
		
	});
}

