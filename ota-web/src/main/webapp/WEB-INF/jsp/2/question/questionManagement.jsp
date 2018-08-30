<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../include/_taglib_includes.jsp"%>

<!DOCTYPE html>
<html>
<head lang="en">
<%@include file="../include/_html_head.jsp"%>
<title>新闻发言人在线学习培训平台-试题管理</title>
<link href="<%=basePath%>media/2/css/exam_member_questionManagement.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="<%=basePath%>media/2/js/question/pageBaseJS.js"></script>
<script src="<%=basePath%>media/2/js/question/questionManagement.js"></script>
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

				<div class="block block-two" id="questionManagement_part">
					<!-- Default panel contents -->
					<div class="block-heading">
						<span>试题管理</span>
					</div>
					<form id="data" action="<%=basePath%>question/list" method="get">
						<div style="display: none">
							<input name="page" id="page" value="${pageBaseDto.page }">
							<input name="rows" id="rows" value="${pageBaseDto.rows }">
							<input name="pageNum" id="pageNum"
								value="${pageBaseDto.pageNum }"> <input name="searchKey"
								id="searchKey" value="${pageBaseDto.searchKey }"> <input
								name="status" id="status" value="${pageBaseDto.status }">
							<input id="subjectIdStr" name="subjectIdStr"
								value="${subjectIdStr }"> <input id="typeIdStr"
								name="typeIdStr" value="${typeIdStr }"> <input
								id="requiredStr" name="requiredStr" value="${requiredStr }">
						</div>
					</form>
					<div class="block-body questionManagement_body"
						id="questionManagement_body">
						<div id="pb_top" class="pb_top">
							<div class="col-md-3">
								<label id="record_num"> <select size="1"
									name="sample_1_length" aria-controls="sample_1" class=""
									id="rows_input" onchange="submit()">
										<c:if test="${pageBaseDto.rows==1 }">
											<option value="1" selected="selected">1</option>
										</c:if>
										<c:if test="${pageBaseDto.rows!=1 }">
											<option value="1">1</option>
										</c:if>
										<c:if test="${pageBaseDto.rows==5 }">
											<option value="5" selected="selected">5</option>
										</c:if>
										<c:if test="${pageBaseDto.rows!=5 }">
											<option value="5">5</option>
										</c:if>
										<c:if test="${pageBaseDto.rows==15 }">
											<option value="15" selected="selected">15</option>
										</c:if>
										<c:if test="${pageBaseDto.rows!=15 }">
											<option value="15">15</option>
										</c:if>
										<c:if test="${pageBaseDto.rows==20 }">
											<option value="20" selected="selected">20</option>
										</c:if>
										<c:if test="${pageBaseDto.rows!=20 }">
											<option value="20">20</option>
										</c:if>
								</select> 记录/页
								</label>
							</div>
							<div class="search_part text-center col-md-5">
								<label id="search"><input type="text" aria-controls=""
									class="form-control" placeholder="题干信息" id="search_text"
									value="${pageBaseDto.searchKey }"> </label> <input
									type="button" class="btn btn-primary" value="搜索"
									id="search_btn" onclick="submit()">
							</div>
							<div class="col-md-4 text-right">
								<a href="<%=basePath%>question/import" class="btn btn-success">添加题目</a>
							</div>
							<div class="filter_div">
								<ul class="filter_ul">
									<li><label class="text_title">题型：</label>
										<div class="checkbox-inline">
											<label> <input type="checkbox" name="questionType"
												value="-1" class="all_checkBoxQuestionType"> 全部
											</label>
										</div> <c:forEach items="${questionTypes }" var="questionType">
											<div class="checkbox-inline">
												<label> <input type="checkbox" name="questionType"
													value="${questionType.id }"
													class="condition_checkBoxQuestionType">
													${questionType.name }
												</label>
											</div>
										</c:forEach></li>
									<li><label class="text_title">知识点：</label>
										<div class="checkbox-inline">
											<label> <input type="checkbox" name="questionSubject"
												value="-1" class="all_checkBoxQuestionSubject"> 全部
											</label>
										</div> <c:forEach items="${questionSubjects }" var="questionSubject">
											<div class="checkbox-inline">
												<label> <input type="checkbox"
													name="questionSubject" value="${questionSubject.id }"
													class="condition_checkBoxQuestionSubject">
													${questionSubject.name }
												</label>
											</div>
										</c:forEach></li>
									<li><label class="text_title">必选题：</label>
										<div class="checkbox-inline">
											<label> <input type="checkbox"
												name="questionRequired" value="-1"
												class="all_checkBoxQuestionRequired"> 全部
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input type="checkbox"
												name="questionRequired" value="0"
												class="condition_checkBoxQuestionRequired"> 非必选
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input type="checkbox"
												name="questionRequired" value="1"
												class="condition_checkBoxQuestionRequired"> 必选
											</label>
										</div></li>
								</ul>

							</div>
						</div>
						<div id="pb_content">
							<ul class="question_list">
								<c:forEach items="${questions}" var="question"
									varStatus="status">
									<li class="question_item">
										<div class="question_info">
											<ul class="info_list">
												<li class="question_num"><label>${status.index+1 }</label></li>
												<li class="question_type"><label>${question.typeName}</label></li>
												<li class="info_item"><label class="question_required">
														<c:if
															test="${!empty question.required && question.required }">
														必选题
													</c:if> <c:if
															test="${empty question.required || !question.required }">
														非必选题
													</c:if>
												</label></li>
												<li class="info_item"><label class="question_point">${question.subjectName }</label></li>
												<li class="info_item"><label class="question_score">${question.marks }</label></li>
												<li class="info_item"><label
													class="question_complexity">${question.difficulty }</label></li>
												<li class="question_time">${question.createTime }</li>
												<li class="show_answer"><a>收起<span
														class="glyphicon glyphicon-chevron-up"></span></a></li>
												<li class="operation_part">
													<div class="dropdown">
														<a id="dLabel${question.id }"
															data-toggle="dropdown" aria-haspopup="true"
															aria-expanded="false"> 操作 <span class="caret"></span>
														</a>
														<ul class="dropdown-menu" role="menu"
															aria-labelledby="dLabel${question.id }">
															<li><a
																href="<%=basePath%>question/edit/${question.id}">编辑</a></li>
															<li role="separator" class="divider"></li>
															<li><a href="#" data-toggle="modal"
																data-target="#delete_Modal"
																onclick="conveyQuestionIdForDelete('${question.id}')">删除</a></li>
															<li role="separator" class="divider"></li>
															<c:if
																test="${!empty question.required && question.required }">
																<li><a href="#"
																	onclick="removeRequiredQuestion('${question.id}','<%=basePath%>')">设定为非必选题</a></li>
															</c:if>
															<c:if
																test="${empty question.required || !question.required }">
																<li><a href="#"
																	onclick="setRequiredQuestion('${question.id}','<%=basePath%>')">设定为必选题</a></li>
															</c:if>
														</ul>
													</div>
												</li>
											</ul>
										</div>
										<div class="question_content">
											<div class="question_stem" data-toggle="modal"
												data-target="#questionInfo_Modal">${question.content }</div>
											<div class="stem_pic">
												<ul class="pic_list">
													<c:if test="${!empty question.pic1 && questin.pic1!='' }">
														<li class="pic_item"><img
															src="<%=basePath%>${question.pic1}" width="200px" height="200px">图1</li>
													</c:if>
													<c:if test="${!empty question.pic2 && questin.pic2!='' }">
														<li class="pic_item"><img
															src="<%=basePath%>${question.pic2}" width="200px" height="200px">图2</li>
													</c:if>
													<c:if test="${!empty question.pic3 && questin.pic3!='' }">
														<li class="pic_item"><img
															src="<%=basePath%>${question.pic3}" width="200px" height="200px">图3</li>
													</c:if>
												</ul>
											</div>
										</div> <c:if test="${!empty question.questionItems }">
											<div class="answer_content">
												<ul class="answer_list">
													<c:forEach items="${question.questionItems }"
														var="questionItem">
														<c:if
															test="${!empty questionItem.selected && questionItem.selected  }">
															<li class="answer_item"><input type="checkbox"
																checked="checked" disabled><span>${questionItem.sequence }</span>
																<c:if
																	test="${!empty questionItem.content && questionItem.content!='' }">
																	${questionItem.content }
																</c:if> <c:if
																	test="${!empty questionItem.pic && questionItem.pic!='' }">
																	<img src="<%=basePath%>${questionItem.pic}">
																</c:if></li>
														</c:if>
														<c:if
															test="${empty questionItem.selected || !questionItem.selected  }">
															<li class="answer_item"><input type="checkbox"
																disabled><span>${questionItem.sequence }</span>
																<c:if
																	test="${!empty questionItem.content && questionItem.content!='' }">
																	${questionItem.content }
																</c:if> <c:if
																	test="${!empty questionItem.pic && questionItem.pic!='' }">
																	<img src="<%=basePath%>${questionItem.pic}">
																</c:if></li>
														</c:if>
													</c:forEach>


												</ul>
											</div>
										</c:if> <c:if test="${!empty question.answers }">
											<div class="reference_version">
												<span>参考答案</span>
												<div class="correct_answer">
													<c:forEach items="${question.answers }" var="answer"
														varStatus="status">
														<c:if test="${status.index+1 == 1 }">
															<label class="answer">${answer }</label>
														</c:if>
														<c:if test="${status.index+1 !=1 }">
															或<label class="answer">${answer }</label>
														</c:if>
													</c:forEach>
												</div>
											</div>
										</c:if>
									</li>

								</c:forEach>

							</ul>

							<span>总共<label class="num" id="records">${pageBaseDto.sum
									}</label>条记录
								| 第<label class="num" id="page_num">${pageBaseDto.page
									}</label>页/共<label
								class="num" id="pages">${pageBaseDto.pageNum
									}</label>页
							</span>
							<nav id="paging_nav">
								<ul class="pagination">
									<li><a href="#" aria-label="first"
										class="glyphicon glyphicon-step-backward" alt="第一页"
										title="第一页" onclick="changePage(1)"></a></li>
									<!-- 第一页不能上一页 -->
									<c:if test="${pageBaseDto.page==1 }">
										<li><a href="#" aria-label="previous"
											class="glyphicon glyphicon-chevron-left" alt="上一页"
											title="上一页"></a></li>
									</c:if>
									<c:if test="${pageBaseDto.page!=1 }">
										<li><a href="#" aria-label="previous"
											class="glyphicon glyphicon-chevron-left" alt="上一页"
											title="上一页" onclick="changePage('${pageBaseDto.page-1}')"></a>
										</li>
									</c:if>
									<!-- 最后一页没有下一页 -->
									<c:if test="${pageBaseDto.page==pageBaseDto.pageNum }">
										<li><a href="#" aria-label="next"
											class="glyphicon glyphicon-chevron-right" alt="下一页"
											title="下一页"></a></li>
									</c:if>
									<c:if test="${pageBaseDto.page!=pageBaseDto.pageNum }">
										<li><a href="#" aria-label="next"
											class="glyphicon glyphicon-chevron-right" alt="下一页"
											title="下一页" onclick="changePage('${pageBaseDto.page+1}')"></a>
										</li>
									</c:if>
									<li><a href="#" aria-label="last"
										class="glyphicon glyphicon-step-forward" alt="最后一页"
										title="最后一页" onclick="changePage('${pageBaseDto.pageNum}')"></a>
									</li>
									<li><input type="text" aria-controls=""
										class="input-custom" placeholder="页码" id="page_text">
										<input type="button" class="btn-custom btn-middle" value="跳转"
										id="goto_btn" onclick="changePage(-1)"></li>

								</ul>
							</nav>



						</div>
					</div>
				</div>

			</div>
		</div>
		<!--message end-->
	</div>
	<!--main content end-->


	<!--delete modal start-->
	<div class="modal fade" id="delete_Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="deleteInfoModalLabel">删除确认</h4>
				</div>
				<div class="modal-body">确定要删除该题吗？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="deleteQuestion('<%=basePath%>')">删除</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
				<input id="questionIdForDelete" style="display:none">
			</div>
		</div>
	</div>
	<!--delete modal end-->

	<!--footer start-->
	<%@include file="../include/_html_body.jsp"%>
	<!--footer end-->

	<script>
		window.onload = function() {

			var height = $("#attach_part").height() + 20 > $(
					".content .row .col-md-9").height() ? $("#attach_part")
					.height() + 20 : $(".content .row .col-md-9").height();
			height = height > document.body.clientHeight - 228 ? height
					: document.body.clientHeight - 228;
			$(".content .row>.col-md-3").css("height", height);
		}

		$(".show_answer")
				.on(
						'click',
						function() {
							var answerContent = $(this).parent().parent()
									.next().next();
							var queation_creater = $(this).parent().parent()
									.next().next().next();
							var operation = $(this).children();
							console.log($(answerContent).css('display'));
							if (answerContent.css('display') == 'none') {
								$(answerContent).show(300);
								$(queation_creater).show(300);
								$(operation)
										.html(
												'收起<span class="glyphicon glyphicon-chevron-up"></span>');
							} else {
								$(answerContent).hide(300);
								$(queation_creater).hide(300);

								$(operation)
										.html(
												'展开<span class="glyphicon glyphicon-chevron-down"></span>');
							}
						});

		//题型类型筛选，全部事件
		$(".all_checkBoxQuestionType").change(function() {

			if ($(this).is(':checked') == true) {
				$(".condition_checkBoxQuestionType").removeAttr('checked');
			}
			submitChange();
		});

		$(".condition_checkBoxQuestionType").change(
				function() {
					if ($(".all_checkBoxQuestionType").is(':checked') == true
							&& $(this).is(':checked') == true) {
						$(".all_checkBoxQuestionType").removeAttr('checked');
					}
					submitChange();
				});

		//知识点类型筛选，全部事件
		$(".all_checkBoxQuestionSubject").change(function() {

			if ($(this).is(':checked') == true) {
				$(".condition_checkBoxQuestionSubject").removeAttr('checked');
			}
			submitChange();
		});

		$(".condition_checkBoxQuestionSubject")
				.change(
						function() {
							if ($(".all_checkBoxQuestionSubject")
									.is(':checked') == true
									&& $(this).is(':checked') == true) {
								$(".all_checkBoxQuestionSubject").removeAttr(
										'checked');
							}
							submitChange();
						});

		//必选题类型筛选，全部事件
		$(".all_checkBoxQuestionRequired").change(function() {

			if ($(this).is(':checked') == true) {
				$(".condition_checkBoxQuestionRequired").removeAttr('checked');
			}
			submitChange();
		});

		$(".condition_checkBoxQuestionRequired")
				.change(
						function() {
							if ($(".all_checkBoxQuestionRequired").is(
									':checked') == true
									&& $(this).is(':checked') == true) {
								$(".all_checkBoxQuestionRequired").removeAttr(
										'checked');
							}
							submitChange();
						});
	</script>
</body>
</html>