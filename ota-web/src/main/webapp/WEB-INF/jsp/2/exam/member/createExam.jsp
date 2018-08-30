<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../2/include/_taglib_includes.jsp"%>

<!--散装办发布考试-->
<!DOCTYPE html>
<html>
<head lang="en">
    <%@include file="../../../2/include/_html_head.jsp"%>
    <link href="<%=basePath%>media/2/css/exam_member_release.css" type="text/css" rel="stylesheet"/>
    <link href="<%=basePath%>media/css/bootstrap-datetimepicker-master/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>media/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>media/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>media/2/js/exam/member/createExam.js"></script>
    <title>新闻发言人在线学习培训平台-发布考试</title>
</head>
<body>

	<!--header start-->
	<%@include file="../../../2/include/_member_header.jsp" %>
	<!--header end-->

    <!--main content start-->

    <!--message start-->
    <div class="content container">
        <div class="row">
            <div class="col-md-3">
                <!-- side menu start -->
				<%@include file="menu.jsp"%>
				<!-- side menu end -->
            </div>
            <div class="col-md-9">
                <div class="block block-two">
                    <!-- Default panel contents -->
                    <div class="block-heading"><span>发布考试-考试相关信息</span></div>
                    <div class="block-body">
                        <div class="col-md-12">
                            <form class="form-horizontal" id="data" method="post"
								action="<%=basePath%>exam/member/create">
                                <div class="form-group">
                                    <div class="col-md-6">
                                        <label for="exam_name" class="text_title">考试名称：</label>
                                        <input type="text" class="form_input" id="exam_name" placeholder="考试名称" name="examName">
                                        <label class="error" id="examNameError" style="dispaly:none"></label>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="text_title">考试类型：</label>
                                        <label class="radio-inline">
                                            <input type="radio" name="examType" value="0" id="official" checked="checked">正式考试
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="examType" value="1" id="simulation">模拟考试
                                        </label>
                                    </div>
                                </div>

                                <div class="form-group" id="modality_part">
                                    <div class="col-md-6">
                                        <label class="text_title">考试形式：</label>
                                        <label class="radio-inline">
                                            <input type="radio" name="examSubType" value="0" id="online" checked>线上考试
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="examSubType" value="2" id="outline">线下考试
                                        </label>
                                    </div>
                                </div>
                                <div id="time_all">
                                    <div class="form-group">
                                        <div class="col-md-6">
                                            <label class="text_date">报名开始时间：</label>
                                            <div class='input-group date form_date' data-date-format="yyyymmdd">
                                                <input name="signupStartTime" id="startTm" type='text' class="form-control input-sm" readonly="readonly"/>
                                                <span class="input-group-addon input-sm">
                                                    <span class="glyphicon glyphicon-remove"></span>
                                                </span>
                                                <span class="input-group-addon input-sm">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                            <label class="error" id="signupStartTimeeError" style="dispaly:none"></label>
                                        </div>
                                        <div class="col-md-6">
                                            <label class="text_date">报名结束时间：</label>
                                            <div class='input-group date form_date' data-date-format="yyyymmdd">
                                                <input name="signupEndTime" id="endTm" type='text' class="form-control input-sm" readonly="readonly"/>
                                                <span class="input-group-addon input-sm">
                                                    <span class="glyphicon glyphicon-remove"></span>
                                                </span>
                                                <span class="input-group-addon input-sm">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                            <label class="error" id="signupEndTimeError" style="dispaly:none"></label>
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-6">
                                            <label class="text_date">考试开始时间：</label>
                                            <div class='input-group date form_date' data-date-format="yyyymmdd">
                                                <input name="examStartTime" id="examStart" type='text' class="form-control input-sm" readonly="readonly"/>
                                                <span class="input-group-addon input-sm">
                                                    <span class="glyphicon glyphicon-remove"></span>
                                                </span>
                                                <span class="input-group-addon input-sm">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                            <label class="error" id="examStartTimeError" style="dispaly:none"></label>
                                        </div>
                                        <div class="col-md-6">
                                            <label class="text_date">考试结束时间：</label>
                                            <div class='input-group date form_date' data-date-format="yyyymmdd">
                                                <input name="examEndTime" id="examEnd" type='text' class="form-control input-sm" readonly="readonly"/>
                                                <span class="input-group-addon input-sm">
                                                    <span class="glyphicon glyphicon-remove"></span>
                                                </span>
                                                <span class="input-group-addon input-sm">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                            <label class="error" id="examEndTimeError" style="dispaly:none"></label>
                                        </div>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <div class="col-md-6">
                                        <label for="exam_time" class="text_title">考试时长：</label>
                                        <input type="text" class="form_input" id="exam_time" placeholder="考试时长（分钟）" name="duration" disabled>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="text_title">试卷组成：</label>
                                        <label class="radio-inline">
                                            <input type="radio" name="paperType" value="exist" id="existPaper" checked="checked">使用已有试卷
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="paperType" value="new" id="newPaper">重新组卷
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <label for="explain_container" class="text_title" style="vertical-align: top;">考试说明：</label>
                                        <textarea id="explain_container" class="form_textarea" placeholder="考试说明，该信息考生可见（选填）" name="examDescription"></textarea>
                                    </div>
                                </div>
                                <div class="form-group text-center">
                                    <button type="button" class="btn-custom btn-long" onclick="createExam()">下一步</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!--message end-->
    </div>
    <!--main content end-->
   <!--footer start-->
	<%@include file="../../include/_html_body.jsp"%>
	<!--footer end-->

<script>
	window.onload = function() {
		var height = $("#attach_part").height() + 20 > $(
				".content .row .col-md-9").height() ? $("#attach_part")
				.height() + 20 : $(".content .row .col-md-9").height();
		height = height > document.body.clientHeight - 228 ? height
				: document.body.clientHeight - 228;
		$(".content .row .col-md-3").css("height", height);
		//        console.log($("#official").is(':checked'));
		//        判断模拟考试还是正式考试
		$(":radio").click(function() {
			if ($(this).val() == 1) {
				//模拟考试
				$("#time_all").hide();
				$("#exam_time").attr("disabled", false);
				$("#modality_part").hide();

			} else if ($(this).val() == 0) {
				//正式考试
				$("#time_all").show();
				$("#exam_time").attr("disabled", true);
				$("#modality_part").show();
			}
		});

		$(".date").datetimepicker({
			format : 'yyyy/mm/dd hh:ii',
			startView : 2,
			language : 'zh-CN',
			autoclose : true
		});
	}
</script>
</body>
</html>