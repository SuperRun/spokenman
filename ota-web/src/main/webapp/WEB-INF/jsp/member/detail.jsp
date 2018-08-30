<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../taglib_includes.jsp" %>
<html>
<head>
    <%@ include file="../basic_includes.jsp"%>
    <title>浙江省散装水泥专用车辆驾驶员管理系统-员工详细信息</title>
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
        <div class="col-md-10">
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">员工资料卡<span id="operation"><a href="#" id="hide"
                                                                         class="glyphicon glyphicon-chevron-up"></a><a
                        href="#" id="refresh" class="glyphicon glyphicon-refresh"></a><a href="#" id="close"
                                                                                         class="glyphicon glyphicon-remove"></a></span>
                </div>
                <div class="panel-body">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <form class="form-horizontal" id="organizationInfo">
                            <div class="form-group">
                                <label for="loginName" class="col-sm-3 control-label control-head">登录名：</label>
                                <span class="control-answer col-sm-9" id="loginName">
                                    <c:if test="${not empty member.loginName}">
                                        ${member.loginName}
                                    </c:if>
                                    <c:if test="${empty member.loginName}">
                                        无
                                    </c:if>
                                </span>
                            </div>
                            <div class="form-group">
                                <label for="memberStatus" class="col-sm-3 control-label control-head">账号状态:</label>
                                    <span class="control-answer col-sm-9" id="memberStatus">
                                        ${member.status.stateInfo}
                                    </span>
                            </div>
                            <div class="form-group">
                                <label for="Name" class="col-sm-3 control-label control-head">员工姓名：</label>
                                <span class="control-answer col-sm-9" id="Name">
                                    <c:if test="${not empty member.name}">
                                        ${member.name}
                                    </c:if>
                                    <c:if test="${empty member.name}">
                                        无
                                    </c:if>
                                </span>
                            </div>
                            <div class="form-group">
                                <label for="tel" class="col-sm-3 control-label control-head">联系方式：</label>
                                <span class="control-answer col-sm-9" id="tel">
                                    <c:if test="${not empty member.phone}">
                                        ${member.phone}
                                    </c:if>
                                    <c:if test="${empty member.phone}">
                                        无
                                    </c:if>
                                </span>
                            </div>
                            <c:if test="${not empty org}">
                                <div class="form-group">
                                    <label for="orgType" class="col-sm-3 control-label control-head">所在机构类型:</label>
                                    <span class="control-answer col-sm-9" id="orgType">
                                        ${org.type.info}
                                    </span>
                                </div>
                            </c:if>

                            <div class="form-group">
                                <label for="orgName" class="col-sm-3 control-label control-head">机构名称:</label>
                                <span class="control-answer col-sm-9" id="orgName">
                                <c:if test="${not empty org}">
                                    <a href="<%=basePath %>organization/${org.id }" target="_blank">${org.name }(${org.shortName})</a>
                                </c:if>
                                <c:if test="${empty org}">
                                    无
                                </c:if>
                                </span>
                            </div>
                            <div class="form-group">
                                <label for="department" class="col-sm-3 control-label control-head">部门名称:</label>
                                <span class="control-answer col-sm-9" id="department">
                                    <c:if test="${not empty dep}">
                                        ${dep.name}
                                    </c:if>
                                    <c:if test="${empty dep}">
                                        无
                                    </c:if>
                                </span>
                            </div>
                            <%--TODO 还需要限制是否可以更改的权限--%>
                            <div class="form-group">
                                <label for="memberOp" class="col-sm-3 control-label control-head">操作:</label>
                                <div class="control-answer col-sm-9" id="memberOp">
                                    <a role="button" class="btn btn-default"
                                       href="<%=path%>/member/${member.id}/modify">修改</a>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
        </div>

    </div>
</div>
<!--message end-->


<%@ include file="../include/hr/_member_hr_footer.jsp" %>

</body>
</html>
