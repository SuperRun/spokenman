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
    <title>${title}_基于B/S程序的无纸化网络考试系统</title>
    <link href="${path}/media/2/css/hr_new.css" rel="stylesheet">

    <link href="http://cdn.bootcss.com/zTree.v3/3.5.26/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet">

</head>
<body>
<%@include file="../../include/_member_header.jsp" %>

<div class="container">

    <div class="content">
        <div class="row">
            <div class="col-md-4">
                <div id="ztree" class="ztree" style="margin-bottom: 500px;"></div>
            </div>

            <div class="col-md-8" id="orgInfo">
                <div class="block block-two" v-if="org">
                    <!-- Default panel contents -->
                    <div class="block-heading"><span v-if="org" v-text="org.name?org.name:'-'"></span>
                        <%--<a target="_blank" v-if="org" :href="org?path+'/organization/'+org.id:'#'">&nbsp;详情</a>--%>

                        <a target="_blank" v-if="org && org.type=='区县散装办'"
                           :href="org?path+'/organization/newchild/'+org.id:'#'">&nbsp;创建车企</a>
                        <a v-if="org" v-show="!show.org" @click="showOrg()" style="cursor: hand">&nbsp;详情</a>
                        <a v-if="org" v-show="show.org" @click="hideOrg()" style="cursor: hand">&nbsp;收起</a>

                    </div>
                    <div class="block-body">
                        <div class="col-md-12" v-show="show.org">
                            <table class="table table-striped table_info table-bordered" id="organizationInfo"
                                   v-if="org">
                                <thead>
                                <tr>
                                    <th colspan="4">机构基本信息

                                    </th>

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
                                    <td colspan="3" v-text="company.legalPersonEmail?company.legalPersonEmail:'-'"></td>
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

                        <!--搜索 start-->
                        <div class="col-md-12" v-if="org" style="margin-bottom: 10px">
                            <input type="text" class="form_input" v-model="search.keywd" @keyup.enter="searchCompany()">
                            <button class="btn btn-primary" @click="searchCompany()">搜索</button>
                        </div>
                        <!--搜索 end-->
                        <!--搜索结果 start-->
                        <div class="col-md-12" v-if="org">
                            <table class="table table-striped table_info table-bordered" v-if="search.list">
                                <thead>
                                <tr>
                                    <th style="text-align: center">
                                        序号(共<span
                                            v-text="search.resultList?search.resultList.length:search.list.length"></span>)
                                    </th>
                                    <th style="text-align: center">名称</th>
                                    <th style="text-align: center">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%--<tr v-for="(o,index) in search.list">--%>
                                <tr v-for="(o,index) in page.list">
                                    <%--<tr v-for="(o,index) in search.resultList?search.resultList:search.list">--%>
                                    <td style="text-align: center" v-text="o.index"></td>
                                    <td v-text="o.name"></td>
                                    <td>
                                        <a class="btn btn-primary btn-xs" target="_blank"
                                           :href="path+'/organization/'+o.id">详情</a>
                                        <a class="btn btn-primary btn-xs" target="_blank"
                                           :href="path+'/organization/'+o.id+'/modify'">修改</a>
                                        <button class="btn btn-primary btn-xs" @click="orgDisable = o"
                                                data-toggle="modal" data-target="#delete_Modal">
                                            删除
                                        </button>

                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <nav>
                                <ul class="pager">
                                    <li><a @click="page2(page.cur-1)" style="cursor: hand">上一页</a></li>
                                    <span v-text="page.cur"></span>/
                                    <span v-text="page.all"></span>
                                    <li><a @click="page2(page.cur+1)" style="cursor: hand">下一页</a></li>
                                </ul>
                            </nav>
                        </div>
                        <!--搜索结果 end-->
                    </div>
                </div>

                <!--cancel modal start-->
                <div class="modal fade" id="delete_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="detailModalLabel">删除确认</h4>
                            </div>
                            <div class="modal-body" v-if="org">
                                是否确定删除 [{{orgDisable?orgDisable.name:org.name}}]？
                                <span style="color: red;"> 此操作不可逆！</span>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default btn-middle" data-dismiss="modal">关闭
                                </button>
                                <button type="button" class="btn btn-primary btn-middle" @click="disableOrg()"
                                        data-dismiss="modal">
                                    确认删除
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!--cancel modal end-->

            </div>

        </div>
    </div>
</div>
<%@include file="../../include/_html_body.jsp" %>
<script src="http://cdn.bootcss.com/zTree.v3/3.5.26/js/jquery.ztree.core.min.js"></script>
<script>
    var tree = $.parseJSON('${ztree}');
</script>
<script src="${path}/media/2/js/organization/list2.js"></script>
</body>
</html>
