function getDriverDetail(examId,driverId,basePath){
	$.ajax({
		url:basePath+"exam/company/"+examId+"/"+driverId+"/detail",
		type:'GET',
		success:function(data){
			console.log(data);
			if(data.success){
				var driver=data.data;
				//报名时间
				if(driver.signupTime==null||driver.signupTime==""){
					$("#driverSignupTime").parent().hide();
				}else{
					$("#driverSignupTime").parent().show();
					$("#driverSignupTime").html(driver.signupTime);
				}
				//参加考试状态
				if(driver.takeExamStatus==null||driver.takeExamStatus==""){
					$("#driverTakeExamStatus").parent().hide();
				}else{
					$("#driverTakeExamStatus").parent().show();
					$("#driverTakeExamStatus").html(driver.takeExamStatus);
				}
				//考试成绩
				if(driver.score==null||driver.score==""){
					$("#driverExamScore").parent().hide();
				}else{
					$("#driverExamScore").parent().show();
					$("#driverExamScore").html(driver.score);
				}
				//是否通过
				if(driver.ifPass==null){
					$("#driverIfPass").parent().hide();
				}else{
					$("#driverIfPass").parent().show();
					if(driver.ifPass){
						$("#driverIfPass").html("通过");
					}else{
						$("#driverIfPass").html("未通过");
					}
				}
				
				//证书号
				if(driver.certificateNo==null||driver.certificateNo==""){
					$("#driverCertificateNo").parent().hide();
				}else{
					$("#driverCertificateNo").parent().show();
					$("#driverCertificateNo").html(driver.certificateNo);
				}
				
				//证书照片
				if(driver.certificate==null||driver.certificate==""){
					$("#driverCertificatePhoto").parent().hide();
				}else{
					$("#driverCertificatePhoto").parent().show();
					$("#driverCertificatePhoto").attr('src',basePath+driver.certificate);
				}
				
			}else{
				alert("获取详情失败");
			}
		},
		error:function(){alert("请求失败!");}
	})
}