
var picItemBtn ;

function clickPicItemBtn(event){
	picItemBtn=event.target;
	//console.log(picItemBtn);
	$("#realItemsPicUpload").click();
}



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
function questionItemPicChange() {
	//显示本地url的input
	var fileLocal=$(picItemBtn).prev();
	//console.log(fileLocal);
	//图片预览
	var picPre=$(picItemBtn).parent().next().find("img");
	//console.log(picPre);
	//图片服务器url
	var picUrl=$(picItemBtn).parent().next().find("input");
	//console.log(picUrl);
	
	$("#realItemsPicUploadForm").ajaxSubmit({
		success : function(data) {
			console.log('success');
			if (typeof (data) != 'object')
				data = JSON.parse(data);
			//console.log(data);
			$(picItemBtn).parent().next().find("input").val(
					data.files);
			$(picItemBtn).parent().next().find("img").attr('src',
					path + '/' + data.files);
			$(picItemBtn).parent().next().find("img").show(100);
			$(picItemBtn).prev().val($("#realItemsPicUpload").val());
		},
		error : function(data) {
			console.log('fail');
			console.log(data);
		}
	});
	
}

/**
 * 添加题目
 * 
 * @returns {Boolean}
 */
function getQuestionInfo() {
	$("#scoreError").empty();
	$("#contentError").empty();
	$("#answerError").empty();
	$("#newTypeError").empty();
	$("#newSubjectError").empty();
	var flag = 1;
	//分数
	var score = $("#score").val();
	//题干
	var content = $("#question").val();
	//题型
	var typeId = $("#questionType").val();
	//选项类型
	var questionItemsType = $("input[name='optionType']:checked").val();
	
	if (typeId == '670' || typeId == '671') {
		//填空题或者简答题，选项类型为无选项
		questionItemsType="none";
	}
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
	// 获取答案
	var answer = "";
	if (typeId == '670' || typeId == '671') {
		// 填空题或简单题
		$('textarea[name="correct_answer"]').each(function() {
			if ($(this).val() == null || $(this).val() == "") {

			} else {
				answer += $(this).val() + "##";// 用or多答案分割
			}
		});
	} else {
		// 有选项
		if (typeId == '643') {
			// 判断题
			answer = $("input[name='estimateOption']:checked").val();
		} else if (questionItemsType == 'font') {
			// 文字选项
			var i = 0;
			$('input[name="fontOption"]').each(function() {
				if ($(this).is(':checked')) {
					var answerItem = String.fromCharCode((65 + i));
					answer += answerItem + "&&"; // 用and多答案分割
				}
				i++;
			});
		} else if (questionItemsType == 'picture') {
			//图片选项
			var i = 0;
			$('input[name="picOption"]').each(function() {
				if ($(this).is(':checked')) {
					var answerItem = String.fromCharCode((65 + i));
					answer += answerItem + "&&"; // 用and多答案分割
				}
				i++;
			});
		}
	}
	console.log("答案="+answer);

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
	// 获取文字选项string
	var questionItemsContentStr = "";
	if (typeId == '643') {
		// 判断题
		questionItemsContentStr += "正确;错误;";
	} else if (typeId == '670' || typeId == '671') {
		// 填空题、简答题无选项
	} else {
		$('input[name="question_item_content"]').each(function() {
			if ($(this).val() == null || $(this).val() == "") {

			} else {
				questionItemsContentStr += $(this).val() + ";";
			}
		});
	}

	
	// 获取图片选项string
	var questionItemsPicStr = "";
	if (typeId == '670' || typeId == '671') {
		// 填空题、简答题无选项
	} else {
		$('input[name="question_item_pic"]').each(function() {
			if ($(this).val() == null || $(this).val() == "") {

			} else {
				questionItemsPicStr += $(this).val() + ";";
			}
		});
	}
	
	if(questionItemsType == 'picture'){
		//图片选项的话文字选项清空
		questionItemsContentStr="";
	}else if(questionItemsType == 'font'){
		//文字选项的话图片选项清空
		questionItemsPicStr="";
	}else{
		//无选项，两个选项都清空
		questionItemsContentStr="";
		questionItemsPicStr="";
	}
	
	console.log("文字选项内容="+questionItemsContentStr);

	console.log("图片选项url= "+questionItemsPicStr);

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


function clearQuestionPic(event,idx){
	var btn=event.target;
	var img=$(btn).prev().prev().prev().prev();
	img.attr('src','');
	
	$("#pic"+idx).val("");
}
