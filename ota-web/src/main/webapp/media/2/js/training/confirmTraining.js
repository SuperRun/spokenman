/**
 * 给一位驾驶员确认状态
 * @param trainingId
 * @param driverId
 * @param basePath
 * @param event
 */
function confirmOneDriver(trainingId, driverId, basePath, event) {
	var idStr = "#statusConfirm" + driverId;
	var status = $(idStr).val();
	console.log("trainingId="+trainingId);
	console.log("driverId=",driverId);
	console.log(status);
	if(status==null||status==""){
		return false;
	}
		$.ajax({
			url : basePath + "training/" + trainingId+"/"+driverId + "/confirm" ,
			type : "POST",
			data : {
				driverTrainingStatus : $("#statusConfirm" + driverId).val()
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

}

/**
 * 提交培训确认
 */
function submitTrainingConfirm(){
	$("#submitTrainingConfirmForm").submit();
}