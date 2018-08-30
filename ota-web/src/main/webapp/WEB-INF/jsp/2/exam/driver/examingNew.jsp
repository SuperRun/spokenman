<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../../../2/include/_taglib_includes.jsp"%>

<!--模拟考试-->
<!DOCTYPE html>
<html>
<head lang="en">
<%@include file="../../../2/include/_html_head.jsp"%>
<link href="<%=basePath %>media/2/css/exam_driver_practice.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript"
	src="<%=basePath%>media/2/js/exam/driver/examing.js"></script>
<title>新闻发言人在线学习培训平台-考试中</title>
</head>
<body>

	<!--header start-->
	<div class="header" id="header">
		<div class="side_nav">
			<div class="user_nav">
				<a href="#" class="dropdown-toggle navbar-right common_color"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false" id="su_user"><img
					src="<%=basePath%>media/images/user.png">${examingBaseInfo.driverName }<span
					class="caret"></span></a>
				<ul class="dropdown-menu dropdown-menu-right">

					<li><a href="#" onclick="logout('<%=basePath%>')">退出账号</a></li>
				</ul>
			</div>
		</div>
		<nav class="navbar navbar-default common_bg">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand common_color"> <img
                            src="<%=basePath%>media/images/logo_exam.png" class="logo_pic">
						新闻发言人在线学习培训平台
					</a>
				</div>
			</div>
		</nav>
	</div>
	<!--header end-->

	<!--main content start-->
	<div class="content practice_content container">
		<div class="row">
			<div class="info-up">
				<div class="col-md-3">
					<div class="block block-two">
						<div class="block-heading">
							<span>考生信息</span>
						</div>
						<div class="block-body text-center">
							<img src="<%=basePath%>media/images/user_header.png">

							<p class="name">
								考生姓名：<span class="nickname ellipsis">${examingBaseInfo.driverName }</span>
							</p>

							<p>考试名称：${examingBaseInfo.examName }</p>

							<p>试卷名称：${examingBaseInfo.paperName }</p>

							<p>考试时长：${examingBaseInfo.duration }</p>

						</div>
					</div>
				</div>
				<div class="col-md-9">
					<div class="block block-two">
						<div class="block-heading">
							<span>考试题目</span>
						</div>
						<div style="display:none">
							<form
								action="<%=basePath%>exam/driver/examing/execution/${examId }"
								method="post" id="data">
								<input id="questionIdNow" name="questionIdNow"
									value="${changeQuestion.questionIdNow }"> <input
									id="questionTypeNow" name="questionTypeNow"
									value="${changeQuestion.questionTypeNow }"> <input
									id="answer" name="answer" value="${changeQuestion.answer }">
								<input id="sequenceNow" name="sequenceNow"
									value="${changeQuestion.sequenceNow }"> <input
									id="sequenceNext" name="sequenceNext"
									value="${changeQuestion.sequenceNext }"> <input
									id="submitable" name="submitable"
									value="${changeQuestion.submitable }"> <input id="sum"
									name="sum" value="${changeQuestion.sum }">
								<!-- 是否已交卷，0表示未交卷，1表示已交卷 -->
								<input id="haveSubmit" name="haveSubmit"
									value="${changeQuestion.haveSubmit }">
							</form>
							<div>
								<input id="examStartTime" name="examStartTime"
									value="${examTime.examStartTime }"> <input
									id="examEndTime" name="examEndTime"
									value="${examTime.examEndTime }"> <input id="now"
									name="now" value="${examTime.now }"> <input id="examId"
									name="examId" value="${examId }"> <input id="basePath"
									name="basePath" value="<%=basePath%>">
								<!-- 考试状态 0考试未开始，1正在考试，2考试结束 -->
								<input id="examStatus" name="examStatus">

								<!-- 题目类型，多选题选项可选择多个 -->
								<div class="question_type" id="question_type"
									style="display: none;">${question.typeName }</div>
							</div>
						</div>
						<div class="block-body" id="question-container">
							<div class="col-md-9">
								<h3 class="question" id="question">
									<span class="serial_number">${changeQuestion.sequenceNow }</span>&nbsp;<span
										class="question_content">${question.content }</span>
								</h3>
								<ul id="answer_list">
									<c:forEach items="${question.questionItems }"
										var="questionItem">
										<!-- 文字选项 -->
										<c:if
											test="${!empty questionItem.content && questionItem.content!='' }">
											<li class="answer_item"><span class="">${questionItem.sequence }</span>&nbsp;<span
												class="answer_content">${questionItem.content }</span></li>
										</c:if>
										<!-- 图片选项 -->
										<c:if
											test="${!empty questionItem.pic && questionItem.pic!='' }">
											<li class="answer_item"><span class="">${questionItem.sequence }</span>&nbsp;<span
												class="answer_content"> <img
													src="<%=basePath %>${questionItem.pic}" width="100px"
													height="100px"></span></li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
							<div class="col-md-3 text-center" id="media-container">
								<c:if test="${empty question.pic1 || question.pic1=='' }">
									<div style="width:170px;height:170px"></div>
								</c:if>
								<c:if test="${!empty question.pic1 && question.pic1!='' }">
									<img src="<%=basePath %>${question.pic1}" title="图1"
										width="160px" height="160px">
									<a data-toggle="modal" data-target="#bigPic_Modal1" href="#">查看大图</a>
								</c:if>
								<c:if test="${!empty question.pic2 && question.pic2!='' }">
									<img src="<%=basePath %>${question.pic2}" title="图2"
										width="160px" height="160px">
									<a data-toggle="modal" data-target="#bigPic_Modal2" href="#">查看大图</a>
								</c:if>
								<c:if test="${!empty question.pic3 && question.pic3!='' }">
									<img src="<%=basePath %>${question.pic3}" title="图3"
										width="160px" height="160px">
									<a data-toggle="modal" data-target="#bigPic_Modal3" href="#">查看大图</a>
								</c:if>
							</div>
							<div id="answer">
								<!-- 选择题 -->
								<c:if test="${!empty question.questionItems }">
                    		      请选择：
                        <ul id="user_answer">
										<c:forEach items="${question.questionItems }"
											var="questionItem" varStatus="status">
											<c:if
												test="${!empty questionItem.selected && questionItem.selected }">
												<li class="ua_item answer_hover"><a href="#"
													onclick="selectQuestionItems('${questionItem.sequence}')">${questionItem.sequence }</a></li>
											</c:if>
											<c:if
												test="${empty questionItem.selected || !questionItem.selected }">
												<li class="ua_item"><a href="#"
													onclick="selectQuestionItems('${questionItem.sequence}')">${questionItem.sequence }</a></li>
											</c:if>
										</c:forEach>
									</ul>
									<div style="display:none">
										<input type="text" id="questionItemsSelected"
											name="questionItemsSelected" value="">
									</div>
								</c:if>
								<!-- 非选择题 -->
								<c:if test="${empty question.questionItems }">
								<div>
									  <ul id="user_answer">
									  </ul>
								</div>
                    		请解答：
                    		<c:if test="${ empty question.answerPre }">
										<input id="answer_input" type="text">
									</c:if>
									<c:if test="${ !empty question.answerPre }">
										<input id="answer_input" type="text"
											value="${question.answerPre }">
									</c:if>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="block block-two">
					<div class="block-heading" id="examStatusShow">剩余时间</div>
					<div class="block-body text-center">
						<span id="count_down"></span>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="block block-two">
					<div class="block-heading">
						<span>提示信息</span>
					</div>
					<div class="block-body">
						<div class="tip-content" id="examTipsContent">${question.help }</div>
					</div>
				</div>
			</div>
			<div class="col-md-5 group_btn">

				<c:if test="${changeQuestion.sequenceNow!=1 }">
					<button type="button" class="btn btn-primary login_btn"
						onclick="changeQuestion(-1)">上一题</button>
				</c:if>
				<c:if test="${changeQuestion.sequenceNow!=changeQuestion.sum }">
					<button type="button" class="btn btn-primary login_btn"
						onclick="changeQuestion(0)">下一题</button>
				</c:if>
				<button class="btn btn-success login_btn" id="submit_paper"
					onclick="submitPaper('${examId}','<%=basePath%>')">交卷</button>
			</div>
			<div class="col-md-12">
				<div class="block">
					<div class="block-heading">
						<span>答题信息</span>
					</div>
					<div class="block-body">
						<ul class="answer_part">
							<c:forEach items="${changeQuestion.sequenceStatus }" var="state"
								varStatus="idx">
								<c:if test="${idx.index+1==changeQuestion.sequenceNow }">
									<li class="col_head row0"><a
										href="javascript:changeQuestion('${idx.index+1 }');"
										class="answer_hover">${idx.index+1 }</a></li>
								</c:if>
								<c:if test="${idx.index+1!=changeQuestion.sequenceNow }">
									<c:if test="${state==true }">
										<!-- 已答题 -->
										<li class="row0"><a
											href="javascript:changeQuestion('${idx.index+1 }');"
											class="answer_right">${idx.index+1 }</a></li>
									</c:if>
									<c:if test="${state==false }">
										<!-- 未答题 -->
										<li class="row0"><a
											href="javascript:changeQuestion('${idx.index+1 }');">${idx.index+1 }</a></li>
									</c:if>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--bigPic modal start-->
	<div class="modal fade" id="bigPic_Modal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="bigPicModalLabel">查看大图</h4>
				</div>
				<div class="modal-body text-center">
					<img src="<%=basePath %>${question.pic1}"
						style="width: 500px;height: auto;">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--bigPic modal end-->
	<!--bigPic modal start-->
	<div class="modal fade" id="bigPic_Modal2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="bigPicModalLabel">查看大图</h4>
				</div>
				<div class="modal-body text-center">
					<img src="<%=basePath %>${question.pic2}"
						style="width: 500px;height: auto;">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--bigPic modal end-->
	<!--bigPic modal start-->
	<div class="modal fade" id="bigPic_Modal3" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="bigPicModalLabel">查看大图</h4>
				</div>
				<div class="modal-body text-center">
					<img src="<%=basePath %>${question.pic3}"
						style="width: 500px;height: auto;">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--bigPic modal end-->

	<!--main content end-->
	<!--footer start-->
	<div class="footer">
		<nav class="navbar navbar-default common_bg" id="footer_navbar">
            <div class="common_color">
				<p>版权所有 © 浙江科技学院 xcb@zust.edu.cn 浙ICP备11051284号</p>

				<p>地址：浙江·杭州市留和路318号 邮编：310023</p>
			</div>
		</nav>
	</div>
	<!--footer end-->
	</div>
	<script>
		window.onload = function() {
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

					if (sub_menu.css("display") == "none") {
						sub_menu.show();
					} else if (sub_menu.css("display") != "none") {
						sub_menu.hide();
					}

				}
			}
			console.log($("#question_type").html());
			if ($("#question_type").html() == '多选题') {

				$(".ua_item").on('click', function() {
					if ($(this).hasClass('answer_hover')) {
						$(this).removeClass('answer_hover');
					} else {
						$(this).addClass('answer_hover');
					}
				})
			} else {
				clickClass($(".ua_item"), "answer_hover");
			}

			clickClass($(".sub_list"), "list_hover");

			clickClass($(".answer_part a"), "answer_hover");

		}
		function clickClass(list, className) {
			for (var i = 0; i < list.length; i++) {
				list[i].onclick = function() {
					for (var j = 0; j < list.length; j++) {
						if (j != i) {
							$(list[j]).removeClass(className);
						}

					}
					$(this).addClass(className);
				}
			}
		}
	</script>
</body>
</html>