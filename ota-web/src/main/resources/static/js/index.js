new Vue({
	el:"#app"
	
});
layui.use(['element', 'form','layer'],function(){
	var $=layui.$;
	var layer=layui.layer;
	$("#loginBtn").click(function(){
		layer.open({
		  type:2
		  ,area:['400px','320px']
		  ,title: ''
		  ,content: '/users/login'

		})
	});

	$("#registBtn").click(function(){
		layer.open({
		  type:2
		  ,area:['960px','600px']
		  ,title: ''
		  ,content: '/users/regist'
		})
	});

});

layui.use('element',function(){
	var $=layui.$;
    
	//菜单条事件
	$('#menu li').each(function(index){
		
		if(index==0){
			$(this).addClass("layui-this");
			$(this).find("dd").each(function(i){
				if(i==0){
					$(this).addClass("menu-this");
					$(this).find('a').css('color','#fff');
				}else{
					$(this).removeClass("menu-this");
					$(this).find('a').css('color','#000');
				}
			});
			
		}else{
			$(this).removeClass("layui-this");
		}
	});
});