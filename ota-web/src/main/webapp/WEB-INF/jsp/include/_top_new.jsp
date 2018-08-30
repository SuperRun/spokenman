<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="top">
		<div class="top_main">
			<div class="top_l">
				<img style="display:none;" class="eclose" id="eclose"
					src="${mystatic}/img/Password_close.png" /> <img class="eopen"
					src="${mystatic}/img/eopen.png" /> 
				<span class="top_0">
				<c:if test="${sessionScope.sessionInfo.loginType==0}"><!-- Orgnization账号 -->
	               	您好，<a href="${pageContext.request.contextPath}/goHome.html"><font style="vertical-align:top;" color="blue">
	               	 <c:if test="${sessionScope.sessionInfo.orgnizationName!=null&&sessionScope.sessionInfo.orgnizationName!=''}">${sessionScope.sessionInfo.orgnizationName}</c:if>
	             	 <c:if test="${sessionScope.sessionInfo.orgnizationName==null||sessionScope.sessionInfo.orgnizationName==''}">尊敬的新机构</c:if>
	               	</font></a>，欢迎使用检啦平台！<!-- ,欢迎您！ -->&nbsp;&nbsp;
	              </c:if>
	              <c:if test="${sessionScope.sessionInfo.loginType==1}"><!-- Member账号 -->
	             	 你好，<a href="${pageContext.request.contextPath}/goHome.html">【<font color="blue">
	             	 ${(sessionScope.sessionInfo.realName!=null&&sessionScope.sessionInfo.realName!='')?sessionScope.sessionInfo.realName:"尊敬的新会员"}
	             	 </font>】</a>，欢迎使用检啦平台！<!-- ,欢迎您！ -->&nbsp;&nbsp;
	              </c:if>
             	</span>
			</div>
			<div class="top_r">
				<span id="back2index" class="top_0"><a href="${pageContext.request.contextPath}/">回到首页</a></span>
				<span id="showChannel" style="font-family: 微软雅黑;font-size: 14px;color:#666;cursor: pointer;margin: auto;">热门栏目</span>
				<div id="channelMenu" style="top:38px;" class="downmenu"><ul id="channelList"><li></ul></div>
				<span class="top_1"><a href="${pageContext.request.contextPath}/report/searchCommonReport?showId=">公开报告查询</a>
				</span> 
					<c:if test="${empty sessionScope.sessionInfo}">
						<!-- href="${pageContext.request.contextPath}/login.html" onclick="openLoginFrame()" -->
						<span class="top_2"><a href="${pageContext.request.contextPath}/login.html">用户登录</a></span>
					<span class="top_3"><a href="${pageContext.request.contextPath}/register.html">免费注册</a></span>
					</c:if>
					<c:if test="${! empty sessionScope.sessionInfo}">
						<span class="top_2"><a href="${pageContext.request.contextPath}/logout.html">退出登录</a></span>
						<input type="hidden" id="topOrgId" value="${sessionScope.sessionInfo.orgnizationId }" >
						<span class="top_3"><a href="${pageContext.request.contextPath}/register.html">免费注册</a></span>
			         </c:if>

			</div>
		</div>
</div>


<div id="miniLogin" class="mini-login" style="display: hidden; top: 30%; left: 42%;">
	
</div>


<script>
function loginSuccess(){
	closeLoginFrame();
	location.reload();
}
function closeLoginFrame(){
	$('#miniLogin').css('display','none');
}
function openLoginFrame(){
	$('#miniLogin').html('<div class="mnl-bg"></div><div class="mnl-box"><div class="mnl-hd">账户登录</div><div class="mnl-bd"><iframe class="mnl-ifr" scrolling="no" frameborder="no" src="${pageContext.request.contextPath}/mini_login.html"></iframe></div>'
			+'<div class="mnl-ft"><a class="mnl-close" id="closeMiniLoginBtn" target="_self" title="关闭此窗口" href="javascript:void(0)" onclick="closeLoginFrame()">×</a></div></div>');
	$('#miniLogin').css('display','block');
}
function checkSessoin(){
	var ret=false;
	$.ajax({
		async:false,
		type:'GET',
		url:'${pageContext.request.contextPath}/validate/hasSession.json',
		success:function(data){
			if(typeof(data)!="object") data=JSON.parse(data);
			ret=data;
		}
	});
	return ret;
}
$(function(){
	$.ajax({
		async:true,
		type:'GET',
		url:'${pageContext.request.contextPath}/channel/getChannelType.html',
		success:function(data){
			if(typeof(data)!="object") data=JSON.parse(data);
			console.log(data);
			$.each(data.children,function(i,treeNode){
				//console.log(treeNode);
				var li=$("<li>").appendTo($("#channelList"));
				var a=$("<a>").prop("href",treeNode.url).text(treeNode.title).appendTo(li);
				var div;
				var dl;
				$.each(treeNode.children,function(i,tree2){
					if(i==0){
						 div=$("<div>").addClass("d_erji").appendTo(li);
						 dl=$("<dl>").addClass("erji").appendTo(div);
					}
					var a2=$("<a>").prop("href",tree2.url).text(tree2.title);
					$("<dd>").append(a2).appendTo(dl);
				});

			});
		}
	});

	$("#channelMenu").delegate("ul li", "mouseover", function ( e ) {
		$(this).find("div").show();
	});
	$("#channelMenu").delegate("ul li", "mouseout", function ( e ) {
		$(this).find("div").hide();
	});

	var sign1=true;
	var sign2=true;

	$("#showChannel").mouseover(function(){
		$("#channelMenu").css("left",$('#showChannel').offset().left);
		$("#channelMenu").show();
		sign1=false;
	})


	$("#channelMenu").mouseover(function(){
		$("#channelMenu").css("left",$('#showChannel').offset().left);
		$("#channelMenu").show();
	})
	$("#channelMenu").mouseout(function(){
		sign2=true;
		$("#channelMenu").hide();
	})
	function check() {
		if (sign1 && sign2) {
			$("#channelMenu").hide();
		}
	}
})

</script>