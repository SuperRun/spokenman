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
            orgClicked(treeNode.id);
        }
    }
};

function orgClicked(id) {
    showOrgInfo(id);
    updateCompany(id);

}

$(document).ready(function () {
    //加载ztree
    $.fn.zTree.init($("#ztree"), setting, tree);
    //展开树根
    var treeObj = $.fn.zTree.getZTreeObj("ztree");
    if (treeObj.getNodes()) {
        var n = treeObj.getNodes()[0];
        treeObj.expandNode(n, true);
        orgClicked(n.id)
    }

});

var v = new Vue({
    el: '#orgInfo',
    data: {
        path: path,
        org: null,
        company: null,
        show: {
            org: false
        },
        search: {
            keywd: null,
            list: null,
            resultList: null
        },
        orgDisable: null,
        page: {
            list: [],
            cur: '',
            all: '',
            per: 20
        }
    },
    methods: {
        showOrg: function () {
            this.show.org = true;
        },
        hideOrg: function () {
            this.show.org = false;
        },
        disableOrg: function (orgId) {

            if (!orgId) {
                if (this.orgDisable) {
                    orgId = this.orgDisable.id;
                } else {
                    orgId = this.org.id;
                }
            }

            $.ajax({
                url: fns.ajax.path("/organization/" + orgId),
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
        },
        searchCompany: function () {

            var keywd = this.search.keywd;
            var resultList = [];
            for (var i = 0; i < this.search.list.length; i++) {
                var obj = this.search.list[i];
                if (obj.name.indexOf(keywd) >= 0) {
                    resultList.push(obj);
                }
            }
            console.log('resultList:', resultList);
            this.search.resultList = resultList;
            v.pagetable(1);
        },
        page2: function (page) {
            if (page < 1) {
                page = 1;
            }
            var t = this.page.all;
            if (page > t) {
                page = t;
            }
            this.page.cur = page;
        },
        pagetable: function (cur) {
            var l = [];

            var totalL;
            //v.search.resultList.length>0
            if (v.search.resultList) {
                totalL = v.search.resultList;
            } else {
                totalL = v.search.list;
            }

            var start = (cur - 1) * this.page.per;
            var end = (cur ) * this.page.per;
            if (end > totalL.length) {
                end = totalL.length;
            }
            for (var i = start; i < end; i++) {
                totalL[i]['index'] = i + 1;
                l.push(totalL[i]);
            }
            console.log('l:', l);
            v.page.list = l;
        }
    },
    watch: {
        'search.list': function (val) {
            v.page.all = Math.ceil(val.length / v.page.per);
            if (v.page.all) {
                v.page.cur = 1;
            }
        },
        'search.resultList': function (val) {
            v.page.all = Math.ceil(val.length / v.page.per);
            if (v.page.all) {
                v.page.cur = 1;
            }
        },
        'page.cur': function (cur) {
            if (cur > 0) {

                v.pagetable(cur);
            }
        }
    }
});

function updateCompany(orgId) {

    $.get(fns.ajax.path("/organization/" + orgId + "/companys"), function (r) {
        if (r.success) {
            v.search.list = r.data;
            v.search.resultList = null;
            v.pagetable(1);

        }
    })
}

function showOrgInfo(orgId) {
    $.get(fns.ajax.path("/organization/" + orgId + "/org"), function (r) {
        if (!r.success) {
            console.info("org 不存在 orgId=", orgId);
            v.org = null;
        } else {
            v.org = r.data;
        }
    });

    $.get(fns.ajax.path("/organization/" + orgId + "/orgbi"), function (r) {
        if (!r.success) {
            v.company = null;
        } else {
            v.company = r.data;
        }
    });

}