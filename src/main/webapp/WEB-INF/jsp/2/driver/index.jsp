<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../2/include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../2/include/_html_head.jsp" %>
    <link href="${path}/media/2/css/driver_index.css" type="text/css" rel="stylesheet"/>
    <title>新闻发言人在线学习培训平台</title>
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
                    <div class="block-heading"><span><%=AnnouncementTypeEnum.TRAINNOTICE.getInfo()%></span><a href="#">更多</a></div>
                    <div class="block-body">
                        <ul class="ul-list">
                            <c:if test="${empty anntrain or fn:length(anntrain)==0}">
                                <li><a href="#" class="list_item">无通知</a><span
                                        class="time">2015-08-01</span></li>
                            </c:if>
                            <c:if test="${not empty anntrain and fn:length(anntrain)>0}">
                                <c:forEach items="${anntrain }" var="ann">
                                    <li><a href="<%=path %>/announcement/${ann.id }" class="list_item">${ann.title }</a>
                                        <span class="time">${ann.createDate }</span></li>
                                </c:forEach>
                            </c:if>

                        </ul>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="block">
                    <!-- Default panel contents -->
                    <div class="block-heading"><span><%=AnnouncementTypeEnum.EXAMNOTICE.getInfo()%></span><a href="#">更多</a></div>
                    <div class="block-body">
                        <ul class="ul-list">
                            <c:if test="${empty annexam or fn:length(annexam)==0}">
                                <li><a href="#" class="list_item">无通知</a><span
                                        class="time">2015-08-01</span></li>
                            </c:if>
                            <c:if test="${not empty annexam and fn:length(annexam)>0}">
                                <c:forEach items="${annexam }" var="ann">
                                    <li><a href="<%=path %>/announcement/${ann.id }" class="list_item">${ann.title }</a>
                                        <span class="time">${ann.createDate }</span></li>
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
</body>
</html>
