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
		<span>题目管理</span>
	</div>
	<div class="attach-body">
		<ul class="ul-list">
			<li><a href="<%=basePath %>question/list" class="list_item">所有题目</a></li>
			<li><a href="<%=basePath %>question/import" class="list_item">题目导入</a></li>
		</ul>
	</div>
</div>