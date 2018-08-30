<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>平台管理-培训管理</title>
    <#include "../base.ftl">
    <link rel="stylesheet" type="text/css" href="../../css/train/trainManage.css">
    <link rel="stylesheet" type="text/css" href="../../css/path.css">
</head>

<body>

	<#include "../header.ftl">	
	<#if Session.sessionInfo.type ==0> 	
	<#include "../platformMenu.ftl">
	<#else>
	<#include "../orgMenu.ftl">
	</#if>
<div id="app">

<div class="layui-fluid management">
	<input id="userType" type="hidden" value="${(Session.sessionInfo.type)}"  class="layui-input"/>
    <div class="layui-main" style="min-height: 400px">
		<div class="layui-row path">
			<span><a href="/index">&nbsp首页</a></span>
			<i class="path-split">\</i>
			<span><a style="color:#c2c2c2">查看培训</a></span>
		</div>
        <form class="layui-form" action="">
            <div class="layui-form-item rsearch-bar2">
                <div class="layui-inline" style="">
                    <label class="layui-form-label">名称</label>
                    <div class="layui-input-inline">
                        <input type="text" id="name" autocomplete="off" class="layui-input"/>
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
                            <option value="5">乡镇科级</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">条线</label>
                    <div class="layui-input-inline">
                        <select id="typeId" lay-verify="required" lay-search="">
                            <option value="">全部</option>
                            <option value="1">宣传</option>
                            <option value="2">教育</option>
                            <option value="3">卫生</option>
                            <option value="4">安全</option>
                        </select>
                    </div>
                </div>
				<#if Session.sessionInfo.type ==0> 	
				    <div class="layui-inline" style="width: 18%;">
				        <label class="layui-form-label" style="width: 30%;">发布单位</label>
				        <div class="layui-input-inline">
				            <select id="searchOrgName"  lay-search="">
				            	<option value="">搜索发布单位</option>
				            </select>
				        </div>
				    </div>
				</#if>  
                <div class="layui-inline" style="width: 18%;">
                    <label class="layui-form-label" style="width: 30%;">发布人</label>
                    <div class="layui-input-inline">
                        <input type="text" id="userName" autocomplete="off" class="layui-input"/>
                    </div>
                </div>

                <div class="layui-inline" style="width:30%">
                    <label class="layui-form-label" style="width:17%">创建时间</label>
                    <div class="layui-input-inline" style="width:50%">
                        <input type="text"  class="layui-input" id="createTime" placeholder="yyyy-MM-dd HH:mm:ss">
                    </div>
                </div>
                          
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select id="status" lay-verify="required" lay-search="">
                            <option value="">全部</option>
                            <option value="1">已发布</option>
                            <option value="2">报名中</option>
                            <option value="3">待培训</option>
                            <option value="4">培训证</option>
                            <option value="5">确认中</option>
                            <option value="6">已完成</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <button type="button" class="layui-btn layui-btn-primary rsearch-bar-btn" @click="searchTrain">查询</button>
                </div>
                <div class="layui-inline">
                	<button type="button" class="layui-btn layui-btn-primary rsearch-bar-btn" @click="addTrain" style="margin-left: 31%;">新增</button>
           		</div>
            </div>
        </form>



        <div class="layui-row">
            <table class="layui-hide" id="train" lay-filter="tablefilter"></table>
        </div>
    </div>
</div>

</div>

<#include "../footer.ftl">


<script src="../../js/train/trainManage.js"></script>

<script>

</script>
</body>
</html>