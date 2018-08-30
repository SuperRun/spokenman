<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>培训管理-配置资源</title>
     <#include "../base.ftl">
    <link rel="stylesheet" type="text/css" href="../../css/train/trainConfig.css">
    <link rel="stylesheet" type="text/css" href="../../css/path.css">
</head>

<body>
	<#include "../header.ftl">	
	<#if Session.sessionInfo.type ==0> 	
	<#include "../platformMenu.ftl">
	<#else>
	<#include "../orgMenu.ftl">
	</#if>
	<input id="userType" type="hidden" value="${(Session.sessionInfo.type)}"  class="layui-input"/>
<div id="app">	
<div class="layui-fluid management">
    <div class="layui-main">
        <div class="layui-row" style="margin-top: 20px;">
            <form class="layui-form" action="" style="background: white">
                <div class="layui-form-item train-info rsearch-bar" style="font-size: 15px">
                    <div class="layui-inline" style="">
                        <label class="layui-form-label">名称:</label>
                        <div class="layui-input-inline train-info-detail" id="name">
                           {{training.name}}
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">级别:</label>
                        <div class="layui-input-inline train-info-detail" id="level">
                            {{training.levelName}}
                        </div>
                    </div>
                    <div class="layui-inline" style="width:12%">
                        <label class="layui-form-label">条线:</label>
                        <div class="layui-input-inline train-info-detail" id="tiaoxian">
                          {{training.typeName}}
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">单位:</label>
                        <div class="layui-input-inline train-info-detail" id="organization">
                           {{training.orgName}}
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">发布人:</label>
                        <div class="layui-input-inline train-info-detail" id="pubperson">
                            {{training.userName}}
                        </div>
                    </div>

                    <div class="layui-inline" style="width:25%">
                        <label class="layui-form-label" style="width:28%">创建日期</label>
                        <div class="layui-input-inline train-info-detail" id="pubtime">
                            {{training.createTime}}
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">状态:</label>
                        <div class="layui-input-inline train-info-detail" id="status">
                            {{training.statusName}}
                        </div>
                    </div>
                    <div class="layui-inline" style="width:18%">
                        <label class="layui-form-label" style="width:33%">选修学分:</label>
                        <div class="layui-input-inline train-info-detail" id="status">
                            {{training.optionalScore}}
                        </div>
                    </div>
                    <div class="layui-inline" style="width:18%">
                        <label class="layui-form-label" style="width:33%">必修学分:</label>
                        <div class="layui-input-inline train-info-detail" id="status">
                            {{training.requiredScore}}
                        </div>
                    </div>   
                <div class="layui-inline" style="width:10%">
                        <label class="layui-form-label" style="width:33%">
                        	 <button type="button" class="layui-btn layui-btn-primary rsearch-bar-btn" @click="editTrain">修改培训信息</button>
                        </label>
                        
                    </div>
                </div>
            </form>

        </div>
        
		<div class="layui-row path" style="line-height:40px;">
			<span><a href="/index">&nbsp首页</a></span>
			<i class="path-split">\</i>
			<span><a href="/puUser/trainManage">查看培训</a></span>
			<i class="path-split">\</i>
			<span><a style="color:#c2c2c2">配置培训名单</a></span>
		</div>
		        
        <!--培训名单-->
        <fieldset class="layui-elem-field layui-field-title" style="border:1px dashed #c3c1c1;padding: 10px;margin-bottom: 60px; text-align:center;">
            <h3 class="types-title" style="line-height: 40px;">
                <i class="layui-icon" style="font-size: 40px; color: #5fb878;">&#xe62e;</i>
                <span style="font-size: 20px;"><em>培</em>／<em>训</em>／<em>名</em>／<em>单</em></span>
                <i class="layui-icon" style="font-size: 40px; color: #5fb878;">&#xe62e;</i>
            </h3>

            <div>
                <div class="layui-row search-bar">
                    <div class="layui-col-md10"  style="margin-top: 2%">
                        <form class="layui-form " action="">
                            <div class="layui-inline" style="width: 25%">
                                <label class="layui-form-label search-bar-label" style="width: 15%;">区域</label>
                                <div class="layui-input-inline"  style="width: 70%;">
                                    <input id="area" type="text" name="area"  lay-verify="area" placeholder="浙江省-杭州市-西湖区" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline" style="width: 18%">
                                <label class="layui-form-label search-bar-label"  style="width: 20%;">条线</label>
                                <div class="layui-input-inline"  style="width: 60%;">
							        <select id="searchUserTypeId" name="typeId" lay-verify="required" lay-search="">
							          <option value="">请选择条线</option>
							          <option value="1">组织条线</option>
									  <option value="2">安全生产</option>
									  <option value="3">组织宣传</option>
							        </select>
                                </div>
                            </div>
                            <div class="layui-inline" style="width: 18%">
                                <label class="layui-form-label search-bar-label" style="width: 20%;">等级</label>
                                <div class="layui-input-inline" style="width: 60%;">
							        <select id="searchUserLevel" name="level" lay-verify="required" lay-search="">
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
                                <label class="layui-form-label search-bar-label" style="width: 20%;">姓名</label>
                                <div class="layui-input-inline" style="width: 60%;">
                                    <input id="userName" type="text" name="sname"   autocomplete="off" class="layui-input">

                                </div>
                            </div>
                            <div class="layui-inline" style="width: 19%">
                                <div class="layui-input-inline" style="width: 15%;">
                                    <button type="button" class="layui-btn layui-btn-primary search-bar-input" @click="searchTrainUser">查询</button>
                                </div>
                                <!--<div class="layui-input-inline" style="width: 15%;margin-left: 26%">
                                    <button type="button" class="layui-btn layui-btn-primary search-bar-input" id="upbtn">导入名单</button>
                                </div>-->
                            </div>
                        </form>
                    </div>
                    <div class="layui-col-md2"  style="margin-top: 2%">

                        <button class="layui-btn layui-btn-primary search-bar-input" type="button" @click="addTrainUser">添加</button>
                        <button class="layui-btn layui-btn-primary search-bar-input" data-method="isDel" id="delbtn">删除</button>
                    </div>
                </div>
                <dl class="layui-nav-child choose-area" > <!-- 选地区弹出框 -->
                    <dd>
                        <form class="layui-form " lay-filter="choose-form" action="" style="width: 400px;line-height: 50px">
                            <div class="layui-input-inline" style="width: 18%;">
                                <label class="layui-form-label search-bar-label" style="width: 20%;float: right;">区域</label>
                            </div>
                            <div class="layui-input-inline" style="width: 22%;margin-left: 1%">
                                <select id="province" lay-filter="province" name="province" lay-verify="required" style="width: 40%;" lay-search="">
                                </select>
                            </div>
                            <div class="layui-input-inline" style="width: 22%;margin-left: 1%">
                                <select id="city" lay-filter="city" name="city" lay-verify="required" lay-search="">
                                </select>
                            </div>
                            <div class="layui-input-inline" style="width: 22%;margin-left: 1%">
                                <select id="county" name="county" lay-verify="required" lay-search="">
                                </select>
                            </div>
                        </form>
                    </dd>
                    <dd>
                        <div class="layui-inline" style="margin-left:50%;height: 60px;line-height: 50px;">
                            <div class="layui-input-inline" >
                                <button id="area-confirm"  class="layui-btn layui-btn-primary search-bar-input">确定</button>
                            </div>
                            <div class="layui-input-inline" style="margin-left: 22px;">
                                <button class="layui-btn layui-btn-primary search-bar-input" id="area-cancel">取消</button>
                            </div>
                        </div>
                    </dd>
                </dl>
            </div>
            <div class="layui-row">
                <table class="layui-hide" id="training-user" lay-filter="tablefilter"></table>
            </div>
        </fieldset>
        
		<div class="layui-row path" style="line-height:40px;">
			<span><a href="/index">&nbsp首页</a></span>
			<i class="path-split">\</i>
			<span><a href="/puUser/trainManage">查看培训</a></span>
			<i class="path-split">\</i>
			<span><a style="color:#c2c2c2">配置培训资源</a></span>
		</div>
		
        <!--培训资源-->
        <fieldset class="layui-elem-field layui-field-title" style="border:1px dashed #c3c1c1;padding: 10px;margin-bottom: 60px; text-align:center">
            <h3 class="types-title" style="line-height: 40px;">
            <i class="layui-icon" style="font-size: 40px; color: #5fb878;">&#xe62e;</i>
            <span style="font-size: 20px;"><em>培</em>／<em>训</em>／<em>资</em>／<em>源</em></span>
            <i class="layui-icon" style="font-size: 40px; color: #5fb878;">&#xe62e;</i>
        </h3>
            <div>
                <div class="layui-row search-bar">
                    <div class="layui-col-md10"  style="margin-top: 2%">
                        <form class="layui-form " action="">
                            <div class="layui-inline" style="width: 25%">
                                <label class="layui-form-label search-bar-label" style="width: 37%;"><span>必修：</span><span>{{training.optionalScore}}分</span></label>
                                <label class="layui-form-label search-bar-label" style="width: 37%;"><span>选修：</span><span>{{training.requiredScore}}分</span></label>
                            </div>
                            <div class="layui-inline" style="width: 18%">
                                <label class="layui-form-label search-bar-label" style="width: 18%;">讲师</label>
                                <div class="layui-input-inline"  style="width: 60%;">
                                    <input id="tname" type="text" name="tname"  autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline" style="width: 18%">
                                <label class="layui-form-label search-bar-label"  style="width: 20%;">条线</label>
                                <div class="layui-input-inline"  style="width: 60%;">
							        <select id="searchResTypeId" name="typeId" lay-verify="required" lay-search="">
							          <option value="">请选择条线</option>
							          <option value="1">组织条线</option>
									  <option value="2">安全生产</option>
									  <option value="3">组织宣传</option>
							        </select>
                                </div>
                            </div>
                            <div class="layui-inline" style="width: 18%">
                                <label class="layui-form-label search-bar-label" style="width: 20%;">等级</label>
                                <div class="layui-input-inline" style="width: 60%;">
							        <select id="searchResLevel" name="level" lay-verify="required" lay-search="">
							          <option value="">请选择等级</option>
							          <option value="1">国家级</option>
							          <option value="2">省部级</option>
							          <option value="3">司厅局级</option>
							          <option value="4">县处级</option>
							          <option value="5">乡镇级</option>
							        </select>
                                </div>
                            </div>

                            <div class="layui-inline" style="width: 19%">
                                <div class="layui-input-inline" style="width: 15%;">
                                    <button type="button" class="layui-btn layui-btn-primary search-bar-input" @click="searchTrainResource">查询</button>
                                </div>
 								<!--<div class="layui-input-inline" style="width: 15%;margin-left: 26%">
                                    <button type="button" class="layui-btn layui-btn-primary search-bar-input" id="upbtn2">导入名单</button>
                                </div>-->
                            </div>
                        </form>
                    </div>
                    <div class="layui-col-md2"  style="margin-top: 2%">
                        <button type="button" class="layui-btn layui-btn-primary search-bar-input" @click="addTrainResource">添加</button>
                        <button class="layui-btn layui-btn-primary search-bar-input" data-method="isDel2" id="delbtn2">删除</button>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <table class="layui-hide" id="training-resource" lay-filter="table"></table>
            </div>
        </fieldset>

    </div>



</div>

</div>
<#include "../footer.ftl">	
<#include "../linkage.ftl">
<script src="../../js/common/areaLayer.js"></script>
<script src="../../js/train/trainConfig.js"></script>



</body>
</html>