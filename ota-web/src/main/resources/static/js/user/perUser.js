
var vue=new Vue({
        el: "#app",
        data: {
        	addIndex:"",
        	editIndex:"",
        	isChanged1:false,//是否增加了新用户
        	isChanged2:false,//是否更改了用户信息
        	addForm:{
            	orgName:"",
            	phone:"",
            	email:"",
            	type:1,
            	areaId:"",
            	typeId:"",
            	level:""
            },
            editForm:{
            	areaId:"",
            	id:"",
            	level:"",
            	typeId:"",
            	orgName:"",
            	email:"",
            	name:"",
            	phone:""	
            }
        },
        mounted:function () {
            var self = this;
            self.fetch();
        },
        methods:{
            fetch: function () {
            	layui.use('table',function(){
            	    /*对表格进行渲染*/
				    var table=layui.table;
				    table.render({ 
						elem:'#per-user-table'
						,height :'471'
						,page: true
						, limit:10 
						,cols:[[
					          {field:'name', title:'姓名', width:80, unresize: true}
					          ,{field:'areaName', title:'区域',width:150}
					          ,{field:'levelName', title:'等级',width:80,sort:true}
					          ,{field:'typeName', title: '条线', width:80, sort: true}
					          ,{field:'orgName', title:'所属组织', width:110, unresize: true}
					          ,{field:'phone', title:'电话', width:120, unresize: true}
					          ,{field:'email', title:'邮箱', width:120, unresize: true}
					          ,{field:'statusName', title:'状态', width:70,sort: true,unresize: true}
					          ,{field:'userType', title:'用户类型', width:95, templet:function(d){
					        	  if(d.orgId==1){
								    	return "<span>个人用户</span>";
								    }else{
								    	return "<span>单位员工</span>";
								    }
					          }}
					          ,{field:'operate', title:'操作',templet:function(d){
								    if(d.statusName=='正常'){
								    	return "<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='editUser("+d.id+","+d.areaId+")'>修改</a>"
											   +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserStatus("+d.id+",-1)'>删除</a>"
											   +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserStatus("+d.id+",-2)'>冻结账号</a>";
								    }else if(d.statusName=='申请中'||d.statusName=='新注册'){
								    	var str="<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='editUser("+d.id+","+d.areaId+")'>修改</a>"
										   +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserStatus("+d.id+",-1)'>删除</a>";
								    	if(d.userApplyId!=null){
								    		str+="<a class='layui-btn layui-btn-primary layui-btn-xs' href='/platform/checkUser/"+d.id+"?apply="+d.userApplyId+"'>审核信息</a>";
								    	}
								    	return str;
										       
								    }else if(d.statusName=='删除'){
								    	return "<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserStatus("+d.id+",1)'>恢复账号</a>"
								    	
								    }else if(d.statusName=='账号冻结'){
								    	return "<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='editUser("+d.id+","+d.areaId+")'>修改</a>"
										       +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserStatus("+d.id+",-1)'>删除</a>"
									           +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserStatus("+d.id+",1)'>解冻账号</a>";
								    	
								    }else if(d.statusName=='审核拒绝'){
								    	return "<a class='layui-btn layui-btn-primary layui-btn-xs' href='/platform/checkUser/"+d.id+"?apply="+d.userApplyId+"'>重新审核</a>";
								    }
							        
						      }}
					    ]] ,
					  	url: '/user/1/list',
					  	//where: {token: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
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
            search:function(){//查询用户 
            	var areaId=$('#county').val();
            	if(areaId==null){
            		areaId="";
            	}
            	var data=$('#search-info').serialize()+"&areaId="+areaId;
            	layui.use(['table','form'],function(){
            		var table=layui.table;
            		var form=layui.form;
            		table.reload('per-user-table', {
            			  url: '/user/1/list'
            			  ,method:'get'
            			  ,where: {
            				  areaId:$('#county').val()
            				  ,name:$('#userName').val()
            				  ,orgName:$('#searchOrgName').val()
            			      ,level:$('#searchLevel').val()
            			      ,status:$('#searchStatus').val()
            			      ,typeId:$('#searchTypeId').val()
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
            insertUser:function(){//新增用户提交表单
            	//先对表单验证
            	var self=this;
            	var data=self.addForm;
            	var phone = self.addForm.phone;
            	var email=self.addForm.email;
            	var temp=submitVerify(phone,email);
            	var typeId=$('#addTypeId').val();
            	var level=$('#level').val();
            	if(self.addForm.name==""||phone==""||email==""){
            		layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('个人信息不能为空');
                    });   
            		return false;
            	}else if(typeId==''||level==''||self.addForm.orgName==''){
            		layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('组织信息不能为空');
                    });   
            		return false;
            	}else if(temp==-1){
            		layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('手机号只能为11位数字');
                    });   
            		return false;
            	}else if(temp==1){
            		layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('邮箱格式不正确');
                    });   
            		return false;
            	}else if(temp==0){
                	$.ajax({
                        type : "post",
                        url : "/user/personal",
                        data:data,
                        success : function(res) {
                        	console.log(res);
                        	layui.use('layer',function(){
                        		var layer=layui.layer;
                        		self.isChanged1=true;
                        		layer.close(self.addIndex);  
                        		
                        	});
                        }
                	});
            	}
            	
            },
            addUser:function(){//点击增加 出现弹出层
            	var self=this;
            	layui.use(['layer','province'],function(){
            		var layer=layui.layer;
            		var province=layui.province;
            		self.addIndex=layer.open({
                        title:'新增个人用户',
                        type: 1,
                        skin: 'layui-layer-rim',
                        area: ['500px', '400px'], 
                        content: $('#add-per-user'),
                        success:function(layer){
	              			//增加表单部分的select
                        	var mask = $(".layui-layer-shade");
                            mask.appendTo(layer.parent());
                            console.log(layer);
	              		    var default2={
	              		            s1: 'add-province',
	              		            s2: 'add-city',
	              		            s3: 'add-county',
	              		            v1: null,
	              		            v2: null,
	              		            v3: null      
	              		    }
	              			province.linkage(default2);
                        },
                        end:function(){
                        	if(isChanged2){
                        		layui.use('table',function(){
                            		var table=layui.table;
                            		table.reload('per-user-table', {
                            			  url: '/user/1/list'
                            			  ,method:'get'
                            			  ,page: {
                            				   curr: 1 //重新从第 1 页开始
                            			  },done: function(res, curr, count){
                					  	    console.log(res);
                					  	    console.log(curr);					  	  
                					  	    console.log(count);
                					  	 }
                            		});  
                            	});
                        	}
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
                    		var layer=layui.layer;
                    		self.isChanged2=true;
                    		layer.close(self.editIndex);  
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
        		var layer=layui.layer;
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


		        vue.editIndex=layer.open({
		            title:'修改个人用户',
		            type: 1,
		            skin: 'layui-layer-rim', 
		            area: ['500px', '350px'], 
		            content: $('#edit-per-user'),
		            success: function(layero, index){
		            	var mask = $(".layui-layer-shade");
                        mask.appendTo("#app");
                        console.log(layero);
		            },
                    end:function(){
                    	if(vue.isChanged2){
                    		layui.use('table',function(){
                        		var table=layui.table;
                        		table.reload('per-user-table', {
                        			  url: '/user/1/list'
                        			  ,method:'get'
                        			  ,page: {
                        				   curr: 1 //重新从第 1 页开始
                        			  },done: function(res, curr, count){
            					  	    console.log(res);
            					  	    console.log(curr);					  	  
            					  	    console.log(count);
            					  	 }
                    			  
                        		});
                        	});
                    	}
                    }
		        });
        	});
        	
        }
	});

}

layui.use('element',function(){
	var $=layui.$;
    
    //菜单条事件
    $('#menu li').each(function(index){
    	
    	if(index==1){
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


