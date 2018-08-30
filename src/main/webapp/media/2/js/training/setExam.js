

var examInfo = new Vue({
	el:'#examInfo_Modal',
	data:{
		exam:null
	}
})


/**
 * 为了培训指定考试获取考试详情
 * @param trainingId
 * @param examId
 * @param basePath
 */
function getExamDetailInfo(trainingId,examId,basePath){
	$.ajax({
		url:basePath+"training/"+trainingId+"/"+examId,
		success:function(data){
			console.log(data.data);
			if(data.success){
				var exam=data.data;
				//考试名称
				$("#exmaDetailName").html(exam.name);
				//考试开始时间
				$("#examDetailStartTime").html(exam.examStartTime);
				//考试结束时间
				$("#examDetailEndTime").html(exam.examEndTime);
				//发起组织
				$("#examDetailOrg").html(exam.org);
				//试卷名称
				$("#examDetailPaperName").html(exam.paperName);
				//总题数
				$("#examDetailSum").html(exam.sum);
				//总分
				$("#examDetailScore").html(exam.score);
				//及格分
				$("#examDetailPassScore").html(exam.passScore);
				//及格题目数
				$("#examDetailPassNum").html(exam.passNum);
			}else{
				alert("获取考试信息失败");
			}
		},
		error:function(){alert("请求失败");}
	})
}

function conveyExamId(examId){
	$("#confirmSetExamId").val(examId);
}

function confirmSetExam(trainId,basePath){
	var examId=$("#confirmSetExamId").val();
	console.log(examId);
	var actionUrl=basePath+"training/"+trainId+"/"+examId;
	console.log(actionUrl);
	$("#confirmSetExam").attr("action",actionUrl);
	$("#confirmSetExam").submit();
}