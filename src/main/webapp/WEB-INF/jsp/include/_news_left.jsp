<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<div class="main_l" style="margin-left:-220px">
	<img src="${pageContext.request.contextPath}/img/about_1.png" />
	<ul class="list">
		<li class="d1"><a class="zk"><p>新闻资讯</p></a>
			<ul class="no">
				<li><a
					href="${pageContext.request.contextPath}/news/showNewsList.json?ddTypeId=190">质检品控社会新闻</a></li>
				<li><a
					href="${pageContext.request.contextPath}/news/showNewsList.json?ddTypeId=63">质检品控行业资讯</a></li>
				<li><a
					href="${pageContext.request.contextPath}/news/showNewsList.json?ddTypeId=67">检测机构动态活动</a></li>

			</ul></li>


		<li class="d2"><a class="zk"><p>关于检啦</p></a>

			<!--类名zk,zk2是左侧导航进入页面的样式，点击进入的时候，ul应是展开状态，并且相应的li的颜色是白色的-->
			<ul class="no">
				<li><a href="${pageContext.request.contextPath}/jiancenews/137.html">检啦简介</a></li>
<!-- 				<li><a href="#.html">联系检啦</a></li> -->
<!-- 				<li><a href="#.html">人才招聘</a></li> -->
				<!-- 
				<li><a
					href="${pageContext.request.contextPath}/news/showNewsList.json?ddTypeId=191">招商</a></li>
				<li><a
					href="${pageContext.request.contextPath}/news/showNewsList.json?ddTypeId=64">保障</a></li>
				 -->
				<li><a
					href="${pageContext.request.contextPath}/news/showNewsList.json?ddTypeId=66">检啦平台规则</a></li>
				<li><a
					href="${pageContext.request.contextPath}/news/showNewsList.json?ddTypeId=65">检啦平台公告</a></li>
			</ul></li>
		<li class="d3"><a href="${pageContext.request.contextPath}/news/showNewsList.json?ddTypeId=191"><p>机构入驻</p></a></li>
		<li class="d4"><a href="${pageContext.request.contextPath}/news/showNewsList.json?ddTypeId=64"><p>检啦服务</p></a></li>

	</ul>
</div>
<script>
$(function(){
	$(".main_l ul.list a.zk").click(function(){
		var t=$(this).parent().find(".no");
		if(t.is(":hidden")){
			t.show();
			console.log("show");
		}else{
			t.hide();
		}
	});
})
</script>