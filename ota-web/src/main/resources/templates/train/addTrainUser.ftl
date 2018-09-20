<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>培训配置-新增培训用户</title>
    <#include "../addEditBase.ftl">
    <#include "../linkage.ftl">
    <link rel="stylesheet" type="text/css" href="../../css/style1.css">
    <link rel="stylesheet" type="text/css" href="../../css/train/addTrainUser.css">
    <script src="../../js/common/areaLayer.js"></script>
</head>

<body>
<div id="app">

<div class="layui-fluid management">
	<input id="userType" type="hidden" value="${(Session.sessionInfo.type)}"  class="layui-input"/>
    <div class="layui-main">
        <div id="edit-unit-user" style="padding: 20px;">
            <div>
                <div class="layui-row search-bar">
                    <div class="layui-col-md12"  style="margin-top: 2%">
                        <form class="layui-form " action="" id="search-info">
                            <div class="layui-inline" style="width: 20%">
                                <label class="layui-form-label search-bar-label" style="width: 15%;">区域</label>
                                <div class="layui-input-inline"  style="width: 70%;">
                                    <input id="area" type="text" name="area"  lay-verify="area" placeholder="浙江省-杭州市-西湖区" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline" style="width: 15%">
                                <label class="layui-form-label search-bar-label"  style="width: 20%;">条线</label>
                                <div class="layui-input-inline"  style="width: 60%;">
                                    <select id="searchTypeId" lay-verify="required" lay-search="">
                                        <option value="">安全生产</option>
                                        <option value="2">教育</option>
                                        <option value="3">卫生</option>
                                        <option value="4">宣传</option>
                                        <option value="5">组织</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline" style="width: 15%">
                                <label class="layui-form-label search-bar-label" style="width: 20%;">等级</label>
                                <div class="layui-input-inline" style="width: 60%;">
                                    <select id="searchLevel" name="level" lay-verify="required" lay-search="">
                                        <option value="">国家级</option>
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
                                    <input id="sname" type="text" name="sname"   autocomplete="off" class="layui-input">

                                </div>
                            </div>
                            <div class="layui-inline" style="width: 25%">
                                <div class="layui-input-inline" style="width: 15%;">
                                    <button type="button" @click="searchTrainUser" class="layui-btn layui-btn-primary search-bar-input">查询</button>
                                </div>
                                <div class="layui-input-inline" style="width: 15%;margin-left: 26%">
                                    <button type="button" class="layui-btn layui-btn-primary search-bar-input" @click="addTrainUser">添加</button>
                                </div>
                                <div class="layui-input-inline" style="width: 15%;margin-left: 26%">
                                    <button class="layui-btn layui-btn-primary search-bar-input" data-method="isDel" id="delbtn">删除</button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
                <dl class="layui-nav-child choose-area"> <!-- 选地区弹出框 -->
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
                <table class="layui-hide" id="training-users" lay-filter="tablefilter"></table>
            </div>
        </div>
    </div>
</div>
</div>
<script src="../../js/train/addTrainUser.js"></script>
</body>


</html>