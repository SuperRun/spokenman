/**
 * 为删除试卷传递id
 */
function conveyPaperId(paperId){
	$("#paperId").val(paperId);
}

function deletePaper(basePath){
	$.ajax({
		url:basePath+"paper/member/"+$("#paperId").val(),
		type:'DELETE',
		success:function(data){
			if(data.success){
				location.href=basePath+"paper/member/list";
			}else{
				alert("删除失败");
			}
			
		},
		error:function(){alert("请求出错");}
	})
}