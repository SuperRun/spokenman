new Vue({
	el:"#app",
	data:{
		userId:"",
		trainings:[],
		training:{
			requiredRes:[],
			optionalRes:[]
		},
		trainPass:[],
		trainFail:[]
	},
	created:function(){
    	var self=this;
        var split = location.pathname.split('/');
        if (split.length>2) {
            self.userId = split[2];
            console.log("用户ID="+self.userId);
           
        }
        
    },
	mounted:function(){
		var self=this;
		self.getTraining();
		self.getTrainPassed();
		self.getTrainFailed();
		//self.fetch();
	},
	methods:{
		fetch:function(){
			console.log("sada");
			layui.use(['element', 'layer'], function(){
			  var element = layui.element;
			  var layer = layui.layer;
			  element.init();
			  
			});
			for (var i = 0; i < $('.types-content').length; i++) {
	            var a = $('.types-content').eq(i).children().length;
	            var c = $('.types-content').eq(i).children().height();
	            var f = $('.types-content').eq(i).children().length / 5;
	            var g = $('.types-content').eq(i).children().length % 5;
	            if ((g != 0 )&&(a>5)) {
	                $(".types-content").eq(i).css("min-height", c + 180 * (f));
	                $(" .container-types").eq(i/2).css("min-height", 650 + 152 * (f));
	            };
	        }
		},
		getTraining:function(){
			var self=this;
			$.get('/training-user/user/'+self.userId,function(res){
				console.log("用户参与培训");
				console.log(res);
			
				for(var i=0;i<res.data.length;i++){
					var requiredRes=[];
					var optionalRes=[];
					var trainRes=res.data[i].trainingResources;
					self.training=res.data[i].training;
					self.training.signStartTime=self.formatDateTime(self.training.signStartTime);
					self.training.signEndTime=self.formatDateTime(self.training.signStartTime);
					self.training.startTime=self.formatDateTime(self.training.startTime);
					self.training.endTime=self.formatDateTime(self.training.endTime);
					console.log("培训课程1");
					console.log(trainRes);
					for(var j=0;j<trainRes.length;j++){
						if(trainRes[j].required==0){
							optionalRes.push(trainRes[j].learningResource);
						}else if(trainRes[j].required==1){
							requiredRes.push(trainRes[j].learningResource);
						}
						
					}
					self.training.requiredRes=requiredRes;
					self.training.optionalRes=optionalRes;
					self.trainings.push(self.training);
					console.log("所有培训");
					console.log(self.trainings);
				}
				//数据渲染完再重新渲染element
				self.$nextTick(function(){
					self.fetch();
					console.log("用户培训记录");
					console.log(self.training);
				});
				
				
			});
		},
		getTrainPassed:function(){
			var self=this;
			$.get('/training-user/user/'+self.userId+'?status=1',function(res){
				console.log(res);
				for(var i=0;i<res.data.length;i++){
					var requiredRes=[];
					var unRequiredRes=[];
					var trainRes=res.data[i].trainingResources;
					self.training=res.data[i].training;
					self.training.signStartTime=self.formatDateTime(self.training.signStartTime);
					self.training.signEndTime=self.formatDateTime(self.training.signStartTime);
					self.training.startTime=self.formatDateTime(self.training.startTime);
					self.training.endTime=self.formatDateTime(self.training.endTime);
					console.log("培训课程1");
					console.log(trainRes);
					for(var j=0;j<trainRes.length;j++){
						if(trainRes[j].required==0){
							unRequiredRes.push(trainRes[j].learningResource);
						}else if(trainRes[j].required==1){
							requiredRes.push(trainRes[j].learningResource);
						}
						
					}
					self.training.requiredRes=requiredRes;
					self.training.optionalRes=unRequiredRes;
					self.trainPass.push(self.training);
					console.log("所有培训");
					console.log(self.trainPass);
				}
				//数据渲染完再重新渲染element
				self.$nextTick(function(){
					self.fetch();
					console.log("用户培训记录");
					console.log(self.trainPass);
				});
			});			
		},
		getTrainFailed:function(){
			var self=this;
			$.get('/training-user/user/'+self.userId+'?status=2',function(res){
				console.log(res);
				for(var i=0;i<res.data.length;i++){
					var requiredRes=[];
					var unRequiredRes=[];
					var trainRes=res.data[i].trainingResources;
					self.training=res.data[i].training;
					self.training.signStartTime=self.formatDateTime(self.training.signStartTime);
					self.training.signEndTime=self.formatDateTime(self.training.signStartTime);
					self.training.startTime=self.formatDateTime(self.training.startTime);
					self.training.endTime=self.formatDateTime(self.training.endTime);
					console.log("培训课程1");
					console.log(trainRes);
					for(var j=0;j<trainRes.length;j++){
						if(trainRes[j].required==0){
							unRequiredRes.push(trainRes[j].learningResource);
						}else if(trainRes[j].required==1){
							requiredRes.push(trainRes[j].learningResource);
						}
						
					}
					self.training.requiredRes=requiredRes;
					self.training.optionalRes=unRequiredRes;
					self.trainFail.push(self.training);
					console.log("所有培训");
					console.log(self.trainPass);
				}
				//数据渲染完再重新渲染element
				self.$nextTick(function(){
					self.fetch();
					console.log("用户培训记录");
					console.log(self.trainFail);
				});
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
