/**
 * Created by dxb on 12/11/2016.
 */
var v = new Vue({
    el: '#app',
    methods: {
        modifyMember: function () {
            $.post(fns.ajax.path("/member/modify"), this.modify.member,

                function (r) {
                    if (!r.success) {
                        setMemberModifyError(r.error);
                    } else {
                        location.reload();
                    }
                }
            )
        }
    },
    data: {
        modify: {
            member: {
                sfzNo: msfzNo,
                phone: mphone,
                name: mname,
                error: null
            }
        }
    }

});

function setMemberModifyError(info) {
    v.modify.member.error = info;
}