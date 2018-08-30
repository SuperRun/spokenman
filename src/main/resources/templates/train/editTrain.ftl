<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>平台管理-修改培训</title>

	<#include "../addEditBase.ftl">
	<link rel="stylesheet" type="text/css" href="../../css/uploadFile.css">

</head>

<body>


<div id="app" class="layui-fluid" style="margin-top: 20px;">

    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" v-model="editTrain.name" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">说明</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" class="layui-textarea" v-model="editTrain.description"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">级别</label>
                <div class="layui-input-inline">
                    <select id="level" lay-search="">
                        <option value="">请选择等级</option>
                        <option value="1">国家级</option>
                        <option value="2">省部级</option>
                        <option value="3">司厅局级</option>
                        <option value="4">县处级</option>
                        <option value="5">乡镇科级</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width:22%">条线</label>
                <div class="layui-input-inline" style="width:63%">
                    <select id="typeId" lay-search="">
                        <option value="">请选择条线</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">培训时间</label>
                <div class="layui-input-block">
                    <input type="text" id="trainTime"  placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">报名时间</label>
                <div class="layui-input-block">
                    <input type="text" id="signTime" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">关联考试</label>
                <div class="layui-input-block">
                    <select id="test" lay-filter="aihao">
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">

        <div class="layui-inline">
                <label class="layui-form-label">必修学分</label>
                <div class="layui-input-block">
                    <input type="text"  v-model="editTrain.requiredScore" autocomplete="off"  class="layui-input" placeholder="必修学分">
                </div>
        </div>
        <div class="layui-inline">
        	<label class="layui-form-label">选修学分</label>
            <div class="layui-input-block">
                <input type="text" v-model="editTrain.optionalScore"  autocomplete="off"  class="layui-input" placeholder="选修学分">
            </div>
        </div>
        </div>
        <div class="layui-form-item">
            <div id="cover" class="identityPic" style="margin-left:25%">
                <i class="layui-icon">&#xe654;</i>
                <p>上传培训宣传图</p>
                <img id="trainCover" :src="trainCover"/>
            </div>
            <input style="display:none"  type="file" id="uploadTrainCover">
        </div>          
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" type="button" @click="updateTrain">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

</div>
<script src="../../js/train/editTrain.js"></script>
</body>
</html>