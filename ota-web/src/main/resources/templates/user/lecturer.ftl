<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>平台管理-讲师管理</title>
	<#include "../base.ftl">
	<link rel="stylesheet" type="text/css" href="../../css/user/perUser.css">
	<link rel="stylesheet" type="text/css" href="../../css/path.css">
</head>

<body >
	
	<#include "../header.ftl">	
	<#include "../platformMenu.ftl">	
<div id="app">
	<div class="layui-fluid management" >
		<div class="layui-main" style="min-height: 400px">
			<div class="layui-row path">
				<span><a href="/index">&nbsp首页</a></span>
				<i class="path-split">\</i>
				<span><a href="/platform/lecUser">讲师管理</a></span>
				<i class="path-split">\</i>
				<span><a style="color:#c2c2c2">讲师经历管理</a></span>
			</div>			
			<div class="search-bar">
				<div style="margin-top: 2%;margin-left:10%">
					<form class="layui-form " id="search-info" lay-filter="searchForm">
                         <div class="layui-inline" style="width: 18%">
	    			    	<label class="layui-form-label search-bar-label"  style="width: 20%;">条线</label>
	    			    	<div class="layui-input-inline"  style="width: 60%;">
						        <select class="getType" id="searchTypeId" name="typeId" lay-verify="required" lay-search="">
						          <option value="">请选择条线</option>
						        </select>
						    </div>
					    </div>	
						<div class="layui-inline" style="width: 18%">
							<label class="layui-form-label search-bar-label" style="width: 20%;">等级</label>
							<div class="layui-input-inline" style="width: 60%;">
						        <select id="searchLevel" name="level" lay-verify="required" lay-search="">
						          <option value="">请选择等级</option>
						          <option value="1">国家级</option>
						          <option value="2">省部级</option>
						          <option value="3">司厅局级</option>
						          <option value="4">县处级</option>
						          <option value="5">乡镇级</option>
						        </select>
						    </div>
					    </div> 
						<div class="layui-inline" style="width: 18%">
							<label class="layui-form-label search-bar-label" style="width: 20%;">状态</label>
							<div class="layui-input-inline" style="width: 60%;">
						        <select id="searchStatus"  name="status" lay-verify="required" lay-search=""  >
						        <option value="">请选择状态</option>
						          <option value="-1">删除</option>
						          <option value="1">正常</option>
						        
						        </select>
						    </div>
					    </div>  					    					    						    	
						<div class="layui-inline" style="width: 19%">
							<label class="layui-form-label search-bar-label" style="width: 16%;">姓名</label>
							<div class="layui-input-inline" style="width: 50%;">
						         <input id="userName" type="text" name="name" autocomplete="off"  class="layui-input search-bar-input">
						    </div>
							<div class="layui-input-inline" style="width: 15%;">
						         <button class="layui-btn layui-btn-primary search-bar-input" type="button"  @click="search">查询</button>
						    </div>							    
	    				</div> 
	    				
	    				<div class="layui-inline" style="width: 19%;margin-left:5%;">
							<div class="layui-input-inline" >
						         <button type="button" data-method="add" id="add-btn" class="layui-btn layui-btn-primary search-bar-input change-btn" @click="insert">新增</button>
						    </div>
							<div class="layui-input-inline">
						         <button type="button" class="layui-btn layui-btn-primary search-bar-input">删除</button>
						    </div>							    
		    			</div> 		    
					</form>
				</div>	
												
			</div>
 
		    <div class="layui-row" style="width: 100%;">
				<table id="per-user-table" lay-filter="unit-user"></table>
		    </div>
		    
		</div>
	</div>
	<!-- 左边隐藏栏 -->
	<div id="left-menu" style="display: block;">
		<dl id="left-menu-item" class="layui-nav-child" style="display: block;">
	      <dd><a href="javascript:;">个人资料</a></dd>
	      <dd><a href="javascript:;">设置密码</a></dd>
	      <dd><a href="javascript:;">电商平台</a></dd>
	      <dd><a href="javascript:;">设置密码</a></dd>
	      <dd><a href="javascript:;">电商平台</a></dd>
	      <dd><a href="javascript:;">设置密码</a></dd>
	      <dd><a href="javascript:;">电商平台</a></dd>
	      <dd><a href="javascript:;">设置密码</a></dd>
	      <dd><a href="javascript:;">电商平台</a></dd>
	    </dl> 
	</div>

	<#include "../footer.ftl">


	
		
</div>

	<#include "../linkage.ftl">
	<script src="../../js/common/updateState.js"></script>
	<script src="../../js/user/lecturer.js"></script>
	<script src="../../js/common/areaLayer.js"></script>
	<script src="../../js/common/closeLayer.js"></script>
	<script src="../../js/common/formVerify.js"></script>
	<script src="../../js/getOrgType.js"></script>	
</body>
</html>