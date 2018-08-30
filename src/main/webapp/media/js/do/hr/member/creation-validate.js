$.validator.addMethod('isLetterAndNum', function (value, element) {
    var tel = /^[A-Za-z0-9]+$/;
    return this.optional(element) || (tel.test(value));
}, "必须由英文字母和数字组成");

$().ready(function () {

    $('#organizationInfo').validate({
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
            name: {
                required: true,
                minlength: 2
            },
            loginName: {
                required: true,
                minlength: 5,
                maxlength: 50,
                isLetterAndNum: true,
                remote: {
                    url: creationOrganization.url.verifyLinkedPersonExsit(),
                    data: {
                        fanzhuan: true,
                        directorId: function () {
                            return $('#loginName').val();
                        }
                    }
                }
            },
            sfzNo: {
                required: true,
                rangelength: [15, 18]
            }
        },
        messages: {
            name: {
                required: '请输入姓名',
                minlength: '姓名至少两位'
            },
            loginName: {
                required: '请输入登录名',
                minlength: '登录名至少5位',
                maxlength: '登录名最多50位',
                isLetterAndNum: '登录名必须由英文或者数字组合而成',
                remote: '登录名已存在，请尝试其他'
            },
            sfzNo: {
                required: '请输入身份证',
                rangelength: '身份证号码长度应在15-18位'
            }
        }
    });
})
;