<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../2/include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../../2/include/_html_head.jsp" %>
    <title>基于B/S程序的无纸化网络考试系统</title>
    <script type="text/javascript">
        var path = "<%=path%>";
    </script>
    <script type="text/javascript" src="<%=path %>/media/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=path %>/media/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=path %>/js/jquery.form.js"></script>
    <script type="text/javascript" src="<%=path %>/js/jquery.validate.js"></script>
    <link href="http://cdn.bootcss.com/zTree.v3/3.5.26/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet">

    <script src="http://cdn.bootcss.com/zTree.v3/3.5.26/js/jquery.ztree.core.min.js"></script>

    <link href="${path}/media/2/css/hr_new.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/media/2/css/jquery-ui.min.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/media/2/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet"/>

    <script src="<%=path%>/media/js/do/hr/driver/driver_update.js"></script>

</head>
<body>
<%@include file="../../../2/include/_member_header.jsp" %>

<!--main content start-->

<!--message start-->
<div class="content container">
    <div class="row">

        <div class="col-md-4">
            <br>
            <div class="block-heading"><span>选择车企</span></div>
            <div id="ztree" class="ztree" style="margin-bottom: 256px" ></div>
        </div>

        <%--<%@include file="../../include/hr/_driver_part.jsp"%>--%>

        <div class="col-md-8">
            <div class="block block-two">
                <!-- addEmployee panel contents start-->
                <div class="block-heading"><span>更新驾驶员</span> | <b id="orgNameB" >${org.name}</b></div>
                <div class="block-body">
                    <div class="col-md-12">
                        <form class="form-horizontal employee_form">
                            <div class="form-group">
                                <div class="col-md-6">
                                    <label class="text_title">姓名：</label>
                                    <input type="text" class="form_input" placeholder="新增员工姓名"  id="name" value="${driver.name }" >
                                </div>
                                <div class="col-md-6">
                                    <label class="text_title">所属籍贯：</label>
                                    <input type="text" class="form_input" placeholder="所属籍贯"  id="birthplace" value="${driver.birthPlace }"  >
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-6">
                                    <label class="text_title">性别：</label>
                                    <select class="form_input" id="gender" >
                                        <option value="0" <c:if test="${driver.gender == 0 }">selected="selected"</c:if> >女</option>
                                        <option value="1" <c:if test="${driver.gender == 1 }">selected="selected"</c:if> >男</option>
                                    </select>

                                    <%--<div class="col-sm-6">--%>
                                        <%--<select size="1" name="sample_1_length" aria-controls="sample_1" class="" id="gender">--%>
                                            <%--<option value="0" >女</option>--%>
                                            <%--<option value="1" >男</option>--%>
                                        <%--</select>--%>
                                    <%--</div>--%>
                                    <%--<label class="radio-inline">--%>
                                        <%--<input type="radio" name="inlineRadioOptions" value="m"> 男--%>
                                    <%--</label>--%>
                                    <%--<label class="radio-inline">--%>
                                        <%--<input type="radio" name="inlineRadioOptions" value="f"> 女--%>
                                    <%--</label>--%>
                                </div>
                                <div class="col-md-6">
                                    <label class="text_title">身份证号码：</label>
                                    <input type="text" class="form_input" placeholder="身份证号码" id="sfz_no" onblur="check_sfz_no()" value="${driver.sfzNo }" ><p id="sfz_no_hint" style="display:none" ></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-6">
                                    <label class="text_title">邮编：</label>
                                    <input type="text" class="form_input" placeholder="邮编" id="postcode"  value="${driver.postcode }" >
                                </div>
                                <div class="col-md-6">
                                    <label class="text_title">家庭住址：</label>
                                    <input type="text" class="form_input" placeholder="家庭住址" id="address"  value="${driver.address }"  >
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-6">
                                    <label class="text_title">Email：</label>
                                    <input type="text" class="form_input" placeholder="Email"  id="email"  value="${driver.email }" >
                                </div>
                                <div class="col-md-6">
                                    <label class="text_title">联系电话：</label>
                                    <input type="text" class="form_input" placeholder="联系电话"  id="mobile" onblur="check_mobile()" value="${driver.mobile }" ><p id="mobile_hint" style="display:none" ></p>
                                </div>
                            </div>


                            <div class="form-group" style="display:none;" >
                                <label class="col-sm-3 control-label">选择车企：</label>
                                <div class="col-md-9">
                                    <input id="org" value="${org.id }" type="text" />
                                </div>
                            </div>

                            <%--<div class="form-group">--%>
                                <%--<div class="col-md-12">--%>
                                    <%--<label class="text_title">选择车企：</label>--%>
                                    <%--<select class="form_input" id="org" >--%>
                                        <%--<c:forEach items="${orgs }" var="org" >--%>
                                            <%--<option value="${org.id}">${org.name}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>

                                <%--</div>--%>
                            <%--</div>--%>

                            <%--
                            <div class="form-group">
                                <div class="col-md-6">
                                    <label class="text_title">驾驶证类别：</label>
                                    <select class="form_input">
                                        <option value="0">A1</option>
                                        <option value="1">C1</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label class="text_title">驾驶车辆号牌：</label>
                                    <input type="text" class="form_input" placeholder="驾驶车辆号牌">
                                </div>
                            </div>
                            --%>
                            <hr>

                            <!-- -----------------------------驾驶员照片---------------------- -->
                            <div class="form-group">
                                <label class="col-sm-2 control-label">驾驶员照片</label>
                                <div class="col-sm-8">
                                    <input type="hidden" name="pictureName">
                                    <input type="text" id="photoCover" class="form-control"  placeholder="点击选择上传照片"
                                           onclick="$('input[id=photoFile]').click();" onkeydown="return false;">
                                </div>
                                <div class="col-sm-2"></div>
                            </div>

                            <div class="form-group" style="display:none;" >
                                <label class="col-sm-2 control-label">图片预览</label>
                                <div class="col-sm-8">
                                    <img id="imgPreview" alt="上传图片预览" src="" style="max-width: 256px;"/>
                                </div>
                                <div class="col-sm-2"></div>
                            </div>
                            <%-- ---------------------------------------------------------- --%>

                            <div class="form-group text-center">
                                <input  onclick="submits()" type="button" class="btn-custom btn-long" value="确认" >
                            </div>
                        </form>
                    </div>
                </div>
            </div>




        </div>

    </div>
