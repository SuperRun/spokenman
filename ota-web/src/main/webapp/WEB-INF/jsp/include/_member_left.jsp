<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 
<div class="col-md-2" style="padding:0px; width:220px;">
          <div class="list-group" style="margin-bottom: 2px;">
            <a href="#" class="list-group-item active">我的功能 </a>
            <c:if test="${!empty sessionScope.sessionInfo.authMenus }">
            	<c:forEach var="menuNode" items="${sessionScope.sessionInfo.authMenus}">
            	<a href="${pageContext.request.contextPath}/${menuNode.attributes.url}" class="list-group-item">${menuNode.text}</a>
            </c:forEach>
            </c:if>
          </div>
 </div>
  -->
<link href="../css/_provider_left.css" type="text/css" rel="stylesheet"/>

<div class="main_l">
	<h1>
		<a href="${pageContext.request.contextPath}/member/homepage.html">个人中心</a>
	</h1>
	<ul class="list">
		<c:if test="${!empty sessionScope.sessionInfo.authMenus }">
           	<c:forEach var="menuParentNode" items="${sessionScope.sessionInfo.authMenus}" varStatus="status">
           		<li class="d${ status.index + 1}"><a class="zk"><p>${menuParentNode.text }</p></a>
           			<ul class="no">
           				<c:forEach var="menuNode" items="${menuParentNode.children }">
           					<li><a href="${pageContext.request.contextPath}/${menuNode.attributes.url}">${menuNode.text }</a></li>
           				</c:forEach>
           			</ul>
           		</li> 
            </c:forEach>
         </c:if>
			</ul>
</div>


<script>
$(function(){
	$(".main_l ul.list a.zk").click(function(){
		var t=$(this).parent().find(".no");
		if(t.is(":hidden")){
			t.show();
			//console.log("show");
		}else{
			t.hide();
		}
	});
})
</script>
  