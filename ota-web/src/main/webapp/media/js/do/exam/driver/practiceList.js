/**
 * 点击模拟考试名称出现模态框显示模拟考试信息
 * 
 * @param examId
 * @param basePath
 */
function getExamDesignDetail(examId,basePath){
	$.ajax({
		url:basePath+"exam/practice/"+examId+"/detail",
		type:"GET",
		success:function(data){
			console.log(data);
			if(data.success){
				$("#practiceDetailDesigns").html("");
				$("#practiceDetailName").html(data.data.name);
				$("#practiceDetailOrgName").html(data.data.orgName);
				$("#practiceDetailDuration").html(data.data.duration);
				$("#practiceDetailQuestionSum").html(data.data.questionSum);
				$("#practiceDetailScoreSum").html(data.data.scoreSum);
				$("#practiceDetailPassNum").html(data.data.passNum);
				$("#practiceDetailPassScore").html(data.data.passScore);
				$("#practiceDetailDescription").html(data.data.description);
				for(var i=0;data.data.paperDesigns!=null&& i<data.data.paperDesigns.length;i++){
					var detail="";
					/*
					 * <tr> <td class="text-center">单选题</td>
					 * <td class="text-center">让车情景相关试题</td>
					 * <td class="text-center">20</td> </tr>
					 */
					detail+="<tr>";
					// 题型
					detail+="<td class='text-center'>"+data.data.paperDesigns[i].typeName+"</td>";
					//知识点
					detail+="<td class='text-center'>"+data.data.paperDesigns[i].subjectName+"</td>";
					//数量
					detail+="<td class='text-center'>"+data.data.paperDesigns[i].num+"</td>";
					detail+="</tr>";
					
					$("#practiceDetailDesigns").append(detail);
						
				}
			}else{
				alert("获取模拟考试信息失败");
			}
			
		},
		error:function(){alert("请求出错");}
	});
}

function startPractice(examId,basePath){
	var url=basePath+"exam/driver/practice/"+examId;
	$("#toPractice").attr("action",url);
	$("#toPractice").submit();
}