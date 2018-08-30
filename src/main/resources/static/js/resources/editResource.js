
new Vue({
	el:"#app",
	data:{
		resId:-1,
		editResource:{
			name:"",
			type:"",
			url:"",
			description:"",
			lecturerId:"",
			lecturerName:"",
			level:"",
			typeId:"",
			score:"",
			authType:"",
			remark:"",
			time:"",
			description:"",
			pic:""
		},
		resCover:""
	},
	created:function(){
		var self=this;
        var split = location.pathname.split('/');
        if (split.length > 2) {
            self.resId = split.pop();
            console.log(self.resId);
            
        }
	},
	mounted:function(){
		var self=this;
		self.fetch();
	},
	methods:{
		fetch:function(){
			layui.use(['element', 'form','laydate','upload'],function(){
				var form=layui.form;
				var laydate = layui.laydate;
				var $ = layui.jquery
			    ,upload = layui.upload;
				
				form.render('select');

		        //时间选择器
		        laydate.render({
		            elem: '#time'
		            ,type: 'datetime'
		        });

			});
			
			//上传宣传图
			var self=this;
			$('#cover').click(function(){
				$('#uploadResCover').click();
			});
			$('#uploadResCover').change(function(){
				var file=$(this)[0].files[0];
				var url=self.scanPic(file);
				$("#resCover").css('display','inline');
				self.resCover=url;
			});
			
			//从后台获取条线
    	    $.get('/data-dictionary/org-types',function(res){
    	    	var names=res.data;
    	    	
    	    	$("#typeId").html();
    	    	console.log($("#typeId").html());
    			for(var i=0;i<names.length;i++){
    				$("#typeId").append("<option value='"+names[i].id+"'>"+names[i].remark+"</option>");
    			}
    			layui.use('form',function(){
    	    		var form=layui.form;
    	    		form.render();
    	    	});
    			self.getResource();
	    	});
			
		},
		scanPic:function(file){
			var url = null ;
		    if (window.createObjectURL!=undefined){ // basic
		      url = window.createObjectURL(file) ;
		    }else if (window.URL!=undefined){
		      // mozilla(firefox)
		      url = window.URL.createObjectURL(file) ;
		    }else if (window.webkitURL!=undefined) {
		      // webkit or chrome
		      url = window.webkitURL.createObjectURL(file) ;
		    }
		    return url 
		},
		initRes:function(){
			var self = this;
			$("#authType").find("option[value='"+self.editResource.authType+"']").attr("selected",true);
            $("#level").find("option[value='"+self.editResource.level+"']").attr("selected",true);
            $("#typeId").find("option[value='"+self.editResource.typeId+"']").attr("selected",true);
            $("#type").find("option[value='"+self.editResource.type+"']").attr("selected",true);
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
   	            		form.render('select','editForm');
                 	}
                 	//获取父页面的讲师经历ID
   	       			console.log("讲师经历Id="+self.editResource.lecturerId);
   	       			//根据讲师经历ID获取讲师ID
   	       			$.get("/lecturer/"+self.editResource.lecturerId, function(res){
   	       				console.log("讲师data="+res.data.userId);
   	       				var id=res.data.userId;
   	       				console.log("讲师data="+res.data.userId);
   	       				$('#lecturerName').find("option[value='"+res.data.userId+"']").attr("selected",true);
   	       				//根据讲师ID获取讲师的经历
   	       				$.get("/lecturer/user/"+id, function(res){
   	      					  console.log("讲师经历"+res);
   	      					  var expriences=res.data;
   	      					  console.log("讲师经历"+expriences.length);
   	      					  layui.use('form',function(){
   	      	              		var form=layui.form;
   	      	              		$("#lecturerExp").empty();
   	                    		for(var i=0;i<expriences.length;i++){
   	                    			$("#lecturerExp").append("<option value='"+expriences[i].id+"'>"+expriences[i].name+"</option>");
   	      	                	}
   	      					  });
   	      					form.render('select','editForm');
   	      				 });
   	       				$('#lecturerExp').find("option[value='"+self.editResource.lecturerId+"']").attr("selected",true);
   	       				form.render('select','editForm');
   	       			});
   	       			
   	              	//讲师姓名下拉框监听事件
                 		form.on('select(lecName)', function(data){
   	              		var userId=data.value;
   	      				 console.log("userId="+userId);
   	      				 $.get("/lecturer/user/"+userId, function(res){
   	      					  console.log("讲师经历"+res);
   	      					  var expriences=res.data;
   	      					  console.log("讲师经历"+expriences.length);
   	      					  layui.use('form',function(){
   	      	              		var form=layui.form;
   	      	              		$("#lecturerExp").empty();
   	                    		for(var i=0;i<expriences.length;i++){
   	                    			$("#lecturerExp").append("<option value='"+expriences[i].id+"'>"+expriences[i].name+"</option>");
   	      	                	}
   	      	            		form.render('select','editForm');
   	      	              		
   	      					  });
   	      				 });
                 		}); 

   	            		
   				  });
   			 });           
		},
		getResource:async function(){
			var self = this;
            if(self.resId !== -1){
                await $.get("/learning-resource/"+self.resId,res=>{
                 self.editResource = res.data;
                 self.resCover=self.editResource.pic;
                 var t=res.data.time;
                 if(t!=null){
                     self.editResource.time=t.split('T')[0]+' '+t.split('T')[1].split('.')[0];
                 }
                 if(self.resCover!=null){
                	 $("#resCover").css('display','inline');
                 }
            })
                self.initRes();
            }
		},
		updateResource:async function(){
			var res=this.editResource;
			res.authType=$('#authType').val();
			res.level=$('#level').val();
			res.typeId=$('#typeId').val();
			res.type=$('#type').val();
			res.time=$('#time').val();
			res.lecturerName=$("#lecturerExp").find("option:selected").text();
			res.lecturerId=$('#lecturerExp').val();
			res.pic=resCover;
			console.log('time='+res.time);
			//上传图片
			if(($('#uploadResCover')[0].files[0])!=null){
				var formData = new FormData();
				formData.append('file',$('#uploadResCover')[0].files[0]);
				formData.append('filename',$('#uploadResCover')[0].files[0].name);
				await $.ajax({
					url:"/file-upload/pic",
					type:'post',
					data:formData,
					cache: false,//上传文件无需缓存
	                processData: false,//用于对data参数进行序列化处理 这里必须false
	                contentType: false, //必须
					success:function(res){
						self.resCover=res.data;
					}
				});
			}
				
			
			var str="name="+res.name+'&authType='+res.authType+'&type='+res.type+'&remark='+res.remark+'&lecturerId='+res.lecturerId
					+'&lecturerName='+res.lecturerName+'&level='+res.level
					+'&typeId='+res.typeId+'&score='+res.score+'&time='+encodeURIComponent(res.time)+'&pic='+self.resCover;
			console.log('str='+str);
			
        	$.ajax({
                type : "put",
                url : "/learning-resource/"+this.resId+"?"+str,
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
