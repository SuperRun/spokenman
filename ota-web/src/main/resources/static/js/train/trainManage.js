var vw=new Vue({
	el:"#app",
	data:{
		userType:""
		
	},
	mounted:function(){
		var self=this;
		self.fetch();
	},
	methods:{
		fetch:function(){
			var self=this;
			layui.use('laydate', function(){
			    var laydate = layui.laydate;
			    //时间选择器
			    laydate.render({
			        elem: '#createTime'
			        ,range:true
			        ,type: 'datetime'
			    });

			});
			self.userType=$("#userType").val();
			self.getTrains();
			
        	//找出所有组织名称
        	$.ajax({
                type : "get",
                url : "/org",
                success : function(res) {
                	console.log("组织");
                	console.log(res);
                	var names=res.data;
                	layui.use('form',function(){
                		var form=layui.form;
                		for(var i=0;i<names.length;i++){
                    		$("#searchOrgName").append("<option value='"+names[i].name+"'>"+names[i].name+"</option>");
                    	}
                		form.render('select','searchForm');
                	});
                }
        	});
		},
		getTrains:function(){
			//加载表格数据
			layui.use('table', function(){
			    var table = layui.table;
			    table.render({
			        elem: '#train'
			        ,height:471
			        ,limit:10 
			        ,cols: [[ //标题栏
			            {field: 'name', title: '名称', width: 120}
			            ,{field: 'orgName', title: '发布单位', width: 120}
			            ,{field: 'userName', title: '发布人', width: 80}
			            ,{title: '创建时间', width: 100,templet: function(d){
			            	return vw.formatDateTime(d.createTime);
			            }}
			            ,{field: 'levelName', title: '等级', width: 120}
			            ,{field: 'typeName', title: '条线', width: 80}
			            ,{title: '必修/选修学分', width: 120, templet: function(d){
			            	return d.requiredScore+'/'+d.optionalScore;
			            }}
			            ,{ title: '培训开始/结束时间', width: 180,templet: function(d){
			            	return vw.formatDateTime(d.startTime)+'/'+vw.formatDateTime(d.endTime);
			            }}
			            ,{title: '报名开始/结束时间', width: 185,templet: function(d){
			            	return vw.formatDateTime(d.signStartTime)+'/'+vw.formatDateTime(d.signEndTime);
			            }}
			            ,{field: 'examName', title: '关联考试', width: 260}
			            ,{field: 'statusName', title: '状态', width: 100}
			            ,{fixed: 'right', title: '操作',width: 240, align:'center', templet: function(d){
			            	if(d.status==6){
			            		return "<a class='layui-btn layui-btn-xs layui-btn-primary' href='/puUser/trainResult/"+d.id+"'>查看结果</a>"
				                +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='delTrain("+d.id+")'>删除</a>";
			            	}else{
			            		return "<a class='layui-btn layui-btn-xs layui-btn-primary' href='/puUser/trainConfig/"+d.id+"'>配置培训</a>"
				                +"<a class='layui-btn layui-btn-xs layui-btn-primary' href='/puUser/trainResult/"+d.id+"'>查看结果</a>"
			            		+"<a class='layui-btn layui-btn-xs layui-btn-primary' onclick='editTrain("+d.id+")'>修改</a>"
				                +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='delTrain("+d.id+")'>删除</a>";
			            	}
			            	
			            }}
			        ]]
			        ,page: true 
			        ,url: "/training"
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
		searchTrain:function(){
    		var self=this;
    		var date=$("#createTime").val();
    		if(date==""){
    			var startTime = '1970-01-01';
        		var endTime = '2100-01-01';
        		console.log("time:"+date);
        		console.log("startTime="+startTime+";endTime:"+endTime);
    		}else{
    			var startTime =date.split(" - ")[0];
    			var endTime =date.split(" - ")[1];
        		console.log("time1:"+date1);
        		console.log("startTime="+startTime+";endTime:"+endTime);
    		}
        	layui.use(['table','form'],function(){
        		var table=layui.table;
        		var form=layui.form;
        		table.reload('train', {
        			  url: '/training'
        			  ,method:'get'
        			  ,where: {
        				  orgId:$("#searchOrgName").val(),
        				  userName:$("#userName").val(),
        				  name:$("#name").val(),
        				  level:$("#level").val(),
        				  typeId:$("#typeId").val(),
        				  userName:$("#userName").val(),
        				  startTime:"",
        				  endTime:"",
        				  status:$("#status").val(),
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
		addTrain:function(){
			layui.use('layer', function(){ //独立版的layer无需执行这一句
    		    var layer = layui.layer; //独立版的layer无需执行这一句

	            layer.open({
	                type: 2 //此处以iframe举例
	                ,title: '新增培训'
	                ,area: ['600px', '600px']
	                ,maxmin: true
	                ,content: '/puUser/addTrain'
	                
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
function editTrain(id){
	layui.use('layer',function(){
		var layer=layui.layer;
		layer.open({
            type: 2,
            title: '修改培训信息',
            maxmin: true,
            area: ['750px', '600px'],
            content: '/puUser/editTrain/'+id
		});
	});
}
function delTrain(id){
	layer.confirm('确定要删除该资源吗？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			$.ajax({
              type : "delete",
              url : "/training/"+id,
              success : function(res) {
              	console.log(res);
              }
      	});
			layer.close(layer.index);  
          window.parent.location.reload();
		}, function(){
			layer.close(layer.index);  
	});
}
layui.use('element',function(){
	var $=layui.$;
    
	//菜单条事件
	$('#menu li').each(function(index){
		
		if(index==3){
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



