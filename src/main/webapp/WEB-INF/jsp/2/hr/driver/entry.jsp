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

    <script src="<%=path%>/media/js/do/hr/driver/entry.js"></script>

</head>
<body>
<%@include file="../../../2/include/_member_header.jsp" %>

<!--main content start-->

<!--message start-->
<div class="container content">
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
                <!-- Default panel contents -->
                <div class="block-heading"><span>驾驶员入职</span></div>
                <br>
                <div class="block-body">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <%--<form class="form-horizontal" id="search_form">--%>
                            <div class="form-group">
                                <label for="sfzNo" class="col-sm-4 control-label text_title">请输入该驾驶员身份证号：</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="sfzNo" placeholder="驾驶员身份证号码">
                                </div>
                                <div class="col-sm-3 text-center">
                                    <input type="button" class="btn-custom btn-long" value="搜索"
                                           onclick="submit()">
                                </div>
                            </div>
                        <%--</form>--%>
                    </div>
                    <div class="col-md-1"></div>
                </div>

            </div>
        </div>


        <div class="col-md-9">
            <div class="block block-two">
                <!-- Default panel contents -->
                <div class="block-body">
                    <div class="col-md-12 text-center">
                        <h3 id="sfz_no_hint" style="display: none" ></h3>
                        <h3 id="successInfo" style="display: none" >
                            该驾驶员信息已在系统中,是否<a id="suchref" href="" >确认</a>入职?
                        </h3>
                        <h3 id="falseInfo" style="display: none" >
                            该驾驶员信息不在系统中,请您<a id="falhref" href="" >添加</a>
                        </h3>
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
    var path = '${path}';
    window.onload = function () {
        var height = $("#attach_part").height()+20>$(".content .row .col-md-9").height()?$("#attach_part").height()+20:$(".content .row .col-md-9").height();
        height=height>document.body.clientHeight-228?height:document.body.clientHeight-228;
        $(".content .row .col-md-3").css("height",height);
    };
</script>
</html>
