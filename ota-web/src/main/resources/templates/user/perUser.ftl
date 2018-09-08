<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>平台管理-个人用户</title>
	<#include "../base.ftl">
	<link rel="stylesheet" type="text/css" href="../css/user/perUser.css">
	<link rel="stylesheet" type="text/css" href="../css/path.css">
</head>

<body>
	<#include "../platformFrame.ftl">	
<div id="app" class="layui-body">
	<div class="layui-fluid management" >
		<div class="layui-main" style="min-height: 400px">
			<div class="layui-row path">
				<span><a href="/index">&nbsp首页</a></span>
				<i class="path-split">\</i>
				<span><a style="color:#c2c2c2">个人用户</a></span>
			</div>		
			<div class="search-bar">
				<div style="margin-top: 2%;margin-left:4%">
					<form class="layui-form " id="search-info" lay-filter="searchForm">
						<div class="layui-inline" style="width: 20%">
							<label class="layui-form-label search-bar-label" style="width: 20%;">状态</label>
							<div class="layui-input-inline" style="width: 60%;">
						        <select id="searchStatus"  name="status" lay-verify="required" lay-search=""  >
						        <option value="">请选择状态</option>
						          <option value="-1">删除</option>
						          <option value="1">正常</option>
						          <option value="0">审核拒绝</option>
						          <option value="2">申请中</option>
						          <option value="-2">账号冻结</option>
						        </select>
						    </div>
					    </div> 	
						<div class="layui-inline" style="width: 25%">
							<label class="layui-form-label search-bar-label" style="width: 25%;">组织名称</label>
							<div class="layui-input-inline" style="width: 60%;">
						         <select id="searchOrgName" lay-verify="required"  lay-search="">
						         	<option value="">搜索组织名称</option>
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
	    				
	    				<div class="layui-inline" style="width: 19%;margin-left:15%;">
							<div class="layui-input-inline" >
						         <button type="button" data-method="add" id="add-btn" class="layui-btn layui-btn-primary search-bar-input change-btn" @click="addUser">新增</button>
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

	<#include "../platformFooter.ftl">

	<!-- 增加单位用户 -->
	<div id="add-per-user" style="display: none;padding: 20px;">
        <form id="insert-info" class="layui-form" lay-filter="add-form" action="" style="width: 400px">
			<fieldset class="layui-elem-field layui-field-title">
				<legend style="font-size: 14px">请填写个人信息</legend>
			</fieldset>	
			<div class="layui-form-item">
			<label class="layui-form-label">用户姓名</label>
			<div class="layui-input-block" style="height: 38px">
			  <input v-model="addForm.name" type="text" name="name" placeholder="请输入用户姓名" class="layui-input change-input">
			</div>
			</div>
			
			<div class="layui-form-item">
			<label class="layui-form-label">联系电话</label>
			<div class="layui-input-block" style="height: 38px">
			  <input v-model="addForm.phone" type="text" onblur="isPoneAvailable(this)" onkeyup="phoneVerify(this)"  name="phone" placeholder="请输入联系电话" class="layui-input change-input">
			</div>
			</div>			


			<div class="layui-form-item">
			<label class="layui-form-label">电子邮箱</label>
			<div class="layui-input-block" style="height: 38px">
			  <input onblur="emailVerify(this)" v-model="addForm.email" type="text" name="email" placeholder="请输入电子邮箱" class="layui-input change-input">
			</div>
			</div>

			<!--<fieldset class="layui-elem-field layui-field-title">
				<legend style="font-size: 14px">请填写单位信息</legend>
			</fieldset>	

			<div class="layui-form-item">
			<label class="layui-form-label">单位名称</label>
			<div class="layui-input-block" style="height: 38px">
			  <input v-model="addForm.orgName" type="text" name="orgName" placeholder="请输入单位全称" class="layui-input change-input">
			</div>
			</div>			
			<div class="layui-form-item">
			<label class="layui-form-label">所在区域</label>
			<div  class="layui-input-inline change-select" style="width:22%;margin-right: 13px;">
			  <select id="add-province" lay-filter="add-province" name="add-province">
			  </select>
			</div>
			<div class="layui-input-inline change-select" style="width:22%;margin-right: 13px;">
			  <select id="add-city" lay-filter="add-city" name="add-city">
			  </select>
			</div>
			<div class="layui-input-inline change-select" style="width:22%;margin-right: 0px;">
			  <select id="add-county" lay-filter="add-county" name="add-county">
			  </select>
			</div>
			</div>

			<div class="layui-form-item">
			<label class="layui-form-label">所属条线</label>
			<div class="layui-input-block">
			  <select class="getType" id="addTypeId" name="typeId" lay-verify="required" lay-search="">
				  <option value="">请选择条线</option>
			  </select>
			</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">单位等级</label>
				<div class="layui-input-block">
			        <select id="addLevel" name="level" lay-verify="required" lay-search="">
			          <option value="">请选择等级</option>
			          <option value="1">国家级</option>
			          <option value="2">省部级</option>
			          <option value="3">司厅局级</option>
			          <option value="4">县处级</option>
			          <option value="5">乡镇级</option>
			        </select>
				</div>
			</div>-->

			<div class="layui-btn-container" style="text-align :center;line-height: 48px ">
			  <button type="button" class="layui-btn  layui-btn-radius " style="width: 150px" @click="insertUser">提交</button> 
			  <button type="button" class="layui-btn  layui-btn-radius layui-btn-primary" style="width: 150px" onclick="closeLayer()">取消</button> 
			  <button type="reset" id="emptyAddForm" class="layui-btn layui-btn-primary"  style="display:none">
			</div>		  	  		  		  		  		  		        
        </form>
	</div>	
	
	<!-- 修改单位用户 -->
	<div id="edit-per-user" style="display: none;padding: 20px;">
        <form class="layui-form" lay-filter="edit-form" action="" style="width: 400px">

			<fieldset class="layui-elem-field layui-field-title">
				<legend style="font-size: 14px">请修改个人信息</legend>
			</fieldset>	
			
			<div class="layui-form-item">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-block" style="height: 38px">
			  <input v-model="editForm.name" id="change-name" type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入姓名" class="layui-input change-input">
			</div>
			</div>


			<div class="layui-form-item">
			<label class="layui-form-label">联系电话</label>
			<div class="layui-input-block" style="height: 38px">
			  <input onblur="isPoneAvailable(this)" onkeyup="phoneVerify(this)" id="change-tel" v-model="editForm.phone" type="text"  class="layui-input change-input">
			</div>
			</div>
			<div class="layui-form-item">
			<label class="layui-form-label">电子邮箱</label>
			<div class="layui-input-block" style="height: 38px">
			  <input onblur="emailVerify(this)" id="change-email" v-model="editForm.email" type="text" class="layui-input change-input">
			</div>
			</div>
			
			<div class="layui-btn-container" style="text-align :center;line-height: 48px ">
			  <button type="button" class="layui-btn  layui-btn-radius " style="width: 150px" @click="updateUser">提交</button> 
			  <button type="button" class="layui-btn  layui-btn-radius layui-btn-primary" style="width: 150px" onclick="closeLayer()">取消</button> 
			  
			</div>		  	  		  		  		  		  		        
        </form>
	</div>

</div>

	<#include "../linkage.ftl">
	<script src="../../js/common/updateState.js"></script>
	<script src="../../js/getOrgType.js"></script>
	<script src="../../js/user/perUser.js"></script>
	<script src="../../js/common/areaLayer.js"></script>
	<script src="../../js/common/closeLayer.js"></script>
	<script src="../../js/common/formVerify.js"></script>
</body>
</html>