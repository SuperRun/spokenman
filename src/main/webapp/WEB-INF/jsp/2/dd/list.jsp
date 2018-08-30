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
    <title>数据字典_新闻发言人在线学习培训平台</title>
    <link href="${path}/media/2/css/dd.css" type="text/css" rel="stylesheet"/>

</head>
<body>
<%@include file="../include/_member_header.jsp" %>

<!--main content start-->

<!--message start-->
<div class="content container">
    <div class="row">

        <div class="block block-two">
            <!-- Default panel contents -->
            <div class="block-heading"><span>数据字典</span></div>
            <div class="block-body">
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

                        <c:forEach items="${list}" var="dd" varStatus="varStatus">

                            <tr>
                                <td class="text-center">${varStatus.index+1}</td>

                                <td class="text-center">${dd.valueStr}<span
                                        style="color: grey">(${dd.codeType})</span></td>


                                <td class="text-center">
                                        ${dd.remark}</td>

                                <td class="text-center">
                                    <button class="btn-custom btn-middle btn-table"

                                            onclick="location.href=path+'/mdd/${dd.id}'"
                                    >详情
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>


                        </tbody>

                    </table>

                </div>
            </div>
        </div>
    </div>

</div>

<!--message end-->
<!--main content end-->

<%@include file="../include/_html_body.jsp" %>
</body>
</html>
