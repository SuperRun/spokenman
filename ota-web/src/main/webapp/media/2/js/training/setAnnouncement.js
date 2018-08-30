$(document).ready(function() {
	$('form').validate({
		rules : {
			title : {
				required : true
			}
		},
		messages : {
			title : {
				required : "不能为空"
			}
		},
		errorClass : 'has-error',
		validClass : 'has-success',
		submitHandler : function() {
			submits();
		}

	});
});

function submits() {

	// var orgTypes = "";
	// $('input:checkbox[name=orgType]:checked').each(function (i) {
	// if (0 == i) {
	// orgTypes = $(this).val();
	// } else {
	// orgTypes += ("," + $(this).val());
	// }
	// });
	if (notNull($("#title").val())) {
		$("#title").focus();
	} else {
		console.log($("#trainingId").val());
		$.ajax({
			url : path + '/training/' + $("#trainingId").val()
					+ '/announcement',
			type : 'post',
			dataType : 'json',
			data : {
				title : $("#title").val(),
				text : $("#body").val(),
				closureDateStr : $("#startTm").val(),
				attachment : $("#fileName").val()
			// attachment : $("#filePreview").attr("src"),
			// type : $("#annType").val()
			},
			success : function(rd) {
				if (rd.success) {
					alert("添加成功");
					window.location.href = path + "/training/home";
				} else {
					alert("添加失败");
				}

			}
		});
	}

}

function notNull(obc) {
	return (obc == null || obc.length <= 0);
}
