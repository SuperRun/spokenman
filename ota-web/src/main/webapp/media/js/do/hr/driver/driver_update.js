function check_mobile(){
	var mobile = $("#mobile").val();
	var mobile_hint = $("#mobile_hint");

	if(mobile.length != 11){
		mobile_hint.html("请输入正确位数手机号");
		mobile_hint.show();
		return false;
	} else {
		//var j = 1;
		for (var i = 0; i < mobile.length; i++) {
			if(mobile.charAt(i)<"0" || mobile.charAt(i)>"9"){
				mobile_hint.html("手机号必须为纯数字");
				mobile_hint.show();
				return false;
			}
		}
	}

	//$.ajax({
	//	url:'../check/mob/'+mobile,
	//	type:'get',
	//	dataType:'json',
	//	//data:{sfzNo:sfz_no},
	//	success : function(rd) {
	//		if (rd) {
	//			mobile_hint.hide();
	//		} else {
	//			mobile_hint.html("手机号存在");
	//			mobile_hint.show();
	//		}
	//	},
	//	error : function(r){
	//
	//	}
	//});

	mobile_hint.hide();
	return true;



}

function check_sfz_no(){
	var sfz_no = $("#sfz_no").val();
	var sfz_no_hint = $("#sfz_no_hint");
	if(sfz_no.length != 18){
		sfz_no_hint.html("请输入正确位数身份证号");
		sfz_no_hint.show();
		return false;
	} else {
		//var j = 1;
		for (var i = 0; i < sfz_no.length; i++) {
			if(sfz_no.charAt(i)<"0" || sfz_no.charAt(i)>"9"){
				sfz_no_hint.html("身份证号必须为纯数字");
				sfz_no_hint.show();
				return false;
			}
		}
	}

	//$.ajax({
	//	url: '../check/sfz/' + sfz_no,
	//	type:'get',
	//	dataType:'json',
	//	//data:{sfzNo:sfz_no},
	//	success : function(rd) {
	//		if (rd) {
	//			sfz_no_hint.hide();
	//		} else {
	//			sfz_no_hint.html("身份证号存在");
	//			sfz_no_hint.show();
	//		}
	//	},
	//	error : function(r){
	//
	//	}
	//});


	sfz_no_hint.hide();

	return true;




}




function submits(){

	var name = $("#name").val();
	var gender = $("#gender").val();
	var birthplace = $("#birthplace").val();
	var sfz_no = $("#sfz_no").val();
	var mobile = $("#mobile").val();
	var org = $("#org").val();
	if (notNull(org)) {
		org = $("#orgo").val();
	}
	if(notNull(name)){$("#name").focus();}
	else if(notNull(gender)){$("#gender").focus();}
	else if(notNull(birthplace)){$("#birthplace").focus();}
	else if(notNull(sfz_no)){$("#sfz_no").focus();}
	else if(notNull(mobile)){$("#mobile").focus();}
	else if(notNull(org)){alert("未选择车企");}
	else if(!check_sfz_no()){$("#sfz_no").focus();}
	else if(!check_mobile()){$("#mobile").focus();}
	else {
		console.log("-----*-"+name+"---"+gender+"---"+birthplace+"---"+sfz_no+"---"+mobile+"---"+org);

		$.ajax({
			url:'update',
			type:'post',
			dataType:'json',
			data:{
				name : name,
				gender : gender,
				birthPlace : birthplace,
				sfzNo : sfz_no,
				mobile : mobile,
				org_id : org,
				
				status : $("#status").val(),
				photo : $('#imgPreview').attr('src'),
				//driveLincencePhoto : $('#imgPreview2').attr('src'),
				//safeCentificatePhoto : $('#imgPreview3').attr('src'),
				
				address : $("#address").val(),
				postcode : $("#postcode").val(),
				email : $("#email").val(),
				emergencyContact : $("#emergencyContact").val(),
				emergencyContactMobile : $("#emergencyContactMobile").val(),
				driveLicenceNo : $("#driveLicenceNo").val(),
				driveLicenceStartTimeStr : $("#driveLicenceStartTime").val(),
				driveLicenceEndTimeStr : $("#driveLicenceEndTime").val(),
				safeCentificateNo : $("#safeCentificateNo").val(),
				safeCentificateStartTimeStr : $("#safeCentificateStartTime").val(),
				safeCentificateEndTimeStr : $("#safeCentificateEndTime").val()
				
				
			},
			success : function(rd) {
				if(rd)
					alert("修改成功");
				window.location.href=path+"/driver";
			}
		})


	}

}

function notNull(obc) {
	return (obc == null || obc.length <= 0);
}

//function getOrg(){
//	var org = $("#org");
//	var orgo = $("#orgo");
//
//	$.ajax({
//		url : path+'/'+'/organization/type/'+$("#orgType").val(),
//		type:'get',
//		dataType:'json',
//		/*data:{
//		 input:orgType.val()
//		 },*/
//		success : function(rd) {
//			console.log(rd);
//			if(rd.success) {
//				var list = rd.data;
//				var str = '';
//				orgo.css("display", "none");
//				org.show();
//				for (var i = 0; i < list.length; i++){
//					str += ('<option value="' + list[i].id + '">'
//					+ list[i].name + '</option>');
//				}
//				org.html(str);
//			} else {
//				console.log("return data 有错");
//			}
//
//		},
//		error : function(r) {
//			console.log("r");
//		}
//	})
//}