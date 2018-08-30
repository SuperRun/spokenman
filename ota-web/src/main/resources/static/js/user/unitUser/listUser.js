var vue=new Vue({
	el:"#app",
    data:{
    	addForm:{
        	name:"",
        	phone:"",
        	email:"",
        	type:1
           
        },
        editForm:{
        	email:"",
        	name:"",
        	phone:""	
        },
        searchForm:{
        	name:""
        }
	},
	mounted:function(){
		var self=this;
		self.fetch();
	},
	methods:{
		fetch:function(){
        	layui.use('table',function(){
        		var $=layui.$;
        	    //菜单条事件
        	    $('#menu li').each(function(index){
        	    	if(index==1){
        	    		$(this).addClass("layui-this");
        	    	}else{
        	    		$(this).removeClass("layui-this");
        	    	}
        	    });
        		
        	    /*对表格进行渲染*/
			    var table=layui.table;
			    table.render({ 
					elem:'#per-user-table'
					,height :'471'
					,page: true
					, limit:10 
					,cols:[[
				          {field:'name', title:'姓名', width:80, unresize: true, sort: true}
				          ,{field:'areaName', title:'区域',width:180}
				          ,{field:'levelName', title:'等级',width:120,sort:true}
				          ,{field:'typeName', title: '条线', width:120, sort: true}
				          ,{field:'orgName', title:'所属组织', width:150, unresize: true}
				          ,{field:'phone', title:'电话', width:130, unresize: true}
				          ,{field:'email', title:'邮箱', width:150, unresize: true}
				          ,{field:'statusName', title:'状态', width:80,sort: true,unresize: true}
				          ,{field:'score', title:'总学分', width:80,sort: true,unresize: true}
				          ,{fixed: 'right',width:200,field:'operate', title:'操作',templet:function(d){
							    if(d.statusName=='正常'){
							    	return "<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='editUser("+d.id+","+d.areaId+")'>修改</a>"
										   +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserStatus("+d.id+",-1)'>删除</a>"
										   +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserStatus("+d.id+",-2)'>冻结账号</a>";
							    }else if(d.statusName=='申请中'){
							    	return "<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='editUser("+d+","+d.areaId+")'>修改</a>"
										   +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserStatus("+d.id+",-1)'>删除</a>"
									       +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserApply("+d.userApplyId+")'>审核通过</a>";
							    }else if(d.statusName=='删除'){
							    	return "<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserStatus("+d.id+",1)'>恢复账号</a>"
							    	
							    }else if(d.statusName=='账号冻结'){
							    	return "<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='editUser("+d.id+","+d.areaId+")'>修改</a>"
									       +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserStatus("+d.id+",-1)'>删除</a>"
								           +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserStatus("+d.id+",1)'>解冻账号</a>";
							    	
							    }else if(d.statusName=='审核拒绝'){
							    	return "<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserApply("+d.userApplyId+")'>重新审核</a>";
							    }
						        
					      }}
				    ]] ,
				  	url: '/user/org',
				  	method: 'get', //如果无需自定义HTTP类型，可不加该参数
				  	request: {
				  		limitName: 'rows'
				  	},//如果无需自定义数据响应名称，可不加该参数
				  	done: function(res, curr, count){
				  	    console.log(res);
				  	   
				  	    //得到当前页码
				  	    console.log(curr); 
				  	  
				  	    console.log(count);
				  	 }
			    		 
			   });
        	});
        	//搜索区域组织名称搜索
        	
        },
        search:function(){//查询用户 
        	layui.use(['table','form'],function(){
        		var table=layui.table;
        		var form=layui.form;
        		var self=this;
        		table.reload('per-user-table', {
        			  url: '/user/org'
        			  ,method:'get'
        			  ,where: {
        				  name:self.searchForm.name
        			      ,status:$('#searchStatus').val()
        			      ,orgName:$('#searchOrgName').val()
        			  }//设定异步数据接口的额外参数
        			  ,page: {
        				   curr: 1 //重新从第 1 页开始
        			  },done: function(res, curr, count){
				  	    console.log(res);
				  	    //得到当前页码
				  	    console.log(curr);					  	  
				  	    console.log(count);
				  	 }
        			  
        		});
        	});
        },
        insertUser:function(){//新增用户提交表单
			var orgId=$("#orgId").val();
			console.log("orgId"+orgId);
        	//先对表单验证
        	var self=this;
        	console.log(self.addForm);
        	var data=self.addForm;
        	console.log(data);
        	if(self.addForm.name==""){
        		layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('请填写用户姓名');
                });   
        		return false;
        	}else if(self.addForm.phone==""){
        		layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('请填写联系方式');
                });   
        		return false;
        	}else if(self.addForm.email==""){
        		layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('请填写邮箱');
                });   
        		return false;
        	}else{
            	$.ajax({
                    type : "post",
                    url : "/user/org/"+orgId,
                    data:data,
                    success : function(res) {
                    	console.log(res);
                    	layui.use('layer',function(){
                    		layer.close(layer.index);  
	        	             window.parent.location.reload();
                    	});
                    }
            	});
        	}
        	
        },
        addUser:function(){//点击增加 出现弹出层
        	layui.use(['layer','province'],function(){
        		var layer=layui.layer;
        		var province=layui.province;
        		layer.open({
                    title:'新增用户',
                    type: 1,
                    skin: 'layui-layer-rim',
                    area: ['500px', '320px'], 
                    content: $('#add-per-user'),
                    success:function(){
          			//增加表单部分的select
          		    var default2={
          		            s1: 'add-province',
          		            s2: 'add-city',
          		            s3: 'add-county',
          		            v1: null,
          		            v2: null,
          		            v3: null      
          		    }
          			province.linkage(default2);
                    }
                  });
        	})               
        },
        updateUser:function(){//更新用户信息
        	var self=this;
        	//alert("kvfdk"+self.editForm.id);
        	self.editForm.level=$('#change-grade').val();
        	self.editForm.typeId=$('#change-tiaoxian').val();
        	self.editForm.areaId=$('#change-county').val();
        	console.log(self.editForm);
        	var user=self.editForm;
        	var id=user.id;
        	var str='email='+user.email+'&phone='+user.phone+'&name='+user.name;
        	console.log(str);
        	$.ajax({
                type : "put",
                url : "/user/"+id+"?"+str,
                success : function(res) {
                	console.log(res);
                	layui.use('layer',function(){
                		layer.close(layer.index);  
        	            window.parent.location.reload();
                	});
                }
        	});
        } 
        
        
        
	}
	
	
});
function editUser(userId,areaId){
	$.ajax({
        type : "GET",
        url : "/user/"+userId,
        success : function(res) {
            vue.editForm=res.data;
			console.log(self.editForm);
			$("#change-tiaoxian option[value='"+vue.editForm.typeId+"']").attr("selected","selected");
			$("#change-grade option[value='"+vue.editForm.level+"']").attr("selected","selected");
			
        	layui.use(['layer','province'],function(){
        		var province=layui.province;
            	var areaCode=new Array();
            	if(areaId!=null){
            		console.log(areaId);
                	areaCode[0]=areaId.toString().substring(0,3)+'000';
                	areaCode[1]=areaId.toString().substring(0,4)+'00';
                	areaCode[2]=areaId;
                	var default3={
                            s1: 'change-province',
                            s2: 'change-city',
                            s3: 'change-county',
                            v1: areaCode[0],
                            v2: areaCode[1],
                            v3: areaCode[2]
                   };
                	province.linkage(default3); 
            	}


		        layer.open({
		            title:'修改个人用户',
		            type: 1,
		            skin: 'layui-layer-rim', 
		            area: ['500px', '350px'], 
		            content: $('#edit-per-user')
		        });
        	});
        	
        }
	});

}