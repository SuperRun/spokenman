/**
 * 创建机构的方法
 * Created by dxb on 2016/12/10.
 */

var v = new Vue({
    el: '.content',
    data: {
        org: {
            name: null,
            shortName: null,
            tel: null,
            email: null,
            loginName: null,
            password: null,
            lpName: null,
            lpTel: null,
            linkmanName: null,

            description: null,

            legalPerson: null,
            legalPersonTel: null,
            legalPersonEmail: null,
            leaderName: null,
            leaderTel: null,
            leaderEmail: null,
            region: null,

            parentId: parentId,
            orgType: orgType
        },
        show: {
            company: orgType == '3'
        }
    },
    methods: {

        submit: function () {
            $.post(
                fns.ajax.path("/organization"),
                this.org,
                function (r) {
                    if (r.success) {
                        location.href = path + "/organization/" + r.data;
                    } else {
                        fns.ajax.error(r);
                    }
                }
            );


        }

    }


});
