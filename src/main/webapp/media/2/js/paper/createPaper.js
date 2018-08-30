/**
 * 将页面信息保存到表单中
 * 
 * @returns {Boolean}
 */
function savePaperInfo(basePath) {

	$("#passNumError").empty();
	$("#passNumError").hide();
	$("#passScoreError").empty();
	$("#passScoreError").hide();
	$("#paperNameError").empty();
	$("#paperNameError").hide();

	/*
	 * 简单难度占比
	 */
	var easyPercent = $("#easy").html();
	if (easyPercent == null || easyPercent == "") {
		return false;
	}
	easyPercent = toPoint(easyPercent);
	$("#easyPercent").val(easyPercent);

	/*
	 * 中等难度占比
	 */
	var mediumPercent = $("#middle").html();
	if (mediumPercent == null || mediumPercent == "") {
		return false;
	}
	mediumPercent = toPoint(mediumPercent);
	$("#mediumPercent").val(mediumPercent);

	/*
	 * 困难难度占比
	 */
	var hardPercent = $("#hard").html();
	if (hardPercent == null || hardPercent == "") {
		return false;
	}
	hardPercent = toPoint(hardPercent);
	$("#hardPercent").val(hardPercent);

	/*
	 * 试卷类型
	 */
	var paperType = $("#paper_type").val();
	if (paperType == null || paperType == "") {
		paperType = 0;
	}
	$("#paperType").val(paperType);

	/*
	 * 试卷名称
	 */
	var paperName = $("#title_container").val();
	if (paperName == null || paperName == "") {
		$("#paperNameError").show();
		$("#paperNameError").html("请填写试卷名称");
		return false;
	}
	$("#paperName").val(paperName);

	/*
	 * 试卷描述
	 */
	var paperDescription = $("#describe_container").val();
	$("#paperDescription").val(paperDescription);

	/*
	 * 及格方式
	 */
	var passType = $("input[name='passingType']:checked").val();
	if (passType == "number") {
		var passNum = $("#pass_num").val();
		if (passNum == null || passNum == "") {
			$("#passNumError").show();
			$("#passNumError").html("必填");
			return false;
		} else if (!isNumber(passNum)) {
			$("#passNumError").show();
			$("#passNumError").html("请填写正确格式");
			return false;
		}
		$("#passNum").val(passNum);
	} else if (passType = "score") {
		var passScore = $("#pass_score").val();
		if (passScore == null || passScore == "") {
			$("#passScoreError").show();
			$("#passScoreError").html("必填");
			return false;
		} else if (!isNumber(passScore)) {
			$("#passScoreError").show();
			$("#passScoreError").html("请填写正确格式");
			return false;
		}
		$("#passScore").val(passScore);
	}

	/*
	 * paperDesignString
	 */
	savePaperDesignString();

	/*
	 * 提交
	 */
	if ($("#paperType").val() == 0) {
		// 一人一卷表单提交跳转页面
		$("#data").submit();
	} else {
		savePaperInfoAjax(basePath);
	}

}

function toPoint(percent) {
	var str = percent.replace("%", "");
	str = str / 100;
	return str;
}

/**
 * 讲paperDesign转化为String
 */
function savePaperDesignString() {
	var a = new Array();
	var i = $(".paper_design_input");
	for (var j = 0; j < i.length; j++) {
		var id = $(i[j]).attr('id');
		var num = $(i[j]).val();
		var b = id + "&" + num;
		if (num != null && num != "" && parseInt(num) > 0) {
			a.push(b);
		}
	}
	var res = a.join(";");
	console.log(res);
	$("#paperDesignString").val(res);
}

/**
 * 众人一卷ajax提交保存paper基本信息
 * 
 * @param basePath
 */
