<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<link href="<%=path%>/media/images/favicon.ico" rel="shortcut icon" type="image/x-icon">
<link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
<%-- <link href="<%=path%>/media/css/bootstrap/bootstrap.min.css"
	type="text/css" rel="stylesheet" />
 --%>	
	
<link href="<%=path%>/media/css/common.css" type="text/css"
	rel="stylesheet" />

<!-- 
<script src="//cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
 -->	
<script src="<%=path%>/media/js/jquery.1.9.js"></script>
<script src="<%=path%>/media/js/jquery.form.js"></script>
<script src="<%=path%>/media/js/jquery.validate.min.js"></script>
<script src="<%=path%>/media/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/vue/2.1.4/vue.min.js"></script>
<script>
	var path = "<%=path%>";
	var basePath = "<%=basePath%>";
</script>



<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chrome=1">
