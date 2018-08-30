<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../2/include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../../2/include/_html_head.jsp" %>

    <script type="text/javascript" src="<%=path %>/media/js/bootstrap-filestyle.min.js"></script>

    <link rel="stylesheet" href="<%=path %>/media/css/bootstrap-datetimepicker-master/bootstrap-datetimepicker.min.css"
          type="text/css"/>
    <link href="<%=path %>/media/css/list.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=path %>/media/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=path %>/media/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=path %>/js/jquery.form.js"></script>
    <script type="text/javascript" src="<%=path %>/js/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=path %>/media/js/bootstrap-filestyle.min.js"></script>
    <style>
        .hideOver{
            width: 436px;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
        .newButton{
            width:56px;
        }
    </style>

    <title>新闻发言人在线学习培训平台</title>
</head>
<body>

<!--menu end-->
<%@include file="../../include/_member_header.jsp"%>
<!--main content start-->
<div class="container">
<!--message start-->
<div class="content">
    <div class="row">

        <%@include file="../../include/hr/_announcement_part.jsp"%>

        <div class="col-md-9">
            <div class="block block-two">
                <!-- Default panel contents -->
                <div class="block-heading"><span>通知列表</span></div>

                <div class="block-body">

                    <div class="col-md-12">

                        <div class="list_info">
                            <c:forEach items="${anns}" var="ann" >
                                <div class="col-md-7 hideOver">[${ann.type.info}] <a href="${path}/announcement/${ann.id}" class="bm_item">${ann.title}</a></div>
                                <div class="col-md-5" >
                                    <c:if test="${loginedMemberProvince}">
                                        <button onclick="saveDelId(${ann.id})" class="btn btn-long edit_btn newButton"  data-toggle="modal" data-target="#sure_Modal">删除</button>
                                        <a href="${path}/announcement/${ann.id}/update"><button class="btn btn-primary btn-long edit_btn newButton">编辑</button></a>
                                    </c:if>
                                    <span class="time">[${ann.createDate}]</span>
                                </div><br><hr>
                            </c:forEach>
                            <br><hr><br>

                        </div>
                    </div>
                </div>
            </div>
        </div>
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
                    是否确认删除？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="delAnn()" >确认</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
    <!--sure modal end-->
</div>



<!--main content end-->

<%-- display none value-start --%>
<input type="text" value="0" style="display: none;" id="delAnn" />
<%-- display none value end --%>

<!--footer start-->
<div class="footer">
    <nav class="navbar navbar-default common_bg" id="footer_navbar">
        <div class="common_color">Copyright：2016-2017第一学期 J2EE课程设计</div>
    </nav>
</div>
<!--footer end-->

<script>
    window.onload = function () {
        var height = $("#attach_part").height()+20>$(".content .row .col-md-9").height()?$("#attach_part").height()+20:$(".content .row .col-md-9").height();
        height=height>document.body.clientHeight-228?height:document.body.clientHeight-228;
        $(".content .row .col-md-3").css("height",height);
    };
    $(".date").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        startView: 3,
        language: 'zh-CN'
    });
    function saveDelId(id) {
        $("#delAnn").val(id);
    }
    function delAnn() {
        $.ajax({
            url : 'delete/'+$("#delAnn").val(),
            type : 'get',
            dataType : 'json',
            success : function(rd){
                if (rd){
                    window.location.href=path+"/announcement/list";
                }
            }
        })
    }


</script>
</body>
</html>
