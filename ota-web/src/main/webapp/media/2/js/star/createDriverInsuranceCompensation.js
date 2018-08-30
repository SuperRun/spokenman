function submit(){
	
	$("#driverIdError").html("");
	$("#carNoError").html("");
	$("#occurePlaceError").html("");
	$("#injuredNumError").html("");
	$("#deadNumError").html("");
	$("#compensationFeeError").html("");
	
	if(isNull($("#carNo").val())){
		$("#carNoError").html("请选择车牌号！");
		$("#closeButton").click();
		return false;
		
	}
	
	if(isNull($("#occureTime").val())){
		$("#occureTime").focus();
		$("#closeButton").click();
		return false;
		
	}
	
	if(isNull($("#occurePlace").val())){
		$("#occurePlaceError").html("请填写事故地点！");
		$("#closeButton").click();
		return false;
	}
	
	if(isNull($("#injuredNum").val())){
		$("#injuredNumError").html("请填写受伤人数！");
		$("#closeButton").click();
		return false;
	}
	
	if(isNull($("#deadNum").val())){
		$("#deadNumError").html("请填写死亡人数！");
		$("#closeButton").click();
		return false;
	}
	
	if(isNull($("#compensationFee").val())){
		$("#compensationFeeError").html("请填写理赔费用！");
		$("#closeButton").click();
		return false;
	}
	
	if(isNull($("input[name='select_user']:checked").val())){
		$("#driverIdError").html("请选择责任驾驶员！");
		$("#closeButton").click();
		return false;
	}
	
	$("#driverId").val($("input[name='select_user']:checked").val());
	
	$("#data").submit();
	
	
}

function isNull(obj){
	return (obj==null||obj.length<=0);
}