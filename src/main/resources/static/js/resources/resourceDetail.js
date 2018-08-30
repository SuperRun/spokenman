var vw=new Vue({
	el:"#app",
	data:{
		resId:"",
		res:{
			description:"",
			lecturerName:"",
			name:"",
			score:"",
			id:"",
			typeIdName:"",
			process:"",
			learnedHour:"",
			learnedMinute:"",
			learnedSecond:""
		},
		userRes:{
		    id: -1
		},
		learningProgress:""
	},
	created:function(){
		var self=this;
        var split = location.pathname.split('/');
        if (split.length > 2) {
            self.resId = split.pop();
            console.log(self.resId);
        }
	},
	mounted:function(){
		var self=this;
		self.getResource();
	},
	methods:{
		getResource:function(){
			var self=this;
			$.get('/learning-resource/'+self.resId,function(res){
				console.log("getResource");
				console.log(res);
				self.res=res.data;
				
			});
			self.getUserResource();
			
		},
		learnRes:function(){
			var self=this;
			$.post('/user-resource?resourceId='+self.resId,function(res){
				console.log(res);
				$.get('/user-resource/'+self.resId+'/learn',function(res){
					//self.userRes.id=res.data.id;
					window.location.href="/video/"+self.resId+"?id="+res.data.id;
				});
				
			});
		},
		getUserResource:function(){
			var self=this;
			$.get('/user-resource/'+self.resId+'/learn',function(res){
				if(res.data!=null){
					self.userRes=res.data;
				}else{
					self.userRes=res.data;
					self.userRes.id=-1;
				}
				layui.use('element',function(){
					var element=layui.element;
					var progress=self.userRes.process+'%';
					console.log(progress);
					element.progress('learningProgress', progress);
					//element.init();
				});
				console.log("getUserResource");
				console.log(res);
			});
		}
	}
});
layui.use('element', function(){
    var element = layui.element;
});
			
$(".courses-star-box i").click(function () {
    if($(".courses-star-box i").html()=="&#xe600;"){
        $(".courses-star-box i").html("&#xe658;");
    }else{
        $(".courses-star-box i").html("&#xe600;");
    }
});