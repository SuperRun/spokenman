function logout(){
	$.ajax({  
        url : '/user/logout',  
        type : 'post',  
        success : function(res) { 
        	window.location.href="/index"
        }
	});
}