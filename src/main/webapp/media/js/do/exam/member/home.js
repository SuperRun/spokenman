/**
 * 改变考试状态
 */
function changeStatus(status) {
	$("#status").val(status);
	$("#data").submit();
}

/**
 * 获取考试详情
 */
function getExamDetail(examId, basePath) {
	$.ajax({
		url : basePath + "exam/" + examId + "/detail",
		success : function(json) {
			console.log(json);
			$("#examDetailName").html(json.data.name);
			$("#examDetailExamStartTime").html(json.data.examStartTime);
			$("#examDetailDescription").html(json.data.description);
			$("#examDetailExamId").val(json.data.examId);
		},
		error : function() {
			alert("请求出错!");
		}
	});
}

/**
 * 跳转至报名管理
 */
function directToSignup(examId, basePath) {
	location.href = basePath + "exam/member/signup/" + examId + "";
}

function directToScore(examId, basePath) {
	location.href = basePath + "exam/member/score/" + examId + "";
}

/**
 * 跳转至证书录入
 *
 * @param examId
 * @param basePath
 */
function directToCertificate(examId, basePath) {
	location.href = basePath + "exam/member/certificate/" + examId + "";
}

function getExamFinishInfo(examId, basePath) {
	$.ajax({
		url : basePath + "exam/member/finish/" + examId,
		type : 'GET',
		success : function(data) {
			console.log(data);
			if (data.success) {
				var examInfo = data.data;
				console.log(examInfo);
				// 考试名称
				$("#examName").html(examInfo.examName);
				// 发布时间
				$("#examCreateTime").html(examInfo.createTime);
				// 发布组织
				$("#examOrg").html(examInfo.organization);
				// 考试通过人数
				$("#examPassNum").html(examInfo.passNum);
				// 通过人员详情
				$("#examPassDrivers").empty();
				var passDrivers = examInfo.passDrivers;
				for (var i = 0; i < passDrivers.length; i++) {
					/*
					 * <tr> <td class="text-center">张三</td>
					 * <td class="text-center">杭州第一车企</td>
					 * <td class="text-center">杭州市散装办</td>
					 * <td class="text-center">90</td>
					 * <td class="text-center">1238434</td> </tr>
					 */
					var driverInfo = passDrivers[i];
					var detail = "";
					detail += "<tr>";

					detail += "<td class='text-center'>" + driverInfo.name
							+ "</td>";
					detail += "<td class='text-center'>" + driverInfo.orgName
							+ "</td>";
					detail += "<td class='text-center'>" + driverInfo.score
							+ "</td>";
					detail += "<td class='text-center'>"
							+ driverInfo.certificateNo + "</td>";

					detail += "</tr>";

					$("#examPassDrivers").append(detail);
				}

				// 考试参加人数
				$("#examPresentNum").html(examInfo.presentNum);
				// 参加驾驶员详情
				$("#examPresentDrivers").empty();
				var presentDrivers = examInfo.presentDrivers;
				for (var i = 0; i < presentDrivers.length; i++) {
					/*
					 * <tr> <td class="text-center">张三</td>
					 * <td class="text-center">杭州第一车企</td>
					 * <td class="text-center">杭州市散装办</td>
					 * <td class="text-center">2016/11/2 10:00</td>
					 * <td class="text-center">70</td> </tr>
					 */
					var driverInfo = presentDrivers[i];
					var detail = "";
					detail += "<tr>";

					detail += "<td class='text-center'>" + driverInfo.name
							+ "</td>";
					detail += "<td class='text-center'>" + driverInfo.orgName
							+ "</td>";
					detail += "<td class='text-center'>"
							+ driverInfo.presentTime + "</td>";
					detail += "<td class='text-center'>" + driverInfo.score
							+ "</td>";

					detail += "</tr>";

					$("#examPresentDrivers").append(detail);
				}

				// 考试报名人数
				$("#examSignupNum").html(examInfo.signupNum);
				// 报名详情
				$("#examSignupDrivers").empty();
				var signupDrivers = examInfo.signupDrivers;
				for (var i = 0; i < signupDrivers.length; i++) {
					/*
					 * <tr> <td class="text-center">张三</td>
					 * <td class="text-center">杭州第一车企</td>
					 * <td class="text-center">杭州市散装办</td>
					 * <td class="text-center">2016/10/30 09：00</td> </tr>
					 */
					var driverInfo=signupDrivers[i];
					var detail="";
					detail += "<tr>";

					detail += "<td class='text-center'>" + driverInfo.name
							+ "</td>";
					detail += "<td class='text-center'>" + driverInfo.orgName
							+ "</td>";
					detail += "<td class='text-center'>"
							+ driverInfo.signupTime + "</td>";

					detail += "</tr>";

					$("#examSignupDrivers").append(detail);
				}

			} else {
				alert(data.error);
			}
		},
		error : function() {
			alert("请求出错");
		}
	});
}
