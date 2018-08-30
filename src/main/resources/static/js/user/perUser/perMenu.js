
/*用户个人设置板块*/
function menuindexhover(){
	
    $('#usermsg').hover(function(){
    	$('#arrows').addClass('layui-anim layui-anim-rotate');
        $('#setting').slideDown(1000);
        
    },function(){
    	$('#arrows').removeClass('layui-anim layui-anim-rotate');
		$('#setting').hide();
    })
}		
menuindexhover();			
