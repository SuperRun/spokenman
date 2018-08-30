/**
 * 题型切换时分值显示切换
 */
function conveyQuestionTypeScore() {
	var score = $("#questionType").find("option:selected").attr("score");
	$("#score").val(score);
}

/**
 * 上传选项图片
 * 
 * @param event
 */
function questionItemPicChange(event) {
	// file型 input
	console.log($(event.target));
	// 表单
	console.log($(event.target).parent().parent());
	// 预览图片input
	console.log($(event.target).parent().parent().next().find("img"));
	// 保存图片url的input
	console.log($(event.target).parent().parent().next().find("input"));
	var form = $(event.target).parent().parent();
	form.ajaxSubmit({
		success : function(data) {
			console.log('success');
			console.log(data);
			if (typeof (data) != 'object')
				data = JSON.parse(data);
			console.log(data);
			$(event.target).parent().parent().next().find("input").val(
					data.files);
			$(event.target).parent().parent().next().find("img").attr('src',
					path + '/' + data.files);
			$(event.target).parent().parent().next().find("img").show(100);
		},
		error : function(data) {
			console.log('fail');
			console.log(data);
		}
	})
}

function getQuestionInfo() {
	$("#scoreError").empty();
	$("#contentError").empty();
	$("#answerError").empty();
	$("#newTypeError").empty();
	$("#newSubjectError").empty();
	var flag = 1;
	var score = $("#score").val();
	var content = $("#question").val();
	var answer = $("#answer_input").val();
	var typeId = $("#questionType").val();
	var newType = "";
	if (typeId == -1) {
		newType = $("#questionText").val();
		if (newType == null || newType == "") {
			$("#newTypeError").html("请输入新题型描述！");
		}
	}
	var subjectId = $("#knowledge").val();
	var newSubject = "";
	if (subjectId == -1) {
		newSubject = $("#knowledgeText").val();
		if (newSubject == null || newSubject == "") {
			$("#newSubjectError").html("请输入新知识点描述！");
		}
	}
	if (score == null || score == "") {
		$("#scoreError").html("请输入分值！");
		flag = 0;
	}
	if (content == null || content == "") {
		$("#contentError").html("请输入题干信息！");
		flag = 0;
	}
	if (answer == null || answer == "") {
		$("#answerError").html("请输入答案！");
		flag = 0;
	}
	if (flag == 0) {
		return false;
	}
	var difficulty = $("input[name='difficulty']:checked").val();
	var questionItemsType = $("input[name='optionType']:checked").val();
	// 获取文字选项string
	var questionItemsContentStr = "";
	$('input[name="questionItemContent"]').each(function() {
		if($(this).val()==null||$(this).val()==""){
			
		}else{
			questionItemsContentStr += $(this).val() + ";";
		}
	});
	console.log(questionItemsContentStr);
	// 获取图片选项string
	var questionItemsPicStr = "";
	$('input[name="questionItemPicUrl"]').each(function() {
		if($(this).val()==null||$(this).val()==""){
			
		}else{
			questionItemsPicStr += $(this).val() + ";";
		}
	});
	console.log(questionItemsPicStr);

	// 表单赋值
	$("#content").val(content);
	$("#typeId").val(typeId);
	$("#subjectId").val(subjectId);
	$("#marks").val(score);
	$("#answer").val(answer);
	$("#difficulty").val(difficulty);
	$("#questionItemsContentStr").val(questionItemsContentStr);
	$("#questionItemsPicStr").val(questionItemsPicStr);
	$("#questionItemsType").val(questionItemsType);
	$("#newType").val(newType);
	$("#newSubject").val(newSubject);
	// 表单提交
	$("#questionImport").submit();
}
