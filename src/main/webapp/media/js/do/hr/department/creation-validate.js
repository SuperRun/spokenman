$('#' + department.ids.creation.form).validate({
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
            maxlength: 50
        },
        description: {
            maxlength: 250
        }
    },
    messages: {
        name: {
            required: '请输入部门名称',
            maxlength: '请输入50字以下的名称'
        },
        description: {
            maxlength: '请输入250字以下的描述'
        }
    }
});