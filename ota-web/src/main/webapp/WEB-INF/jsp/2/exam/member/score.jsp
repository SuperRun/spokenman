<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../2/include/_taglib_includes.jsp"%>

<!--成绩手动录入-->
<!DOCTYPE html>
<html>
<head lang="en">
<%@include file="../../../2/include/_html_head.jsp"%>
<link href="<%=basePath %>media/2/css/exam_member_recording.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="<%=basePath %>media/js/bootstrap-filestyle.min.js"></script>
<script type="text/javascript"
	src="<%=basePath %>media/js/jquery.form.js"></script>
<script src="<%=basePath%>media/2/js/exam/pageBaseJS.js"></script>
<script src="<%=basePath%>media/2/js/exam/member/score.js"></script>
<script src="<%=path%>/media/2/js/driverhrupload.js"></script>
<title>新闻发言人在线学习培训平台-成绩录入</title>
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
				<div class="block block-two batch_update">
					<div class="block-heading">
						<span>成绩批量录入</span>
					</div>
					<div class="block-body">
						<div class="col-md-2">
							<a href="<%=basePath%>${excelPath}" download="${examName }成绩录入">模板下载</a>
						</div>
						<form action="<%=basePath%>up/scoreExcel" method="post"
							id="excelScoreForm" enctype="multipart/form-data">
							<div class="col-md-8">
								<input type="file" name="scoreExcel" id="scoreExcel"
									class="filestyle">
							</div>
						</form>
						<form action="<%=basePath %>exam/member/score/${examId }"
							method="post" id="excelScoreSubmitForm">
							<input id="scoreExcelUrl" type="text" style="display:none"
								name="scoreExcelUrl"> <input id="scoreExcelLocalPath"
								type="text" style="display:none" name="scoreExcelLocalPath">
							<input id="basePath" name="basePath" value="<%=basePath%>"
								style="display:none">
							<div class="col-md-2">
								<!-- <input  value="成绩批量导入" class="btn btn-primary" type="submit"> -->
								<button value="成绩批量导入" class="btn btn-primary"
									onclick="scoreExcelSubmit()">成绩批量导入</button>
							</div>
						</form>
						<script>
							$(document)
									.ready(
											function() {
												var realFileInputE = $('#scoreExcel');
												var localPathShowE = $('#scoreExcelLocalPath');
												var realFromE = $('#excelScoreForm');
												var serverPathE = $('#scoreExcelUrl');
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
						<span>成绩录入</span>
					</div>
					<form id="data" action="<%=basePath%>exam/member/score/${examId}"
						method="get">
						<div style="display: none">
							<input name="searchKey" id="searchKey"
								value="${pageBaseDto.searchKey }"> <input
								name="examName" id="examName" value="${examName }">
						</div>
					</form>
					<form id="submitScore"
						action="<%=basePath%>exam/member/score/save/${examId}"
						method="post"></form>
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

										<th class="text-center" style="cursor: pointer;">成绩</th>

										<th class="text-center" style="cursor: pointer">操作</th>

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

											<td class="text-center"><c:if
													test="${empty driver.score || driver.score==0.0 }">
													<input type="text" class="form-control grade"
														id="score${driver.driverId }"
														oninput="OnInput (event,'${driver.driverId}','${examId}','<%=basePath %>')"
														onpropertychange="OnPropChanged (event,'${driver.driverId}','${examId}','<%=basePath %>')">
												</c:if> <c:if test="${!empty driver.score && driver.score!=0.0 }">
													<input type="text" class="form-control grade"
														value="${driver.score }" id="score${driver.driverId }"
														oninput="OnInput (event,'${driver.driverId}','${examId}','<%=basePath %>')"
														onpropertychange="OnPropChanged (event,'${driver.driverId}','${examId}','<%=basePath %>')">
												</c:if> 分</td>

											<td class="text-center"><c:if
													test="${!empty driver.score && driver.score!=0.0 }">
													<label class="label label-success">已保存</label>
												</c:if> <c:if test="${empty driver.score || driver.score==0.0 }">
													<label class="label label-default">未保存</label>
												</c:if></td>
										</tr>
									</c:forEach>

								</tbody>

							</table>
							<span>总共<label class="num" id="records">${pageBaseDto.sum }</label>条记录
							</span>
							<button class="btn-custom btn-long submit_btn"
								onclick="submitScore()">提交</button>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!--message end-->
	<!--examInfo modal start-->
	<div class="modal fade" id="driverInfo_Modal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="driverInfoModalLabel">驾驶员信息</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-4 text_title">驾驶员名称：</label> <label
								class="col-sm-8 text_message">张三</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">驾驶员性别：</label> <label
								class="col-sm-8 text_message">男</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">驾驶员年龄：</label> <label
								class="col-sm-8 text_message">35</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">驾驶员联系方式：</label> <label
								class="col-sm-8 text_message">17829716578</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">家庭住址：</label> <label
								class="col-sm-8 text_message">杭州市西湖区xxxxxxxxxxxxxxxxxxxxxx</label>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--examInfo modal end-->
	<!--cancel modal start-->
	<div class="modal fade" id="cancel_Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="cancelModalLabel">取消报名</h4>
				</div>
				<div class="modal-body">是否确定取消该驾驶员本次考试报名资格？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary">确定</button>
				</div>
			</div>
		</div>
	</div>
	<!--cancel modal end-->

	<!--cancel modal start-->
	<div class="modal fade" id="detail_Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="detailModalLabel">证书详情</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-4 text_title">证书编号：</label> <label
								class="col-sm-8 text_message">417041641587649</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">证书有效开始日期：</label> <label
								class="col-sm-8 text_message">2016/08/20</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">有效期：</label> <label
								class="col-sm-8 text_message">1年</label>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--cancel modal end-->

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
		function OnInput(event, driverId, examId, basePath) {
			var status = $(event.target).parent().next();
			status.empty();
			var btn = $('<button>保存</button>').appendTo($(status));
			//var buttonStr='<button onclick="scoreOneDriver(\''+examId+'\',\''+driverId+'\',\''+basePath+'\')">保存</button>';

			btn.addClass('btn');
			btn.addClass('btn-primary');
			btn.addClass('table_save');
			btn.click(function() {
				scoreOneDriver(examId, driverId, basePath, event);
			});
		}
		// Internet Explorer
		function OnPropChanged(event) {
			var status = $(event.target).parent().next();
			status.empty();
			var btn = $('<button>保存</button>').appendTo($(status));
			//var buttonStr='<button onclick="scoreOneDriver(\''+examId+'\',\''+driverId+'\',\''+basePath+'\')">保存</button>';

			btn.addClass('btn');
			btn.addClass('btn-primary');
			btn.addClass('table_save');
			btn.click(function() {
				scoreOneDriver(examId, driverId, basePath, event);
			});
		}
	</script>
</body>
</html>