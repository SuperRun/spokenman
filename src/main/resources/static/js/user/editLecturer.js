 
var vw=new Vue({
	el:"#app",
	data:{
		lecId:"",
		editLecturer:{
			name:"",
			userId:"",
			orgId:"",
			introduction:"",
			level:"",
			typeId:""
		}
	},
	created:function(){
		var self=this;
		var self = this;
        var split = location.pathname.split('/');
        if (split.length > 2) {
            self.lecId = split.pop();
            console.log("讲师经历ID="+self.lecId);
            self.getLecturer();
        }
	},
	mounted:function(){
		var self=this;
		self.fetch();
	},
	methods:{
		fetch:function(){
			layui.use(['element', 'form','laydate'],function(){
				var form=layui.form;
				var laydate = layui.laydate;
				var $ = layui.jquery;
				form.render('select');
		        //时间选择器
		        laydate.render({
		            elem: '#time'
		            ,type: 'datetime'
		        });

			});
		},
		initLecturer:function(){
			var self=this;
			console.log('editLevel='+self.editLecturer.level);
			console.log('editTypeId='+self.editLecturer.typeId);
            $("#editLevel").find("option[value='"+self.editLecturer.level+"']").attr("selected",true);
            $("#editTypeId").find("option[value='"+self.editLecturer.typeId+"']").attr("selected",true);
            
			//从后台获取讲师姓名
			 $.get("/user/3/list", function(res){
				  console.log("讲师信息"+res);
				  var lecturers=res.data;
				  console.log("讲师信息"+lecturers.length);
				  layui.use('form',function(){
             		var form=layui.form;
             		if(lecturers.length!=null){
             			for(var i=0;i<lecturers.length;i++){
	                		$("#lecturerName").append("<option value='"+lecturers[i].id+"'>"+lecturers[i].name+"</option>");
	                	}
             			$("#lecturerName").find("option[value='"+self.editLecturer.userId+"']").attr("selected",true);
	            		form.render('select','editForm');
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
			         		$("#searchOrgName").append("<option value='"+names[i].id+"'>"+names[i].name+"</option>");
			         	}
			     		$("#searchOrgName").find("option[value='"+self.editLecturer.orgId+"']").attr("selected",true);
			     		form.render('select','editForm');
			     	});
			     }
			});	
			 
            
		},
		getLecturer:function(){
			var self=this;
			$.get('/lecturer/'+self.lecId,function(res){
				self.editLecturer=res.data;
				console.log('editLevel1='+self.editLecturer.level);
				console.log('editTypeId1='+self.editLecturer.typeId);
				self.initLecturer();
			});
			
		},
		updateLecturer:function(){
			var self=this;
			self.editLecturer.userId=$("#lecturerName").val();
			self.editLecturer.orgId=$("#searchOrgName").val();
			self.editLecturer.level=$("#editLevel").val();
			self.editLecturer.typeId=$("#editTypeId").val();
			console.log('editLecturer='+self.editLecturer.level);
			console.log('editLecturer='+self.editLecturer.orgId);
			console.log('editLecturer='+self.editLecturer.typeId);
			var str="name="+self.editLecturer.name+"&introduction="+self.editLecturer.introduction
				+"&orgId="+self.editLecturer.orgId+"&level="+self.editLecturer.level
				+"&typeId="+self.editLecturer.typeId+"&status="+self.editLecturer.status;
			$.ajax({
				url:"/lecturer/"+self.lecId,
				type:'put',
				data:str,
				success:function(res){
					console.log('修改成功');
					layer.close(layer.index);  
    	            window.parent.location.reload();
				}
			});
		}
	}
});
