<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>新增讲师经历</title>
	<link rel="stylesheet" type="text/css" href="../layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>
<div id="app">

	<!-- 增加单位用户 -->
	<div style="margin-top: 20px;">
        <form id="insert-info" class="layui-form"  lay-filter="addForm">
	        <div class="layui-form-item layui-form-text">
	            <div class="layui-inline">
	                <label class="layui-form-label">经历名称</label>
	                <div class="layui-input-inline" >
	                   <input placeholder="请输入讲师经历名称" class="layui-input" v-model="addLecturer.name"/>
	                </div>
	            </div>	            
	        </div>	
	        		
	        <div class="layui-form-item">
	            <div class="layui-inline">
	                <label class="layui-form-label">讲师姓名</label>
	                <div class="layui-input-inline">
	                    <select id="lecturerName" lay-filter="lecName" lay-search="">
	                        <option value="">请选择</option>
	                    </select>
	                </div>
	            </div>
	        </div>

			<div class="layui-form-item">
	            <div class="layui-inline">
	                <label class="layui-form-label">所属条线</label>
	                <div class="layui-input-inline">
					  <select class="getType" id="addTypeId" name="typeId" lay-verify="required" lay-search="">
						  <option value="">请选择条线</option>
					  </select>
	                </div>
	            </div>
	        </div>			

			<div class="layui-form-item">
	            <div class="layui-inline">
	                <label class="layui-form-label">单位等级</label>
	                <div class="layui-input-inline">
			        <select id="addLevel" name="level" lay-verify="required" lay-search="">
			          <option value="">请选择等级</option>
			          <option value="1">国家级</option>
			          <option value="2">省部级</option>
			          <option value="3">司厅局级</option>
			          <option value="4">县处级</option>
			          <option value="5">乡镇级</option>
			        </select>
	                </div>
	            </div>
	        </div>

			<div class="layui-form-item">
	            <div class="layui-inline">
	                <label class="layui-form-label search-bar-label">组织名称</label>
	                <div class="layui-input-inline">
			         <select id="searchOrgName" lay-verify="required"  lay-search="">
			         	<option value="">搜索组织名称</option>
			         </select>
	                </div>
	            </div>
	        </div>


	        <div class="layui-form-item layui-form-text">
	            <label class="layui-form-label">讲师简介</label>
	            <div class="layui-input-block">
	                <textarea style="width:60%;" placeholder="请输入内容" class="layui-textarea" v-model="addLecturer.introduction"></textarea>
	            </div>
	        </div>		    

			<div class="layui-btn-container" style="text-align :center;line-height: 48px ">
			  <button type="button" class="layui-btn  layui-btn-radius " style="width: 150px" @click="insertLecturer">提交</button> 
			  <button type="button" class="layui-btn  layui-btn-radius layui-btn-primary" style="width: 150px" onclick="closeLayer()">取消</button> 
			</div>		  	  		  		  		  		  		        
        </form>
	</div>	
</div>
</body>
<script src="../js/jquery-2.1.0.js"></script>
<script src="../layui/layui.js"></script>
<script src="../js/vue.js"></script>
<script src="../js/user/addLecturer.js"></script>
<script src="../../js/getOrgType.js"></script>
</html>