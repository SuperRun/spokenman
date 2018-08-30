var modifyOrg={
    url:{
        modify: function (orgId) {
            return path+'/organization/'+orgId;
        },

        getParentFriends: function (orgId) {
            return path+'/organization/'+orgId+'/parent/friends';
        }

    },
    funcs:{
        modifyOrg: function (parentId, name, shortName,
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

//TODO 是车企的条件
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
                    safeProductionLicencePhoto: safeProductionLicensePhoto,
                    orgbiId:orgbiId
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
                url: modifyOrg.url.modify(orgId),
                data: orginfo,
                success: function (r) {
                    console.log(r);
                    if (r.success) {
                        location.href = creationOrganization.url.getdetail(r.data);
                    } else {
                        alert('失败 ' + r.error);
                    }
                },
                error: function (r) {
                    console.log(r);
                    alert('失败 ' + r.statusText);
                }
            });
        },
        updateBelong: function (parentOrgId) {

            var parentOrgDiv = $('#' + creationOrganization.ids.parentOrgDiv);
            var provinceOrgDiv = $('#' + creationOrganization.ids.provinceOrgDiv);
            var cityOrgDiv = $('#' + creationOrganization.ids.cityOrgDiv);
            var districtOrgDiv = $('#' + creationOrganization.ids.districtOrgDiv);
            var carCompanyInputDiv = $('#' + creationOrganization.ids.carCompanyInputDiv);

            var provinceOrgSelect = $('#' + creationOrganization.ids.provinceOrgOrg);
            var cityOrgSelect = $('#' +creationOrganization.ids.cityOrg);
            var districtOrgSelect = $('#' +creationOrganization.ids.districtOrg);

            var orgType = $("#" + creationOrganization.ids.orgType).val();

            console.log('updateBelong orgType = '+orgType);

            if (orgType == 3) {
                carCompanyInputDiv.show(200);
                parentOrgDiv.show(100);
                provinceOrgDiv.show(200);
                cityOrgDiv.show(200);
                districtOrgDiv.show(200);
                modifyOrg.funcs.refreshParentSelect(creationOrganization.ids.districtOrg, orgId);

                districtOrgSelect.val(parentOrgId);
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
                //市级机构
                parentOrgDiv.show(100);
                provinceOrgDiv.show(200);
                cityOrgDiv.hide(100);
                districtOrgDiv.hide(100);
                modifyOrg.funcs.refreshParentSelect(creationOrganization.ids.provinceOrgOrg,orgId);
                provinceOrgSelect.val(parentOrgId);

            } else if (orgType == 2) {
                //创建区级机构
                parentOrgDiv.show(100);
                provinceOrgDiv.show(200);
                cityOrgDiv.show(200);
                districtOrgDiv.hide(100);
                modifyOrg.funcs.refreshParentSelect(creationOrganization.ids.cityOrg, orgId);
                cityOrgSelect.val(parentOrgId);


            }
        },
        refreshParentSelect: function (selectElementId, orgId) {
            $.ajax({
                type: 'GET',
                url: modifyOrg.url.getParentFriends(orgId),
                success: function (r) {
                    console.log(r);
                    if (r.success) {
                        console.log('refreshParentSelect '+r);
                        var parentId = $('#' + selectElementId);
                        var list = r.data;
                        var selectStr = '';
                        for (var i = 0; i < list.length; i++) {
                            selectStr += ('<option value="' + list[i].id + '">'
                            + list[i].name + '(' + list[i].shortName + '(' + '</option>');
                        }
                        parentId.html(selectStr);
                        //parentId.find("option[value='"+ r.parentId +"']").attr("selected",'selected');

                        parentId.val(r.parentId).change();
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
        }
    }

};
