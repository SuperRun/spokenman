/**
 * 点击驾驶员姓名获取驾驶员详情信息
 */
function getDriverDetailInfo(driverId, basePath) {
	$.ajax({
		url : basePath + "exam/" + driverId + "/driverInfo",
		type : "GET",
		success : function(data) {
			console.log(data);
			$("#driverDetailName").html(data.data.name);
			$("#driverDetailSex").html(data.data.sex);
			$("#driverDetailAge").html(data.data.age);
			$("#driverDetailTel").html(data.data.tel);
			$("#driverDetailAddress").html(data.data.address);
		},
		error : function() {
			alert("请求出错");
		}
	});
}

/**
 * 点击取消考试报名，将驾驶员id传递给小窗体
 * 
 * @param driverId
 */
function deliverDriverId(driverId) {
	$("#cancelDriverId").val(driverId);
}

function cancelSignup(examId, basePath) {
	$.ajax({
		url : basePath + "exam/member/signup/" + examId + "/"
				+ $("#cancelDriverId").val(),
		type : "DELETE",
		success : function(data) {
			// 刷新页面
			location.href = basePath + "exam/member/signup/" + examId;
		},
		error : function() {
			alert("请求出错");
		}
	})
}

function getSignupDrivers(basePath, examId) {
	var source = "";
	$('input:checkbox[name=signupDriver]:checked').each(function() {
		var res = $(this).val();
		source += res + ";";
	});
	console.log(source);
	$("#signupDriversString").val(source);
	$.ajax({
		url : basePath + "exam/member/signup/" + examId + "/import",
		type : 'POST',
		data : {
			signupDriversString : $("#signupDriversString").val()
		},
		success : function(data) {
			if (data.success) {
				location.href = basePath + "exam/member/signup/" + examId;
			} else {
				alert(data.error);
			}
		}
	});
}
