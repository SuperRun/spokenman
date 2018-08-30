<%--
  Created by IntelliJ IDEA.
  User: dxb
  Date: 12/7/2016
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../include/_html_head.jsp" %>
    <title>${title}_基于B/S程序的无纸化网络考试系统</title>
    <link href="http://cdn.bootcss.com/zTree.v3/3.5.26/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet">
</head>
<body>
<%@include file="../../include/_member_header.jsp" %>

<div class="container">

    <div class="content">
        <div class="row">
            <div class="col-md-4">
                <div id="ztree" class="ztree" style="margin-bottom: 500px;"></div>
            </div>

            <div class="col-md-8" id="orgInfo">
                <div class="block block-two">
                    <!-- Default panel contents -->
                    <div class="block-heading"><span>机构信息</span>
                        <a target="_blank" v-if="org" href="#" data-toggle="modal"
                           data-target="#delete_Modal">&nbsp;删除</a>
                        <a target="_blank" v-if="org" :href="org?path+'/organization/'+org.id+'/modify':'#'">
                            &nbsp;修改</a>
                        <a target="_blank" v-if="org && org.type!='区县散装办'"
                           :href="org?path+'/organization/newchild/'+org.id:'#'">创建子机构</a>
                    </div>
                    <div class="block-body">
                        <div class="col-md-12">
                            <table class="table table-striped table_info table-bordered" id="organizationInfo"
                                   v-if="org">
                                <thead>
                                <tr>
                                    <th colspan="4">机构基本信息</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td style="min-width: 120px;">组织机构类型</td>
                                    <td>{{org.type}}</td>
                                    <td>上级机构</td>
                                    <td>{{org.parentOrg?org.parentOrg.name:'-'}}</td>
                                </tr>
                                <tr>
                                    <td>机构全称</td>
                                    <td>{{org.name?org.name:'-'}}</td>
                                    <td>机构简称</td>
                                    <td>{{org.shortName?org.shortName:'-'}}</td>
                                </tr>
                                <tr>
                                    <td>主管姓名</td>
                                    <td>{{org.memberName?org.memberName:'-'}}</td>

                                    <td>主管联系电话</td>
                                    <td>{{org.memberTel?org.memberTel:'-'}}</td>
                                </tr>
                                <tr>
                                    <td>联系人姓名</td>
                                    <td>{{org.linkmanName?org.linkmanName:'-'}}</td>


                                    <td>联系电话</td>
                                    <td>{{org.tel?org.tel:'-'}}</td>


                                </tr>
                                <tr>
                                    <td>Email</td>
                                    <td colspan="3">{{org.email?org.email:'-'}}</td>
                                </tr>
                                <tr>
                                    <td>机构描述</td>
                                    <td colspan="3">
                                        {{org.description?org.description:'-'}}
                                    </td>
                                </tr>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
                <!--cancel modal start-->
                <div class="modal fade" id="delete_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="detailModalLabel">删除确认</h4>
                            </div>
                            <div class="modal-body" v-if="org">
                                是否确定删除 [{{org.name}}]？
                                <span style="color: red;"> 此操作不可逆！</span>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default btn-middle" data-dismiss="modal">关闭
                                </button>
                                <button type="button" class="btn btn-primary btn-middle" @click="disableOrg()"
                                        data-dismiss="modal">
                                    确定
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!--cancel modal end-->
            </div>

        </div>
    </div>
</div>
<%@include file="../../include/_html_body.jsp" %>
<script src="http://cdn.bootcss.com/zTree.v3/3.5.26/js/jquery.ztree.core.min.js"></script>
<script>
    var tree = $.parseJSON('${ztree}');
</script>

<script src="${path}/media/2/js/organization/list.js"></script>
</body>
</html>
