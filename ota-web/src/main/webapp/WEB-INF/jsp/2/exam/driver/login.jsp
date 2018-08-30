<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!--散装办以及车企登录-->
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chrome=1">
    <link href="<%=basePath%>media/css/bootstrap/bootstrap.min.css"
          type="text/css" rel="stylesheet"/>
    <link href="<%=basePath%>media/2/css/login.css" type="text/css"
          rel="stylesheet"/>
    <script type="text/javascript"
            src="<%=basePath%>media/js/jquery.1.9.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>media/2/js/exam/driver/login.js"></script>
    <title>新闻发言人在线学习培训平台</title>
    <script>
      window.onload = function() {
        var height = window.screen.height;
        $('body').css('height', height);
      }
    </script>
</head>
<body
        style="background-image: url('<%=basePath%>media/images/login.jpg');background-repeat: no-repeat;background-size: 100% 100%;-webkit-background-size: cover;-o-background-size: cover;">
<!--header start-->
<div class="header" id="header">
    <nav class="navbar navbar-default common_bg">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand common_color" href="#">
                    新闻发言人在线学习培训平台 </a>
            </div>
        </div>
    </nav>
</div>
<!--header end-->
<!--main content start-->
<div class="container" id="content">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4" id="login">
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">考生登录</div>
                <div class="panel-body">
                    <div class="form-horizontal" id="login_form">

                        <div class="form-group">
                            <label for="id_num" class="col-sm-3 control-label">身份证号</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="id_num"
                                       placeholder="身份证号">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 text-center">
                                <button class="btn btn-primary driver_login"
                                        onclick="examLoginCheck('<%=basePath%>')">登录
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="login_error" id="loginError" style="display:none"></div>
                    <div style="display:none">
                        <form id="examingForm"
                              action="<%=basePath%>exam/driver/examing/execution/examId"
                              method="post"></form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
<!--main content end-->
<!--footer start-->
<div class="footer">
    <nav class="navbar navbar-default navbar-fixed-bottom common_bg"
         id="footer_navbar">
        <div class="container common_color">
            <p>版权所有 © 浙江科技学院 xcb@zust.edu.cn 浙ICP备11051284号</p>

            <p>地址：浙江·杭州市留和路318号 邮编：310023</p></div>
    </nav>
</div>
<!--footer end-->

</body>
</html>