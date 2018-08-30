var trainingDetailInfo = new Vue({
	el:'#trainingHome',
	data:{
		training:{
			name:'',
			signupStartTime:'',
			signupEndTime:'',
			startTime:'',
			endTime:'',
			examName:''
		},
	},
	methods:{
	    	getTrainingDetail:function(trainingId,basePath){
	    		getTrainingDetail(trainingId,basePath);
	    	},
	    	getCompeletedTrainingDetail:function(trainingId,basePath){
	    		getCompeletedTrainingDetail(trainingId,basePath);
	    	}
	    }
})



/**
 * 获取培训详情
 * @param trainingId
 * @param basePath
 */
function getTrainingDetail(trainingId,basePath){
	$.ajax({
		url:basePath+"exam/training/"+trainingId+"/detail",
		type:'GET',
		success:function(data){
			if(data.success){
				console.log(data.data);
				trainingDetailInfo.training=data.data;
				console.log(trainingDetailInfo.training);
			}else{
				alert("获取培训详细信息失败");
			}
		},
		error:function(){alert("请求失败");}
	});
}


/**
 * 获取培训完成的详情信息
 * @param trainingId
 * @param basePath
 */
//function getCompeletedTrainingDetail(trainingId,basePath){
//	$.ajax({
//		url:basePath+"training/"+trainingId+"/completed",
//		success:function(data){
//			console.log(data.data);
//			if(data.success){
//				trainingDetailInfo.trainingCompeleted=data.data;
//				console.log(trainingCompeletedDetailInfo.training);
//			}else{
//				alert("请求详情出错");
//			}
//		},
//		error:function(){alert("请求出错");}
//	})
//}


function getCompeletedTrainingDetail(trainingId,basePath){
	$.ajax({
		url : basePath + "training/" + trainingId + "/completed",
		success : function(data) {
			if (data.success) {
				var training=data.data;
				console.log(training);
				//培训名称
				$("#compeletedTrainingName").html(training.name);
				//培训开始时间
				$("#completedTrainingStartTime").html(training.startTime);
				//培训结束时间
				$("#completedTrainingEndTime").html(training.endTime);
				//培训报名人数
				$("#completedTrainingSignupNum").html(training.signupNum);
				//培训报名驾驶员
				var signupDrivers=training.signupDrivers;
				$("#completedTrainingSignupDrivers").empty();
				for(var i=0;i<signupDrivers.length;i++){
					var detail="";
					var driver=signupDrivers[i];
					detail+="<tr>";
					
					detail+="<td class='text-center'>";
					detail+=driver.name;
					detail+="</td>";
					
					detail+="<td class='text-center'>";
					detail+=driver.orgName;
					detail+="</td>";
					
					detail+="<td class='text-center'>";
					detail+=driver.orgParentName;
					detail+="</td>";
					
					detail+="</tr>";
					
					$("#completedTrainingSignupDrivers").append(detail);
				}
				
				//培训报名人数
				$("#completedTrainingSignupNum").html(training.signupNum);
				//培训报名驾驶员
				var signupDrivers=training.signupDrivers;
				$("#completedTrainingSignupDrivers").empty();
				for(var i=0;i<signupDrivers.length;i++){
					var detail="";
					var driver=signupDrivers[i];
					detail+="<tr>";
					
					detail+="<td class='text-center'>";
					detail+=driver.name;
					detail+="</td>";
					
					detail+="<td class='text-center'>";
					detail+=driver.orgName;
					detail+="</td>";
					
					detail+="<td class='text-center'>";
					detail+=driver.orgParentName;
					detail+="</td>";
					
					detail+="</tr>";
					
					$("#completedTrainingSignupDrivers").append(detail);
				}
				
				
				//培训通过人数
				$("#completedTrainingPassNum").html(training.passNum);
				//培训通过驾驶员
				var passDrivers=training.passDrivers;
				$("#completedTrainingPassDrivers").empty();
				for(var i=0;i<passDrivers.length;i++){
					var detail="";
					var driver=passDrivers[i];
					detail+="<tr>";
					
					detail+="<td class='text-center'>";
					detail+=driver.name;
					detail+="</td>";
					
					detail+="<td class='text-center'>";
					detail+=driver.orgName;
					detail+="</td>";
					
					detail+="<td class='text-center'>";
					detail+=driver.orgParentName;
					detail+="</td>";
					
					detail+="</tr>";
					
					$("#completedTrainingPassDrivers").append(detail);
				}
				
				
				//培训未通过人数
				$("#completedTrainingFailNum").html(training.failNum);
				//培训未通过驾驶员
				var failDrivers=training.failDrivers;
				$("#completedTrainingFailDrivers").empty();
				for(var i=0;i<failDrivers.length;i++){
					var detail="";
					var driver=failDrivers[i];
					detail+="<tr>";
					
					detail+="<td class='text-center'>";
					detail+=driver.name;
					detail+="</td>";
					
					detail+="<td class='text-center'>";
					detail+=driver.orgName;
					detail+="</td>";
					
					detail+="<td class='text-center'>";
					detail+=driver.orgParentName;
					detail+="</td>";
					
					detail+="</tr>";
					
					$("#completedTrainingFailDrivers").append(detail);
				}
				
				//培训缺席人数
				$("#completedTrainingAbsentNum").html(training.absentNum);
				//培训缺席驾驶员
				var absentDrivers=training.absentDrivers;
				$("#completedTrainingAbsentDrivers").empty();
				for(var i=0;i<absentDrivers.length;i++){
					var detail="";
					var driver=absentDrivers[i];
					detail+="<tr>";
					
					detail+="<td class='text-center'>";
					detail+=driver.name;
					detail+="</td>";
					
					detail+="<td class='text-center'>";
					detail+=driver.orgName;
					detail+="</td>";
					
					detail+="<td class='text-center'>";
					detail+=driver.orgParentName;
					detail+="</td>";
					
					detail+="</tr>";
					
					$("#completedTrainingAbsentDrivers").append(detail);
				}
				
				
				
				
				
			} else {
				alert("请求详情出错");
			}
		},
		error : function() {
			alert("请求出错");
		}
	})
}





