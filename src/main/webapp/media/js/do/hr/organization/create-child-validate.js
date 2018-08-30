$().ready(function () {
    //        表单验证
//        $('#'+creationOrganization.ids.organizationInfo).validate(creationOrganization.validateData);

    $('#' + creationOrganization.ids.organizationInfo).validate({
        highlight: function (element, errorClass, validClass) {
            console.log($(element).parent());
            $(element).parent().addClass(errorClass).removeClass(validClass);
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parent().addClass(validClass).removeClass(errorClass);
        },
        validClass: 'has-success',
        errorClass: 'has-error',
        rules: {
            orgName: {
                required: true,
                maxlength: 50
            },
            shortName: {
                required: true,
                maxlength: 20
            },
            orgDescribe: {
                maxlength: 500
            },
            directorId: {
                remote: creationOrganization.url.verifyLinkedPersonExsit()
            },
            directorTel: {
                required: true
            },
            directorEmail: {
                required: true,
                email: true
            },
            capital: {
                number: true
            }
        },
        messages: {
            orgName: {
                required: "未输入",
                maxlength: "50字内"
            },
            shortName: {
                required: '未输入',
                maxlength: '20字内'
            },
            orgDescribe: {
                maxlength: '500字内'
            },
            directorId: {
                remote: '用户不存在'
            },
            directorTel: {
                required: '未输入'
            },
            directorEmail: {
                required: '未输入',
                email: '格式不正确'
            },
            capital: {
                number: '格式不正确'
            }
        },
        submitHandler: function () {
            console.log("tijiao");

            var orgType = orgTypeVal;
            var parentId = orgId;


            var name = $('#' + creationOrganization.ids.name).val();
            var shortName = $('#' + creationOrganization.ids.shortName).val();
            var description = $('#' + creationOrganization.ids.description).val();
            var linkPerson = $('#' + creationOrganization.ids.linkPerson).val();
            var tel = $('#' + creationOrganization.ids.tel).val();
            var email = $('#' + creationOrganization.ids.email).val();
            var legalPerson = $('#' + creationOrganization.ids.legalPerson).val();
            var businessLicense = $('#' + creationOrganization.ids.businessLicense).val();
            var businessLicensePhoto = $('#' + creationOrganization.ids.businessLicensePhoto).val();
            var capital = $('#' + creationOrganization.ids.capital).val();
            var taxCode = $('#' + creationOrganization.ids.taxCode).val();
            var taxCodePhoto = $('#' + creationOrganization.ids.taxCodePhoto).val();
            var creditCode = $('#' + creationOrganization.ids.creditCode).val();
            var creditCodePhoto = $('#' + creationOrganization.ids.creditCodePhoto).val();
            var orgCode = $('#' + creationOrganization.ids.orgCode).val();
            var orgCodePhoto = $('#' + creationOrganization.ids.orgCodePhoto).val();
            var safeProductionLicense = $('#' + creationOrganization.ids.safeProductionLicenceCode).val();
            var safeProductionLicensePhoto = $('#' + creationOrganization.ids.safeProductionLicencePhoto).val();

            creationOrganization.funcs.createOrg(parentId, name, shortName,
                description, linkPerson, tel, email,
                legalPerson, businessLicense, businessLicensePhoto,
                taxCode, taxCodePhoto, creditCode, creditCodePhoto,
                orgCode, orgCodePhoto, safeProductionLicense,
                safeProductionLicensePhoto, capital);

            //alert('执行结束');
        }


    });


});