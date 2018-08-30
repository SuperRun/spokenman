function changeStatus(){
	//赋值状态
	var status=$("#state").val();
	$("#status").val(status);
	
	//分页信息初始化
	$("#page").val(1);
	$("#rows").val(5);
	$("#searchKey").val("");
	
	//表单提交
	$("#data").submit();
}

/**
 * 开始考试
 * @param examId
 * @param basePath
 */
function startExam(examId,basePath){
	url=basePath+"exam/driver/examing/execution/"+examId+"";
	$("#examing").attr("action",url);
	$("#examing").submit();
}

function setExamId(examId){
	$("#examId").val(examId);
}

/**
 * 取消报名
 * @param basePath
 */
function cancelSignup(basePath){
	$.ajax({
		url : basePath + "exam/driver/" + $("#examId").val() + "/signup",
		type : "DELETE",
		success : function() {
			location.href = basePath + "exam/driver/list";
		},
		error : function() {
			alert("请求出错");
		}
	});
}

/**
 * 证书录入状态考试获取考试信息
 * @param basePath
 * @param examId
 */
function getExamInfoForCertificateStatus(basePath,examId){
	$.ajax({
		url:basePath+"exam/driver/finish/"+examId,
		success:function(data){
			if(data.success){
				$("#examName").html(data.data.examName);
				$("#examPassScore").html(data.data.passScore);
				$("#examScore").html(data.data.finalScore);
				$("#examPassNum").html(data.data.passNum);
				$("#examNum").html(data.data.finalNum);
				$("#certificateNoDiv").hide();
				$("#certificatePhotoDiv").hide();
			}else{
				alert("获取信息失败");
			}
		},
		error:function(){alert("请求出错！");}
	});
}

function getExamInfoForFinishStatus(basePath,examId){
	$.ajax({
		url:basePath+"exam/driver/finish/"+examId,
		success:function(data){
			if(data.success){
				console.log(data.data);
				$("#examName").html(data.data.examName);
				$("#examPassScore").html(data.data.passScore);
				$("#examScore").html(data.data.finalScore);
				$("#examPassNum").html(data.data.passNum);
				$("#examNum").html(data.data.finalNum);
				$("#certificateNoDiv").show();
				$("#certificateNo").html(data.data.certificateNo);
				if(data.data.certificatePhoto!=null&&data.data.certificatePhoto!=""){
					$("#certificatePhotoDiv").show();
					$("#certificatePhoto").attr('src',basePath+data.data.certificatePhoto);
				}else{
					$("#certificatePhotoDiv").hide();
				}
			}else{
				alert("获取信息失败");
			}
		},
		error:function(){alert("请求出错！");}
	});
}