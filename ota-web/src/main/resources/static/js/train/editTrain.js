
new Vue({
	el:"#app",
	data:{
		trainId:-1,
		editTrain:{
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
			examId:""
				
		},
		trainCover:""
	},
	created:function(){
		var self=this;
        var split = location.pathname.split('/');
        if (split.length > 2) {
            self.trainId = split.pop();
            console.log(self.trainId);
            self.getTrain();
        }
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
				    ,type: 'datetime'
				});
				laydate.render({
				    elem: '#signTime'
				    ,range: true
				    ,type: 'datetime'
				});
			});
			//上传宣传图
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
		initTrain:function(){
			var self = this;
            $("#level").find("option[value='"+self.editTrain.level+"']").attr("selected",true);
            $("#typeId").find("option[value='"+self.editTrain.typeId+"']").attr("selected",true);
            $("#test").find("option[value='"+self.editTrain.examId+"']").attr("selected",true);
            $("#trainTime").val(self.editTrain.startTime+' - '+self.editTrain.endTime);
            $("#signTime").val(self.editTrain.signStartTime+' - '+self.editTrain.signEndTime);
            $("#trainCover").css('display','inline');
            layui.use( 'form',function(){
            	var form=layui.form;
            	form.render();
            });
		},
		getTrain:async function(){
			var self = this;
            if(self.resId !== -1){
                await $.get("/training/"+self.trainId,res=>{
                	self.editTrain = res.data;
                	self.trainCover=res.data.pic;
                	self.editTrain.startTime=self.formatDateTime(res.data.startTime);
                	self.editTrain.endTime=self.formatDateTime(res.data.endTime);
                	self.editTrain.signStartTime=self.formatDateTime(res.data.signStartTime);
                	self.editTrain.signEndTime=self.formatDateTime(res.data.signEndTime);
                });
                console.log(self.editTrain);
                self.initTrain();
            }
		},
		updateTrain:async function(){
			var self=this;
			var train=self.editTrain;
			train.level=$('#level').val();
			train.typeId=$('#typeId').val();
			train.startTime=$("#trainTime").val().split(" - ")[0];
			train.endTime=$("#trainTime").val().split(" - ")[1];
			train.signStartTime=$("#signTime").val().split(" - ")[0];
			train.signEndTime=$("#signTime").val().split(" - ")[1];
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
					self.trainCover= res.data;
				}
			});
			var str="name="+train.name+'&requiredScore='+train.requiredScore+'&optionalScore='+train.optionalScore+'&description='+train.description
					+'&startTime='+encodeURIComponent(train.startTime)+'&endTime='+encodeURIComponent(train.endTime)
					+'&typeId='+train.typeId+'&level='+train.level+'&signStartTime='+encodeURIComponent(train.signStartTime)
					+'&pic='+self.trainCover;
        	$.ajax({
                type : "put",
                url : "/training/"+this.trainId+"?"+str,
                success : function(res) {
                	console.log(res);
                	layui.use('layer',function(){
                		layer.close(layer.index);  
        	            window.parent.location.reload();
                	});
                }
        	});
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
