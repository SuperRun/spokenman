<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--footer start-->

<div class="col-md-3">
    <div class="attach_part" id="attach_part">
            <div class="attach-heading"><span>通知管理</span></div>
            <div class="attach-body">
                <ul class="ul-list">
                    <c:if test="${loginedMemberProvince}">
                        <li><a href="${path}/announcement/new/<%=AnnouncementTypeEnum.TRAINNOTICE.getType()%>" class="list_item" >培训通知发布</a></li>
                        <li><a href="${path}/announcement/new/<%=AnnouncementTypeEnum.EXAMNOTICE.getType()%>" class="list_item" >考试通知发布</a></li>
                        <li><a href="${path}/announcement/new/<%=AnnouncementTypeEnum.SAFENOTICE.getType()%>" class="list_item" >安全通知发布</a></li>
                        <li><a href="${path}/announcement/new/<%=AnnouncementTypeEnum.PLATFORMNOTICE.getType()%>" class="list_item" >平台通知发布</a></li>
                    </c:if>
                    <li><a href="${path}/announcement/list" class="list_item">通知列表</a></li>
                </ul>
            </div>
    </div>
</div>

