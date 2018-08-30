var vw=new Vue({
	el:"#app",
	data:{
		userType:"",
		url:"",
		trainingId:""
	},
	created: function(){
		var self=this;
        var split = location.pathname.split('/');
        if (split.length > 2) {
            self.trainingId = split.pop();
            console.log(self.trainingId);
            self.getOrgUsers();
        }
        
	},
	mounted:function(){
		
	},
	methods:{
		 getOrgUsers:function(){
		    layui.use('table', function(){
		        var table = layui.table;

		        //展示已知数据
		         table.render({
		            elem: '#training-users'
		            ,url:'/training-user/training/'+vw.trainingId+'/unselected?type=1'
	            	,height:471
	            	, limit:10 
		            ,cols: [[ //标题栏
		                {width:50, fixed: 'left',title: "<input type='checkbox' id='selectAll' lay-skin='primary' lay-filter='selectAll'>",templet:function(d){
		                	console.log(d);
		                	return "<input type='checkbox' name='userId'  value='"+d.id+"' lay-skin='primary'>";
		                }}
		                ,{field: 'name', title: '姓名', width: 160}
		                ,{field: 'areaName', title: '区域'}
		                ,{field: 'levelName', title: '等级', width: 120}
		                ,{field: 'typeName', title: '条线', width: 160}
		                ,{field: 'orgName',title: '所属组织'}
		            ]]
		            ,page: true //是否显示分页
		            ,method: 'get' //如果无需自定义HTTP类型，可不加该参数
				  	,request: {
				  		limitName: 'rows'
				  	}
				  	,done: function(res, curr, count){
				  	   
				  	}
		        });

		    });
		},
		searchTrainUser:function(){
        	layui.use(['table','form'],function(){
        		var table=layui.table;
        		var form=layui.form;
        		table.reload('training-users', {
        			  url: vw.url
        			  ,method:'get'
        			  ,where: {
        				  areaId:$('#county').val()
        				  ,name:$('#userName').val()
        			      ,level:$('#searchLevel').val()
        			      ,typeId:$('#searchTypeId').val()
        			      ,status:1
        			  }//设定异步数据接口的额外参数
        			  ,page: {
        				   curr: 1 //重新从第 1 页开始
        			  },done: function(res, curr, count){
				  	   
				  	 }
        			  
        		});
        	});
		},
		addTrainUser:function(){
			layui.use('layer',function(){
				var layer=layui.layer;
	    		layer.confirm('确定要添加这些用户吗？', {
	    			  btn: ['确定','取消'] //按钮
	    			}, function(){
	    				$("input[name='userId']:checked").each(function(temp){
	    					var id=$(this).val();
	    					$.ajax({
	    						type:'post'
	    						,url:'/training-user'
	    						,data:{
	    							userId:id,
	    							trainingId:vw.trainingId
	    						}
	    						,success:function(res){
	    							console.log("index="+temp);
	    							console.log("size="+$("input[name='userId']:checked").size());
	    							if((temp+1)==$("input[name='userId']:checked").size()){
	    								layer.close(layer.index); 
	    			    				layer.msg("添加成功！",{time:1000},function(){
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
	form.on('checkbox(selectAll)', function(data){
	  if(data.elem.checked){//是否被选中，true或者false
		  $('input[name="userId"]').each(function(){  
              //此处如果用attr，会出现第三次失效的情况  
              $(this).prop("checked",true);  
          });  
	  } else{
		  $('input[name="userId"]').each(function(){  
              $(this).removeAttr("checked",false);  
          }); 
	  }
	  form.render();
	});
});

