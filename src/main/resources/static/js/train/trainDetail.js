var vw=new Vue({
	el:"#app",
	data:{
		trainId:"",
		userId:"",
		train:{
			description:"",
			lecturerName:"",
			name:"",
			score:"",
			id:"",
			typeIdName:""
		},
		userTrain:{
			process:0,
			learnedHour:0,
			learnedMinute:0,
			learnedSecond:0
		},
		trainResource:[],
		userRes:{
		    id:-1
		},
		isJoin:true
	},
	created:function(){
		var self=this;
        var split = location.pathname.split('/');
        if (split.length > 2) {
            self.trainId = split.pop();
            console.log(self.trainId);
        }
        self.userId=$("#userId").val();
	},
	mounted:function(){
		var self=this;
		self.getTraining();
	},
	methods:{
		getTraining:function(){
			var self=this;
			$.get('/training/'+self.trainId,function(res){
				console.log("getTrain");
				console.log(res);
				self.train=res.data;
			});
			self.getTrainRes();
			self.getUserTrain();
			self.isJoinTrain();
		},
		getTrainRes:function(){
			var self=this;
			$.get('/training/resource/list/'+self.trainId,function(res){
				self.trainResource=res.data;
				console.log("getTrainRes");
				console.log(res.data);
				console.log(self.trainResource);
			});
			
		},
		learnRes:function(id){//学习培训里某个资源
			var self=this;
			$.post('/user-resource?resourceId='+id,function(res){
				console.log(res);
				$.get('/user-resource/'+id+'/learn',function(res){
					self.userRes.id=res.data.id;
					window.location.href="/video/"+id+"?id="+self.userRes.id;
				});
				
			});
		},
		getUserTrain:function(){
			var self=this;
			$.get('/training-user/detail/'+self.userId+'/'+self.trainId+'/',function(res){
				console.log(res);
				if(res.data!=null){
					self.userTrain=res.data;
				}
				layui.use('element',function(){
					var element=layui.element;
					
					var progress=self.userTrain.process+'%';
					console.log(progress);
					element.progress('trainingProcess', progress);
					//element.init();
				});
				console.log("getUserResource");
				
			});
		},
		addUserTrain:function(){
			var self=this;
			$.ajax({
				type:"post",
				url:"/training-user",
				data:{
					trainingId:self.trainId,
					userId:self.userId
				},
				success:function(res){
					console.log(res);
					window.location.href="/perUser/"+self.userId+"/listUserTrain";
				}
				
			})
		},
		isJoinTrain:function(){
			var self=this;
			$.get('/training-user/sign-up/'+self.userId+'/'+self.trainId,function(res){
				console.log(res);
				self.isJoin=res.data;
			});
		},
		formatDateTime:function(inputTime) {    
		    var date = new Date(inputTime);  
		    var y = date.getFullYear();    
		    var m = date.getMonth() + 1;    
		    m = m < 10 ? ('0' + m) : m;    
		    var d = date.getDate();    
		    d = d < 10 ? ('0' + d) : d;    
		    var h = date.getHours();  
		    h = h < 10 ? ('0' + h) : h;  
		    var minute = date.getMinutes();  
		    var second = date.getSeconds();  
		    minute = minute < 10 ? ('0' + minute) : minute;    
		    second = second < 10 ? ('0' + second) : second;   
		    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
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