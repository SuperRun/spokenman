var creationOrganization = {
    url: {
        updateParentOrg: function (orgType) {
            return path + '/organization/type/' + orgType + '/parent';
        },
        createSnb: path + '/organization',
        verifyLinkedPerson: function (loginName) {
            return path + '/member/loginname/' + loginName;
        },
        verifyLinkedPersonExsit: function (loginName) {
            console.log('loginname = ' + loginName);
            //console.log('loginname2 = '+$('#' + creationOrganization.ids.linkPerson).val());
            return path + '/member/loginname/' + "/exsit";
        },
        createSuccess: function (orgId) {
            return path + '/organization/' + orgId;
        }
        ,
        getChilds: function (orgId) {
            return path + '/organization/' + orgId + "/childs";
        },
        getdetail: function (orgId) {
            return path + "/organization/" + orgId;
        }
    },
    ids: {
        //表单
        organizationInfo: 'organizationInfo',


        //父级组织信息
        orgType: 'orgType',
        provinceOrgOrg: 'provinceOrgOrg',//省机构
        districtOrg: 'districtOrg',//区机构
        cityOrg: 'cityOrg',//市机构

        //基本信息
        name: 'orgName',//名称
        shortName: 'shortName',//简称
        description: 'orgDescribe',
        linkPerson: 'directorId',
        tel: 'directorTel',
        email: 'directorEmail',

        //车企信息
        legalPerson: 'lrName',
        businessLicense: 'licenseNum',
        taxCode: 'taxNum',
        creditCode: 'creditNum',
        orgCode: 'orgNum',
        safeProductionLicenceCode: 'safetyNum',
        capital: 'capital',
        businessLicensePhoto: 'businessLicensePhoto',
        taxCodePhoto: 'taxCodePhoto',
        creditCodePhoto: 'creditCodePhoto',
        orgCodePhoto: 'orgCodePhoto',
        safeProductionLicensePhoto: 'safeProductionLicensePhoto',


        //其他信息
        parentOrgDiv: 'parentOrgDiv', //父级组织div
        provinceOrgDiv: 'provinceOrgDiv',//父级组织div
        cityOrgDiv: 'cityOrgDiv',//市级机构下div
        districtOrgDiv: 'districtOrgDiv',//区机构div

        carCompanyInputDiv: 'carCompany'


    },
    funcs: {
        freshProvinceOrg: function (selectElementId) {
            var orgType = 1;
            $.ajax({
                type: 'GET',
                url: creationOrganization.url.updateParentOrg(orgType),
                dataType: 'json',
                success: function (r) {
                    console.log(r);
                    if (r.success) {
                        var parentId = $('#' + selectElementId);
                        var list = r.data;
                        var selectStr = '';
                        for (var i = 0; i < list.length; i++) {
                            selectStr += ('<option value="' + list[i].id + '">'
                            + list[i].name + '(' + list[i].shortName + ')' + '</option>');
                        }
                        parentId.html(selectStr);
                        parentId.change();
                    } else {
                        // 提示出错
                        alert('请求错误，请刷新重试 (' + r.error + ')');
                    }
                },
                error: function (r) {
                    console.log(r);
                    alert('请求错误，请联系管理员 (' + r.statusText + ')');

                }
            });
        },
        freshChildSelect: function (selectElementId, parentOrgId) {
            $.ajax({
                type: 'GET',
                url: creationOrganization.url.getChilds(parentOrgId),
                success: function (r) {
                    console.log(r);
                    if (r.success) {
                        var parentId = $('#' + selectElementId);
                        var list = r.data;
                        var selectStr = '';
                        for (var i = 0; i < list.length; i++) {
                            selectStr += ('<option value="' + list[i].id + '">'
                            + list[i].shortName + '(' + list[i].name + ')' + '</option>');
                        }
                        parentId.html(selectStr);
                        parentId.change();
                    } else {
                        // 提示出错
                        alert('请求错误，请刷新重试 (' + r.error + ')');
                    }
                },
                error: function (r) {
                    console.log(r);
                    alert('请求错误，请联系管理员 (' + r.statusText + ')');
                }
            });
        },
        createOrg: function (parentId, name, shortName,
                             description, linkPerson, tel, email,
                             legalPerson, businessLicense, businessLicensePhoto,
                             taxCode, taxCodePhoto, creditCode, creditCodePhoto,
                             orgCode, orgCodePhoto, safeProductionLicense,
                             safeProductionLicensePhoto, capital) {

            var orginfo = {
                parentId: parentId,
                name: name,
                shortName: shortName,
                description: description,
                linkPerson: linkPerson,
                tel: tel,
                email: email
            };
            console.log('开始提交机构 ： '+orginfo);


            if (capital != null && capital != '') {
                var cq = {
                    legalPerson: legalPerson,
                    businessLicense: businessLicense,
                    businessLicensePhoto: businessLicensePhoto,
                    capital: capital,
                    taxCode: taxCode,
                    taxCodePhoto: taxCodePhoto,
                    creditCode: creditCode,
                    creditCodePhoto: creditCodePhoto,
                    orgCode: orgCode,
                    orgCodePhoto: orgCodePhoto,
                    safeProductionLicenceCode: safeProductionLicense,
                    safeProductionLicencePhoto: safeProductionLicensePhoto
                };
                console.log('是车企，加添以下信息 = '+cq);
                for(var temp in  cq){
                    orginfo[temp] = cq[temp];
                }
                console.log('开始提交机构2 ： '+orginfo);
            }

            //alert('开始提交机构');
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: creationOrganization.url.createSnb,
                data: orginfo,
                success: function (r) {
                    console.log(r);
                    if (r.success) {
                        location.href = creationOrganization.url.getdetail(r.data);
                    } else {
                        alert(r.error);
                    }
                },
                error: function (r) {
                    console.log(r);
                    alert('失败 ' + r.statusText);
                }
            });
        },
        updateBelong : function () {
            var parentOrgDiv =  $('#'+creationOrganization.ids.parentOrgDiv);
            var provinceOrgDiv = $('#'+creationOrganization.ids.provinceOrgDiv);
            var cityOrgDiv = $('#'+creationOrganization.ids.cityOrgDiv);
            var districtOrgDiv = $('#'+creationOrganization.ids.districtOrgDiv);
            var carCompanyInputDiv = $('#'+creationOrganization.ids.carCompanyInputDiv);

            //更新省元素
            creationOrganization.funcs.freshProvinceOrg(creationOrganization.ids.provinceOrgOrg);


            var orgType = $("#" + creationOrganization.ids.orgType).val();
            if (orgType == 3) {
                carCompanyInputDiv.show(200);
                parentOrgDiv.show(100);
                provinceOrgDiv.show(200);
                cityOrgDiv.show(200);
                districtOrgDiv.show(200);
            } else {
                carCompanyInputDiv.hide(100);
            }
            if (orgType == 0) {
                //创建省机构的时候隐藏上级机构
                parentOrgDiv.hide(100);
                provinceOrgDiv.hide(200);
                cityOrgDiv.hide(100);
                districtOrgDiv.hide(100);
            } else if (orgType == 1) {
                //创建市级机构
                parentOrgDiv.show(100);
                provinceOrgDiv.show(200);
                cityOrgDiv.hide(100);
                districtOrgDiv.hide(100);
            } else if (orgType == 2) {
                //创建区级机构
                parentOrgDiv.show(100);
                provinceOrgDiv.show(200);
                cityOrgDiv.show(200);
                districtOrgDiv.hide(100);
            }
        }

    }
};
