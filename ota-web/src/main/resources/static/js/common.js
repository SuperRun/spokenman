layui.use(['element', 'form'],function(){
	var element=layui.element,$=layui.$;

	/*用户个人设置板块*/
/*	function menuindexhover(){
		
	    $('#usermsg').hover(function(){
	    	$('#arrows').addClass('layui-anim layui-anim-rotate');
	        $('#setting').slideDown(5000);
	        
	    },function(){
	    	$('#arrows').removeClass('layui-anim layui-anim-rotate');
			$('#setting').hide(2000);
	    })
	}		
	menuindexhover();*/	
	
	$("#usermsg").on({
        "mouseenter": function(e) {
        	var timer = $(this).data("timer");
            clearTimeout(timer);
        	$('#arrows').addClass('layui-anim layui-anim-rotate');
	        $('#setting').slideDown(500);
	        e.stopPropagation();
            
        },
        "mouseleave": function(e) {
            var timer = $(this).data("timer");
           // clearTimeout(timer);
            timer = setTimeout(function() {
            	$('#arrows').removeClass('layui-anim layui-anim-rotate');
    			$('#setting').hide(200);
            }, 500);
            //clearTimeout(timer);
            $(this).data("timer", timer);
        }
    });

	/*导航栏 点击改变样式*/
	$('.layui-nav li a').click(function(){
		$('.layui-this').removeClass('layui-this');
		$('this').addClass('layui-this');
	});
	

});