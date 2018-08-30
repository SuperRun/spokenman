  function nextPage(){
		var pageNow = parseInt($("#pageNow").val()) + 1;
		//var pageNow = ${pageNow} + 1;
		$("#pageNow").val(pageNow);
		$("#data").submit();
	}
	function prePage(){
		var pageNow = parseInt($("#pageNow").val()) - 1;
		$("#pageNow").val(pageNow);
		$("#data").submit();
	}
	
	function toPage(index){
		var pageNow = index;
		$("#pageNow").val(pageNow);
		$("#data").submit();
	}
  		function submit(){
			var rowsNow = document.getElementById("selectrow").value;
			$("#rowsNow").val(rowsNow);
			$("#data").submit();
		}
  	function newPage(){
  		var pageNow = parseInt($("#page_text").val());
  		if (pageNow > pageNum) pageNow = pageNum;
  		$("#pageNow").val(pageNow);
		$("#data").submit();
  	}


function search() {
	var keywd = $("#keywd").val();
	$("#keywdNow").val(keywd);
	$("#data").submit();
}
function sort(sortStr) {
	$("#sortNow").val(sortStr);
	$("#data").submit();
}
function pass(id) {
	$.ajax({
		url : id + '/pass',
		type : 'post',
		dataType : 'json',
		success : function(rd){
			if(rd){
				
				$("#data").submit();
				alert("操作成功");
			}

		}
	})
}
function deleteVehicle() {
	var id = $("#delId").val();
	$.ajax({
		url : 'vehicle/' + id + '/delete',
		type : 'get',
		dataType : 'json',
		success : function(rd){
			if(rd){
				
				$("#data").submit();
				alert("操作成功");
			}

		}
	})
}

function newRows() {
	var rowsNew = $("#selectrow").val();
	$("#pageNow").val(1);
	$("#rowsNow").val(rowsNew);
	$("#data").submit();
}

/*function newPage(){
	var pageNew = $("#selectrow").val();
	$("#pageNow").val(pageNew);
	$("#data").submit();
}*/



function notNull(obc) {
	return (obc == null || obc.length <= 0);
}
