<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../2/include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../../2/include/_html_head.jsp" %>
    <title>基于B/S程序的无纸化网络考试系统</title>
    <script type="text/javascript">
        var passUrl = "<%=basePath %>"+"driver/pass";
    </script>
    <script src="<%=path%>/media/js/do/hr/driver/check_driver.js"></script>
</head>
<body>

<%@include file="../../../2/include/_member_header.jsp" %>
<div class="container">
<!--message start-->
<div class="content">
    <div class="row">
        
        <%@include file="../../../2/include/hr/_driver_part.jsp"%>
        
        <div class="col-md-9">
            <div class="block block-two">
                <!-- Default panel contents -->
                <div class="block-heading"><span><a name="d_driver"></a>驾驶员基本资料</span></div>
                <div class="block-body">

                    <table class="table table-striped table_info table-bordered">
                        <thead>
                        <tr>
                            <th style="cursor: pointer" colspan="5">驾驶员基本信息
                                <span class="glyphicon glyphicon-chevron-up show-up"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td style="cursor: pointer;">姓名</td>
                            <td style="cursor: pointer;">${driver.name }</td>
                            <td style="cursor: pointer;">性别</td>
                            <td style="cursor: pointer;"><c:if test="${driver.gender == 0}">
                                女</c:if><c:if test="${driver.gender == 1}">男
                            </c:if></td>
                            <td rowspan="6">
                                <div id="driver_header" class="text-center">
                                    <img alt="未上传照片" src="${driver.photo }" style="max-width:128px;max-height:160px;" ><br>
                                    <c:if test="${loginedMemberProvince}">
                                         <a  href="<%=basePath %>driver/${driver.id }/update" >
                                           <input type="button" class="btn-custom btn-long" value="编辑">
                                         </a>
                                    </c:if>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="cursor: pointer;">籍贯</td>
                            <td style="cursor: pointer;">${driver.birthPlace }</td>
                            <td style="cursor: pointer;">身份证号</td>
                            <td style="cursor: pointer;">${driver.sfzNo }</td>
                        </tr>
                        <tr>
                            <td style="cursor: pointer;">驾驶证类别</td>
                            <td style="cursor: pointer;">...</td>
                            <td style="cursor: pointer;">驾驶车辆号牌</td>
                            <td style="cursor: pointer;">...</td>
                        </tr>
                        <tr>
                            <td style="cursor: pointer;">联系方式</td>
                            <td style="cursor: pointer;">${driver.mobile }</td>
                            <td style="cursor: pointer;">邮编</td>
                            <td style="cursor: pointer;">${driver.postcode }</td>
                        </tr>
                        <tr>
                            <td style="cursor: pointer;">Email</td>
                            <td style="cursor: pointer;">${driver.email }</td>
                            <td style="cursor: pointer;">所属车企</td>
                            <td style="cursor: pointer;">...</td>
                        </tr>
                        <tr>
                            <td style="cursor: pointer;">家庭住址</td>
                            <td style="cursor: pointer;" colspan="3">${driver.address }</td>
                        </tr>
                        </tbody>
                    </table>

                    <!--驾驶员所属信息 -->
                    <table class="table table-striped table-bordered table_info">
                        <thead>
                        <tr>
                            <th style="cursor: pointer" colspan="6"><a name="d_orgInfo"></a>驾驶员所属信息
                            <span class="glyphicon glyphicon-chevron-up show-up"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td style="cursor: pointer">设区市名称</td>
                            <td style="cursor: pointer">${orgInfo.orgCityName}</td>
                            <td style="cursor: pointer">县市区名称</td>
                            <td style="cursor: pointer">${orgInfo.orgDistrictName}</td>
                            <td style="cursor: pointer">企业名称</td>
                            <td style="cursor: pointer">${orgInfo.orgCompanyName}</td>
                        </tr>
                        <tr>
                            <td style="cursor: pointer">法人姓名</td>
                            <td style="cursor: pointer">${orgInfo.legalPerson}</td>
                            <td style="cursor: pointer">法人电话</td>
                            <td style="cursor: pointer">${orgInfo.legalPersonTel}</td>
                            <td style="cursor: pointer">分管领导姓名</td>
                            <td style="cursor: pointer">${orgInfo.leaderName}</td>
                        </tr>
                        <tr>
                            <td style="cursor: pointer">分管领导电话</td>
                            <td style="cursor: pointer">${orgInfo.leaderTel}</td>
                            <td style="cursor: pointer">车队长姓名</td>
                            <td style="cursor: pointer">${orgInfo.driverName}</td>
                            <td style="cursor: pointer">车队长电话</td>
                            <td style="cursor: pointer">${orgInfo.driverTel}</td>
                        </tr>
                        </tbody>
                    </table>


                    <table class="table table-bordered table-striped table_info">
                        <thead>
                        <tr>
                            <th style="cursor: pointer" colspan="6"><a name="d_info"></a>驾驶员详细信息
                                <span class="glyphicon glyphicon-chevron-up show-up"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td style="cursor: pointer">家庭联系人</td>
                            <td style="cursor: pointer">${driver.emergencyContact }</td>
                            <td style="cursor: pointer">联系人电话</td>
                            <td style="cursor: pointer">${driver.emergencyContactMobile }</td>
                            <td style="cursor: pointer">培训证号码</td>
                            <td style="cursor: pointer">42379526</td>
                        </tr>
                        <tr>
                            <td style="cursor: pointer">驾驶证号</td>
                            <td style="cursor: pointer">${driver.driveLicenceNo }</td>
                            <td style="cursor: pointer">驾驶证生效日期</td>
                            <td style="cursor: pointer">${driver.driveLicenceStartTime }</td>
                            <td style="cursor: pointer">驾驶证失效日期</td>
                            <td style="cursor: pointer">${driver.driveLicenceEndTime }</td>
                        </tr>
                        <tr>
                            <td style="cursor: pointer">安全证书号</td>
                            <td style="cursor: pointer">${driver.safeCentificateNo }</td>
                            <td style="cursor: pointer">安全证书生效日期</td>
                            <td style="cursor: pointer">${driver.safeCentificateStartTime }</td>
                            <td style="cursor: pointer">安全证书失效日期</td>
                            <td style="cursor: pointer">${driver.safeCentificateEndTime }</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="block block-two">
                <div class="block-heading"><span>员工档案资料</span></div>
                <div class="block-body">
                    <table class="table table-bordered table-striped table_driver">
                        <thead>
                        <tr>
                            <th style="cursor: pointer" colspan="6"><a name="d_history"></a>驾驶员工作经历
                                <span class="glyphicon glyphicon-chevron-up show-up"></span></th>
                        </tr>
                        <tr>
                            <th  style="cursor: pointer;text-align: center;">开始时间</th>
                            <th  style="cursor: pointer;text-align: center;">结束时间</th>
                            <th  style="cursor: pointer;text-align: center;">所在单位</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${driverWorkHis }" var="dh" >
                                <tr>
                                    <td style="cursor: pointer;text-align: center;">${dh.startDate }</td>
                                    <td style="cursor: pointer;text-align: center;">${dh.endDate }</td>
                                    <td style="cursor: pointer;text-align: center;">${dh.orgName }</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <table class="table table-bordered table-striped table_driver">
                        <thead>
                        <tr>
                            <th style="cursor: pointer" colspan="6"><a name="d_insurance"></a>驾驶员出险记录
                                <span class="glyphicon glyphicon-chevron-up show-up"></span></th>
                        </tr>
                        <tr>
                            <th style="cursor: pointer;text-align: center;">时间</th>
                            <th style="cursor: pointer;text-align: center;">地点</th>
                            <th style="cursor: pointer;text-align: center;">（号牌）车辆</th>
                            <th style="cursor: pointer;text-align: center;">人员伤亡情况</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${driverInsurance}" var="di" >
                            <tr>
                                <td style="cursor: pointer;text-align: center;">${di.occureTime }</td>
                                <td style="cursor: pointer;text-align: center;">${di.injuredPlace }</td>
                                <td style="cursor: pointer;text-align: center;">${di.carNo }</td>
                                <td style="cursor: pointer;text-align: center;">伤${di.injuredNum }、亡${di.deadNum }</td>

                            </tr>

                        </c:forEach>
                    </table>
                    <table class="table table-bordered table-striped table_driver">
                        <thead>
                        <tr>
                            <th style="cursor: pointer" colspan="6"><a name="d_rate"></a>保险赔付记录
                                <span class="glyphicon glyphicon-chevron-up show-up"></span></th>
                        </tr>
                        <tr>
                            <th style="cursor: pointer;text-align: center;">开始时间</th>
                            <th style="cursor: pointer;text-align: center;">结束时间</th>
                            <th style="cursor: pointer;text-align: center;">保险赔付</th>
                            <th style="cursor: pointer;text-align: center;">全年赔付率</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:if test="${rate.feeSum != null}">
                        <tr>
                            <td style="cursor: pointer;text-align: center;">${rate.thisYear }</td>
                            <td style="cursor: pointer;text-align: center;">${rate.nextYear }</td>
                            <td style="cursor: pointer;text-align: center;">${rate.feeSum }</td>
                            <td style="cursor: pointer;text-align: center;">${rate.rate }</td>

                        </tr>
                        </c:if>

                    </table>

                    <table class="table table-bordered table-striped table_driver">
                        <thead>
                        <tr>
                            <th style="cursor: pointer" colspan="6"><a name="d_train"></a>驾驶员培训记录
                                <span class="glyphicon glyphicon-chevron-up show-up"></span></th>
                        </tr>
                        <tr>
                            <th style="cursor: pointer;text-align: center;">开始时间</th>
                            <th style="cursor: pointer;text-align: center;">结束时间</th>
                            <th style="cursor: pointer;text-align: center;">名称</th>
                            <th style="cursor: pointer;text-align: center;">驾驶员状态</th>
                            <th style="cursor: pointer;text-align: center;">考试状态</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${trains }" var="train" >
                            <tr>
                                <td style="cursor: pointer;text-align: center;">${train.startTime }</td>
                                <td style="cursor: pointer;text-align: center;">${train.endTime }</td>
                                <td style="cursor: pointer;text-align: center;">${train.name }</td>
                                <td style="cursor: pointer;text-align: center;">${train.driverStatus }</td>
                                <td style="cursor: pointer;text-align: center;">${train.examStatus }</td>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>


                    <table class="table table-bordered table-striped table_driver">
                        <thead>
                        <tr>
                            <th style="cursor: pointer" colspan="6"><a name="d_exam"></a>驾驶员考试记录
                                <span class="glyphicon glyphicon-chevron-up show-up"></span></th>
                        </tr>
                        <tr>
                            <th style="cursor: pointer;text-align: center;">考试时间</th>
                            <th style="cursor: pointer;text-align: center;">考试名称</th>
                            <th style="cursor: pointer;text-align: center;">考试分数</th>
                            <th style="cursor: pointer;text-align: center;">证书号</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${exams }" var="exam" >
                            <tr>
                                <td style="cursor: pointer;text-align: center;">${exam.examTime }</td>
                                <td style="cursor: pointer;text-align: center;">${exam.examName }</td>
                                <td style="cursor: pointer;text-align: center;">${exam.finalScore }</td>
                                <td style="cursor: pointer;text-align: center;">${exam.certificateNo }</td>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>



                    <table class="table table-bordered table-striped table_driver">
                        <thead>
                        <tr>
                            <th style="cursor: pointer" colspan="6"><a name="d_honour"></a>驾驶员荣誉记录
                                <span class="glyphicon glyphicon-chevron-up show-up"></span></th>
                        </tr>
                        <tr>
                            <th style="cursor: pointer;text-align: center;">获奖时间</th>
                            <th style="cursor: pointer;text-align: center;">颁发车企</th>
                            <th style="cursor: pointer;text-align: center;">荣誉称号</th>

                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${driverHonour }" var="dh" >
                                <tr>
                                    <td style="cursor: pointer;text-align: center;">${dh.honourTime }</td>
                                    <td style="cursor: pointer;text-align: center;">${dh.orgName }</td>
                                    <td style="cursor: pointer;text-align: center;">${dh.honourName }</td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>

                    <table class="table table-bordered table-striped table_driver">
                        <thead>
                        <tr>
                            <th style="cursor: pointer" colspan="6"><a name="d_star"></a>驾驶员安全星级
                                <span class="glyphicon glyphicon-chevron-up show-up"></span></th>
                        </tr>
                        <tr>
                            <th style="cursor: pointer;text-align: center;">起始时间</th>
                            <th style="cursor: pointer;text-align: center;">终止时间</th>
                            <th style="cursor: pointer;text-align: center;">星级</th>

                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${driverInfoStar }" var="di" >
                                <tr>
                                    <td style="cursor: pointer;text-align: center;">${di.beginTime}</td>
                                    <td style="cursor: pointer;text-align: center;">${di.endTime}</td>
                                    <td style="cursor: pointer;text-align: center;">${di.starName}</td>
                                </tr>
                            </c:forEach>
                        </tbody>


                    </table>
                    <table class="table table-striped table_info table-bordered" id="IDcard">
                        <thead>
                        <tr>
                            <th colspan="2"><a name="d_sfzNo"></a>驾驶员身份证<span class="glyphicon glyphicon-chevron-up show-up"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>身份证正面照</td>
                            <td><img src="${path}/media/images/question_pic.jpg"></td>
                        </tr>
                        <tr>
                            <td>身份证反面照</td>
                            <td><img src="${path}/media/images/question_pic.jpg"></td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

    </div>
</div>
    </div>
<!--message end-->

<!--main content end-->

<%@include file="../../../2/include/_html_body.jsp" %>
</body>
<script>
    window.onload = function () {
        var height = $("#attach_part").height()+20>$(".content .row .col-md-9").height()?$("#attach_part").height()+20:$(".content .row .col-md-9").height();
        height=height>document.body.clientHeight-228?height:document.body.clientHeight-228;
        $(".content .row .col-md-3").css("height",height);
    };
    $(".show-up").bind('click',function () {
        var part = $(this).parent().parent().parent().next();
        console.log($(part));
        if ($(part).css('display')=='none'){
            part.show(200);
            $(this).addClass('glyphicon-chevron-up');
            $(this).removeClass('glyphicon-chevron-down');
        }else {
            part.hide(200);
            $(this).removeClass('glyphicon-chevron-up');
            $(this).addClass('glyphicon-chevron-down');
        }
    });
    $(window).scroll(function() {
        if ($(document).scrollTop() > 176) {
            $("#theFixedList").css("position", "fixed");
            $("#theFixedList").css("top", "20px");
        } else {
            $("#theFixedList").css("position", "");
            $("#theFixedList").css("top", "0");
        }
    });


</script>
</html>
