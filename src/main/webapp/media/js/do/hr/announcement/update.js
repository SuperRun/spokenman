$(document).ready(function () {
	$('form').validate({
		rules : {
			title : {
				required: true
			}
		},
		messages: {
			title : {
				required: "不能为空"
			}
		},
		errorClass: 'has-error',
        validClass: 'has-success',
        submitHandler: function () {
        	submits();
        }

	});
});

function submits(){
	
    //var orgTypes = "";
    //$('input:checkbox[name=orgType]:checked').each(function (i) {
    //    if (0 == i) {
    //        orgTypes = $(this).val();
    //    } else {
    //        orgTypes += ("," + $(this).val());
    //    }
    //});
	if (notNull($("#title").val())) {
		$("#title").focus();
	} else {

		$.ajax({
			url : path+'/announcement/'+$("#annId").val() +'/update',
			type:'post',
			dataType:'json',
			data:{
				title : $("#title").val(),
				text : $("#body").val(),
				closureDateStr : $("#startTm").val(),
				attachment : $("#fileName").val()
				//attachment : $("#filePreview").attr("src"),
				//type : $("#annType").val()
			},
			success : function(rd) {
				if(rd)
					alert("修改成功");
					//window.location.href=path+"/driver/"+driverId+"/trafficVio";
					window.location.href=path+"/announcement/list";
			}
		});
	}

}

function notNull(obc) {
	return (obc == null || obc.length <= 0);
}
