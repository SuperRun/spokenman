<%@ page import="com.zust.itee.exam.enums.AnnouncementTypeEnum" %><%--
  Created by IntelliJ IDEA.
  User: dxb
  Date: 12/7/2016
  Time: 9:45
  员工的页头
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- <link href="${path}/media/2/css/driver_index.css" type="text/css"
      rel="stylesheet"/> --%>

<!--header start-->
<div class="header" id="header">
    <div class="side_nav">
        <div class="user_nav">
            <c:if test="${!logined}">
                <a href="${path}/exam/driver/login">考生入口</a>
                <a href="#" data-toggle="modal" data-target="#login_Modal">登录</a>
            </c:if>
            <c:if test="${logined}">

                <a href="#" class="dropdown-toggle navbar-right common_color"
                   data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false" id="su_user"><img
                        src="${path}/media/images/user.png">${sessionScope.sessionInfo.realName}
                    | ${sessionScope.sessionInfo.organizationName}<span class="caret"></span></a>
                <ul class="dropdown-menu dropdown-menu-right">
                    <li><a href="${path}/member/me">查看用户信息</a></li>
                    <li><a href="#" data-toggle="modal"
                           data-target="#changePwd_modal">修改密码</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="${path}/member/logout">注销</a></li>
                </ul>

            </c:if>
        </div>
    </div>
    <nav class="navbar navbar-default common_bg">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand common_color" href="${path}/"> <img
                        src="${path}/media/images/logo_exam.png" class="logo_pic">
                    新闻发言人在线学习培训平台
                </a>
            </div>
        </div>
    </nav>
</div>
<!--header end-->

<!--menu start-->
<nav class="navbar navbar-default">
    <ul class="nav navbar-nav">
        <li><a href="${path}/" class="start menu">首页</a></li>

        <c:if test="${!logined or loginedMember}">
            <c:if test="${!loginedRoot}">

                <!-- sdy -->

                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">考试管理<span
                        class="caret"></span></a>
                    <ul class="sub_menu dropdown-menu" role="menu">
                        <c:if
                                test="${!logined or loginedMemberDistrict or loginedMemberCity or loginedMemberProvince or loginedMemberCompany}">
                            <li><a href="<%=basePath%>exam/member/create"
                                   class="list_item">发布考试</a></li>
                            <li><a href="<%=basePath%>exam/member/home"
                                   class="list_item">所有考试</a></li>
                            <li><a
                                    href="<%=basePath%>exam/member/home?page=1&rows=5&searchKey=&status=1"
                                    class="list_item">报名管理</a></li>
                            <li><a
                                    href="<%=basePath%>exam/member/home?page=1&rows=5&searchKey=&status=4"
                                    class="list_item">成绩录入</a></li>
                            <li><a
                                    href="<%=basePath%>exam/member/home?page=1&rows=5&searchKey=&status=5"
                                    class="list_item">证书录入</a></li>
                            <li><a
                                    href="<%=basePath%>exam/member/home?page=1&rows=5&searchKey=&status=6"
                                    class="list_item">已完成考试</a></li>
                        </c:if>
                    </ul>
                </li>

                <c:if test="${!logined or loginedMemberCompany}">
                    <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">考试安排结果<span
                            class="caret"></span></a>
                        <ul class="sub_menu dropdown-menu" role="menu">
                            <li><a href="<%=basePath%>exam/company/list"
                                   class="sub_list">考试安排结果</a></li>
                        </ul>
                    </li>
                </c:if>


                <c:if test="${!logined or loginedMemberProvince}">
                    <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">考试题库<span
                            class="caret"></span></a>
                        <ul class="sub_menu dropdown-menu" role="menu">
                            <li><a href="<%=basePath%>mdd/cd/driverhr_question_type"
                                   class="sub_list">题型管理</a></li>
                            <li><a href="<%=basePath%>mdd/cd/driverhr_question_subject"
                                   class="sub_list">知识点管理</a></li>
                            <li><a href="<%=basePath%>question/import" class="sub_list">题目批量导入</a>
                            </li>
                            <li><a href="<%=basePath%>question/import" class="sub_list">新增题目</a>
                            </li>
                            <li><a href="<%=basePath%>question/list" class="sub_list">题库管理</a></li>
                        </ul>
                    </li>

                </c:if>

                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">试卷管理<span
                        class="caret"></span></a>
                    <ul class="sub_menu dropdown-menu" role="menu">
                        <li><a href="<%=basePath%>paper/member/list"
                               class="list_item">所有试卷</a></li>
                        <li><a href="<%=basePath%>paper/member/create"
                               class="list_item">发布试卷</a></li>
                    </ul>
                </li>


            </c:if>

            <c:if test="${!logined or loginedMemberProvince or loginedRoot}">
                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">数据导入<span
                        class="caret"></span></a>
                    <ul class="sub_menu dropdown-menu" role="menu">
                        <c:if test="${!loginedRoot}">
                            <li><a href="<%=basePath%>question/import" class="sub_list">考试题库导入</a>
                            </li>
                        </c:if>
                    </ul>
                </li>
            </c:if>


        </c:if>

    </ul>
</nav>


<!--menu end-->
<div id="modal">
    <!--login modal start-->
    <div class="modal fade" id="login_Modal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="loginModalLabel">用户登录</h4>
                </div>
                <div class="modal-body">

                    <form class="form-horizontal login_form" id="login_form">
                        <div class="form-group">
                            <label for="user" class="text_title">账号：</label> <input
                                type="text" class="form_input" id="user" placeholder="账号"
                                v-model="user.username" @keyup.enter="login()">
                        </div>
                        <div class="form-group">
                            <label for="password" class="text_title">密码：</label> <input
                                type="password" class="form_input" id="password"
                                placeholder="密码" v-model="user.password" @keyup.enter="login()">
                        </div>
                        <div class="form-group" v-if="error">
                            <div class="login_error">{{error}}</div>
                        </div>
                    </form>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary btn-middle"
                            @click="login()">登录
                    </button>
                    <button type="button" class="btn btn-default btn-middle"
                            data-dismiss="modal">取消
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="changePwd_modal" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">修改密码</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal login_form">
                        <div class="form-group">
                            <label class="text_title">旧密码：</label> <input type="password"
                                                                          class="form_input"
                                                                          v-model="changepwd.oldPwd"
                                                                          @keyup.enter="changePwd()">
                        </div>
                        <div class="form-group">
                            <label class="text_title">新密码：</label> <input type="password"
                                                                          class="form_input"
                                                                          v-model="changepwd.newPwd"
                                                                          @keyup.enter="changePwd()">
                        </div>
                        <div class="form-group">
                            <label class="text_title">确认密码：</label> <input type="password"
                                                                           class="form_input"
                                                                           v-model="changepwd.newPwd2"
                                                                           @keyup.enter="changePwd()">
                        </div>
                        <div class="form-group" v-if="changepwd.error">
                            <div class="login_error">{{changepwd.error}}</div>
                        </div>
                    </form>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary btn-middle"
                            @click="changePwd()">确认修改
                    </button>
                    <button type="button" class="btn btn-default btn-middle"
                            data-dismiss="modal">关闭
                    </button>
                </div>
            </div>
        </div>
    </div>

    <!--login modal end-->
</div>
<script src="${path}/media/2/js/login_modal.js"></script>
