/**
 * 新建
 * Created by dxb on 12/8/2016.
 */
var v = new Vue({
    el: '#organizationInfo',
    data: {
        org: {
            name: null,
            shortName: null,
            description: null,
            tel: null,
            email: null,
            linkPerson: null,
            legalPerson: null,
            businessLicense: null,
            businessLicensePhoto: null,
            capital: null,
            taxCode: null,
            taxCodePhoto: null,
            creditCode: null,
            creditCodePhoto: null,
            orgCode: null,
            orgCodePhoto: null,
            safeProductionLicenceCode: null,
            safeProductionLicencePhoto: null,
            lpName: null,
            loginName: null
        }
    },
    methods: {
        submit: function () {

            var img1 = $('#businessLicensePhoto');
            if (img1) {
                this.org.businessLicensePhoto = img1.val();
            }
            var img2 = $('#taxCodePhoto');
            if (img2) {
                this.org.taxCodePhoto = img2.val();
            }
            var img3 = $('#creditCodePhoto');
            if (img3) {
                this.org.creditCodePhoto = img3.val();
            }
            var img4 = $('#safeProductionLicencePhoto');
            if (img4) {
                this.org.safeProductionLicencePhoto = img4.val();
            }
            var img5 = $('#orgCodePhoto');
            if (img5) {
                this.org.orgCodePhoto = img5.val();
            }


            $.ajax({
                url: fns.ajax.path("/organization"),
                type: 'post',
                data: this.org,
                success: function (r) {
                    if (!r.success) {
                        fns.ajax.error(r);
                    } else {
                        location.href = path + "/organization/" + r.data;
                    }
                }
            });
        }
    }
});