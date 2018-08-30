<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../2/include/_taglib_includes.jsp"%>

<!--对某次考试报名人员进行报名管理-->
<!DOCTYPE html>
<html>
<head lang="en">
<%@include file="../../../2/include/_html_head.jsp"%>
<link
	href="http://cdn.bootcss.com/zTree.v3/3.5.26/css/zTreeStyle/zTreeStyle.min.css"
	rel="stylesheet">
<script
	src="http://cdn.bootcss.com/zTree.v3/3.5.26/js/jquery.ztree.core.min.js"></script>
<link
	href="<%=basePath%>media/2/css/exam_member_registrationManagement.css"
	type="text/css" rel="stylesheet" />
<title>基于B/新闻发言人在线学习培训平台--报名管理</title>
</head>
<body>

	<!--header start-->
	<%@include file="../../include/_member_header.jsp"%>
	<!--header end-->

	<!--main content start-->

	<!--message start-->
	<div class="content container">
		<div class="row">
			<div class="col-md-4">
				<div id="ztree" class="ztree" style="margin-bottom: 500px;"></div>
			</div>
			<div class="col-md-8">
				<input id="examId" style="display:none" value="${examId }">
				<input id="orgId" style="display:none" value="${orgId }">
				<iframe id="includes"
					src="<%=basePath%>exam/member/signup/${examId}/content?tid=${orgId}"
					frameborder="0" height="1440" width="770" scrolling="no">
				</iframe>
			</div>

		</div>
	</div>
	<!--message end-->

	<!--main content end-->

	<!--footer start-->
	<%@include file="../../include/_html_body.jsp"%>
	<!--footer end-->


	<script>
		var nodeClick = function(event, treeId, treeNode) {
			console.log("点击节点");
			var children = tree.methods.getNodeById(treeNode.id).children;
			console.log(children);
			if (children.length <= 0) {
				$("#includes").attr(
						"src",
						path + "/exam/member/signup/" + $("#examId").val()
								+ "/content?tid=" + treeNode.id);
			}
		};

		var treeObj;
		var tree = {
			data : $.parseJSON('${ztree}'),
			expandId : '${orgId}',
			setting : {
				view : {
					showIcon : false,
					expandSpeed : ''
				},
				callback : {
					onClick : nodeClick
				}
			},
			methods : {
				getTreeObj : function() {
					if (!treeObj)
						treeObj = $.fn.zTree.getZTreeObj("ztree");
					return treeObj;
				},
				getNodeById : function(id) {
					return tree.methods.getTreeObj().getNodeByParam('id', id);
				},
				expandNode : function(id) {
					//
					console.log('展开节点', id);
					if (id) {
						var node = tree.methods.getNodeById(id);
						tree.methods.getTreeObj().expandNode(
								node.getParentNode(), true);
						tree.methods.getTreeObj().expandNode(node, true)
					}

				}
			}
		};

		$().ready(
				function() {
					//加载ztree
					$.fn.zTree.init($("#ztree"), tree.setting, tree.data);
					//展开树根
					var treeObj = tree.methods.getTreeObj();
					if (treeObj.getNodes()) {
						var n = treeObj.getNodes()[0];
						treeObj.expandNode(n, true);
					}

					//展开上次点击
					if (tree.expandId) {
						tree.methods.expandNode(tree.expandId);
					}

					$("#includes").attr(
							"src",
							path + "/exam/member/signup/" + $("#examId").val()
									+ "/content?tid=" + $("#orgId").val());

				});
	</script>
</body>
</html>