function savePaperInfoAjax(basePath) {
	$.ajax({
		url : basePath + "paper/member/basicInfoAjax",
		type : "POST",
		data : {
			examId : $("#examId").val(),
			paperId : $("#paperId").val(),
			easyPercent : $("#easyPercent").val(),
			mediumPercent : $("#mediumPercent").val(),
			hardPercent : $("#hardPercent").val(),
			paperDesignString : $("#paperDesignString").val(),
			paperType : $("#paperType").val(),
			paperName : $("#paperName").val(),
			paperDescription : $("#paperDescription").val(),
			examDescription : $("#examDescription").val(),
			passNum : $("#passNum").val(),
			passScore : $("#passScore").val()
		},
		success : function(data) {
			if (data.success) {
				// 页面上保存examId
				$("#paperId").val(data.data);
				showQuestions(basePath);
			} else {
				// ajax保存出错
				alert("保存失败！");
			}
		}
	});
}

function showQuestions(basePath) {
	$
			.ajax({
				url : basePath + "paper/member/" + $("#paperId").val()
						+ "/create",
				type : "GET",
				success : function(data) {
					if (data.success) {
						$("#paperDetail").html("");
						$("questionsString").val("");
						console.log(data);
						/*
						 * 显示题目
						 */
						for (var i = 0; data.data != null
								&& i < data.data.length; i++) {
							var detail = "";
							// 显示开始
							detail += "<div class='form-group'><div class='col-md-9'>";
							/*
							 * 题干开始
							 */
							detail += "<h3 class='question' id='question'>";

							detail += "<span class='serial_number'>"
									+ parseInt(i + 1)
									+ "</span>&nbsp;<span class='question_content'>"
									+ data.data[i].content + "</span>";
							// 题干结束
							detail += "</h3>";

							/*
							 * 选项开始
							 */
							detail += "<ul id='answer_list'>";

							for (var j = 0; data.data[i].questionItems != null
									&& j < data.data[i].questionItems.length; j++) {
								detail += "<li class='answer_item'><span class=''>"
										+ data.data[i].questionItems[j].sequence
										+ "</span>&nbsp;"
										+ "<spanclass='answer_content'>";
								if (data.data[i].questionItems[j].pic != null
										&& data.data[i].questionItems[j].pic != "") {
									detail += "<img src='" + basePath
											+ data.data[i].questionItems[j].pic
											+ "'> </span></li>";
								} else {
									detail += "<span>"
											+ data.data[i].questionItems[j].content
											+ "</span>";
								}

							}
							// 选项结束
							detail += "</ul>";
							// 题目结束
							detail += "</div>";

							/*
							 * 题目附加描述图片开始
							 */
							detail += "<div class='col-md-3 text-center' id='media-container'>";

							if (data.data[i].pic1 != null
									&& data.data[i].pic1 != "") {
								detail += "<img src='" + basePath
										+ data.data[i].pic1 + "'>";
							}
							if (data.data[i].pic2 != null
									&& data.data[i].pic2 != "") {
								detail += "<img src='" + basePath
										+ data.data[i].pic2 + "'>";
							}
							if (data.data[i].pic3 != null
									&& data.data[i].pic3 != "") {
								detail += "<img src='" + basePath
										+ data.data[i].pic3 + "'>";
							}
							// 题目附加描述图片结束
							detail += "</div>";

							// 显示完毕
							detail += "</div>";

							// 显示题目信息
							$("#paperDetail").append(detail);
							// 添加id信息
							console.log($("#questionsString").val());
							$("#questionsString").val(
									$("#questionsString").val()
											+ data.data[i].id + ";");
							console.log($("#questionsString").val());
						}
					} else {
						alert("获取题目失败");
					}

				},
				error : function() {
					alert("请求出错");
				}
			});
}

/**
 * 保存试卷题目
 * 
 * @param basePath
 */
function savePaper(basePath) {
	console.log($("#questionsString").val());
	$.ajax({
		url : basePath + "paper/member/" + $("#paperId").val() + "/save",
		type : 'POST',
		data : {
			questionsString : $("#questionsString").val()
		},
		success : function(data) {
			if (data.success) {
				location.href = basePath + "exam/member/home";
			} else {
				alert("保存失败");
			}
		},
		error : function() {
			alert("请求出错！");
		}
	});
}

function isNumber(s) {
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1) {
		return true;
	} else {
		return false;
	}
};