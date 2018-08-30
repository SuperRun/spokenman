var vw=new Vue({
	el:"#app",
	data:{
		trainingId:"",
		training:{
			name:"",
			userName:"",
			statusName:"",
			requiredScore:"",
			optionalScore:"",
			startTime:"",
			endTime:"",
			signStartTime:"",
			signEndTime:"",
			levelName:"",
			typeName:"",
			description:"",
			examName:"",
			orgName:"",
			orgId:"",
			createTime:""
		},
		userName:""
	},
	created:function(){
		var self=this;
        var split = location.pathname.split('/');
        if (split.length > 2) {
            self.trainingId = split.pop();
            console.log(self.trainingId);
        }
	},
	mounted:function(){
		var self=this;
		self.getTrain();
		self.getTrainResult();
	},
	methods:{
		getTrain:function(){
			var self=this;
			$.get('/training/'+self.trainingId,function(res){
				console.log(res);
				self.training=res.data; 
				self.training.createTime=self.formatDateTime(res.data.createTime);
			});
			//用姓名搜索
			
			$('#namefil').bind('input propertychange', function() {
				var name=$("namefil").val();
				console.log(name);
				layui.use('table', function(){
					var table = layui.table;
					table.reload('reloadTrainUser', {
						url: '/training-user/training/'+vw.trainingId
						,where: {
							userName:name
						}
						,page: {
							curr: 1 //重新从第 1 页开始
						}
					});
				});
			}); 
		},
		getTrainResult:function(){
			layui.use('table', function(){
			    var table = layui.table;
			    //课程结果
			    table.render({
			        elem: '#training-result'
			        ,url:'/training-user/training/'+vw.trainingId
		        	,limit:10
		        	,id: 'reloadTrainUser'
			        , cols:  [[ //标题栏
			        	{rowspan:2, fixed: 'left',title: "<input type='checkbox' id='selectAll' lay-skin='primary' lay-filter='selectAll'>",templet:function(d){
		                	return "<input type='checkbox' name='trainingUserId'  value='"+d.id+"' lay-skin='primary'>";
		                }}
			            ,{field: 'userName', title: '姓名', width: 120, rowspan:2}  //rowspan即纵向跨越的单元格数
			            ,{ title: '单位',width: 180, rowspan: 2,templet:function(d){
			            	return d.userDto.orgName;
			            }}
			            ,{align: 'center', title: '学分完成',colspan: 2}
			            ,{field: 'examScore', title: '成绩',event:'getExamScore',style:'cursor:pointer;', width: 70, rowspan: 2}
			            ,{field: 'certificateNo', title: '证书号',width: 108, rowspan: 2}
			            ,{field: 'statusName', title: '状态',width: 108, rowspan: 2}
			            ,{fixed:'right', title:'操作',width:125,rowspan: 2,templet:function(d){
						    //if(d.status==0){
						    	return "<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='pass("+d.id+")'>通过</a>"
									   +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='fail("+d.id+")'>不通过</a>";
						   // }
					        
				      }}
			        ], [
			            {field: 'requiredScore', title: '必修',event:'getRequiredCourse',style:'cursor:pointer;', width: 65}
			            ,{field: 'optionalScore', title: '选修',event:'getOptionalCourse',style:'cursor:pointer;', width: 65}
			        ]]
			        ,page: true //是否显示分页
			        ,method: 'get' //如果无需自定义HTTP类型，可不加该参数
				  	,request: {
				  		limitName: 'rows'
				  	}
				  	,done: function(res, curr, count){
				  	    console.log(res);
				  	    console.log(curr); 
				  	    console.log(count);
				  	}
			    });
			    
			    table.on('tool(tablefilter)', function(obj){ 
			        var trainingUserDto  = obj.data //获得当前行数据
			            ,layEvent = obj.event; //获得 lay-event 对应的值
			         vw.userName=trainingUserDto.userName;
			         console.log(obj.data);
			         if(layEvent === 'getRequiredCourse'||layEvent === 'getOptionalCourse'){
			        	 if(layEvent === 'getRequiredCourse'){
			        		 console.log("当前行数据");
					         console.log(trainingUserDto.learnedResources);
					         $('#list').show();
					         $('#test').hide();
					         console.log(trainingUserDto.learnedResources.length);
					         var resources=[];
					         
					         for(var i=0;i<trainingUserDto.learnedResources.length;i++){
					        	 if(trainingUserDto.learnedResources[i].required==1){
					        		 var res=trainingUserDto.learnedResources[i];
						        	 //res.updateTime=vw.formatDateTime(res.updateTime);
						        	 res.lecturerUserName=trainingUserDto.learnedResources[i].learningResource.lecturerUserName;
						        	 console.log("某个资源");
						        	 console.log(res);
						        	 resources.push(res); 
					        	 }
					        	 
					         }
					         
					         console.log("资源");
					         console.log(resources);
					         if(trainingUserDto.requiredResources.length!=0){
					        	//初始化用户所学课程表格
					        	 table.init('table', {
					        		data:resources
					        	 }); 
 
					         }else{
					        	 console.log("无课程");
					        	 table.init('table');
					         }   
			        	 }else if(layEvent === 'getOptionalCourse'){
			        		 console.log("当前行数据");
					         console.log(trainingUserDto.learnedResources);
					         $('#list').show();
					         $('#test').hide();
					         console.log(trainingUserDto.learnedResources.length);
					         var resources=[];
					         
					         for(var i=0;i<trainingUserDto.learnedResources.length;i++){
					        	 if(trainingUserDto.learnedResources[i].required==0){
					        		 var res=trainingUserDto.learnedResources[i];
						        	 //res.updateTime=vw.formatDateTime(res.updateTime);
						        	 res.lecturerUserName=trainingUserDto.learnedResources[i].learningResource.lecturerUserName;
						        	 console.log("某个资源");
						        	 console.log(res);
						        	 resources.push(res); 
					        	 }
					        	 
					         }
					         
					         console.log("资源");
					         console.log(resources);
					         if(trainingUserDto.optionalResources.length!=0){
					        	//初始化用户所学课程表格
					        	 table.init('table', {
					        		data:resources
					        	 }); 
 
					         }else{
					        	 console.log("无课程");
					        	 table.init('table');
					         } 
					         	
			        	 }
				        	 		        	 
			            
			         }else if(layEvent === 'getExamScore'){
			             $('#test').show();
			             $('#list').hide();
			         }
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
		},
		createCertificateNo:function(){
			var self=this;
			var myDate = new Date();
		    var year = myDate.getYear();
		    var year = year < 2000 ? year + 1900 : year;
		    var yy = year.toString().substr(2,2);
		    var mm = ("0"+(myDate.getMonth()+ 1)).slice(-2);
		    var orgId =("000"+self.training.orgId).slice(-4);
		    //2位年份;2位月份;4位单位ID;1位等级;1位条线;4位人数
		    return yy+mm+orgId+self.training.level+self.training.typeId;
		},
		updateTrainUser:function(){
			layui.use('layer',function(){
				var layer=layui.layer;
	    		layer.confirm('确定要生成证书号吗？', {
	    			  btn: ['确定','取消'] //按钮
	    			}, function(){
	    				$("input[name='trainingUserId']:checked").each(function(temp){
	    					var id=$(this).val();
	    					var num=("000"+temp+1).slice(-4);
	    					var certificateNo=vw.createCertificateNo()+num;
	    					$.ajax({
	    						type:'put'
	    						,url:'/training-user/'+id+'?certificateNo='+certificateNo
	    						,success:function(res){
	    							console.log(res);
	    							if((temp+1)==$("input[name='trainingUserId']:checked").size()){
	    								layer.close(layer.index); 
	    			    				layer.msg("生成成功！",{time:1000},function(){
	    			    					window.location.reload();
	    			    				});
	    	    					}
	    						}
    						});
	    				});
	    				
	    			}, function(){
	    				layer.close(layer.index);  
	    		});
		});
	}
	}
});

layui.use(['element', 'form'],function(){
	var form=layui.form;
	var $=layui.$;
	form.render();
	form.on('checkbox(selectAll)', function(data){
	  if(data.elem.checked){//是否被选中，true或者false
		  $('input[name="trainingUserId"]').each(function(){  
              //此处如果用attr，会出现第三次失效的情况  
              $(this).prop("checked",true);  
          });  
	  } else{
		  $('input[name="trainingUserId"]').each(function(){  
              $(this).removeAttr("checked",false);  
          }); 
	  }
	  form.render();
	});
	//菜单条事件
	$('#menu li').each(function(index){
		
		if(index==5){
			$(this).addClass("layui-this");
			$(this).find("dd").each(function(i){
				if(i==0){
					$(this).addClass("menu-this");
					$(this).find('a').css('color','#fff');
				}else{
					$(this).removeClass("menu-this");
					$(this).find('a').css('color','#000');
				}
			});
			
		}else{
			$(this).removeClass("layui-this");
		}
	});	
});

function pass(id){
	//配置一个透明的询问框
	layui.use('layer',function(){
		layer.confirm('确定该用户通过培训吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
                    type : "put",
                    url : "/training-user/status/"+id+"?status=1",
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
function fail(id){
	//配置一个透明的询问框
	layui.use('layer',function(){
		layer.confirm('确定该用户不通过培训吗', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
                    type : "put",
                    url : "/training-user/status/"+id+"?status=2",
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

