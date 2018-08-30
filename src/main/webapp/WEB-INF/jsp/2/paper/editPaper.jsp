<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../include/_taglib_includes.jsp"%>

<!--散装办编辑组卷-->
<!DOCTYPE html>
<html>
<head lang="en">
<%@include file="../include/_html_head.jsp"%>
<link href="<%=basePath%>media/2/css/exam_member_formPaper.css"
	type="text/css" rel="stylesheet" />
<link href="<%=basePath%>media/2/css/jquery-ui.min.css" type="text/css"
	rel="stylesheet" />
<link href="<%=basePath%>media/2/css/bootstrap-datetimepicker.min.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="<%=basePath%>media/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>media/js//bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>media/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="<%=basePath%>media/2/js/paper/createPaper.js"></script>

<title>新闻发言人在线学习培训平台-自动组卷</title>
</head>
<body>

	<!--header start-->
	<%@include file="../include/_member_header.jsp"%>
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
				<div id="step1">
					<div class="block block-two">
						<!-- Default panel contents -->
						<!--组卷 start-->
						<div class="block-heading">
							<span>发布考试-自动组卷</span>
						</div>
						<div class="block-body">
							<div id="top_setting">
								<div id="difficulty" class="col-md-2" style="text-align: right;">难度设置：</div>
								<div class="col-md-6">
									<span class="range">0</span>
									<div id="slider" style="width: 85%;display: inline-block"></div>
									<span class="range">100</span>
								</div>
								<div class="col-md-4">
									易：<span id="easy" class="percent">30%</span> 中：<span
										id="middle" class="percent">40%</span> 难：<span id="hard"
										class="percent">30%</span> <input style="display:none"
										id="slideStart" value="${paper.slideStart }"> <input
										style="display:none" id="slideEnd" value="${paper.slideEnd }">
								</div>
							</div>
						</div>
						<!--组卷 end-->
					</div>
					<div class="block block-two" id="paper_content">
						<!-- Default panel contents -->
						<div class="block-heading">
							<span><label class="glyphicon glyphicon-equalizer"></label>根据题型、分类设置出题数量和分值</span>


							<div id="heading_right" class="heading_right">
								<div id="count">
									共<label id="count_question">${sumNum}</label>道, 共<label
										id="count_score">${sumScore}</label>分
								</div>
								<button class="btn-custom btn-middle" id="extract">
									下一步<label class="glyphicon glyphicon-menu-right"></label>
								</button>
							</div>
						</div>
						<div class="block-body" id="list" style="height: 380px;">
							<div class="list_header">
								<div class="col col-md-6">题型</div>
								<div class="col col-md-3">题目数</div>
								<div class="col col-md-3">分值</div>
							</div>
							<ul class="list_body">
								<c:forEach items="${questionTypes }" var="questionType">
									<li class="list_item">
										<div class="col col-md-6 topic">
											${questionType.name }<span
												class="glyphicon glyphicon-menu-down"></span>
										</div>
										<div class="col col-md-3 text_field">
											<input type="text" class="text subtotal" disabled="disabled"
												${questionType.count }>题
										</div>
										<div class="col col-md-3 text_field">
											<input type="text" class="text subscore" disabled="disabled"
												value="${questionType.score }">分/题
										</div>
									</li>
									<li>
										<ul class="classify_list">
											<c:forEach items="${questionType.subjects }" var="subject">
												<li class="classify_item">
													<div class="col-md-6 classify">${subject.name }</div>
													<div class="col col-md-3 text_field">
														<div class="input-group spinner">
															<input type="number"
																class="form-control paper_design_input" value="0"
																id="${questionType.id }&${subject.id }"
																max="${subject.sum }" min="0" value="${subject.count }">
														</div>
														<span style="vertical-align: top;">/${subject.sum
																	}</span>
													</div>
													<div class="col col-md-3"></div>
												</li>
											</c:forEach>
										</ul>
									</li>
								</c:forEach>

							</ul>
						</div>
					</div>
				</div>
				<div class="block block-two" id="step2">
					<!--填写试卷信息 start-->
					<div class="block-heading">
						<span>发布考试-自动组卷</span>
						<button id="go_back" class="btn-custom btn-long">
							<label class="glyphicon glyphicon-menu-left"></label>返回上级并重新选题
						</button>
					</div>
					<div class="block-body">
						<!--<div class="col-md-1"></div>-->
						<div class="col-md-12">
							<div id="message_top">

								<div class="message_item col-md-7">
									试卷总题数：非必选题（<span class="message_num" id="selected_question">${sumNum }</span>题）+必选题（<span
										class="message_num" id="required_question">${requiredQuestionNum }</span>题）=<span
										id="total_question" class="message_num"></span>题
								</div>
								<div class="col-md-5">
									考试类型： <select size="1" name="sample_1_length"
										aria-controls="sample_1" class="form-control" id="paper_type">
										<c:if test="${paper.paperType==0 }">
											<option value="0" selected="selected">一人一卷</option>
											<option value="1">众人一卷</option>
										</c:if>
										<c:if test="${paper.paperType==1 }">
											<option value="0">一人一卷</option>
											<option value="1" selected="selected">众人一卷</option>
										</c:if>
									</select>
								</div>
								<div class="message_item col-md-7">
									试卷总分数：非必选题（<span class="message_num" id="selected_score">${sumScore }</span>分）+必选题（<span
										class="message_num" id="required_score">${requiredQuestionMarks }</span>分）=<span
										id="total_score" class="message_num"></span>分
								</div>
								<div class="col-md-5"></div>
							</div>
							<div id="passInfo">
								<div class="col-md-5">
									及格方式：
									<c:if test="${!empty paper.passNum }">
										<label class="radio-inline"> <input type="radio"
											name="passingType" id="inlineRadio1" value="number"
											class="radio_group" checked="checked" style="position:static">
											及格题数
										</label>
										<label class="radio-inline"> <input type="radio"
											name="passingType" id="inlineRadio2" value="score"
											class="radio_group" style="position:static"> 及格分数
										</label>
									</c:if>
									<c:if test="${!empty paper.passScore }">
										<label class="radio-inline"> <input type="radio"
											name="passingType" id="inlineRadio1" value="number"
											class="radio_group" style="position:static"> 及格题数
										</label>
										<label class="radio-inline"> <input type="radio"
											name="passingType" id="inlineRadio2" value="score"
											class="radio_group" checked="checked" style="position:static">
											及格分数
										</label>
									</c:if>
								</div>
								<c:if test="${!empty paper.passNum }">
									<div class="col-md-3" id="passing_num">
										及格题数：<input type="text" class="text" id="pass_num"
											value="${paper.passNum }">题 <label class="error"
											id="passNumError" style="dispaly:none"></label>
									</div>
									<div class="col-md-3" id="passing_score" style="display:none">
										及格分数：<input type="text" class="text" id="pass_score">分
										<label class="error" id="passScoreError" style="dispaly:none"></label>
									</div>
								</c:if>
								<c:if test="${!empty paper.passScore }">
									<div class="col-md-3" id="passing_num" style="display:none">
										及格题数：<input type="text" class="text" id="pass_num">题 <label
											class="error" id="passNumError" style="dispaly:none"></label>
									</div>
									<div class="col-md-3" id="passing_score">
										及格分数：<input type="text" class="text" id="pass_score"
											value="${paper.passScore }">分 <label class="error"
											id="passScoreError" style="dispaly:none"></label>
									</div>
								</c:if>
							</div>
							<div id="message_content">
								<div id="paper_title">
									<textarea id="title_container"
										class="text_area form-control input-lg" placeholder="试卷名字（必填）">${paper.paperName }</textarea>
								</div>
								<div id="paper_describe">
									<c:if
										test="${!empty paper.paperDescription && paper.paperDescription!=''}">
										<textarea id="describe_container"
											class="text_area form-control"
											placeholder="试卷描述，该信息考生不可见（选填）">${paper.paperDescription }</textarea>
									</c:if>
									<c:if
										test="${empty paper.paperDescription || paper.paperDescription==''}">
										<textarea id="describe_container"
											class="text_area form-control"
											placeholder="试卷描述，该信息考生不可见（选填）"></textarea>
									</c:if>
								</div>
							</div>
							<c:if test="${paper.paperType==0 }">
								<input type="button" class="btn btn-primary paper_btn"
									value="保存试卷" id="paperKeep"
									onclick="savePaperInfo('<%=basePath%>')" style="display:inline">
								<input type="button" class="btn btn-primary paper_btn"
									value="保存试卷" id="paperPreview" data-toggle="modal"
									data-target="#preview_Modal"
									onclick="savePaperInfo('<%=basePath%>')" style="display:none">
							</c:if>
							<c:if test="${paper.paperType==1 }">
								<input type="button" class="btn btn-primary paper_btn"
									value="保存试卷" id="paperKeep"
									onclick="savePaperInfo('<%=basePath%>')" style="display:none">
								<input type="button" class="btn btn-primary paper_btn"
									value="保存试卷" id="paperPreview" data-toggle="modal"
									data-target="#preview_Modal"
									onclick="savePaperInfo('<%=basePath%>')" style="display:inline">
							</c:if>
						</div>
						<!--<div class="col-md-1"></div>-->
					</div>
					<!--填写试卷信息 end-->
					<div style="display:none">
						<form action="<%=basePath%>paper/member/basicInfo" method="post"
							id="data">
							<input type="text" id="examId" name="examId" value="${examId }">
							<input type="text" id="easyPercent" name="easyPercent"> <input
								type="text" id="mediumPercent" name="mediumPercent"> <input
								type="text" id="hardPercent" name="hardPercent"> <input
								type="text" id="paperDesignString" name="paperDesignString">
							<input type="text" id="paperType" name="paperType"> <input
								type="text" id="paperName" name="paperName"> <input
								type="text" id="paperDescription" name="paperDescription">
							<input type="text" id="passNum" name="passNum"> <input
								type="text" id="passScore" name="passScore">
						</form>
						<input type="text" id="questionsString" name="questionString">
						<input type="text" id="paperId" name="paperId">
					</div>
				</div>

			</div>
		</div>
		<!--message end-->
	</div>
	<!--preview modal start-->
	<div class="modal fade bs-example-modal-lg" id="preview_Modal"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="previewModalLabel">试卷预览</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="paperDetail"></form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="savePaper('<%=basePath%>')">确认</button>
					<button type="button" class="btn btn-primary"
						onclick="showQuestions('<%=basePath%>')">重新组卷</button>
				</div>
			</div>
		</div>
	</div>
	<!--preView modal end-->

	<!--main content end-->
	<!--footer start-->
	<%@include file="../include/_html_body.jsp"%>
	<!--footer end-->

	<script>
		window.onload = function() {

			var height = $("#attach_part").height() + 20 > $(
					".content .row>.col-md-9").height() ? $("#attach_part")
					.height() + 20 : $(".content .row>.col-md-9").height();
			height = height > document.body.clientHeight - 228 ? height
					: document.body.clientHeight - 228;
			$(".content .row>.col-md-3").css("height", height);

			var radio = $("input[name='passingType']");
			$("input[name='passingType']")
					.change(
							function() {
								if ($("input[name='passingType']:checked")
										.val() == 'number') {
									$("#passing_num").show();
									$("#passing_score").hide();
								} else if ($(
										"input[name='passingType']:checked")
										.val() == 'score') {
									$("#passing_num").hide();
									$("#passing_score").show();
								}
							});

			var numList = $("input[type = number]");
			var subtotal = $(".subtotal");
			var subscore = $(".subscore");
			var selected_question = parseInt($("#selected_question").html()), selected_score = parseInt($(
					"#selected_score").html()), required_question = parseInt($(
					"#required_question").html()), requied_score = parseInt($(
					"#required_score").html());

			$("input[type = number]")
					.change(
							function() {
								for (var i = 0; i < subtotal.length; i++) {
									var obj = $(subtotal[i]).parent().parent()
											.next().children().children()
											.children().children().children();
									$(subtotal[i]).val(sum(obj));
								}
								$("#count_question").html(sum(subtotal));
								$("#selected_question").html(sum(subtotal));
								$("#count_score").html(getScore());
								$("#selected_score").html(getScore());
								var selected_question = parseInt($(
										"#selected_question").html()), selected_score = parseInt($(
										"#selected_score").html()), required_question = parseInt($(
										"#required_question").html()), requied_score = parseInt($(
										"#required_score").html());
								$("#total_question").html(
										selected_question + required_question);
								$("#total_score").html(
										selected_score + requied_score);
							});
			$(".subscore").change(function() {
				$("#count_score").html(getScore());
				$("#selected_score").html(getScore());
			});
			$("#total_question").html(selected_question + required_question);

			$("#total_score").html(selected_score + requied_score);

			//求和函数
			function sum(obj) {
				var sumFinal = 0;
				for (var i = 0; i < obj.length; i++) {
					sumFinal = sumFinal + parseInt($(obj[i]).val());
				}
				return sumFinal;
			}
			//求总分函数
			function getScore() {
				var scoreFinal = 0;
				for (var j = 0; j < subtotal.length; j++) {
					if ($(subtotal[j]).val() == "") {
						$(subtotal[j]).val(0);
					}
					if ($(subscore[j]).val() == "") {
						$(subscore[j]).val(0);
					}
					scoreFinal = scoreFinal + parseInt($(subtotal[j]).val())
							* parseInt($(subscore[j]).val());
				}
				return scoreFinal;

			}

			$("#paper_type").click(function() {
				if ($("select option:checked").val() == 1) {
					$("#paperPreview").show();
					$("#paperKeep").hide();
				} else if ($("select option:checked").val() == 0) {
					$("#paperPreview").hide();
					$("#paperKeep").show();
				}
			});

			showSlider();
			var menu = new Array();
			menu = $(".menu");
			var a_hover = $(".a_hover");
			for (var k = 0; k < a_hover.length; k++) {
				$(a_hover[k]).next().show();
			}
			for (var i = 0; i < menu.length; i++) {
				menu[i].onclick = function() {
					var sub_menu = $(this).next();
					for (var j = 0; j < menu.length; j++) {
						if (j != i) {
							$(menu[j]).removeClass("a_hover");
							$(menu[j]).next().hide();
						}

					}
					$(this).addClass("a_hover");
				}
			}

			var sub_list = new Array();
			sub_list = $(".sub_list");
			for (var m = 0; m < sub_list.length; m++) {
				sub_list[m].onclick = function() {
					for (var n = 0; n < sub_list.length; n++) {
						if (n != m) {
							$(sub_list[n]).removeClass("list_hover");
						}
					}

					$(this).addClass("list_hover");
				}
			}

			var list = new Array();
			list = $(".topic");
			for (var t = 0; t < list.length; t++) {
				list[t].onclick = function() {
					if ($(this).parent().next().children().is(":hidden")) {
						$(this).parent().next().children().show();
						$(this).addClass("topic_click");
					} else if ($(this).parent().next().children()
							.is(":visible")) {
						$(this).parent().next().children().hide();
						$(this).removeClass("topic_click");
					}

				}
			}

			var extract = $("#extract");
			extract.click(function() {
				$("#step1").hide();
				$("#step2").show();
			})
			var back = $("#go_back");
			back.click(function() {
				$("#step1").show();
				$("#step2").hide();
			})

		}
		$("#slider").slider({
			max : 100,
			min : 0,
			step : 1,
			range : true,
			values : [ $("#slideStart").val(), $("#slideEnd").val() ]
		});
		function showSlider() {
			var timer = null;
			clearInterval(timer);
			timer = setInterval(function() {
				var values = $("#slider").slider("option", "values");
				$("#easy").html(values[0] + "%");
				$("#middle").html((values[1] - values[0]) + "%");
				$("#hard").html((100 - values[1]) + "%");
			}, 500);

		}
	</script>
</body>
</html>