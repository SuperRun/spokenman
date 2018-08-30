new Vue({
	el:"#app",
	data:{
		userId:"",
		train:[{
			trainingResource:[]
		}]
	},
	mounted:function(){
		var self=this;
		self.getTrain();
	},
	methods:function(){
		var self=this;
		getTrain:function(){
			$.get('/training-user/user/'+self.userId+'?status=0',function(res){
				
			});
		}
	}
})