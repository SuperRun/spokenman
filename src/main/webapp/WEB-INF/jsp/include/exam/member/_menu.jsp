<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<nav id="nav_menu">
	<div class="nav_title">功能选择</div>
	<ul class="nav">
		<li><a href="<%=basePath%>exam/member/home" class="start menu">首页</a>
		</li>
		<li><a href="javascript:void(0);" class="menu a_hover">考试管理</a>
			<ul class="sub_menu">
				<c:if test="${pageBaseDto.status==-1 }">
					<li><a href="<%=basePath%>exam/member/home"
						class="sub_list list_hover">所有考试</a>
					</li>
				</c:if>
				<c:if test="${pageBaseDto.status!=-1 }">
					<li><a href="<%=basePath%>exam/member/home" class="sub_list">所有考试</a>
					</li>
				</c:if>

				<li><a href="<%=basePath%>exam/member/create" class="sub_list">发布考试</a>
				</li>

				<c:if test="${pageBaseDto.status==1 }">
					<li><a
						href="<%=basePath%>exam/member/home?page=1&rows=5&searchKey=&status=1"
						class="sub_list list_hover">报名管理</a></li>
				</c:if>
				<c:if test="${pageBaseDto.status!=1 }">
					<li><a
						href="<%=basePath%>exam/member/home?page=1&rows=5&searchKey=&status=1"
						class="sub_list">报名管理</a></li>
				</c:if>

				<c:if test="${pageBaseDto.status==4 }">
					<li><a
						href="<%=basePath%>exam/member/home?page=1&rows=5&searchKey=&status=4"
						class="sub_list list_hover">成绩录入</a>
					</li>
				</c:if>
				<c:if test="${pageBaseDto.status!=4 }">
					<li><a
						href="<%=basePath%>exam/member/home?page=1&rows=5&searchKey=&status=4"
						class="sub_list">成绩录入</a>
					</li>
				</c:if>
				
				<c:if test="${pageBaseDto.status==5 }">
					<li><a
						href="<%=basePath%>exam/member/home?page=1&rows=5&searchKey=&status=5"
						class="sub_list list_hover">证书录入</a>
					</li>
				</c:if>
				<c:if test="${pageBaseDto.status!=5 }">
					<li><a
						href="<%=basePath%>exam/member/home?page=1&rows=5&searchKey=&status=5"
						class="sub_list">证书录入</a>
					</li>
				</c:if>
				<li><a href="#" class="sub_list">统计报表</a>
				<li><a href="<%=basePath%>exam/member/practice"
									class="sub_list">模拟考试管理</a></li>
				</li>
			</ul></li>
		<li><a href="#" class="menu">我的证书</a>
		</li>
		<li><a href="#" class="menu">软件下载</a>
		</li>
		<li><a href="#" class="menu">考试管理</a>
			<ul class="sub_menu">
				<li><a href="#" class="sub_list">考试报名</a>
				</li>
				<li><a href="#" class="sub_list">模拟考试</a>
				</li>
				<li><a href="#" class="sub_list">我的考试</a>
				</li>
			</ul></li>
		<li><a href="#" class="menu">软件下载</a>
		</li>
		<li><a href="#" class="menu">考试管理</a>
			<ul class="sub_menu">
				<li><a href="#" class="sub_list">考试报名</a>
				</li>
				<li><a href="#" class="sub_list">模拟考试</a>
				</li>
				<li><a href="#" class="sub_list">我的考试</a>
				</li>
			</ul></li>
		<li><a href="#" class="menu">软件下载</a>
		</li>


	</ul>
</nav>