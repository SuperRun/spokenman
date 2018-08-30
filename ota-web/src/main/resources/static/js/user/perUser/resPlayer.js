var vw=new Vue({
	el:"#app",
	data:{
		resId:"",//资源ID
		userResId:"",//用户资源ID
		status:1,
		score:0,
		pauseTime:[],//问题暂停时间点
		questions:[],
		option:"A",
		temp:0,
		userResource:{
			time:""
		},
		resource:{//资源详细内容
			name:"",
			url:"",
			score:"",
			questions:[
				{
					id:"",
					answer: "",
					content: "",
					pic1:"",
					pic2:"",
					pic3:"",
					time: "",
					items:[]
				}
			]
		},
		question:{
			id:"",
			answer: "",
			content: "",
			pic1:"",
			pic2:"",
			pic3:"",
			time: "",
			items:[]

		}
	},
	created:function(){
		var self=this;
        var str = window.location.search.split('=');
        //console.log(window.location.search);
        self.userResId=str.pop();
        //console.log("userResId="+self.userResId);
        var split=location.pathname.split('/');
        if (split.length > 2) {
            self.resId = split.pop();
            //console.log("self.resId"+self.resId);
            self.getResource();
        }
	},
	watch:{
		question:function(){
			this.$nextTick(function(){
				$(":checkbox[name=answer]:checked").each(function(j){
					$(this).attr("checked",false);
	        	});
				layui.use('form',function(){
					var form=layui.form;
					form.render();
				});
			});
		}
	},
	mounted:function(){
		this.setVideoPlayPlace();
	},
	methods:{
		setVideoPlayPlace:function(){//初始化视频播放位置以及temp值
			var self=this;
			$.get('/user-resource/'+self.userResId,function(res){
				console.log("用户资源详情");
				console.log(res);
				self.userResource=res.data;
				var myVid=document.getElementById("video");
				myVid.currentTime=self.convertTimeToSecond(self.userResource.time);
			});
		},
		getResource:function(){
			var self=this;
			$.get('/learning-resource/'+self.resId,function(res){
				self.resource=res.data;
				console.log("resource");
				console.log(self.resource);
				$('#video').html('');
				$('#video').append("<source src='"+self.resource.url+"'></source>");
				var k=-1;
				var stopTime;
				if(Number(self.userResource.time)==0){
					console.log("获取全部问题");
					for(var i=0;i<self.resource.questions.length;i++){
						stopTime=self.resource.questions[i].time;
						console.log("stopTime="+stopTime);
						stopTime=self.convertTimeToSecond(stopTime);
						console.log("stopTime="+stopTime);
				    	self.pauseTime.push(stopTime);
				    	self.questions.push(self.resource.questions[i]);
				    }
				}else{
					console.log("从某个问题开始获取");
					for(var i=0;i<self.resource.questions.length;i++){
				    	stopTime=self.resource.questions[i].time;
				    	stopTime=self.convertTimeToSecond(stopTime);
				    	console.log("self.userResource.time="+self.userResource.time);
				    	console.log("stopTime="+stopTime);
				    	if(self.userResource.time==stopTime){
				    		k=i;
				    		console.log("k="+k);
				    	}else if(k!=-1){
				    		console.log("addstopTime="+stopTime);
				    		self.pauseTime.push(stopTime);
				    		self.questions.push(self.resource.questions[i]);
				    	}
				    }
				}

				self.setQuestion();

			})
		},
		convertTimeToSecond:function(time){
			time=time.split(':');
			console.log("timmmm="+time);
			console.log("timmmm0="+time[0]);
			console.log("timmmm1="+time[1]);
			console.log("timmmm2="+time[2]);
			var st=0;
			for(var i=0;i<time.length;i++){
				if(i==0){
					st=Number(time[i])*60*60;
				}else if(i==1){
					console.log("time="+time[i]);
					st+=Number(time[i])*60;
					console.log("st="+st);
				}else{
					st+=Number(time[i]);
				}
			}
			return st;
		},
		convertTimeToStr:function(s){
			var t;
	        if(s > -1){
	            var hour = Math.floor(s/3600);
	            var min = Math.floor(s/60) % 60;
	            var sec = s % 60;
	            if(hour < 10) {
	                t = '0'+ hour + ":";
	            } else {
	                t = hour + ":";
	            }

	            if(min < 10){t += "0";}
	            t += min + ":";
	            if(sec < 10){t += "0";}
	            t += sec;
	        }
	        return t;
		},
		setQuestion:function(){
			layui.use(['element', 'form','layer'],function(){
				var element=layui.element,$=layui.$;
			    var layer = layui.layer;
			    var form=layui.form;
			    var myVid=document.getElementById("video");
			    var state=true;
			    var temp=0;
			    //pauseTime=[1,2];
			    myVid.addEventListener("timeupdate",timeupdate);
			    function timeupdate(){
			        //因为当前的格式是带毫秒的float类型的如：12.231233，所以把他转成String了便于后面分割取秒
			        var time = myVid.currentTime+"";
			        var ts = time.substring(0,time.indexOf("."));
			        console.log("vw.pauseTime[temp]="+vw.pauseTime[temp]);
			        if(ts==vw.pauseTime[temp] && state){
			        	var questionId=vw.questions[temp].id;
			        	vw.question=vw.questions[temp];
			        	$("#content").text(vw.questions[temp].content);
			            myVid.pause();
						layer.open({
					        type: 1
			                ,area:['500px','400px']
					        ,offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
					        ,id: 'layerDemo' //防止重复弹出
			                ,title:"问题"
					        ,content: $("#question")
					        ,btn: '提交'
					        ,btnAlign: 'c' //按钮居中
			                ,closeBtn:0
					        ,yes: function(){
					        	//获取用户选择的答案
					        	var checkAnswer;
					        	$(":checkbox[name=answer]:checked").each(function(j){
					        		if (j==0) {
					        			checkAnswer= $(this).val();
								    }else if(j>0){
								    	checkAnswer += "&&"+$(this).val() ;
								    }
					        	});
					        	console.log("checkAnswer="+checkAnswer);
					        	console.log("vw.resource.questions[temp].answer="+vw.questions[temp].answer);

					        	//判断答案是否选择正确
					        	if($(":checkbox[name=answer]:checked").size() == 0){
				                	form.render();
				                    layer.msg('您还未选择答案', {
				                        time: 3000//20s后自动关闭
				                    });
					        	}else if(checkAnswer==vw.questions[temp].answer){
				                	state=false;
				                	console.log("提交vw.score="+vw.resource.score);
				                	console.log("提交temp="+vw.pauseTime.length);
				                	if(temp==(vw.pauseTime.length-1)){
				                		vw.status=2;
				                		vw.score=vw.resource.score;
				                		$.ajax({
				                			url:"/user-resource-question",
					                		type:"post",
					                		data:{
					                			"resourceQuestionId":questionId
					                		},
					                		success:function(res){
					                			console.log(res);
					                			console.log(res);
					                		}
				                		});
				                	}
				                	var timeStr=vw.convertTimeToStr(vw.pauseTime[temp]);
				                	$.ajax({
				                		url:"/user-resource/"+vw.userResId+"?time="+timeStr+"&status="+vw.status+"&score="+vw.score,
				                		type:"put",
				                		success:function(res){
				                			console.log(res);
				                			layer.closeAll();
						                    myVid.play();
				                		}
				                	});

					        	}else{
				                	 form.render();
				                	 layer.msg('您选择的答案不正确', {
				                        time: 3000//20s后自动关闭
				                     });
					        	}
					          }
				        });
			        }

			        var t=Number(vw.pauseTime[temp])+1;//必须加Number否则当字符串处理
			        console.log("t="+t);
			        console.log("ts="+ts);
			        if (ts==t) {
			            state=true;
			            temp+=1;
			        }
			    }

			});
		}
	}
});

