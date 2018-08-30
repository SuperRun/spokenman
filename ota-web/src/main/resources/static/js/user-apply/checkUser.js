new Vue({
	el:"#app",
	data:{
		userId:"",
		userApplyId:"",
		userInfo:{
			id: 0,
		    name: "",
		    password: "",
		    phone: "",
		    email: "",
			areaName:"",
			orgName:"",
			levelName:"",
			typeName:"",
			idCard: "",
		    picFront: "",
		    picBack: "",
		    picOrg: ""
		}
	},
	created:function(){
		var self=this;
		var split = location.pathname.split('/');
		self.userApplyId= window.location.search.split('=').pop();
        if (split.length > 2) {
            self.userId = split.pop();
            console.log("userId="+self.userId);
            
        }
	},
	mounted:function(){
		var self=this;
		self.fetch();
	},
	methods:{
		fetch:function(){
			var self=this;
			console.log(self.userId);
			$.get('/user/'+self.userId,function(res){
				console.log(res);
				self.userInfo=res.data;
			});
		},
		agree:function(){
			var self=this;
			layui.use('layer',function(){
				layer.confirm('确定审核通过吗？', {
					  btn: ['确定','取消'] //按钮
					}, function(){
						$.ajax({
			                type : "put",
			                url : "/user-apply/"+self.userApplyId+"/status?status="+2,
			                success : function(res) {
			                	console.log(res);
			                }
			        	});
						layer.close(layer.index);  
						location.replace(document.referrer);
						//window.location.href="/platform/perUser";
					}, function(){
						layer.close(layer.index);  
				});
			});
			
		},
		disagree:function(){
			var self=this;
			layui.use('layer',function(){
				layer.confirm('确定审核不通过吗？', {
					  btn: ['确定','取消'] //按钮
					}, function(){
						$.ajax({
			                type : "put",
			                url : "/user-apply/"+self.userApplyId+"/status?status="+0,
			                success : function(res) {
			                	console.log(res);
			                }
			        	});
						layer.close(layer.index);
						location.replace(document.referrer);
			           // window.location.href="/platform/perUser";
					}, function(){
						layer.close(layer.index);  
				});
			});
			
		}
	}
});
