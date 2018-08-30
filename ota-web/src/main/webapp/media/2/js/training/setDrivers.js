/**
 * 批量导入培训报名驾驶员
 * @param basePath
 * @param examId
 */
function getSignupDrivers(basePath, trainingId,orgId) {
	var source = "";
	$('input:checkbox[name=signupDriver]:checked').each(function() {
		var res = $(this).val();
		source += res + ";";
	});
	console.log(source);
	$("#signupDriversString").val(source);
	
	$.ajax({
		url : basePath + "training/" + trainingId + "/signup",
		type : 'POST',
		data : {
			signupDriversString : $("#signupDriversString").val()
		},
		success : function(data) {
			if (data.success) {
				location.href = basePath + "training/" + trainingId + "/signup/content?tid="+orgId;
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

/**
 * 传递驾驶员id
 * @param driverId
 */
function conveyDriverId(driverId){
	$("#cancelDriverId").val(driverId);
}

/**
 * 取消驾驶员培训资格
 * @param trainingId
 * @param basePath
 */
function confirmCancelDriver(trainingId,basePath,orgId){
	$.ajax({
		url:basePath+"training/"+trainingId+"/"+$("#cancelDriverId").val()+"/signup",
		type:'DELETE',
		success:function(data){
			if(data.success){
				location.href=basePath+"training/"+trainingId+"/signup/content?tid="+orgId;
			}else{
				alert("取消培训资格失败");
			}
		},
		error:function(){alert("请求失败");}
	})
}