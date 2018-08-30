<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../2/include/_taglib_includes.jsp"%>

<!--模拟考试管理-->
<!DOCTYPE html>
<html>
<head lang="en">
<%@include file="../../../2/include/_html_head.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>media/2/js/exam/pageBaseJS.js"></script>
<script type="text/javascript"
	src="<%=basePath%>media/2/js/exam/member/practiceList.js"></script>
<script type="text/javascript"
	src="<%=basePath%>media/2/js/exam/member/practice.js"></script>
<title>新闻发言人在线学习培训平台-模拟考试管理</title>
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
				<div class="block block-two">
					<!-- Default panel contents -->
					<div class="block-heading">
						<span>模拟考试</span>
					</div>
					<form id="data" action="<%=basePath%>exam/member/practice"
						method="get">
						<div style="display: none">
							<input name="page" id="page" value="${pageBaseDto.page }">
							<input name="rows" id="rows" value="${pageBaseDto.rows }">
							<input name="pageNum" id="pageNum"
								value="${pageBaseDto.pageNum }"> <input name="searchKey"
								id="searchKey" value="${pageBaseDto.searchKey }"> <input
								name="status" id="status" value="${pageBaseDto.status }">
						</div>
					</form>
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
									class="form-control" placeholder="考试名称/发布组织" id="search_text"
									value="${pageBaseDto.searchKey }"> </label> <input
									type="button" class="search_btn" value="搜索" id="search_btn"
									onclick="submit()">
							</div>
						</div>
						<div id="pb_content">
							<table class="table table-bordered table-striped">
								<thead>

									<tr>
                                    	<th class="text-center" style="cursor: pointer;">发布时间</th>

                                    	<th class="text-center" style="cursor: pointer;">考试名称</th>

                                    	<th class="text-center" style="cursor: pointer;">操作</th>
                                	</tr>

								</thead>



								<tbody>

									<c:forEach items="${practiceExams }" var="exam">
										<tr>

											<td class="text-center">${exam.createTime }</td>

											<td class="text-center"><a data-toggle="modal"
												data-target="#examInfo_Modal" href="#"
												onclick="getExamDesignDetail('${exam.id}','<%=basePath%>')">${exam.name }</a>
											</td>

											<td class="text-center"><a
												href="<%=basePath %>exam/member/edit/${exam.id}"
												class="btn btn-primary">编辑</a>
												<button class="btn btn-primary btn_two" data-toggle="modal"
													data-target="#delete_Modal"
													onclick="conveyExamId('${exam.id}')">删除</button></td>

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
	<!--examInfo modal start-->
	<div class="modal fade" id="examInfo_Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="examInfoModalLabel">模拟考试信息</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-4 text_title">考试名称：</label> <label
								class="col-sm-8 text_message" id="practiceDetailName">杭州市水泥车驾驶员资格考试</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">发布单位：</label> <label
								class="col-sm-8 text_message" id="practiceDetailOrgName">浙江省水泥办</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">考试时长：</label> <label
								class="col-sm-8 text_message" id="practiceDetailDuration">1小时</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">总题数：</label> <label
								class="col-sm-8 text_message" id="practiceDetailQuestionSum">1小时</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">总分：</label> <label
								class="col-sm-8 text_message" id="practiceDetailScoreSum">1小时</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">及格题目数：</label> <label
								class="col-sm-8 text_message" id="practiceDetailPassNum">90</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title">及格分数：</label> <label
								class="col-sm-8 text_message" id="practiceDetailPassScore">90</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 text_title" id="practiceDetailDescription">考试备注：</label>
							<div class="col-sm-12">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th class="text-center" style="cursor: pointer;">题型</th>
											<th class="text-center" style="cursor: pointer;">知识点</th>
											<th class="text-center" style="cursor: pointer;">题目</th>
										</tr>
									</thead>
									<tbody id="practiceDetailDesigns">
										<tr>
											<td class="text-center">单选题</td>
											<td class="text-center">让车情景相关试题</td>
											<td class="text-center">20</td>
										</tr>
									</tbody>
								</table>
							</div>
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
					<h4 class="modal-title" id="deleteModalLabel">删除本次模拟考试</h4>
				</div>
				<div class="modal-body">是否确定删除本次模拟考试？</div>
				<div class="modal-footer">
					<input type="text" id="examId" style="display:none">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="deletePractice('<%=basePath%>')">确定</button>
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
			var height = $("#attach_part").height() + 20 > $(
					".content .row .col-md-9").height() ? $("#attach_part")
					.height() + 20 : $(".content .row .col-md-9").height();
			height = height > document.body.clientHeight - 228 ? height
					: document.body.clientHeight - 228;
			$(".content .row .col-md-3").css("height", height);
		};
	</script>
</body>
</html>