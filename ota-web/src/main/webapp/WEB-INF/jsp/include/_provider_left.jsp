<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="main_l">
	<h1>
		<a href="${pageContext.request.contextPath}/orgnization/providerHomepage.html">机构中心</a>
	</h1>
	<ul class="list">
		<li class="d1"><a href="${pageContext.request.contextPath}/orgnization/password.html"><p>修改账户密码</p></a></li>
		<li class="d2"><a class="zk"><p>机构账号信息</p></a> <!--类名zk,zk2是左侧导航进入页面的样式，点击进入的时候，ul应是展开状态，并且相应的li的颜色是白色的-->
			<ul class="no">
				<li><a href="${pageContext.request.contextPath}/orgnization/updateAccountInfo.html">账号信息</a></li>
				<li><a href="${pageContext.request.contextPath}/orgnization/updateLinkmanInfo.html">联系人资料</a></li>
				<li><a href="${pageContext.request.contextPath}/orgnization/updateOrgInfo.html">机构资料</a></li>
				<li><a href="${pageContext.request.contextPath}/express.html">物流信息</a></li>
				<li><a href="${pageContext.request.contextPath}/finance.html">财务信息</a></li>
				<li><a href="${pageContext.request.contextPath}/orgnization/updateAuthInfo.html">认证信息</a></li>
			</ul></li>
		<li class="d3"><a class="zk"><p>组织机构管理</p></a>
			<ul class="no">
				<li><a href="${pageContext.request.contextPath}/department.html">机构部门管理</a></li>
				<li><a href="${pageContext.request.contextPath}/member.html">员工账号管理</a></li>
			</ul></li>
		<li class="d4"><a class="zk"><p>旺铺装修</p></a>
			<ul class="no">
				<li><a href="${pageContext.request.contextPath}/shop/zhuangxiu.html">旺铺装修</a></li>
				<li><a href="${pageContext.request.contextPath}/shop/newfuwu.html">最新服务推荐</a></li>
				<li><a href="${pageContext.request.contextPath}/shop/hotfuwu.html">热卖服务推荐</a></li>
				<li><a href="#">文件资料管理</a></li>
				<li><a href="#">图片空间管理</a></li>
				<li><a href="${pageContext.request.contextPath}/news/saveNews.html">发布资讯</a></li>
				<li><a href="${pageContext.request.contextPath}/news/news_mng.html">资讯管理</a></li>
			</ul></li>
		<li class="d5"><a class="zk"><p>检测项目管理</p></a>
			<ul class="no">
				<li><a href="${pageContext.request.contextPath}/orgitem.html">检测项目列表</a></li>
				<li><a href="${pageContext.request.contextPath}/orgitem/create.html">项目赋值</a></li>
				<!-- 
				<li><a href="${pageContext.request.contextPath}/item/org_item_back.html">缺失项目名称反馈</a></li>
				<li><a href="${pageContext.request.contextPath}/standard/org_standard_back.html">缺失标准反馈</a></li>
				 -->
			</ul></li>
		<li class="d6"><a class="zk"><p>服务管理</p></a>
			<ul class="no">
				<li><a href="${pageContext.request.contextPath}/service.html">服务列表</a></li>
				<li><a href="${pageContext.request.contextPath}/service/orgServiceTypesInDDMaintain.html">服务类别自定义</a></li>
				<li><a href="${pageContext.request.contextPath}/service/create.html">发布服务</a></li>
				<li><a href="${pageContext.request.contextPath}/extConfig/jdConfig.html">京东对接</a></li>
				<li><a href="${pageContext.request.contextPath}/extConfig/tbConfig.html">淘宝对接</a></li>
				<li><a href="${pageContext.request.contextPath}/extConfig/snConfig.html">苏宁对接</a></li>
			</ul></li>
		<li class="d7"><a href="${pageContext.request.contextPath}/order.html"><p>订单管理</p></a></li>
		<li class="d8"><a href="${pageContext.request.contextPath}/comment/showOrgComments.html?pageNo=1"><p>评价管理</p></a></li>
		<li class="d9"><a href="${pageContext.request.contextPath}/inspect.html"><p>报检管理</p></a></li>
		<li class="d9"><a href="${pageContext.request.contextPath}/ext/abnormalInspections.html"><p>异常报检处理</p></a></li>
<!-- 		<li class="d10"><a href="Finance.html"><p>财务管理</p></a></li> -->
		<li class="d11"><a class="zk"><p>报告管理</p></a>
			<ul class="no">
				<li><a href="${pageContext.request.contextPath}/report/mng.html">报告列表</a></li>
				<li><a href="${pageContext.request.contextPath}/reportConfirm/showOrgConfirm.html">报告验真</a></li>
			</ul></li>
			<!-- ${pageContext.request.contextPath}/elims/trustLogin.html -->
		<li class="d12"><a href="http://elims.ajianla.com"><p>实验室检测管理</p></a></li>
		<li class="d13"><a class="zk"><p>报检客户管理</p></a>
		<ul class="no">
				<li><a href="${pageContext.request.contextPath}/sales/customerList.html">客户管理</a></li>
				<li><a href="${pageContext.request.contextPath}/sales/salesResults.html">销售业绩统计</a></li>
			</ul></li>
<!-- 		<li class="d14"><a href="${pageContext.request.contextPath}/Analysis.html"><p>数据统计分析</p></a></li> -->
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