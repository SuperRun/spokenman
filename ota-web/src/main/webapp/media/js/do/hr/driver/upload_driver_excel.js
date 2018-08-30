function submits(){

	var excelSrc = $('#imgPreview').attr('src');
	//var excelSrc = "uploadFile/hr/driver/excel/12_1478424737050.xlsx";
	console.log(excelSrc + "-------------------------");
	var name = $("#name").val();
	if(notNull(excelSrc)){$("#photoCover").focus();}
	else {
		$.ajax({
			url:'loadExcel',
			type:'post',
			dataType:'json',
			data:{
				name : name,
				excelPath : excelSrc
			},
			success : function(rd) {
				if(rd.success) {
					alert("添加成功");
					window.location.href=path+"/driver";
				} else {
					alert(rd.errorInfo);
				}

			}
		})


	}

}

function notNull(obc) {
	return (obc == null || obc.length <= 0);
}
