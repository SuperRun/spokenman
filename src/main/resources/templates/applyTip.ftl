<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>身份验证提示</title>
	<link rel="stylesheet" type="text/css" href="../layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../css/common1.css">
	<link rel="stylesheet" type="text/css" href="../css/applyTip.css">
	<link rel="stylesheet" type="text/css" href="../css/style1.css">
	<script src="../layui/layui.js"></script>
</head>

<body>
	
	<#include "perHeader.ftl"/>
		
	<div class="layui-main" style="min-height: 463px;" >
		  <div class="layui-row">
		    <div class="layui-col-md7">
				<div id="tipTitle">
					<i class="layui-icon layui-icon-tips" style="font-size: 26px; color: #EB586F;"></i>
					<span>温馨提示</span>
				</div>
				<div id="tipContent">
					<p>您还未提交身份验证信息，无法访问资源。</p>
					<p>页面自动<a href="/perUser/authenticate">跳转</a>,等待时间:<span>6</span>秒。</p>
				</div>
		    </div>
		    <div class="layui-col-md5">
		      	<img id="tipPic" src="../images/stop.png">
		    </div>
		  </div>	
	</div>    

	<#include "footer.ftl"/>
	
	<script src="../js/jquery-2.1.0.js"></script>
	<script src="../js/applyTip.js"></script>
</body>
</html>