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

    <title>首页_新闻发言人在线学习培训平台</title>
</head>
<body>
<%@include file="../../2/include/_member_header.jsp" %>

<div class="container">
<!--main content start-->
<!--message start-->
<div class="content">
    <div class="banner">
        <ul class="imgList">
            <li><a href="#"><img src="${path}/media/images/banner2.png" width="1168px" height="300px"></a></li>
            <li><a href="#"><img src="${path}/media/images/banner3.png" width="1168px" height="300px"></a></li>
        </ul>
        <img src="${path}/media/images/changepicleft.png" width="20px" height="40px" class="prev" id="prev">
        <img src="${path}/media/images/changepicright.png" width="20px" height="40px" class="next" id="next">
        <ul class="indexList"><!-- 图片右下角序号部分 -->
            <li class="indexOn"></li>
            <li></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="block">
                <!-- Default panel contents -->
                <div class="block-heading"><span><%=AnnouncementTypeEnum.PLATFORMNOTICE.getInfo()%></span>
                    <a href="${path}/announcement/list">更多</a></div>
                <div class="block-body">
                    <ul class="ul-list">
                        <c:if test="${empty ann1 or fn:length(ann1)==0}">
                            <li><a href="#" class="list_item">无通知</a><span
                                    class="time">2015-08-01</span></li>
                        </c:if>
                        <c:if test="${not empty ann1 and fn:length(ann1)>0}">


                                <c:forEach items="${ann1 }" var="ann">
                                    <li><a href="<%=path %>/announcement/${ann.id }" class="list_item">${ann.title }</a><span
                                            class="time">${ann.createDate }</span></li>
                                </c:forEach>
                        </c:if>

                    </ul>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="block">
                <!-- Default panel contents -->
                <div class="block-heading"><span><%=AnnouncementTypeEnum.EXAMNOTICE.getInfo()%></span>
                    <a href="${path}/announcement/list">更多</a></div>
                <div class="block-body">
                    <ul class="ul-list">
                        <c:if test="${empty ann2 or fn:length(ann2)==0}">
                            <li><a href="#" class="list_item">无通知</a><span
                                    class="time">2015-08-01</span></li>
                        </c:if>
                        <c:if test="${not empty ann2 and fn:length(ann2)>0}">


                            <c:forEach items="${ann2 }" var="ann">
                                <li><a href="<%=path %>/announcement/${ann.id }" class="list_item">${ann.title }</a><span
                                        class="time">${ann.createDate }</span></li>
                            </c:forEach>
                        </c:if>

                    </ul>
                </div>
            </div>
        </div>


    </div>
</div>
<!--message end-->
<!--main content end-->


    </div>

<%@include file="../../2/include/_html_body.jsp" %>
<script>
</script>
</body>
</html>
