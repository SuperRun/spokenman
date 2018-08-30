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

    <link href="${path}/media/2/css/hr_new.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/media/2/css/jquery-ui.min.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/media/2/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet"/>

    <script src="<%=path%>/media/js/do/hr/excel_upload/driver_insurance.js"></script>

</head>
<body>
<%@include file="../../../2/include/_member_header.jsp" %>

<!--main content start-->

<!--message start-->
<div class="content container">
    <div class="row">

        <div class="col-md-3">
            <form class="attach_part" id="attach_part">
                <div id="tree" class="tree">
                    <%--<iframe id="iframeTree" name="content_frame" marginwidth=0 marginheight=0 width=99% height=100% src="../../../organizationTree.html" frameborder=0  scrolling=YES></iframe>--%>
                </div>
            </form>
        </div>


        <div class="col-md-9">
            <div class="block block-two">
                <!-- addEmployee panel contents start-->
                <div class="block-heading"><span>保险理赔数据导入</span></div>
                <div class="block-body">
                    <div class="col-md-12">
                        <form class="form-horizontal employee_form">

                            <!-- -----------------------------上传---------------------- --><br>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Excel文件</label>
                                <div class="col-sm-8">
                                    <input type="hidden" name="pictureName">
                                    <input type="text" id="photoCover" class="form-control"  placeholder="点击选择上传文件"
                                           onclick="$('input[id=photoFile]').click();" onkeydown="return false;">
                                </div>
                                <div class="col-sm-2"></div>
                            </div>


                            <div class="form-group" style="display:none;" >
                                <label class="col-sm-3 control-label">文件预览</label>
                                <div class="col-sm-9">
                                    <img id="imgPreview" alt="上传图片预览" src=""/>
                                </div>
                            </div>


                            <br>
                            <a href="<%=path%>/media/excel/tdriver_insurance_compensation.xlsx" >导入Excel模板下载</a>

                            <hr>

                            <div class="form-group">
                                <div class="col-sm-5"></div>
                                <div class="col-sm-3">
                                    <input onclick="submits()" type="button" class="btn btn-primary"  value="导入">
                                </div>
                                <div class="col-sm-4"></div>
                            </div>




                            <%-- ---------------------------------------------------------- --%>

                        </form>
                    </div>
                </div>
            </div>




        </div>

    </div>
</div>
<!--message end-->
<form class="form-horizontal" id="picUploadForm" action="<%=path %>/excel/excelIns" role="form" method="post" enctype="multipart/form-data" >
    <!-- 实际的file控件 -->
    <input id="photoFile" type="file" name="photoFile" style="display:none">
</form>

<!--main content end-->

<%@include file="../../../2/include/_html_body.jsp" %>
</body>
<script>
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
                //$('#imgPreview').parent().parent().show();
                //alert($('#addAuthForm').find('input[name=pictureName]').val());
            }
        });
    });
</script>
</html>
