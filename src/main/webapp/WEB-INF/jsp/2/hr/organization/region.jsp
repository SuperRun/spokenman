<%--
  Created by IntelliJ IDEA.
  User: dxb
  Date: 12/7/2016
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../2/include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../../2/include/_html_head.jsp" %>
    <title>行政区管理_基于B/S程序的无纸化网络考试系统</title>
    <link href="http://cdn.bootcss.com/zTree.v3/3.5.26/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet">

</head>
<body>
<%@include file="../../../2/include/_member_header.jsp" %>


<div class="container">
    <div class="content">

        <%--操作--%>
        <div class="row">
            <div style="margin: 10px 0 " class="col-md-12">
                <p>按 “ctrl+F” 快速查找</p>
                <button class="btn btn-primary btn-sm" data-toggle="modal" :disabled="node.selected.id == ''"
                        data-target="#create_Modal">添加子级
                </button>
                <button class="btn btn-primary btn-sm" data-toggle="modal" :disabled="node.selected.id == ''"
                        data-target="#modify_Modal">修改
                </button>
                <button class="btn btn-primary btn-sm" data-toggle="modal" :disabled="node.selected.id == ''"
                        data-target="#delete_Modal">删除
                </button>
            </div>
        </div>
        <%--操作 end--%>

        <%--树--%>
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div id="ztree" class="ztree" style="margin-bottom: 500px;"></div>
            </div>
        </div>
        <%--树 end--%>
        <%--modal--%>


        <div>
            <div class="modal fade" id="create_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="examInfoModalLabel"
                                v-text="'添加['+node.selected.name+']的子级'"></h4>
                        </div>
                        <div class="modal-body">

                            <div class="form-horizontal">
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <label class="text_title">名称：</label>
                                        <input type="text" class="form_input" v-model="node.create.valueStr"
                                               @keyup.enter="create()">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default btn-middle" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary btn-middle" data-dismiss="modal"
                                    @click="create()">添加
                            </button>

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="modify_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title"
                                v-text="'修改['+node.selected.name+']'"></h4>
                        </div>
                        <div class="modal-body">

                            <div class="form-horizontal">
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <label class="text_title">名称：</label>
                                        <input type="text" class="form_input" v-model="node.selected.valueStr"
                                               @keyup.enter="modify()">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default btn-middle" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary btn-middle" data-dismiss="modal"
                                    @click="modify()">保存修改
                            </button>

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="delete_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="detailModalLabel">删除确认</h4>
                        </div>
                        <div class="modal-body">
                            是否确定删除 [{{node.selected.name}}]？
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default btn-middle" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary btn-middle" @click="remove()"
                                    data-dismiss="modal">确定
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<%@include file="../../../2/include/_html_body.jsp" %>
<script src="http://cdn.bootcss.com/zTree.v3/3.5.26/js/jquery.ztree.core.min.js"></script>
<script src="${path}/media/2/js/organization/region.js"></script>
</body>
</html>
