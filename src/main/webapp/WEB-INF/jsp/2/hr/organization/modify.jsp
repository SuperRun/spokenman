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
    <title>修改机构资料_基于B/S程序的无纸化网络考试系统</title>
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
                    <div class="block-heading"><span>机构基本信息</span></div>
                    <div class="block-body">
                        <div class="col-md-12">
                            <form class="form-horizontal" id="organizationInfo">
                                <div v-if="org">
                                    <div class="form-group">
                                        <div class="col-md-6">
                                            <label for="orgName" class="text_title">机构全称*：</label>
                                            <input type="text" id="orgName" class="form_input" placeholder="所创建机构的全称"
                                                   v-model="org.name">
                                        </div>

                                        <div class="col-md-6">
                                            <label for="region" class="text_title">机构区域*：</label>
                                            <input type="text" id="region" class="form_input" placeholder="点击选择"
                                                   onclick="$('#regionSelectModal').modal('show')"
                                                   v-model="org.region">
                                        </div>
                                    </div>
                                    <div class="form-group">

                                        <div class="col-md-6">
                                            <label for="shortName" class="text_title">机构简称：</label>
                                            <input type="text" id="shortName" class="form_input" placeholder="所创建机构的简称"
                                                   v-model="org.shortName">
                                        </div>
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


                                </div>
                                <div id="carCompany" v-if="orgBiId">
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
                                </div>

                                <div class="form-group text-center">
                                    <button type="button" class="btn-custom btn-long" @click="submit()">保存修改
                                    </button>
                                </div>
                            </form>
                        </div>

                        <div class="modal fade" id="regionSelectModal" tabindex="-1" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"><span
                                                aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                        </button>
                                        <h4 class="modal-title" id="myModalLabel">选择区域
                                            <small><a href="${path}/organization/region" target="_blank">行政区管理</a>
                                            </small>
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <label>省</label>
                                                <select class="form-control" v-model="region.province.select">
                                                    <option v-for="r in region.province.list" :value="r.id"
                                                            v-text="r.name"></option>
                                                </select>
                                            </div>
                                            <div class="col-md-4"
                                                 v-if="orgType.type == orgType.district || orgType.type == orgType.city||orgType.type == orgType.company">
                                                <label>市</label>
                                                <select class="form-control" v-model="region.city.select">
                                                    <option v-for="r in region.city.list" :value="r.id"
                                                            v-text="r.name"></option>
                                                </select>
                                            </div>
                                            <div class="col-md-4"
                                                 v-if="orgType.type == orgType.district || orgType.type == orgType.company  ">
                                                <label>区县</label>
                                                <select class="form-control" v-model="region.district.select">
                                                    <option v-for="r in region.district.list" :value="r.id"
                                                            v-text="r.name"></option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                        <button type="button" class="btn btn-primary" data-dismiss="modal"
                                                @click="setRegionId()">确定
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../../2/include/_html_body.jsp" %>
<script>
    var orgId = '${orgId}';
    var orgBiId = '${orgBiId}';
</script>

<script src="${path}/media/2/js/organization/modify.js"></script>


</body>
</html>
