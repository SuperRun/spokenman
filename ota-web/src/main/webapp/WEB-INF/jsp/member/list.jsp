<%--
  Created by IntelliJ IDEA.
  User: dxb
  Date: 2016/8/30
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../taglib_includes.jsp" %>
<html>
<head>
    <%@ include file="../basic_includes.jsp" %>
    <title>${org.name }_员工列表</title>
    <script>
        var pageSum =${p.pageNum};
    </script>
    <script src="<%=path%>/media/js/do/hr/member/list-validate.js"></script>
</head>
<body>
<%@ include file="../include/hr/_member_hr_header.jsp" %>
<!--message start-->
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <!--menu start-->
            <%@ include file="../include/hr/_menu.jsp" %>

            <!--menu end-->
        </div>
        <div class="col-md-10">
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">员工列表 (${org.name } 共有员工 ${sum} 人)<span id="operation"><a
                        href="#" id="hide"
                        class="glyphicon glyphicon-chevron-up"></a><a
                        href="#" id="refresh" class="glyphicon glyphicon-refresh"></a><a href="#" id="close"
                                                                                         class="glyphicon glyphicon-remove"></a></span>
                </div>
                <div class="panel-body">
                    <div>
                        <div class="col-md-6">
                            <label class="form-inline">
                                <select size="1" name="sample_1_length" aria-controls="sample_1" class="form-control"
                                        id="record_num">
                                    <option value="5" selected="selected">5</option>
                                    <option value="15">15</option>
                                    <option value="20">20</option>
                                    <option value="-1">全部</option>
                                </select> 记录/页
                            </label>
                        </div>
                        <script>
                            $().ready(function () {
                                var recordNum = $('#record_num');
                                recordNum.val(${p.rows});
                                recordNum.change(function () {
                                    location.href = "<%=path%>/organization/${org.id}/members?page=1&rows=" + recordNum.val();
                                });
                            });
                        </script>

                        <div class="col-md-6">
                            <form class="form-inline" id="search_form" method="post"
                                  action="<%=path%>/organization/${org.id}/members">
                                <div class="form-group">
                                    <label for="keywd">搜索：</label>
                                    <input name="searchKey" id="keywd" class="form-control" value="${searchKey}">
                                    <input name="rows" type="hidden" id="form_rows" value="-1">
                                    <input name="page" type="hidden" id="form_page" value="1">
                                </div>
                                <div class="form-group">
                                    <button class="btn btn-primary">搜索</button>
                                </div>
                            </form>
                        </div>

                    </div>

                    <div class="col-md-12">
                        <c:if test="${not empty members}">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th class="text-center" style="cursor: pointer;">部门</th>
                                    <th class="text-center" style="cursor: pointer;">登录名</th>
                                    <th class="text-center" style="cursor: pointer;">员工姓名</th>
                                    <th class="text-center" style="cursor: pointer;">联系方式</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${members}" var="tempM">
                                    <tr>
                                        <td class="text-center">
                                            <c:if test="${not empty tempM.departmentName}">
                                                <a target="_blank" href="<%=path%>/department/${tempM.departmentId}">
                                                        ${tempM.departmentName}
                                                </a>
                                            </c:if>
                                            <c:if test="${empty tempM.departmentName}">
                                                无
                                            </c:if>
                                        </td>
                                        <td class="text-center">
                                                ${tempM.loginName}
                                        </td>
                                        <td class="text-center"><a target="_blank"
                                                                   href="<%=path%>/member/${tempM.id}">${tempM.name}</a>
                                        </td>
                                        <td class="text-center">${tempM.phone}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${ empty members}">
                            暂无员工
                        </c:if>
                    </div>
                    <span>总共<label class="num" id="records">${p.sum}</label>条记录 | 第<label class="num"
                                                                                          id="page_num">${p.page}</label>页/共<label
                            class="num" id="pages">${p.pageNum}</label>页</span>
                    <nav id="paging_nav">
                        <ul class="pagination">
                            <c:if test="${p.page>2}">
                                <li>
                                    <a href="<%=path%>/organization/${org.id}/members?rows=${p.rows}&page=1"
                                       aria-label="first" class="glyphicon glyphicon-step-backward" alt="第一页"
                                       title="第一页"></a>
                                </li>
                            </c:if>
                            <c:if test="${p.page<=2}">
                                <li class="disabled">
                                    <a aria-label="first" class="glyphicon glyphicon-step-backward" alt="第一页"
                                       title="第一页"></a>
                                </li>
                            </c:if>

                            <c:if test="${p.page>1}">
                                <li>
                                    <a href="<%=path%>/organization/${org.id}/members?rows=${p.rows}&page=${p.page-1}"
                                       aria-label="previous" class="glyphicon glyphicon-chevron-left" alt="上一页"
                                       title="上一页"></a>
                                </li>
                            </c:if>
                            <c:if test="${p.page<=1}">
                                <li class="disabled">
                                    <a
                                            aria-label="previous" class="glyphicon glyphicon-chevron-left" alt="上一页"
                                            title="上一页"></a>
                                </li>
                            </c:if>

                            <c:if test="${p.page<p.pageNum}">
                                <li>
                                    <a href="<%=path%>/organization/${org.id}/members?rows=${p.rows}&page=${p.page+1}"
                                       aria-label="next" class="glyphicon glyphicon-chevron-right" alt="下一页"
                                       title="下一页"></a>
                                </li>
                            </c:if>
                            <c:if test="${p.page>=p.pageNum}">
                                <li class="disabled">
                                    <a
                                            aria-label="next" class="glyphicon glyphicon-chevron-right" alt="下一页"
                                            title="下一页"></a>
                                </li>
                            </c:if>

                            <c:if test="${p.page<p.pageNum-1}">
                                <li><a href="<%=path%>/organization/${org.id}/members?rows=${p.rows}&page=${p.pageNum}"
                                       aria-label="last" class="glyphicon glyphicon-step-forward" alt="最后一页"
                                       title="最后一页"></a>
                                </li>
                            </c:if>
                            <c:if test="${p.page>=p.pageNum-1}">
                                <li class="disabled">
                                    <a
                                            aria-label="last" class="glyphicon glyphicon-step-forward" alt="最后一页"
                                            title="最后一页"></a>
                                </li>
                            </c:if>

                            <c:if test="${p.pageNum>5}">
                                <li>
                                    <form id="pageToForm" class="form-inline">
                                        <div class="form-group">
                                            <input type="number" aria-controls="" class="form-control"
                                                   placeholder="输入页码"
                                                   id="page_text" name="page">
                                            <input type="hidden" name="rows" value="${p.rows}">
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" class="btn btn-primary" value="跳转" id="goto_btn">
                                        </div>
                                    </form>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>

    </div>
</div>
<!--message end-->


<%@ include file="../include/hr/_member_hr_footer.jsp" %>
</body>
</html>
