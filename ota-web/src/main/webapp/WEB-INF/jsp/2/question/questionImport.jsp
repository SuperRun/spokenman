<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../include/_taglib_includes.jsp"%>

<!--省散装办试题导入-->
<!DOCTYPE html>
<html>
<head lang="en">
<%@include file="../include/_html_head.jsp"%>

<link href="<%=basePath%>media/2/css/questionImport_style.css"
	type="text/css" rel="stylesheet" />
<link
	href="http://cdn.bootcss.com/bootstrap-fileinput/4.3.5/css/fileinput.min.css"
	rel="stylesheet">
<link href="<%=basePath%>media/2/css/bootstrap-datetimepicker.min.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="<%=basePath%>media/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>media/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="<%=basePath%>media/js/bootstrap-filestyle.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>media/js/jquery.form.js"></script>
<script src="<%=path%>/media/2/js/driverhrupload.js"></script>
<script src="<%=path%>/media/2/js/question/questionImport.js"></script>
<title>新闻发言人在线学习培训平台-试题导入</title>
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
				<div class="block-two block batch_update">
					<div class="block-heading">
						<span>批量导入试题</span>
					</div>
					<div class="block-body">
						<div class="col-md-2">
							<a href="<%=basePath%>${excelPath}" download="题目批量导入">模板下载</a>
						</div>
						<form action="<%=basePath%>up/questionExcel" method="post"
							id="excelQuestionForm" enctype="multipart/form-data">
							<div class="col-md-8">
								<input type="file" name="questionExcel" id="questionExcel"
									class="filestyle">
							</div>
						</form>
						<script>
							$(document)
									.ready(
											function() {
												var realFileInputE = $('#questionExcel');
												var localPathShowE = $('#localPath');
												var realFromE = $('#excelQuestionForm');
												var serverPathE = $('#questionExcelUrl');
												var imgPreviewE = $('#imgPreView');
												commonUpload(realFileInputE,
														localPathShowE,
														realFromE, serverPathE,
														imgPreviewE, path);
											});
						</script>
						<div class="col-md-2">
							<form action="<%=basePath%>question/import/excel" method="post"
								id="questionExcelSubmitForm">

								<input type="submit" value="文件上传" class="btn btn-primary">

								<input type="text" id="questionExcelUrl" name="questionExcelUrl"
									style="display:none"> <input type="text"
									id="imgPreview" name="imgPreview" style="display:none">
								<input type="text" id="localPath" name="localPath"
									style="display:none">

							</form>
						</div>
					</div>
				</div>
				<div class="block block-two">
					<!-- Default panel contents -->
					<div class="block-heading">
						<span>单题导入</span>
					</div>
					<form action="<%=basePath%>question/import" method="post"
						id="questionImport">
						<div style="display:none">
							<input type="text" id="content" name="content"> <input
								type="text" id="pic1" name="pic1"> <input type="text"
								id="pic2" name="pic2"> <input type="text" id="pic3"
								name="pic3"> <input type="text" id="typeId"
								name="typeId"> <input type="text" id="subjectId"
								name="subjectId"> <input type="text" id="marks"
								name="marks"> <input type="text" id="answer"
								name="answer"> <input type="text" id="difficulty"
								name="difficulty"> <input type="text"
								id="questionItemsContentStr" name="questionItemsContentStr">
							<input type="text" id="questionItemsPicStr"
								name="questionItemsPicStr"> <input type="text"
								id="questionItemsType" name="questionItemsType"> <input
								type="text" id="newType" name="newType"> <input
								type="text" id="newSubject" name="newSubject">
						</div>
					</form>
					<div class="block-body">
						<div class="col-md-12">
							<div class="form-horizontal question_form" id="question_form">
								<div class="form-group">
									<div class="col-md-6">
										<label for="questionType" class="text_title">试题类型：</label> <select
											class="form_input" id="questionType">
											<option value="642">单选题</option>
											<option value="669">多选题</option>
											<option value="643">判断题</option>
											<option value="670">填空题</option>
											<option value="671">简答题</option>
										</select>
									</div>
									<div class="col-md-6" style="display: none;">
										<label class="text_title" for="questionText">新建试题类型：</label> <input
											type="text" class="form_input" id="questionText">
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-6">
										<label class="text_title" for="knowledge">试题知识点：</label><select
											class="form_input" id="knowledge">
											<c:forEach items="${questionSubjects }" var="questionSubject">
												<option value="${questionSubject.id }">${questionSubject.name }</option>
											</c:forEach>
											<option value="-1">创建新知识点</option>
										</select>
									</div>
									<div class="col-md-6" style="display: none;">
										<label class="text_title" for="knowledgeText">新建知识点：</label> <input
											type="text" class="form_input" id="knowledgeText">
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-6">
										<label class="text_title" for="score">分值：</label> <input
											type="text" class="form_input form-control"
											disabled="disabled" id="score"
											value="${(fn:length(questionTypes))>0?questionTypes[0].score:""}  ">
									</div>
									<div class="col-md-6">
										<label class="text_title text_radio">题目难度：</label> <label
											class="radio-inline" for="easy"> <input type="radio"
											value="1" name="difficulty" id="easy" checked> 易
										</label> <label class="radio-inline" for="medium"> <input
											type="radio" value="2" name="difficulty" id="medium">
											中
										</label> <label class="radio-inline" for="difficult"> <input
											type="radio" value="3" name="difficulty" id="difficult">
											难
										</label>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">
										<label for="question" class="text_title"
											style="vertical-align: top;">题干：</label>
										<textarea id="question" class="form_textarea"
											placeholder="题干内容"></textarea>
										<label class="error" id="contentError"></label>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12 text-center">
										<input type="button" value="插入图片"
											class="btn-custom btn-middle" id="addPic_btn">
									</div>
								</div>
								<div class="form-group questionPic_part" id="questionPic_part">
									<div class="col-md-12">
										<label class="text_title col-sm-2">插入图片：</label>
										<div class="col-sm-10 name_div">

											<div class="col-sm-4 name_div text-center">
												<img src="" class="question-pic" id="questionPic1Preview">
												<input type="text" name="questionPic1LocalPath"
													id="questionPic1LocalPath" style="display:none">
												<form id="questionPic1UploadForm"
													action="<%=basePath%>up/questionPic" method="post">
													<input id="fileupload1" type="file" name="file"
														style="display: none;">
												</form>
												<button class="btn-custom btn-long"
													onclick="$('#fileupload1').click()">选择图片</button>
												<button class="btn btn-default btn-long clear_btn"
													onclick="clearQuestionPic(event,1)">清空</button>
											</div>
											<script>
												$(document)
														.ready(
																function() {
																	var realFileInputE = $('#fileupload1');
																	var localPathShowE = $('#questionPic1LocalPath');
																	var realFromE = $('#questionPic1UploadForm');
																	var serverPathE = $('#pic1');
																	var imgPreviewE = $('#questionPic1Preview');
																	commonUpload(
																			realFileInputE,
																			localPathShowE,
																			realFromE,
																			serverPathE,
																			imgPreviewE,
																			path);
																});
											</script>


											<div class="col-sm-4 name_div text-center">
												<img src="" class="question-pic" id="questionPic2Preview">
												<input type="text" name="questionPic2LocalPath"
													id="questionPic2LocalPath" style="display:none">
												<form id="questionPic2UploadForm"
													action="<%=basePath%>up/questionPic" method="post">
													<input id="fileupload2" type="file" name="file"
														style="display: none;">
												</form>
												<button class="btn-custom btn-long"
													onclick="$('#fileupload2').click()">选择图片</button>
												<button class="btn btn-default btn-long clear_btn"
													onclick="clearQuestionPic(event,2)">清空</button>
											</div>
											<script>
												$(document)
														.ready(
																function() {
																	var realFileInputE = $('#fileupload2');
																	var localPathShowE = $('#questionPic2LocalPath');
																	var realFromE = $('#questionPic2UploadForm');
																	var serverPathE = $('#pic2');
																	var imgPreviewE = $('#questionPic2Preview');
																	commonUpload(
																			realFileInputE,
																			localPathShowE,
																			realFromE,
																			serverPathE,
																			imgPreviewE,
																			path);
																});
											</script>

											<div class="col-sm-4 name_div text-center">
												<img src="" class="question-pic" id="questionPic3Preview">
												<input type="text" name="questionPic3LocalPath"
													id="questionPic3LocalPath" style="display:none">
												<form id="questionPic3UploadForm"
													action="<%=basePath%>up/questionPic" method="post">
													<input id="fileupload3" type="file" name="file"
														style="display: none;">
												</form>
												<button class="btn-custom btn-long"
													onclick="$('#fileupload3').click()">选择图片</button>
												<button class="btn btn-default btn-long clear_btn"
													onclick="clearQuestionPic(event,3)">清空</button>
											</div>

											<script>
												$(document)
														.ready(
																function() {
																	var realFileInputE = $('#fileupload3');
																	var localPathShowE = $('#questionPic3LocalPath');
																	var realFromE = $('#questionPic3UploadForm');
																	var serverPathE = $('#pic3');
																	var imgPreviewE = $('#questionPic3Preview');
																	commonUpload(
																			realFileInputE,
																			localPathShowE,
																			realFromE,
																			serverPathE,
																			imgPreviewE,
																			path);
																});
											</script>


										</div>
									</div>
								</div>
								<div class="form-group" id="answer-input" style="display: none;">
									<div class="col-md-12">
										<label class="col-sm-2 text_title">答案：</label>
										<div class="col-sm-10 option_div">
											<div class="option_item original_item">
												<div class="col-sm-7">
													<textarea class="form-control" placeholder="正确答案"
														id="correct_answer" name="correct_answer"></textarea>
													<label class="error" id="answerError"></label>
												</div>
												<div class="col-sm-1 file-upload">
													<input type="button" class="btn btn-default new-btn"
														value="+">
												</div>
												<div class="col-md-1 file-upload">
													<input type="button" class="btn btn-primary clear-btn"
														value="清空" onclick="clearAnswer($(this));">
												</div>
												<div class="col-sm-1 name_div"></div>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group" id="option-type">
									<div class="col-md-12">
										<label class="col-sm-2 text_title">选项类型：</label>
										<div class="col-sm-10 name_div">
											<label class="radio-inline"> <input type="radio"
												name="optionType" value="font" id="font" checked>文字
											</label> <label class="radio-inline"> <input type="radio"
												name="optionType" value="picture" id="picture">图片
											</label>
										</div>
									</div>
								</div>
								<div class="form-group font_option" id="font-option">
									<div class="col-md-12">
										<label class="col-sm-2 text_title">选项：</label>
										<div class="col-sm-10 option_div">
											<div class="option_item original_item">
												<div class="col-md-1 radio_div select_type">
													<input type="radio" class="radio" name="fontOption">
												</div>
												<div class="col-sm-8 name_div" style="padding-left: 0;">
													<input type="text" placeholder="选项内容" class="form-control"
														name="question_item_content">
												</div>
												<div class="col-sm-1 name_div">
													<input type="button" class="btn btn-default new-btn"
														value="+">
												</div>
												<div class="col-sm-1 name_div">
													<input type="button" class="btn btn-primary clear-btn"
														value="清空" onclick="clearFont($(this))">
												</div>
												<div class="col-sm-1 name_div"></div>
											</div>
										</div>
									</div>

								</div>
								<div class="form-group pic_option" id="pic-option">
									<div class="col-md-12">
										<label class="col-sm-2 text_title">选项：</label>
										<div class="col-sm-10 option_div">
											<div class="option_item original_item">
												<div class="col-md-1 radio_div file-upload select_type">
													<input type="radio" class="radio" name="picOption">
												</div>
												<div class="col-sm-6 file-upload file_part"
													style="padding-left: 0;">
													<input type="text" class="file_input form-control"
														disabled="disabled">
													<button class="btn btn-primary file_btn"
														onclick="clickPicItemBtn(event)">选择图片</button>
												</div>
												<div class="col-sm-2 name_div">
													<img src="" class="question-pic"> <input type="text"
														class="img_url" name="question_item_pic">
												</div>
												<div class="col-sm-1 file-upload">
													<input type="button" class="btn btn-default new-btn"
														value="+">
												</div>
												<div class="col-sm-1 file-upload">
													<input type="button" class="btn btn-primary" value="清空"
														onclick="clearPic($(this));">
												</div>
												<div class="col-sm-1 file-upload"></div>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group estimate_option" id="estimate-option"
									style="display: none;">
									<div class="col-md-12">
										<label class="col-sm-2 text_title">选项：</label>
										<div class="col-sm-10 option_div">
											<div class="option_item">
												<div class="col-sm-1 radio_div">
													<input type="radio" class="radio" name="estimateOption" value="A">
												</div>
												<div class="col-sm-11" style="padding-left: 0;">
													<input type="text" class="form-control" disabled="disabled"
														value="正确">
												</div>
											</div>
											<div class="option_item">
												<div class="col-sm-1 radio_div">
													<input type="radio" class="radio" name="estimateOption" value="B">
												</div>
												<div class="col-sm-11" style="padding-left: 0;">
													<input type="text" class="form-control" disabled="disabled"
														value="错误">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-primary"
											onclick="getQuestionInfo()">添加题目</button>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
		<!--message end-->

	</div>
	<!--main content end-->

	<!--footer start-->
	<%@include file="../include/_html_body.jsp"%>
	<!--footer end-->

	<form action="<%=basePath%>up/questionItemPic" method="post"
		id="realItemsPicUploadForm">
		<div style="display:none">
			<input id="realItemsPicUpload" name="file" type="file"
				onchange="questionItemPicChange()">
		</div>
	</form>


	<script>
		window.onload = function() {
			var height = $("#attach_part").height() + 20 > $(
					".content .row .col-md-9").height() ? $("#attach_part")
					.height() + 20 : $(".content .row .col-md-9").height();
			height = height > document.body.clientHeight - 228 ? height
					: document.body.clientHeight - 228;
			$(".content .row .col-md-3").css("height", height);

			//        判断选项填写的控件是否出现
			$("#questionType")
					.change(
							function() {

								var answer_input = $("#answer-input"), option_type = $("#option-type"), font_option = $("#font-option"), pic_option = $("#pic-option"), estimate_option = $("#estimate-option");

								if ($(this).val() == 642) {
									//          单选题
									/*
									answer_input 隐藏
									option_type 出现
									font_option、pic_option 择一出现设置成单选按钮（radio）
									 estimate_option 隐藏
									 */
									$(answer_input).hide();
									$(option_type).show();
									$(estimate_option).hide();

									if ($("#option-type :radio:checked").val() == 'font') {
										//文字
										$("#font-option")
												.html(
														'<div class="col-md-12"> <label class="col-sm-2 text_title">选项：</label> <div class="col-sm-10 option_div"> <div class="option_item original_item"> <div class="col-md-1 radio_div select_type"> <input type="radio" class="radio" name="fontOption"> </div> <div class="col-sm-8 name_div" style="padding-left: 0;"> <input type="text" placeholder="选项内容" class="form-control" name="question_item_content"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-primary clear-btn" value="清空"onclick="clearFont($(this))"> </div> <div class="col-sm-1 name_div"> </div> </div> </div> </div>');
										$("#font-option").show();
										$("#pic-option").hide();
										new_fontOption();

									} else if ($("#option-type :radio:checked")
											.val() == 'picture') {
										//图片
										$("#pic-option")
												.html(
														'<div class="col-md-12"> <label class="col-sm-2 text_title">选项：</label> <div class="col-sm-10 option_div"> <div class="option_item original_item"> <div class="col-md-1 radio_div file-upload select_type"> <input type="radio" class="radio" name="picOption"> </div> <div class="col-sm-6 file-upload file_part" style="padding-left: 0;"> <input type="text" class="file_input form-control" disabled="disabled"> <button class="btn btn-primary file_btn" onclick="clickPicItemBtn(event)">选择图片</button> </div> <div class="col-sm-2 name_div"> <img src="" class="question-pic"> <input type="text" class="img_url" name="question_item_pic"> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-primary" value="清空"onclick="clearPic($(this));"> </div> <div class="col-sm-1 file-upload"> </div> </div> </div>');
										$(".pic_option .filestyle").filestyle();
										$("#font-option").hide();
										$("#pic-option").show();

										new_picOption();

									}

									$("#option-type :radio")
											.click(
													function() {
														//                判断选项内容为文字还是图片
														if ($(
																"#option-type :radio:checked")
																.val() == 'font') {
															//文字
															$("#font-option")
																	.html(
																			'<div class="col-md-12"> <label class="col-sm-2 text_title">选项：</label> <div class="col-sm-10 option_div"> <div class="option_item original_item"> <div class="col-md-1 radio_div select_type"> <input type="radio" class="radio" name="fontOption"> </div> <div class="col-sm-8 name_div" style="padding-left: 0;"> <input type="text" placeholder="选项内容" class="form-control" name="question_item_content"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-primary clear-btn" value="清空"onclick="clearFont($(this))"> </div> <div class="col-sm-1 name_div"> </div> </div> </div> </div>');
															$("#font-option")
																	.show();
															$("#pic-option")
																	.hide();
															new_fontOption();

														} else if ($(
																"#option-type :radio:checked")
																.val() == 'picture') {
															//图片
															$("#pic-option")
																	.html(
																			'<div class="col-md-12"> <label class="col-sm-2 text_title">选项：</label> <div class="col-sm-10 option_div"> <div class="option_item original_item"> <div class="col-md-1 radio_div file-upload select_type"> <input type="radio" class="radio" name="picOption"> </div> <div class="col-sm-6 file-upload file_part" style="padding-left: 0;"> <input type="text" class="file_input form-control" disabled="disabled"> <button class="btn btn-primary file_btn" onclick="clickPicItemBtn(event)">选择图片</button> </div> <div class="col-sm-2 name_div"> <img src="" class="question-pic"> <input type="text" class="img_url" name="question_item_pic"> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-primary" value="清空"onclick="clearPic($(this));"> </div> <div class="col-sm-1 file-upload"> </div> </div> </div>');
															$(
																	".pic_option .filestyle")
																	.filestyle();
															$("#font-option")
																	.hide();
															$("#pic-option")
																	.show();

															new_picOption();

														}
													});

								} else if ($(this).val() == 669) {
									//                多选题
									/*
									 answer_input 隐藏
									 option_type 出现
									 font_option、pic_option 择一出现设置成多选按钮（checkbox）
									 estimate_option 隐藏
									 */
									$(answer_input).hide();
									$(option_type).show();
									$(estimate_option).hide();

									if ($("#option-type :radio:checked").val() == 'font') {
										//文字
										$("#font-option")
												.html(
														'<div class="col-md-12"> <label class="col-sm-2 text_title">选项：</label> <div class="col-sm-10 option_div"> <div class="option_item original_item"> <div class="col-md-1 checkbox_div select_type"> <input type="checkbox" class="checkbox" name="fontOption"> </div> <div class="col-sm-8 name_div" style="padding-left: 0;"> <input type="text" placeholder="选项内容" class="form-control" name="question_item_content"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-primary clear-btn" value="清空"onclick="clearFont($(this))"> </div> <div class="col-sm-1 name_div"> </div> </div> </div> </div>');
										$("#font-option").show();
										$("#pic-option").hide();
										new_fontOption();

									} else if ($("#option-type :radio:checked")
											.val() == 'picture') {
										//图片
										$("#pic-option")
												.html(
														'<div class="col-md-12"> <label class="col-sm-2 text_title">选项：</label> <div class="col-sm-10 option_div"> <div class="option_item original_item"> <div class="col-md-1 check_div file-upload select_type"> <input type="checkbox" class="checkbox" name="picOption"> </div> <div class="col-sm-6 file-upload file_part" style="padding-left: 0;"> <input type="text" class="file_input form-control" disabled="disabled"> <button class="btn btn-primary file_btn" onclick="clickPicItemBtn(event)">选择图片</button> </div> <div class="col-sm-2 name_div"> <img src="" class="question-pic"> <input type="text" class="img_url" name="question_item_pic"> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-primary" value="清空"onclick="clearPic($(this));"> </div> <div class="col-sm-1 file-upload"> </div> </div> </div>');
										$(".pic_option .filestyle").filestyle();
										$("#font-option").hide();
										$("#pic-option").show();

										new_picOption();

									}

									$("#option-type :radio")
											.click(
													function() {
														//                判断选项内容为文字还是图片
														if ($(
																"#option-type :radio:checked")
																.val() == 'font') {
															//文字
															$("#font-option")
																	.html(
																			'<div class="col-md-12"> <label class="col-sm-2 text_title">选项：</label> <div class="col-sm-10 option_div"> <div class="option_item original_item"> <div class="col-md-1 checkbox_div select_type"> <input type="checkbox" class="checkbox" name="fontOption"> </div> <div class="col-sm-8 name_div" style="padding-left: 0;"> <input type="text" placeholder="选项内容" class="form-control" name="question_item_content"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-primary clear-btn" value="清空"onclick="clearFont($(this))"> </div> <div class="col-sm-1 name_div"> </div> </div> </div> </div>');
															$("#font-option")
																	.show();
															$("#pic-option")
																	.hide();
															new_fontOption();

														} else if ($(
																"#option-type :radio:checked")
																.val() == 'picture') {
															//图片
															$("#pic-option")
																	.html(
																			'<div class="col-md-12"> <label class="col-sm-2 text_title">选项：</label> <div class="col-sm-10 option_div"> <div class="option_item original_item"> <div class="col-md-1 check_div file-upload select_type"> <input type="checkbox" class="checkbox" name="picOption"> </div> <div class="col-sm-6 file-upload file_part" style="padding-left: 0;"> <input type="text" class="file_input form-control" disabled="disabled"> <button class="btn btn-primary file_btn" onclick="clickPicItemBtn(event)">选择图片</button> </div> <div class="col-sm-2 name_div"> <img src="" class="question-pic"> <input type="text" class="img_url" name="question_item_pic"> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-primary" value="清空"onclick="clearPic($(this));"> </div> <div class="col-sm-1 file-upload"> </div> </div> </div>');
															$(
																	".pic_option .filestyle")
																	.filestyle();
															$("#font-option")
																	.hide();
															$("#pic-option")
																	.show();

															new_picOption();

														}
													});

								} else if ($(this).val() == 643) {
									//                判断题
									/*
									 answer_input 隐藏
									 option_type 出现
									 font_option、pic_option 隐藏
									 estimate_option 出现
									 */
									$(answer_input).hide();
									$(option_type).hide();
									$(font_option).hide();
									$(pic_option).hide();
									$(estimate_option)
											.html(
													'<div class="col-md-12"> <label class="col-sm-2 text_title">选项：</label> <div class="col-sm-10 option_div"> <div class="option_item"> <div class="col-sm-1 radio_div"> <input type="radio" class="radio" name="estimateOption" value="A"> </div> <div class="col-sm-11" style="padding-left: 0;"> <input type="text" class="form-control" disabled="disabled" value="正确"> </div> </div> <div class="option_item"> <div class="col-sm-1 radio_div"> <input type="radio" class="radio" name="estimateOption" value="B"> </div> <div class="col-sm-11" style="padding-left: 0;"> <input type="text" class="form-control" disabled="disabled" value="错误"> </div> </div> </div> </div>');
									$(estimate_option).show();
								} else {
									//                简答题或者填空题
									$(answer_input)
											.html(
													'<div class="col-md-12"> <label class="col-sm-2 text_title">答案：</label> <div class="col-sm-10 option_div"> <div class="option_item original_item"> <div class="col-sm-7"> <textarea class="form-control" placeholder="正确答案" id="correct_answer"  name="correct_answer"></textarea> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-md-1 file-upload"> <input type="button" class="btn btn-primary clear-btn" value="清空"onclick="clearAnswer($(this));"> </div> <div class="col-sm-1 name_div"> </div> </div> </div> </div>');
									$(option_type).hide();
									$(font_option).hide();
									$(pic_option).hide();
									$(estimate_option).hide();
									$(answer_input).show();
									new_answerOption();
								}

							});

			$("#knowledge").click(function() {
				if ($(this).val() == -1) {
					$("#knowledgeText").parent().show();
				} else {
					$("#knowledgeText").parent().hide();
				}
			});

			$("#option-type :radio")
					.click(
							function() {
								//                判断选项内容为文字还是图片
								if ($("#option-type :radio:checked").val() == 'font') {
									//文字
									$("#font-option")
											.html(
													'<div class="col-md-12"> <label class="col-sm-2 text_title">选项：</label> <div class="col-sm-10 option_div"> <div class="option_item original_item"> <div class="col-md-1 radio_div select_type"> <input type="radio" class="radio" name="fontOption"> </div> <div class="col-sm-8 name_div" style="padding-left: 0;"> <input type="text" placeholder="选项内容" class="form-control" name="question_item_content"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-primary clear-btn" value="清空"onclick="clearFont($(this))"> </div> <div class="col-sm-1 name_div"> </div> </div> </div> </div>');
									$("#font-option").show();
									$("#pic-option").hide();
									new_fontOption();

								} else if ($("#option-type :radio:checked")
										.val() == 'picture') {
									//图片
									$("#pic-option")
											.html(
													'<div class="col-md-12"> <label class="col-sm-2 text_title">选项：</label> <div class="col-sm-10 option_div"> <div class="option_item original_item"> <div class="col-md-1 radio_div file-upload select_type"> <input type="radio" class="radio" name="picOption"> </div> <div class="col-sm-6 file-upload file_part" style="padding-left: 0;"> <input type="text" class="file_input form-control" disabled="disabled"> <button class="btn btn-primary file_btn" onclick="clickPicItemBtn(event)">选择图片</button> </div> <div class="col-sm-2 name_div"> <img src="" class="question-pic"> <input type="text" class="img_url" name="question_item_pic"> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-primary" value="清空"onclick="clearPic($(this));"> </div> <div class="col-sm-1 file-upload"> </div> </div> </div>');
									$(".pic_option .filestyle").filestyle();
									$("#font-option").hide();
									$("#pic-option").show();

									new_picOption();

								}
							});

			new_fontOption();
			new_picOption();
			new_answerOption();
			deleteOption();

		};

		function deleteOption() {
			$(".delete-btn").on('click', function() {
				var option_item = $(this).parent().parent();
				var optionType = option_item.parent().parent().parent();
				var newBtn;
				if ($(optionType).attr('id') == 'pic-option') {
					newBtn = option_item.prev().children().eq(3).children();
				} else if ($(optionType).attr('id') == 'font-option') {
					newBtn = option_item.prev().children().eq(2).children();
				} else if ($(optionType).attr('id') == 'answer-input') {
					newBtn = option_item.prev().children().eq(1).children();
				}
				if (option_item.next().length == 0) {
					$(newBtn).show();
				}
				option_item.remove();

			});
		}

		function new_answerOption() {
			$("#answer-input .new-btn")
					.on(
							'click',
							function() {
								$(".new-btn").unbind();
								$(this).hide();
								var option_div = $(this).parent().parent()
										.parent();
								var answer_option = '<div class="option_item append_item"> <div class="col-sm-7"> <textarea class="form-control" placeholder="正确答案" id="correct_answer"  name="correct_answer"></textarea> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-md-1 file-upload"> <input type="button" class="btn btn-primary clear-btn" value="清空"onclick="clearAnswer($(this));"> </div> <div class="col-sm-1 file-upload"><input type="button" class="btn btn-default delete-btn" value="删除" style="margin-left: 10px"> </div> </div>';
								$(option_div).append(answer_option);

								new_answerOption();
								deleteOption();

							});
		}

		function new_fontOption() {
			$("#font-option .new-btn")
					.on(
							'click',
							function() {
								$(".new-btn").unbind();
								$(this).hide();
								var option_div = $(this).parent().parent()
										.parent();
								var font_option;
								if ($("#questionType").val() == 642) {
									font_option = '<div class="option_item append_item"> <div class="col-md-1 radio_div"> <input type="radio" class="radio" name="fontOption"> </div> <div class="col-sm-8 name_div" style="padding-left: 0;"> <input type="text" placeholder="选项内容" class="form-control" name="question_item_content"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-primary" value="清空" onclick="clearFont($(this))"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-default delete-btn" value="删除" style="margin-left: 10px"> </div> </div>';
								} else if ($("#questionType").val() == 669) {
									font_option = '<div class="option_item append_item"> <div class="col-md-1 checkbox_div"> <input type="checkbox" class="checkbox" name="fontOption"> </div> <div class="col-sm-8 name_div" style="padding-left: 0;"> <input type="text" placeholder="选项内容" class="form-control" name="question_item_content"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-primary" value="清空" onclick="clearFont($(this))"> </div> <div class="col-sm-1 name_div"> <input type="button" class="btn btn-default delete-btn" value="删除" style="margin-left: 10px"> </div> </div>';
								}

								$(option_div).append(font_option);
								new_fontOption();
								deleteOption();

							});
		}

		function new_picOption() {
			$("#pic-option .new-btn")
					.on(
							'click',
							function() {
								$(".new-btn").unbind();
								$(this).hide();

								var option_div = $(this).parent().parent()
										.parent();
								var pic_option;
								if ($("#questionType").val() == 642) {
									pic_option = '<div class="option_item append_item"> <div class="col-md-1 radio_div file-upload"> <input type="radio" class="radio" name="picOption"> </div> <div class="col-sm-6 file-upload file_part" style="padding-left: 0;"> <input type="text" class="file_input form-control" disabled="disabled"> <button class="btn btn-primary file_btn" onclick="clickPicItemBtn(event)">选择图片</button> </div> <div class="col-sm-2 name_div"> <img src="" class="question-pic"> <input type="text" class="img_url" name="question_item_pic"></div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-primary " value="清空" onclick="clearPic($(this));"> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-default delete-btn" value="删除" style="margin-left: 10px"> </div> </div>';

								} else if ($("#questionType").val() == 669) {
									pic_option = '<div class="option_item append_item"> <div class="col-md-1 checkbox_div file-upload"> <input type="checkbox" class="checkbox" name="picOption"> </div> <div class="col-sm-6 file-upload file_part" style="padding-left: 0;"> <input type="text" class="file_input form-control" disabled="disabled"> <button class="btn btn-primary file_btn" onclick="clickPicItemBtn(event)">选择图片</button> </div> <div class="col-sm-2 name_div"> <img src="" class="question-pic"> <input type="text" class="img_url" name="question_item_pic"></div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-default new-btn" value="+"> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-primary " value="清空" onclick="clearPic($(this));"> </div> <div class="col-sm-1 file-upload"> <input type="button" class="btn btn-default delete-btn" value="删除" style="margin-left: 10px"> </div> </div>';

								}

								$(option_div).append(pic_option);

								$(option_div).children().children().children(
										'.filestyle').parent().children()
										.last().filestyle();

								new_picOption();
								deleteOption()

							});
		}

		function clearPic(clear_btn) {
			var parent_div = clear_btn.parent().prev().prev().prev().children();
			var imgPart = clear_btn.parent().prev().prev().children();
			imgPart.attr('src', "");
			$(parent_div).children().eq(0).val("");

			var localPath = clear_btn.parent().prev().prev().prev().find(
					"input");
			localPath.val("");
		}
		function clearAnswer(clear_btn) {

			var answer_textarea = clear_btn.parent().prev().prev().children();
			$(answer_textarea).val("");
		}

		function clearFont(clear_btn) {
			var font = clear_btn.parent().prev().prev().children();
			$(font).val("");
		}

		function appointRadio() {
			$(":radio").click(function() {
				return $(this).val();
			});
		}

		$("#addPic_btn").on('click', function() {
			if ($("#questionPic_part").css('display') == 'none') {
				$("#questionPic_part").show(300);
				$(this).val('隐藏图片');
			} else {
				$("#questionPic_part").hide(300);
				$(this).val('插入图片');
			}
		});
		$(".clear_btn").on('click', function() {
			$(this).prev().prev().prev().attr('src', "");
		});
	</script>
</body>
</html>