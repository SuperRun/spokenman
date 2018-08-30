var vw=new Vue({
	el:"#app",
	data:{
		trainingId:"",
		userType:"",
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
		}
		
	},
	created:function(){
		var self=this;
        var split = location.pathname.split('/');
        if (split.length > 2) {
            self.trainingId = split.pop();
            console.log(self.trainingId);
            self.userType=$("#userType").val();
        }
	},
	mounted:function(){
		var self=this;
		self.fetch();
	},
	methods:{
		fetch:function(){
			var self=this;
			self.getTrain();
			self.getTrainingUser();
			self.getTrainingResource();
		},
		getTrain:function(){
			var self=this;
			$.get('/training/'+self.trainingId,function(res){
				console.log(res);
				self.training=res.data;
				self.training.createTime=self.formatDateTime(res.data.createTime);
			});
		},
		getTrainingUser:function(){//获取培训名单
			layui.use('table', function(){
			    var table = layui.table;
			    //培训名单表格数据
			    table.render({
			        elem: '#training-user'
			        ,url:'/training-user/training/'+vw.trainingId+'?type=1'
			        ,limit:10
			        ,height:471
			        ,cols: [[ //标题栏
			            {type:'checkbox', fixed: 'left'}
			            ,{field: 'userName',align:'center', title: '姓名', width: 160}
			            ,{ title: '区域',align:'center',templet:function(d){
			            	if(d.userDto.areaName==null){
			            		return "无";
			            	}
			                return d.userDto.areaName;
			            }}
			            ,{title: '等级', width: 120,align:'center',templet:function(d){
			            	if(d.userDto.levelName==null){
			            		return "无";
			            	}
			                return d.userDto.levelName;
			            }}
			            ,{title: '条线', width: 120,align:'center',templet:function(d){
			            	if(d.userDto.typeName==null){
			            		return "无";
			            	}
			                return d.userDto.typeName;
			            }}
			            ,{title: '所属组织',width: 160,align:'center',templet:function(d){
			            	if(d.userDto.orgName==null){
			            		return "无";
			            	}
			                return d.userDto.orgName;
			            }}
			            ,{fixed: 'right', title: '操作',width: 160, align:'center', templet:function(d){
			                return "<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='delTrainUser("+d.id+")'>删除</a>";
			            }}
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
			});
		},
		getTrainingResource:function(){//获取培训资源
			layui.use('table', function(){
			    var table = layui.table;
			    //培训资源
			    table.render({
			        elem: '#training-resource'
		        	,url:'/training/resource/list/'+vw.trainingId
		        	,limit:10 
		        	,height:471
			        ,cols: [[ //标题栏
			            {type:'checkbox', fixed: 'left'}
			            ,{field: 'resourceName', title: '资源名称', width: 120}
			            ,{field: 'typeName', title: '资源类型',width: 120,align:'center',templet:function(d){
    		            	return d.learningResource.typeName;
    		            }}
			            ,{field: 'lecturerName', title: '讲师姓名'}
			            ,{field: 'levelName', title: '等级', width: 100,templet:function(d){
    		            	if(d.learningResource.levelName==null){
    		            		return "无";
    		            	}
			            	return d.learningResource.levelName;
    		            }}
			            ,{field: 'typeIdName', title: '条线', width: 80,templet:function(d){
    		            	return d.learningResource.typeIdName;
    		            }}
			            ,{field: 'score', title: '学分', width: 80}
			            ,{title: '课程性质', width: 100,templet:function(d){
			            	if(d.required==0){
			            		return "选修";
			            	}else{
			            		return "必修";
			            	}
			            }}
			            ,{fixed: 'right', title: '操作',width: 160, align:'center', templet:function(d){
			            	if(d.required==0){
			            		return "<a class='layui-btn layui-btn-xs layui-btn-primary' onclick='updateToRequired("+d.id+")'>设为必修</a>"
				                +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='delTrainRes("+d.learningResource.id+")'>删除</a>";
			            	}else{
			            		return "<a class='layui-btn layui-btn-xs layui-btn-primary' onclick='updateToUnRequired("+d.id+")'>设为选修</a>"
				                +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='delTrainRes("+d.learningResource.id+")'>删除</a>"
			            	}
			                
			            }}
			        ]]
			    	,method: 'get' //如果无需自定义HTTP类型，可不加该参数
				  	,request: {
				  		limitName: 'rows'
				  	}
				  	,done: function(res, curr, count){
				  	    console.log(res);
				  	    console.log(curr); 
				  	    console.log(count);
				  	}
			        ,page: true //是否显示分页
			    });
			});
		},
		formatDateTime:function(inputTime) {//时间戳转换  
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
		addTrainUser:function(){//给培训添加用户
			layui.use('layer',function(){
				var layer=layui.layer;
				layer.open({
	                type: 2 //根据组织ID列出所有用户
	                ,title: '新增培训名单'
	                ,area: ['900px', '600px']
	                ,maxmin: true
	                ,content: '/puUser/addTrainUser/'+vw.trainingId
	                ,cancel:function(){
		            	location.reload(true);
		            }
	            });
			});
		},
		searchTrainUser:function(){//查询培训用户
			var areaId=$('#county').val();
        	if(areaId==null){
        		areaId="";
        	}
        	var data=$('#search-info').serialize()+"&areaId="+areaId;
        	layui.use(['table','form'],function(){
        		var table=layui.table;
        		var form=layui.form;
        		table.reload('training-user', {
        			  url: '/training-user/training/'+vw.trainingId
        			  ,method:'get'
        			  ,where: {
        				  userAreaId:$('#county').val()
        				  ,userName:$('#userName').val()
        			      ,userLevel:$('#searchUserLevel').val()
        			      ,userTypeId:$('#searchUserTypeId').val()
        			  }//设定异步数据接口的额外参数
        			  ,page: {
        				   curr: 1 //重新从第 1 页开始
        			  },done: function(res, curr, count){
				  	    console.log(res);
				  	    //清空地区部分select
				  	    $("#province").empty();
				  	    $("#city").empty();
				  	    $("#county").empty();
				  	    form.render('select','choose-form');
				  	    $('#area').css('color','#666');
				  	    $('#area').val('请选择区域');
				  	    //得到当前页码
				  	    console.log(curr);					  	  
				  	    console.log(count);
				  	 }
        			  
        		});
        	});
		},
		editTrain:function(){//修改培训信息
			layui.use('layer',function(){
				var layer=layui.layer;
				layer.open({
		            type: 2,
		            title: '修改培训信息',
		            maxmin: true,
		            area: ['750px', '600px'],
		            content: '/puUser/editTrain/'+vw.trainingId
		            
				});
			});
		},
		addTrainResource:function(){//添加培训资源
			layui.use('layer',function(){
				var layer=layui.layer;
				layer.open({
		            type: 2 //此处以iframe举例
		            ,title: '添加培训资源'
		            ,area: ['900px', '520px']
		            ,maxmin: true
		            ,content: '/puUser/addTrainResource/'+vw.trainingId
		            ,cancel:function(){
		            	location.reload(true);
		            }
		        });
				
			});
			
		},
		searchTrainResource:function(){
			layui.use(['table','form'],function(){
        		var table=layui.table;
        		var form=layui.form;
        		table.reload('training-resource', {
        			  url: vw.url
        			  ,method:'get'
        			  ,where: {
        				  lecturerName:$('#lecturerName').val()
        			      ,level:$('#searchResLevel').val()
        			      ,typeId:$('#searchResTypeId').val()
        			      ,status:1
        			  }//设定异步数据接口的额外参数
        			  ,page: {
        				   curr: 1 //重新从第 1 页开始
        			  },done: function(res, curr, count){
        				  
				  	  }
        			  
        		});
        	});
		}
	}
});
//删除培训里某个用户
function delTrainUser(trainUserId){
	layui.use('layer',function(){
		var layer=layui.layer;
		layer.confirm('确定删除这个用户吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
					url:'/training-user/'+trainUserId,
					type:'delete',
					success:function(res){
						console.log(res);
						layer.close(layer.index);  
	    	            window.parent.location.reload();
					}
				});
			},function(){
				layer.close(layer.index);
			}
		);
	});
}
//删除培训里某个资源
function delTrainRes(trainResId){
	layui.use('layer',function(){
		var layer=layui.layer;
		layer.confirm('确定删除这个资源吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
					url:'/training/resource/'+vw.trainingId+'/'+trainResId,
					type:'delete',
					success:function(res){
						console.log(res);
						layer.close(layer.index);  
	    	            window.parent.location.reload();
					}
				});
			},function(){
				layer.close(layer.index);
			}
		);
	});
}
//修改培训资源课程性质
function updateToRequired(id){
	layui.use('layer',function(){
		var layer=layui.layer;
		layer.confirm('确定设为必修吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
					type:"put",
					url:"/training/resource/"+id+"?required=1",
					success:function(){
						layer.close(layer.index);  
	    	            window.parent.location.reload();
					}
				});
			},function(){
				layer.close(layer.index);
			}
		);
	});
	
}
function updateToUnRequired(id){
	layui.use('layer',function(){
		var layer=layui.layer;
		layer.confirm('确定设为选修吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
					type:"put",
					url:"/training/resource/"+id+"?required=0",
					success:function(){
						layer.close(layer.index);  
	    	            window.parent.location.reload();
					}
				});
			},function(){
				layer.close(layer.index);
			}
		);
	});
	
}
layui.use('element',function(){
	var $=layui.$;
    
	//菜单条事件
	$('#menu li').each(function(index){
		
		if(index==3){
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