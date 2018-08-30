<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%--用户状态、类型--%>
<%@ page import="com.zust.itee.exam.enums.LoginTypeEnum" %>
<%@ page import="com.zust.itee.exam.enums.OrganizationTypeEnum" %>


<c:set var="loginTypeEnumDriver" value="<%=LoginTypeEnum.DRIVER%>"/>
<c:set var="loginTypeEnumMember" value="<%=LoginTypeEnum.MEMBER%>"/>
<c:set var="OrganizationTypePROVINCE" value="<%=OrganizationTypeEnum.PROVINCE%>"/>
<c:set var="OrganizationTypeCITY" value="<%=OrganizationTypeEnum.CITY%>"/>
<c:set var="OrganizationTypeDISTRICT" value="<%=OrganizationTypeEnum.DISTRICT%>"/>
<c:set var="OrganizationTypeCOMPANY" value="<%=OrganizationTypeEnum.COMPANY%>"/>


<%--已登录--%>
<c:set var="logined" value="${sessionScope.sessionInfo != null and sessionScope.sessionInfo.userId > 0}"/>
<%--登录用户类型--%>
<%--员工--%>
<c:set var="loginedMember" value="${logined and loginTypeEnumMember eq sessionScope.sessionInfo.loginType}"/>
<%--驾驶员--%>
<c:set var="loginedDriver" value="${logined and loginTypeEnumDriver eq sessionScope.sessionInfo.loginType}"/>


<%--员工类型--%>
<%--省散装办员工--%>
<c:set var="loginedMemberProvince"
       value="${loginedMember and OrganizationTypePROVINCE eq sessionScope.sessionInfo.organizationType}"/>
<%--市散装办员工--%>
<c:set var="loginedMemberCity" value="${loginedMember and OrganizationTypeCITY eq sessionScope.sessionInfo.organizationType}"/>
<%--区散装办员工--%>
<c:set var="loginedMemberDistrict"
       value="${loginedMember and OrganizationTypeDISTRICT eq sessionScope.sessionInfo.organizationType}"/>
<%--车企员工--%>
<c:set var="loginedMemberCompany" value="${loginedMember and OrganizationTypeCOMPANY eq sessionScope.sessionInfo.organizationType}"/>