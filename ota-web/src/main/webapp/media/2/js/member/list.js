/**
 * 员工列表页面
 * Created by dxb on 12/10/2016.
 */


var v = new Vue({
    el: '#app',
    data: {
        vlist: null,
        memberDel: {
            name: ''
        },
        managerId: managerId,
        modify: {
            member: {
                sfzNo: null,
                phone: null,
                name: null,
                error: null
            }
        }
    },
    methods: {
        deleteModal: function (memberId) {
            var mb;
            for (var i = 0; i < v.vlist.length; i++) {
                var m = v.vlist[i];
                if (m.id == memberId) {
                    mb = m;
                    break;
                }
            }

            this.memberDel = mb;

        },
        deleteAction: function () {
            var mid = this.memberDel.id;
            $.get(fns.ajax.path("/member/" + mid + "/rmorg"), function (r) {
                if (r.success) {
                    rmorg(mid);
                }
            });
        },
        modifyModal: function (memberId) {
            for (var i = 0; i < this.vlist.length; i++) {
                var m = this.vlist[i];
                if (memberId == m.id) {
                    this.modify.member = m;
                    break;
                }
            }
        },
        modifyMember: function () {
            var m = this.modify.member;
            $.post(fns.ajax.path("/member/" + m.id), m, function (r) {
                if (r.success) {
                    $('#modifyMember_modal').modal('hide');
                } else {
                    fns.ajax.error(r);
                }
            })

        }

    }
});

// function updateMember(m) {
//     for (var i = 0; i < v.vlist.length; i++) {
//         var mt = v.vlist[i];
//         if (mt.id == m.id) {
//             // v.vlist[i] = m;
//             mt = m;
//             break;
//         }
//     }
// }
//

function rmorg(memberId) {
    var nl = [];
    for (var i = 0; i < v.vlist.length; i++) {
        var m = v.vlist[i];
        if (m.id != memberId) {
            nl.push(m)
        }
    }
    v.vlist = nl;
}

function updateList() {

    $.ajax({
        type: 'get',
        url: fns.ajax.path('/organization/' + orgId + "/members"),
        success: function (r) {
            if (r.success) {
                v.vlist = r.data;
            } else {
                fns.ajax.error(r);
            }
        }
    })


}
