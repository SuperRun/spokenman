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
	// 获取模拟结束时间
	var endTimeStr = $("#endTime").val();
	var endTime = new Date(endTimeStr);
	// 时间差
	var diff = endTime.getTime() - now.getTime();
	if ($("#haveSubmitPractice").val() == null
			|| $("#haveSubmitPractice").val() == ""
			|| $("#haveSubmitPractice").val() == "false") {
		//还没交卷
		if (diff > 0) {
			// 计算出相差天数
			var days = Math.floor(diff / (24 * 3600 * 1000));
			// 计算出小时数
			var leave1 = diff % (24 * 3600 * 1000);
			var hours = Math.floor(leave1 / (3600 * 1000));
			// 计算相差分钟数
			var leave2 = leave1 % (3600 * 1000);
			var minutes = Math.floor(leave2 / (60 * 1000));
			// 计算相差秒数
			var leave3 = leave2 % (60 * 1000);
			var seconds = Math.round(leave3 / 1000);
			$("#examStatusShow").html("距考试结束");
			$("#count_down").html(getDiffTime(days, hours, minutes, seconds));
		} else {
			//时间到了自动交卷
			var examId = $("#examId").val();
			var basePath = $("#basePath").val();
			submitPractice(examId, basePath, 0);
		}
	}else{
		//已交卷
		$("#examStatusShow").html("已交卷");
		$("#count_down").html("00:00");
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

function selectQuestionItems(sequence){
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
	if ($("#haveSubmitPractice").val() == null
			|| $("#haveSubmitPractice").val() == ""
			|| $("#haveSubmitPractice").val() == "false") {
		$("#data").submit();
	} else {
		var examId = $("#examId").val();
		var basePath = $("#basePath").val();
		submitPractice(examId, basePath, 1);
	}

}

/**
 * 交卷
 * 
 * @param examId
 * @param basePath
 */
function submitPractice(examId, basePath, status) {
	if(status==0){
		var answer = $("#answer_input").val();
		if (answer != null && answer != "") {
			$("#answer").val(answer);
		}
	}
	var url = basePath + "exam/driver/practice/" + examId + "/save";
	$("#data").attr("action", url);
	if (status == 0) {
		// 第一次交卷
		$("#sequenceNext").val(null);
	}
	$("#data").submit();
}
