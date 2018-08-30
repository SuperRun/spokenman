var common = {
	validate : {
		input : function(inputElement, len) {
			if (inputElement && inputElement.val().length >= len) {
				return true;
			}
			return false;
		}
	},
	alertMsg : function(boxId, msg, clazz) {
		var msgBox = $('#' + boxId);
		if (!msgBox.is(":hidden")) {
			msgBox.hide(100);
		}
		msgBox.attr('class',clazz);
		msgBox.html(msg);
		msgBox.show(200);
	}
};
var login = {
	url : {
		loginReq : path + '/member/login',
		index : path + '/member/index'
	},
	alertMsg : {
		login : function(msg, clazz) {
			common.alertMsg('msgBox', msg, clazz);
		},
		phone : function(msg, clazz) {
			common.alertMsg('msgPhone', msg, clazz);
		},
		password : function(msg, clazz) {
			common.alertMsg('msgPassword', msg, clazz);
		},

	},
	funcs : {
		login : function() {
			var usernameInput = $('#username');
			var passwordInput = $('#password');
			if (!common.validate.input(usernameInput, 5)) {
				login.alertMsg.phone('用户名输入不正确', 'red');
				return;
			}

			if (!common.validate.input(passwordInput, 6)) {
				login.alertMsg.password('密码长度必须大于6位', 'red');
				return;
			}

			$.ajax({
				url : login.url.loginReq,
				type : 'POST',
                dataType : 'json',
                data:{
                	username:usernameInput.val(),
					password :passwordInput.val()
                },
                success:function(r){
                	console.log(r);
                	if(r.success){
                        login.alertMsg.login("登录成功", 'green');
                		window.location=login.url.index;
                	}else{
                		login.alertMsg.login(r.error,'red');
                	}
                },
                error:function(r){
                	console.log(r);
            		login.alertMsg.login(r.status+' '+r.statusText,'red');
                }
			});
		}
	}
};

$(document).ready(function(){
	var loginBtn = $('#loginBtn');
	loginBtn.click(function(){
		login.funcs.login();
	});
});
