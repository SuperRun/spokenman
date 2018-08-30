<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="header">
	<a href="${pageContext.request.contextPath}/"> <img
		src="${mystatic}/img/logo.png">
	</a>
	
	<div class="search radius6" style="box-sizing:content-box">
		<form id="commonSearchForm">
			<input type="hidden" value="${commonPageNo}" id="commonPageNo" /> <input
				type="hidden" value="${commonTotalPages }" id="commonTotalPages" />
			<input type="hidden" value="${commonOrderBy}" id="commonOrderBy" name="commonOrderBy" /> <input
				class="inp_srh" value="${commonTitle }" id="commonTitle"
				name="commonTitle" placeholder="请输入关键字"> <select
				class="select_box" id="choose">
				<option value="0">检测服务</option>
				<option value="1">质检机构</option>
				<option value="2">新闻资讯</option>
						<!-- 
						<option value="4">技术文档</option>
						<option value="22">下载中心</option> -->
			</select> <input class="btn_srh" id="commonSearchBtn" type="button"
				name="submit" value="搜索">
		</form>
		<p>检测测试|检验检疫|验厂验货|审核认证|质检品控</p>
	</div>
	
	<ul class="header_r">
		<li class="aa"><a href="${pageContext.request.contextPath}/jiancenews/133.html"><img
				src="${mystatic}/img/header_r2.png" />
				<p>网上价格优惠</p></a></li>
		<li class="aa"><a href="${pageContext.request.contextPath}/jiancenews/131.html"><img
				src="${mystatic}/img/header_r1.png" />
				<p>24h服务响应</p></a></li>
		<li><a href="${pageContext.request.contextPath}/jiancenews/132.html"><img
				src="${mystatic}/img/header_r3.png" />
				<p>极速检测周期</p></a></li>
	</ul>
</div>

<script type="text/javascript">
$(function(){
	$("#commonOrderBy").val("t.salesVolume");
	$("#commonSearchForm").keydown(function(e){
		e = e||event;
		if(e.keyCode==13){
			$('#commonSearchBtn').click();
			return false;
		}
	});
	$("#commonSearchBtn").click(function(){
		var choose=$("#choose").val();
		if(choose==0){//检测服务
			var url="${pageContext.request.contextPath}/search/jiancefuwu.html?"+$('#commonSearchForm').serialize();
			location=url;
		}
		if(choose==1){//检测店铺
			var url="${pageContext.request.contextPath}/search/organization.html?commonTitle="+$("#commonTitle").val();
			location=url;
		}
		if(choose==2){//新闻资讯
			var url="${pageContext.request.contextPath}/search/news.html?wd="+$("#commonTitle").val();
			location=url;
		}
	});
});
</script>