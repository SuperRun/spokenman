var creationMember = {
    ids: {
        orgType: 'orgType',
        provinceOrg: 'provinceOrg',
        cityOrg: 'cityOrg',
        districtOrg: 'districtOrg',
        companyOrg: 'companyOrg',
        companyOrgDiv: 'companyOrgDiv',
        districtOrgDiv: 'districtOrgDiv',
        cityOrgDiv: 'cityOrgDiv',
        provinceOrgDiv: 'provinceOrgDiv',
        department: 'department',
        orgDiv: 'orgDiv',
        departmentDiv: 'departmentDiv'
    },
    funcs: {
        orgDiv: function (orgType) {
            console.log('orgType=' + orgType);
            switch (orgType) {
                case '0':
                {
                    //省机构
                    creationMember.funcs.orgDivTrigger(true, true);
                    break;
                }
                case '1':
                {
                    //市机构
                    creationMember.funcs.orgDivTrigger(true, true, true);
                    break;
                }
                case '2':
                {
                    //区县机构
                    creationMember.funcs.orgDivTrigger(true, true, true, true);
                    break;
                }
                case '3':
                {
                    //车企
                    creationMember.funcs.orgDivTrigger(true, true, true, true, true);
                    break;
                }
                default:
                {
                    //其他
                    creationMember.funcs.orgDivTrigger(false);
                    break;
                }
            }
        },
        orgDivTrigger: function (orgDiv, provinceOrgDiv, cityOrgDiv, districtOrgDiv, companyOrgDiv) {
            console.log(orgDiv, provinceOrgDiv, cityOrgDiv, districtOrgDiv, companyOrgDiv);
            var o = $('#' + creationMember.ids.orgDiv);
            var p = $('#' + creationMember.ids.provinceOrgDiv);
            var ci = $('#' + creationMember.ids.cityOrgDiv);
            var d = $('#' + creationMember.ids.districtOrgDiv);
            var co = $('#' + creationMember.ids.companyOrgDiv);
            var department = $('#' + creationMember.ids.departmentDiv);
            orgDiv ? o.show(200) : o.hide(100);
            orgDiv ? department.show(200) : department.hide(100);
            provinceOrgDiv ? p.show(200) : p.hide(100);
            cityOrgDiv ? ci.show(200) : ci.hide(100);
            districtOrgDiv ? d.show(200) : d.hide(100);
            companyOrgDiv ? co.show() : co.hide();
        },
        refreshDep: function (selectElementId, orgId) {
            console.log('refreshDep被调用 ', selectElementId, orgId, creationMember.url.refreshDep(orgId));
            $.ajax({
                type: 'GET',
                url: creationMember.url.refreshDep(orgId),
                success: function (r) {
                    if (r.success) {
                        var selectElement = $('#' + selectElementId);
                        var appendStr = '<option value="-1">无</option>';
                        var list = r.data;
                        for (var i = 0; i < list.length; i++) {
                            var item = list[i];
                            appendStr += '<option value="' + item.id + '">' + item.name + '</option>';
                        }
                        selectElement.html(appendStr);
                    } else {
                        alert(r.error);
                    }
                },
                error: function (r) {
                    alert('失败 ' + r.statusText);
                }
            });
        },
        trimOrgTypeId: function (id) {
            return id.substr(7, 1);
        }
    },
    url: {
        refreshDep: function (orgId) {
            return path + "/organization/" + orgId + "/department";
        }
    }
};
