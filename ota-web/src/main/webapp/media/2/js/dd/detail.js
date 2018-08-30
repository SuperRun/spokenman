var v = new Vue({
    el: "#app",
    data: {
        vlist: vlist,
        vparent: vparent,
        ddCreate: {valueStr: "", remark: ""},
        ddModify: {valueStr: "", remark: ""},
        ddDelete: {id: null, valueStr: null}
    },
    methods: {
        create: function () {
            var t = this.ddCreate;
            $.post(fns.ajax.path("/mdd/" + this.vparent.id), this.ddCreate, function (r) {
                if (r.success) {
                    pushVlist(r.data, t.valueStr, t.remark)
                }
            })
        }, createModal: function () {
            this.ddCreate.valueStr = "";
            this.ddCreate.remark = ""
        }, modifyModal: function (ddId) {
            for (var i = 0; i < this.vlist.length; i++) {
                var ddtemp = this.vlist[i];
                if (ddId == ddtemp.id) {
                    this.ddModify = ddtemp;
                    console.log(ddtemp.id);
                    break
                }
            }
        }, modify: function () {
            $.post(fns.ajax.path("/mdd/" + this.ddModify.id + "/modify"), {
                valueStr: this.ddModify.valueStr,
                remark: this.ddModify.remark
            }, function (r) {
                if (r.success) {
                }
            })
        }, deleteModal: function (ddId) {
            for (var i = 0; i < this.vlist.length; i++) {
                var ddtemp = this.vlist[i];
                if (ddId == ddtemp.id) {
                    this.ddDelete = ddtemp;
                    break
                }
            }
        }, deleteAction: function () {
            var nlist = [];
            for (var i = 0; i < this.vlist.length; i++) {
                var ddtemp = this.vlist[i];
                if (ddtemp.id != this.ddDelete.id) {
                    nlist.push(ddtemp)
                }
            }
            this.vlist = nlist;
            $.get(fns.ajax.path("/mdd/" + this.ddDelete.id + "/delete"))
        }
    }
});
function pushVlist(id, valueStr, remark) {
    v.vlist.push({id: id, valueStr: valueStr, remark: remark})
};