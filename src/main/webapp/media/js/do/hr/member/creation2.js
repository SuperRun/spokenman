/**
 * Created by dxb on 2016/8/12.
 * 创建新用户
 */
var creation = {
    url: {
        getOrgIdInput: function (orgType) {
            return path + '/organization/type/' + orgType;
        },
        getDepIdInput: function (orgId) {
            return path + '/organization/' + orgId + '/department';
        },
        createNew: path + '/member'


    },
    funcs: {
        getOrgIdInput: function (orgType, orgIdInput, depIdInput) {
            $.ajax({
                type: 'GET',
                dataType: 'json',
                url: creation.url.getOrgIdInput(orgType),
                success: function (r) {
                    console.log(r);
                    if (r.success) {
                        list = r.data;
                        opStr = "";
                        for (var i = 0; i < list.length; i++) {
                            opStr += '<option value="' + list[i].id + '">' + list[i].shortName + '(' + list[i].name + ')' + '</option>';
                        }
                        orgIdInput.html(opStr);
                        if (list.length > 0) {
                            creation.funcs.getDepIdInput(orgIdInput.val(), depIdInput);
                        }
                    } else {
                        console.log('失败');
                    }
                },
                error: function (r) {
                    console.log(r);
                }

            });
        },
        getDepIdInput: function (orgId, depIdInput) {
            $.ajax({
                type: 'GET',
                dataType: 'json',
                url: creation.url.getDepIdInput(orgId),
                success: function (r) {
                    console.log(r);
                    if (r.success) {
                        list = r.data;
                        opStr = "";
                        for (var i = 0; i < list.length; i++) {
                            opStr += '<option value="' + list[i].id + '">' + list[i].shortName + '(' + list[i].name + ')' + '</option>';
                        }
                        depIdInput.html(opStr);

                    } else {
                        console.log('失败');
                    }
                },
                error: function (r) {
                    console.log(r);
                }

            });
        },
        createNew: function (name, phone, sfzNo, orgId, depId, loginName) {
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: creation.url.createNew,
                data: {
                    name: name,
                    phone: phone,
                    sfzNo: sfzNo,
                    orgId: orgId,
                    depId: depId,
                    loginName: loginName
                },
                success: function (r) {
                    console.log(r);
                    //TODO
                },
                error: function (r) {
                    //TODO
                    console.log(r);
                }

            });
        }
    },
    alertMsg: {
        nameInputSpan: {
            show: function (msg, clazz) {
                common.alertMsg('nameInputSpan', msg, clazz);
            },
            hide: function () {
                common.alertMsgHide('nameInputSpan');
            }
        },
        phoneInputSpan: {
            show: function (msg, clazz) {
                common.alertMsg('phoneInputSpan', msg, clazz);
            },
            hide: function () {
                common.alertMsgHide("phoneInputSpan");
            }
        },
        sfzNoInputSpan: {
            show: function (msg, clazz) {
                common.alertMsg('sfzNoInputSpan', msg, clazz);
            },
            hide: function () {
                common.alertMsgHide("sfzNoInputSpan");
            }
        }
    }
};

$(document).ready(function () {
    var nameInput = $('#name');
    var flagNameInput = false;
    var phoneInput = $('#phone');
    var flagPhoneInput = false;
    var sfzNoInput = $('#sfzNo');
    var flagSfzNoInput = false;
    var loginNameInput = $('#loginName');
    var flagLoginNameInput = false;


    var orgTypeInput = $('#orgType');
    var orgIdInput = $('#orgId');
    var depIdInput = $('#depId');


    creation.funcs.getOrgIdInput(orgTypeInput.val(), orgIdInput, depIdInput);
    orgTypeInput.change(function () {
        creation.funcs.getOrgIdInput(orgTypeInput.val(), orgIdInput, depIdInput);
    });

    nameInput.change(function () {
        creation.alertMsg.nameInputSpan.hide();
        if (common.validate.input(nameInput, 2)) {
            flagNameInput = true;
        } else {
            flagNameInput = false;
            creation.alertMsg.nameInputSpan.show('名字必须2位以上', 'red');
        }
    });


    phoneInput.change(function () {
        creation.alertMsg.phoneInputSpan.hide();
        if (common.validate.input(phoneInput, 8)) {
            flagPhoneInput = true;
        } else {
            flagNameInput = false;
            creation.alertMsg.phoneInputSpan.show('手机号必须8位以上', 'red');
        }
    });

    sfzNoInput.change(function () {
        creation.alertMsg.sfzNoInputSpan.hide();
        if (common.validate.input(sfzNoInput, 15)) {
            flagSfzNoInput = true;
        } else {
            flagSfzNoInput = false;
            creation.alertMsg.sfzNoInputSpan.show('身份证需要15位以上', 'red');
        }
    });


    var createOrgBtn = $('#createOrg');
    createOrgBtn.click(function () {
        if (flagNameInput && flagPhoneInput && flagSfzNoInput) {
            creation.funcs.createNew(nameInput.val(), phoneInput.val(), sfzNoInput.val(), orgIdInput.val(), depIdInput.val(), loginNameInput.val());
        } else {
            //TODO
        }
    });
});