function toStep(t) {
	if (t == 1)
		;
	else if (eval("check" + (t - 1) + "()") == false)
		return false;
	// console.log("check is ok");
	for (var i = 1; i <= 6; i++) {
		if (i == t)
			$(".main_ror" + i).show();
		else
			$(".main_ror" + i).hide();
	}
}

function IsEmail(yx) {
	var reyx = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
	console.log(yx);
	console.log(reyx.test(yx));
	return (reyx.test(yx));
}

function check1() {
	if ($('#agreeRadio').prop("checked") == false) {
		alert("还未同意检测服务协议！");
		return false;
	}
	console.log("pass 1");
}
function check2() {
	var str;
	str = $('#inspectionForm input[name=clientName]').val();
	if (str == "") {
		alert("委托单位名称不能为空");
		return false;
	}
	str = $('#inspectionForm input[name=clientAddress]').val();
	if (str == "") {
		alert("委托单位地址不能为空");
		return false;
	}
	str = $('#inspectionForm input[name=linkName]').val();
	if (str == "") {
		alert("联系人姓名不能为空");
		return false;
	}
	str = $('#inspectionForm input[name=linkEmail]').val();
	if (str == "") {
		alert("联系人邮箱不能为空");
		return false;
	}
	if (IsEmail(str) == false) {
		alert("联系人邮箱格式不正确");
		return false;
	}
	console.log("pass 2");
}
function check3() {
	var str;
	str = $('#inspectionForm input[name=sampleName]').val();
	if (str == "") {
		alert("样品名称不能为空");
		return false;
	}
	str = $('#inspectionForm input[name=sampleDescription]').val();
	if (str == "") {
		alert("样品描述不能为空");
		return false;
	}
	if(str.length>=100){
		alert("样品描述不能超过100字符");
		return false;
	}
	str = $('#inspectionForm input[name=miscSampleNum]').val();
	if (str == "") {
		alert("样品数量不能为空");
		return false;
	}
	str = $('#inspectionForm input[name=miscProductLind]').val();
	if (str == "") {
		alert("样品链接不能为空");
		return false;
	}
	var re = /^[1-9]+[0-9]*]*$/;
	str = $('#inspectionForm input[name=itemSku]').val();
	if (str != "" && !re.test(str) ){
		alert("请输出正整数");
		return false;
	}
	console.log("pass 3");
}
function getsceneApplied(){
	var t=$('input[name=appCheck]:checked').val();
 	$('#sceneApplied').val(t);
}
function check4() {
	reportLangInput();// 获得报告语言
	getsceneApplied();// 获得应用场景
	var str;
	str = $('#sceneApplied').val();
	if (str==null || str == "") {
		alert("应用场景不能为空");
		return false;
	}
	// console.log(str);
	str = $('#inspectionForm input[name=reportPeriod]').val();
	if (str==null || str == "") {
		alert("检测周期不能为空");
		return false;
	}
	console.log("pass 4");
}
function check5(){
	reportMediumInput();
    remainderInput();
    invoiceRequireInput();
    /*
    var str;
    str=$('#reportLinkMan').val();
    if(str==""){
      alert("快递联系人不能为空");
      return false;
    }
    str=$('#reportTel').val();
    if(str==""){
      alert("快递电话不能为空");
      return false;
    }
    str=$('#reportAddress').val();
    if(str==""){
      alert("快递地址不能为空");
      return false;
    }
    console.log("pass 5");
    */
}
