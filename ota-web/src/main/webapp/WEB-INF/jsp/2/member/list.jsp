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
    <link href="${path}/media/2/css/dd.css" type="text/css" rel="stylesheet"/>
    <title>本机构员工列表_新闻发言人在线学习培训平台</title>
</head>
<body>
<%@include file="../../2/include/_member_header.jsp" %>
<div id="app">

    <!--message start-->
    <div class="content container">
        <div class="row">

            <div class="block block-two">
                <!-- Default panel contents -->
                <div class="block-heading">
                    <h4>员工列表</h4>
                </div>
                <div class="block-body">
                    <div id="pb_top">
                        <a href="${path}/member/new" class="btn btn-primary">添加</a>
                    </div>
                    <div id="pb_content">
                        <table class="table table-striped">
                            <thead>

                            <tr>
                                <th class="text-center">序号</th>

                                <th class="text-center">登录名</th>
                                <th class="text-center">姓名</th>

                                <th class="text-center">联系方式</th>


                                <th class="text-center">操作</th>
                            </tr>

                            </thead>


                            <tbody>


                            <tr v-for="(m,index) in vlist">
                                <td class="text-center" v-text="index + 1"></td>

                                <td class="text-center" v-text="m.loginName"></td>
                                <td class="text-center" v-text="m.name"></td>

                                <td class="text-center" v-text="m.phone"></td>

                                <td class="text-center">
                                    <button class="btn btn-primary btn-xs" data-toggle="modal"
                                            data-target="#modifyMember_modal" @click="modifyModal(m.id)"
                                    >修改
                                    </button>
                                    <button class="btn btn-primary btn-xs" data-toggle="modal"
                                            data-target="#delete_Modal"
                                            @click="deleteModal(m.id)" v-if="m.id != managerId">移除
                                    </button>
                                </td>

                            </tr>


                            </tbody>

                        </table>

                    </div>
                </div>
            </div>
        </div>

    </div>

    <!--message end-->

    <!--delete modal start-->
    <div class="modal fade" id="delete_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="detailModalLabel">确认</h4>
                </div>
                <div class="modal-body">
                    是否确定移除 [{{memberDel.name}}]？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-middle" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary btn-middle" @click="deleteAction()"
                            data-dismiss="modal">确定
                    </button>
                </div>
            </div>
        </div>
    </div>
    <!--delete modal end-->

    <%--修改用户 start--%>
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
                            <label class="text_title">真实姓名*：</label>
                            <input type="text" class="form_input"
                                   v-model="modify.member.name" @keyup.enter="modifyMember()">
                        </div>
                        <div class="form-group">
                            <label class="text_title">联系方式*：</label>
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
    <%--修改用户 end--%>
    <!--main content end-->
</div>

<%@include file="../../2/include/_html_body.jsp" %>
<script>
    var orgId = '${sessionScope.sessionInfo.organizationId}';
    var managerId = parseInt('${managerId}');
</script>
<script src="${path}/media/2/js/member/list.js"></script>
<script>
    updateList();
</script>
</body>
</html>
