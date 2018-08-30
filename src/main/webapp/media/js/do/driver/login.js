var login = {
    url: {
        loginReq: path + '/driver/login',
        index: path + '/driver/index'
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
            if (!common.validate.input(usernameInput, 5)) {
                login.alertMsg.phone('手机号输入不正确', 'red');
                return;
            }

            if (!common.validate.input(passwordInput, 6)) {
                login.alertMsg.password('密码长度必须大于6位', 'red');
                return;
            }

            $.ajax({
                url: login.url.loginReq,
                type: 'POST',
                dataType: 'json',
                data: {
                    username: usernameInput.val(),
                    password: passwordInput.val()
                },
                success: function (r) {
                    console.log(r);
                    if (r.success) {
                        login.alertMsg.login(r.data.info, '');
                        window.location = login.url.index;
                    } else {
                        login.alertMsg.login(r.error, '');
                    }
                },
                error: function (r) {
                    console.log(r);
                    login.alertMsg.login(r.status + ' ' + r.statusText, '');
                }
            });
        }
    }
};

$(document).ready(function () {
    var loginBtn = $('#loginBtn');
    loginBtn.click(function () {
        login.funcs.login();
    });
});
