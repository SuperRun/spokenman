<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>培训管理-培训结果</title>
    <#include "../base.ftl">
    <link rel="stylesheet" type="text/css" href="../../css/train/trainConfig.css">
    <link rel="stylesheet" type="text/css" href="../../css/path.css">
</head>

<body>
	<#if Session.sessionInfo.type ==0> 	
	<#include "../platformFrame.ftl">
	<#else>
	<#include "../orgMenu.ftl">
	</#if>


<div id="app" class="layui-body">
	<div class="layui-fluid management" id="app" style="top:0;">
	    <div class="layui-main" style="min-height: 400px">
			<div class="layui-row path">
				<span><a href="/index">&nbsp首页</a></span>
				<i class="path-split">\</i>
				<span><a href="/puUser/trainManage">查看培训</a></span>
				<i class="path-split">\</i>
				<span><a style="color:#c2c2c2">培训结果</a></span>
			</div>    
	      	<div class="layui-row" style="margin-top: 20px;">
	            <form class="layui-form" action="" style="background: white">
	                <div class="layui-form-item train-info rsearch-bar" style="font-size: 15px">
	                    <div class="layui-inline" style="">
	                        <label class="layui-form-label">名称:</label>
	                        <div class="layui-input-inline train-info-detail" id="name">
	                           {{training.name}}
	                        </div>
	                    </div>
	                    <div class="layui-inline">
	                        <label class="layui-form-label">级别:</label>
	                        <div class="layui-input-inline train-info-detail" id="level">
	                            {{training.levelName}}
	                        </div>
	                    </div>
	                    <div class="layui-inline" style="width:13%">
	                        <label class="layui-form-label">条线:</label>
	                        <div class="layui-input-inline train-info-detail" id="tiaoxian">
	                          {{training.typeName}}
	                        </div>
	                    </div>
	                    <div class="layui-inline">
	                        <label class="layui-form-label">单位:</label>
	                        <div class="layui-input-inline train-info-detail" id="organization">
	                           {{training.orgName}}
	                        </div>
	                    </div>
	                    <div class="layui-inline">
	                        <label class="layui-form-label">发布人:</label>
	                        <div class="layui-input-inline train-info-detail" id="pubperson">
	                            {{training.userName}}
	                        </div>
	                    </div>
	
	                    <div class="layui-inline" style="width:25%">
	                        <label class="layui-form-label" style="width:28%">创建日期</label>
	                        <div class="layui-input-inline train-info-detail" id="pubtime">
	                            {{training.createTime}}
	                        </div>
	                    </div>
	                    <div class="layui-inline">
	                        <label class="layui-form-label">状态:</label>
	                        <div class="layui-input-inline train-info-detail" id="status">
	                            {{training.statusName}}
	                        </div>
	                    </div>
	                    <div class="layui-inline" style="width:18%">
	                        <label class="layui-form-label" style="width:33%">选修学分:</label>
	                        <div class="layui-input-inline train-info-detail" id="status">
	                            {{training.optionalScore}}
	                        </div>
	                    </div>
	                    <div class="layui-inline" style="width:18%">
	                        <label class="layui-form-label" style="width:33%">必修学分:</label>
	                        <div class="layui-input-inline train-info-detail" id="status">
	                            {{training.requiredScore}}
	                        </div>
	                    </div>   
	                </div>
	            </form>
	        </div>
	
	        <div class="layui-row">
	            <!--培训名单-->
	            <div>
	                <fieldset class="layui-elem-field layui-field-title">
	                    <legend style="font-size: 14px">课程成绩</legend>
	                </fieldset>
	                <div>
	                    <div class="layui-row">
	                        <div class="layui-col-md12"  style="">
	                            <form class="layui-form " action="">
	                                <div class="layui-inline" style="width: 35%">
	                                    <label class="layui-form-label search-bar-label" style="width: 15%;">姓名</label>
	                                    <div class="layui-input-inline"  style="width: 70%;">
	                                        <input id="namefil" type="text" class="layui-input">
	                                    </div>
	                                </div>
	
	                                <div class="layui-inline" style="width:50%">
	                                    <div class="layui-input-inline" style="width: 15%;">
	                                        <button type="button" @click="updateTrainUser" class="layui-btn layui-btn-primary search-bar-input">证书号生成</button>
	                                    </div>
	                                    <div class="layui-input-inline" style="width: 15%;margin-left: 26%">
	                                        <button type="button" class="layui-btn layui-btn-primary search-bar-input" id="upbtn">证书打印</button>
	                                    </div>
	                                </div>
	                            </form>
	                        </div>
	                    </div>
	                    
	                </div>
	                <div class="layui-row">
	                    <table class="layui-hide" id="training-result" lay-filter="tablefilter"></table>
	                </div>
	            </div>
	        </div>
	        
	        <div class="layui-row">
	        <!--学习资源清单-->
	            <div id="list" style="display: none">
	                <fieldset class="layui-elem-field layui-field-title">
	                    <legend style="font-size: 14px">{{userName}}的学习资源清单</legend>
	                </fieldset>
	
	                <div class="layui-row">
	                    <table class="layui-hide" id="resource-list" lay-filter="table">
	                	  <thead>
						    <tr>
						      <th lay-data="{field: 'resourceName', width: 100}">名称</th>
						      <th lay-data="{field: 'lecturerUserName',width: 85}">讲师</th>
						      <th lay-data="{field: 'score', width: 60}">学分</th>
						     <!-- <th lay-data="{field: 'updateTime'}">时间</th>-->
						      <th lay-data="{field: 'requiredName'}">是否必修</th>
						    </tr>
						  </thead>
	                    </table>
	                </div>
	            </div>
	        </div>
	        
	        <div class="layui-row">
	        <!--考试情况-->
	            <div id="test" style="display: none">
	                <fieldset class="layui-elem-field layui-field-title">
	                    <legend style="font-size: 14px">{{userName}}的考试情况</legend>
	                </fieldset>
	                <div class="layui-row">
	                    <div class="layui-col-md-12">
	                       <div>1、题目1**************************************（1分）</div>
	                        <div>（得分0：答案：A，正确：D）</div>
	                        <div> 2、题目1**************************************（1分）</div>
	                        <div>（得分1：答案：B，正确：B）</div>
	                        <div> 3、题目3**************************************（1分）</div>
	                        <div>（得分1：答案：C，正确：C）</div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</div>
<#include "../platformFooter.ftl">	
	
<script src="../../js/train/trainResult.js"></script>
<script>

</script>
</body>
</html>