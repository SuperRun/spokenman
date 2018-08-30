<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<!-- 管理员 ../include/_admin_left.jsp -->
	<c:if test="${sessionScope.sessionInfo.orgnizationType==1}">
		<%@include file="../include/_provider_left.jsp"%>
	</c:if>
	<!-- 企业 -->
	<c:if test="${sessionScope.sessionInfo.orgnizationType==2||sessionScope.sessionInfo.orgnizationType==3}">
		<%@include file="../include/_consumer_left.jsp"%>
	</c:if>
	<!-- 机构 -->
	<c:if
		test="${sessionScope.sessionInfo.orgnizationType>=4&&sessionScope.sessionInfo.orgnizationType<=8}">
		<%@include file="../include/_provider_left.jsp"%>
	</c:if>
	
	<!-- 营销机构 -->
	<c:if test="${sessionScope.sessionInfo.orgnizationType==9}">
		<%@include file="../include/_partner_left.jsp"%>
	</c:if>