function getOrg(){
	
	var org = $("#org");
	
	$.ajax({
		url : path+'/'+'/organization/type/'+$("#orgType").val(),
		type:'get',
		dataType:'json',
		/*data:{
			input:orgType.val()
		},*/
		success : function(rd) {
			console.log(rd);
			if(rd.success) {
				var list = rd.data;
				var str = '';
				for (var i = 0; i < list.length; i++){
					str += ('<option value="' + list[i].id + '">'
					+ list[i].name + '</option>');
				}
				org.html(str);
			} else {
				console.log("return data 有错");
			}
			
		},
		error : function(r) {
			console.log("r");
		}
	
	
	
	})
	
}

function submits(){

	var rewardType = $("#rewardType").val();
	var org = $("#org").val();
	var remark = $("#remark").val();

	console.log("*****");

	if(notNull(rewardType)){$("#rewardType").focus();}
	else if(notNull(org)){$("#org").focus();}
	else {
		$.ajax({
			url:'create',
			type:'post',
			dataType:'json',
			data:{
				rewardType : rewardType,
				org_id : org,
				remark : remark,
			},
			success : function(rd) {
				if(rd){
					alert("添加成功");
					window.location.href=path+"/driver/"+driverId+"/reward";
				}
			}
		})


	}

}

function notNull(obc) {
	return (obc == null || obc.length <= 0);
}
