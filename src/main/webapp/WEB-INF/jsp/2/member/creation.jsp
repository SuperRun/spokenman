<%--
  Created by IntelliJ IDEA.
  User: dxb
  Date: 12/7/2016
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../2/include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../2/include/_html_head.jsp" %>
    <title>添加本机构员工_新闻发言人在线学习培训平台</title>
</head>
<body>
<%@include file="../../2/include/_member_header.jsp" %>

<!--message start-->
<div class="container content">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="block block-two">
                <!-- Default panel contents -->
                <div class="block-heading"><span>员工基本信息</span></div>
                <div class="block-body">
                    <div class="col-md-12">
                        <div class="form-horizontal" id="organizationInfo">
                            <div class="form-group">
                                <label for="loginName" class="col-sm-2 control-label text_title">登录名*：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="loginName" class="form-control" placeholder="登录名"
                                           name="loginName" v-model="member.loginName">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="Name" class="col-sm-2 control-label text_title">员工姓名*：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="Name" class="form-control" placeholder="员工姓名" name="name"
                                           v-model="member.name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="tel" class="col-sm-2 control-label text_title">联系电话*：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="tel" class="form-control" placeholder="联系电话" name="phone"
                                           v-model="member.phone">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="idCard" class="col-sm-2 control-label text_title">身份证号:</label>
                                <div class="col-sm-9">
                                    <input type="text" id="idCard" class="form-control" placeholder="身份证号" name="sfzNo"
                                           v-model="member.sfzNo">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-5"></div>
                                <div class="col-sm-3">
                                    <span style="color: red;" v-if="error">{{error}}</span>
                                    <button type="button" class="btn-custom btn-long" @click="create()">
                                        添加
                                    </button>
                                </div>
                                <div class="col-sm-4"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<!--message end-->


<%@include file="../../2/include/_html_body.jsp" %>
<script src="${path}/media/2/js/member/creation.js"></script>
</body>
</html>
