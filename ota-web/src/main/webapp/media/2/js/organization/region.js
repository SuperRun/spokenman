/**
 * Created by dxb on 2016/12/15.
 */
//ztree设置
var nodeClick = function (event, treeId, treeNode) {
    v.select(treeNode);
};
var treeObj;
function getTreeObj() {
    if (!treeObj) {
        treeObj = $.fn.zTree.getZTreeObj("ztree");
    }
    return treeObj;
}

function getNodeById(id) {
    return getTreeObj().getNodeByParam('id', id);
}

function addNode(parentId, node) {
    var parentNode = getNodeById(parentId);
    getTreeObj().addNodes(parentNode, node);
}
function removeNode(id) {
    getTreeObj().removeNode(getNodeById(id));
}
function updateNode(node) {
    var n = getNodeById(node.id);
    n.name = node.valueStr;
    getTreeObj().updateNode(n);
    v.select(n);
}
function initZtree(tree) {
    var setting = {
        view: {
            showIcon: false
        },
        callback: {
            onClick: nodeClick
        }
    };

    //加载ztree
    $.fn.zTree.init($("#ztree"), setting, tree);
    //展开
    getTreeObj().expandAll(true);
}

$(document).ready(function () {
    $.get(fns.ajax.path("/organization/region/tree"), function (r) {
        if (r.success) {
            // v.select(r.data);
            if (!r.data) {
                r.data = [];
            }
            initZtree(r.data);
            return;
        }
        fns.ajax.error(r);
    })
});


var v = new Vue({
    el: '.content',
    data: {
        node: {
            create: {
                parentId: '',
                valueStr: ''
            },
            selected: {
                id: '',
                name: '',
                valueStr: ''

            }
        }
    },
    methods: {
        create: function () {
            var n = this.node.create;
            $.post(fns.ajax.path("/organization/region"), n, function (r) {
                if (r.success) {
                    var newNode = {
                        id: r.data,
                        name: n.valueStr
                    };
                    addNode(n.parentId, newNode);
                    $('#create_Modal').modal('hide');
                    n.valueStr = '';
                }
            })
        },
        select: function (node) {
            if (node) {
                this.node.selected.id = node.id;
                this.node.selected.name = node.name;
                this.node.selected.valueStr = node.name;
                this.node.create.parentId = node.id;
            }
        },
        remove: function () {
            $.get(fns.ajax.path("/mdd/" + this.node.selected.id + "/delete"), function (r) {
                // location.reload();
                removeNode(r.data);
            });
        },
        modify: function () {
            var n = this.node.selected;
            $.post(fns.ajax.path('/mdd/' + this.node.selected.id + "/modify"), n, function (r) {
                updateNode(n);
            });
        }

    }

});