<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../taglib_includes.jsp" %>
<html>
<head>
    <%@ include file="../basic_includes.jsp" %>

    <title>修改资料-${member.name}</title>

    <script src="<%=path%>/media/js/do/hr/member/creation.js"></script>
    <script src="<%=path%>/media/js/do/hr/organization/creation.js"></script>
    <script src="<%=path%>/media/js/do/hr/organization/modify.js"></script>
    <script src="<%=path%>/media/js/do/hr/member/creation-validate.js"></script>
    <script src="<%=path%>/media/js/do/member/modify.js"></script>


</head>
<body>

<%@ include file="../include/hr/_member_hr_header.jsp" %>


<!--message start-->
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <!--menu start-->
            <%@ include file="../include/hr/_menu.jsp" %>

            <!--menu end-->
        </div>
        <div class="col-md-10">
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">员工信息修改<span id="operation"><a href="#" id="hide"
                                                                         class="glyphicon glyphicon-chevron-up"></a><a
                        href="#" id="refresh" class="glyphicon glyphicon-refresh"></a><a href="#" id="close"
                                                                                         class="glyphicon glyphicon-remove"></a></span>
                </div>
                <div class="panel-body">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <form class="form-horizontal" id="organizationInfo" method="post"
                              action="<%=path%>/member/${member.id}">
                            <div class="form-group">
                                <label for="loginName" class="col-sm-3 control-label">登录名*：</label>
                                <span class="control-answer col-sm-9" id="loginName">
                                    <c:if test="${not empty member.loginName}">
                                        ${member.loginName}
                                    </c:if>
                                    <c:if test="${empty member.loginName}">
                                        无
                                    </c:if>
                                </span>
                            </div>
                            <div class="form-group">
                                <label for="Name" class="col-sm-3 control-label">员工姓名*：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="Name" class="form-control" placeholder="员工姓名" name="name"
                                           value="${member.name}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="tel" class="col-sm-3 control-label">联系方式：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="tel" class="form-control" placeholder="联系方式" name="phone"
                                           value="${member.phone}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="idCard" class="col-sm-3 control-label">身份证号:</label>
                                <div class="col-sm-9">
                                    <input type="text" id="idCard" class="form-control" placeholder="身份证号" name="sfzNo"
                                           value="${member.sfzNo}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">所在机构*：</label>
                                <div class="col-sm-9">
                                    <label class="radio-inline">
                                        <input type="radio" name="orgType" value="-1" checked="checked"> 无
                                    </label>
                                    <c:set var="orgTypes" value="<%=se.zust.enums.OrganizationTypeEnum.values()%>"/>
                                    <c:forEach items="${orgTypes}" var="typeTemp">
                                        <label class="radio-inline">
                                            <input type="radio" name="orgType"
                                                   value="${typeTemp.type }"
                                                   id="orgType${typeTemp.type }"> ${typeTemp.info }
                                        </label>
                                    </c:forEach>
                                </div>
                            </div>

                            <div class="form-group" id="orgDiv" style="display: none;">
                                <label class="col-sm-3 control-label">机构:</label>
                                <div class="col-sm-9">
                                    <div class="row">
                                        <div class="col-sm-3" id="provinceOrgDiv">
                                            <select id="provinceOrg" name="provinceOrg" class="form-control">
                                            </select>
                                        </div>
                                        <div class="col-sm-3" id="cityOrgDiv">
                                            <select id="cityOrg" name="cityOrg" class="form-control">
                                            </select>
                                        </div>
                                        <div class="col-sm-3" id="districtOrgDiv">
                                            <select id="districtOrg" name="districtOrg" class="form-control">
                                            </select>
                                        </div>
                                        <div class="col-sm-3" id="companyOrgDiv">
                                            <select id="companyOrg" name="companyOrg" class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group" id="departmentDiv" style="display: none;">
                                <label for="department" class="col-sm-3 control-label">部门名称:</label>
                                <div class="col-sm-9">
                                    <select id="department" name="department" class="form-control">
                                        <option value="-1">无</option>
                                        <c:forEach items="${depList}" var="tempDep">
                                            <option value="${tempDep.id}">${tempDep.name}</option>
                                        </c:forEach>
                                    </select>
                                    <c:if test="${not empty dep}">
                                        <script>
                                            $().ready(function () {
                                                $('#' + creationMember.ids.department).val('${dep.id}');
                                            });
                                        </script>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-5"></div>
                                <div class="col-sm-3">
                                    <input type="submit" class="btn btn-primary" value="修改">
                                </div>
                                <div class="col-sm-4"></div>
                            </div>
                            <hr>
                        </form>
                        <%--员工状态 start--%>
                        <div class="row">
                            <div class="form-group">
                                <label for="memberStatus" class="col-sm-3 control-label control-head">账号状态:</label>
                                    <span class="control-answer col-sm-9" id="memberStatus">
                                        ${member.status.stateInfo}
                                    </span>
                            </div>
                            <div class="form-group">
                                <label for="memberStatusOp" class="col-sm-3 control-label control-head">操作:</label>
                                <div class="control-answer col-sm-9" id="memberStatusOp">
                                    <button class="btn btn-primary"
                                            onclick="modifyMember.funcs.dismissMember('${member.id}')">离职
                                    </button>
                                    <button class="btn btn-danger"
                                            onclick="modifyMember.funcs.deleteMember('${member.id}')">删除
                                    </button>
                                    <button class="btn btn-success"
                                            onclick="modifyMember.funcs.undoDeleteMember('${member.id}')">恢复删除
                                    </button>
                                </div>
                            </div>
                        </div>
                        <%--员工状态 end--%>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
        </div>

    </div>
