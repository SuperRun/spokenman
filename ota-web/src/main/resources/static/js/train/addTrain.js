new Vue({
	el:"#app",
	data:{
		addTrain:{
			name:"",
			requiredScore:"",
			optionalScore:"",
			startTime:"",
			endTime:"",
			signStartTime:"",
			signEndTime:"",
			level:"",
			typeId:"",
			description:"",
			examId:"",
			pic:""
		},
		trainCover:""
	},
	mounted:function(){
		var self=this;
		self.fetch();
	},
	methods:{
		fetch:function(){
			layui.use(['element', 'form'],function(){
				var form=layui.form;
				//获取关联考试
				$.get('/exam/member/list',function(res){
					console.log("关联考试");
					console.log(res);
					for(var i=0;i<res.data.length;i++){
						$("#test").append("<option value='"+res.data[i].examId+"'>"+res.data[i].name+"</option>");
					}
					form.render();
				});
			});

			layui.use('laydate', function(){
				var laydate = layui.laydate;
				//时间选择器
				laydate.render({
				    elem: '#trainTime'
				    ,range: true
				    ,type:'datetime'
				});
				laydate.render({
				    elem: '#signTime'
				    ,range: true
				    ,type:'datetime'
				});
				
				
			});
			//上传培训封面
			var self=this;
			$('#cover').click(function(){
				$('#uploadTrainCover').click();
			});
			$('#uploadTrainCover').change(function(){
				var file=$(this)[0].files[0];
				var url=self.scanPic(file);
				$("#trainCover").css('display','inline');
				self.trainCover=url;
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
		insertTrain:async function(){
			var self=this;
			self.addTrain.level=$("#level").val();
			self.addTrain.typeId=$("#typeId").val();
			self.addTrain.examId=$("#test").val();
			self.addTrain.startTime=$("#trainTime").val().split(" - ")[0];
			self.addTrain.endTime=$("#trainTime").val().split(" - ")[1];
			self.addTrain.signStartTime=$("#signTime").val().split(" - ")[0];
			self.addTrain.signEndTime=$("#signTime").val().split(" - ")[1];
			console.log(self.addTrain);
			//验证截止日期是否比当今时间还早
			if(self.addTrain.endTime<=self.getNowFormatDate()){
				layui.use('layer',function(){
					var layer=layui.layer;
					layer.msg("培训截止日期不合理！");
				})
			}else{
				var formData = new FormData();
				formData.append('file',$('#uploadTrainCover')[0].files[0]);
				formData.append('filename',$('#uploadTrainCover')[0].files[0].name);
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
						self.addTrain.pic= res.data;
					}
				});
				console.log("新增培训");
				console.log(self.addTrain);
				$.post('/training',self.addTrain,function(res){
					console.log(res);
		            window.parent.location.reload();
				});
			}
			
		},
		getNowFormatDate:function() {
		    var date = new Date();
		    var seperator1 = "-";
		    var seperator2 = ":";
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		            + " " + date.getHours() + seperator2 + date.getMinutes()
		            + seperator2 + date.getSeconds();
		    return currentdate;
		}
	}
});
