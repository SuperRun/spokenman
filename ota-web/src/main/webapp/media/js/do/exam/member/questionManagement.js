/**
 * 改变筛选题型
 */
function changeQuestionType(questionTypeId) {
	$("#typeId").val(questionTypeId);
}

/**
 * 改变筛选题目知识点
 * 
 * @param questionSubjectId
 */
function changeQuestionSubject(questionSubjectId) {
	$("#subjectId").val(questionSubjectId);
}

/**
 * 提交筛选
 */
function submitChange() {
	$("#data").submit();
}

/**
 * 获取一道题的详细信息
 * 
 * @param questionId
 * @param basePath
 */
function getQuestionDetail(questionId, basePath) {
	$.ajax({
		url : basePath + "question/" + questionId,
		type : 'GET',
		success : function(data) {
			console.log(data);
			if (data.success) {
				var question = data.data;
				// 题干
				$("#questionDetailContent").empty();
				$("#questionDetailContent").html(question.content);
				/*
				 * 题干图片
				 */
				$("#questionDetailPics").empty();
				if (question.pic1 != null && question.pic1 != "") {
					/*
					 * <li class="option_item"><img
					 * src="../../images/question_pic.jpg">图1</li>
					 */
					var pic = "<li class='option_item'>";
					pic += "<img src='" + basePath + question.pic1 + "'>图1";
					pic += "</li>";
					$("#questionDetailPics").append(pic);
				}
				if (question.pic2 != null && question.pic2 != "") {
					/*
					 * <li class="option_item"><img
					 * src="../../images/question_pic.jpg">图1</li>
					 */
					var pic = "<li class='option_item'>";
					pic += "<img src='" + basePath + question.pic2 + "'>图2";
					pic += "</li>";
					$("#questionDetailPics").append(pic);
				}
				if (question.pic3 != null && question.pic3 != "") {
					/*
					 * <li class="option_item"><img
					 * src="../../images/question_pic.jpg">图1</li>
					 */
					var pic = "<li class='option_item'>";
					pic += "<img src='" + basePath + question.pic3 + "'>图3";
					pic += "</li>";
					$("#questionDetailPics").append(pic);
				}

				/*
				 * 选项
				 */
				$("#questionItems").empty();
				var questionItems = question.questionItems;
				for (var i = 0; i < questionItems.length; i++) {
					var questionItem = questionItems[i];
					if (questionItem.content != null
							&& questionItem.content != "") {
						/*
						 * <li class="option_item">正确</li>
						 */
						var detail = "<li class='option_item'>";
						detail += questionItem.content;
						detail += "</li>";
						$("#questionItems").append(detail);
					}
					if (questionItem.pic != null && questionItem.pic != "") {
						/*
						 * <li class="option_item"><img
						 * src="../../images/question_pic.jpg"></li>
						 */
						var detail = "<li class='option_item'>";
						detail += "<img src='" + basePath + questionItem.pic
								+ "'>";
						detail += "</li>";
						$("#questionItems").append(detail);
					}
				}

				// 发布时间
				$("#questionDetailCreateTime").empty();
				$("#questionDetailCreateTime").html(question.date);
				// 题型
				$("#questionDetailType").empty();
				$("#questionDetailType").html(question.typeName);
				// 知识点
				$("#questionDetailSubject").empty();
				$("#questionDetailSubject").html(question.subjectName);
				// 题目难度
				$("#questionDetailDifficulty").empty();
				var difficulty = question.difficulty;
				if (difficulty == 1) {
					$("#questionDetailDifficulty").html("易");
				} else if (difficulty == 2) {
					$("#questionDetailDifficulty").html("中");
				} else {
					$("#questionDetailDifficulty").html("难");
				}
				// 发布组织
				$("#questionDetailOrg").empty();
				$("#questionDetailOrg").html(question.organization);
				// 分值
				$("#questionDetailMark").empty();
				$("#questionDetailMark").html(question.marks);
			} else {
				alert("获取题目详情失败");
			}
		},
		error : function(error) {
			alert("请求出错！");
		}
	});
}

/**
 * 传递题目id
 * 
 * @param questionId
 */
function conveyQuestionId(questionId) {
	$("#questionId").val(questionId);
}

/**
 * 删除题目
 * 
 * @param basePath
 */
function deleteQuestion(questionId, basePath) {
	if (confirm("确认删除题目？")) {
		$.ajax({
			url : basePath + "question/delete/" + questionId,
			type : 'DELETE',
			success : function(data) {
				if (data.success) {
					location.href = basePath + "question/list";
				} else {
					alert(data.error);
				}
			},
			error : function() {
				alert("请求出错");
			}
		});
	}

}