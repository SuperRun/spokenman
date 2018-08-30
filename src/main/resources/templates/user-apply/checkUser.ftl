<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>审核用户信息</title>
    <#include "../base.ftl">
    <link rel="stylesheet" type="text/css" href="../../css/user/perUser/accountBind.css">
    <link rel="stylesheet" type="text/css" href="../../css/user/perUser/setprofile.css">
    <link rel="stylesheet" type="text/css" href="../../css/user-apply/checkUser.css">
</head>

<body>

<#include "../header.ftl">
<#include "../platformMenu.ftl">

<div class=" management" id="app">
    <div class="layui-row" style="position: relative">
        <div class="page-home js-usercard-box" id="notices">
            <div class="settings-cont clearfix">
                <div class="setting-right">
                    <div class="setting-right-wrap wrap-boxes settings">

                        <div class="page-settings">
                            <form class="layui-form profile" action="">
                                <div class="common-title">
                                    <strong>{{userInfo.name}}</strong>的个人信息
                                </div>
                                <fieldset class="layui-elem-field layui-field-title">
                                    <legend>组织信息</legend>
                                    <div class="line"></div>
                                </fieldset>

                                <div class="layui-form-item">
                                    <label class="layui-form-label">姓名</label>
                                    <div class="layui-input-block">
                                       <span>{{userInfo.name}}</span>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">所属条线</label>
                                        <div class="layui-input-inline">
                                            <span>{{userInfo.typeName}}</span>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">所属级别</label>
                                        <div class="layui-input-inline">
                                            <span>{{userInfo.levelName}}</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">所属组织</label>
                                    <div class="layui-input-block">
                                        <span>{{userInfo.orgName}}</span>
                                    </div>
                                </div>
                                <div class="layui-form-item orag-info">
                                    <div class="layui-inline">
                                        <div class="layui-input-inline">
                                            <img class="course-banner" v-bind:src="userInfo.picOrg">
                                        </div>
                                    </div>
                                </div>

                                <fieldset class="layui-elem-field layui-field-title">
                                    <legend>个人信息</legend>
                                    <div class="line"></div>
                                </fieldset>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">手机</label>
                                        <div class="layui-input-inline">
                                            <span>{{userInfo.phone}}</span>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">邮箱</label>
                                        <div class="layui-input-inline">
                                            <span>{{userInfo.email}}</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                	
                                	<div class="layui-inline">
                                        <label class="layui-form-label">身份证</label>
                                        <div class="layui-input-inline">
                                            <span>{{userInfo.idCard}}</span>
                                        </div>
                                    </div>   
                                    <div class="layui-inline">
                                        <label class="layui-form-label">所在地</label>
                                        <div class="layui-input-inline" style="line-height: 40px;font-size:16px;">
                                            {{userInfo.areaName}}
                                        </div>
                                        
                                    </div>                                 
                                </div>
                       
                                <div class="layui-form-item orag-info">
                                    <div class="layui-inline">
                                        <div class="layui-input-inline">
                                            <img class="course-banner" v-bind:src="userInfo.picFront">
                                        </div>
                                        <div class="layui-input-inline">
                                            <img class="course-banner" v-bind:src="userInfo.picBack">
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item" style="margin-top:5%;">
                                    <div class="layui-input-block">
                                        <button class="layui-btn" type="button" @click="agree" style="width:150px;margin-left:25%;border-radius: 50px">审核通过</button>
                                        <button class="layui-btn layui-btn-danger" type="button" @click="disagree" style="width:150px;margin-left:10px;border-radius: 50px">审核不通过</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<#include "../footer.ftl">
<script src="../../js/user-apply/checkUser.js"></script>
</body>

</html>