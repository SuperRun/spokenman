<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../2/include/_taglib_includes.jsp"%>

<!--车企对某次考试报名人员进行报名管理-->
<!DOCTYPE html>
<html>
<head lang="en">
<%@include file="../../../2/include/_html_head.jsp"%>
<link
	href="<%=basePath%>media/2/css/exam_member_registrationManagement.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="<%=basePath%>media/2/js/exam/pageBaseJS.js"></script>
<script type="text/javascript"
	src="<%=basePath%>media/2/js/exam/company/signup.js"></script>
<title>新闻发言人在线学习培训平台-报名管理</title>
</head>
<body>

	<!--header start-->
	<%@include file="../../include/_member_header.jsp"%>
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
						<span>未报名考生</span> <input id="examId" style="display:none"
							value="${examId }">
					</div>
					<form id="data"
						action="<%=basePath%>exam/company/${examId}/drivers/signup"
						method="get">
						<div style="display: none">
							<input name="page" id="page" value="${pageBaseDto.page }">
							<input name="rows" id="rows" value="${pageBaseDto.rows }">
							<input name="pageNum" id="pageNum"
								value="${pageBaseDto.pageNum }"> <input name="searchKey"
								id="searchKey" value="${pageBaseDto.searchKey }"> <input
								name="status" id="status" value="${pageBaseDto.status }">

							<input name="pageForSignup" id="pageForSignup"
								value="${pageBaseDtoForSignup.pageForSignup }"> <input
								name="rowsForSignup" id="rowsForSignup"
								value="${pageBaseDtoForSignup.rowsForSignup }"> <input
								name="pageNumForSignup" id="pageNumForSignup"
								value="${pageBaseDtoForSignup.pageNumForSignup }"> <input
								name="searchKeyForSignup" id="searchKeyForSignup"
								value="${pageBaseDtoForSignup.searchKeyForSignup }"> <input
								id="signupStatus" name="signupStatus" value="${signupStatus }">
						</div>
					</form>
					<div class="block-body">
						<div id="pb_top">
							<div class="col-md-6">
								<label id="record_num"> <select size="1"
									name="sample_1_length" aria-controls="sample_1" class=""
									id="rows_inputForSignup" onchange="submitForSignup()">
										<c:if test="${pageBaseDtoForSignup.rowsForSignup==1 }">
											<option value="1" selected="selected">1</option>
										</c:if>
										<c:if test="${pageBaseDtoForSignup.rowsForSignup!=1 }">
											<option value="1">1</option>
										</c:if>
										<c:if test="${pageBaseDtoForSignup.rowsForSignup==5 }">
											<option value="5" selected="selected">5</option>
										</c:if>
										<c:if test="${pageBaseDtoForSignup.rowsForSignup!=5 }">
											<option value="5">5</option>
										</c:if>
										<c:if test="${pageBaseDtoForSignup.rowsForSignup==15 }">
											<option value="15" selected="selected">15</option>
										</c:if>
										<c:if test="${pageBaseDtoForSignup.rowsForSignup!=15 }">
											<option value="15">15</option>
										</c:if>
										<c:if test="${pageBaseDtoForSignup.rowsForSignup==20 }">
											<option value="20" selected="selected">20</option>
										</c:if>
										<c:if test="${pageBaseDtoForSignup.rowsForSignup!=20 }">
											<option value="20">20</option>
										</c:if>
								</select> 记录/页
								</label>
							</div>
							<div class="search_part text-center col-md-6">
								<label id="search"><input type="text" aria-controls=""
									class="form-control" placeholder="考生姓名/身份证号/联系方式"
									id="search_textForSignup"
									value="${pageBaseDtoForSignup.searchKeyForSignup }"> </label> <input
									type="button" class="search_btn" value="搜索" id="search_btn"
									onclick="submitForSignup()">
							</div>
						</div>
						<!-- <div class="operation_part">
							<span>筛选条件：</span> <input type="checkbox"
								class="checkbox-inline condition_checkBox" value="2"
								name="signupStatusCheckbox">初次培训驾驶员 <input
								type="checkbox" class="checkbox-inline condition_checkBox"
								value="3" name="signupStatusCheckbox">重新培训驾驶员 <input
								type="checkbox" class="checkbox-inline condition_checkBox"
								value="1" name="signupStatusCheckbox">其他驾驶员 <input
								type="checkbox" class="checkbox-inline all_checkBox" value="-1"
								name="signupStatusCheckbox">全部
						</div> -->
						<div class="panel-body">
							<div class="car_company">
								<span></span><input type="checkbox" class="select_all">全选
							</div>
							<table class="table table-bordered table-striped">

								<thead>
									<tr>
										<th class="text-center">操作</th>
										<th class="text-center">考生姓名</th>
										<th class="text-center">联系方式</th>
										<th class="text-center">身份证号</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${driversForSignup }" var="driver">
										<tr>
											<td class="text-center"><input type="checkbox"
												class="select_user" value="${driver.driverId }"
												name="signupDriver"></td>

											<td class="text-center"><a data-toggle="modal"
												data-target="#driverInfo_Modal" href="#"
												onclick="getDriverDetailInfo('${driver.driverId}','<%=basePath%>')">${driver.name }</a></td>

											<td class="text-center">${driver.tel }</td>

											<td class="text-center">${driver.sfzNo }</td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
							<span>总共<label class="num" id="records">${pageBaseDtoForSignup.sumForSignup
									}</label>条记录
								| 第<label class="num" id="page_num">${pageBaseDtoForSignup.pageForSignup
									}</label>页/共<label
								class="num" id="pages">${pageBaseDtoForSignup.pageNumForSignup
									}</label>页
							</span>
							<nav id="paging_nav">
								<ul class="pagination">
									<li><a href="#" aria-label="first"
										class="glyphicon glyphicon-step-backward" alt="第一页"
										title="第一页" onclick="changePageForSignup(1)"></a></li>
									<!-- 第一页不能上一页 -->
									<c:if test="${pageBaseDtoForSignup.pageForSignup==1 }">
										<li><a href="#" aria-label="previous"
											class="glyphicon glyphicon-chevron-left" alt="上一页"
											title="上一页"></a></li>
									</c:if>
									<c:if test="${pageBaseDtoForSignup.pageForSignup!=1 }">
										<li><a href="#" aria-label="previous"
											class="glyphicon glyphicon-chevron-left" alt="上一页"
											title="上一页"
											onclick="changePageForSignup('${pageBaseDtoForSignup.pageForSignup-1}')"></a></li>
									</c:if>
									<!-- 最后一页没有下一页 -->
									<c:if
										test="${pageBaseDtoForSignup.pageForSignup==pageBaseDtoForSignup.pageNumForSignup }">
										<li><a href="#" aria-label="next"
											class="glyphicon glyphicon-chevron-right" alt="下一页"
											title="下一页"></a></li>
									</c:if>
									<c:if
										test="${pageBaseDtoForSignup.pageForSignup!=pageBaseDtoForSignup.pageNumForSignup }">
										<li><a href="#" aria-label="next"
											class="glyphicon glyphicon-chevron-right" alt="下一页"
											title="下一页"
											onclick="changePageForSignup('${pageBaseDtoForSignup.pageForSignup+1}')"></a></li>
									</c:if>
									<li><a href="#" aria-label="last"
										class="glyphicon glyphicon-step-forward" alt="最后一页"
										title="最后一页"
										onclick="changePageForSignup('${pageBaseDtoForSignup.pageNumForSignup}')"></a></li>
									<li><input type="text" aria-controls=""
										class="input-custom" placeholder="页码" id="page_textForSignup">
										<input type="button" class="btn-custom btn-middle" value="跳转"
										id="goto_btn" onclick="changePageForSignup(-1)"></li>

								</ul>
							</nav>
						</div>
						<input id="signupDriversString" style="display:none">
						<button class="btn-custom btn-long submit_btn"
							onclick="getSignupDrivers('<%=basePath%>','${examId }')">提交</button>
					</div>
				</div>
				<div class="block block-two">
					<!-- Default panel contents -->
					<div class="block-heading">
						<span>已报名考生</span>
					</div>
					<div class="block-body">
						<div id="pb_top">
							<div class="col-md-6">
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
							<div class="search_part text-center col-md-6">
								<label id="search"><input type="text" aria-controls=""
									class="form-control" placeholder="考生姓名" id="search_text"
									value="${pageBaseDto.searchKey }"> </label> <input
									type="button" class="search_btn" value="搜索" id="search_btn"
									onclick="submit()">
							</div>
						</div>
						<div id="pb_content">
							<table class="table table-bordered table-striped">
								<thead>

									<tr>
										<th class="text-center" style="cursor: pointer;">序号</th>

										<th class="text-center" style="cursor: pointer;">考生姓名</th>

										<th class="text-center" style="cursor: pointer;">联系方式</th>

										<th class="text-center" style="cursor: pointer;">所属考生办</th>

										<th class="text-center" style="cursor: pointer;">所属考试办</th>

										<th class="text-center" style="cursor: pointer;">操作</th>
									</tr>

								</thead>



								<tbody>

									<c:forEach items="${drivers }" var="driver">
										<tr>
											<td class="text-center">${driver.driverId }</td>

											<td class="text-center"><a data-toggle="modal"
												data-target="#driverInfo_Modal" href="#"
												onclick="getDriverDetailInfo('${driver.driverId}','<%=basePath%>')">${driver.name }</a>
											</td>

											<td class="text-center">${driver.tel }</td>

											<td class="text-center">${driver.orgName }</td>

											<td class="text-center">${driver.orgParentName }</td>

											<td class="text-center"><button class="btn btn-primary"
													data-toggle="modal" data-target="#cancel_Modal"
													onclick="deliverDriverId('${driver.driverId}')">取消考试资格</button>
											</td>

										</tr>
									</c:forEach>

								</tbody>

							</table>
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
	</div>
	<!--message end-->
	<!--driverInfo modal start-->
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
								class="col-sm-8 text_message" id="driverDetailName"></label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">驾驶员性别：</label> <label
								class="col-sm-8 text_message" id="driverDetailSex">男</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">驾驶员年龄：</label> <label
								class="col-sm-8 text_message" id="driverDetailAge"></label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">驾驶员联系方式：</label> <label
								class="col-sm-8 text_message" id="driverDetailTel"></label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">家庭住址：</label> <label
								class="col-sm-8 text_message" id="driverDetailAddress"></label>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--driverInfo modal end-->

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
				<div style="display:none">
					<input id="cancelDriverId" name="cancelDriverId">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" id="cancelSignupHide">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="cancelSignup('${examId}','<%=basePath %>')">确定</button>
				</div>
			</div>
		</div>
	</div>
	<!--cancel modal end-->

	<button class="btn btn-primary"data-toggle="modal" data-target="#cancel_error_Modal" id="showCancelError" style="display:none">
	</button>

	<!--cancel error modal start-->
	<div class="modal fade" id="cancel_error_Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="cancelModalLabel">取消报名失败</h4>
				</div>
				<div class="modal-body">已过报名结束时间，请联系主考考试办调整</div>
				<div style="display:none">
					<input id="cancelDriverId" name="cancelDriverId">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="reloadPage('${examId}','<%=basePath %>')">确定</button>
				</div>
			</div>
		</div>
	</div>
	<!--cancel error modal end-->

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
		};

		//    var selectAll = $("#select_all");
		$(".select_all").change(
				function() {
					if ($(this).is(":checked") == true) {
						$(this).parent().next().children().eq(1).children()
								.children().children('input').prop('checked',
										true);
					} else if ($(this).is(":checked") == false) {
						$(this).parent().next().children().eq(1).children()
								.children().children('input').removeAttr(
										"checked");
					}
				});

		//驾驶员类型筛选，全部事件
		$(".all_checkBox").change(function() {

			if ($(this).is(':checked') == true) {
				$(".condition_checkBox").removeAttr('checked');
			}
			changeSignupStatus();
		});

		$(".condition_checkBox").change(
				function() {
					if ($(".all_checkBox").is(':checked') == true
							&& $(this).is(':checked') == true) {
						$(".all_checkBox").removeAttr('checked');
					}
					changeSignupStatus()
				});
	</script>

</body>
</html>