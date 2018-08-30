<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="main_l">
	<h1>
		<a href="${pageContext.request.contextPath}/homepage.html">会员中心</a>
	</h1>
	<ul class="list">
		<li class="d1"><a href="${pageContext.request.contextPath}/orgnization/password.html"><p>修改账户密码</p></a></li>
		
				<li class="d2"><a href="${pageContext.request.contextPath}/orgnization/updateAccountInfo.html"><p>账号信息</p></a></li>
				<li class="d3"><a href="${pageContext.request.contextPath}/orgnization/updateOrgInfo.html"><p>公司资料</p></a></li>
				<li class="d4"><a href="${pageContext.request.contextPath}/finance.html"><p>财务信息</p></a></li>
			
		<li class="t"><a href="${pageContext.request.contextPath}/cooperate/showResult.html"><p>使用详情</p></a></li>
		
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