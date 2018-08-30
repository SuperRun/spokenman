/**
 * 为删除模拟考试传递id
 * @param examId
 */
function conveyExamId(examId) {
	$("#examId").val(examId);
}

/**
 * 删除模拟考试
 * @param basePath
 */
function deletePractice(basePath){
	$.ajax({
		url:basePath+"exam/member/practice/"+$("#examId").val(),
		type:'DELETE',
		success:function(data){
			if(data.success){
				location.href=basePath+"exam/member/practice";
			}else{
				alert("删除失败");
			}
		},
		error:function(){alert("请求出错");}
	});
}