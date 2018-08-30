<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chrome=1">
<%@ include file="../basic_includes.jsp"%>


<title>浙江省散装水泥专用车辆驾驶员系统</title>
<script>
    window.onload = function () {
    var height = window.screen.height;
    $('body').css('height',height);
    };
</script>
</head>
<body style="background-image: url('<%=path %>/media/images/driverLogin.jpg');background-repeat: no-repeat;background-size: cover;">

<%@ include file="../include/_driver_login_header.jsp"%>


<div class="container" id="content">
    <div class="row">
        <div class="col-md-5">
        </div>
        <div class="col-md-3" id="login">
            <div class="driverLogin-header"><h2>驾驶员登录</h2></div>
            <div class="form-horizontal" id="login_form">
                <div class="form-group">
                    <input type="text" class="form-control userName" id="username" placeholder="手机号"
                           value="18800008888">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control password" id="password" placeholder="密码/默认身份证后六位"
                           value="123456">
                </div>
                <div class="form-group">
                    <button id="loginBtn" class="btn btn-primary login_btn" style="width: 100%;">登录</button>
                </div>
            </div>
            <div id="login_error" style="display: none;">登录成功</div>
            <!--</div>-->
            <!--</div>-->
        </div>
        <div class="col-md-4"></div>
    </div>
</div>


<a role="button" href="<%=path%>/member/login" id="staffBtn"><img src="<%=path %>/media/images/staff.png">员工登录</a>
<%@ include file="../include/_driver_login_footer.jsp"%>

</body>
<script src="<%=path%>/media/js/do/common.js"></script>
<script src="<%=path%>/media/js/do/driver/login.js"></script>
</html>
