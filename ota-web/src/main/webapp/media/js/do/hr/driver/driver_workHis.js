function handle(method) {
	//<%=path %>/driver/${driver.id}/honour/${dh.id}/delete
	//console.log("------"+id+"---"+method);
	var id = $("#delId").val();
	$.ajax({
		url :  'workHis/'+id+'/' + method,
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
function unpass(id) {
	$.ajax({
		url : id + '/pass',
		type : 'delete',
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

function newPage(){
		var pageNow = parseInt($("#page_text").val());
		if (pageNow > pageNum) pageNow = pageNum;
		$("#pageNow").val(pageNow);
	$("#data").submit();
}


function submitss(){

	var name = $("#name").val();
	var gender = $("#gender").val();
	var birthplace = $("#birthplace").val();
	var sfz_no = $("#sfz_no").val();
	var mobile = $("#mobile").val();
	var org = $("#org").val();
console.log(name+"---"+gender+"---"+birthplace+"---"+sfz_no+"---"+mobile+"---"+org);
	if(notNull(name)){$("#name").focus();}
	else if(notNull(gender)){$("#gender").focus();}
	else if(notNull(birthplace)){$("#birthplace").focus();}
	else if(notNull(sfz_no)){$("#sfz_no").focus();}
	else if(notNull(mobile)){$("#mobile").focus();}
	else if(notNull(org)){$("#org").focus();}
	else {
		$.ajax({
			url:'create',
			type:'post',
			dataType:'json',
			data:{
				name : name,
				gender : gender,
				birthPlace : birthplace,
				sfzNo : sfz_no,
				mobile : mobile,
				org_id : org,
			},
			success : function(rd) {
				if(rd)
					alert("添加成功");
			}
		})


	}

}

function notNull(obc) {
	return (obc == null || obc.length <= 0);
}
