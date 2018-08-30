$().ready(function() {
	
	//题型被选中的筛序需要被选中
	var questionType = $("#typeIdStr").val();
	var status = questionType.split(";");
	$('input[name="questionType"]').each(function() {
		for (var i = 0; i < status.length; i++) {
			if (status[i] == $(this).val()) {
				$(this).attr("checked", true);
			}
		}
	});
	
	//知识点被选中的筛选需要被选中
	var questionSubject = $("#subjectIdStr").val();
	var status = questionSubject.split(";");
	$('input[name="questionSubject"]').each(function() {
		for (var i = 0; i < status.length; i++) {
			if (status[i] == $(this).val()) {
				$(this).attr("checked", true);
			}
		}
	});
	
	//必选题被选中的筛选需要被选中
	var questionRequired = $("#requiredStr").val();
	var status = questionRequired.split(";");
	$('input[name="questionRequired"]').each(function() {
		for (var i = 0; i < status.length; i++) {
			if (status[i] == $(this).val()) {
				$(this).attr("checked", true);
			}
		}
	});
	
});

/**
 * 提交筛选
 */
function submitChange() {
	// 获取题型筛选信息
	var questionType = "";
	$('input:checkbox[name=questionType]:checked').each(function() {
		var res = $(this).val();
		questionType += res + ";";
	});
	console.log(questionType);
	$("#typeIdStr").val(questionType);

	// 获取知识点筛选信息
	var questionSubject = "";
	$('input:checkbox[name=questionSubject]:checked').each(function() {
		var res = $(this).val();
		questionSubject += res + ";";
	});
	console.log(questionSubject);
	$("#subjectIdStr").val(questionSubject);

	// 获取必选题筛选
	var questionRequired = "";
	$('input:checkbox[name=questionRequired]:checked').each(function() {
		var res = $(this).val();
		questionRequired += res + ";";
	});
	console.log(questionRequired);
	$("#requiredStr").val(questionRequired);

	$("#data").submit();
}

/**
 * 为删除题目传递id
 * 
 * @param questionId
 */
function conveyQuestionIdForDelete(questionId) {
	$("#questionIdForDelete").val(questionId);
}

/**
 * 删除题目
 * 
 * @param basePath
 */
function deleteQuestion(basePath) {
	var questionId = $("#questionIdForDelete").val();
	console.log(questionId);
	$.ajax({
		url : basePath + "question/delete/" + $("#questionIdForDelete").val(),
		type : 'DELETE',
		success : function(data) {
			if (data.success) {
				location.href = basePath + "question/list";
			} else {
				alert("删除失败");
			}
		},
		error : function() {
			alert("请求失败");
		}
	})
}

/**
 * 将题目设置成非必选题
 * 
 * @param questionId
 * @param basePath
 */
function removeRequiredQuestion(questionId, basePath) {
	$.ajax({
		url : basePath + "question/required/" + questionId,
		type : 'DELETE',
		success : function(data) {
			if (data.success) {
				location.href = basePath + "question/list";
			} else {
				alert("设置失败");
			}
		},
		error : function() {
			alert("请求失败");
		}
	})
}

/**
 * 将题目设置成必选题
 * 
 * @param questionId
 * @param basePath
 */
function setRequiredQuestion(questionId, basePath) {
	$.ajax({
		url : basePath + "question/required/" + questionId,
		type : 'POST',
		success : function(data) {
			if (data.success) {
				location.href = basePath + "question/list";
			} else {
				alert("设置失败");
			}
		},
		error : function() {
			alert("请求出错");
		}
	})
}
