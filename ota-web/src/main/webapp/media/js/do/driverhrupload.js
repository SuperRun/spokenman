/* 
 * 用法见/driverhr/src/main/webapp/WEB-INF/jsp/hr/organization/creation.jsp
 */
function commonUpload(realFileInputE, localPathShowE, realFromE, serverPathE,
		imgPreviewE, path) {
	// realFileInputE真实的文件上传input （<input type="file">）
	// localPathShowE 对于用户来说的本地路径显示控件，显示用户选择文件的目录
	// realFromE 真实的文件上传的input所在的from
	// serverPathE 上传完成后的服务器相对路径，用于存储到数据库
	// imgPreviewE 图片预览的控件 (<img >)
	// path - > request中的contextPath
	realFileInputE.change(function() {
		// 设置用户显示的路径
		localPathShowE.val(realFileInputE.val());
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
			},
			error : function(r) {
				console.log('fail');
				console.log(r);
			}
		});
	});
}

