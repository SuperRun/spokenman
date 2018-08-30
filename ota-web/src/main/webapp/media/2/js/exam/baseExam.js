function getExamDetail(examId,basePath){
	$.ajax({
		url : basePath + "exam/" + examId + "/detail",
		success : function(json) {
			console.log(json);
			if(json.success){
				console.log("获取考试成功");
				$("#examDetailName").html(json.data.name);
				$("#examDetailExamStartTime").html(json.data.examStartTime);
				$("#examDetailDescription").html(json.data.description);
				$("#examDetailExamId").val(json.data.examId);
			}else{
				alert("获取考试信息失败!");
			}
		},
		error : function() {
			alert("请求出错!")
		}
	})
}