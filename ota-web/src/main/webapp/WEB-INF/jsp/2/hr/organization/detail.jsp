<%--
  Created by IntelliJ IDEA.
  User: dxb
  Date: 12/7/2016
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../include/_html_head.jsp" %>
    <title>机构详情_基于B/S程序的无纸化网络考试系统</title>
</head>
<body>
<%@include file="../../include/_member_header.jsp" %>
<div id="app">

    <div class="container">

        <div class="content">
            <div class="row">
                <div class="col-md-12" id="orgInfo">
                    <div class="block block-two">
                        <!-- Default panel contents -->
                        <div class="block-heading"><span>机构信息</span>


                            <a v-if="company" data-toggle="modal" data-target="#new_Modal">设置车队长</a>

                            <c:if test="${loginedMemberProvince}">
                                <a v-if="org" :href="path+'/organization/'+org.id+'/modify'">修改信息&nbsp;</a>
                            </c:if>
                        </div>
                        <div class="block-body">
                            <div class="col-md-12">

                                <table class="table table-striped table_info table-bordered" id="organizationInfo"
                                       v-if="org">
                                    <thead>
                                    <tr>
                                        <th colspan="4">机构基本信息</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td style="min-width: 120px;">组织机构类型</td>
                                        <td v-text="org.type"></td>
                                        <td>上级机构</td>
                                        <td v-text="org.parentOrg?org.parentOrg.name:'-'"></td>
                                    </tr>
                                    <tr>
                                        <td>机构全称</td>
                                        <td v-text="org.name?org.name:'-'"></td>
                                        <td>机构简称</td>
                                        <td v-text="org.shortName?org.shortName:'-'"></td>
                                    </tr>
                                    <tr>
                                        <td>主管姓名</td>
                                        <td v-text="org.memberName?org.memberName:'-'"></td>

                                        <td>主管联系电话</td>
                                        <td v-text="org.memberTel?org.memberTel:'-'"></td>
                                    </tr>
                                    <tr>
                                        <td>联系人姓名</td>
                                        <td v-text="org.linkmanName?org.linkmanName:'-'"></td>


                                        <td>联系电话</td>
                                        <td v-text="org.tel?org.tel:'-'"></td>


                                    </tr>
                                    <tr>
                                        <td>Email</td>
                                        <td colspan="3" v-text="org.email?org.email:'-'"></td>
                                    </tr>
                                    <tr>
                                        <td>机构描述</td>
                                        <td colspan="3" v-text="org.description?org.description:'-'"></td>
                                    </tr>

                                    <tr v-if="company">
                                        <td>分管领导姓名</td>
                                        <td v-text="company.leaderName?company.leaderName:'-'"></td>
                                        <td>分管领导电话</td>
                                        <td v-text="company.leaderTel?company.leaderTel:'-'"></td>


                                    </tr>
                                    <tr v-if="company">
                                        <td>分管领导Email</td>
                                        <td colspan="3" v-text="company.leaderEmail?company.leaderEmail:'-'"></td>
                                    </tr>


                                    <tr v-if="company">
                                        <td>企业法人姓名</td>
                                        <td v-text="company.legalPerson?company.legalPerson:'-'"></td>
                                        <td>企业法人电话</td>
                                        <td v-text="company.legalPersonTel?company.legalPersonTel:'-'"></td>


                                    </tr>
                                    <tr v-if="company">
                                        <td>企业法人Email</td>
                                        <td colspan="3"
                                            v-text="company.legalPersonEmail?company.legalPersonEmail:'-'"></td>
                                    </tr>


                                    <tr v-if="company">
                                        <td>车队长姓名</td>
                                        <td v-text="company.driverName?company.driverName:'-'"></td>
                                        <td>车队长电话</td>
                                        <td v-text="company.driverTel?company.driverTel:'-'"></td>


                                    </tr>
                                    <tr v-if="company">
                                        <td>车队长Email</td>
                                        <td colspan="3" v-text="company.driverEmail?company.driverEmail:'-'"></td>
                                    </tr>

                                    </tbody>
                                </table>


                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>


    </div>

    <div class="modal fade" id="new_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="examInfoModalLabel">选择车队长</h4>
                </div>
                <div class="modal-body">


                    <div class="row">
                        <div class="col-md-12">
                            <select class="form-control" v-model="driver.select">
                                <option v-for="d in driver.list" :value="d.id" v-text="d.name"></option>
                            </select>
                        </div>
                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-middle" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary btn-middle" data-dismiss="modal" @click="setDriver()">
                        设置
                    </button>

                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../include/_html_body.jsp" %>
<script src="${path}/media/2/js/organization/detail.js"></script>
<script>
    showOrgInfo('${orgId}')
</script>
</body>
</html>
