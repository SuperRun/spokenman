
function getCompensationDetail(compensationId,basePath){
	$.ajax({
		url:basePath+"compensation/confirm/"+compensationId+"/detail",
		success:function(data){
			if(data.success){
				var compensation=data.data;
				//公司名称
				$("#companyName").html(compensation.insuranceCompany);
				//车牌号
				$("#carNo").html(compensation.carNo);
				//理赔费
				$("#compensationFee").html(compensation.compensationFee);
				//事故事件
				$("#occureTime").html(compensation.occureTime);
				//受伤人数
				$("#injureNum").html(compensation.injureNum);
				//死亡人数
				$("#deadNum").html(compensation.deadNum);
				//录入时间
				$("#inputTime").html(compensation.inputTime);
			}else{
				alert("获取详情失败");
			}
		}
	})
}

function conveyCompensationIdForConfirm(compensationId){
	$("#compensationIdForConfirm").val(compensationId);
	console.log($("#compensationIdForConfirm").val());
}

function confirmCompensation(basePath){
	var driverId=$("input[name='select_user']:checked").val();
	console.log(driverId);
	console.log($("input[name='data1']:checked").val());
	console.log($("input[name='data2']:checked").val());
	console.log($("input[name='data3']:checked").val());
	console.log($("input[name='data4']:checked").val());
	console.log($("input[name='data5']:checked").val());
	console.log($("input[name='trafficViolationType']:checked").val());
	$.ajax({
		url:basePath+"compensation/confirm/"+$("#compensationIdForConfirm").val(),
		type:'POST',
		data:{
			driverId:$("input[name='select_user']:checked").val(),
			data1:$("input[name='data1']:checked").val(),
			data2:$("input[name='data2']:checked").val(),
			data3:$("input[name='data3']:checked").val(),
			data4:$("input[name='data4']:checked").val(),
			data5:$("input[name='data5']:checked").val(),
			trafficViolationType:$("input[name='trafficViolationType']:checked").val()
		},
		success:function(data){
			if(data.success){
				var driverId=data.data;
				location.href=basePath+"star/"+driverId;
			}else{
				alert("认定失败");
			}
		}
	})
}