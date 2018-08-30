new Vue({
	el:"#app"
	,data:{
		name:""
	    ,password:""
	}
	,mounted:function () {
       
    }
	,methods:{
		login:function(){
			var self=this;
			console.log(self.name);
			$.ajax({  
                url : '/user/login',  
                type : 'post',  
                data : {  
                    name : self.name,  
                    password : self.password  
                },  
                success : function(res) {  
                	console.log(res);
                	if(res.code==1){
                		layui.use('layer',function(){
	                		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	                		parent.layer.close(index);//关闭弹出的子页面窗口
	                		//console.log("type1"+userType);
	                		//平台管理员
	                		parent.window.location.href="/userType";
	                	});
                	}else{
                		layui.use('layer',function(){
                			layer.msg(res.msg, {
            				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
            				}); 
                    	});
                	}
	                	
                }
			});
		}
	}
});