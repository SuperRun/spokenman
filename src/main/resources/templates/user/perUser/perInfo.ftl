<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>完善个人信息</title>
    <#include "../../base.ftl">
    <link rel="stylesheet" type="text/css" href="../../css/user/perUser/accountBind.css">
    <link rel="stylesheet" type="text/css" href="../../css/user/perUser/setprofile.css">
</head>

<body>

<#include "../../perHeader.ftl">

<div class=" management" style="">

    <div class="layui-row" style="position: relative">
        <div class="page-home js-usercard-box" id="notices">
            <div class="settings-cont clearfix">
                <div class="setting-left l">
                    <div class="avator-wapper">
                        <div class="avator-mode">
                            <img class="avator-img" src="//img.mukewang.com/54584dd900014f6c02200220-200-200.jpg" data-portrait="54584dd900014f6c02200220" width="92" height="92">
                            <div class="update-avator" style="bottom: -30px;">
                                <p><a href="javascript:void(0);" data-method="setTop" class="change-pr">更换头像</a></p>
                            </div>
                        </div>
                        <div class="des-mode">
                            <p>麦q</p>
                            <dl class=" clearfix auth-list">
                                <dd><i class="layui-icon">&#xe612;</i></dd>

                                <dd><i class="layui-icon">&#xe63c;</i></dd>

                                <dd><i class="layui-icon">&#xe63b;</i></dd>
                                <dd class="active"><i class="layui-icon">&#xe62a;</i></dd>
                            </dl>
                        </div>
                    </div>

                    <div class="list-wapper">
                        <h2>账户管理</h2>
                        <div class="line"></div>
                        <ul class="layui-nav layui-nav-tree menu">
                            <li class="layui-nav-item"><a href="/perUser/accountBind">账号绑定<span class="arr"><i class="layui-icon">&#xe602;</i></span></a></li>
                            <li  class="layui-nav-item layui-nav-itemed"><a class="on">个人信息<span class="arr"><i class="layui-icon">&#xe602;</i></span></a></li>
                            <li  class="layui-nav-item"><a href="/user/oplog">操作记录<span class="arr"><i class="layui-icon">&#xe602;</i></span></a></li>
                            <li  class="layui-nav-item"><a href="/perUser/authenticate">实名认证<span class="arr"><i class="layui-icon">&#xe602;</i></span></a></li>
                        </ul>

                    </div>
                </div>
                <div class="setting-right">
                    <div class="setting-right-wrap wrap-boxes settings">

                        <div class="page-settings">
                            <form class="layui-form profile" action="" id="userInfo">
                                <fieldset class="layui-elem-field layui-field-title">
                                    <legend>基础信息</legend>
                                    <div class="line"></div>
                                </fieldset>

                                <div class="layui-form-item">
                                    <label class="layui-form-label">姓名</label>
                                    <div class="layui-input-block">
                                        <input type="text"  name="username" lay-verify="required"  class="layui-input">
                                    </div>
                                    <!--<div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>-->
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
					                    <label class="layui-form-label">所属条线</label>
					                    <div class="layui-input-inline">
					                        <select name="typeId" lay-verify="required" lay-search="">
										          <option value="">全部</option>
										          <option value="1">组织条线</option>
												  <option value="2">安全生产</option>
												  <option value="3">组织宣传</option>
					                        </select>
					                    </div>
                                    </div>
                                    <div class="layui-inline">
					                    <label class="layui-form-label">级别</label>
					                    <div class="layui-input-inline">
					                        <select name="level" lay-verify="required" lay-search="">
										          <option value="">全部</option>
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
                                        <label class="layui-form-label">所属组织</label>
                                        <div class="layui-input-inline">
                                            <input name="orgName" type="text" class="layui-input">
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                    
                                </div>
                                <fieldset class="layui-elem-field layui-field-title">
                                    <legend>个人信息</legend>
                                    <div class="line"></div>
                                </fieldset>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">手机</label>
                                        <div class="layui-input-inline">
                                            <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input">
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">邮箱</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">所在地</label>

                                    <div class="layui-input-inline change-select" style="width:22%;margin-right: 13px;">
                                        <select id="change-province" lay-filter="change-province" name="change-province">
                                        </select>
                                    </div>
                                    <div class="layui-input-inline change-select" style="width:22%;margin-right: 13px;">
                                        <select id="change-city" lay-filter="change-city" name="change-city">
                                        </select>
                                    </div>
                                    <div class="layui-input-inline change-select" style="width:22%;margin-right: 0px;">
                                        <select id="areaId" lay-filter="areaId" name="areaId">
                                        </select>
                                    </div>
                                </div>

                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn layui-btn-radius" style="margin-left:35%;width:150px;margin-top:5%;" onclick="saveInfo(${Session.sessionInfo.otaUserId})">保存</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <script type="text/javascript">
                            var hasPass;
                            hasPass=true;</script>


                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--更换头像-->
    <div class="layui-fluid" id="ch-portrait"  style="display: none;">
        <div class="layui-row">
            <div class="layui-col-md8 layui-col-md-offset3">
                <div class="portrait">
                    <div class="pr-avator">
                        <div class="layui-upload">
                            <div class="layui-upload-list">
                                <div class="avator-mode">
                                    <img class="layui-upload-img" id="demo1" src="//img.mukewang.com//533e4cbd00011ecc01000100.jpg"  width="192" height="192">
                                    <p id="demoText"></p>
                                </div>
                            </div>
                            <a type="button" class="" id="test1">上传图片</a>

                        </div>
                        <div class="pr-btn">
                            <a href="javascript:void(0);" class="layui-btn">确定</a>
                            <a href="javascript:void(0);" class="layui-btn layui-btn-primary">取消</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../../footer.ftl">
<#include "../../linkage.ftl">
<script src="../../js/user/perUser/setbindsns.js"></script>
<script src="../../js/user/perUser/setprofile.js"></script>
</body>
</html>