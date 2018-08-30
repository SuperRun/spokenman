<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户学习资源</title>
    <#include "../../perBaseFile.ftl">
    <link rel="stylesheet" type="text/css" href="../../css/user/perUser/setbindsns.css">
    <link rel="stylesheet" type="text/css" href="../../css/user/perUser/listUserResource.css">
</head>

<body>

    <#include "../../courseHeader.ftl">
	<#include "../../userInfo.ftl">
	<div id="app">
	<div class="layui-layout management" style="min-height: 800px;">
            <div class="layui-row" style="position: relative">
			<div class="slider" id="slider">
				<ul>
					<li>
						<a class="active">
							<i class="layui-icon">&#xe617;</i><span>动态</span><b class="icon-drop_right"></b>
						</a>
					</li>
					<li>
						<a>
							<i class="layui-icon">&#xe617;</i><span>课程</span><b class="icon-drop_right"></b>
						</a>
					</li>
					<li>
						<a href="/perUser/${(Session.sessionInfo.otaUserId)}/listUserTrain">
							<i class="layui-icon">&#xe617;</i><span>培训</span><b class="icon-drop_right"></b>
						</a>
					</li>
					<li>
						<a>
							<i class="layui-icon">&#xe617;</i><span>考试</span><b class="icon-drop_right"></b>
						</a>
					</li>
					<li>
						<a>
							<i class="layui-icon">&#xe617;</i><span>笔记</span><b class="icon-drop_right"></b>
						</a>
					</li>
					<li>
						<a>
							<i class="layui-icon">&#xe617;</i><span>猿问</span><b class="icon-drop_right"></b>
						</a>
					</li>
					<li>
						<a>
							<i class="layui-icon">&#xe617;</i><span>手记</span><b class="icon-drop_right"></b>
						</a>
					</li>
					<li>
						<a>
							<i class="layui-icon">&#xe617;</i><span>wiki</span><b class="icon-drop_right"></b>
						</a>
					</li>
				</ul>
			</div>
            <div class="u-container">
                <div class="page-home js-usercard-box" id="notices">
					<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
						<ul class="layui-tab-title">
							<li class="layui-this">最近学习</li>
							<li>我的收藏</li>
						</ul>
						<div class="layui-tab-content" style="">
							<div class="layui-tab-item layui-show">
                                <ul class="layui-timeline" >
                                    <li class="layui-timeline-item" v-for="(res,index) in resList">
                                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                                        <div class="layui-timeline-content layui-text">
                                            <h3 class="layui-timeline-title">{{res.updateTime}}</h3>
                                            <div class="course-list course-list-m">
                                                <ul class="clearfix">
                                                    <li class="course-one" data-courseid="694" data-uid="2538682">
                                                        <div class="course-list-img l">
                                                            <a v-bind:href="'/video/' + res.resourceId +'?id='+res.id" target="_blank">
                                                                <img width="200" height="113" v-bind:alt="res.name" v-bind:src="res.pic">
                                                            </a>
                                                        </div>
                                                        <div class="course-list-cont">
                                                            <h3 class="study-hd">
                                                                <a v-bind:href="'/video/' + res.resourceId +'?id='+res.id" target="_blank">{{res.name}}</a>

                                                                <span class="i-new">{{res.statusName}}</span>
                                                                <!-- 更新完毕 -->
                                                                <!-- 收藏和删除 -->
                                                                <div class="share-box clearfix">
                                                                    <div class="show-btn"><i class="icon-down2"></i></div>
                                                                    <div class="share-box-con courses-r">
                                                                        <a href="javascript:;" title="收藏" class="follow custom_f"><i class="icon icon-star_outline"></i></a>
                                                                        <a href="javascript:;" title="删除" class="del"><i class="icon icon-notdisplay"></i></a>
                                                                    </div>
                                                                </div>
                                                            </h3>
                                                            <div class="study-points">
                                                                <span class="i-left span-common">已学{{res.process}}%</span>
                                                                <span class="i-mid span-common">用时{{res.learnedHour}}小时{{res.learnedMinute}}分{{res.learnedSecond}}秒</span>
                                                            </div>
                                                            <div class="catog-points">
                                                                <span class="i-left span-common"><a href="/u/2538682/notepad/694">笔记 <i>0</i></a></span>
                                                                <span class="i-mid span-common"><a href="/u/2538682/course/694/codes">代码 <i>0</i></a></span>
                                                                <span class="i-right span-common"><a href="/u/2538682/course/694/questions">问答 <i>0</i></a></span>
                                                                <a v-bind:href="'/video/' + res.resourceId +'?id='+res.id"  class="btn-red continute-btn">继续学习</a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li class="course-one" v-for="(sameRes,index) in res.sameTimeList">
                                                        <div class="course-list-img l">
                                                            <a v-bind:href="'/video/' + sameRes.resourceId +'?id='+sameRes.id" target="_blank">
                                                                <img width="200" height="113" v-bind:alt="sameRes.name" src="sameRes.pic">
                                                            </a>
                                                        </div>
                                                        <div class="course-list-cont">
                                                            <h3 class="study-hd">
                                                                <a v-bind:href="'/video/' + sameRes.resourceId +'?id='+sameRes.id" target="_blank">{{sameRes.name}}</a>

                                                                <span class="i-new">{{sameRes.statusName}}</span>
                                                                <!-- 更新完毕 -->
                                                                <!-- 收藏和删除 -->
                                                                <div class="share-box clearfix">
                                                                    <div class="show-btn"><i class="icon-down2"></i></div>
                                                                    <div class="share-box-con courses-r">
                                                                        <a href="javascript:;" title="收藏" class="follow custom_f"><i class="icon icon-star_outline"></i></a>
                                                                        <a href="javascript:;" title="删除" class="del"><i class="icon icon-notdisplay"></i></a>
                                                                    </div>
                                                                </div>
                                                            </h3>
                                                            <div class="study-points">
                                                                <span class="i-left span-common">已学{{sameRes.process}}%</span>
                                                                <span class="i-mid span-common">用时{{sameRes.learnedHour}}小时{{sameRes.learnedMinute}}分{{sameRes.learnedSecond}}秒</span>
                                                            </div>
                                                            <div class="catog-points">
                                                                <span class="i-left span-common"><a href="/u/2538682/notepad/694">笔记 <i>0</i></a></span>
                                                                <span class="i-mid span-common"><a href="/u/2538682/course/694/codes">代码 <i>0</i></a></span>
                                                                <span class="i-right span-common"><a href="/u/2538682/course/694/questions">问答 <i>0</i></a></span>
                                                                <a v-bind:href="'/video/' + sameRes.resourceId +'?id='+sameRes.id" target="_blank" class="btn-red continute-btn">继续学习</a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </li> 
									<li class="layui-timeline-item">
									    
									</li>                                                                   	
                                </ul>
                            </div>
                            <div class="layui-tab-item">
                                <div class="course-list course-list-m">
                                    <ul class="clearfix">
                                    </ul>
                                </div>
                            </div>
                            <div id="paging" v-if="sum>rows"></div>
                        </div>
                   </div>
                </div>
            </div>
        </div>
	</div>
	</div>
	<#include "../../footer.ftl">
	<script src="../../js/user/perUser/listUserResource.js"></script>
</body>
</html>