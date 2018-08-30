<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../taglib_includes.jsp" %>

<html>
<head>
    <%@ include file="../basic_includes.jsp"%>
    <title>修改密码</title>
    <script src="<%=path%>/media/js/do/member/changePassword-validate.js"></script>
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
                <div class="panel-heading">修改密码</div>
                <div class="panel-body">
                    <div class="col-md-3"></div>

                    <div class="col-md-6">

                        <form class="form-horizontal" method="post" action="<%=path%>/member/changepwd" id="formChangePw">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">原密码：</label>
                                <div class="col-sm-9">
                                    <input type="password" name="oldPassword" class="form-control" placeholder="旧密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">新密码：</label>
                                <div class="col-sm-9">
                                    <input type="password" name="newpw1" id="newpw1" class="form-control" placeholder="新密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">确认密码：</label>
                                <div class="col-sm-9">
                                    <input type="password" name="newpw2" class="form-control" placeholder="确认密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-5"></div>
                                <div class="col-sm-2">
                                    <input type="submit" class="btn btn-primary" value="确认修改">
                                </div>
                                <div class="col-sm-5"></div>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-3"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--message end-->

<%@ include file="../include/hr/_member_hr_footer.jsp" %>

</body>
<c:if test="${not empty msg}">
    <script>
        $().ready(function () {
            alert('${msg}');
        });
    </script>
</c:if>
</html>
