<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../../../2/include/_taglib_includes.jsp"%>
<html>
<head>
<%@include file="../../../2/include/_html_head.jsp"%>
<script type="text/javascript"
	src="<%=path%>/media/2/js/exam/member/setAnnouncement.js"></script>
</head>
<link rel="stylesheet"
	href="<%=path%>/media/css/bootstrap-datetimepicker-master/bootstrap-datetimepicker.min.css"
	type="text/css" />
<script type="text/javascript"
	src="<%=path%>/media/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/media/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.validate.js"></script>

<title>基于B/新闻发言人在线学习培训平台</title>
</head>
<body>
	<%@include file="../../../2/include/_member_header.jsp"%>
	<div class="container">
		<!--main content start-->

		<!--message start-->
		<div class="content">
			<div class="row">

				<%@include file="../../include/hr/_announcement_part.jsp"%>

				<div class="col-md-9">
					<div class="block block-two">
						<!-- Default panel contents -->
						<div class="block-heading">
							<span>新建【${examName}】${typeEnum.info}</span> <input
								style="display:none" id="examId" value="${examId }">
						</div>
						<div class="block-body">
							<div class="col-md-12">
								<form class="form-horizontal" id="departmentInfo">
									<div class="form-group">
										<label for="title" class="col-sm-2 control-label text_title">通知标题：</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="title"
												id="title">
										</div>
									</div>
									<div class="form-group">
										<label for="body" class="col-sm-2 control-label text_title">通知正文：</label>
										<div class="col-sm-10">
											<textarea class="form-control" id="body" name="body"
												style="height: 324px;"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label text_title">有效截止日期：</label>
										<div class="col-sm-10">
											<div class='input-group date form_date'
												data-date-format="yyyymmdd">
												<input name="startTm" id="startTm" type='text'
													class="form-control input-sm" readonly="readonly" /> <span
													class="input-group-addon input-sm"> <span
													class="glyphicon glyphicon-remove"></span>
												</span> <span class="input-group-addon input-sm"> <span
													class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>
									</div>


									<div class="form-group">
										<label class="col-sm-2 control-label text_title">附件：</label>
										<div class="col-sm-10">
											<!-- 存数据库附件url -->
											<input type="hidden" name="fileName" id="fileName"> <input
												type="text" id="fileCover" class="form-control"
												placeholder="点击选择上传附件"
												onclick="$('input[id=useFile]').click();"
												onkeydown="return false;">
											<%--<input type="file" name="file" class="filestyle file_sm" data-size="sm" id="accessory">--%>
										</div>
									</div>
									<div class="form-group" style="display:none;">
										<div class="col-sm-9">
											<img id="filePreview" alt="上传文件预览" src="" />
										</div>
									</div>

									<%--<div class="form-group">--%>
									<%--<label class="col-sm-2 control-label text_title">发布对象：</label>--%>
									<%--<div class="col-sm-10">--%>

									<%--<c:set var="AnnouncementTypeEnum" value="<%=se.zust.enums.AnnouncementTypeEnum.values()%>" />--%>
									<%--<c:forEach var="type" items="${AnnouncementTypeEnum}" >--%>
									<%--<label class="checkbox-inline">--%>
									<%--<input name="orgType" type="checkbox" value="${type.type}"> ${type.info}--%>
									<%--</label>--%>
									<%--</c:forEach>--%>
									<%--</div>--%>
									<%--</div>--%>
									<div class="form-group text-center form-btn">
										<input type="submit" class="btn-long btn-custom">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--message end-->

		<!--main content end-->
		<!--footer start-->
		<div class="footer">
			<nav class="navbar navbar-default common_bg" id="footer_navbar">
				<div class="common_color">Copyright：2016-2017第一学期 J2EE课程设计</div>
			</nav>
		</div>
		<form class="form-horizontal" id="fileUploadForm"
			action="<%=path%>/announcement/file" role="form" method="post"
			enctype="multipart/form-data">
			<!-- 实际的file控件 -->
			<input id="useFile" type="file" name="useFile" style="display:none">
		</form>
		<input id="annType" value="${typeEnum.type}" type="text"
			style="display:none">
		<!--footer end-->
	</div>
	<script>
		window.onload = function() {
			var height = $("#attach_part").height() > $(
					".content .row .col-md-9").height() ? $("#attach_part")
					.height() + 20 : $(".content .row .col-md-9").height();
			console.log(height);
			$(".content .row .col-md-3").css("height", height);
		};

		$(".date").datetimepicker({
			format : 'yyyy-mm-dd hh:ii',
			startView : 3,
			language : 'zh-CN'
		});

		$('input[id=useFile]').change(
				function() {
					$('#fileCover').val($(this).val());
					$('#fileUploadForm').ajaxSubmit(
							{
								success : function(data) {
									if (typeof (data) != 'object')
										data = JSON.parse(data);
									console.info(data.errorInfo);
									$('#departmentInfo').find(
											'input[name=fileName]').val(
											data.files);
									console.info(data.files);
									$('#filePreview').attr(
											'src',
											'${pageContext.request.contextPath}/'
													+ data.files);

								}
							});
				});
	</script>


	<%@include file="../../../2/include/_html_body.jsp"%>
</body>
</html>
