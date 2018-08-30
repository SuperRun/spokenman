<%--
  Created by IntelliJ IDEA.
  User: dxb
  Date: 12/7/2016
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../include/_taglib_includes.jsp" %>
<html>
<head>
    <%@include file="../../include/_html_head.jsp" %>
    <title>树演示_基于B/S程序的无纸化网络考试系统</title>
    <link href="http://cdn.bootcss.com/zTree.v3/3.5.26/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet">
</head>
<body>
<%@include file="../../include/_member_header.jsp" %>
<div class="container">
    <div id="ztree" class="ztree" style="margin-bottom: 500px"></div>
</div>

<%@include file="../../include/_html_body.jsp" %>
<script src="http://cdn.bootcss.com/zTree.v3/3.5.26/js/jquery.ztree.core.min.js"></script>
<script>
    var nodeClick = function (event, treeId, treeNode) {
        // 点击 操作
        alert("节点 id = " + treeNode.id + " 被点击");
        location.href = fns.ajax.path("/organization/treetest?tid=" + treeNode.id);
    };

    var treeObj;
    var tree = {
        data: $.parseJSON('${ztree}'),
        expandId: '${orgId}',
        setting: {
            view: {
                showIcon: false,
                expandSpeed: ''
            },
            callback: {
                onClick: nodeClick
            }
        },
        methods: {
            getTreeObj: function () {
                if (!treeObj) treeObj = $.fn.zTree.getZTreeObj("ztree");
                return treeObj;
            },
            getNodeById: function (id) {
                return tree.methods.getTreeObj().getNodeByParam('id', id);
            },
            expandNode: function (id) {
//
                console.log('展开节点', id);
                if (id) {
                    var node = tree.methods.getNodeById(id);
                    tree.methods.getTreeObj().expandNode(node.getParentNode(), true);
                    tree.methods.getTreeObj().expandNode(node, true)
                }

            }
        }
    };


    $().ready(function () {
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

    });


</script>
</body>
</html>
