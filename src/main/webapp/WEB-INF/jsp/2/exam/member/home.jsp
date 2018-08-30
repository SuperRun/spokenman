<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="../../../2/include/_taglib_includes.jsp" %>

<!--散装办首页-->
<!DOCTYPE html>
<html>
<head lang="en">
    <%@include file="../../../2/include/_html_head.jsp" %>
    <link href="<%=basePath%>media/2/css/exam_member_indexOrg.css"
          type="text/css" rel="stylesheet"/>
    <script type="text/javascript"
            src="<%=basePath%>media/2/js/exam/baseExam.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>media/2/js/exam/pageBaseJS.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>media/2/js/exam/member/home.js"></script>
    <title>新闻发言人在线学习培训平台-全部考试</title>
</head>
<body>

<!--header start-->
<%@include file="../../include/_member_header.jsp" %>
<!--header end-->

<!--main content start-->

<!--message start-->
<div class="content container">
    <div class="row">
        <div class="col-md-3">
            <!-- side menu start -->
            <%@include file="menu.jsp" %>
            <!-- side menu end -->
        </div>
        <div class="col-md-9">
            <div class="block-two block">
                <!-- Default panel contents -->
                <div class="block-heading">
                    <span>已发布的考试</span>
                </div>
                <form id="data" action="<%=basePath%>exam/member/home" method="get">
                    <div style="display: none">
                        <input name="page" id="page" value="${pageBaseDto.page }">
                        <input name="rows" id="rows" value="${pageBaseDto.rows }">
                        <input name="pageNum" id="pageNum"
                               value="${pageBaseDto.pageNum }"> <input name="searchKey"
                                                                       id="searchKey"
                                                                       value="${pageBaseDto.searchKey }">
                        <input
                                name="status" id="status" value="${pageBaseDto.status }">
                    </div>
                </form>
                <div class="block-body">
                    <div id="pb_top">
                        <div class="col-md-6">
                            <label id="record_num"> <select size="1"
                                                            name="sample_1_length"
                                                            aria-controls="sample_1" class=""
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
                                                      class="form-control" placeholder="考试名称/发布组织"
                                                      id="search_text"
                                                      value="${pageBaseDto.searchKey }"> </label>
                            <input
                                    type="button" class="search_btn" value="搜索" id="search_btn"
                                    onclick="submit()">
                        </div>
                    </div>
                    <div id="pb_content">
                        <table class="table table-bordered table-striped">
                            <thead>

                            <tr>
                                <th class="text-center" style="cursor: pointer;">发布时间</th>

                                <th class="text-center" style="cursor: pointer;">考试时间</th>

                                <th class="text-center" style="cursor: pointer;">考试名称</th>

                                <th class="text-center" style="cursor: pointer;">考试状态</th>

                                <th class="text-center" style="cursor: pointer;">操作</th>
                            </tr>

                            </thead>


                            <tbody>

                            <c:forEach items="${exams }" var="exam">
                                <tr>

                                    <td class="text-center">${exam.creatTime }</td>

                                    <td class="text-center">${exam.examStartTime
                                            }--${exam.examEndTime }</td>

                                    <td class="text-center"><a data-toggle="modal"
                                                               data-target="#examInfo_Modal"
                                                               href="#"
                                                               onclick="getExamDetail('${exam.examId}','<%=basePath%>')">${exam.name }</a>
                                    </td>

                                    <c:if test="${exam.state==0 }">
                                        <!-- 已发布状态 -->
                                        <td class="text-center"><span
                                                class="label-info label release">已发布</span></td>
                                        <td class="text-center">
                                            <button
                                                    class="btn-custom btn-middle btn-table"
                                                    onclick="deirectToEditExam('${exam.examId}','<%=basePath%>')">
                                                编辑
                                            </button>
                                            <button class="btn-custom btn-middle btn-table"
                                                    onclick="deirectToEditPaper('${exam.examId}','<%=basePath%>')">
                                                试卷管理
                                            </button>
                                            <button class="btn btn-default btn-middle btn-table"
                                                    data-toggle="modal" data-target="#delete_Modal"
                                                    onclick="conveyExamIdForDelete('${exam.examId}','<%=basePath%>')">
                                                删除
                                            </button>
                                            <br/> <c:if
                                                test="${!empty exam.hasAnnouncement && exam.hasAnnouncement }">
                                            <button class="btn-custom btn-table btn-middle"
                                                    onclick="forwordToEditAnnouncement('${exam.announcementId}','<%=basePath%>')">
                                                修改公告
                                            </button>
                                        </c:if> <c:if
                                                test="${empty exam.hasAnnouncement || !exam.hasAnnouncement }">
                                            <button class="btn-custom btn-table btn-middle"
                                                    onclick="forwordToSetAnnouncement('${exam.examId}','<%=basePath%>')">
                                                发布公告
                                            </button>
                                        </c:if></td>


                                    </c:if>

                                    <c:if test="${exam.state==1 }">
                                        <!-- 报名状态 -->
                                        <td class="text-center"><span
                                                class="label-warning label signUp">报名中</span></td>
                                        <td class="text-center">
                                            <button class="btn-custom btn-middle btn-table"
                                                    onclick="directToSignup('${exam.examId }','<%=basePath%>')">
                                                报名管理
                                            </button>
                                            <button class="btn-custom btn-middle btn-table"
                                                    onclick="deirectToEditPaper('${exam.examId}','<%=basePath%>')">
                                                试卷管理
                                            </button>
                                            <button class="btn btn-primary btn-middle btn-table"
                                                    onclick="deirectToEditExam('${exam.examId}','<%=basePath%>')">
                                                编辑
                                            </button>
                                            <c:if
                                                    test="${!empty exam.hasAnnouncement && exam.hasAnnouncement }">
                                                <button class="btn-custom btn-table btn-middle"
                                                        onclick="forwordToEditAnnouncement('${exam.announcementId}','<%=basePath%>')">
                                                    修改公告
                                                </button>
                                            </c:if> <c:if
                                                test="${empty exam.hasAnnouncement || !exam.hasAnnouncement }">
                                            <button class="btn-custom btn-table btn-middle"
                                                    onclick="forwordToSetAnnouncement('${exam.examId}','<%=basePath%>')">
                                                发布公告
                                            </button>
                                        </c:if>
                                        </td>

                                    </c:if>

                                    <c:if test="${exam.state==2 }">
                                        <!-- 待考试状态 -->
                                        <td class="text-center"><span
                                                class="label label-danger Examing">待考试</span></td>
                                        <td class="text-center">
                                            <button class="btn btn-primary btn-middle btn-table"
                                                    onclick="deirectToEditExam('${exam.examId}','<%=basePath%>')">
                                                编辑
                                            </button>
                                            <button class="btn-custom btn-middle btn-table"
                                                    onclick="directToSignup('${exam.examId }','<%=basePath%>')">
                                                报名管理
                                            </button>
                                            <button class="btn-custom btn-middle btn-table"
                                                    onclick="deirectToEditPaper('${exam.examId}','<%=basePath%>')">
                                                试卷管理
                                            </button>
                                            <c:if
                                                    test="${!empty exam.hasAnnouncement && exam.hasAnnouncement }">
                                                <button class="btn-custom btn-table btn-middle"
                                                        onclick="forwordToEditAnnouncement('${exam.announcementId}','<%=basePath%>')">
                                                    修改公告
                                                </button>
                                            </c:if> <c:if
                                                test="${empty exam.hasAnnouncement || !exam.hasAnnouncement }">
                                            <button class="btn-custom btn-table btn-middle"
                                                    onclick="forwordToSetAnnouncement('${exam.examId}','<%=basePath%>')">
                                                发布公告
                                            </button>
                                        </c:if>
                                        </td>
                                    </c:if>

                                    <c:if test="${exam.state==3 }">
                                        <!-- 考试中状态 -->
                                        <td class="text-center"><span
                                                class="label label-danger Examing">考试中</span></td>
                                        <td class="text-center"><span id="wait">等待考试结束</span></td>
                                    </c:if>

                                    <c:if test="${exam.state==4 }">
                                        <!-- 阅卷状态 -->
                                        <td class="text-center"><span
                                                class="label label-success marking">阅卷中</span></td>
                                        <td class="text-center">
                                            <button
                                                    class="btn-custom btn-long"
                                                    onclick="directToScore('${exam.examId}','<%=basePath%>')">
                                                成绩录入
                                            </button>
                                        </td>
                                    </c:if>

                                    <c:if test="${exam.state==5 }">
                                        <!-- 证书录入状态 -->
                                        <td class="text-center"><span
                                                class="label label-primary finished">证书录入中</span>
                                        </td>
                                        <td class="text-center">
                                            <button
                                                    class="btn-custom btn-long"
                                                    onclick="directToCertificate('${exam.examId}','<%=basePath%>')">
                                                证书录入
                                            </button>
                                        </td>
                                    </c:if>

                                    <c:if test="${exam.state==6 }">
                                        <!-- 已完成状态 -->
                                        <td class="text-center"><span
                                                class="label label-default finished">已完成</span></td>
                                        <td class="text-center">
                                            <button
                                                    class="btn-custom btn-long" data-toggle="modal"
                                                    data-target="#exam_Modal"
                                                    onclick="getExamFinishInfo('${exam.examId}','<%=basePath%>')">
                                                查看考试情况
                                            </button>
                                        </td>
                                    </c:if>


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
                                           title="上一页"
                                           onclick="changePage('${pageBaseDto.page-1}')"></a>
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
                                           title="下一页"
                                           onclick="changePage('${pageBaseDto.page+1}')"></a>
                                    </li>
                                </c:if>
                                <li><a href="#" aria-label="last"
                                       class="glyphicon glyphicon-step-forward" alt="最后一页"
                                       title="最后一页"
                                       onclick="changePage('${pageBaseDto.pageNum}')"></a>
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
<!--delete_Modal start-->
<div class="modal fade" id="delete_Modal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="deleteModalLabel">删除确认</h4>
            </div>
            <div class="modal-body">是否确定删除本次考试？</div>
            <div style="display:none">
                <input id="examIdForDelete">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary btn-middle"
                        onclick="confirmDeleteExam('<%=basePath%>')">确定
                </button>
                <button type="button" class="btn btn-default btn-middle"
                        data-dismiss="modal">关闭
                </button>
            </div>
        </div>
    </div>
