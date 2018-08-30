/**
 * 修改机构信息
 * Created by dxb on 12/11/2016.
 */
var v = new Vue({
    el: '.content',
    data: {
        org: {
            name: null,
            shortName: null,
            tel: null,
            email: null,
            linkmanName: null,
            region: null,
            regionId: null,
            description: null,

            legalPerson: null,
            legalPersonTel: null,
            legalPersonEmail: null,
            leaderName: null,
            leaderTel: null,
            leaderEmail: null

        },
        orgBiId: orgBiId,
        orgId: orgId,
        orgType: {
            type: '',
            province: '省散装办',
            city: '市散装办',
            district: '区县散装办',
            company: '车企'
        },
        region: {
            province: {
                list: [],
                select: null
            },

            city: {
                list: [],
                select: null
            },

            district: {
                list: [],
                select: null
            }
        }
    },
    methods: {
        submit: function () {
            $.post(
                fns.ajax.path("/organization/" + this.orgId),
                this.org,
                function (r) {
                    if (r.success) {
                        location.href = path + "/organization/" + r.data;
                    } else {
                        fns.ajax.error(r);
                    }
                }
            );
        },
        regionUpdateProvince: function () {
            $.get(fns.ajax.path('/organization/region/dd'), function (r) {
                v.region.province.list = r.data;
            });
        },
        regionUpdateCity: function (provinceId) {
            $.get(fns.ajax.path('/organization/region/dd?id=' + provinceId), function (r) {
                v.region.city.list = r.data;
            });
        },
        regionUpdateDistrict: function (cityId) {
            $.get(fns.ajax.path('/organization/region/dd?id=' + cityId), function (r) {
                v.region.district.list = r.data;
            });
        },
        setRegionId: function () {
            var i;
            var obj;
            if (v.orgType.type == v.orgType.province) {
                v.org.regionId = v.region.province.select;
                for (i = 0; i < v.region.province.list.length; i++) {
                    obj = v.region.province.list[i];
                    if (obj.id == v.org.regionId) {
                        v.org.region = obj.name;
                        break;
                    }
                }
            } else if (v.orgType.type == v.orgType.city) {
                v.org.regionId = v.region.city.select;
                for (i = 0; i < v.region.city.list.length; i++) {
                    obj = v.region.city.list[i];
                    if (obj.id == v.org.regionId) {
                        v.org.region = obj.name;
                        break;
                    }
                }
            } else {
                v.org.regionId = v.region.district.select;
                for (i = 0; i < v.region.district.list.length; i++) {
                    obj = v.region.district.list[i];
                    if (obj.id == v.org.regionId) {
                        v.org.region = obj.name;
                        break;
                    }
                }
            }
            console.log(v.org.regionId);
        }

    },
    watch: {
        'region.province.select': function (val) {
            this.regionUpdateCity(val);
        },
        'region.city.select': function (val) {
            this.regionUpdateDistrict(val);
        }
    }
});


$().ready(function () {

    if (orgId) {
        $.get(fns.ajax.path("/organization/" + orgId + "/org"), function (r) {
            if (r.success) {
                v.org.name = r.data.name;
                v.org.shortName = r.data.shortName;
                v.org.tel = r.data.tel;
                v.org.email = r.data.email;
                v.org.linkmanName = r.data.linkmanName;
                v.org.region = r.data.region;
                v.org.regionId = r.data.regionId;
                v.org.description = r.data.description;

                v.orgType.type = r.data.type;
            }
        });
    }

    if (orgBiId) {
        $.get(fns.ajax.path("/organization/" + orgId + "/orgbi"), function (r) {
            if (r.success) {
                v.org.legalPerson = r.data.legalPerson;
                v.org.legalPersonTel = r.data.legalPersonTel;
                v.org.legalPersonEmail = r.data.legalPersonEmail;
                v.org.leaderName = r.data.leaderName;
                v.org.leaderTel = r.data.leaderTel;
                v.org.leaderEmail = r.data.leaderEmail;
            }
        });
    }

    v.regionUpdateProvince();


});