</div>
<!--message end-->


<%@ include file="../include/hr/_member_hr_footer.jsp" %>

</body>
<script>
    $().ready(function () {
        var flagClicked = false;

        $('input:radio[name=' + creationMember.ids.orgType + ']').change(function () {
            console.log('change');
            creationMember.funcs.orgDiv($(this).val());

            if (flagClicked) {
                var orgType = $(this).val();
                console.log('被选中的id=', orgType);
                if (orgType == '0' || orgType == 0) {
                    //省
                    console.log('更新省');
                    creationMember.funcs.refreshDep(creationMember.ids.department, $("#" + creationMember.ids.provinceOrg).val());
                } else if (orgType == '1' || orgType == 1) {
                    //市
                    console.log('更新市');
                    creationMember.funcs.refreshDep(creationMember.ids.department, $("#" + creationMember.ids.cityOrg).val());
                } else if (orgType == '2' || orgType == 2) {
                    //区
                    console.log('更新区');
                    creationMember.funcs.refreshDep(creationMember.ids.department, $("#" + creationMember.ids.districtOrg).val());
                } else if (orgType == '3' || orgType == 3) {
                    //车企
                    console.log('更新车企');
                    creationMember.funcs.refreshDep(creationMember.ids.department, $("#" + creationMember.ids.companyOrg).val());
                }
            }


        }).click(function () {
            flagClicked = true;
        });


        $('#' + creationMember.ids.provinceOrg).change(function () {
            if (flagClicked) {
                creationOrganization.funcs.freshChildSelect(creationMember.ids.cityOrg, $(this).val());
                creationMember.funcs.refreshDep(creationMember.ids.department, $(this).val());
            } else {
            }
        }).click(function () {
            flagClicked = true;
        });

        $('#' + creationMember.ids.cityOrg).change(function () {
            if (flagClicked) {
                creationOrganization.funcs.freshChildSelect(creationMember.ids.districtOrg, $(this).val());
                creationMember.funcs.refreshDep(creationMember.ids.department, $(this).val());
            } else {
                modifyOrg.funcs.refreshParentSelect(creationMember.ids.provinceOrg, $(this).val());
            }
        }).click(function () {
            flagClicked = true;
        });

        $('#' + creationMember.ids.districtOrg).change(function () {
            if (flagClicked) {
                creationOrganization.funcs.freshChildSelect(creationMember.ids.companyOrg, $(this).val());
                creationMember.funcs.refreshDep(creationMember.ids.department, $(this).val());
            } else {
                modifyOrg.funcs.refreshParentSelect(creationMember.ids.cityOrg, $(this).val());
            }
        }).click(function () {
            flagClicked = true;
        });

        $('#' + creationMember.ids.companyOrg).change(function () {
            if (flagClicked) {
                creationMember.funcs.refreshDep(creationMember.ids.department, $(this).val());
            } else {
                modifyOrg.funcs.refreshParentSelect(creationMember.ids.districtOrg, $(this).val());
            }
        }).click(function () {
            flagClicked = true;
        });


    });
</script>
<c:if test="${not empty org}">
    <script>
        $().ready(function () {
            //设置员工的类型
            $('#orgType${org.type.type}').attr("checked", "checked");
            creationMember.funcs.orgDiv('${org.type.type}');
            modifyMember.funcs.updateOrgBelong('${org.type.type}', '${org.id}');
        });
    </script>
</c:if>
<c:if test="${empty org}">
    <script>
        $().ready(function () {
            //更新机构
            creationOrganization.funcs.freshProvinceOrg(creationMember.ids.provinceOrg);
        });
    </script>
</c:if>


</html>
