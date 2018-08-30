<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../2/include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../../2/include/_html_head.jsp" %>
    <title>基于B/S程序的无纸化网络考试系统</title>
    <%--<script src="${path}/media/js/do/hr/driver/index_.js"></script>--%>
    <link href="http://cdn.bootcss.com/zTree.v3/3.5.26/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet">
</head>
<body>
<%@include file="../../../2/include/_member_header.jsp" %>
<div class="container">

<!--main content start-->

<!--message start-->
<div class="content">
    <div class="row">

        <div class="col-md-4">
            <div id="ztree" class="ztree" style="margin-bottom: 256px" ></div>
        </div>

        <%--<div >--%>
            <%--------------------------------%>
            <iframe class="col-md-8" id="includes" src="${path}/driver/list?orgId=${orgId}" frameborder ="0" height="1440" width="680" scrolling="no">

            </iframe>
        <%--</div>--%>

    </div>
</div>
<!--message end-->
<!--sure modal start-->
<div class="modal fade" id="sure_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="sureModalLabel">消息确认</h4>
            </div>
            <div class="modal-body text-center">
                是否确认删除该驾驶员？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--sure modal end-->
<!--main content end-->
</div>

<%@include file="../../../2/include/_html_body.jsp" %>
<script src="http://cdn.bootcss.com/zTree.v3/3.5.26/js/jquery.ztree.core.min.js"></script>
</body>

<script>
    var pageNum = '${pageNum }';
    var orgId = '${orgId}';
    var jsonZtree = $.parseJSON('${ztree}');



    //    window.onload = function () {
//        var height = $("#attach_part").height()+20>$(".content .row .col-md-9").height()?$("#attach_part").height()+20:$(".content .row .col-md-9").height();
//        height=height>document.body.clientHeight-228?height:document.body.clientHeight-228;
//        $(".content .row .col-md-3").css("height",height);
//    };
</script>

<script src="${path}/media/2/js/driver/driver_tree.js"></script>

</html>