</div>
<!--delete_Modal end-->

<!--exam modal start-->
<div class="modal fade" id="exam_Modal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="examModalLabel">考试情况</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-4 text_title">考试名称：</label> <label
                            class="col-sm-8 text_message" id="examName">xxx考试</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 text_title">发布时间：</label> <label
                            class="col-sm-8 text_message" id="examCreateTime">2016/05/23</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 text_title">发布组织：</label> <label
                            class="col-sm-8 text_message" id="examOrg">杭州市散装办</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 text_title">考试通过人数：</label> <label
                            class="col-sm-8 text_message" id="examPassNum">45</label>
                    </div>
                    <div class="form-group text-center">
                        <a id="show-pass" style="cursor: pointer;">显示考试通过详情</a>
                    </div>
                    <div id="passInfo_part" class="moreInfo_part">
                        <div class="col-sm-12">
                            <table class="table table-bordered">
                                <caption>考试通过人员</caption>
                                <thead>
                                <tr>
                                    <th class="text-center" style="cursor: pointer;">驾驶员</th>
                                    <th class="text-center" style="cursor: pointer;">所在组织</th>
                                    <th class="text-center" style="cursor: pointer;">得分</th>
                                    <th class="text-center" style="cursor: pointer;">证书号</th>
                                </tr>
                                </thead>
                                <tbody id="examPassDrivers">
                                <tr>
                                    <td class="text-center">张三</td>
                                    <td class="text-center">杭州第一车企</td>
                                    <td class="text-center">杭州市散装办</td>
                                    <td class="text-center">90</td>
                                    <td class="text-center">1238434</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 text_title">考试参加人数：</label> <label
                            class="col-sm-8 text_message" id="examPresentNum">60</label>
                    </div>
                    <div class="form-group text-center">
                        <a id="show-join" style="cursor: pointer;">显示考试参加详情</a>
                    </div>
                    <div id="joinInfo_part" class="moreInfo_part">
                        <div class="col-sm-12">

                            <table class="table table-bordered">
                                <caption>考试参加人员</caption>
                                <thead>
                                <tr>
                                    <th class="text-center" style="cursor: pointer;">驾驶员</th>
                                    <th class="text-center" style="cursor: pointer;">所在组织</th>
                                    <th class="text-center" style="cursor: pointer;">参加时间</th>
                                    <th class="text-center" style="cursor: pointer;">得分</th>
                                </tr>
                                </thead>
                                <tbody id="examPresentDrivers">
                                <tr>
                                    <td class="text-center">张三</td>
                                    <td class="text-center">杭州第一车企</td>
                                    <td class="text-center">杭州市散装办</td>
                                    <td class="text-center">2016/11/2 10:00</td>
                                    <td class="text-center">70</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 text_title">考试报名人数：</label> <label
                            class="col-sm-8 text_message" id="examSignupNum">62</label>
                    </div>
                    <div class="form-group text-center">
                        <a id="show-sign" style="cursor: pointer;">显示考试报名详情</a>
                    </div>
                    <div id="signInfo_part" class="moreInfo_part">
                        <div class="col-sm-12">

                            <table class="table table-bordered">
                                <caption>考试报名人员</caption>
                                <thead>
                                <tr>
                                    <th class="text-center" style="cursor: pointer;">驾驶员</th>
                                    <th class="text-center" style="cursor: pointer;">所在组织</th>
                                    <th class="text-center" style="cursor: pointer;">报名时间</th>
                                </tr>
                                </thead>
                                <tbody id="examSignupDrivers">
                                <tr>
                                    <td class="text-center">张三</td>
                                    <td class="text-center">杭州第一车企</td>
                                    <td class="text-center">杭州市散装办</td>
                                    <td class="text-center">2016/10/30 09：00</td>
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
<!--exam modal end-->

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
                <h4 class="modal-title" id="examInfoModalLabel">考试信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-4 text_title">考试名称：</label> <label
                            class="col-sm-8 text_message" id="examDetailName"></label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 text_title">考试时间：</label> <label
                            class="col-sm-8 text_message" id="examDetailExamStartTime"></label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 text_title">考试备注：</label> <label
                            class="col-sm-8 text_message" id="examDetailDescription"></label>
                    </div>
                    <div class="form-group" style="display:none">
                        <input id="examDetailExamId" name="examDetailExamId">
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

