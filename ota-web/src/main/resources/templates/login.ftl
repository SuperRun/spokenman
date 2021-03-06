<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="../layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>
<div id="app">

	<div id="login">
		<fieldset class="layui-elem-field layui-field-title">
		  <legend style="font-size: 18px">登&nbsp录</legend>
		</fieldset>
		<form class="layui-form" action="">

			<div class="layui-form-item">
				<div class="wrap-item">
	        		<i class="layui-icon" style="font-weight: bold;">&#xe63b;</i>
	        		<input type="text" v-model="name"  placeholder="手机号/用户名" autocomplete="off" class="layui-input">
	    		</div>
			</div>		


			<div class="layui-form-item">
				<div class="wrap-item">
	        		<i class="layui-icon">&#xe64c;</i>
	        		<input type="password" v-model="password" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
	    		</div>
			</div>
		                                                        				
		    <button type="button" class="layui-btn layui-btn-fluid login-btn" @click="login">登录</button>				
		</form>	
		
	</div>
	
</div>	

</body>
<script src="../js/jquery-2.1.0.js"></script>
<script src="../layui/layui.js"></script>
<script src="../js/vue.js"></script>
<script src="../js/login.js"></script>
</html>