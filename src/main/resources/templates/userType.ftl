<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>中间页面</title>
	<link rel="stylesheet" type="text/css" href="../layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>
<div id="app">
	<input id="type" type="hidden" value="<#if Session.sessionInfo ? exists>${Session.sessionInfo.type}</#if>"/>
	<input id="status" type="hidden" value="<#if Session.sessionInfo ? exists>${Session.sessionInfo.status}</#if>"/>
</div>
</body>
<script src="../js/jquery-2.1.0.js"></script>
<script src="../layui/layui.js"></script>
<script src="../js/vue.js"></script>
<script src="../js/userType.js"></script>
</html>