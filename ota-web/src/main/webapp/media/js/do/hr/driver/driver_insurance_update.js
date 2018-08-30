function submits(){

	var insuranceType = $("#insuranceType").val();
	var insuranceId = $("#insuranceId").val();
	var compensationId = $("#compensationId").val();
	var insuranceCompany = $("#insuranceCompany").val();
	var policeId = $("#policeId").val();
	var carNo = $("#carNo").val();
	var compensationFee = $("#compensationFee").val();
	var remark = $("#remark").val();


	if(notNull(insuranceType)){$("#insuranceType").focus();}
	else {
		
		if(notNull(compensationFee))
			compensationFee = 0;

		$.ajax({
			url:'update',
			type:'post',
			dataType:'json',
			data:{
				insuranceType : insuranceType,
				insuranceId : insuranceId,
				compensationId : compensationId,
				insuranceCompany : insuranceCompany,
				policeId : policeId,
				carNo : carNo,
				compensationFee : compensationFee,
				remark : remark
			},
			success : function(rd) {
				if(rd)
					alert("修改成功");
					window.location.href=path+"/driver/"+driverId+"/insurance";
			}
		})


	}

}

function notNull(obc) {
	return (obc == null || obc.length <= 0);
}
