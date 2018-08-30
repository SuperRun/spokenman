$().ready(function () {

    $('#search_form').validate({
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
            searchKey: "required"
        },
        messages: {
            searchKey: "未输入"
        },
        submitHandler: function (form) {
            //$('#form_rows').val($('#record_num').val());
            form.submit();
        }
    });


    $("#pageToForm").validate({
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
            page: {
                required: true,
                digits: true,
                range: [1, pageSum]
            }
        },
        messages: {
            page: "请输入1-" + pageSum + "整数"
        }
    });
})
;