layui.use('form',async function(){
	var $=layui.$;
	var form=layui.form;
    
    //从后台获取条线
    await $.get('/data-dictionary/org-types',function(res){
    	var names=res.data;
    	console.log(names);
    	for(var i=0;i<names.length;i++){
    		$(".getType").each(function(){
    			
     			$(this).append("<option value='"+names[i].id+"'>"+names[i].remark+"</option>");
     		});
    	}
    	
		form.render();
		
    });

});
