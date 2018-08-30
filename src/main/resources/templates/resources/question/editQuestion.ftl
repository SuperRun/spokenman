<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资源管理-修改问题</title>
  
	<#include "../../base.ftl">

</head>

<body>
<div class="layui-fluid" style="margin-top: 20px;" id="app">

    <form class="layui-form" action="" id="checklist">
    
        <div class="layui-form-item">
            <div class="layui-inline">
		      <label class="layui-form-label">时间</label>
		      <div class="layui-input-inline">
		        <input type="text" class="layui-input" id="time" placeholder="HH:mm:ss">
		      </div>
		    </div>
        </div>    
    
        <div class="layui-form-item">
            <label class="layui-form-label">题干</label>
            <div class="layui-input-block">
                <input type="text" name="name" v-model="getQuestion.content" lay-verify="title" autocomplete="off"  class="layui-input">
                <div class="layui-input-block">
                    <input type="radio" name="choice" value="1" title="单选">
                    <input type="radio" name="choice" value="2" title="多选">
                </div>
            </div>
        </div>
        <div class="layui-form-item" v-for="(item,index) in getQuestion.items">
            <label class="layui-form-label">{{String.fromCharCode(option.charCodeAt(0) + index)}}</label>
            <div class="layui-inline">
                <input type="text"   v-model="item.content" class="layui-input content">
            </div>
            <div class="layui-inline">
                <input type="checkbox" name="answer" v-bind:value="String.fromCharCode(option.charCodeAt(0) + index)" lay-skin="primary">
            </div>
        </div>
        
        
        <div class="layui-form-item">
            <span style="margin-left: 10%"></span>
            <div class="layui-inline">
                <button type="button" class="layui-btn layui-btn-primary" id="addbtn2" onclick="add_ask($(this))">新增选项</button>
            </div>
            <div class="layui-inline">
                <button type="button" class="layui-btn layui-btn-primary"  onclick="del_ask($(this))">删除最后选项</button>
            </div>
        </div>
        <div class="layui-input-block formbtn" style="">
            <button type="button" class="layui-btn"  @click="editForm">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>

</div>
<script src="../../js/resources/question/editQuestion.js"></script>
</body>
</html>