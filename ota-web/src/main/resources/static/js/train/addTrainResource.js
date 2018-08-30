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
            self.getTrainResource();
        }
        
	},
	mounted:function(){
		
	},
	methods:{
		getTrainResource:function(){
		    layui.use('table', function(){
		        var table = layui.table;

		        //展示已知数据
		         table.render({
		            elem: '#training-resource'
		            ,url:'/training/resource/list/'+vw.trainingId+'/unselected'
	            	,height:471
	            	, limit:10 
		            ,cols: [[ //标题栏
		                {width:50, fixed: 'left',title: "<input type='checkbox' id='selectAll' lay-skin='primary' lay-filter='selectAll'>",templet:function(d){
		                	return "<input type='checkbox' name='resourceId'  value='"+d.id+"' lay-skin='primary'>";
		                }}
		                ,{field: 'name', title: '资源名称', width: 160}
		                ,{field: 'type', title: '类型',width: 100,templet:function(d){
		                	if(d.type==1){
    		            		return "<i class='layui-icon' style='font-size:22px'>&#xe6ed;</i>";
    		            	}else if(d.type==2){
    		            		return "<i class='layui-icon' style='font-size:22px'>&#xe6fc;</i>";
    		            	}else if(d.type==3){
    		            		return "<i class='layui-icon' style='font-size:22px'>&#xe634;</i>";
    		            	}
		                }} 
		                ,{field: 'lecturerName', title: '讲师姓名',width: 200}
		                ,{field: 'levelName', title: '等级', width: 120}
		                ,{field: 'typeName', title: '条线', width: 120}
		                ,{field: 'score', title: '学分', width: 80}
		                ,{ title: '是否设为必修',templet:function(d){
		                	return "<input type='checkbox' class='required' id='required"+d.id+"' lay-filter='required' lay-skin='switch'>";
		                }}
		                
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
		searchTrainResource:function(){
        	
        	layui.use(['table','form'],function(){
        		var table=layui.table;
        		var form=layui.form;
        		table.reload('training-users', {
        			  url: vw.url
        			  ,method:'get'
        			  ,where: {
        				  lecturerName:$('#lecturerName').val()
        			      ,level:$('#searchLevel').val()
        			      ,typeId:$('#searchTypeId').val()
        			      ,status:1
        			  }//设定异步数据接口的额外参数
        			  ,page: {
        				   curr: 1 //重新从第 1 页开始
        			  },done: function(res, curr, count){
				  	    $("#province").empty();
				  	    $("#city").empty();
				  	    $("#county").empty();
				  	    form.render('select','choose-form');
				  	    $('#area').css('color','#666');
				  	    $('#area').val('请选择区域');
				  	    //得到当前页码
				  	 }
        			  
        		});
        	});
		},
		addTrainResource:function(){
			layui.use('layer',function(){
				var layer=layui.layer;
	    		layer.confirm('确定要添加这些资源吗？', {
	    			  btn: ['确定','取消'] //按钮
	    			}, function(){
	    				$("input[name='resourceId']:checked").each(function(temp){
	    					var id=$(this).val();
	    					var require=0;
	    					console.log($('#required'+id).is(':checked'));
	    					if($('#required'+id).is(':checked')){
	    						require=1;
	    					}
	    					$.ajax({
	    						type:'post'
	    						,url:'/training/resource/'
	    						,data:{
	    							resourceId:id,
	    							trainingId:vw.trainingId,
	    							required:require
	    						}
	    						,success:function(res){
	    							console.log(res);
	    							if((temp+1)==$("input[name='resourceId']:checked").size()){
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
			
		},
		getRequiredValue:function(){
			layui.use(['element', 'form'],function(){
				var form=layui.form;
				var $=layui.$;
				form.render();
				form.on('checkbox(required)', function(data){
				  if(data.elem.checked){
					  return 1;//必修
				  } else{
					  return 0;//选修
				  }
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