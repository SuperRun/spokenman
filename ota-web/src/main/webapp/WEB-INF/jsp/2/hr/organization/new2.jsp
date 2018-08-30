<%--
  Created by IntelliJ IDEA.
  User: dxb
  Date: 12/7/2016
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../2/include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../../2/include/_html_head.jsp" %>
    <title>创建[${org.name}]的子机构_基于B/S程序的无纸化网络考试系统</title>
    <link href="${path}/media/2/css/hr_new.css" rel="stylesheet">

</head>
<body>
<%@include file="../../../2/include/_member_header.jsp" %>

<div class="container">
    <div class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="block block-two">
                    <!-- Default panel contents -->
                    <div class="block-heading"><span>创建[${org.name}]的子机构</span></div>
                    <div class="block-body">
                        <div class="col-md-12">
                            <form class="form-horizontal" id="organizationInfo">
                                <div class="form-group">
                                    <div class="col-md-6">
                                        <label for="orgName" class="text_title">机构全称*：</label>
                                        <input type="text" id="orgName" class="form_input" placeholder="所创建机构的全称"
                                               v-model="org.name">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="shortName" class="text_title">机构简称：</label>
                                        <input type="text" id="shortName" class="form_input" placeholder="所创建机构的简称"
                                               v-model="org.shortName">
                                    </div>
                                </div>
                                <div class="form-group">

                                    <div class="col-md-6">
                                        <label for="linkman" class="text_title">联系人姓名：</label>
                                        <input type="text" id="linkman" class="form_input" placeholder="联系人姓名"
                                               v-model="org.linkmanName">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-md-6">
                                        <label for="orgTel" class="text_title">联系人电话：</label>
                                        <input type="text" id="orgTel" class="form_input" placeholder="联系人电话"
                                               v-model="org.tel">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="orgEmail" class="text_title">联系人Email：</label>
                                        <input type="email" id="orgEmail" class="form_input" placeholder="联系人Email"
                                               v-model="org.email">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <label for="orgDescribe" class="text_title"
                                               style="vertical-align: top;">机构描述：</label>
                                        <textarea id="orgDescribe" class="form_textarea" placeholder="对所创建机构的描述"
                                                  v-model="org.description"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-md-6">
                                        <label for="directorId" class="text_title">主管用户名*：</label>
                                        <input type="text" id="directorId" class="form_input" placeholder="主管用户名"
                                               v-model="org.loginName">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="directorName" class="text_title">主管姓名*：</label>
                                        <input type="text" id="directorName" class="form-control form_input"
                                               placeholder="主管姓名" v-model="org.lpName">
                                    </div>

                                </div>
                                <div class="form-group">
                                    <div class="col-md-6">
                                        <label for="directorTel" class="text_title">主管联系方式*：</label>
                                        <input type="text" id="directorTel" class="form_input" placeholder="主管联系方式"
                                               v-model="org.lpTel">
                                    </div>
                                    <div class="col-md-6">
                                        <label class="text_title">密码：</label>
                                        <input type="password" class="form_input" v-model="org.password">
                                    </div>
                                </div>

                                <%--如果创建的子机构是车企--%>
                                <c:set value="<%=OrganizationTypeEnum.COMPANY%>" var="orgCOMPANY"/>
                                <c:if test="${orgCOMPANY eq childOrgType}">
                                <div id="carCompany">
                                    <div class="form-group">
                                        <div class="col-md-6">
                                            <label for="leaderName" class="text_title">分管领导姓名：</label>
                                            <input type="text" id="leaderName" class="form_input" placeholder="分管领导姓名"
                                                   v-model="org.leaderName">
                                        </div>
                                        <div class="col-md-6">
                                            <label for="leaderTel" class="text_title">分管领导电话：</label>
                                            <input type="text" id="leaderTel" class="form_input" placeholder="分管领导电话"
                                                   v-model="org.leaderTel">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-6">
                                            <label for="leaderEmail" class="text_title">分管领导Email：</label>
                                            <input type="email" id="leaderEmail" class="form_input"
                                                   placeholder="分管领导Email"
                                                   v-model="org.leaderEmail">
                                        </div>
                                    </div>
                                    <div>
                                        <div class="form-group">
                                            <div class="col-md-6">
                                                <label for="legalPerson" class="text_title">法人代表姓名：</label>
                                                <input type="text" id="legalPerson" class="form_input"
                                                       placeholder="法人代表姓名"
                                                       v-model="org.legalPerson">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="legalPersonTel" class="text_title">法人代表电话：</label>
                                                <input type="text" id="legalPersonTel" class="form_input"
                                                       placeholder="法人代表电话"
                                                       v-model="org.legalPersonTel">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-6">
                                                <label for="legalPersonEmail" class="text_title">法人代表Email：</label>
                                                <input type="text" id="legalPersonEmail" class="form_input"
                                                       placeholder="法人代表Email"
                                                       v-model="org.legalPersonEmail">
                                            </div>
                                        </div>


                                    </div>
                                    </c:if>

                                    <div class="form-group text-center">
                                        <button type="button" class="btn-custom btn-long" @click="submit()">创建</button>
                                    </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../../2/include/_html_body.jsp" %>

<script>
    var parentId = '${org.id}', orgType = '${childOrgType.type}';
</script>

<script src="${path}/media/2/js/organization/new2.js"></script>


</body>
</html>
