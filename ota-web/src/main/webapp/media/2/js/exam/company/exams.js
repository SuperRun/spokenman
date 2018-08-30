
/**
 * 更改考试状态筛选
 */
function changeExamStatus(){
	var status=$("#examStatusSelect").val();
	$("#status").val(status);
	$("#data").submit();
}

/**
 * 获取考试详情
 * @param examId
 * @param basePath
 */
function getExamDetail(examId,basePath){
	$.ajax({
		url:basePath+"exam/company/"+examId+"/detail",
		type:'GET',
		success:function(data){
			if(data.success){
				console.log(data.data);
				var exam=data.data;
				//考试名称
				$("#examDetailName").html(exam.name);
				//试卷名称 
				$("#examDetailPaperName").html(exam.paperName);
				//总题数
				$("#examDetailNum").html(exam.sum);
				//及格题目数
				$("#examDetailPassNum").html(exam.passNum);
				//总分
				$("#examDetailScore").html(exam.score);
				//及格分数
				$("#examDetailPassScore").html(exam.passScore);
				//报名时间
				$("#examDetailSignupTime").html(exam.signupStartTime+"--"+exam.signupEndTime);
				//考试时间
				$("#examDetailExamTime").html(exam.startTime+"--"+exam.endTime);
				//发起组织
				$("#examDetailOrg").html(exam.org);
				//考试说明
				$("#examDetailDescription").html(exam.description);
			}else{
				alert("获取详情失败");
			}
		},
		error:function(){alert("请求出错");}
	})
}

/**
 * 查看某个考试的详细情况
 * @param examId
 * @param basePath
 */
function redirectToExamDrivers(examId,basePath){
	location.href=basePath+"exam/company/"+examId+"/drivers";
}

/**
 * 为某个考试报名管理
 * @param examId
 * @param basePath
 */
function redirectToExamDriversSignup(examId,basePath){
	location.href=basePath+"exam/company/"+examId+"/drivers/signup";
}