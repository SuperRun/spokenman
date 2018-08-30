/**
 * 分页信息提交
 */
function submit() {
	// 获取上一次的每页行数
	var rowsPre = $("#rows").val();

	// 获取当前每页行数
	var rows = $("#rows_input").val();

	$("#rows").val(rows);

	// 如果每页行数变化，跳转到第一页
	if (rowsPre != rows) {
		$("#page").val(1);
	}

	// 获取搜索关键词
	var searchKey = $("#search_text").val();
	$("#searchKey").val(searchKey);

	// 表单提交
	$("#data").submit();
}

function changePage(page) {
	if (page == -1) {
		// 用户输入页面
		page = $("#page_text").val();
		pageNum = $("#pageNum").val();
		if (page > pageNum || page <= 0) {
			// 跳页不合法
			return false;
		}
	}
	$("#page").val(page);

	// 提交表单，不再次赋值搜索关键字，不然跳页可能不正常
	$("#data").submit();
}

/**
 * 获取考试详情
 */
function getExamDetailForSignup(status,examId, basePath) {
	$.ajax({
		url : basePath + "exam/" + examId + "/detail",
		success : function(json) {
			console.log(json);
			$("#examDetailNameInSignUp").html(json.data.name);
			$("#examDetailExamStartTimeInSignUp").html(json.data.examStartTime);
			$("#examDetailDescriptionInSignUp").html(json.data.description);
			$("#examDetailExamIdInSignUp").val(json.data.examId);
			if(status==1){
				$("#myModalLabel").html("考试报名");
				$("#forSignup").show();
				$("#forCancelSignup").hide();
			}else{
				$("#myModalLabel").html("取消报名");
				$("#forCancelSignup").show();
				$("#forSignup").hide();
			}
		},
		error : function() {
			alert("请求出错!")
		}
	})
}

/**
 * 确认报名
 * 
 * @param basePath
 */
function signup(basePath) {
	// 借助ajax post提交
	$.ajax({
		url : basePath + "exam/driver/signup/" + $("#examDetailExamIdInSignUp").val(),
		type : "POST",
		success : function() {
			location.href = basePath + "exam/driver/signup";
		},
		error : function() {
			alert("请求出错");
		}
	});
}

/**
 * 取消报名
 * @param basePath
 */
function cancelSignup(basePath) {
	$.ajax({
		url : basePath + "exam/driver/signup/" + $("#examDetailExamIdInSignUp").val(),
		type : "DELETE",
		success : function() {
			location.href = basePath + "exam/driver/signup";
		},
		error : function() {
			alert("请求出错");
		}
	})
}