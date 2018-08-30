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
    <title>创建子机构_基于B/S程序的无纸化网络考试系统</title>
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
                                <div class="form-group">
                                    <div class="col-md-6">
                                        <label for="districtOrg" class="text_title">自身机构：</label>
                                        <input type="text" id="districtOrg" class="form_input form-control"
                                               disabled="disabled" value="${org.name}">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="orgName" class="text_title">机构全称：</label>
                                        <input type="text" id="orgName" class="form_input" placeholder="所创建机构的全称"
                                               v-model="org.name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-6">
                                        <label for="shortName" class="text_title">机构简称：</label>
                                        <input type="text" id="shortName" class="form_input" placeholder="所创建机构的简称"
                                               v-model="org.shortName">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="directorId" class="text_title">主管用户名：</label>
                                        <input type="text" id="directorId" class="form_input" placeholder="主管用户名"
                                               v-model="org.loginName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-6">
                                        <label for="directorName" class="text_title">主管姓名：</label>
                                        <input type="text" id="directorName" class="form-control form_input"
                                               placeholder="主管姓名" v-model="org.lpName">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="directorTel" class="text_title">联系方式：</label>
                                        <input type="text" id="directorTel" class="form_input" placeholder="联系方式"
                                               v-model="org.tel">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-6">
                                        <label for="directorEmail" class="text_title">Email：</label>
                                        <input type="email" id="directorEmail" class="form_input" placeholder="Email"
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
                                <%--如果创建的子机构是车企--%>
                                <c:set value="<%=OrganizationTypeEnum.COMPANY%>" var="orgCOMPANY"/>
                                <c:if test="${orgCOMPANY eq childOrgType}">
                                    <div id="carCompany">
                                        <div class="form-group">
                                            <div class="col-md-6">
                                                <label for="lrName" class="text_title">法人代表姓名：</label>
                                                <input type="text" id="lrName" class="form_input" placeholder="法人代表姓名"
                                                       v-model="org.legalPerson">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="capital" class="text_title">总注册资本：</label>
                                                <input type="number" id="capital" class="form_input" placeholder="总注册资本"
                                                       v-model="org.capital">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label for="licenseNum" class="text_title">营业执照编号：</label>
                                                <input type="text" id="licenseNum" class="form_textarea"
                                                       placeholder="营业执照编号" v-model="org.businessLicense">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label for="licenseScanner" class="text_scanner">营业执照扫描件：</label>
                                                <div class="scanner_input">
                                                    <input type="text" name="file" id="licenseScanner"
                                                           class="form_input" placeholder="点击上传"
                                                           onclick="$('#uploadbusinessphoto').click()">
                                                    <input type="hidden" id="businessLicensePhoto"/>
                                                    <div class="preview">
                                                        <a target="_blank" id="businessLicensePhotoA">
                                                            <img id="businessLicensePhotoImg"/>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label for="taxNum" class="text_title">税务登记证号：</label>
                                                <input type="text" id="taxNum" class="form_textarea"
                                                       placeholder="税务登记证号" v-model="org.taxCode">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label for="taxScanner" class="text_scanner">税务登记证扫描件：</label>
                                                <div class="scanner_input">
                                                    <input type="text" name="file" class="form_input" placeholder="点击上传"
                                                           id="taxScanner" onclick="$('#uploadtaxCodePhoto').click();">
                                                    <input type="hidden" id="taxCodePhoto"/>

                                                    <div class="preview"><a id="taxCodePhotoA" target="_blank">
                                                        <img id="taxCodePhotoImg"></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label for="creditNum" class="text_title">机构信用证号：</label>
                                                <input type="text" id="creditNum" class="form_textarea"
                                                       v-model="org.creditCode"
                                                       placeholder="机构信用证号">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label for="creditScanner" class="text_scanner">机构信用证扫描件：</label>
                                                <div class="scanner_input">
                                                    <input type="text" name="file" class="form_input" placeholder="点击上传"
                                                           id="creditScanner"
                                                           onclick="$('#uploadcreditCodePhoto').click()">
                                                    <input type="hidden" id="creditCodePhoto">
                                                    <div class="preview">
                                                        <a id="creditCodePhotoA" target="_blank">
                                                            <img id="creditCodePhotoImg">
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label for="orgNum" class="text_title">组织机构证号：</label>
                                                <input type="text" id="orgNum" class="form_textarea"
                                                       v-model="org.orgCode"
                                                       placeholder="组织机构证号">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label for="orgScanner" class="text_scanner">组织机构证扫描件：</label>
                                                <div class="scanner_input">
                                                    <input type="text" name="file" class="form_input" placeholder="点击上传"
                                                           id="orgScanner" onclick="$('#uploadorgCodePhoto').click()">
                                                    <input type="hidden" id="orgCodePhoto">
                                                    <div class="preview"><a id="orgCodePhotoA"
                                                                            target="_blank"><img
                                                            id="orgCodePhotoImg"></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label for="safetyNum" class="text_title"
                                                       style="vertical-align: top;text-align: left;">安全生产许可证号：</label>
                                                <input type="text" id="safetyNum" class="form_textarea"
                                                       v-model="org.safeProductionLicenceCode"
                                                       placeholder="安全生产许可证号">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label for="safetyScanner" class="text_scanner">安全生产许可证扫描件：</label>
                                                <div class="scanner_input">
                                                    <input type="text" name="file" class="form_input" placeholder="点击上传"
                                                           id="safetyScanner"
                                                           onclick="$('#uploadsafeProductionLicencePhoto').click()">
                                                    <input type="hidden" id="safeProductionLicencePhoto">
                                                    <div class="preview"><a id="safeProductionLicencePhotoA"
                                                                            target="_blank"><img
                                                            id="safeProductionLicencePhotoImg"></a>
                                                    </div>
                                                </div>
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
<script src="${path}/media/js/bootstrap-filestyle.min.js"></script>
<script src="${path}/media/2/js/organization/new.js"></script>

