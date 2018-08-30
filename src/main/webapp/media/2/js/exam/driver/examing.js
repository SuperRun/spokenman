$(document).ready(function() {
	countDown();
	setInterval(countDown, 1000);
})

/**
 * 倒计时
 */
function countDown() {
	// 获取当前时间
	var now = new Date();
	// 获取考试开始时间
	var examStartTimeStr = $("#examStartTime").val();
	var examStartTime = new Date(examStartTimeStr);
	// 获取考试结束时间
	var examEndTimeStr = $("#examEndTime").val();
	var examEndTime = new Date(examEndTimeStr);
	// 获取是否已经交卷
	var haveSubmit = $("#haveSubmit").val();

	// 当前时间与考试开始时间差值
	var diff1 = examStartTime.getTime() - now.getTime();
	var diff2 = examEndTime.getTime() - now.getTime();
	if (haveSubmit == "true") {
		$("#examStatusShow").html("已交卷");
		$("#count_down").html("00:00");
	} else if (diff1 > 0) {
		/*
		 * 考试还没开始
		 */
		$("#examStatus").val(0);
		// 计算出相差天数
		var days = Math.floor(diff1 / (24 * 3600 * 1000));
		// 计算出小时数
		var leave1 = diff1 % (24 * 3600 * 1000);
		var hours = Math.floor(leave1 / (3600 * 1000));
		// 计算相差分钟数
		var leave2 = leave1 % (3600 * 1000);
		var minutes = Math.floor(leave2 / (60 * 1000));
		// 计算相差秒数
		var leave3 = leave2 % (60 * 1000);
		var seconds = Math.round(leave3 / 1000);
		$("#examStatusShow").html("距考试开始");
		$("#count_down").html(getDiffTime(days, hours, minutes, seconds));
		$("#questionContent").hide();
		$("#examOperation").hide();
		$("#questionList").hide();
		$("#examTips").hide();
	} else if (diff2 > 0) {
		// 正在考试
		$("#examStatus").val(1);
		// 计算出相差天数
		var days = Math.floor(diff2 / (24 * 3600 * 1000));
		// 计算出小时数
		var leave1 = diff2 % (24 * 3600 * 1000);
		var hours = Math.floor(leave1 / (3600 * 1000));
		// 计算相差分钟数
		var leave2 = leave1 % (3600 * 1000);
		var minutes = Math.floor(leave2 / (60 * 1000));
		// 计算相差秒数
		var leave3 = leave2 % (60 * 1000);
		var seconds = Math.round(leave3 / 1000);
		$("#examStatusShow").html("距考试结束");
		$("#count_down").html(getDiffTime(days, hours, minutes, seconds));
		$("#questionContent").show();
		$("#examOperation").show();
		$("#questionList").show();
		$("#examTips").show();
	} else {
		// 考试已经结束
		if (haveSubmit == 0) {
			// 还未交卷,自动交卷
			var examId = $("#examId").val();
			var basePath = $("#basePath").val();
			submitPaper(examId, basePath);
		}
		$("#examStatus").val(2);
		$("#examStatusShow").html("考试结束");
		$("#count_down").html("00:00");
		$("#questionContent").show();
		$("#examOperation").show();
		$("#questionList").show();
		$("#examTips").show();
		$("#submitable").val(false);
	}

}

function getDiffTime(days, hours, minutes, seconds) {
	var res = "";
	if (parseInt(days) >= 1) {
		res += days + "天 ";
	}
	if (parseInt(hours) >= 1) {
		res += hours + "小时 ";
	}
	if (parseInt(minutes) >= 1) {
		res += minutes + "分 ";
	}
	if (parseInt(seconds) < 10) {
		res += "0" + seconds + "秒";
	} else {
		res += seconds + "秒";
	}
	return res;
}

function selectQuestionItems(sequence) {
	$("#answer").val(sequence);
}

/**
 * 切题
 * 
 * @param sequence
 */
function changeQuestion(sequence) {
	/*
	 * 保存答案
	 */
	var answer = $("#answer_input").val();
	if (answer != null && answer != "") {
		$("#answer").val(answer);
	}
	console.log($("#question_type").html());
	if ($("#question_type").html() == "多选题") {
		// 多选题的话答案为多个选项
		var answers="";
		$("#user_answer").children().each(function(i, n) {
			var obj = $(n);
			if (obj.hasClass('answer_hover')) {
				var child = obj.children();
				console.log(child.html());
				answers+="&&"+child.html();
			}
		});
		$("#answer").val(answers);
	}
	if (sequence == -1) {
		// 上一题
		var sequenceNow = $("#sequenceNow").val();
		$("#sequenceNext").val(parseInt(sequenceNow) - 1);
	} else if (sequence == 0) {
		// 下一题
		var sequenceNow = $("#sequenceNow").val();
		$("#sequenceNext").val(parseInt(sequenceNow) + 1);
	} else {
		// 跳题
		$("#sequenceNext").val(sequence);
	}
	$("#data").submit();
}

/**
 * 交卷
 * 
 * @param examId
 */
function submitPaper(examId, basePath) {
	if (confirm("交卷后将不能再修改答题！确认交卷？")) {
		$.ajax({
			url : basePath + "exam/driver/examing/save/" + examId,
			type : "POST",
			data : {
				answer : $("#answer_input").val(),
				questionIdNow : $("#questionIdNow").val(),
				questionTypeNow : $("#questionTypeNow").val(),
				sequenceNow : $("#sequenceNow").val(),
				sequenceNext : $("#sequenceNext").val(),
				submitable : $("#submitable").val(),
				sum : $("#sum").val()
			},
			success : function(data) {
				if (data.success) {
					// alert("设置不能提交");
					$("#submitable").val(false);
					// alert("设置已经交卷");
					$("#haveSubmit").val(true);
					$("#examStatus").val(2);

					$("#examStatusShow").html("已交卷！");
					$("#examTipsContent").html("已交卷");
					$("#count_down").html("00:00");
					location.href = basePath + "exam/driver/login";
				} else {
					$("#examTipsContent").html("交卷失败！");
				}
			},
			error : function() {

			}
		});
	}

}

/**
 * 驾驶员退出登录
 * 
 * @param basePath
 */
function logout(basePath) {
	$.ajax({
		url : basePath + "exam/driver/logout",
		type : 'POST',
		success : function(data) {
			if (data.success) {
				location.href = basePath + "exam/driver/login";
			} else {
				alert("退出失败");
			}
		},
		error : function() {
			alert("请求失败！");
		}
	})
}
