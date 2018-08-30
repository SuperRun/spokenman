
function getPaperDetail(paperId,basePath){
	$.ajax({
		url:basePath+"paper/member/"+paperId+"/detail",
		success:function(data){
			console.log(data);
			if(data.success){
				var paper=data.data;
				/*
				 * 基本信息
				 */
				//试卷名称
				$("#paperName").html("");
				$("#paperName").html(paper.name);
				//发布组织
				$("#paperOrgName").html("");
				$("#paperOrgName").html(paper.data)
				//发布时间
				$("#createTime").html("");
				$("#createTime").html(paper.createTime);
				//考试类型
				$("#paperType").html("");
				$("#paperType").html(paper.type);
				//总分
				$("#totalScore").html("");
				$("#totalScore").html(paper.scoreSum);
				//及格分
				$("#passScore").html("");
				$("#passScore").html(paper.passScore);
				//总题数
				$("#totalNum").html("");
				$("#totalNum").html(paper.questionSum);
				//及格题目数
				$("#passNum").html("");
				$("#passNum").html(paper.passNum);
				//备注注
				$("#paperDescription").html("");
				$("#paperDescription").html(paper.description);
				
				/*
				 * 题目设计
				 */
				$("#paperDesigns").html("");
				for(var i=0;i<paper.paperDesigns.length;i++){
					/*
					 * <tr>
												<td class="text-center">单选题</td>
												<td class="text-center">让车情景相关试题</td>
												<td class="text-center">20</td>
											</tr>
					 */
					var paperDesign="<tr>";
					//题型
					paperDesign+="<td class='text-center'>"+paper.paperDesigns[i].typeName+"</td>";
					//知识点
					paperDesign+="<td class='text-center'>"+paper.paperDesigns[i].subjectName+"</td>";
					//数量
					paperDesign+="<td class='text-center'>"+paper.paperDesigns[i].num+"</td>";
					
					$("#paperDesigns").append(paperDesign);
				}
				
				/*
				 * 显示题目
				 */
				$("#questionList").html("");
				for (var i = 0; paper.questions != null
						&& i < paper.questions.length; i++) {
					var detail = "";
					// 显示开始
					detail += "<div class='form-group'><div class='col-md-9'>";
					/*
					 * 题干开始
					 */
					detail += "<h3 class='question' id='question'>";

					detail += "<span class='serial_number'>"
							+ parseInt(i + 1)
							+ "</span>&nbsp;<span class='question_content'>"
							+ paper.questions[i].content + "</span>";
					// 题干结束
					detail += "</h3>";

					/*
					 * 选项开始
					 */
					detail += "<ul id='answer_list'>";

					for (var j = 0; paper.questions[i].questionItems != null
							&& j < paper.questions[i].questionItems.length; j++) {
						detail += "<li class='answer_item'><span class=''>"
								+ paper.questions[i].questionItems[j].sequence
								+ "</span>&nbsp;"
								+ "<spanclass='answer_content'>";
						if (paper.questions[i].questionItems[j].pic != null
								&& paper.questions[i].questionItems[j].pic != "") {
							detail += "<img src='"
									+ basePath+paper.questions[i].questionItems[j].pic
									+ "'> </span></li>";
						} else {
							detail += "<span>"
									+ paper.questions[i].questionItems[j].content
									+ "</span>";
						}

					}
					// 选项结束
					detail += "</ul>";
					// 题目结束
					detail += "</div>";

					/*
					 * 题目附加描述图片开始
					 */
					detail += "<div class='col-md-3 text-center' id='media-container'>";

					if (paper.questions[i].pic1 != null
							&& paper.questions[i].pic1 != "") {
						detail += "<img src='"
								+ basePath+paper.questions[i].pic1
								+ "'>";
					}
					if (paper.questions[i].pic2 != null
							&& paper.questions[i].pic2 != "") {
						detail += "<img src='"
								+ basePath+paper.questions[i].pic2 
								+ "'>";
					}
					if (paper.questions[i].pic3 != null
							&& paper.questions[i].pic3 != "") {
						detail += "<img src='"
								+ basePath+paper.questions[i].pic3
								+ "'>";
					}
					// 题目附加描述图片结束
					detail += "</div>";

					// 显示完毕
					detail += "</div>";

					// 显示题目信息
					$("#questionList").append(detail);
				}
				
			}
			else{
				alert(data.error);
			}
		},
		error:function(){alert("请求出错！");}
	});
}

function conveyPaperId(paperId){
	$("#paperId").val(paperId);
}

/**
 * 选择试卷
 */
function selectPaper(){
	$("#selectPaper").submit();
}

