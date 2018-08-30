<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../2/include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../../2/include/_html_head.jsp" %>
    <title>新闻发言人在线学习培训平台</title>
    <script type="text/javascript" src="${path}/media/js/bootstrap-filestyle.min.js"></script>
    <script type="text/javascript" src="${path}/media/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="${path}/media/js/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body>
<%@include file="../../../2/include/_member_header.jsp" %>
<div class="container">
<!--message start-->
<div class="content">
    <div class="row">

        <%@include file="../../include/hr/_announcement_part.jsp"%>

        <div class="col-md-9">
            <div class="block block-two">
                <!-- Default panel contents -->
                <div class="block-heading"><span>通知浏览 (类型：${annDto.type.info})</span></div>
                <div class="block-body">
                    <div class="col-md-12">
                        <h2 id="announcement_title" class="text-center">${annDto.title }</h2>
                        <br>

                        <div id="announcement_info" class="announcement_mes text-center">

                            <label class="announcement_label" for="releaseObj">发布：</label>

                            <span class="announcement_answer" id="releaseObj"> ${org.name }</span>

                            <label class="announcement_label" for="releaseTime">发布时间：</label>

                            <span class="announcement_answer" id="releaseTime">${annDto.createDate }</span>

                        </div>
                        <br> <br>

                        <div id="announcement_body">
                            <p class="paragraph">
                                ${annDto.text }
                            </p>
                        </div>
                        <br>

                        <div id="announcement_footer">
                            <c:if test="${annDto.attachment != null && annDto.attachment != 'error' && annDto.attachment != '' }">
                                <a href="${path}/${annDto.attachment }" download="${annDto.title }_附件" class="attachment"><img src="${path}/media/images/fileupload.jpg"  >附件下载</a>
                            </c:if>
                            <span class="col-sm-9" >阅读数: ${annDto.readCount }</span>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    </div>
<!--message end-->
<script>
    window.onload = function () {
        var height = $("#attach_part").height()>$(".content .row .col-md-9").height()?$("#attach_part").height()+20:$(".content .row .col-md-9").height();
        console.log(height);
        $(".content .row .col-md-3").css("height",height);
    };
    $(".date").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        startView: 3,
        language: 'zh-CN'
    });
</script>
<%@include file="../../../2/include/_html_body.jsp" %>
</body>
</html>
