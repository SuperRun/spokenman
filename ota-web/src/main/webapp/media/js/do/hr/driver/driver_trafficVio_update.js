$(document).ready(function () {
	$('form').validate({
		rules : {
			policeId : {
				required: true,
				number: true
			},
			speed : {
				required: true,
				number: true
			},
			deathNum : {
				required: true,
				number: true
			},
			injuredNum : {
				required: true,
				number: true
			},
			occurTime : {
				required: true,
				dateISO:true
			},
			start : {
				required: true
			}
		},
		messages: {
			policeId : {
				required: "不能为空",
				number: "必须为数字"
			},
			speed : {
				required: "不能为空",
				number: "必须为数字"
			},
			deathNum : {
				required: "不能为空",
				number: "必须为数字"
			},
			injuredNum : {
				required: "不能为空",
				number: "必须为数字"
			},
			occurTime : {
				required: "不能为空",
				dateISO: "日期不正确"
			},
			start : {
				required: "不能为空"
			}
		},
		errorClass: 'has-error',
        validClass: 'has-success',
        submitHandler: function () {
        	submits();
        }

	})
})

function submits(){

	var trafficVioType = $("#trafficVioType").val();
	var policeId = $("#policeId").val();
	var occurPlace = $("#occurPlace").val();
	var occurTime = $("#occurTime").val();
	var speed = $("#speed").val();
	var deathNum = $("#deathNum").val();
	var injuredNum = $("#injuredNum").val();
	var data1 = $("#data1").val();
	var data2 = $("#data2").val();
	var data3 = $("#data3").val();
	var data4 = $("#data4").val();
	var data5 = $("#data5").val();
	var remark = $("#remark").val();

	if(notNull(trafficVioType)){$("#trafficVioType").focus();}
	else if(notNull(policeId)){$("#policeId").focus();}
	else {
		$.ajax({
			url:'update',
			type:'post',
			dataType:'json',
			data:{
				trafficVioType : trafficVioType,
				policeId : policeId,
				occurPlace : occurPlace,
				occurTimeStr : occurTime,
				speed : speed,
				deathNum : deathNum,
				injuredNum : injuredNum,
				data1 : data1,
				data2 : data2,
				data3 : data3,
				data4 : data4,
				data5 : data5,
				remark : remark,
			},
			success : function(rd) {
				if(rd)
					alert("修改成功");
					window.location.href=path+"/driver/"+driverId+"/trafficVio";
			}
		})


	}

}

function notNull(obc) {
	return (obc == null || obc.length <= 0);
}
