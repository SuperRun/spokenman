/**
 * 车企导入
 * Created by dxb on 2016/12/10.
 */
var v = new Vue({
    el: '.content',
    data: {
        shown: {
            waiting: false
        },
        list: null

    },
    methods: {
        ana: function () {
            this.shown.waiting = true;
            $('#uploadexcelForm').ajaxSubmit({
                success: function (r) {
                    // 转化成json
                    if (typeof (r) != 'object') {
                        r = JSON.parse(r);
                    }
                    var path = r.files;
                    $.ajax({
                        url: fns.ajax.path("/organization/import/company/ana"),
                        type: 'post',
                        data: {
                            path: path
                        },
                        success: function (r) {
                            if (r.success) {
                                updateList(r.data);
                                hideWaiting();

                            } else {
                                fns.ajax.error(r);
                            }
                        }
                    })

                }
            });
        }
    }
});

function updateList(l) {
    v.list = l;
}

function hideWaiting() {
    v.shown.waiting = false;

}

$().ready(function () {
    $("#uploadexcel").change(function () {
        $('#showPath').val($(this).val());
    });
});