<%--文件上传--%>
<script src="${path}/media/js/jquery.form.js"></script>
<script src="${path}/media/2/js/common-upload.js"></script>
<form action="<%=path %>/up/businessphoto" id="uploadbusinessphotoForm" method="post"
      enctype="multipart/form-data" style="display:none">
    <input id="uploadbusinessphoto" type="file" name="uploadbusinessphoto">
</form>
<form action="<%=path %>/up/businessphoto" id="uploadtaxCodePhotoForm" method="post"
      enctype="multipart/form-data" style="display:none">
    <input id="uploadtaxCodePhoto" type="file" name="uploadtaxCodePhoto">
</form>
<form action="<%=path %>/up/businessphoto" id="creditCodePhotoForm" method="post"
      enctype="multipart/form-data" style="display:none">
    <input id="uploadcreditCodePhoto" type="file" name="creditCodePhoto">
</form>
<form action="<%=path %>/up/businessphoto" id="safeProductionLicencePhotoForm" method="post"
      enctype="multipart/form-data" style="display:none">
    <input id="uploadsafeProductionLicencePhoto" type="file" name="safeProductionLicencePhoto">
</form>
<form action="<%=path %>/up/businessphoto" id="orgCodePhotoForm" method="post"
      enctype="multipart/form-data" style="display:none">
    <input id="uploadorgCodePhoto" type="file" name="uploadorgCodePhoto">
</form>

<script>
    $().ready(function () {
        commonUpload($('#uploadbusinessphotoForm'),
            $('#uploadbusinessphoto'),
            $('#licenseScanner'),
            $('#businessLicensePhoto'),
            $('#businessLicensePhotoA'),
            $('#businessLicensePhotoImg')
        );
        commonUpload($('#uploadtaxCodePhotoForm'),
            $('#uploadtaxCodePhoto'),
            $('#taxScanner'),
            $('#taxCodePhoto'),
            $('#taxCodePhotoA'),
            $('#taxCodePhotoImg')
        );
        commonUpload($('#creditCodePhotoForm'),
            $('#uploadcreditCodePhoto'),
            $('#creditScanner'),
            $('#creditCodePhoto'),
            $('#creditCodePhotoA'),
            $('#creditCodePhotoImg')
        );
        commonUpload($('#safeProductionLicencePhotoForm'),
            $('#uploadsafeProductionLicencePhoto'),
            $('#safetyScanner'),
            $('#safeProductionLicencePhoto'),
            $('#safeProductionLicencePhotoA'),
            $('#safeProductionLicencePhotoImg')
        );
        commonUpload($('#orgCodePhotoForm'),
            $('#uploadorgCodePhoto'),
            $('#orgScanner'),
            $('#orgCodePhoto'),
            $('#orgCodePhotoA'),
            $('#orgCodePhotoImg')
        );

    });
</script>


</body>
</html>
