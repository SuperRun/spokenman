<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="header" id="header">
	<nav class="navbar navbar-default common_bg">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand common_color" href="<%=basePath%>driver/index"> 浙江省散装水泥专用车辆驾驶员系统
				</a>
			</div>
			<a href="#" class="dropdown-toggle navbar-right common_color"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false" id="su_user">${sessionScope.sessionInfo.realName} <span class="caret"></span>
			</a>
			<ul class="dropdown-menu dropdown-menu-right">
				<li><a href="<%=basePath %>driver/${sessionScope.sessionInfo.userId}">查看个人资料</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="#">退出账号</a></li>
			</ul>
		</div>
	</nav>
</div>