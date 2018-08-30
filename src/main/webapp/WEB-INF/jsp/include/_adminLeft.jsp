<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="col-md-2" style="padding:0px; width:220px;">
          
          <div class="list-group" style="margin-bottom: 2px;">
            <a href="#" class="list-group-item active">
              组织机构管理
            </a>
            <a href="${pageContext.request.contextPath}/member.html" class="list-group-item">员工帐号管理</a>
            <a href="${pageContext.request.contextPath}/auth/authHome.html" id="authHome" class="list-group-item">权限管理</a>
            <a href="${pageContext.request.contextPath}/authGroup/authGroupHome.html" id="authGroupHome" class="list-group-item">权限分组管理</a>
            <a href="${pageContext.request.contextPath}/role/roleHome.html" id="roleHome" class="list-group-item">角色管理</a>
          </div>
          <div class="list-group" style="margin-bottom: 2px;">
            <a href="#" class="list-group-item active">
              页面控制
<%--                <a href="${pageContext.request.contextPath}/admin/setWebIndex.html" id="setWebIndex" class="list-group-item">首页控制</a> --%>
                <a href="${pageContext.request.contextPath}/admin/setWebIndexImages.html" id="setWebIndexImages" class="list-group-item">首页图片管理</a>
                 <a href="${pageContext.request.contextPath}/admin/setWebIndexQuality.html" id="setWebIndexQuality" class="list-group-item">品控工具管理</a>
                  <a href="${pageContext.request.contextPath}/admin/setWebIndexElec.html" id="setWebIndexElec" class="list-group-item">机电产品检测推荐</a>
                   <a href="${pageContext.request.contextPath}/admin/setWebIndexFood.html" id="setWebIndexFood" class="list-group-item">食品饮料检测推荐</a>
                   <a href="${pageContext.request.contextPath}/admin/setWebIndexLight.html" id="setWebIndexLight" class="list-group-item">轻工产品检测推荐</a>
                   <a href="${pageContext.request.contextPath}/admin/setWebIndexFriendlyLink.html" id="setWebIndexFriendlyLink" class="list-group-item">友情链接配置</a>
               <a target="_blank" href="${pageContext.request.contextPath}/admin/webIndex.html" id="webIndex" class="list-group-item">首页查看</a>
            </a>
          </div>
          <div class="list-group" style="margin-bottom: 2px;">
            <a href="#" class="list-group-item active">
              底层参数配置
            </a>
            <a href="${pageContext.request.contextPath}/platformConfig/businessTypesMaintainInDD.html" id="businessTypesMaintainInDD" class="list-group-item">主营业务类别</a>
            <a href="${pageContext.request.contextPath}/platformConfig/serviceTypesMaintainInDD.html" id="serviceTypesMaintainInDD" class="list-group-item">服务产品类别</a>
			<a href="${pageContext.request.contextPath}/platformConfig/applicationTypesMaintainInDD.html" id="applicationTypesMaintainInDD" class="list-group-item">应用场景类别</a>
            <a href="${pageContext.request.contextPath}/standard/standard_mng.html" id="standard_mng" class="list-group-item">标准数据库</a>
			<a href="${pageContext.request.contextPath}/item/item_mng.html" id="item_mng" class="list-group-item">项目名称数据库</a>
            <a href="#" class="list-group-item">靠谱软件服务协议</a>
			<a href="#" class="list-group-item">靠谱检测服务协议</a>
          </div>
          <div class="list-group" style="margin-bottom: 2px;">
             <a href="${pageContext.request.contextPath}/admin/showProviders.html" id="showProviders" class="list-group-item active">
              机构管理
            </a>
            <a href="#" id="" class="list-group-item">机构帐号详细信息</a>
            <a href="#" id="" class="list-group-item">机构帐号性质</a>
			<a href="#" id="" class="list-group-item">机构积分等级</a>
			<a href="#" id="" class="list-group-item">机构活跃度</a>
			<a href="#" id="" class="list-group-item">机构认证</a>
          </div>
		  <div class="list-group" style="margin-bottom: 2px;">
            <a href="${pageContext.request.contextPath}/admin/showConsumers.html" id="showConsumers" class="list-group-item active">
              会员管理
            </a>
            <a href="#" id="" class="list-group-item">会员详细信息</a>
            <a href="#" id="" class="list-group-item">会员积分等级</a>
			<a href="#" id="" class="list-group-item">会员活跃度</a>
          </div>
		  <div class="list-group" style="margin-bottom: 2px;">
		   
            <a href="${pageContext.request.contextPath}/service/plat_service_mng.html" id="plat_service_mng" class="list-group-item active">
              服务管理
            </a>
			<a href="${pageContext.request.contextPath}/orgitem/create.html" id="orgitem_add" class="list-group-item">项目赋值</a>