/**
 * 跳转至指定考试 
 * @param trainingId
 * @param basePath
 */
function forwordToSetExam(trainingId,basePath){
	location.href=basePath+"training/"+trainingId+"/exams";
}

/**
 * 跳转至指定参加驾驶员
 * @param trainingId
 * @param basePath
 */
function forwordToSetDrivers(trainingId,basePath){
	location.href=basePath+"training/"+trainingId+"/signup";
}

/**
 * 跳转至培训编辑
 * @param trainingId
 * @param basePath
 */
function forwordToEditTraining(trainingId,basePath){
	location.href=basePath+"training/"+trainingId+"/edit";
}

/**
 * 跳转至培训确认
 * @param trainingId
 * @param basePath
 */
function forwordToConfirmTraining(trainingId,basePath){
	location.href=basePath+"training/"+trainingId+"/confirm";
}


/**
 * 删除培训
 * @param trainingId
 * @param basePath
 */
function deleteTraining(trainingId,basePath){
	if(confirm("确认删除培训？")){
		$.ajax({
			url:basePath+"training/"+trainingId,
			type:'DELETE',
			success:function(data){
				if(data.success){
					location.href=basePath+"training/home";
				}else{
					alert("删除失败");
				}
			},
			error:function(){alert("请求出错");}
		})
	}
}

/**
 * 跳转至发布通知页面
 * @param trainingId
 * @param basePath
 */
function forwordToSetAnnouncement(trainingId,basePath){
	location.href=basePath+"training/"+trainingId+"/announcement";
}

/**
 * 跳转至修改通知页面
 * @param announcementId
 * @param basePath
 */
function forwordToEditAnnouncement(announcementId,basePath){
	location.href=basePath+"announcement/"+announcementId+"/update";
}


