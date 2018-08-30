<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../2/include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../../2/include/_html_head.jsp" %>
    <title>基于B/S程序的无纸化网络考试系统</title>
    <script src="${path}/media/js/do/hr/driver/index_list.js"></script>
    <style>
        body{
            background-color: #fff;}
    </style>
    <link href="http://cdn.bootcss.com/zTree.v3/3.5.26/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet">
</head>
<body>
<%--<div class="container">--%>

<!--main content start-->

<!--message start-->
<div class="content">
    <%--<div class="row">--%>

        <div class="col-md-6">
            <div class="block block-two">
                <!-- Default panel contents -->
                <div class="block-heading"><span>驾驶员档案资料 | <b>${orgName}</b></span></div>
                <div class="block-body">
                    <div id="pb_top" class="col-md-12" >
                        <div class="col-md-3"  >
                            <label id="record_num" class="record_num">
                                    <select size="1" name="sample_1_length" aria-controls="sample_1" class="select"  id="selectrow" onchange="newRows()">
                                        <c:if test="${rowsNow==1 }">
                                            <option value = "1" selected="selected" >1</option>
                                        </c:if>
                                        <c:if test="${rowsNow!=1 }">
                                            <option value = "1" >1</option>
                                        </c:if>
                                        <c:if test="${rowsNow==5 }">
                                            <option value = "5" selected="selected" >5</option>
                                        </c:if>
                                        <c:if test="${rowsNow!=5 }">
                                            <option value = "5" >5</option>
                                        </c:if>

                                        <c:if test="${rowsNow==10 }">
                                            <option value = "10" selected="selected" >10</option>
                                        </c:if>
                                        <c:if test="${rowsNow!=10 }">
                                            <option value = "10" >10</option>
                                        </c:if>
                                        <c:if test="${rowsNow==20 }">
                                            <option value = "20" selected="selected" >20</option>
                                        </c:if>
                                        <c:if test="${rowsNow!=20 }">
                                            <option value = "20" >20</option>
                                        </c:if>
                                        <c:if test="${rowsNow==40 }">
                                            <option value = "40" selected="selected" >40</option>
                                        </c:if>
                                        <c:if test="${rowsNow!=40 }">
                                            <option value = "40" >40</option>
                                        </c:if>
                                    </select> 记录/页
                            </label>
                        </div>
                        <div class="search_part text-center col-md-3">
                            <input type="text" aria-controls="" class="search_input" placeholder="驾驶员档案信息" id="keywd"  name="keywd" style="width: 255px;" >
                            <input type="button" class="search_btn" value="搜索" id="search_btn" name="submit" onclick="search()" >
                        </div>

                    </div>
                    <div id="pb_content">
                        <table class="table table-bordered table-striped">
                            <thead>

                            <tr>
                                <th class="text-center" style="cursor: pointer;"><a href="#" onclick="sort('name')" alter="点击按此排序" title="点击按此排序">驾驶员</a></th>

                                <th class="text-center" style="cursor: pointer;"><a href="#" onclick="sort('sfzNo')" alter="点击按此排序" title="点击按此排序">身份证</a></th>

                                <th class="text-center" style="cursor: pointer;"><a href="#" onclick="sort('mobile')" alter="点击按此排序" title="点击按此排序">联系方式</a></th>

                                <th class="text-center" style="cursor: pointer;">所属考生办</th>

                                <c:if test="${loginedMemberProvince}">
                                    <th class="text-center" style="cursor: pointer;">操作</th>
                                </c:if>
                            </tr>

                            </thead>



                            <tbody>

                            <c:forEach items="${drivers }" var="driver" >
                                <tr>
                                    <td class="text-center">${driver.name }</td>
                                    <%--<td class="text-center">${driver.gen }</td>--%>
                                    <td class="text-center">${driver.sfzNo }</td>
                                    <td class="text-center">${driver.mobile }</td>
                                    <td class="text-center">${driver.org_name }</td>
                                    <c:if test="${loginedMemberProvince}">
                                        <td class="text-center">
                                            <button onclick="udpateDri(${driver.id })" class="btn-custom btn-middle btn-table">修改</button>
                                            <%--<button class="btn-custom btn-middle btn-table" data-toggle="modal" data-target="#sure_Modal" onclick="del(${driver.id})" >删除</button>--%>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>

                            </tbody>

                        </table>
                        <%--<a href="<%=path %>/driver/create" ><button class="btn-custom btn-middle" id="new_btn">新增</button></a>--%>
                        <span>总共<label class="num" id="records">${driverNum }</label>条记录 | 第<label class="num" id="page_num">${pageNow }</label>页/共<label class="num" id="pages">${pageNum }</label>页</span>
                        <nav id="paging_nav">
                            <ul class="pagination">
                                <li>
                                    <a href="#" aria-label="first" class="glyphicon glyphicon-step-backward" onclick="toPage(1)" alt="第一页" title="第一页"></a>
                                </li>
                                <li>
                                    <c:if test="${pageNow==1 }">
                                        <a href="#" aria-label="previous" class="glyphicon glyphicon-chevron-left" alt="上一页" title="上一页"></a>
                                    </c:if>
                                    <c:if test="${pageNow!=1 }">
                                        <a href="#" aria-label="previous" onclick="prePage()" class="glyphicon glyphicon-chevron-left" alt="上一页" title="上一页"></a>
                                    </c:if>
                                </li>
                                <li>
                                    <c:if test="${pageNow==pageNum }">
                                        <a href="#" aria-label="next" class="glyphicon glyphicon-chevron-right" alt="下一页" title="下一页"></a>
                                    </c:if>
                                    <c:if test="${pageNow!=pageNum }">
                                        <a href="#" aria-label="next" onclick="nextPage()" class="glyphicon glyphicon-chevron-right" alt="下一页" title="下一页"></a>
                                    </c:if>
                                </li>
                                <li>
                                    <a href="#" aria-label="last" class="glyphicon glyphicon-step-forward" onclick="toPage(${pageNum })" alt="最后一页" title="最后一页"></a>
                                </li>

                                <li>
                                    <input type="text" aria-controls="" class="input-custom" placeholder="页码" id="page_text">
                                </li>
                                <li>
                                    <input type="button" class="btn btn-primary btn-middle" value="跳转" id="goto_btn" onclick="newPage()" >
                                </li>

                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

    </div>
<%--</div>--%>
<!--message end-->
<!--sure modal start-->
<div class="modal fade" id="sure_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="sureModalLabel">消息确认</h4>
            </div>
            <div class="modal-body text-center">
                是否确认删除该驾驶员？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--sure modal end-->
<div style="display: none">
    <form action="<%=basePath%>driver/list" method="post" id="data" name="data">
        <input type="text" id="pageNow" name="page" value="${pageNow }">
        <input type="text" id="sortNow" name="sort" value="${sortNow }">
        <input type="text" id="keywdNow" name="keywd" value="${keywdNow }">
        <input type="text" id="rowsNow" name="rows" value="${rowsNow }">
        <input type="text" id="orgId" name="orgId" value="${orgId}" >
    </form>
    <input type="text" id="delId" >
</div>
<!--main content end-->
<%--</div>--%>

</body>

<script>
    var pageNum = '${pageNum }';
    var orgId = '${orgId}';
    var jsonZtree = $.parseJSON('${ztree}');

    function udpateDri(id) {
        if(top.location != self.location)
            top.location = path+"/driver/"+id+"/update";
    }
    function toDriver(id) {
        if(top.location != self.location)
            top.location = path+"/driver/"+id;
    }



    //    window.onload = function () {
//        var height = $("#attach_part").height()+20>$(".content .row .col-md-9").height()?$("#attach_part").height()+20:$(".content .row .col-md-9").height();
//        height=height>document.body.clientHeight-228?height:document.body.clientHeight-228;
//        $(".content .row .col-md-3").css("height",height);
//    };
</script>

<%--<script src="${path}/media/2/js/driver/driver_tree.js"></script>--%>

</html>
