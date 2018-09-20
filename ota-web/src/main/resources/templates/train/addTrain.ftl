<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>培训管理-添加培训</title>
    <#include "../addEditBase.ftl">
	<link rel="stylesheet" type="text/css" href="../../css/uploadFile.css">
</head>

<body>
<div id="app">
<div class="layui-fluid" style="margin-top: 20px;">

    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" v-model="addTrain.name" id="name" lay-verify="title" autocomplete="off"  class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">说明</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" class="layui-textarea" v-model="addTrain.description"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline" style="width:45%">
                <label class="layui-form-label" style="width:32%">级别</label>
                <div class="layui-input-inline" style="width:50%">
                    <select id="level" lay-verify="required" lay-search="">
                        <option value="">请选择等级</option>
                        <option value="1">国家级</option>
                        <option value="2">省部级</option>
                        <option value="3">司厅局级</option>
                        <option value="4">县处级</option>
                        <option value="5">乡镇科级</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline" style="width:45%">
                <label class="layui-form-label" style="width:32%">条线</label>
                <div class="layui-input-inline" style="width:50%">
                    <select class="getType" id="typeId" lay-verify="required" lay-search="">
                        <option value="">请选择条线</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline" style="width:45%">
                <label class="layui-form-label" style="width:32%">培训时间</label>
                <div class="layui-input-block" style="width:50%">
                    <input type="text"  id="trainTime" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">

                </div>
            </div>
            <div class="layui-inline" style="width:45%">
                <label class="layui-form-label" style="width:32%">报名时间</label>
                <div class="layui-input-block" style="width:50%">
                    <input type="text" id="signTime" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline" style="width:45%">
                <label class="layui-form-label">学分要求</label>
                <div class="layui-input-block" style="width:50%">
                    <input type="text" v-model="addTrain.requiredScore"  autocomplete="off"  class="layui-input" placeholder="必修学分">
                </div>
            </div>
            <div class="layui-inline" style="width:24%">
                <input type="text" v-model="addTrain.optionalScore"  autocomplete="off"  class="layui-input" placeholder="选修学分">
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
                <button class="layui-btn" type="button" @click="insertTrain">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

</div>
</div>
<script src="../../js/getOrgType.js"></script>
<script src="../../js/train/addTrain.js"></script>
</body>
</html>