$(document).ready(function(){
	 var second=parseInt($("#tipContent span").html());
	 var timer=setInterval(redirect, 1000);  
	    
	 function redirect() {
	  
	  if (second <=0) {
	   	console.log("second="+second);
	   	clearInterval(timer);
	   	location.href = '/perUser/authenticate';
	  } else {
	   
	    $("#tipContent span").html(second-1);
	    console.log("second1="+second);
	    second=parseInt($("#tipContent span").html());
	    console.log("second2="+second);
	  }
	 
	 }

});