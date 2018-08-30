
function pass(type, id) {
	$.ajax({
		url : path+'/driver/'+ type +'/'+id+'/pass',
		type : 'post',
		dataType : 'json',
		data : {id : id},
		success : function(rd){
			if(rd){
				alert("操作成功");
				window.location.reload();
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



