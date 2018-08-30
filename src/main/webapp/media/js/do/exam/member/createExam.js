function createExam(){
	var flag=1;
	$("#examNameError").empty();
	$("#examNameError").hide();
	$("#examTypeError").empty();
	$("#examTypeError").hide();
	$("#signupStartTimeeError").empty();
	$("#signupStartTimeeError").hide();
	$("#signupEndTimeError").empty();
	$("#signupEndTimeError").hide();
	$("#examStartTimeError").empty();
	$("#examStartTimeError").hide();
	$("#examEndTimeError").empty();
	$("#examEndTimeError").hide();
	$("#durationError").empty();
	$("#durationError").hide();
	if($("#exam_name").val()==null||$("#exam_name").val()==""){
		$("#examNameError").show();
		$("#examNameError").html("请填写考试名称");
		flag=0;
	}
	if($("input[name='examType']:checked").val()==null||$("input[name='examType']:checked").val()==""){
		$("#examTypeError").show();
		$("#examTypeError").html("请选择考试类型");
		flag=0;
	}
	if($("#startTm").val()==null||$("#startTm").val()==""){
		if($("input[name='examType']:checked").val()==0){
			$("#signupStartTimeeError").show();
			$("#signupStartTimeeError").html("请填写报名开始时间");
			flag=0;
		}
		
	}
	if($("#endTm").val()==null||$("#endTm").val()==""){
		if($("input[name='examType']:checked").val()==0){
			$("#signupEndTimeError").show();
			$("#signupEndTimeError").html("请填写报名结束时间");
			flag=0;
		}
		
	}
	if($("#examStart").val()==null||$("#examStart").val()==""){
		if($("input[name='examType']:checked").val()==0){
			$("#examStartTimeError").show();
			$("#examStartTimeError").html("请填写考试开始时间");
			flag=0;
		}
	}
	if($("#examEnd").val()==null||$("#examEnd").val()==""){
		if($("input[name='examType']:checked").val()==0){
			$("#examEndTimeError").show();
			$("#examEndTimeError").html("请填写考试结束时间");
			flag=0;
		}
	}
	if($("input[name='examType']:checked").val()==1){
		if($("#exam_time").val()==null||$("#exam_time").val()==""){
			$("#durationError").show();
			$("#durationError").html("请填写考试时长");
			flag=0;
		}else if(!isNumber($("#exam_time").val())){
			$("#durationError").show();
			$("#durationError").html("考试时长格式不正确");
			flag=0;
		}
	}
	if(flag==0){
		return false;
	}else{
		$("#data").submit();
	}
}

function isNumber( s )
{
    var regu = "^[0-9]+$";
    var re = new RegExp(regu);
    if (s.search(re) != - 1) {
        return true;
    }
    else {
        return false;
    }
};