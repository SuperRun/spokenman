/**
 * excel文件上传 
 */
$(document).ready(
		function() {
			var realFileInputE = $('#scoreExcel');
			var localPathShowE = $('#scoreExcelLocalPath');
			var realFromE = $('#excelScoreForm');
			var serverPathE = $('#scoreExcelUrl');
			var imgPreviewE = $('#imgPreView');
			commonUpload(realFileInputE, localPathShowE, realFromE,
					serverPathE, imgPreviewE, path);
		});

/**
 * 给一位考生录入成绩
 */
function scoreOneDriver(examId, driverId, basePath, event) {
	var idStr = "#score" + driverId;
	var score = $(idStr).val();
	if (isScore(score)) {
		$.ajax({
			url : basePath + "exam/member/score/" + examId + "/" + driverId,
			type : "POST",
			data : {
				score : $("#score" + driverId).val()
			},
			success : function(data) {
				console.log(event);
				var status_container = $(event.target).parent().next();
				status_container.empty();
				var label;
				if (data.success) {
					// alert("保存成功");
					label = $('<label>已保存</label>').appendTo(
							$(status_container));
					label.addClass('label-success');
				} else {
					// alert("保存成绩失败！");
					label = $('<label>未保存</label>').appendTo(
							$(status_container));
					label.addClass('label-default');
				}
				label.addClass('label');
			},
			error : function() {
				alert("请求失败！");
			}
		});
	} else {
		alert("请输入正确的分数格式");
	}

}
/**
 * 检查分数格式是否正确
 * 
 * @param s
 * @returns {Boolean}
 */
function isScore(s) {
	var regu = "^[0-9]+[\.]{0,1}[0-9]{0,1}$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
};

function scoreExcelSubmit(){
	$("#excelScoreSubmitForm").submit();
	//alert($("#scoreExcelUrl").val());
}

/**
 * 提交成绩录入
 */
function submitScore() {
	if (confirm("提交之后所有成绩不能再修改，请确认每一位考生成绩都保存成功，确认提交？")) {
		$("#submitScore").submit();
	} else {
		return;
	}
}