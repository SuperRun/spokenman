var modifyMember = {
    url: {
        getFriends: function (orgId) {
            return path + '/organization/' + orgId + '/friends';
        },
        getDepFriends: function (depId) {
            return path + '/department/' + depId + '/friends';
        },
        deleteMember: function (memberId) {
            return path + "/member/" + memberId;
        },
        dismissMember: function (memberId) {
            return path + "/member/" + memberId + '/dismiss';
        }
    },
    funcs: {
        updateOrgBelong: function (orgType, orgId) {
            //orgType 整形
            //orgId 整形
            console.log('updateOrgBelong : ', orgType, orgId);
            switch (orgType) {
                case '0':
                {
                    //省机构
                    modifyMember.funcs.refreshFriends(creationMember.ids.provinceOrg, orgId);
                    break;
                }
                case '1':
                {
                    //市机构
                    modifyMember.funcs.refreshFriends(creationMember.ids.cityOrg, orgId);
                    break;
                }
                case '2':
                {
                    //区县机构
                    modifyMember.funcs.refreshFriends(creationMember.ids.districtOrg, orgId);
                    break;
                }
                case '3':
                {
                    //车企
                    modifyMember.funcs.refreshFriends(creationMember.ids.companyOrg, orgId);
                    break;
                }
                default:
                {
                    break;
                }
            }
        },
        refreshFriends: function (selectElementId, orgId) {
            $.ajax({
                type: 'GET',
                url: modifyMember.url.getFriends(orgId),
                success: function (r) {
                    console.log(r);
                    if (r.success) {
                        console.log('refreshParentSelect ' + r);
                        var parentId = $('#' + selectElementId);
                        var list = r.data;
                        var selectStr = '';
                        for (var i = 0; i < list.length; i++) {
                            selectStr += ('<option value="' + list[i].id + '">'
                            + list[i].name + '(' + list[i].shortName + ')' + '</option>');
                        }
                        parentId.html(selectStr);

                        parentId.val(orgId).change();
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
        updateDepBelong: function (depId) {
            modifyMember.funcs.refreshDepFriends(creationMember.ids.department, depId);
        },
        refreshDepFriends: function (selectElementId, depId, selectedDepId) {
            $.ajax({
                type: 'GET',
                url: modifyMember.url.getDepFriends(depId),
                success: function (r) {
                    console.log(r);
                    if (r.success) {
                        console.log('refreshParentSelect ' + r);
                        var parentId = $('#' + selectElementId);
                        var list = r.data;
                        var selectStr = '<option value="-1">无</option>';
                        for (var i = 0; i < list.length; i++) {
                            selectStr += ('<option value="' + list[i].id + '">'
                            + list[i].name + '</option>');
                        }
                        parentId.html(selectStr);
                        if (selectedDepId) {
                            parentId.val(selectedDepId).change()
                        } else {
                            parentId.val(depId).change();
                        }
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
        deleteMember: function (memberId) {
            $.ajax({
                type: 'DELETE',
                url: modifyMember.url.deleteMember(memberId),
                success: function (r) {
                    if (r.success) {
                        console.log('删除成功');
                        location.href = modifyMember.url.deleteMember(memberId);
                    } else {
                        console.log('删除失败');
                        alert('失败 ' + r.error);
                    }
                },
                error: function (r) {
                    console.log('删除req失败');
                    alert('失败 ' + r.statusText);
                }
            })
        },
        undoDeleteMember: function (memberId) {
            $.ajax({
                type: 'PUT',
                url: modifyMember.url.deleteMember(memberId),
                success: function (r) {
                    if (r.success) {
                        console.log('恢复成功');
                        location.href = modifyMember.url.deleteMember(memberId);
                    } else {
                        console.log('恢复失败');
                        alert('失败 ' + r.error);
                    }
                },
                error: function (r) {
                    console.log('恢复req失败');
                    alert('失败 ' + r.statusText);
                }
            })
        },
        dismissMember: function (memberId) {
            $.ajax({
                type: 'GET',
                url: modifyMember.url.dismissMember(memberId),
                success: function (r) {
                    if (r.success) {
                        console.log('恢复成功');
                        location.href = modifyMember.url.deleteMember(memberId);
                    } else {
                        console.log('恢复失败');
                        alert('失败 ' + r.error);
                    }
                },
                error: function (r) {
                    console.log('恢复req失败');
                    alert('失败 ' + r.statusText);
                }
            })
        }
    }

};
