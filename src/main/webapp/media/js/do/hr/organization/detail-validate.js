$(document).ready(function () {
    $('#verifyFail').validate({
        highlight: function (element, errorClass, validClass) {
            console.log($(element).parent());
            $(element).parent().addClass(errorClass).removeClass(validClass);
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parent().addClass(validClass).removeClass(errorClass);
        },
        validClass: 'has-success',
        errorClass: 'has-error',
        rules: {
            reason: {
                required: true,
                minlength: 5,
                maxlength: 200
            }
        },
        messages: {
            reason: {

                required: '请输入内容',
                minlength: '请输入5个字以上审核不通过原因',
                maxlength: '字数限制200个'
            }
        }

    });


    $("#set_lp_form").validate({
        highlight: function (element, errorClass, validClass) {
            console.log($(element).parent());
            $(element).parent().addClass(errorClass).removeClass(validClass);
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parent().addClass(validClass).removeClass(errorClass);
        },
        validClass: 'has-success',
        errorClass: 'has-error',
        rules: {
            loginName: {
                required: true,
                remote: {
                    type: 'get',
                    url: creationOrganization.url.verifyLinkedPersonExsit(),
                    dataType: 'json',
                    data: {
                        directorId: function () {
                            return $("#loginName").val();
                        }
                    }
                }
            }
        },
        messages: {
            loginName: {
                required: '未输入',
                remote: '用户不存在'
            }
        },
        submitHandler: function () {
            $.ajax({
                url: path + "/organization/" + orgId + "/lp",
                type: 'POST',
                dataType: 'json',
                data: {
                    loginName: function () {
                        return $('#loginName').val();
                    }
                },
                success: function (r) {
                    if (r.success) {
                        alert('成功');
                        location.reload(true);
                    } else {
                        alert(r.error);
                    }

                }
            });
        }

    });

});
