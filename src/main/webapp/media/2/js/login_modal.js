/**
 * 弹窗登陆的代码
 * Created by dxb on 12/10/2016.
 */


var v = new Vue({
    el: '#modal',
    data: {
        user: {
            username: null,
            password: null
        },
        error: null,
        changepwd: {
            oldPwd: null,
            newPwd: null,
            newPwd2: null,
            error: null
        }
    },
    methods: {
        login: function () {
            if (!this.user.username) {
                setError('请输入用户名');
            } else if (!this.user.password) {
                setError('请输入密码');
            } else {
                $.ajax({
                    url: fns.ajax.path("/member/login"),
                    type: 'post',
                    data: this.user,
                    success: function (r) {
                        if (!r.success) {
                            setError(r.error);
                        } else {
                            location.reload();
                        }
                    }
                })
            }

        },
        changePwd: function () {
            if (!this.changepwd.oldPwd) {
                setChangePwdError("请输入旧密码");
                return;
            }
            if (!this.changepwd.newPwd) {
                setChangePwdError("请输入新密码");
                return;
            } else if (this.changepwd.newPwd.length < 6) {
                setChangePwdError("新密码必须在6位以上");
                return;
            }
            if (!this.changepwd.newPwd2) {
                setChangePwdError("请输入确认密码");
                return;
            }
            if (this.changepwd.newPwd2 != this.changepwd.newPwd) {
                setChangePwdError("新密码和确认密码必须相同");
                return;
            }
            $.ajax({
                url: fns.ajax.path('/member/changepwd'),
                type: 'post',
                data: this.changepwd,
                success: function (r) {
                    if (r.success) {
                        setChangePwdError("修改成功");
                        $('#changePwd_modal').modal('hide');
                        resetChangePwdForm();

                    } else {
                        setChangePwdError(r.error);
                    }
                }
            });
        }
    }


});

function setError(info) {
    v.error = info;
}


function setChangePwdError(info) {
    v.changepwd.error = info;
}

function resetChangePwdForm() {
    v.changepwd.newPwd2 = null;
    v.changepwd.newPwd = null;
    v.changepwd.oldPwd = null;
    v.changepwd.error = null;
}

