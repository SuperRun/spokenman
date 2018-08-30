/**
 * Created by dxb on 2016/12/9.
 */

var orgInfo = new Vue({
    el: '#app',
    data: {
        org: null,
        company: null,
        driver: {
            list: [],
            select: null
        }
    },
    methods: {
        setDriver: function () {
            console.log('setting');
            if (this.company && this.company.id) {
                $.ajax({
                    url: fns.ajax.path("/organization/driver"),
                    type: 'post',
                    data: {
                        orgBiId: this.company.id,
                        driverId: this.driver.select
                    },
                    success: function (r) {
                        if (r.success) {
                            location.reload();
                        } else {
                            fns.ajax.error(r);
                        }
                    }
                })
            }
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

    $.get(fns.ajax.path("/organization/" + orgId + "/orgbi"), function (r) {
        if (!r.success) {
            console.info("orgbi 不存在 orgId=", orgId);
            orgInfo.company = null;
        } else {
            orgInfo.company = r.data;
            getDriverList(orgId);
        }
    });

}
function getDriverList(orgId) {
    $.get(fns.ajax.path('/hr/vehicle/getVehicleDriver/' + orgId), function (r) {
        if (!r.success) {
            console.info("orgbi 不存在 orgId=", orgId);
            orgInfo.driver.list = null;
        } else {
            orgInfo.driver.list = r.data;
        }
    })
}