</div>
<!--message end-->
<input type="text" class="form-control" id="driverId" value="${driver.id }" style="display: none;" >
<div style="display:none;" ><input type="text" id="status" value="${driver.status }" ></div>
<form class="form-horizontal" id="picUploadForm" action="<%=path %>/driver/pic" role="form" method="post" enctype="multipart/form-data" >
    <!-- 实际的file控件 -->
    <input id="photoFile" type="file" name="photoFile" style="display:none">
</form>


<!--main content end-->

<%@include file="../../../2/include/_html_body.jsp" %>
</body>
<script>

    var orgId = '${orgId}';
    var jsonZtree = $.parseJSON('${ztree}');

    window.onload = function () {
        var height = $("#attach_part").height()+20>$(".content .row .col-md-9").height()?$("#attach_part").height()+20:$(".content .row .col-md-9").height();
        height=height>document.body.clientHeight-228?height:document.body.clientHeight-228;
        $(".content .row .col-md-3").css("height",height);
    };
    $(".date").datetimepicker({
        format: 'yyyy-mm-dd',
        startView: 4,
        language: 'zh-CN',
        minView:2
    });
    $('input[id=photoFile]').change(function() {
        $('#photoCover').val($(this).val());
        $('#picUploadForm').ajaxSubmit({
            success: function (data) {
                if(typeof(data)!='object') data=JSON.parse(data);
                console.info(data.errorInfo);
                $('#addAuthForm').find('input[name=pictureName]').val(data.files);
                console.info(data.files);
                $('#imgPreview').attr('src','${pageContext.request.contextPath}/'+data.files);
                $('#imgPreview').parent().parent().show();
                //alert($('#addAuthForm').find('input[name=pictureName]').val());
            }
        });
    });
    var photo = "${driver.photo }";
    if(photo != null && photo.length > 0) {
        $("#photoCover").attr('placeholder', '点击更改照片');
        $('#imgPreview').attr('src', photo);
        $('#imgPreview').parent().parent().show();
    }
</script>
<script src="${path}/media/2/js/driver/creation_tree.js"></script>
</html>
