<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dxb
  Date: 12/7/2016
  Time: 9:47
   html头部引入文件
    放在head开始标签之后
    必要的meta、js、css
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<c:set value="<%=path%>" var="path"/>


<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chrome=1">
<%--<link href="${path}/favicon.ico" rel="shortcut icon" type="image/x-icon">--%>
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="${path}/media/2/css/common.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/vue/2.1.4/vue.min.js"></script>
<script>
    var path = '${path}';
</script>