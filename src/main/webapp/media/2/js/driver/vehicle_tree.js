var nodeClick = function (event, treeId, treeNode) {
    // 点击 操作
    //alert("节点 id = " + treeNode.id + " 被点击");
    //$("#orgId").val(treeNode.id);
    //console.log("onClick treeNode.id  =  " + $("#orgId").val() );
    //$("#data").submit();
    $("#includes").attr("src", path + "/hr/vehicle/list?orgId="+treeNode.id);
    //location.href = fns.ajax.path("/organization/treetest?tid=" + treeNode.id);
};


var treeObj;
var tree = {
    //data: $.parseJSON('${ztree}'),
    data: jsonZtree,
    expandId: orgId,
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


