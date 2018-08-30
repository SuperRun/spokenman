<%--
  Created by IntelliJ IDEA.
  User: dxb
  Date: 12/7/2016
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../include/_html_head.jsp" %>
    <title>新闻发言人在线学习培训平台</title>
    <link href="${path}/media/2/css/dd.css" type="text/css" rel="stylesheet"/>

</head>
<body>
<%@include file="../include/_member_header.jsp" %>
<div id="app">

    <!--message start-->
    <div class="content container">
        <div class="row">

            <div class="block block-two">
                <!-- Default panel contents -->
                <div class="block-heading">
                    <h4>${parent.valueStr} (${parent.codeType})</h4>
                    <p>${parent.remark}</p>
                </div>
                <div class="block-body">
                    <div id="pb_top">
                        <button class="btn-custom btn-long add_btn" data-toggle="modal" data-target="#new_Modal"
                                @click="createModal()">添加
                        </button>
                    </div>
                    <div id="pb_content">
                        <table class="table table-striped">
                            <thead>

                            <tr>
                                <th class="text-center">序号</th>

                                <th class="text-center">内容</th>

                                <th class="text-center">备注</th>

                                <th class="text-center">操作</th>
                            </tr>

                            </thead>


                            <tbody>

                            <%--<c:forEach items="${list}" var="dd" varStatus="varStatus">--%>

                            <%--<tr>--%>
                            <%--<td class="text-center">${varStatus.index+1}</td>--%>

                            <%--<td class="text-center">${dd.valueStr}</td>--%>

                            <%--<td class="text-center">${dd.remark}</td>--%>

                            <%--<td class="text-center">--%>
                            <%--<button class="btn-custom btn-middle btn-table" data-toggle="modal"--%>
                            <%--data-target="#modify_Modal">编辑--%>
                            <%--</button>--%>
                            <%--<button class="btn-custom btn-middle btn-table" data-toggle="modal"--%>
                            <%--data-target="#delete_Modal">删除--%>
                            <%--</button>--%>
                            <%--</td>--%>

                            <%--</tr>--%>
                            <%--</c:forEach>--%>


                            <tr v-for="(dd,index) in vlist">
                                <td class="text-center">{{index + 1}}</td>

                                <td class="text-center">{{dd.valueStr}}</td>

                                <td class="text-center">{{dd.remark}}</td>

                                <td class="text-center">
                                    <button class="btn btn-primary btn-xs"
                                            data-toggle="modal" data-target="#modify_Modal"
                                            @click="modifyModal(dd.id)">编辑
                                    </button>
                                    <button class="btn btn-primary btn-xs" data-toggle="modal"
                                            data-target="#delete_Modal"
                                            @click="deleteModal(dd.id)">删除
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
    <!--examInfo modal start-->
    <div class="modal fade" id="new_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="examInfoModalLabel">添加子项</h4>
                </div>
                <div class="modal-body">

                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label class="text_title">内容：</label>
                                <input type="text" class="form_input" placeholder="内容" v-model="ddCreate.valueStr">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <label class="text_title" style="vertical-align: top;">备注：</label>
                                <textarea class="form_textarea" v-model="ddCreate.remark"></textarea>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-middle" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary btn-middle" data-dismiss="modal" @click="create()">添加
                    </button>

                </div>
            </div>
        </div>
    </div>
    <!--examInfo modal end-->
    <%--modify modal start--%>
    <div class="modal fade" id="modify_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">修改子项</h4>
                </div>
                <div class="modal-body">

                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label class="text_title">内容：</label>
                                <input type="text" class="form_input" placeholder="内容" v-model="ddModify.valueStr">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <label class="text_title" style="vertical-align: top;">备注：</label>
                                <textarea class="form_textarea" v-model="ddModify.remark"></textarea>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-middle" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary btn-middle" @click="modify" data-dismiss="modal">保存修改
                    </button>

                </div>
            </div>
        </div>
    </div>
    <%--modify modal end--%>

    <!--cancel modal start-->
    <div class="modal fade" id="delete_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="detailModalLabel">删除确认</h4>
                </div>
                <div class="modal-body">
                    是否确定删除 [{{ddDelete.valueStr}}]？
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
    <!--cancel modal end-->
    <!--main content end-->
</div>

<%@include file="../include/_html_body.jsp" %>
<script>
    var vlist = $.parseJSON('${vlist}');
    var vparent = $.parseJSON('${vparent}');
</script>
<script src="${path}/media/2/js/dd/detail.js"></script>
</body>
</html>
