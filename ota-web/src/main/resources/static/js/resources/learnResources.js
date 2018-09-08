var vw=new Vue({
	el:"#app",
	data:{
		searchForm:{
			startTime:"",
			endTime:""
		}
	},
    mounted:function(){
    	var self=this;
    	self.fetch();
    },
    methods:{
    	fetch:function(){
    		layui.use('table', function(){
    		    var table = layui.table;
    		    //展示已知数据
    		    table.render({
    		        elem: '#res-table'
    		        ,height:471
    		        ,cols: [[ //标题栏
    		            {field: 'name', title: '资源名称', width: 120}
    		            ,{field: 'description', title: '资源介绍', width: 120}
    		            ,{field: 'authTypeName', title: '权限名称', width: 110}
    		            ,{field: 'levelName', title: '级别', width: 100}
    		            ,{field: 'typeIdName', title: '条线', width: 100}
    		            ,{field: 'url', title: '资源链接', width: 90,align:'center',templet:function(d){
    		            	if(d.type==1){
    		            		return "<a class='goto' href='/puUser/questionRes/"+d.id+"'><i class='layui-icon' style='font-size:22px'>&#xe6ed;</i></a>";
    		            	}else if(d.type==2){
    		            		return "<a class='goto' href='/puUser/questionRes/"+d.id+"'><i class='layui-icon' style='font-size:22px'>&#xe6fc;</i></a>";
    		            	}else if(d.type==3){
    		            		return "<a class='goto' href='/puUser/questionRes/"+d.id+"'><i class='layui-icon' style='font-size:22px'>&#xe634;</i></a>";
    		            	}
    		            }}
    		            ,{field: 'lecturerName', title: '讲师经历', width: 120}
    		            ,{field: 'orgName', title: '发布组织', width: 110}
    		            ,{field: 'userName', title: '发布人', width: 80}
    		            ,{field: 'createtime', title: '发布时间', width: 100,templet:function(d){
    		            	return vw.formatDateTime(d.createTime);  
    		            }}
    		            ,{field: 'updatetime', title: '修改时间', width: 100,templet:function(d){
    		            	return vw.formatDateTime(d.updateTime);     
    		        		    
    		            }}
    		            ,{field: 'statusName', title: '状态', width: 100}
    		            ,{field: 'remark', title: '备注', width: 100}
    		            ,{fixed: 'right', title: '操作',width: 145, align:'center', templet:  function(d){
    		            	if(d.statusName =='正常'){
    		            		return "<a class='layui-btn layui-btn-xs layui-btn-primary' onclick='editResource("+d.id+")'>修改</a>"
    		            			   +"<a class='layui-btn layui-btn-primary layui-btn-xs' lay-event='del'>删除</a>";
    		            	}else if(d.statusName=='已删除'){
    		            		return "<a class='layui-btn layui-btn-primary layui-btn-xs' lay-event='recover'>恢复资源</a>";
    		            	}
    		            }}
    		        ]]
    		        ,page: true 
    		        ,url: '/learning-resource',
				  	method: 'get',
				  	request: {
				  		limitName: 'rows'
				  	},
				  	done: function(res, curr, count){
				  	    $('.goto').each(function(){
				  	    	$(this).hover(function(){
				  	    		layer.tips('点我给资源添加问题',this,{time:2000});
				  	    	});
				  	    });
				  	}
    		    });
    		    
    		    layui.use(['laydate','element', 'form'], function(){
    		        var laydate = layui.laydate;
    		        //时间选择器
    		        laydate.render({
    		            elem: '#time'
    		            ,type: 'datetime'
    		            ,range:true
    		        });
    		    });
    		    
    		  //监听工具条
        	    table.on('tool(tablefilter)', function(obj){
        	        var data = obj.data 
        	            ,layEvent = obj.event; 

        	         if(layEvent === 'del'){
        	        	 
        	     		layer.confirm('确定要删除该资源吗？', {
        	    			  btn: ['确定','取消'] //按钮
        	    			}, function(){
        	    				$.ajax({
        	                        type : "delete",
        	                        url : "/learning-resource/"+data.id,
        	                        success : function(res) {
        	                        	console.log(res);
        	                        }
        	                	});
        	    				layer.close(layer.index);  
        	    	            window.parent.location.reload();
        	    			}, function(){
        	    				layer.close(layer.index);  
        	    		});
        	     		
        	        } else if(layEvent === 'recover'){
        	        	layer.confirm('确定要恢复该资源？', function(index){
        	        		$.ajax({
    	                        type : "put",
    	                        url : "/learning-resource/status/"+data.id+"?status=1",
    	                        success : function(res) {
    	                        	console.log(res);
    	                        	layer.close(layer.index); 
    	                        	window.parent.location.reload();
    	                        }
    	                	});
        	            },function(){
        	    				layer.close(layer.index);  
        	    		});
        	        }
        	        
        	    });     		    

    		});
    	},
    	search:function(){
    		var self=this;
    		var date=$("#time").val();
    		if(date==""){
    			var startTime = '1970-01-01';
        		var endTime = '2100-01-01';
        		console.log("time:"+date);
        		console.log("startTime="+startTime+";endTime:"+endTime);
    		}else{
    			var startTime =date.split(" - ")[0];
    			var endTime =date.split(" - ")[1];
        		console.log("time:"+date);
        		console.log("startTime="+startTime+";endTime:"+endTime);
    		}
    		
        	layui.use(['table','form'],function(){
        		var table=layui.table;
        		var form=layui.form;
        		table.reload('res-table', {
        			  url: '/learning-resource'
        			  ,method:'get'
        			  ,where: {
        				  name:$("#name").val(),
        				  level:$("#level").val(),
        				  typeId:$("#typeId").val(),
        				  lecturerName:$("#lecturerName").val(),
        				  type:$("#type").val(),
        				  userName:$("#userName").val(),
        				  startTime:startTime,
        				  endTime:endTime,
        				  status:$("#status").val()
        			  }
        			  ,page: {
        				   curr: 1
        			  },done: function(res, curr, count){
				  	    console.log(res);
				  	    //得到当前页码
				  	    console.log(curr);					  	  
				  	    console.log(count);
				  	 }
        			  
        		});
        	});    		
    	},
    	addLearnResource:function(){
    		console.log("增加资源");
    		layui.use('layer', function(){ //独立版的layer无需执行这一句
    		    var layer = layui.layer; //独立版的layer无需执行这一句

	            layer.open({
	                type: 2 //此处以iframe举例
	                ,title: '新增学习资源'
	                ,area: ['600px', '600px']
	                ,maxmin: true
	                ,content:'/puUser/addLearnResource'
	                
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
function editResource(resId){
	console.log('resId='+resId);
	layui.use('layer',function(){
		var layer=layui.layer;
		layer.open({
            type: 2,
            title: '修改资源信息',
            maxmin: true,
            area: ['750px', '600px'],
            content: '/puUser/editResource/'+resId
		});
	});
	
}

layui.use('element',function(){
	var $=layui.$;
    
	//菜单条事件
	$('#menu li').each(function(index){
		
		if(index==2){
			$(this).addClass("layui-nav-itemed");
			$(this).find("dd").each(function(i){
				if(i==0){
					$(this).addClass("menu-this");
					$(this).find('a').css('color','#fff');
				}else{
					$(this).removeClass("menu-this");
				}
			});
			
		}else{
			$(this).removeClass("layui-nav-itemed");
		}
	});
});



