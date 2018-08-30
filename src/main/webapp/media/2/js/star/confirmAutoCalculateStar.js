$(".affirm_btn").on('click', function() {

});

function confirmDriverStar(event) {
	var btn = $(event.target).next();
	btn.show();
	$(event.target).hide();
}

function confirmDriverStarAgain(driverStarId, basePath, event) {
	console.log(driverStarId);
	$.ajax({
		url:basePath+"compensation/confirm/"+driverStarId+"/confirm",
		type:'POST',
		success:function(data){
			if(data.success){
				var status_container = $(event.target).parent();
				status_container.empty();
				label = $('<label>已保存</label>').appendTo($(status_container));
				label.addClass('label-success').addClass('label');
			}else{
				alert("确认失败");
			}
		},
		error:function(){alert("请求失败");}
	})
}
