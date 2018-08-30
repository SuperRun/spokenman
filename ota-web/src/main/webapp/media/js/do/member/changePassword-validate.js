$().ready(function () {
    $('#formChangePw').validate({
        highlight: function (element, errorClass, validClass) {
            console.log($(element).parent());
            $(element).parent().addClass(errorClass).removeClass(validClass);
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parent().addClass(validClass).removeClass(errorClass);
        },
        validClass: 'has-success',
        errorClass: 'has-error',
        rules:{
            oldPassword:{
                required:true,
                minlength:6,
                remote:{
                    type:'POST',
                    url:path+"/member/verifypwd"
                }
            },
            newpw1:{
                minlength:6
            },
            newpw2:{
                equalTo:'#newpw1'
            }
        },
        messages:{
            oldPassword:{
                required:'未输入',
                minlength:'6位及以上',
                remote:'旧密码错误'
            },
            newpw1:{
                minlength:'6位及以上'
            },
            newpw2:{
                equalTo:'两次输入不同'
            }
        }
    });
});