function commonUpload(realFormE, realFileInputE, localPathShowE, serverPathHidden, serverPathImg, serverPathA) {
    // realFormE 真实的上传表单
    // realFileInput 真实的文件上传控件 在realFormE中
    // localPathShowE 显示给用户看的被选文件
    // serverPathHidden 提交到后台的input
    // serverPathImg 预览图片
    // serverPathA 预览图片指向的大图
    realFileInputE.change(function () {
        // 设置用户显示的路径
        localPathShowE.val(realFileInputE.val());
        realFormE.ajaxSubmit({
            success: function (r) {
                // 转化成json
                if (typeof (r) != 'object') {
                    r = JSON.parse(r);
                }
                serverPathHidden.val(r.files);
                serverPathImg.attr('src', path + '/' + r.files);
                serverPathA.attr('href', path + '/' + r.files);

            }
        });
    });
}