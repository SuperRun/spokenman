new Vue({
	el:"#userInfo",
	data:{
		userId:"",
		user:{
			
		}
	},
	mounted:function(){
		var self=this;
		self.fetch();
	},
	methods:{
		fetch:function(){
			var self=this;
			self.userId=$("#userId").val();
			$.get('/user/'+self.userId,function(res){
				console.log(res);
				self.user=res.data;
			});
		}
	}
	
});
