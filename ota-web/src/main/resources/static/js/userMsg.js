layui.use(['element', 'form'],function(){
	var element=layui.element,$=layui.$;

	/*用户个人设置板块*/
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
           // clearTimeout(timer);
            $(this).data("timer", timer);
        }
    });	
	
	$("#keyWord").click(function(){
		if($(this).val()==="请输入关键词"){
			$(this).val('');
		}
	});
	$("#keyWord").blur(function(){
		if($(this).val()===""){
			$(this).val("请输入关键词");
		}
	});
});