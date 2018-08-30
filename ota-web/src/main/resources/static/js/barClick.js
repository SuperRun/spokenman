layui.use('form',function(){
	var $=layui.$;
	$('#course').on('click',function(e){
		$("html,body").animate({ scrollTop: 620}, 500); 
    });
	$('#train').on('click',function(e){
		$("html,body").animate({ scrollTop: 1250}, 500); 
    });
});