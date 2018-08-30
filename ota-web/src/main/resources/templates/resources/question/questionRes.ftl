<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资源管理-资源详情页</title>
  	<#include "../../base.ftl">
    <link rel="stylesheet" type="text/css" href="../../css/resources/question/resourcesDetails.css">
    <link rel="stylesheet" type="text/css" href="../../css/path.css">
</head>

<body>

<#include "../../header.ftl">
<#if Session.sessionInfo.type ==0> 	
<#include "../../platformMenu.ftl">
<#else>
<#include "../../orgMenu.ftl">
</#if>

<div id="app">

<div class="layui-fluid management">
    <div class="layui-main">
		<div class="layui-row path">
			<span><a href="/index">&nbsp首页</a></span>
			<i class="path-split">\</i>
			<span><a >培训资源管理</a></span>
			<i class="path-split">\</i>
			<span><a href="/platform/resourcesManage">查看资源</a></span>
			<i class="path-split">\</i>
			<span><a style="color:#c2c2c2">{{res.name}}</a></span>
		</div>	    
        <div class="layui-fluid video-detail">
            <div class="layui-row">
                <div class="layui-col-md5">
                    <video id="video" width="371" height="294" controls="controls" style="margin-left: 10%">
                        
                    </video>
                </div>
                <div class="layui-col-md7">
                    <div class="layui-row grid-demo video-detail-inline">
                        <div class="layui-col-md12">
                            <h1>
                                {{res.name}}
                            </h1>
                        </div>
                        <div class="layui-col-md3">
                            <h5 class="layui-inline">讲师：</h5>
                            <span class="layui-inline">{{res.lecturerName}}</span>
                        </div>
                        <div class="layui-col-md3">
                            <h5 class="layui-inline">上传者：</h5>
                            <span class="layui-inline">{{res.userName}}</span>
                        </div>
                        <div class="layui-col-md4">
                            <h5 class="layui-inline">时间：</h5>
                            <span class="layui-inline">{{res.createTime}}</span>
                        </div>
                        <div class="layui-col-md12">
                            <h3 class="layui-inline" style="margin-bottom: 10px;">课程概述：</h3>
                            <span class="layui-inline summary">
                                   {{res.description}}
                            </span>
                        </div>
                        <div class="layui-col-md12">
                            <button type="button" class="layui-btn layui-btn-primary rsearch-bar-btn" @click="addQuestion">新增问题</button>
                        </div>
                        <div class="layui-col-md12">
                            	提示：默认用户成功提交完最后一个问题即视作学完本视频。
                        </div>
                    </div>
                </div>

            </div>
            <div class="layui-row">
                <table class="layui-hide" id="question" lay-filter="tablefilter"></table>
            </div>
        </div>

    </div>
</div>

</div>
<#include "../../footer.ftl">

<script src="../../js/resources/question/questionRes.js"></script>

</body>
</html>