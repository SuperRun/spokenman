<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<link href="${pageContext.request.contextPath}/css/_shop_head.css" type="text/css" rel="stylesheet" />

<div id="header">
	<div class="ad" style="display:none;">
		<a href="#"><img src="${pageContext.request.contextPath}/img/ad.png" /></a>
	</div>
	<%@include file="_top_new.jsp" %>
	<div class="header_store">
		<img style="height:84px;width:84px;" alt="机构头像" src="${pageContext.request.contextPath}/${orgnization.logoUrl}">
		<div style="width:400px;padding-left:8px;margin-top:55px;float:left;">
		
		</div>
		<div class="search radius6" style="box-sizing:content-box;margin-left: 67px;">
			<form id="commonSearchForm">
				<input type="hidden" value="${commonPageNo}" id="commonPageNo" /> <input
					type="hidden" value="${commonTotalPages }" id="commonTotalPages" />
				<input type="hidden" value="${commonOrderBy}" id="commonOrderBy" /> <input
					class="inp_srh" value="${commonTitle }" id="commonTitle"
					name="commonTitle" placeholder="请输入关键字"> <select
					class="select_box" id="choose">
					<option value="0">检测服务</option>
					<option value="1">商家店铺</option>
					<option value="2">新闻资讯</option>
							<!-- 
							<option value="4">技术文档</option>
							<option value="22">下载中心</option> -->
				</select> <input class="btn_srh" id="commonSearchBtn" type="button"
					name="submit" value="搜索">
			</form>
		</div>

	</div>
	<img style=" width:100%; text-align:center;" src="${pageContext.request.contextPath}/img/Store_ad.jpg" />
	<div id="nav">
		<div class="nav">
			
			<div style="width: 221px;float: left; position:relative;">
			
				<p id="psl" class="psl1">检啦质检商城</p>
			<div id="downmenu" class="downmenu" style=" display:block;">
				<ul>
					<c:forEach var="treeNode" items="${jianlafn:sampleType().children }">
						<li>
							<a href="${pageContext.request.contextPath}/search/jiancefuwu?typePid=${treeNode.id}">${treeNode.title}</a>
							<div class="d_erji">
								<dl class="erji">
									<c:forEach items="${treeNode.children}" var="tree2">
										<dd>
										<a href="${pageContext.request.contextPath}/search/jiancefuwu?typeId=${tree2.id}">${tree2.title}</a>
										</dd>
									</c:forEach>
								</dl>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			</div>
			<ul class="nav_list_s">
				<li><a href="${pageContext.request.contextPath}/jiancejigou/${orgnization.shopSld}.html" >机构首页 </a></li>
				<li><a href="${pageContext.request.contextPath}/jiancejigou/shopService/${orgnization.shopSld}.html">检测服务 </a></li>
				<li><a href="${pageContext.request.contextPath}/jiancejigou/shopNews.html?orgId=${orgnization.id }">机构动态</a></li>
				<li><a href="${pageContext.request.contextPath}/jiancejigou/shopAbout/${orgnization.shopSld}.html">关于我们</a></li>
			</ul>
		</div>

	</div>

</div>

<script type="text/javascript">
	$(function() {
		$(".eclose").click(function(){
			$(".ad").hide();
			$(".eopen").show();
			$(".eclose").hide();
		})
		$(".eopen").click(function(){
			$(".ad").show();
			$(".eclose").show();
			$(".eopen").hide();
		})
		$(".top_1").click(function(){
			$("top_1").css("background:red;");
			$(".head_sou").show();
			$(".top_1 a").addClass("hom");
		
		})
		
	});
</script>
<script>
$(function(){
	
	$("#downmenu").delegate("ul li", "mouseover", function ( e ) {
		$(this).find("div").show();
	});
	$("#downmenu").delegate("ul li", "mouseout", function ( e ) {
		$(this).find("div").hide();
	});
	
	$("#psl").click(function(){
		if($(".downmenu").is(":hidden")){
		       $(".downmenu").show();    //如果元素为隐藏,则将它显现
		}else{
		      $(".downmenu").hide();     //如果元素为显现,则将其隐藏
		}
	});
	$(".downmenu").hide();
});
</script>
<script type="text/javascript">
$("#commonSearchForm").keydown(function(e){
	e = e||event;
	if(e.keyCode==13){
		$('#commonSearchBtn').click();
		return false;
	}
});
$(function(){
	$("#commonSearchBtn").click(function(){
		var choose=$("#choose").val();
		if(choose==0){//检测服务
			var url="${pageContext.request.contextPath}/search/jiancefuwu.html?"+$('#commonSearchForm').serialize();
			location=url;
		}
		if(choose==1){//检测店铺
			var url="${pageContext.request.contextPath}/search/organization.html?"+$('#commonSearchForm').serialize();
			location=url;
		}
		if(choose==2){//检测店铺
			var url="${pageContext.request.contextPath}/search/news.html?wd="+$("#commonTitle").val();
			location=url;
		}
	});
});
</script>