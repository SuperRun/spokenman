 
var vw=new Vue({
	el:"#app",
	data:{
		addLecturer:{
			name:"",
			userId:"",
			orgId:"",
			introduction:"",
			level:"",
			typeId:""
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
	            		form.render('select','addForm');
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
                 		form.render('select','addForm');
                 	});
                 }
         	});			 
			
		},
		insertLecturer:function(){
			var self=this;
			self.addLecturer.userId=$("#lecturerName").val();
			self.addLecturer.orgId=$("#searchOrgName").val();
			self.addLecturer.level=$("#addLevel").val();
			self.addLecturer.typeId=$("#addTypeId").val();
			console.log('addLecturer='+self.addLecturer.level);
			console.log('addLecturer='+self.addLecturer.orgId);
			console.log('addLecturer='+self.addLecturer.typeId);
			$.ajax({
				url:"/lecturer",
				type:'post',
				data:self.addLecturer,
				success:function(res){
					console.log('新增成功');
					layer.close(layer.index);  
    	            window.parent.location.reload();
				}
			});
		}
	}
});
