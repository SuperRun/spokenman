<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../taglib_includes.jsp" %>
<html>
<head>
    <%@ include file="../basic_includes.jsp" %>
    <title>首页</title>
</head>
<body>
<%@ include file="../include/hr/_member_hr_header.jsp" %>

<!--message start-->
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <!--menu start-->
            <%@ include file="../include/hr/_menu.jsp" %>
            <!--menu end-->
        </div>
        <div class="col-md-10" id="message">
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">通知</div>
                <div class="panel-body">
                    <c:if test="${empty anns or fn:length(anns)==0}">
                        无通知
                    </c:if>
                    <c:if test="${not empty anns and fn:length(anns)>0}">

                        <ul>
                            <c:forEach items="${anns }" var="ann">
                                <li><a href="<%=path %>/announcement/${ann.id }"
                                       class="message_item">${ann.title }</a><span
                                        class="time">${ann.createDate }</span></li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </div>
            </div>
        </div>
        
    </div>
</div>
<!--message end-->


<%@ include file="../include/hr/_member_hr_footer.jsp" %>
</body>
</html>
