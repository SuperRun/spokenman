<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人信息-账号绑定</title>
    
	<#include "../../base.ftl">
    <link rel="stylesheet" type="text/css" href="../../css/user/perUser/accountBind.css">
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
                                <li class="layui-nav-item layui-nav-itemed"><a  class="on">账号绑定<span class="arr"><i class="layui-icon">&#xe602;</i></span></a></li>
                                <li  class="layui-nav-item"><a href="/perUser/fillPerInfo">个人信息<span class="arr"><i class="layui-icon">&#xe602;</i></span></a></li>
                                <li  class="layui-nav-item"><a href="/user/oplog">操作记录<span class="arr"><i class="layui-icon">&#xe602;</i></span></a></li>
                                <li  class="layui-nav-item"><a href="/perUser/authenticate">实名认证<span class="arr"><i class="layui-icon">&#xe602;</i></span></a></li>
                                </ul>

                        </div>
                    </div>
                    <div class="setting-right">
                        <div class="setting-right-wrap wrap-boxes settings">

                            <div class="page-settings">

                                <div class="common-title">
                                    账号绑定 <span class="title-tips">完成<b class="color-red">3/3</b></span>
                                </div>
                                <div class="line"></div>
                                <div class="setting">
                                    <div class="contentBox">
                                        <div class="bingd">
                                            <div class="itemBox">
                                                <div class="left">	<i class="layui-icon">&#xe62a;</i></div>
                                                <div class="center">
                                                    <p><span class="font1">邮箱</span>
                                                        <span class="font3">
                                                            745422351@qq.com
                                                            <span>已绑定</span>
                                                        </span>
                                                    </p>
                                                    <p class="font2">可用邮箱加密码登录慕课网，可用邮箱找回密码</p>
                                                </div>
                                                <div class="right">
                                                    <button  data-method="setEmail" id="binding-email" class="layui-btn layui-btn-primary">更改</button>

                                                </div>
                                            </div>
                                            <div class="itemBox">
                                                <div class="left"> <i class="layui-icon">&#xe63b;</i></div>
                                                <div class="center">
                                                    <p> <span class="font1">手机</span> <span class="font4" id="jsPhone">已绑定</span></p>
                                                    <p class="font2">可用手机号加密码登录慕课网，可通过手机号找回密码 </p>
                                                </div>
                                                <div class="right">
                                                    <button  data-method="setPhone" id="binding-phone" class="layui-btn layui-btn-primary">更改</button>
                                                </div>
                                            </div>

                                            <div class="itemBox">
                                                <div class="left"><i class="layui-icon">&#xe620;</i></div>
                                                <div class="center">
                                                    <p> <span class="font1">密码</span> 已设置</p>
                                                    <p class="font2">用于保护账号信息和登录安全</p>
                                                </div>
                                                <div class="right">
                                                    <button  data-method="setPwd" id="binding-pwd" class="layui-btn layui-btn-primary">更改</button>
                                                </div>
                                            </div>


                                        </div>
                                    </div>
                                </div>
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
    <!--更换密码-->
    <div class="layui-fluid" id="ch-pwd"  style="display: none;">
        <div class="layui-row">
            <div class="layui-col-md12" style="margin-top: 30px">
                <div class="portrait">
                    <div class="pr-avator">
                        <form class="layui-form" action="">
                            <div class="layui-form-item">
                                <label class="layui-form-label">旧密码</label>
                                <div class="layui-input-block">
                                    <input type="password" lay-verify="required|pass"   class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">新密码</label>
                                <div class="layui-input-block">
                                    <input type="password" lay-verify="pass" id="newpwd"  class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">确认密码</label>
                                <div class="layui-input-block">
                                    <input type="password" lay-verify="pass|surepwd" name="surepwd"  class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item pr-btn">
                            <div class="layui-input-block">
                                <a class="layui-btn" lay-submit="" lay-filter="demo1">提交</a>
                                <a type="reset" class="layui-btn layui-btn-primary">重置</a>
                            </div>
                        </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--更换手机-->
    <div class="layui-fluid" id="ch-phone"  style="display: none;">
        <div class="layui-row">
            <div class="layui-col-md12" style="margin-top: 30px">
                <div class="portrait">
                    <div class="pr-avator">
                        <form class="layui-form" action="">
                            <div class="layui-form-item">
                                <label class="layui-form-label">手机号码</label>
                                <div class="layui-input-block">
                                    <input type="text" lay-verify="email"   class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item pr-btn">
                                <div class="layui-input-block">
                                    <a class="layui-btn" lay-submit="" lay-filter="demo1">提交</a>
                                    <a type="reset" class="layui-btn layui-btn-primary">重置</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--更换邮箱-->
    <div class="layui-fluid" id="ch-email"  style="display: none;">
        <div class="layui-row">
            <div class="layui-col-md12" style="margin-top: 30px">
                <div class="portrait">
                    <div class="pr-avator">
                        <form class="layui-form" action="">
                            <div class="layui-form-item">
                                <label class="layui-form-label">邮箱地址</label>
                                <div class="layui-input-block">
                                    <input type="text" lay-verify="email"   class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item pr-btn">
                                <div class="layui-input-block">
                                    <a class="layui-btn" lay-submit="" lay-filter="demo1">提交</a>
                                    <a type="reset" class="layui-btn layui-btn-primary">重置</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../../footer.ftl">
<script src="../../js/user/perUser/accountBind.js"></script>

</body>
</html>