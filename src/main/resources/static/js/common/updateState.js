/**
 * 
 * 修改单位用户状态
 * editUser
 * updateUserStatus
 * updateUserApply
 * 
 */



function updateUserStatus(userId,status){//更新用户状态
	if(status==-1){
		//配置一个透明的询问框
		layui.use('layer',function(){
    		layer.confirm('确定要删除该用户吗？', {
    			  btn: ['确定','取消'] //按钮
    			}, function(){
    				$.ajax({
                        type : "put",
                        url : "/user/"+userId+"/status?status="+status,
                        success : function(res) {
                        	console.log(res);
                        }
                	});
    				layer.close(layer.index);  
    	            window.parent.location.reload();
    			}, function(){
    				layer.close(layer.index);  
    		});
		});
	}else if(status==-2){
		layui.use('layer',function(){
    		layer.confirm('确定要冻结该账户吗？', {
    			  btn: ['确定','取消'] //按钮
    			}, function(){
    				$.ajax({
                        type : "put",
                        url : "/user/"+userId+"/status?status="+status,
                        success : function(res) {
                        	console.log(res);
                        }
                	});
    				layer.close(layer.index);  
    	            window.parent.location.reload();
    			}, function(){
    				layer.close(layer.index);  
    		});
		});
	}else if(status==1){
		layui.use('layer',function(){
    		layer.confirm('确定恢复该账号吗？', {
    			  btn: ['确定','取消'] //按钮
    			}, function(){
    				$.ajax({
                        type : "put",
                        url : "/user/"+userId+"/status?status="+status,
                        success : function(res) {
                        	console.log(res);
                        }
                	});
    				layer.close(layer.index);  
    	            window.parent.location.reload();
    			}, function(){
    				layer.close(layer.index);  
    		});
		});
	}
	
}
function updateUserApply(userApplyId){
	var status=2;
	console.log("userApplyId"+userApplyId);
	layui.use('layer',function(){
		layer.confirm('接受该用户的申请吗？', {
			  btn: ['通过','拒绝'] //按钮
			}, function(){
				$.ajax({
                    type : "put",
                    url : "/user-apply/"+userApplyId+"/status?status="+status,
                    success : function(res) {
                    	console.log(res);
                    }
            	});
				layer.close(layer.index);  
	            window.parent.location.reload();
			}, function(){
				status=0;
				$.ajax({
                    type : "put",
                    url : "/user-apply/"+userApplyId+"/status?status="+status,
                    success : function(res) {
                    	console.log(res);
                    }
            	});
				layer.close(layer.index);  
	            window.parent.location.reload();  
		});
	});
	
}