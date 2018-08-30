<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人信息-实名验证</title>
	<#include "../../common.ftl">
    <link rel="stylesheet" type="text/css" href="../../css/user/perUser/accountBind.css">
    <link rel="stylesheet" type="text/css" href="../../css/user/perUser/setprofile.css">
    <link rel="stylesheet" type="text/css" href="../../css/user/perUser/progress.css">
    <link rel="stylesheet" type="text/css" href="../../css/uploadFile.css">
</head>

<body>
<#include "../../perHeader.ftl">
<div id="app">
<div class=" management" style="">
	<input id="userId" type="hidden" value="${(Session.sessionInfo.otaUserId)}"  class="layui-input"/>
    <div class="layui-row" style="position: relative">
        <div class="page-home js-usercard-box" id="notices">
            <div class="settings-cont clearfix">
                <div class="setting-left l" style="min-height: 929.5px;">
                    <div class="avator-wapper">
                        <div class="avator-mode">
                            <img class="avator-img" src="//img.mukewang.com/54584dd900014f6c02200220-200-200.jpg" data-portrait="54584dd900014f6c02200220" width="92" height="92">
                            <div class="update-avator" style="bottom: -30px;">
                                <p><a href="javascript:void(0);" data-method="setTop" class="change-pr">更换头像</a></p>
                            </div>
                        </div>
                        <div class="des-mode">
                            <p>{{userApply.name}}</p>
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
                            <li class="layui-nav-item"><a href="/perUser/fillPerInfo" >个人信息<span class="arr"><i class="layui-icon">&#xe602;</i></span></a></li>
                            <li class="layui-nav-item"><a href="/user/oplog">操作记录<span class="arr"><i class="layui-icon">&#xe602;</i></span></a></li>
                            <li class="layui-nav-item layui-nav-itemed"><a href="/user/authenticate" class="on">实名认证<span class="arr"><i class="layui-icon">&#xe602;</i></span></a></li>
                        </ul>

                    </div>
                </div>
                <div class="setting-right">
                    <div class="setting-right-wrap wrap-boxes settings">

                        <div class="page-settings">
							<div id="crumbs">
							  <ul>
							    <li class="status1"><a>提交认证</a></li>
							    <li class="status1"><a>等待审核</a></li>
							    <li class="status1"><a>审核通过</a></li>
							  </ul>
							</div>
							
                            <fieldset class="layui-elem-field layui-field-title">
                                <legend>身份认证</legend>
                                <div class="line"></div>
                            </fieldset>

                            <div class="layui-upload">
                                <form class="layui-form profile" action="">
                                    <div class="layui-form-item">
                                        <div class="layui-inline">
                                            <label class="layui-form-label">真实姓名</label>
                                            <div class="layui-input-block">
                                                <input type="text"  v-model="userApply.name" lay-verify="required"  class="layui-input">
                                            </div>
                                        </div>
                                        <div class="layui-inline">
                                            <label class="layui-form-label">身份证</label>
                                            <div class="layui-input-block">
                                                <input type="text" v-model="userApply.idCard"  lay-verify="identity"  autocomplete="off" class="layui-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-form-item" style="margin-top:20px">
                                        <div id="front" class="identityPic">
                                            <i class="layui-icon">&#xe654;</i>
                                            <p>上传身份证正面照片</p>
                                            <img id="frontPic" :src="imgSrc.front"/>
                                        </div>
                                        <div id="back" class="identityPic">
                                            <i class="layui-icon">&#xe654;</i>
                                            <p>上传身份证反面照片</p>
                                            <img id="backPic" :src="imgSrc.back"/>
                                        </div>
                                        <input style="display:none"  type="file" id="uploadFront">
                                        <input style="display:none"  type="file" id="uploadBack">
                                    </div>
									<div class="layui-form-item" style="margin-top:20px">
										<span style="color: #c9c7c7;font-size: 12px;margin-left: 12%;">小贴士：需上传正反面两面哦~</span>
									</div> 
                                </form>

                            </div>

                            <fieldset class="layui-elem-field layui-field-title" style="margin-top:60px">
                                    <legend>组织认证</legend>
                                    <div class="line"></div>
                            </fieldset>

                            <div class="layui-upload">
                                <form class="layui-form profile" lay-filter="orgForm">

                                    <div class="layui-form-item">
                                        <div class="layui-inline" style="width:30%">
                                            <label class="layui-form-label" style="width:30%">所属条线</label>
                                            <div class="layui-input-inline" style="width:50%">
                                               <select class="getType" id="typeId" lay-search="">
				 								  <option value="">请选择条线</option>
							                   </select>
                                            </div>
                                        </div>
                                        <div class="layui-inline" style="width:30%">
                                            <label class="layui-form-label" style="width:30%">所属级别</label>
                                            <div class="layui-input-inline" style="width:50%">
                                                <select id="level" lay-search="">
										          <option value="">请选择等级</option>
										          <option value="1">国家级</option>
										          <option value="2">省部级</option>
										          <option value="3">司厅局级</option>
										          <option value="4">县处级</option>
										          <option value="5">乡镇级</option>
										        </select>
                                            </div>
                                        </div>
                                         <div class="layui-inline" style="width:30%">
                                            <label class="layui-form-label" style="width:30%">所属组织</label>
                                            <div class="layui-input-inline" style="width:50%">
                                                <input id="orgText" type="text" v-model="userApply.orgName"  lay-verify="email" autocomplete="off" class="layui-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                       
                                     	<div class="layui-inline" >
                                            <label class="layui-form-label">所在地区</label>
                                            <div class="layui-input-inline"  style="width: 20%;">
                                            	<select id="province" lay-filter="province" name="province" lay-search="">
									        	</select>
									        </div>
									        <div class="layui-input-inline"  style="width: 20%;">
                                            	<select id="city" lay-filter="city" name="city" lay-search="">
									        	</select>
									        </div>
									        <div class="layui-input-inline"  style="width: 20%;">
                                            	 <select id="county" lay-filter="county" name="county" lay-search="">
									        	</select>
									        </div>
									    </div>
										
                                    </div>
                                    <div class="layui-form-item">
                                        <div id="org" class="identityPic">
                                            <i class="layui-icon">&#xe654;</i>
                                            <p>上传组织证明照片</p>
                                            <img id="orgPic"  :src="imgSrc.org"/>
                                        </div>
                                        <input style="display:none"  type="file" id="uploadOrg">
                                    </div>
                                    <div class="layui-form-item">
                                        <span style="color: #c9c7c7;font-size: 12px;margin-left: 12%;">小贴士:最多上传一张</span>
                                    </div>
                                    <div class="layui-form-item">
                                        <div class="layui-input-block">
                                            <button class="layui-btn" type="button" @click="subApplyApproval" style="margin-left:33%;border-radius: 50px;width:150px">提交审核</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            </form>
                        </div>

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
</div>
<#include "../../footer.ftl">
<#include "../../linkage.ftl">
<script src="../../js/user/perUser/authenticate.js"></script>
</body>
</html>