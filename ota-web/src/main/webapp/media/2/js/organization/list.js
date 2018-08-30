/**
 * Created by dxb on 2016/12/7.
 */


//ztree设置
var setting = {
    view: {
        showIcon: false
    },
    callback: {
        onClick: function (event, treeId, treeNode) {
            showOrgInfo(treeNode.id);
        }
    }
};

$(document).ready(function () {
    //加载ztree
    $.fn.zTree.init($("#ztree"), setting, tree);
    //展开树根
    var treeObj = $.fn.zTree.getZTreeObj("ztree");
    if (treeObj.getNodes()) {
        var n = treeObj.getNodes()[0];
        treeObj.expandNode(n, true);
        showOrgInfo(n.id);
    }
});

var orgInfo = new Vue({
    el: '#orgInfo',
    data: {
        org: null

    },
    methods: {
        disableOrg: function () {
            $.ajax({
                url: fns.ajax.path("/organization/" + this.org.id),
                type: 'delete',
                success: function (r) {
                    if (!r.success) {
                        fns.ajax.error(r);
                    } else {
                        alert('删除成功，页面即将重载');
                        location.reload();
                    }
                }
            })
        }
    }

});

function showOrgInfo(orgId) {
    $.get(fns.ajax.path("/organization/" + orgId + "/org"), function (r) {
        if (!r.success) {
            console.info("org 不存在 orgId=", orgId);
            orgInfo.org = null;
        } else {
            orgInfo.org = r.data;
        }
    });

}