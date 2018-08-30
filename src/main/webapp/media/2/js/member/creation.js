/**
 * Created by dxb on 12/11/2016.
 */
var v = new Vue({
    el: '.content',
    data: {
        member: {
            sfzNo: null,
            phone: null,
            name: null,
            loginName: null
        },
        error: null
    },
    methods: {
        create: function () {

            if (!this.member.loginName) {
                setError('请输入登录名');
                return;
            }
            if (!this.member.name) {
                setError('请输入员工姓名');
                return;
            }
            if (!this.member.phone) {
                setError('请输入联系电话');
                return;
            }

            $.post(fns.ajax.path('/member'), this.member,
                function (r) {
                    if (r.success) {
                        location.href = path + "/organization/members";
                    } else {
                        setError(r.error);
                    }
                }
            )

        }

    }
});

function setError(info) {
    v.error = info;
}