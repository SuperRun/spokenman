<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE>
<html>
<head>
<title>培训_员工_登录</title>

</head>
<body>

	手机号：
	<input type="text" name="usernmae" id="username" value="18800008888"/>
	<span id="msgPhone" style="display:none;"></span> 
	
	密码：
	<input type="password" name="password" id="password" value="123456"/>
	<span id="msgPassword" style="display:none;"></span>
	
	<span id="msgBox" style="display:none;"></span>
	<button id="loginBtn">登录</button>



</body>
<script src="<%=path%>/media/js/jquery.1.9.js"></script>
<script>
	var path = "<%=path%>";
</script>
<script src="<%=path%>/media/js/do/common.js"></script>
<script src="<%=path%>/media/js/do/member/login2.js"></script>
</html>
