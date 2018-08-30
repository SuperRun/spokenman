<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资源首页</title>
    <#include "common.ftl">
    <link rel="stylesheet" type="text/css" href="../css/resIndex.css">
</head>

<body>

<#include "perHeader.ftl">


<div class=" management" id="app" style="min-height:600px;">
    <div class="layui-carousel" id="test1" lay-filter="test1">
        <div carousel-item="">
            <div><img src="/images/pic1.jpg"></div>
            <div><img src="/images/pic2.jpg"></div>
            <div><img src="/images/pic3.jpg"></div>
            <div><img src="/images/pic4.jpg"></div>
        </div>
    </div>
    <div class="bgfff">
        <div class="container-types clearfix">
            <h3 class="types-title" >
                <i class="layui-icon">&#xe636;</i>
                <em>资</em>／<em>源</em>／<em>推</em>／<em>荐</em>
                <i class="layui-icon">&#xe636;</i>
            </h3>
            <div class="types-content"  v-for="res in resources1">
                <div class="index-card-container course-card-container container">
                    <a target="_blank" class="course-card"
                        v-bind:href="'/perUser/resourceDetail/'+res.id"
                       data-track="sztj-1-1">
                    <div class="course-card-top hashadow">
                        <img class="course-banner" v-bind:src="res.pic">
                        <div class="course-label">
                            <label>{{res.typeIdName}}</label>
                        </div>
                    </div>
                    <div class="course-card-content">
                        <h3 class="course-card-name">
                            {{res.name}}
                        </h3>
                        <div class="clearfix course-card-bottom">
                            <div class="course-card-info">
                                <span>实战</span><span>初级</span><span>
                                <i class="layui-icon on">&#xe612;</i>
                                4116</span>
                                <span class="course-star-box">
                                    <i class="layui-icon on">&#xe658;</i>
                                    <i class="layui-icon on">&#xe658;</i>
                                    <i class="layui-icon on">&#xe658;</i>
                                </span>
                            </div>
                            <div class="course-card-price">￥366.00</div>
                        </div>
                    </div>
                    </a>
                </div>
                
            </div>
            <div class="types-content">
                <div class="index-card-container course-card-container container"  v-for="(res,index) in resources2">
                    <a target="_blank" class="course-card"
                       v-bind:href="'/perUser/resourceDetail/'+res.id">
                        <div class="course-card-top hashadow">
                            <img class="course-banner" v-bind:src="res.pic">
                            <div class="course-label">
                                <label>{{res.typeIdName}}</label>
                            </div>
                        </div>
                        <div class="course-card-content">
                            <h3 class="course-card-name">
                            {{res.name}}
                            </h3>
                            <div class="clearfix course-card-bottom">
                                <div class="course-card-info">
                                    <span>实战</span><span>初级</span><span>
                                <i class="layui-icon on">&#xe612;</i>
                                4116</span>
                                    <span class="course-star-box">
                                    <i class="layui-icon on">&#xe658;</i>
                                    <i class="layui-icon on">&#xe658;</i>
                                    <i class="layui-icon on">&#xe658;</i>

                                </span>
                                </div>
                                <div class="course-card-price">￥366.00</div>
                            </div>
                        </div>
                    </a>
                </div>
                
				<div class="index-card-container course-card-container card-last">
                    <a target="_blank" class="course-card" href="">
                        <div class="course-card-top hashadow">
                            <img class="course-banner" src="../../images/backImg.jpg">
                            <div class="course-label">
                                <label>查看全部课程，点击此处哦~</label>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>  
    
    <div class="bg000">
        <div class="container-types clearfix">
            <h3 class="types-title" >
                <i class="layui-icon">&#xe636;</i>
                <em>培</em>／<em>训</em>／<em>推</em>／<em>荐</em>
                <i class="layui-icon">&#xe636;</i>
            </h3>
            <div class="types-content"  v-for="train in training1">
                <div class="index-card-container course-card-container container">
                    <a target="_blank" class="course-card"
                        v-bind:href="'/perUser/trainDetail/'+train.id"
                       data-track="sztj-1-1">
                    <div class="course-card-top hashadow">
                        <img class="course-banner" v-bind:src="train.pic">
                        <div class="course-label">
                            <label>{{train.typeIdName}}</label>
                        </div>
                    </div>
                    <div class="course-card-content">
                        <h3 class="course-card-name">
                            {{train.name}}
                        </h3>
                        <div class="clearfix course-card-bottom">
                            <div class="course-card-info">
                                <span>实战</span><span>初级</span><span>
                                <i class="layui-icon on">&#xe612;</i>
                                4116</span>
                                <span class="course-star-box">
                                    <i class="layui-icon on">&#xe658;</i>
                                    <i class="layui-icon on">&#xe658;</i>
                                    <i class="layui-icon on">&#xe658;</i>
                                </span>
                            </div>
                            <div class="course-card-price">￥366.00</div>
                        </div>
                    </div>
                    </a>
                </div>
                
            </div>
            
            <div class="types-content">
                <div class="index-card-container course-card-container container"  v-for="(train,index) in training2">
                    <a target="_blank" class="course-card"
                       v-bind:href="'/perUser/resourceDetail/'+train.id">
                        <div class="course-card-top hashadow">
                            <img class="course-banner" v-bind:src="train.pic">
                            <div class="course-label">
                                <label>{{train.typeIdName}}</label>
                            </div>
                        </div>
                        <div class="course-card-content">
                            <h3 class="course-card-name">
                            {{train.name}}
                            </h3>
                            <div class="clearfix course-card-bottom">
                                <div class="course-card-info">
                                    <span>实战</span><span>初级</span><span>
                                <i class="layui-icon on">&#xe612;</i>
                                4116</span>
                                    <span class="course-star-box">
                                    <i class="layui-icon on">&#xe658;</i>
                                    <i class="layui-icon on">&#xe658;</i>
                                    <i class="layui-icon on">&#xe658;</i>
                                </span>
                                </div>
                                <div class="course-card-price">￥366.00</div>
                            </div>
                        </div>
                    </a>
                </div>
                
				<div class="index-card-container course-card-container card-last">
                    <a target="_blank" class="course-card" href="">
                        <div class="course-card-top hashadow">
                            <img class="course-banner" src="../../images/backImg.jpg">
                            <div class="course-label">
                                <label>查看全部培训，点击此处哦~</label>
                            </div>
                        </div>
                    </a>
                </div>
                
            </div>
        </div>
    </div>      
</div>

<#include "footer.ftl">

<script src="../js/resIndex.js"></script>
</body>
</html>