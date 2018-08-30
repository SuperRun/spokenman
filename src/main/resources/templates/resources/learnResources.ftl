<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>平台管理-资源管理</title>
    <#include "../base.ftl">
    <link rel="stylesheet" type="text/css" href="../../css/resources/resourcesLearning.css">
    <link rel="stylesheet" type="text/css" href="../../css/path.css">
</head>

<body>
<#include "../header.ftl">
<#include "../platformMenu.ftl">
 	
<div id="app">
<div class="layui-fluid management">
    <div class="layui-main" style="min-height: 400px">
		<div class="layui-row path">
			<span><a href="/index">&nbsp首页</a></span>
			<i class="path-split">\</i>
			<span><a>培训资源管理</a></span>
			<i class="path-split">\</i>
			<span><a style="color:#c2c2c2">查看资源</a></span>
		</div>	    
		<form class="layui-form" action="">
		    <div class="layui-form-item rsearch-bar">
		        <div class="layui-inline" style="">
		            <label class="layui-form-label">资源名</label>
		            <div class="layui-input-inline">
		                <input id="name" type="text" autocomplete="off" class="layui-input"/>
		            </div>
		        </div>
		        <div class="layui-inline">
		            <label class="layui-form-label">级别</label>
		            <div class="layui-input-inline">
		                <select id="level" lay-verify="required" lay-search="">
					          <option value="">全部</option>
					          <option value="1">国家级</option>
					          <option value="2">省部级</option>
					          <option value="3">司厅局级</option>
					          <option value="4">县处级</option>
					          <option value="5">乡镇级</option>
		                </select>
		            </div>
		        </div>
		        <div class="layui-inline">
		            <label class="layui-form-label">条线</label>
		            <div class="layui-input-inline">
		                <select class="getType" id="typeId" lay-verify="required" lay-search="">
					          <option value="">全部</option>
		                </select>
		            </div>
		        </div>
		        <div class="layui-inline">
		            <label class="layui-form-label">类型</label>
		            <div class="layui-input-inline">
		                <select id="type" lay-verify="required" lay-search="">
		                    <option value="">全部</option>
		                    <option value="1">视频</option>
		                    <option value="2">音频</option>
		                    <option value="3">图文</option>
		                </select>
		            </div>
		        </div>
		        <div class="layui-inline">
		            <label class="layui-form-label">发布人</label>
		            <div class="layui-input-inline">
		                <input type="text" id="userName" autocomplete="off" class="layui-input"/>
		            </div>
		        </div>
		        <div class="layui-inline">
		            <label class="layui-form-label">讲师</label>
		            <div class="layui-input-inline">
		                <input type="text" id="lecturerName" autocomplete="off" class="layui-input"/>
		            </div>
		        </div>
		        <div class="layui-inline" style="width:30%">
		            <label class="layui-form-label" style="width:33%">日期时间选择</label>
		            <div class="layui-input-inline" style="width:50%">
		                <input type="text" v-model="searchForm.startTime" class="layui-input" id="time" >
		            </div>
		        </div>
		        <div class="layui-inline">
		            <label class="layui-form-label">状态</label>
		            <div class="layui-input-inline">
		                <select id="status" lay-verify="required" lay-search="">
		                	<option value="">请选择状态</option>
		                    <option value="1">正常</option>
		                    <option value="-1">删除</option>
		                </select>
		            </div>
		        </div>
		        <div class="layui-inline" style="width:10%">
		            <button type="button" class="layui-btn layui-btn-primary rsearch-bar-btn" @click="search">查询</button>
		        </div>
		        <div class="layui-inline">
		            <button type="button" class="layui-btn layui-btn-primary rsearch-bar-btn"  @click="addLearnResource" >新增</button>
		        </div>                
		    </div>
		</form>

        <div class="layui-row">
            <table  id="res-table" lay-filter="tablefilter"></table>
        </div>
    </div>
</div>
</div>
<#include "../footer.ftl">
<script src="../../js/getOrgType.js"></script>
<script src="../../js/resources/learnResources.js"></script>


</body>
</html>