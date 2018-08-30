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
    <title>导入车企信息_基于B/S程序的无纸化网络考试系统</title>
    <link href="${path}/media/2/css/hr_new.css" rel="stylesheet">

</head>
<body>
<%@include file="../../include/_member_header.jsp" %>

<div class="content container">
    <div class="row" style="padding-bottom: 200px">
        <div class="col-md-12">
            <div class="block block-two batch_update">
                <div class="block-heading"><span>车企信息导入</span></div>
                <div class="block-body">
                    <div class="col-md-2"><a href="${path}/media/excel/company.xlsx" target="_blank" class="btn btn-default">模板下载</a></div>
                </div>

                <div class="col-md-8 "><input type="text" id="showPath" name="file" placeholder="点击选择文件"
                                              onclick="$('#uploadexcel').click();" class="form_input"></div>
                <div class="col-md-2">
                    <button class="btn btn-primary" @click="ana()">上传</button>
                </div>
                <div style="display:none">
                    <form action="<%=path %>/up/excel" id="uploadexcelForm" method="post"
                          enctype="multipart/form-data" style="display:none">
                        <input id="uploadexcel" type="file" name="upload">
                    </form>
                </div>
            </div>
        </div>
        <div v-show="shown.waiting">
            <img src="${path}/media/images/loading.gif">正在上传，可能耗时3-4分钟，请耐心等待...
        </div>
        <div v-if="list">
            <!-- Default panel contents -->
            <div class="block-heading"><span>已导入信息</span></div>
            <div class="block-body">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>企业名称</th>
                        <th>所属市</th>
                        <th>所属区县</th>
                        <th>企业法人</th>
                        <th>分管领导</th>
                        <th>车队长</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="(o,index) in list">
                        <td>{{index+1}}</td>
                        <td>{{o.companyName}}</td>

                        <td>{{o.superRegion}}</td>

                        <td>
                            {{o.region}}
                        </td>

                        <td>{{o.legalPerson+' '+o.legalPersonTel+' '+o.legalPersonEmail}}</td>

                        <td>{{o.leaderName+' '+o.leaderTel+' '+o.leaderEmail}}</td>

                        <td>{{o.driverName+' '+o.driverSfzNo}}</td>

                    </tr>

                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>
</div>
<!--message end-->


<%@include file="../../include/_html_body.jsp" %>
<script src="${path}/media/js/jquery.form.js"></script>
<script src="${path}/media/2/js/organization/companyImport.js"></script>
</body>
</html>
