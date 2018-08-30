<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>培训介绍页</title>
    <#include "../common.ftl">
    <link rel="stylesheet" type="text/css" href="../../css/resources/resourceDetail.css">
</head>

<body>
<#include "../courseHeader.ftl">

<div id="app" class=" management" style="">
	<input id="userId" type="hidden" value="${(Session.sessionInfo.otaUserId)}"  class="layui-input"/>
    <div class="bg-other user-head-info">
        <div class="courses" style="padding-bottom: 0px;">
            <div class="courses-info">
                <div class="path">
                    <a href="/course/list">培训</a>
                    <span class="path-split">\</span><a href="/course/list?c=fe">{{train.typeIdName}}</a>
                </div>
                <div class="courses-star-box">
                    <i class="layui-icon">&#xe600;</i><span>收藏</span>
                    <!--<i class="layui-icon">&#xe677;</i>-->
                    <!--<i class="layui-icon">&#xe676;</i>-->
                    <!--<i class="layui-icon">&#xe675;</i>-->
                </div>
                <h2 class="courses-name">
                    <span>{{train.name}}</span>
                </h2>
                <div class="statics">
                    <div class="teacher-info l">
                        <a>
                            <img data-userid="494742" class="js-usercard-dialog" src="//img4.mukewang.com/58c64f960001794b02500250-80-80.jpg" width="80" height="80">
                        </a>
                        <span class="tit">
                            <a>{{train.userName}}</a>
                        </span>
                        <span class="job">{{train.lecturerName}}</span>
                        <div class="teacher-course">
                            <div class="teacher-course-arrow"><span></span></div>
                        </div>
                    </div>
                    <div class="static-item l">
                        <span class="meta">必修学分</span><span class="meta-value">{{train.requiredScore}}</span>
                    </div>
                    <div class="static-item l">
                        <span class="meta">选修学分</span><span class="meta-value">{{train.optionalScore}}</span>
                    </div>
                    <!--<div class="static-item l">
                        <span class="meta">学习人数</span><span class="meta-value js-learn-num">169602</span>
                    </div>
                    <div class="static-item l score-btn">
                        <span class="meta">综合评分</span><span class="meta-value">9.3</span>
                    </div>-->

                </div>
            </div>

        </div>
    </div>
    <div class="layui-row" style="position: relative">
        <div class="d-container">
            <div class="page-home" id="notices">
                <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                    <ul class="layui-tab-title">
                        <li class="layui-this">课程章节</li>
                        <li>问答评论</li>
                        <li>同学笔记</li>
                        <li>用户评价</li>
                    </ul>
                    <div class="layui-tab-content" style="">
                        <div class="layui-tab-item   layui-show">
                            <div class="course-description course-wrap">
                                	简介：{{train.description}}
                            </div>
                            <div class="chapter course-wrap" v-for="(trainRes,index) in trainResource">
                                <h3>
                                    {{trainRes.resourceName}}
                                </h3>
                                <ul class="video">
                                    <li data-media-id="17023"><em class="laststudy">最近学习</em>
                                        <a  class="media-item" @click="learnRes(trainRes.resourceId)">
                                            <i class="layui-icon ing">&#xe617;</i>
                                            <i class="layui-icon type">&#xe652;</i>
                                            <span> {{trainRes.resourceName}}</span>
                                            <span>(02:47)</span>
                                        </a>
                                    </li>
                                </ul>
                           </div>
                      </div>
                        
                        
                      <div class="layui-tab-item">
                            <div class="course-wrap mod-qa-list">
                                <div class="headslider qa-medias l">
                                    <a href="/u/4274469/courses" class="media" target="_blank">
                                        <img src="//img1.mukewang.com/59df5c1d0001ea5c04800480-40-40.jpg" width="40" height="40">
                                    </a>
                                </div>
                                <div class="wendaslider qa-content">
                                    <h2 class="wendaquetitle qa-header">
                                        <div class="wendatitlecon qa-header-cnt clearfix">
                                            <a href="/qadetail/258763" target="_blank" class="qa-tit">
                                                <i>为啥我安装cnpm会报错</i>
                                            </a>
                                        </div>
                                    </h2>
                                    <div class="replydes">
                                        <span class="replysign">最新回答 / <a href="/u/4274469/courses" target="_blank" title="w_sun" class="nickname">w_sun</a></span>
                                        <div class="replydet">是下载失败了嘛</div>
                                    </div>
                                    <div class="replymegfooter qa-footer clearfix">
                                        <div class="l-box l">
                                            <a href="/qadetail/258763" target="_blank" class="replynumber static-count ">
                                            <span class="static-item answer">
                                                1 回答
                                            </span>
                                            <span class="static-item">
                                                9 浏览
                                            </span>
                                            </a>
                                        </div>
                                        <em class="r">时间：23小时前</em>

                                    </div>
                                </div>
                            </div>
                            <div class="course-wrap mod-qa-list">
                                <div class="headslider qa-medias l">
                                    <a href="/u/4274469/courses" class="media" target="_blank">
                                        <img src="//img1.mukewang.com/59df5c1d0001ea5c04800480-40-40.jpg" width="40" height="40">
                                    </a>
                                </div>
                                <div class="wendaslider qa-content">
                                    <h2 class="wendaquetitle qa-header">
                                        <div class="wendatitlecon qa-header-cnt clearfix">
                                            <a href="/qadetail/258763" target="_blank" class="qa-tit">
                                                <i>为啥我安装cnpm会报错</i>
                                            </a>
                                        </div>
                                    </h2>
                                    <div class="replydes">
                                        <span class="replysign">最新回答 / <a href="/u/4274469/courses" target="_blank" title="w_sun" class="nickname">w_sun</a></span>
                                        <div class="replydet">是下载失败了嘛</div>
                                    </div>
                                    <div class="replymegfooter qa-footer">
                                        <div class="l-box l">
                                            <a href="/qadetail/258763" target="_blank" class="replynumber static-count ">
                                            <span class="static-item answer">
                                                1 回答
                                            </span>
                                                <span class="static-item">
                                                9 浏览
                                            </span>
                                            </a>
                                        </div>
                                        <em class="r">时间：23小时前</em>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="layui-tab-item">
                            <div class="course-wrap mod-qa-list">
                                <div class="headslider qa-medias l">
                                    <a href="/u/4274469/courses" class="media" target="_blank">
                                        <img src="//img1.mukewang.com/59df5c1d0001ea5c04800480-40-40.jpg" width="40" height="40">
                                    </a>
                                </div>
                                <div class="wendaslider qa-content">
                                    <h2 class="wendaquetitle qa-header">
                                        <div class="wendatitlecon qa-header-cnt clearfix">
                                            <a href="/qadetail/258763" target="_blank" class="qa-tit">
                                                豆芽花花儿酱
                                            </a>
                                        </div>
                                    </h2>
                                    <div class="comm-main">
                                        <a href="javascript:;" class="r tipoff" data-src="/video/12308">举报</a>
                                        <div class="notelist-content">
                                            <div class="note-content"><p>单页面的路由功能</p></div>
                                        </div>

                                    </div>
                                    <div class="replymegfooter qa-footer">
                                        <div class="actions l">
                                                <a title="点赞" href="javascript:;" class="Jpraise moco-btn moco-btn-gray-l l">
                                                    <i class="layui-icon">&#xe6c6;</i>
                                                    <em>0</em>
                                                </a>
                                                <a href="/notepad/201715" target="_blank" class="post-action">查看全文</a>
                                            </div>
                                        <em class="qa-r r">时间：23小时前</em>

                                    </div>
                                </div>
                            </div>
                            <div class="course-wrap mod-qa-list">
                                <div class="headslider qa-medias l">
                                    <a href="/u/4274469/courses" class="media" target="_blank">
                                        <img src="//img1.mukewang.com/59df5c1d0001ea5c04800480-40-40.jpg" width="40" height="40">
                                    </a>
                                </div>
                                <div class="wendaslider qa-content">
                                    <h2 class="wendaquetitle qa-header">
                                        <div class="wendatitlecon qa-header-cnt clearfix">
                                            <a href="/qadetail/258763" target="_blank" class="qa-tit">
                                                豆芽花花儿酱
                                            </a>
                                        </div>
                                    </h2>
                                    <div class="comm-main">
                                        <a href="javascript:;" class="r tipoff" data-src="/video/12308">举报</a>
                                        <div class="notelist-content">
                                            <div class="note-content"><p>单页面的路由功能</p></div>
                                        </div>

                                    </div>
                                    <div class="replymegfooter qa-footer">
                                        <div class="actions l">
                                            <a title="点赞" href="javascript:;" class="Jpraise moco-btn moco-btn-gray-l l">
                                                <i class="layui-icon">&#xe6c6;</i>
                                                <em>0</em>
                                            </a>
                                            <a href="/notepad/201715" target="_blank" class="post-action">查看全文</a>
                                        </div>
                                        <em class="qa-r r">时间：23小时前</em>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="layui-tab-item">
                            <div class="course-wrap mod-qa-list">
                                <div class="headslider qa-medias l">
                                    <a href="/u/4274469/courses" class="media" target="_blank">
                                        <img src="//img1.mukewang.com/59df5c1d0001ea5c04800480-40-40.jpg" width="40" height="40">
                                    </a>
                                </div>
                                <div class="wendaslider qa-content">
                                    <h2 class="wendaquetitle qa-header">
                                        <div class="wendatitlecon qa-header-cnt clearfix">
                                            <a href="/qadetail/258763" target="_blank" class="qa-tit">
                                                	豆芽花花儿酱
                                            </a>
                                            <span class="qa-star-box">
                                                <i class="layui-icon active">&#xe658;</i>
                                                <i class="layui-icon active">&#xe658;</i>
                                                <i class="layui-icon active">&#xe658;</i>
                                                <i class="layui-icon">&#xe658;</i>
                                                <i class="layui-icon">&#xe658;</i>
                                                <span>8分</span>
                                            </span>
                                        </div>
                                    </h2>
                                    <div class="comm-main">
                                        <div class="notelist-content">
                                            <div class="note-content"><p>单页面的路由功能</p></div>
                                        </div>

                                    </div>
                                    <div class="replymegfooter qa-footer">
                                        <div class="actions l">
                                            <a title="点赞" href="javascript:;" class="Jpraise moco-btn moco-btn-gray-l l">
                                                <i class="layui-icon">&#xe6c6;</i>
                                                <em>0</em>
                                            </a>
                                            <a href="/notepad/201715" target="_blank" class="post-action">查看全文</a>
                                        </div>
                                        <em class="qa-r r">时间：23小时前</em>

                                    </div>
                                </div>
                            </div>
                            <div class="course-wrap mod-qa-list">
                                <div class="headslider qa-medias l">
                                    <a href="/u/4274469/courses" class="media" target="_blank">
                                        <img src="//img1.mukewang.com/59df5c1d0001ea5c04800480-40-40.jpg" width="40" height="40">
                                    </a>
                                </div>
                                <div class="wendaslider qa-content">
                                    <h2 class="wendaquetitle qa-header">
                                        <div class="wendatitlecon qa-header-cnt clearfix">
                                            <a href="/qadetail/258763" target="_blank" class="qa-tit">
                                               	 豆芽花花儿酱
                                            </a>
                                            <span class="qa-star-box">
                                                <i class="layui-icon active">&#xe658;</i>
                                                <i class="layui-icon active">&#xe658;</i>
                                                <i class="layui-icon active">&#xe658;</i>
                                                <i class="layui-icon">&#xe658;</i>
                                                <i class="layui-icon">&#xe658;</i>
                                                <span>8分</span>
                                            </span>
                                        </div>
                                    </h2>
                                    <div class="comm-main">
                                        <div class="notelist-content">
                                            <div class="note-content"><p>单页面的路由功能</p></div>
                                        </div>

                                    </div>
                                    <div class="replymegfooter qa-footer">
                                        <div class="actions l">
                                            <a title="点赞" href="javascript:;" class="Jpraise moco-btn moco-btn-gray-l l">
                                                <i class="layui-icon">&#xe6c6;</i>
                                                <em>0</em>
                                            </a>
                                            <a href="/notepad/201715" target="_blank" class="post-action">查看全文</a>
                                        </div>
                                        <em class="qa-r r">时间：23小时前</em>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="aside r">
            <div class="course-wrap course-aside-info">
                <div class="learn-btn">
                    <div class="learn-info">
                        <span>已学 {{userTrain.process}}% </span>学习耗时{{userTrain.learnedHour}}小时{{userTrain.learnedMinute}}分{{userTrain.learnedSecond}}秒
                    </div>
                    <div class="layui-progress layui-progress-big" lay-filter="trainingProcess">
                        <div class="layui-progress-bar layui-bg-red"></div>
                    </div>
                    <div v-if="isJoin"> 
                    	<a :href="'/perUser/'+userId+'/listUserTrain'" class="layui-btn layui-btn-danger">继续培训</a>
                    </div>
                    <div v-else> 
                    	<a @click="addUserTrain" class="layui-btn layui-btn-danger">开始培训</a>
                    </div>
                </div>
                <div class="course-info-tip">
                    <dl class="first">
                        <dt>课程须知</dt>
                        <dd class="autowrap">
                        	1. 有html，css，js前端开发基础
                            2. 了解前端工程化，node，webpack gulp等
                            3. 对前端模块化有基本的概念
                            4. es6 的一些基本语法
                        </dd>
                    </dl>
                    <dl>
                        <dt>老师告诉你能学到什么？</dt>
                        <dd class="autowrap">1. vuejs的背景及其项目相关知识
                            2. 了解流行的前端项目搭建方式 webpack + gulp
                            3. 用 vue-cli 脚手架工具初始化vue项目
                            4. 学习vue项目基本的结构和开发方法
                            5. 学习使用vuejs常用的接口和方法
                            6. 教程中教你如何在一个项目中使用vuejs
                        </dd>
                    </dl>
                </div>



            </div>
            <div class="commend-box">
                <div class="recom-course-list-box">
                    <h4>推荐课程</h4>
                    <ul class="recom-course-list">
                        <li class="">
                            <a href="//coding.imooc.com/class/119.html" class="clearfix" target="_blank">
                                <div class="l course-img" style="background-image: url(//img4.mukewang.com/szimg/59eeb2340001f59105400300-360-202.jpg);"></div>
                                <div class="l content-box">
                                    <p class="smalle-title">开发微信全家桶项目Vue/Node/MongoDB高级技术栈全覆盖</p>
                                    <div class="learn-detail">
                                        实战
                                        <span>·</span>
                                        高级
                                        <span>·</span>
                                        <i class="layui-icon on">&#xe612;</i>
                                        711
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="">
                            <a href="//coding.imooc.com/class/119.html" class="clearfix" target="_blank">
                                <div class="l course-img" style="background-image: url(//img4.mukewang.com/szimg/59eeb2340001f59105400300-360-202.jpg);"></div>
                                <div class="l content-box">
                                    <p class="smalle-title">开发微信全家桶项目Vue/Node/MongoDB高级技术栈全覆盖</p>
                                    <div class="learn-detail">
                                        实战
                                        <span>·</span>
                                        高级
                                        <span>·</span>
                                        <i class="layui-icon on">&#xe612;</i>
                                        711
                                    </div>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="related-box">
                <div class="mb40 recom-course-list-box">
                    <h4>相关课程</h4>
                    <ul class="js-recom-course recom-course-list clearfix">
                        <li class="clearfix"><a href="//www.imooc.com/learn/948" class="clearfix" target="_blank">
                            <div class="l course-img" style="background-image: url(//img3.mukewang.com/5a81337f000118a306000338-240-135.jpg);">

                            </div>
                            <div class="l content-box">
                                <p class="smalle-title">Weex入门</p>
                                <div class="clearfix learn-detail">
                                    中级
                                    <span>·</span>
                                    <i class="layui-icon on">&#xe612;</i>
                                    10003
                                </div>
                            </div>
                        </a>
                        </li>
                        <li class="clearfix">
                            <a href="//www.imooc.com/learn/796" class="clearfix" target="_blank">
                                <div class="l course-img" style="background-image: url(//img3.mukewang.com/587dd3d50001112f06000338-240-135.jpg);">

                                </div>
                                <div class="l content-box">
                                    <p class="smalle-title">使用vue2.0实现购物车和地址选配功能</p>
                                    <div class="clearfix learn-detail">
                                        中级
                                        <span>·</span>
                                        <i class="layui-icon on">&#xe612;</i>
                                        73221
                                    </div>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

        </div>
    </div>
</div>

<#include "../footer.ftl">

<script src="../../js/train/trainDetail.js"></script>
</body>
</html>