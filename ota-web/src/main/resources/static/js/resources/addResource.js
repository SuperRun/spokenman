 
var vw=new Vue({
	el:"#app",
	data:{
		fileName:"",
		addResource:{
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
			pic:"",			
				
		},
		resCover:""
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
			//上传文件(视频、图片)
			
			var self=this;
			$('#upload').click(function(){
				$('#uploadFile').click();
			});
			$('#uploadFile').change(function(){
				self.fileName=$(this)[0].files[0].name;
				//var url = URL.createObjectURL($(this)[0].files[0]);  
				//$('#video').html('');
				//$('#video').append("<source src='"+url+"'></source>");
				console.log($(this)[0].files[0]);
			});
			$('#cover').click(function(){
				$('#uploadResCover').click();
			});
			$('#uploadResCover').change(function(){
				var file=$(this)[0].files[0];
				var url=self.scanPic(file);
				$("#resCover").css('display','inline');
				self.resCover=url;
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
	      	            		form.render('select','addForm');
	      	              		
	      					  });
	      				 });
              		}); 

	            		
				  });
			 });
			

		},
		insertResource:async function(){
			var self=this;
			if(($('#uploadFile')[0].files[0])!=null){
				var formData = new FormData();
				var myVid=document.getElementById("video");
				console.log("文件");
				console.log($('#uploadFile')[0].files[0]);
				formData.append('file',$('#uploadFile')[0].files[0]);
				formData.append('filename',$('#uploadFile')[0].files[0].name);
				//console.log("视频时长");
				//console.log(self.convertTimeToStr(myVid.duration));
				//formData.append('filename',self.convertTimeToStr(myVid.duration));
				await $.ajax({
					url:"/file-upload/video",
					type:'post',
					data:formData,
					cache: false,//上传文件无需缓存
	                processData: false,//用于对data参数进行序列化处理 这里必须false
	                contentType: false, //必须
					success:function(res){
						console.log('视频上传完成');
						vw.addResource.url= res.data.url;
						vw.addResource.duration=res.data.duration;
					}
				});
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
						console.log('图片上传完成');
						console.log(res);
						vw.addResource.pic= res.data;
					}
				});
				vw.addResource.authType=$("#authType").val();
				vw.addResource.level=$("#level").val();
				vw.addResource.typeId=$("#typeId").val();
				vw.addResource.type=$("#type").val();
				vw.addResource.lecturerId=$("#lecturerName").val();
				vw.addResource.lecturerName=$("#lecturerExp").find("option:selected").text();
				vw.addResource.time=$("#time").val();//.format("yyyy-MM-dd hh:mm:ss");
				console.log(vw.addResource);
				$.post("/learning-resource",vw.addResource,function(res){
				    console.log(res);
				    if(res.code==1){
				    	layer.close(layer.index);  
	    	            window.parent.location.reload();
				    }
				});
			}else {
				layui.use('layer',function(){
					var layer=layui.layer;
					layer.msg("请上传文件");
				});
			}
			
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
		}
	}
});
