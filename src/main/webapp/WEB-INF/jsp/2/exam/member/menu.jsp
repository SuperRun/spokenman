<%--
  Created by IntelliJ IDEA.
  User: dxb
  Date: 12/7/2016
  Time: 9:47
  放于 body 结束标签之前 必要的js
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="attach_part" id="attach_part">
	<div class="attach-heading">
		<span>考试管理</span>
	</div>
	<div class="attach-body">
		<ul class="ul-list">
			<li><a href="<%=basePath %>exam/member/create" class="list_item">发布考试</a></li>
			<li><a href="<%=basePath %>exam/member/home" class="list_item">所有考试</a></li>
			<li><a href="<%=basePath %>exam/member/home?page=1&rows=5&searchKey=&status=1" class="list_item">报名管理</a></li>
			<li><a href="<%=basePath %>exam/member/home?page=1&rows=5&searchKey=&status=4" class="list_item">成绩录入</a></li>
			<li><a href="<%=basePath %>exam/member/home?page=1&rows=5&searchKey=&status=5" class="list_item">证书录入</a></li>
			<li><a href="<%=basePath %>exam/member/home?page=1&rows=5&searchKey=&status=6" class="list_item">已完成考试</a></li>
		</ul>
	</div>
</div>