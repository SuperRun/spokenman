function submits(){

	var registrationNumber = $("#registrationNumber").val();
	var productionTime = $("#productionTime").val();
	var buyTime = $("#buyTime").val();
	var type = $("#type").val();
	var specification = $("#specification").val();
	var selfWeight = $("#selfWeight").val();
	var maxWeight = $("#maxWeight").val();
	var org = $("#org").val();
	var driver = $("#driver").val();

	console.log(productionTime+"----------99----"+org + driver);

	if(notNull(registrationNumber)){$("#registrationNumber").focus();}
	else if(notNull(org)){alert("请选择车企")}
	else if(notNull(driver)){$("#driver").focus();}
	else if(notNull(type)){$("#type").focus();}
	else {
		$.ajax({
			url:'update',
			type:'post',
			dataType:'json',
			data:{
				registrationNumber : registrationNumber,
				orgId : org,
				driverId : driver,
				productionTimeStr : productionTime,
				buyTimeStr : buyTime,
				type : type,
				specification : specification,
				selfWeight : selfWeight,
				maxWeight : maxWeight

			},
			success : function(rd) {
				if(rd)
					alert("添加成功");
				window.location.href=path+"/hr/vehicle";
			}
		})


	}

}


function notNull(obc) {
	return (obc == null || obc.length <= 0);
}

function getVehicleDriver() {
	var id = $("#org").val();
	console.log("---in is cli"+id);
	if (!notNull(id)) {
		$.ajax({
			url: path + '/hr/vehicle/getVehicleDriver/' + id,
			type: 'post',
			dataType: 'json',
			data: {
				id: id
			},
			success: function (rd) {
				if (rd.success) {
					vue.drivers = rd.data;

				}
			}

		})
	}
}

var driverSel = {'id':'', 'name':''};

var vue = new Vue({
	el: '#search_form',
	data: {
		drivers: [{
			id: '',
			name: ''
		}]
	}
});

