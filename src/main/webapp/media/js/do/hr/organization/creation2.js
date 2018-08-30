var creation = {
	url : {
		updateParentOrg : function(orgType) {
			return path + '/organization/type/' + orgType + '/parent';
		},
		createSnb : path + '/organization',
		verifyLinkedPerson : function(loginName) {
			return path + '/member/loginname/' + loginName;
		}

	},
	funcs : {
		companyInputShow : function() {
			$('.companyInput').show(200);
		},
		companyInputHide : function() {
			$('.companyInput').hide(200);
		},
		updateParentOrg : function(orgType) {
			// 更新父级组织列表
			$.ajax({
				type : 'GET',
				url : creation.url.updateParentOrg(orgType),
				dataType : 'json',
				success : function(r) {
					console.log(r);
					if (r.success) {
						var parentId = $('#parentId');
						var list = r.data;
						var selectStr = '';
						for (var i = 0; i < list.length; i++) {
							selectStr += ('<option value="' + list[i].id + '">'
									+ list[i].name + '</option>');
						}
						parentId.html(selectStr);
					} else {
						// 提示出错

					}
				},
				error : function(r) {
					console.log(r);
				}
			});
		},
		createSnb : function(parentIdInput, nameInput, shortNameInput,
				descriptionInput, linkPersonInput, telInput, emailInput,
				legalPersonInput,businessLicenseInput,businessLicensePhotoInput,
				taxCodeInput,taxCodePhotoInput,creditCodeInput,creditCodePhotoInput,
				orgCodeInput,orgCodePhotoInput,safeProductionLicenseInput,
				safeProductionLicensePhotoInput,capitalInput) {
			$.ajax({
				type : 'POST',
				dataType : 'json',
				url : creation.url.createSnb,
				data : {
					parentId : parentIdInput.val(),
					name : nameInput.val(),
					shortName : shortNameInput.val(),
					description : descriptionInput.val(),
					linkPerson : linkPersonInput.val(),
					tel : telInput.val(),
					email : emailInput.val(),
					legalPerson :legalPersonInput.val(),
					businessLicense:businessLicenseInput.val(),
					businessLicensePhoto:businessLicensePhotoInput.val(),
					capital:capitalInput.val(),
					taxCode:taxCodeInput.val(),
					taxCodePhoto:taxCodePhotoInput.val(),
					creditCode:creditCodeInput.val(),
					creditCodePhoto:creditCodePhotoInput.val(),
					orgCode:orgCodeInput.val(),
					orgCodePhoto:orgCodePhotoInput.val(),
					safeProductionLicenceCode:safeProductionLicenseInput.val(),
					safeProductionLicencePhoto:safeProductionLicensePhotoInput.val()
				},
				success : function(r) {
					console.log(r);
				},
				error : function(r) {
					console.log(r);
				}
			});
		},
		verifyLinkedPerson : function(loginName) {
			$.ajax({
				type : 'GET',
				dataType : 'json',
				url : creation.url.verifyLinkedPerson(loginName),
				success : function(r) {
					console.log(r);
					if (r.success) {
						creation.alertMsg.linkPersonSpan.show('姓名'
								+ r.data.name, 'green');
						linkPersonInputFlag= true;
					} else {
						creation.alertMsg.linkPersonSpan.show(r.error, 'red');
						linkPersonInputFlag=false;
					}
				},
				error : function(r) {
					console.log(r);
					creation.alertMsg.linkPersonSpan.show(r.status + " : "
							+ r.statusText, 'red');
					linkPersonInputFlag=false;
				}
			});
			return false;
		}
	},
	alertMsg : {
		createOrg : {
			show : function(msg, clazz) {
				common.alertMsg('createOrg', msg, clazz);
			},
			hide : function() {
				common.alertMsgHide('createOrg');
			}
		},
		nameSpan : {
			show : function(msg, clazz) {
				common.alertMsg('nameSpan', msg, clazz);
			},
			hide : function() {
				common.alertMsgHide('nameSpan');
			}
		},
		shortNameSpan : {
			show : function(msg, clazz) {
				common.alertMsg('shortNameSpan', msg, clazz);
			},
			hide : function() {
				common.alertMsgHide('shortNameSpan');
			}
		},
		descriptionSpan : {
			show : function(msg, clazz) {
				common.alertMsg('descriptionSpan', msg, clazz);
			},
			hide : function() {
				common.alertMsgHide('descriptionSpan');
			}
		},
		linkPersonSpan : {
			show : function(msg, clazz) {
				common.alertMsg('linkPersonSpan', msg, clazz);
			},
			hide : function() {
				common.alertMsgHide('linkPersonSpan');
			}
		},
		telSpan : {
			show : function(msg, clazz) {
				common.alertMsg('telSpan', msg, clazz);
			},
			hide : function() {
				common.alertMsgHide('telSpan');
			}
		},
		emailSpan : {
			show : function(msg, clazz) {
				common.alertMsg('emailSpan', msg, clazz);
			},
			hide : function() {
				common.alertMsgHide('emailSpan');
			}
		}

	}
};

var linkPersonInputFlag = false;

