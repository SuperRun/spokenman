<%--
  Created by IntelliJ IDEA.
  User: dxb
  Date: 12/7/2016
  Time: 10:00
  员工资料
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../2/include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../2/include/_html_head.jsp" %>
    <title>新闻发言人在线学习培训平台</title>
</head>
<body>
<%@include file="../../2/include/_member_header.jsp" %>
<div id="app">
    <div class="container">

        <!--main content start-->

        <!--message start-->
        <div class="content">
            <div class="row">

                <div class="col-md-8 col-md-offset-2">
                    <div class="block">
                        <!-- Default panel contents -->
                        <div class="block-heading"><span>员工基本信息</span>
                            <c:if test="${member.id eq sessionScope.sessionInfo.userId}">
                                <a data-toggle="modal" data-target="#modifyMember_modal">修改</a>
                            </c:if>
                        </div>
                        <div class="block-body">
                            <div class="col-md-12">

                                <table class="table table-bordered table_info table-striped" id="staffInfo">

                                    <thead>

                                    <tr>
                                        <th colspan="4">基本信息</th>
                                    </tr>

                                    </thead>

                                    <tbody>

                                    <tr>

                                        <td> 登录名</td>

                                        <td>${empty member.loginName ? "-" : member.loginName}</td>

                                        <td>员工姓名</td>

                                        <td>${empty member.name ? "-" : member.name}</td>

                                    </tr>

                                    <tr>

                                        <td>联系方式</td>

                                        <td>${empty member.phone ? "-" : member.phone}</td>

                                        <td>身份证号</td>

                                        <td>${empty member.sfzNo ? "-" : member.sfzNo}</td>

                                    </tr>

                                    <tr>

                                        <td>所在机构</td>

                                        <td>${(not empty org and not empty org.type) ? org.type.info:"-"  }</td>

                                        <td>机构名称</td>

                                        <td>${(not empty org and not empty org.name) ? org.name:"-"  }
                                            <c:if test="${(not empty org and not empty org.shortName)}">
                                                ,
                                            </c:if>
                                            ${(not empty org and not empty org.shortName) ? org.shortName:"-"  }
                                        </td>

                                    </tr>


                                    </tbody>

                                </table>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--message end-->


        <!--main content end-->
    </div>

    <div class="modal fade" id="modifyMember_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">修改个人资料</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal login_form">
                        <div class="form-group">
                            <label class="text_title">真实姓名：</label>
                            <input type="text" class="form_input"
                                   v-model="modify.member.name" @keyup.enter="modifyMember()">
                        </div>
                        <div class="form-group">
                            <label class="text_title">联系方式：</label>
                            <input type="text" class="form_input"
                                   v-model="modify.member.phone" @keyup.enter="modifyMember()">
                        </div>
                        <div class="form-group">
                            <label class="text_title">身份证号：</label>
                            <input type="text" class="form_input"
                                   v-model="modify.member.sfzNo" @keyup.enter="modifyMember()">
                        </div>
                        <div class="form-group" v-if="modify.member.error">
                            <div class="login_error">{{modify.member.error}}</div>
                        </div>
                    </form>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary btn-middle" @click="modifyMember()">确认修改</button>
                    <button type="button" class="btn btn-default btn-middle" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../2/include/_html_body.jsp" %>
<script>
    var mname = '${member.name}';
    var mphone = '${member.phone}';
    var msfzNo = '${member.sfzNo}';
</script>
<script src="${path}/media/2/js/memberDetail.js"></script>
</body>
</html>