<!--main content end-->

<!--footer start-->
<%@include file="../../include/_html_body.jsp" %>
<!--footer end-->

<script>
  window.onload = function() {
    window.onload = function() {
      var height = $("#attach_part").height()
      + 20
      > $(".content .row .col-md-9").height() ? $("#attach_part")
          .height() + 20 : $(".content .row .col-md-9").height();
      height = height > document.body.clientHeight - 228 ? height : document.body.clientHeight
        - 228;
      $(".content .row .col-md-3").css("height", height);
    };

    $("#show-pass").bind('click', function() {
      if ($("#passInfo_part").css('display') == 'none') {
        $("#passInfo_part").show();
        $(this).html("隐藏内容");
      } else if ($("#passInfo_part").css('display') == 'block') {
        $("#passInfo_part").hide();
        $(this).html("显示更多内容");
      }
    });

    $("#show-sign").bind('click', function() {
      if ($("#signInfo_part").css('display') == 'none') {
        $("#signInfo_part").show();
        $(this).html("隐藏内容");
      } else if ($("#signInfo_part").css('display') == 'block') {
        $("#signInfo_part").hide();
        $(this).html("显示更多内容");
      }
    });

    $("#show-join").bind('click', function() {
      if ($("#joinInfo_part").css('display') == 'none') {
        $("#joinInfo_part").show();
        $(this).html("隐藏内容");
      } else if ($("#joinInfo_part").css('display') == 'block') {
        $("#joinInfo_part").hide();
        $(this).html("显示更多内容");
      }
    });

  }
</script>
</body>
</html>