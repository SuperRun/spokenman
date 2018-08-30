
function examLoginCheck(basePath){
	var sfz=$("#id_num").val();
	if(sfz==null||sfz==""){
		$("#loginError").html("请输入身份证号");
		$("#loginError").show(1000);
		return false;
	}
	console.log(sfz);
	$.ajax({
		url:basePath+"exam/driver/login",
		type:'POST',
		data:{
			sfzNo:$("#id_num").val()
		},
		success:function(data){
			if(data.success){
				console.log(data);
				var formAction=basePath+"exam/driver/examing/execution/"+data.data;
				$("#examingForm").attr("action",formAction);
				$("#examingForm").submit();
			}else{
				$("#loginError").hide();
				$("#loginError").html(data.error);
				$("#loginError").show(1000);
				return false;
			}
		},
		error:function(){alert("请求出错");}
	});
}