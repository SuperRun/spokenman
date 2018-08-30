<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户培训</title>
    <#include "../../perBaseFile.ftl">
    <link rel="stylesheet" type="text/css" href="../../css/user/perUser/setbindsns.css">
    <link rel="stylesheet" type="text/css" href="../../css/train/perUser/listUserTrain.css">
</head>

<body>

    <#include "../../courseHeader.ftl">
	<#include "../../userInfo.ftl">
	<div id="app">
    <div class="layui-row" style="position: relative">
        <div class="slider" id="slider">
            <ul>
                <li>
                    <a>
                        <i class="layui-icon">&#xe617;</i><span>动态</span><b class="icon-drop_right"></b>
                    </a>
                </li>
                <li>
                    <a href="/perUser/${(Session.sessionInfo.otaUserId)}/listUserResource">
                        <i class="layui-icon">&#xe617;</i><span>课程</span><b class="icon-drop_right"></b>
                    </a>
                </li>
                <li>
                    <a href="/perUser/${(Session.sessionInfo.otaUserId)}/listUserTrain"  class="active">
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
                        <li class="layui-this">正在培训</li>
                        <li>推荐培训</li>
                        <li>已通过培训</li>
                        <li>参加但未通过的培训</li>
                    </ul>
                    <div class="layui-tab-content" style="">
                        <div class="layui-tab-item layui-show">
                            <div class="layui-collapse" lay-filter="test">
                                <div class="layui-colla-item" v-for="(train,index) in trainings">
                                    <h2 class="layui-colla-title">{{train.name}}</h2>
                                    <div class="layui-colla-content">
                                        <div class="container-types clearfix">
                                            <h3 class="types-title">
							                                    发布人为：<span>{{train.userName}}</span>
                                            </h3>
                                            <div class="types-title">
                                            	必修学分：<span>{{train.requiredScore}}分</span>
				                                                          选修学分：<span>{{train.optionalScore}}分</span>
                                            </div>
                                            <div class="types-title">
                                            	 报名时间：<span>{{train.signStartTime}}-{{train.signEndTime}}</span>
							                                     培训时间：<span>{{train.startTime}}-{{train.endTime}}</span>
                                            </div>
                                            <div class="types-title">							                                                
							                                       培训等级：<span>{{train.levelName}}</span>
							                                       培训条线：<span>{{train.typeName}}</span>
                                            </div>
                                            <div class="types-title">简介：{{train.description}}</div>
                                            <fieldset class="layui-elem-field layui-field-title">
                                                <legend>必修课程</legend>
                                            </fieldset>
                                            <div class="types-content">
                                     
                                                <div class="index-card-container course-card-container container" v-for="(reqRes,index) in train.requiredRes">
                                                    <a target="_blank" class="course-card"
                                                       href="//coding.imooc.com/class/96.html?mc_marking=58fc4a3eef797bb53e2a572b4492e1cb&amp;mc_channel=syb9"
                                                       data-track="sztj-1-4">
                                                        <div class="course-card-top hashadow">
                                                            <img class="course-banner" :src="reqRes.pic">
                                                            <div class="course-label">
                                                                <label>{{reqRes.levelName}}</label>
                                                                <label>{{reqRes.typeName}}</label>
                                                            </div>
                                                        </div>
                                                        <div class="course-card-content">
                                                            <h3 class="course-card-name">{{reqRes.name}}</h3>
                                                            <div class="clearfix course-card-bottom">
                                                                <div class="course-card-info">
                                                                    <span>实战</span><span>初级</span><span><i class="layui-icon on">&#xe612;</i>4116</span>
                                                                    <span class="course-star-box">
									                                    <i class="layui-icon on">&#xe658;</i>
									                                    <i class="layui-icon on">&#xe658;</i>
									                                    <i class="layui-icon on">&#xe658;</i>
									                                </span>
                                                                </div>
                                                                <!--<div class="course-card-price">￥348.00</div>-->
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>
                                              
                                            </div>
                                            <fieldset class="layui-elem-field layui-field-title">
                                                <legend>选修课程</legend>
                                            </fieldset>
                                            <div class="types-content">
                                                
                                                <div class="index-card-container course-card-container container" v-for="(reqRes,index) in train.optionalRes">
                                                    <a>
                                                        <div class="course-card-top hashadow">
                                                            <img class="course-banner" :src="reqRes.pic">
                                                            <div class="course-label">
                                                                <label>{{reqRes.levelName}}</label>
                                                                <label>{{reqRes.typeName}}</label>
                                                            </div>
                                                        </div>
                                                        <div class="course-card-content">
                                                            <h3 class="course-card-name">{{reqRes.resourceName}}</h3>
                                                            <div class="clearfix course-card-bottom">
                                                                <div class="course-card-info">
                                                                    <span>实战</span><span>初级</span><span><i class="layui-icon on">&#xe612;</i>4116</span>
                                                                    <span class="course-star-box">
									                                    <i class="layui-icon on">&#xe658;</i>
									                                    <i class="layui-icon on">&#xe658;</i>
									                                    <i class="layui-icon on">&#xe658;</i>
									                                </span>
                                                                </div>
                                                            <!--    <div class="course-card-price">￥348.00</div>-->
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="layui-tab-item">
                            
                        </div>
                        <div class="layui-tab-item">
                            <div class="layui-collapse" lay-filter="test">
                                <div class="layui-colla-item" v-for="(train,index) in trainPass">
                                    <h2 class="layui-colla-title">{{train.name}}</h2>
                                    <div class="layui-colla-content">
                                        <div class="container-types clearfix">
                                            <h3 class="types-title">
							                                                      发布人为：<span>{{train.userName}}</span>
                                            </h3>
                                            <div class="types-title">
                                            	必修学分：<span>{{train.requiredScore}}分</span>
							                                                      选修学分：<span>{{train.optionalScore}}分</span>
                                            </div>
                                            <div class="types-title">
                                            	 报名时间：<span>{{train.signStartTime}}-{{train.signEndTime}}</span>
							                                                      培训时间：<span>{{train.startTime}}-{{train.endTime}}</span>
                                            </div>
                                            <div class="types-title">							                                                
							                                                      培训等级：<span>{{train.levelName}}</span>
							                                                      培训条线：<span>{{train.typeName}}</span>
                                            </div>
                                            <div class="types-title">简介：{{train.description}}</div>
                                            <fieldset class="layui-elem-field layui-field-title">
                                                <legend>必修课程</legend>
                                            </fieldset>
                                            <div class="types-content">
                                     
                                                <div class="index-card-container course-card-container container" v-for="(reqRes,index) in train.requiredRes">
                                                    <a>
                                                        <div class="course-card-top hashadow">
                                                            <img class="course-banner" :src="reqRes.pic">
                                                            <div class="course-label">
                                                                <label>{{reqRes.levelName}}</label>
                                                                <label>{{reqRes.typeName}}</label>
                                                            </div>
                                                        </div>
                                                        <div class="course-card-content">
                                                            <h3 class="course-card-name">{{reqRes.name}}</h3>
                                                            <div class="clearfix course-card-bottom">
                                                                <div class="course-card-info">
                                                                    <span>实战</span><span>初级</span><span><i class="layui-icon on">&#xe612;</i>4116</span>
                                                                    <span class="course-star-box">
									                                    <i class="layui-icon on">&#xe658;</i>
									                                    <i class="layui-icon on">&#xe658;</i>
									                                    <i class="layui-icon on">&#xe658;</i>
									                                </span>
                                                                </div>
                                                           <!--     <div class="course-card-price">￥348.00</div>-->
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>
                                              
                                            </div>
                                            <fieldset class="layui-elem-field layui-field-title">
                                                <legend>选修课程</legend>
                                            </fieldset>
                                            <div class="types-content">
                                                
                                                <div class="index-card-container course-card-container container" v-for="(reqRes,index1) in train.optionalRes">
                                                    <a target="_blank" class="course-card"
                                                       href="//coding.imooc.com/class/96.html?mc_marking=58fc4a3eef797bb53e2a572b4492e1cb&amp;mc_channel=syb9"
                                                       data-track="sztj-1-4">
                                                        <div class="course-card-top hashadow">
                                                            <img class="course-banner" :src="reqRes.pic">
                                                            <div class="course-label">
                                                                <label>{{reqRes.levelName}}</label>
                                                                <label>{{reqRes.typeName}}</label>
                                                            </div>
                                                        </div>
                                                        <div class="course-card-content">
                                                            <h3 class="course-card-name">{{reqRes.pic}}</h3>
                                                            <div class="clearfix course-card-bottom">
                                                                <div class="course-card-info">
                                                                    <span>实战</span><span>初级</span><span><i class="layui-icon on">&#xe612;</i>4116</span>
                                                                    <span class="course-star-box">
									                                    <i class="layui-icon on">&#xe658;</i>
									                                    <i class="layui-icon on">&#xe658;</i>
									                                    <i class="layui-icon on">&#xe658;</i>
									                                </span>
                                                                </div>
                                                              <!--  <div class="course-card-price">￥348.00</div>-->
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>                           
                        </div>
                        <div class="layui-tab-item">
                            <div class="layui-collapse" lay-filter="test">
                                <div class="layui-colla-item" v-for="(train,index) in trainFail">
                                    <h2 class="layui-colla-title">{{train.trainingName}}</h2>
                                    <div class="layui-colla-content">
                                        <div class="container-types clearfix">
                                            <h3 class="types-title">
							                                                      发布人为：<span>{{train.userName}}</span>
                                            </h3>
                                            <div class="types-title">
                                            	必修学分：<span>{{train.requiredScore}}分</span>
							                                                      选修学分：<span>{{train.optionalScore}}分</span>
                                            </div>
                                            <div class="types-title">
                                            	 报名时间：<span>{{train.signStartTime}}-{{train.signEndTime}}</span>
							                                                      培训时间：<span>{{train.startTime}}-{{train.endTime}}</span>
                                            </div>
                                            <div class="types-title">							                                                
							                                                      培训等级：<span>{{train.levelName}}</span>
							                                                      培训条线：<span>{{train.typeName}}</span>
                                            </div>
                                            <div class="types-title">简介：{{train.description}}</div>
                                            <fieldset class="layui-elem-field layui-field-title">
                                                <legend>必修课程</legend>
                                            </fieldset>
                                            <div class="types-content">
                                     
                                                <div class="index-card-container course-card-container container" v-for="(reqRes,index) in train.requiredResource">
                                                    <a>
                                                        <div class="course-card-top hashadow">
                                                            <img :src="reqRes.learningResource.pic">
                                                            <div class="course-label">
                                                                <label>{{reqRes.levelName}}</label>
                                                                <label>{{reqRes.typeName}}</label>
                                                            </div>
                                                        </div>
                                                        <div class="course-card-content">
                                                            <h3 class="course-card-name">{{reqRes.resourceName}}</h3>
                                                            <div class="clearfix course-card-bottom">
                                                                <div class="course-card-info">
                                                                    <span>实战</span><span>初级</span><span><i class="layui-icon on">&#xe612;</i>4116</span>
                                                                    <span class="course-star-box">
									                                    <i class="layui-icon on">&#xe658;</i>
									                                    <i class="layui-icon on">&#xe658;</i>
									                                    <i class="layui-icon on">&#xe658;</i>
									                                </span>
                                                                </div>
                                                             <!--   <div class="course-card-price">￥348.00</div>-->
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>
                                              
                                            </div>
                                            <fieldset class="layui-elem-field layui-field-title">
                                                <legend>选修课程</legend>
                                            </fieldset>
                                            <div class="types-content">
                                                
                                                <div class="index-card-container course-card-container container" v-for="(reqRes,index) in train.unRequiredResource">
                                                    <a class="course-card">
                                                        <div class="course-card-top hashadow">
                                                            <img class="course-banner" :src="reqRes.learningResource.pic">
                                                            <div class="course-label">
                                                                <label>{{reqRes.levelName}}</label>
                                                                <label>{{reqRes.typeName}}</label>
                                                            </div>
                                                        </div>
                                                        <div class="course-card-content">
                                                            <h3 class="course-card-name">{{reqRes.resourceName}}</h3>
                                                            <div class="clearfix course-card-bottom">
                                                                <div class="course-card-info">
                                                                    <span>实战</span><span>初级</span><span><i class="layui-icon on">&#xe612;</i>4116</span>
                                                                    <span class="course-star-box">
									                                    <i class="layui-icon on">&#xe658;</i>
									                                    <i class="layui-icon on">&#xe658;</i>
									                                    <i class="layui-icon on">&#xe658;</i>
									                                </span>
                                                                </div>
                                                               <!-- <div class="course-card-price">￥348.00</div>-->
                                                            </div>
                                                        </div>
                                                    </a>
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
    </div>
     
	</div>
	<#include "../../footer.ftl">
	<script src="../../js/train/perUser/listUserTrain.js"></script>
</body>
</html>