function changeTrainingStatus(){
	var status=$("#trainingStatusSelect").val();
	console.log(status);
	$("#status").val(status);
	$("#data").submit();
}

function redirectToTrainingDrivers(trainingId,basePath){
	location.href=basePath+"training/company/"+trainingId+"/drivers";
}

function redirectToTrainingDriversSignup(trainingId,basePath){
	location.href=basePath+"training/company/"+trainingId+"/drivers/signup";
}