<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="main_l">
	<h1>
		<a href="${pageContext.request.contextPath}/homepage.html">会员中心</a>
	</h1>
	<ul class="list">
		<li class="f"><a href="${pageContext.request.contextPath}/orgnization/password.html"><p>修改账户密码</p></a></li>
		<li class="s zk"><a class="zk"><p>企业会员信息</p></a>
			<ul class="no">
				<li class="zk2"><a href="${pageContext.request.contextPath}/orgnization/updateAccountInfo.html">账号信息</a></li>
				<li><a href="${pageContext.request.contextPath}/orgnization/updateLinkmanInfo.html">联系人资料</a></li>
				<li><a href="${pageContext.request.contextPath}/orgnization/updateOrgInfo.html">公司资料</a></li>
				<li><a href="${pageContext.request.contextPath}/express.html">物流信息</a></li>
				<li><a href="${pageContext.request.contextPath}/finance.html">财务信息</a></li>
				<li><a href="${pageContext.request.contextPath}/orgnization/updateAuthInfo.html">公司证件</a></li>
			</ul></li>
		<li class="s zk"><a class="zk"><p>委外检测</p></a>
			<ul class="no" style="display: block;">
				<li><a href="${pageContext.request.contextPath}/order.html">订单管理</a></li>
				<li><a href="${pageContext.request.contextPath}/comment/showMemComments.html?pageNo=1">评价管理</a></li>
				<li><a href="${pageContext.request.contextPath}/inspect.html">报检管理</a></li>
				<li><a href="${pageContext.request.contextPath}/favorite.html">服务收藏</a></li>
			</ul></li>
		<li class="s zk"><a class="zk"><p>企业品控</p></a>
			<ul class="no" style="display: block;"><!-- mem_report_mng -->
				<li><a href="${pageContext.request.contextPath}/report/all_report.html">报告列表</a></li>
				<li><a href="${pageContext.request.contextPath}/report/collect.html">报告收藏</a></li>
				<li><a href="${pageContext.request.contextPath}/reportConfirm/showAllConfirm.html?pageNo=1">报告验真</a></li>
				<!-- ${pageContext.request.contextPath}/qaqc/trustLogin.html -->
				<li><a href="http://qaqc.ajianla.com">内控实验室管理</a></li>
				<li><a href="http://qaqc.ajianla.com/report/manage.html">内控报告列表</a></li>
				<li><a href="${pageContext.request.contextPath}/tuisong.html">推送列表</a></li>
			</ul></li>
		<li class="t"><a class="zk"><p>检测项目管理</p></a>
			<ul class="no">
				<li><a href="${pageContext.request.contextPath}/orgitem.html">检测项目列表</a></li>
				<li><a href="${pageContext.request.contextPath}/orgitem/create.html">项目赋值</a></li>
			</ul></li>
		<li class="si"><a href="#"><p>发布需求</p></a></li>
		
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