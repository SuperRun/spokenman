<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>首页</title>
	<#include "base.ftl">
	<link rel="stylesheet" type="text/css" href="../css/index/index.css">
	<link rel="stylesheet" type="text/css" href="../css/index/hex.css">
	
	
</head>

<body>

<div id="app">


	<#include "header.ftl">	
	<#if Session.sessionInfo?exists> 
		<#if Session.sessionInfo.type ==0> 	
		<#include "platformMenu.ftl">
		<#else>
		<#include "orgMenu.ftl">
		</#if>
	<#else>
		<#include "indexMenu.ftl">
	</#if>	
	
	<div class="layui-main" style="min-height: 400px;" >
		<div id="part1">
			<img src="../images/index-pic.jpg">
			<div id="part1-notice-bg">
				<h2>梳理天下新闻</h2>
				<p>快速掌握新闻传播学、心理学、公共关系学等知识，熟悉新闻相关政策与法规、社交礼仪常识。新闻发言人还需掌握新闻发言技巧。让您快速成为一个优秀的新闻发言人。</p>
				<button class="layui-btn  join-btn ">报名加入</button>
			</div>
		</div>
	</div>	
	
	<div class="layui-fluid part2">
		<div class="part-title">
			<div class="part-title-left">
				01
			</div>
			<div class="part-title-right">
				灵活学习方式
			</div>				
		</div>
		<div id="part2-container">
		  <div class="layui-row layui-col-space30">
		    <div class="layui-col-md4">
		      <div class="part2-grid">
				<i class="iconfont part2-icon" >&#xe629;</i> 
				<h3>/ 加入培训班 /</h3>
				<p>用户可以加入单位组织的培训班进行系统学习，还可以加入运营平台组织的培训进行学习。</p>
		      </div>
		    </div>
		    <div class="layui-col-md4">
		      <div class="part2-grid">
		      	<i class="iconfont part2-icon" >&#xe698;</i>
				<h3>/ 开放性学习 /</h3>
				<p>系统注册用户可以随时利用开放资源进行学习，按年度累计学分。</p>
		      </div>
		    </div>
		    <div class="layui-col-md4">
		      <div class="part2-grid">
		      	<i class="iconfont part2-icon" >&#xe6fb;</i>
				<h3>/ 以考试考核 /</h3>
				<p>每期培训可安排一次考试以检验培训学习效果，培训考试通过者可发放相应证书，也可以单独组织一次考试。</p>
		      </div>
		    </div>
		  </div>			
		</div>
	</div>	
	<div class="layui-fluid part3">
		<div class="part-title">
			<div class="part-title-left">
				02
			</div>
			<div class="part-title-right">
				丰富学习资源
			</div>				
		</div>
		<div id="part3-container">
		  <div class="layui-row layui-col-space30">
		    <div class="layui-col-md6">
		      <div class="part3-grid part3-grid-bg1">
		      	<img src="../images/part3-img1.png">
		      	<p>不定期将会有优秀讲师开课教你快速成为一个优秀的新闻发言人</p>

		      </div>
		    </div>
		    <div class="layui-col-md6">
		      <div class="part3-grid part3-grid-bg2">
		      	<img  src="../images/part3-img2.png">
		      	<p>讲座形分为音频、视频，用户可根据自己需求添加资源</p>
		      </div>
		    </div>
		  </div>
		  <div class="layui-row layui-col-space30">
		    <div class="layui-col-md6">
		      <div class="part3-grid part3-grid-bg3">
		      	<img src="../images/part3-img3.png">
		      	<p>我们还提供丰富的典型案例分析，内容形式多样</p>
		      </div>
		    </div>
		    <div class="layui-col-md6">
		      <div class="part3-grid part3-grid-bg4">
		      	<img src="../images/part3-img4.png">
		      	<p>图文相结合，并驾齐驱</p>
		      </div>
		    </div>
		  </div>							  				
		</div>
	</div>
	<div class="layui-fluid part4">
		<div class="part-title">
			<div class="part-title-left">
				03
			</div>
			<div class="part-title-right">
				面向多类用户
			</div>				
		</div>
		<div id="part4-tittle1">
			<fieldset class="layui-elem-field layui-field-title">
			  <legend>如果你是单位用户你能？</legend>
			</fieldset>				
		</div>
		<div id="part4-container1">
			<ul id="hex">
				<li class="p2"><a href="/"><b></b><span>01考试发布</span><em></em></a></li>
				<li class="p2"><a class="inner" href="/"><b></b><span>02报名与名单管理</span><em></em></a></li>
				<li class="p2"><a href="/"><b></b><span>03考试控制</span><em></em></a></li>
				<li class="p2"><a class="inner" href="/"><b></b><span>04客观题自动阅卷</span><em></em></a></li>
				<li class="p2"><a href="/"><b></b><span>05模拟发布阅卷</span><em></em></a></li>
				<li class="p2"><a class="inner" href="/"><b></b><span>06考试成绩发布</span><em></em></a></li>
				<li class="p2"><a href="/"><b></b><span>07证书发布</span><em></em></a></li>		
				<li class="p1 p2"><a href="/"><b></b><span>01培训班开班</span><em></em></a></li>
				<li class="p2"><a class="inner" href="/"><b></b><span>02学员报名/导入</span><em></em></a></li>
				<li class="p2"><a href="/"><b></b><span>03培训资源配置</span><em></em></a></li>	
				<li class="p2"><a class="inner" href="/"><b></b><span>04培训结束后的考试</span><em></em></a></li>
				<li class="p2"><a href="/"><b></b><span>05考试及培训证书</span><em></em></a></li>	
			</ul>
		</div>

		<div id="part4-tittle2">
			<fieldset class="layui-elem-field layui-field-title">
			  <legend>如果你是个人用户你能？</legend>
			</fieldset>				
		</div>
		<div id="part4-container2">
			<ul id="hex">
				<li class="p2"><a href="/"><b></b><span>01加入培训班</span><em></em></a></li>
				<li class="p2"><a class="inner" href="/"><b></b><span>02按时完成课程</span><em></em></a></li>
				<li class="p2"><a href="/"><b></b><span>03学分达到要求</span><em></em></a></li>
				<li class="p2"><a class="inner" href="/"><b></b><span>04参与考试</span><em></em></a></li>
				<li class="p2"><a href="/"><b></b><span>05成绩合格</span><em></em></a></li>
				<li class="p2"><a class="inner" href="/"><b></b><span>06领取证书</span><em></em></a></li>
				<li class="p1 p2"><a href="/"><b></b><span>01学习开放资源</span><em></em></a></li>
				<li class="p2"><a class="inner" href="/"><b></b><span>02学习过程记笔记</span><em></em></a></li>
				<li class="p2"><a href="/"><b></b><span>03自由提问</span><em></em></a></li>	
				<li class="p2"><a class="inner" href="/"><b></b><span>04查看自己学分</span><em></em></a></li>
				<li class="p2"><a href="/"><b></b><span>05与专业人士互动</span><em></em></a></li>	
			</ul>
		</div>
	</div>
</div>

<#include "footer.ftl">
	<script src="../js/index.js"></script>
	
</body>
</html>
