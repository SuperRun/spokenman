<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>培训配置-新增培训资源</title>
    <#include "../addEditBase.ftl">
    <link rel="stylesheet" type="text/css" href="../../css/style1.css">
    <link rel="stylesheet" type="text/css" href="../../css/train/addTrainUser.css">
</head>

<body>
<div id="app">

<div class="layui-fluid management">
	<input id="userType" type="hidden" value="${(Session.sessionInfo.type)}"  class="layui-input"/>
    <div class="layui-main">
        <div id="edit-unit-user" style="padding: 20px;">
            <div>
                <div class="layui-row search-bar">
                    <div class="layui-col-md12"  style="margin-top: 2%">
		                <form class="layui-form " action="" id="search-info">
		                    <div class="layui-inline" style="width: 18%">
		                        <label class="layui-form-label search-bar-label" style="width: 25%;">讲师</label>
		                        <div class="layui-input-inline"  style="width: 50%;">
		                            <input id="lecturerName"  type="text"  autocomplete="off" class="layui-input">
		                        </div>
		                    </div>
		                    <div class="layui-inline" style="width: 22%">
		                        <label class="layui-form-label search-bar-label"  style="width: 20%;">条线</label>
		                        <div class="layui-input-inline"  style="width: 50%;">
		                            <select id="searchTypeId" lay-search="">
		                                <option value="">安全生产</option>
		                                <option value="2">教育</option>
		                                <option value="3">卫生</option>
		                                <option value="4">宣传</option>
		                                <option value="5">组织</option>
		                            </select>
		                        </div>
		                    </div>
		                    <div class="layui-inline" style="width: 22%">
		                        <label class="layui-form-label search-bar-label" style="width: 20%;">等级</label>
		                        <div class="layui-input-inline" style="width: 60%;">
		                            <select class="" id="searchLevel" lay-search="">
		                                <option value="">国家级</option>
		                                <option value="2">省部级</option>
		                                <option value="3">司厅局级</option>
		                                <option value="4">县处级</option>
		                                <option value="5">乡镇级</option>
		                            </select>
		                        </div>
		                    </div>
		
		                    <div class="layui-inline" style="width: 19%">
		                        <div class="layui-input-inline" style="width: 10%;">
		                            <button class="layui-btn layui-btn-primary search-bar-input">查询</button>
		                        </div>
		                        <div class="layui-input-inline" style="width: 10%;margin-left: 28%">
		                            <button type="button" class="layui-btn layui-btn-primary search-bar-input" @click="addTrainResource">添加</button>
		                        </div>
		                        <div class="layui-input-inline" style="width: 10%;margin-left: 28%">
		                            <button class="layui-btn layui-btn-primary search-bar-input" data-method="isDel" id="delbtn3">删除</button>
		                        </div>
		                    </div>
		                </form>                        
                    </div>

                </div>
               
            </div>
            <div class="layui-row">
                <table class="layui-hide" id="training-resource" lay-filter="tablefilter"></table>
            </div>
        </div>
    </div>
</div>

<script src="../../js/train/addTrainResource.js"></script>
</body>


</html>