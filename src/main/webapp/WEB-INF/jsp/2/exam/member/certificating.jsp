<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../2/include/_taglib_includes.jsp"%>

<!--证书手动录入-->
<!DOCTYPE html>
<html>
<head lang="en">
<%@include file="../../../2/include/_html_head.jsp"%>
<link href="<%=basePath%>media/2/css/exam_member_certificates.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="<%=basePath%>media/js/bootstrap-filestyle.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>media/js/jquery.form.js"></script>
<script src="<%=basePath%>media/2/js/exam/pageBaseJS.js"></script>
<script src="<%=basePath%>media/2/js/exam/member/certificate.js"></script>
<script src="<%=path%>/media/2/js/driverhrupload.js"></script>
<title>新闻发言人在线学习培训平台-证书录入</title>
</head>
<body>

	<!--header start-->
	<%@include file="../../../2/include/_member_header.jsp"%>
	<!--header end-->

	<div class="content container">
		<div class="row">
			<div class="col-md-3">
				<!-- side menu start -->
				<%@include file="menu.jsp"%>
				<!-- side menu end -->
			</div>
			<div class="col-md-9">
				<div class="block block-two batch_update">
					<div class="block-heading">
						<span>证书信息批量上传</span>
					</div>
					<div class="block-body">
						<div class="col-md-2">
							<a href="<%=basePath%>${excelPath}" download="${examName }证书号录入">模板下载</a>
						</div>
						<form action="<%=basePath%>up/certificateExcel" method="post"
							id="excelCertificateForm" enctype="multipart/form-data">
							<div class="col-md-8">
								<input type="file" name="certificateExcel" id="certificateExcel"
									class="filestyle">
							</div>
						</form>
						<form action="<%=basePath %>exam/member/certificate/${examId }"
							method="post" id="excelCertificateForm">
							<input id="certificateExcelUrl" type="text" style="display:none"
								name="certificateExcelUrl"> <input
								id="certificateExcelLocalPath" type="text" style="display:none"
								name="certificateExcelLocalPath"> <input id="basePath"
								name="basePath" value="<%=basePath%>" style="display:none">
							<div class="col-md-2">
								<input type="submit" value="证书号导入" class="btn btn-primary">
							</div>
						</form>
						<script>
							$(document)
									.ready(
											function() {
												var realFileInputE = $('#certificateExcel');
												var localPathShowE = $('#certificateExcelLocalPath');
												var realFromE = $('#excelCertificateForm');
												var serverPathE = $('#certificateExcelUrl');
												var imgPreviewE = $('#imgPreView');
												commonUpload(realFileInputE,
														localPathShowE,
														realFromE, serverPathE,
														imgPreviewE, path);
											});
						</script>
					</div>
				</div>
				<div class="block block-two">
					<!-- Default panel contents -->
					<div class="block-heading">
						<span>证书录入</span>
					</div>
					<form id="data"
						action="<%=basePath%>exam/member/certificate/${examId}"
						method="get">
						<div style="display: none">
							<input name="searchKey" id="searchKey"
								value="${pageBaseDto.searchKey }"> <input
								name="examName" id="examName" value="${examName }">
						</div>
					</form>
					<form id="submitCertificate" method="post"
						action="<%=basePath%>exam/member/certificate/save/${examId}">
					</form>
					<div class="block-body">
						<div id="pb_top">
							<div class="col-md-6"></div>
							<div class="search_part text-center col-md-6">
								<input type="text" aria-controls="" class="search_input"
									placeholder="驾驶员信息" id="search_text"
									value="${pageBaseDto.searchKey }"> <input type="button"
									class="search_btn" value="搜索" id="search_btn"
									onclick="submit()">
							</div>
						</div>
						<div id="pb_content">
							<table class="table table-bordered table-striped">
								<thead>

									<tr>
										<th class="text-center" style="cursor: pointer;">序号</th>

										<th class="text-center" style="cursor: pointer;">姓名</th>

										<th class="text-center" style="cursor: pointer;">联系方式</th>

										<th class="text-center" style="cursor: pointer;">所属考生办</th>

										<th class="text-center" style="cursor: pointer;">所属考试办</th>

										<th class="text-center" style="cursor: pointer;">证书号</th>

										<th class="text-center" style="cursor: pointer;">证书扫描件</th>

										<th class="text-center" style="cursor: pointer;">操作</th>

									</tr>

								</thead>



								<tbody>

									<c:forEach items="${drivers }" var="driver" varStatus="status">
										<tr>
											<td class="text-center">${status.index+1 }</td>

											<td class="text-center"><a data-toggle="modal"
												data-target="#driverInfo_Modal" href="#">${driver.name }</a></td>

											<td class="text-center">${driver.tel }</td>

											<td class="text-center">${driver.orgName }</td>

											<td class="text-center">${driver.orgParentName }</td>

											<!-- 证书号 -->
											<td class="text-center">
												<!-- 原先没有证书号 --> <c:if test="${empty driver.certificateNo }">
													<input type="text" class="form-control grade"
														id="certificate${driver.driverId }"
														oninput="OnInput (event,'${examId}','${driver.driverId }','<%=basePath%>')"
														onpropertychange="OnPropChanged (event,'${examId}','${driver.driverId }','<%=basePath%>')">
												</c:if> <!-- 原先有证书号 --> <c:if
													test="${!empty driver.certificateNo }">
													<input type="text" class="form-control grade"
														id="certificate${driver.driverId }"
														oninput="OnInput (event,'${examId}','${driver.driverId }','<%=basePath%>')"
														onpropertychange="OnPropChanged (event,'${examId}','${driver.driverId }','<%=basePath%>')"
														value="${driver.certificateNo}">
												</c:if> <!-- 证书照片url隐藏input --> <input type="text"
												style="display:none" id="imgUrl${driver.driverId }">
											</td>

											<!-- 上传照片操作 -->
											<td class="text-center scanner">
												<!-- 原先没有照片 --> <c:if
													test="${empty driver.certificate || driver.certificate=='' }">
													<!-- 上传按钮 -->
													<button class="btn btn-primary" data-toggle="modal"
														data-target="#scanner_Modal"
														onclick="createUploadSubmitButton('${examId }','<%=basePath %>','${driver.driverId}','<%=path%>',event)">上传证书扫描件</button>
													<!-- 隐藏图片显示区域，上传后将显示 -->
													<a data-toggle="modal" data-target="#scanner_Modal"
														title="点击可修改证书照片" style="display:none"
														onclick="createUploadSubmitButtonOnPic('${examId }','<%=basePath %>','${driver.driverId}','<%=path%>')"><img
														width="100px" height="100px"
														id="certificatePhotoPreview${driver.driverId }"
														class="certificates_pic"></a>
												</c:if> <!-- 原先就存在图片 --> <c:if
													test="${!empty driver.certificate && driver.certificate!='' }">
													<a data-toggle="modal" data-target="#scanner_Modal"
														title="点击可修改证书照片"><img width="100px" height="100px"
														src="<%=basePath %>${driver.certificate}"
														id="certificatePhotoPreview${driver.driverId }"
														class="certificates_pic"
														onclick="createUploadSubmitButtonOnPic('${examId }','<%=basePath %>','${driver.driverId}','<%=path%>')"></a>
												</c:if>
											</td>

											<!-- 保存状态显示 -->
											<td class="text-center" id="status${driver.driverId }">
												<!-- 证书信息为空 --> <c:if
													test="${(empty driver.certificateNo|| driver.certificateNo=='')&&(empty driver.certificate || driver.certificate=='') }">
													<label class="label label-default">未保存</label>
												</c:if> <!-- 存在证书信息 --> <c:if
													test="${(!empty driver.certificateNo&& driver.certificateNo!='')||(!empty driver.certificate && driver.certificate!='') }">
													<label class="label label-success">已保存</label>
												</c:if>
											</td>
										</tr>
									</c:forEach>

								</tbody>

							</table>
							<span>总共<label class="num" id="records">${pageBaseDto.sum }</label>条记录
							</span>

							<button class="btn-custom btn-long" onclick="submitCertificate()">提交</button>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>
	<!--message end-->
	<!--scanner modal start-->
	<div class="modal fade" id="scanner_Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="scannerModalLabel">上传证书扫描件</h4>
				</div>
				<div class="modal-body">
					<form action="<%=path%>/up/certificatePhoto"
						id="uploadCertificatePhotoForm" method="post"
						enctype="multipart/form-data">
						<input type="file" name="certificatePhoto"
							class="filestyle file_sm" data-size="sm" id="certificatePhoto">

					</form>
				</div>
				<div class="modal-footer" id="modalFooter">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--scanner modal end-->

	<!--main content end-->
	<!--footer start-->
	<%@include file="../../include/_html_body.jsp"%>
	<!--footer end-->

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
		}
		// Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
		function OnInput(event, examId, driverId, basePath) {
			var status = $(event.target).parent().next().next();
			status.empty();
			var btn = $('<button>保存</button>').appendTo($(status));

			btn.addClass('btn');
			btn.addClass('btn-primary');
			btn.addClass('table_save');
			btn.click(function() {
				certificateOneDriver(examId, driverId, basePath, event);
			});
		}
		// Internet Explorer
		function OnPropChanged(event) {
			var status = $(event.target).parent().next().next();
			status.empty();
			var btn = $('<button>保存</button>').appendTo($(status));

			btn.addClass('btn');
			btn.addClass('btn-primary');
			btn.addClass('table_save');
			btn.click(function() {
				certificateOneDriver(examId, driverId, basePath, event);
			});
		}
	</script>
</body>
</html>