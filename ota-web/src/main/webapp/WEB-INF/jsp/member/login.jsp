<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chrome=1">
    <%@ include file="../basic_includes.jsp" %>


    <title>基于B/S程序的无纸化网络考试系统</title>
    <script>
        window.onload = function () {
            var height = window.screen.height;
            $('body').css('height', height);
        };
    </script>
</head>
<%--<body style="background-image: url('<%=path %>/media/images/login.jpg');background-repeat: no-repeat;background-size: cover;">--%>
<body style="background-image: url('<%=path %>/media/images/login.jpg');background-repeat: no-repeat;background-size: cover;">

<%@ include file="../include/_driver_login_header.jsp" %>

<div class="container" id="content">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4" id="login">
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">员工登录</div>
                <div class="panel-body">
                    <div class="form-horizontal" id="login_form">
                        <div class="form-group">
                            <label for="username" class="col-sm-3 control-label">账号</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="username">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-3 control-label">密码</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control" id="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 text-center">
                                <button id="loginBtn" class="btn btn-primary login_btn">登录</button>
                            </div>
                        </div>
                    </div>
                    <div id="login_error" style="display: none;">登录成功</div>
                </div>
            </div>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>

<%@ include file="../include/_driver_login_footer.jsp" %>

</body>
<script src="<%=path%>/media/js/do/common.js"></script>
<script src="<%=path%>/media/js/do/member/login.js"></script>
</html>
