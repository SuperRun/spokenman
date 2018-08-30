/**
 * 为一位驾驶员保存证书信息
 */
function certificateOneDriver(examId,driverId,basePath,event){
	//获取证书号
	var idStr="#certificate"+driverId;
	//获取证书照片
	var certificate=$(idStr).val();
	idStr="#imgUrl"+driverId;
	var certificatePhoto=$(idStr).val();
	if((certificate==""||certificate==null)&&(certificatePhoto==""||certificatePhoto==null)){
		return ;
	}
	$.ajax({
		url:basePath+"exam/member/certificate/"+examId+"/"+driverId,
		type:"POST",
		data:{
			certificate:$("#certificate"+driverId).val(),
			certificatePhoto:$("#imgUrl"+driverId).val()
		},
		success:function(data){
			console.log(event);
			var status_container = $(event.target).parent().next().next();
			status_container.empty();
			var label;
			if(data.success){
				label = $('<label>已保存</label>').appendTo($(status_container));
				label.addClass('label-success');
			}else{
				label = $('<label>未保存</label>').appendTo($(status_container));
				label.addClass('label-default');
			}
			label.addClass('label');
		},
		error:function(){alert("请求失败！");}
	});
}

/**
 * 为一位驾驶员保存证书信息
 */
function certificateOneDriverForPhotoChange(examId,driverId,basePath){
	//获取证书号
	var idStr="#certificate"+driverId;
	//获取证书照片
	var certificate=$(idStr).val();
	idStr="#imgUrl"+driverId;
	var certificatePhoto=$(idStr).val();
	if((certificate==""||certificate==null)&&(certificatePhoto==""||certificatePhoto==null)){
		return ;
	}
	$.ajax({
		url:basePath+"exam/member/certificate/"+examId+"/"+driverId,
		type:"POST",
		data:{
			certificate:$("#certificate"+driverId).val(),
			certificatePhoto:$("#imgUrl"+driverId).val()
		},
		success:function(data){
			var status_container = $("#status"+driverId);
			status_container.empty();
			var label;
			if(data.success){
				label = $('<label>已保存</label>').appendTo($(status_container));
				label.addClass('label-success');
			}else{
				label = $('<label>未保存</label>').appendTo($(status_container));
				label.addClass('label-default');
			}
			label.addClass('label');
		},
		error:function(){alert("请求失败！");}
	});
}

/*
 * 添加到上传按钮onclick事件，点击触发上传文件
 */
function commonUploadOnclick(realFileInputE, realFromE,
		serverPathE, imgPreviewE, path,examId,driverId) {
	console.log("startUpload");
	realFromE.ajaxSubmit({
		success : function(r) {
			// 转化成json
			console.log('success');
			console.log(r);
			if (typeof (r) != 'object')
				r = JSON.parse(r);
			console.log(r);
			serverPathE.val(r.files);
			imgPreviewE.attr('src', path + '/' + r.files);
			imgPreviewE.show(100);
			//保存信息
			path=path+"/";
			certificateOneDriverForPhotoChange(examId,driverId,path);
			
		},
		error : function(r) {
			console.log('fail');
			console.log(r);
		}
	});
}

/**
 * 点击图片更改模态框上传按钮信息
 * @param driverId
 * @param path
 */
function createUploadSubmitButtonOnPic(examId,basePath,driverId,path){
	$("#modalFooter").empty();
	var btn = $('<button>上传</button>').appendTo($("#modalFooter"));
	btn.addClass('btn');
	btn.addClass('btn-primary');
	btn.addClass('table_save');
	var realFileInputE = $('#certificatePhoto');
    var realFromE = $('#uploadCertificatePhotoForm');
    var saverPathEStr="#imgUrl"+driverId;
    var serverPathE = $(saverPathEStr);
    var imgPreviewEStr="#certificatePhotoPreview"+driverId;
    var imgPreviewE = $(imgPreviewEStr);
	btn.click(function() {
		//上传图片
		commonUploadOnclick(realFileInputE, realFromE,
				serverPathE, imgPreviewE, path,examId,driverId);
		
		
	});
	
	var close=$("<button data-dismiss='modal'>关闭</button>").appendTo($("#modalFooter"));
	close.addClass('btn');
	close.addClass('btn-default');
	close.addClass('table_save');
}

/**
 * 通过按钮上传，上传后需要隐藏按钮，显示图片
 * @param driverId
 * @param path
 * @param event
 */
function createUploadSubmitButton(examId,basePath,driverId,path,event){
	$("#modalFooter").empty();
	var btn = $('<button>上传</button>').appendTo($("#modalFooter"));
	btn.addClass('btn');
	btn.addClass('btn-primary');
	btn.addClass('table_save');
	var realFileInputE = $('#certificatePhoto');
    var realFromE = $('#uploadCertificatePhotoForm');
    var saverPathEStr="#imgUrl"+driverId;
    var serverPathE = $(saverPathEStr);
    var imgPreviewEStr="#certificatePhotoPreview"+driverId;
    var imgPreviewE = $(imgPreviewEStr);
	btn.click(function() {
		//上传图片文件
		commonUploadOnclick(realFileInputE, realFromE,
				serverPathE, imgPreviewE, path,examId,driverId);
		//隐藏上传图片按钮，显示图片
		$(event.target).hide();
		$(event.target).next().show();
	});
	
	var close=$("<button data-dismiss='modal'>关闭</button>").appendTo($("#modalFooter"));
	close.addClass('btn');
	close.addClass('btn-default');
	close.addClass('table_save');
}

/**
 * 证书录入完成提交
 */
function submitCertificate(){
	if(confirm("提交后将不可修改，请确认输入完成")){
		$("#submitCertificate").submit();
	}
	
}