<a href="${pageContext.request.contextPath}/orgitem.html" class="list-group-item">检测项目列表</a>
          </div>
		  <div class="list-group" style="margin-bottom: 2px;">
            <a href="${pageContext.request.contextPath}/order.html" id="plat_order_mng" class="list-group-item active">
              订单管理
            </a>
          </div>
		  <div class="list-group" style="margin-bottom: 2px;">
            <a href="${pageContext.request.contextPath}/inspect.html" id="plat_inspect_mng" class="list-group-item active">
              报检管理
            </a>
          </div>
		  <div class="list-group" style="margin-bottom: 2px;">
            <a href="${pageContext.request.contextPath}/report/mng.html" id="plat_report_mng" class="list-group-item active">
              报告管理
            </a>
			<a href="#" class="list-group-item">上传报告</a>
          </div>
		  <div class="list-group" style="margin-bottom: 2px;">
            <a href="#" class="list-group-item active">
              需求管理
            </a>
          </div>
		  <div class="list-group" style="margin-bottom: 2px;">
            <a href="${pageContext.request.contextPath}/comment/showAllcomment.html?pageNo=1" class="list-group-item active">
              评价管理
            </a>
          </div>
          <div class="list-group" style="margin-bottom: 2px;">
            <a href="${pageContext.request.contextPath}/jsp/push.jsp" class="list-group-item active">
              百度推送
            </a>
          </div>
		  <div class="list-group" style="margin-bottom: 2px;">
            <a href="#" class="list-group-item active">
              资讯管理
            </a>
			<a href="${pageContext.request.contextPath}/news/saveNews.html" id="saveNews" class="list-group-item">发布资讯</a>
          <a href="${pageContext.request.contextPath}/news/news_mng.html" id="news_mng" class="list-group-item">我的资讯</a>
          <a href="${pageContext.request.contextPath}/news/committed_news_mng.html" id="committed_news_mng" class="list-group-item">资讯审核</a>
          </div>
		   <div class="list-group" style="margin-bottom: 2px;">
            <a href="#" class="list-group-item active">
              图片空间管理
            </a>
          </div>
		   <div class="list-group" style="margin-bottom: 2px;">
            <a href="#" class="list-group-item active">
              资料空间管理
            </a>
          </div>
		   <div class="list-group" style="margin-bottom: 2px;">
            <a href="#" class="list-group-item active">
              广告资源位管理
            </a>
          </div>
		   <div class="list-group" style="margin-bottom: 2px;">
            <a href="#" class="list-group-item active">
              搜索排名系统
            </a>
          </div>
 <div class="list-group" style="margin-bottom: 2px;">
	<a href="#" id="showPartner" class="list-group-item active">
              外部合作方管理
            </a>
            <a href="${pageContext.request.contextPath}/cooperate/savePartner.html" id="" class="list-group-item">
              合作方管理
            </a>
            <a href="${pageContext.request.contextPath}/cooperate/result.html" id="" class="list-group-item">合作方营销结果</a>
            
          </div>
    <div class="list-group" style="margin-bottom: 2px;">
        <a href="${pageContext.request.contextPath}/channel/showChannel.html" class="list-group-item active">
            频道管理
        </a>
    </div>
    <!--
		   <div class="list-group" style="margin-bottom: 2px;">
            <a href="#" class="list-group-item active">
              官方eLIMES
            </a>
          </div>
		   <div class="list-group" style="margin-bottom: 2px;">
            <a href="#" class="list-group-item active">
              官方CRM系统
            </a>
          </div>-->
        </div>