<link rel="stylesheet" type="text/css" href="/css/perHeader.css">
	<div class="layui-header header">
		<div class="layui-row">
		  <div class="layui-col-md2 layui-col-md-offset1">
		    <img id="logo" src="../images/logo.png">
		  </div>
		  <div class="layui-col-md6">
		    <div class="layui-row">
		    	<div class="layui-col-md2">
		    		<a href="/perUser/resIndex" class="menua">首页</a>
		    	</div>

		    	<div class="layui-col-md2">
		    		<a id="course" class="menua">课程</a>
		    	</div>

		    	<div  class="layui-col-md2">
		    		<a id="train" class="menua">培训</a>
		    	</div>

              <div  class="layui-col-md2">
                <a href="/perUser/exam/login" class="menua">考试</a>
              </div>

				<div  class="layui-col-md6">
		    		<div id="form_search">
						<input type="text" id="keyWord" name="" value="请输入关键词" autocomplete="off">
						<i class="layui-icon" style="color:#fff">&#xe615;</i>
					</div>
		    	</div>

		    </div>
		  </div>
		  <div class="layui-col-md3 ">
		    <div id="usermsg" class="layui-row">
		    	<div class="layui-col-md3">
		    		<img id="headImg" src="../images/head.jpg">
		    	</div>

		    	<div  class="layui-col-md9">
		    		<span id="username">
	      				${(Session.sessionInfo.name)!}
	      			</span>
		    		<i id="arrows" class="layui-icon "  style="font-size: 15px;color:#fff">&#xe61a;</i>
		    	</div>

				<#if Session.sessionInfo.type ==1>

			    	<div id="setting" style="display: none;">
						<i id="triangle" class="iconfont" >&#xe67c;</i>
						<dl id="detail" class="layui-nav-child" style="display: block;">
					      <dd><a href="/perUser/accountBind">个人设置</a></dd>
					      <dd><a href="/perUser/${(Session.sessionInfo.otaUserId)}/listUserResource">我的课程</a></dd>
					      <dd><a href="/perUser/${(Session.sessionInfo.otaUserId)}/listUserTrain">我的培训</a></dd>
					      <dd><a onclick="logout()">安全退出</a></dd>
					    </dl>
					</div>

				<#else>
					<div id="setting" style="display: none;">
						<i id="triangle" class="iconfont" >&#xe67c;</i>
						<dl id="detail" class="layui-nav-child" style="display: block;">
					      <dd><a href="/perUser/accountBind">个人设置</a></dd>
					      <dd><a href="/index">后台管理</a></dd>
					      <dd><a href="/perUser/${(Session.sessionInfo.otaUserId)}/listUserTrain">设置密码</a></dd>
					      <dd><a onclick="logout()">安全退出</a></dd>
					    </dl>
					</div>
				</#if>
		    </div>
		  </div>
		</div>
	</div>
<script src="/js/userMsg.js"></script>
<script src="/js/barClick.js"></script>