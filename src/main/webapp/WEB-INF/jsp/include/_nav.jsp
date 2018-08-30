<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div id="nav">
	<div class="nav">
		<div style="width: 221px;float: left; position:relative;">
			<p id="psl" class="psl">检啦质检商城</p>
			<div id="downmenu" class="downmenu" style=" display:hidden;">
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
		<ul class="nav_list">
		    <li></li>
			<li><a href="${pageContext.request.contextPath}/">首页</a></li>
		    <li><a href="${pageContext.request.contextPath}/search/organization.html">第三方质检机构公司</a></li>
			<li><a href="${pageContext.request.contextPath}/search/jiancefuwu.html">检测服务</a></li>
			<li><a href="${pageContext.request.contextPath}/search/news.html?wd=">新闻资讯</a></li>
			<li style="float:right;"></li><!-- <a href="#">网站地图</a> -->
		</ul>
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
		$(".top_1 a").click(function(){
			if($(".head_sou").is(":hidden")){
				$("top_1").css("background:red;");
				$(".head_sou").show();
				$(".top_1 a").addClass("hom");
			}else{
				$(".head_sou").hide();
				$(".top_1 a").removeClass("hom");
			}
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
		if($("#downmenu").is(":hidden")){
		       $("#downmenu").show();    //如果元素为隐藏,则将它显现
		}else{
		      $("#downmenu").hide();     //如果元素为显现,则将其隐藏
		}
	});
	
});
</script>