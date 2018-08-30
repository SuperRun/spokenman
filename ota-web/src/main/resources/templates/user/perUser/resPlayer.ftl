<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>播放页</title>
	<link rel="stylesheet" type="text/css" href="../../layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../../css/iconfont/iconfont.css">
    <link rel="stylesheet" href="../../css/user/perUser/resPlayer.css">     
</head>
<body>
<div id="app">
	<div class="layui-header vedio-header" >
		<div class="layui-row">
		  <div class="layui-col-md1">
		  	<a href="javascript:history.go(-1)" ><i id="back" class="iconfont" >&#xe644;</i></a>
		  </div>
		  <div id="course-title" class="layui-col-md1">
		  	<h3>{{resource.name}}</h3>
		  </div>
	  
		  <div class="layui-col-md4 layui-col-md-offset6">
		  	<i id="message" class="iconfont" >&#xe604;</i> 
		  	<img id="headImg" src="../../images/head.jpg">
		  </div>		  			
		</div>
	</div>
	<div class="layui-fluid" >
        <video id="video" width="100%" height="500" controls>

		</video>
	</div>
	<div class="layui-main">
		<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		  <ul class="layui-tab-title">
		    <li class="layui-this">问答</li>
		    <li>笔记</li>
		  </ul>
		  <div class="layui-tab-content">
		  	<div class="layui-tab-item layui-show">
		  		
		  	</div>
		    <div class="layui-tab-item">
 
		    </div>
		  </div>
		</div> 
	</div>	
	<div id="question" style="display: none;">
		<form class="layui-form" action="">
		  <div class="layui-form-item">
		    <h3 id="content">{{question.content}}</h3>
		  </div>
		  <div class="layui-form-item" v-for="(item,index) in question.items">
		    <label class="layui-form-label">{{String.fromCharCode(option.charCodeAt(0) + index)}}</label>
	        <div class="layui-inline">
	            <p>{{item.content}}</p>
	        </div>
	        <div class="layui-inline">
	            <input type="checkbox" name="answer" v-bind:value="String.fromCharCode(option.charCodeAt(0) + index)" lay-skin="primary">
	        </div>
		  </div>
		</form>		
	</div> 	
</div>	
</body>
<script src="/js/jquery-2.1.0.js"></script>
<script src="/js/vue.js"></script>
<script src="../../layui/layui.js"></script>
<script type="text/javascript" src="../../js/user/perUser/resPlayer.js"></script>

</html>