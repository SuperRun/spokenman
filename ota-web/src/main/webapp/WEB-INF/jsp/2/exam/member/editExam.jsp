<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../2/include/_taglib_includes.jsp"%>

<!--散装办编辑考试-->
<!DOCTYPE html>
<html>
<head lang="en">
<%@include file="../../../2/include/_html_head.jsp"%>
<link href="<%=basePath%>media/2/css/exam_member_release.css"
	type="text/css" rel="stylesheet" />
<link
	href="<%=basePath%>media/css/bootstrap-datetimepicker-master/bootstrap-datetimepicker.min.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="<%=basePath%>media/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>media/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="<%=basePath%>media/2/js/exam/member/createExam.js"></script>
<title>新闻发言人在线学习培训平台-编辑考试</title>
</head>
<body>

	<!--header start-->
	<%@include file="../../../2/include/_member_header.jsp"%>
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
					<div class="block-heading">
						<span>发布考试-考试相关信息</span>
					</div>
					<div class="block-body">
						<div class="col-md-12">
							<form class="form-horizontal" id="data" method="post"
								action="<%=basePath%>exam/member/edit/${examId}">
								<div>
									<input type="text" id="examId" name="examId" value="${examId }"
										style="display:none">
								</div>
								<div class="form-group">
									<div class="col-md-6">
										<label for="exam_name" class="text_title">考试名称：</label> <input
											type="text" class="form_input" id="exam_name"
											placeholder="考试名称" name="examName" value="${exam.examName }">
										<label class="error" id="examNameError" style="dispaly:none"></label>
									</div>
									<div class="col-md-6">
										<c:if test="${exam.examType==1}">
											<label class="radio-inline"> <input type="radio"
												name="examType" value="1" id="simulation" checked="checked">模拟考试
											</label>
											<label class="radio-inline"> <input type="radio"
												name="examType" value="0" id="official">正式考试
											</label>
										</c:if>
										<c:if test="${exam.examType!=1}">
											<label class="radio-inline"> <input type="radio"
												name="examType" value="1" id="simulation">模拟考试
											</label>
											<label class="radio-inline"> <input type="radio"
												name="examType" value="0" id="official" checked="checked">正式考试
											</label>
										</c:if>
										<label class="error" id="examTypeError" style="dispaly:none"></label>
									</div>
								</div>

								<div class="form-group" id="modality_part">
									<div class="col-md-6">
										<c:if test="${exam.examType==0 }">
											<label class="radio-inline"> <input type="radio"
												name="examSubType" value="0" id="online" checked="checked">线上考试
											</label>
											<label class="radio-inline"> <input type="radio"
												name="examSubType" value="2" id="outline">线下考试
											</label>
										</c:if>
										<c:if test="${exam.examType!=0 }">
											<label class="radio-inline"> <input type="radio"
												name="examSubType" value="0" id="online">线上考试
											</label>
											<label class="radio-inline"> <input type="radio"
												name="examSubType" value="2" id="outline" checked="checked">线下考试
											</label>
										</c:if>
									</div>
								</div>
								<c:if test="${exam.examType!=1 }">
									<div id="time_all">
										<div class="form-group">
											<div class="col-md-6">
												<label class="text_date">报名开始时间：</label>
												<div class='input-group date form_date'
													data-date-format="yyyymmdd">
													<input name="signupStartTime" id="startTm" type='text'
														class="form-control input-sm" readonly="readonly"
														value="${exam.signupStartTime }" /> <span
														class="input-group-addon input-sm"> <span
														class="glyphicon glyphicon-remove"></span>
													</span> <span class="input-group-addon input-sm"> <span
														class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
												<label class="error" id="signupStartTimeeError"
													style="dispaly:none"></label>
											</div>
											<div class="col-md-6">
												<label class="text_date">报名结束时间：</label>
												<div class='input-group date form_date'
													data-date-format="yyyymmdd">
													<input name="signupEndTime" id="endTm" type='text'
														class="form-control input-sm" readonly="readonly"
														value="${exam.signupEndTime }" /> <span
														class="input-group-addon input-sm"> <span
														class="glyphicon glyphicon-remove"></span>
													</span> <span class="input-group-addon input-sm"> <span
														class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
												<label class="error" id="signupEndTimeError"
													style="dispaly:none"></label>
											</div>

										</div>
										<div class="form-group">
											<div class="col-md-6">
												<label class="text_date">考试开始时间：</label>
												<div class='input-group date form_date'
													data-date-format="yyyymmdd">
													<input name="examStartTime" id="examStart" type='text'
														class="form-control input-sm" readonly="readonly"
														value="${exam.examStartTime }" /> <span
														class="input-group-addon input-sm"> <span
														class="glyphicon glyphicon-remove"></span>
													</span> <span class="input-group-addon input-sm"> <span
														class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
												<label class="error" id="examStartTimeError"
													style="dispaly:none"></label>
											</div>
											<div class="col-md-6">
												<label class="text_date">考试结束时间：</label>
												<div class='input-group date form_date'
													data-date-format="yyyymmdd">
													<input name="examEndTime" id="examEnd" type='text'
														class="form-control input-sm" readonly="readonly"
														value="${exam.examEndTime }" /> <span
														class="input-group-addon input-sm"> <span
														class="glyphicon glyphicon-remove"></span>
													</span> <span class="input-group-addon input-sm"> <span
														class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
												<label class="error" id="examEndTimeError"
													style="dispaly:none"></label>
											</div>
										</div>

									</div>
								</c:if>
								<div class="form-group">
									<div class="col-md-6">
										<c:if test="${exam.examType==1 }">
											<input type="text" class="form-control" id="exam_time"
												placeholder="考试时长（分钟）" name="duration"
												value="${exam.duration }">
										</c:if>
										<c:if test="${exam.examType!=1 }">
											<input type="text" class="form-control" id="exam_time"
												placeholder="考试时长（分钟）" name="duration" disabled="disabled">
										</c:if>
										<label class="error" id="durationError" style="dispaly:none"></label>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">
										<label for="explain_container" class="text_title"
											style="vertical-align: top;">考试说明：</label>
										<c:if
											test="${!empty exam.examDescription &&exam.examDescription!='' }">
											<textarea id="explain_container" class="form-control"
												placeholder="考试说明，该信息考生可见（选填）" name="examDescription">${exam.examDescription }</textarea>
										</c:if>
										<c:if
											test="${empty exam.examDescription ||exam.examDescription=='' }">
											<textarea id="explain_container" class="form-control"
												placeholder="考试说明，该信息考生可见（选填）" name="examDescription"></textarea>
										</c:if>
									</div>
									<div class="form-group text-center">
										<br>
										<button type="button" class="btn-custom btn-long"
											onclick="createExam()">下一步</button>
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