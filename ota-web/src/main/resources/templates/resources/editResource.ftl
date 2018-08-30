<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>修改资源</title>
	<link rel="stylesheet" type="text/css" href="../../layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../../css/iconStyle.css">
	<link rel="stylesheet" type="text/css" href="../../css/uploadFile.css">
</head>

<body>

<div id="app">

<div class="layui-fluid" style="margin-top: 20px;">

    <form class="layui-form" action="" lay-filter="editForm">
    
		<div class="layui-form-item">
		  <div class="layui-inline" style="width:46%">
		    <label class="layui-form-label" style="width:31%">资源名称</label>
		    <div class="layui-input-inline" style="width:50%">
		      <input v-model="editResource.name" class="layui-input textInput">
		    </div>
		  </div>
		  <div class="layui-inline" style="width:46%">
		    <label class="layui-form-label" style="width:30%">资源权限</label>
		    <div class="layui-input-inline" style="width:50%">
	            <select id="authType" >
	            	<option value=""></option>
                    <option value="1">所以等级可见</option>
                    <option value="0">下级不可见</option>
                </select>
		    </div>
		   </div>
		</div>
      


		<div class="layui-form-item">
		  <div class="layui-inline" style="width:46%">
		    <label class="layui-form-label" style="width:31%">资源等级</label>
		    <div class="layui-input-inline" style="width:50%">
		      <select id="level" >
                <option value="">请选择资源等级</option>
                <option value="1">国家级</option>
                <option value="2">省部级</option>
                <option value="3">司厅局级</option>
                <option value="4">县处级</option>
                <option value="5">乡镇科级</option>
              </select>
		    </div>
		  </div>
		  <div class="layui-inline" style="width:46%">
		    <label class="layui-form-label" style="width:30%">资源条线</label>
		    <div class="layui-input-inline" style="width:50%">
                <select class="getType" id="typeId" >
                </select>
		    </div>
		   </div>
		</div> 
		  
		<div class="layui-form-item">
		  <div class="layui-inline" style="width:46%">
		    <label class="layui-form-label" style="width:31%">资源类型</label>
		    <div class="layui-input-inline" style="width:50%">
                <select id="type" >
                    <option value="">请选择</option>
                    <option value="1">视频讲座</option>
                    <option value="2">音频讲座</option>
                    <option value="3">图文</option>
                </select>
		    </div>
		  </div>
		  <div class="layui-inline" style="width:46%">
		    <label class="layui-form-label" style="width:30%">资源学分</label>
		    <div class="layui-input-inline" style="width:50%">
                <input v-model="editResource.score" class="layui-input textInput">
		    </div>
		   </div>
		</div> 		  
		       
		<div class="layui-form-item">
		  <div class="layui-inline" style="width:46%">
		    <label class="layui-form-label" style="width:30%">学习资源时间</label>
		    <div class="layui-input-inline" style="width:52%">
                <input type="text" v-model='editResource.time'  class="layui-input" id="time" placeholder="yyyy-MM-dd HH:mm:ss">
		    </div>
		   </div>
		</div> 
		
         <div class="layui-form-item">
            <div id="cover" class="identityPic" style="margin-left:25%">
                <i class="layui-icon">&#xe654;</i>
                <p>上传资源宣传图</p>
                <img id="resCover" :src="resCover"/>
            </div>
            <input style="display:none"  type="file" id="uploadResCover">
        </div>		
		
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">资源描述</label>
            <div class="layui-input-block">
                <textarea v-model="editResource.description" class="layui-textarea"></textarea>
            </div>
        </div>        
       
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea v-model="editResource.remark" class="layui-textarea"></textarea>
            </div>
        </div>        
       
        
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
		  <legend style="font-size:16px">讲师详情信息</legend>
		</fieldset> 
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">讲师姓名</label>
                <div class="layui-input-inline">
                    <select id="lecturerName" lay-filter="lecName" lay-search="">
                    </select>
                </div>
                <button class="layui-btn layui-btn-primary rsearch-bar-btn">新增讲师</button>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">讲师经历</label>
                <div class="layui-input-inline" style="">
                    <select id="lecturerExp" lay-verify="required" lay-search="">
                        
                    </select>
                </div>
            </div>
        </div> 	
        	       
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="button" class="layui-btn" @click="updateResource">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
        
    </form>

	<input id="resId" type="hidden" value="0">
</div>

</div>
<script src="../../js/jquery-2.1.0.js"></script>
<script src="../../js/vue.js"></script>
<script src="../../layui/layui.js"></script>
<script src="../../js/resources/editResource.js"></script>
</body>
</html>