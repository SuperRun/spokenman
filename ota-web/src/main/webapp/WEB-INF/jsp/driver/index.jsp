<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="../taglib_includes.jsp" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chrome=1">
    
    <%@ include file="../basic_includes.jsp"%>
    <title>浙江省散装水泥专用车辆驾驶员系统-首页</title>
</head>
<body>
<!--header start-->
<%@ include file="../include/_driver_hr_header.jsp" %>
<!--header end-->
<!--main content start-->
<!--message start-->
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <!--menu start-->
            <%@ include file="../include/hr/_menu.jsp" %>
            <!--menu end-->
        </div>
        <div class="col-md-6" id="message">
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">通知</div>
                <div class="panel-body">
                    <c:if test="${empty anns or fn:length(anns)==0}">
                        无通知
                    </c:if>
                    <c:if test="${not empty anns and fn:length(anns)>0}">
                        <ul>
                            <c:forEach items="${anns }" var="ann">
                                <li><a href="<%=path %>/announcement/${ann.id }"
                                       class="message_item">${ann.title }</a><span
                                        class="time">${ann.createDate }</span></li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="col-md-4" id="user">
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">驾驶员信息</div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-4 label_message">姓名：</label>
                            <label class="col-sm-8 label_message">${requestScope.driver.name }</label>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 label_message">性别：</label>
                            <label class="col-sm-8 label_message">${requestScope.driver.gen }</label>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 label_message">联系方式：</label>
                            <label class="col-sm-8 label_message">${requestScope.driver.mobile }</label>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 label_message">家庭住址：</label>
                            <label class="col-sm-8 label_message">${requestScope.driver.address }</label>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 label_message">所属车企：</label>
                            <label class="col-sm-8 label_message">${requestScope.driver.org_name }</label>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button type="button" class="btn btn-primary" id="certificates">查看证书</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        
    </div>
</div>
<!--message end-->
<!--main content end-->
<!--footer start-->

<%@ include file="../include/_driver_hr_footer.jsp" %>

<!--footer end-->
<script>
    window.onload = function(){
        var menu = new Array();
        menu = $(".menu");
        var a_hover = $(".a_hover");
        for(var k =0;k<a_hover.length;k++){
            $(a_hover[k]).next().show();
        }
        for(var i=0;i<menu.length;i++){
            menu[i].onclick = function () {
                var sub_menu = $(this).next();
                for(var j=0;j<menu.length;j++){
                    if(j!=i){
                        $(menu[j]).removeClass("a_hover");
                        $(menu[j]).next().hide();
                    }

                }
                $(this).addClass("a_hover");

                if(sub_menu.css("display")=="none"){
                    sub_menu.show();
                }else if(sub_menu.css("display")!="none"){
                    sub_menu.hide();
                }

            }
        }
        var sub_list = new Array();
        sub_list = $(".sub_list");
        for(var m = 0;m<sub_list.length;m++){
            sub_list[m].onclick = function () {
                for(var n = 0;n<sub_list.length;n++){
                    if(n!=m){
                        $(sub_list[n]).removeClass("list_hover");
                    }
                }

                $(this).addClass("list_hover");
            }
        }
    }
</script>
</body>
</html>