$(document).ready(
		function() {
			var orgType = $('#orgType');

			// 组织类型改变时，基本信息填写内容改变
			orgType.change(function() {
				if (orgType.val() == 3) {
					// 选中的是车企
					creation.funcs.companyInputShow();
				} else {
					creation.funcs.companyInputHide();
				}
				creation.funcs.updateParentOrg(orgType.val());
			});

			var parentIdInput = $('#parentId');
			var nameInput = $('#name');
			var nameInputFlag = false;
			var shortNameInput = $('#shortName');
			var shortNameInputFlag = false;
			var descriptionInput = $('#description');
			var descriptionInputFlag = false;
			var linkPersonInput = $('#linkPerson');
//			var linkPersonInputFlag = false;
			var telInput = $('#tel');
			var telInputFlag = false;
			var emailInput = $('#email');
			var emailInputFlag = false;

			// 表单验证
			nameInput.change(function() {
				if (!common.validate.input(nameInput, 3)) {
					creation.alertMsg.nameSpan.show('名字必须大于3位', 'red');
					nameInputFlag = false;
				} else {
					nameInputFlag = true;
					creation.alertMsg.nameSpan.hide();
				}
			});

			shortNameInput.change(function() {
				if (!common.validate.input(shortNameInput, 3)) {
					creation.alertMsg.shortNameSpan.show('长度必须大于3位', 'red');
					shortNameInputFlag = false;
				} else {
					creation.alertMsg.shortNameSpan.hide();
					shortNameInputFlag = true;
				}
			});

			descriptionInput.change(function() {
				if (!common.validate.input(descriptionInput, 3)) {
					creation.alertMsg.descriptionSpan.show('长度必须大于3位', 'red');
					descriptionInputFlag = false;
				} else {
					creation.alertMsg.descriptionSpan.hide();
					descriptionInputFlag = true;
				}
			});

			linkPersonInput.change(function() {
				if (!common.validate.input(linkPersonInput, 3)) {
					creation.alertMsg.linkPersonSpan.show('长度必须大于3位', 'red');
					linkPersonInputFlag = false;
				} else {
					creation.alertMsg.linkPersonSpan.hide();
					var result = creation.funcs
							.verifyLinkedPerson(linkPersonInput.val());
					console.log('result  = ' + result);
					if (result) {
						console.log(true);
						linkPersonInputFlag = true;
					} else {
						console.log(false);
						linkPersonInputFlag = false;
					}
				}
			});

			telInput.change(function() {
				if (!common.validate.input(telInput, 8)) {
					creation.alertMsg.telSpan.show('长度必须大于8位', 'red');
					telInputFlag = false;
				} else {
					creation.alertMsg.telSpan.hide();
					telInputFlag = true;
				}
			});

			emailInput.change(function() {
				if (!common.validate.input(emailInput, 5)) {
					creation.alertMsg.emailSpan.show('长度必须大于5位', 'red');
					emailInputFlag = false;
				} else {
					creation.alertMsg.emailSpan.hide();
					emailInputFlag = true;
				}
			});

			var legalPersonInput = $('#legalPerson');
			var businessLicenseInput = $('#businessLicense');
			var businessLicensePhotoInput = $('#businessLicensePhoto');
			var taxCodeInput = $('#taxCode');
			var taxCodePhotoInput = $('#taxCodePhoto');
			var creditCodeInput = $('#creditCode');
			var creditCodePhotoInput = $('#creditCodePhoto');
			var orgCodeInput = $('#orgCode');
			var orgCodePhotoInput = $('#orgCodePhoto');
			var safeProductionLicenseInput = $('#safeProductionLicense');
			var safeProductionLicensePhotoInput = $('#safeProductionLicensePhoto');
			var capitalInput=$('#capital');
			
			var createOrgBtn = $("#createOrg");
			createOrgBtn.click(function() {
				console.log("btn clicked linkPersonInputFlag = "+linkPersonInputFlag);
				// 创建按钮被点击
			//creation.alertMsg.createOrg.hide();
				if (orgType.val() == 3) {
					// 创建车企
					creation.funcs.createSnb(parentIdInput, nameInput,
							shortNameInput, descriptionInput,
							linkPersonInput, telInput, emailInput,
							legalPersonInput,businessLicenseInput,businessLicensePhotoInput,
							taxCodeInput,taxCodePhotoInput,creditCodeInput,creditCodePhotoInput,
							orgCodeInput,orgCodePhotoInput,safeProductionLicenseInput,
							safeProductionLicensePhotoInput,capitalInput);
				} else {
					// 创建水泥办
					// 表单是否都验证通过
					if (nameInputFlag && shortNameInputFlag && telInputFlag
							&& emailInputFlag && linkPersonInputFlag
							&& descriptionInputFlag) {
						creation.funcs.createSnb(parentIdInput, nameInput,
								shortNameInput, descriptionInput,
								linkPersonInput, telInput, emailInput,
								legalPersonInput,businessLicenseInput,businessLicensePhotoInput,
								taxCodeInput,taxCodePhotoInput,creditCodeInput,creditCodePhotoInput,
								orgCodeInput,orgCodePhotoInput,safeProductionLicenseInput,
								safeProductionLicensePhotoInput,capitalInput);
					} else {
						console.log("flag验证不通过");
						creation.alertMsg.createOrg.show('请确认输入是否都正确', 'red');
					}
				}
			});

		});