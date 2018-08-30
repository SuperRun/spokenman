var memberLogin = {
    url: {
        loginReq : path + '/member/login',
        index : path + '/member'
    },
    alertMsg: {
        login: function (msg, clazz) {
            common.alertMsg('login_error', msg, clazz);
        },
        phone: function (msg, clazz) {
            common.alertMsg('login_error', msg, clazz);
        },
        password: function (msg, clazz) {
            common.alertMsg('login_error', msg, clazz);
        }
    },
    funcs: {
        login: function () {
            var usernameInput = $('#username');
            var passwordInput = $('#password');
            if (!common.validate.input(usernameInput, 1)) {
                memberLogin.alertMsg.phone('用户名输入不正确', '');
                return;
            }

            if (!common.validate.input(passwordInput, 6)) {
                memberLogin.alertMsg.password('密码长度必须大于6位', '');
                return;
            }

            $.ajax({
                url: memberLogin.url.loginReq,
                type: 'POST',
                dataType: 'json',
                data: {
                    username: usernameInput.val(),
                    password: passwordInput.val()
                },
                success: function (r) {
                    console.log(r);
                    if (r.success) {
                        memberLogin.alertMsg.login(r.data.info, '');
                        window.location = memberLogin.url.index;
                    } else {
                        memberLogin.alertMsg.login(r.error, '');
                    }
                },
                error: function (r) {
                    console.log(r);
                    memberLogin.alertMsg.login(r.status + ' ' + r.statusText, '');
                }
            });
        }
    }
};

$(document).ready(function () {
    var loginBtn = $('#loginBtn');
    loginBtn.click(function () {
        memberLogin.funcs.login();
    });
});
