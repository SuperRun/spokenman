
function pass(id) {
	$.ajax({
		url : path+'/driver/'+id+'/pass',
		type : 'post',
		dataType : 'json',
		data : {id : id},
		success : function(rd){
			if(rd){
				$("#passBtn").css("display", "none");
				$("#unpassBtn").css("display", "none");
				alert("操作成功");

			}

		}
	})
}

function unpass(id) {
	$.ajax({
		url : path+'/driver/'+id+'/pass',
		type : 'delete',
		dataType : 'json',
		data : {id : id},
		success : function(rd){
			if(rd){
				$("#passBtn").css("display", "none");
				$("#unpassBtn").css("display", "none");
				alert("操作成功");

			}

		}
	})
}



