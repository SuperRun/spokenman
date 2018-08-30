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
			if(data.success){
				//取消成功
				// 刷新页面
				location.href = basePath + "exam/company/" + examId+"/drivers/signup";
			}else{
				if(data.error=='取消时间已过'){
					//原来模态框消失
					$("#cancelSignupHide").click();
					//新模态框出现
					$("#showCancelError").click();
				}
			}
			
		},
		error : function() {
			alert("请求出错");
		}
	})
}

function reloadPage(examId,basePath){
	// 刷新页面
	location.href = basePath + "exam/company/" + examId+"/drivers/signup";
}

function getSignupDrivers(basePath, examId,orgId) {
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
				location.href = basePath + "exam/company/" + examId+"/drivers/signup";
			} else {
				alert(data.error);
			}
		}
	});
}

function changePageForSignup() {
	if (page == -1) {
		// 用户输入页面
		page = $("#page_textForSignup").val();
		pageNumForSignup = $("#pageNumForSignup").val();
		if (page > pageNumForSignup || page <= 0) {
			// 跳页不合法
			return false;
		}
	}
	$("#pageForSignup").val(page);

	// 提交表单，不再次赋值搜索关键字，不然跳页可能不正常
	$("#data").submit();
}

function submitForSignup() {
	// 获取上一次的每页行数
	var rowsPre = $("#rowsForSignup").val();

	// 获取当前每页行数
	var rows = $("#rows_inputForSignup").val();

	$("#rowsForSignup").val(rows);

	// 如果每页行数变化，跳转到第一页
	if (rowsPre != rows) {
		$("#page").val(1);
	}
	// 获取搜索关键词
	var searchKey = $("#search_textForSignup").val();
	$("#searchKeyForSignup").val(searchKey);

	// 表单提交
	$("#data").submit();
}

/**
 * 改变未报名驾驶员筛选状态
 */
function changeSignupStatus() {
	var signupStatus = "";
	$('input[name="signupStatusCheckbox"]:checked').each(function() {
		signupStatus += $(this).val() + ";";
	});
	console.log(signupStatus);
	$("#signupStatus").val(signupStatus);
	//提交表单
	$("#data").submit();
}

$().ready(function(){
	//被选择的状态需要被选中
	var signupStatus=$("#signupStatus").val();
	var status=signupStatus.split(";");
	$('input[name="signupStatusCheckbox"]').each(function() {
		for(var i=0;i<status.length;i++){
			if(status[i]==$(this).val()){
				$(this).attr("checked", true);
			}
		}
	});
});