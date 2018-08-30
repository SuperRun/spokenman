<div class="layui-header platform-header">
  <div class="layui-row">
    <div class="layui-col-md2 layui-col-md-offset1">
      <img id="logo" src="/images/logo.png">
    </div>
    <div class="layui-col-md4">
      <h1 id="title">新闻发言人学习培训与测试平台</h1>
    </div>
    <div class="layui-col-md3 layui-col-md-offset2">
      <div id="usermsg" class="layui-row">
      
          <#if Session.sessionInfo?exists> 
   		  <div id="usermsg" class="layui-row">
			<div class="layui-col-md3">
				<img id="headImg" src="/images/head.jpg">
			</div>
	    	<div class="layui-col-md9">
	      		<span id="username">
	      			${(Session.sessionInfo.name)!} 
	      		</span>
	      		<i id="arrows" class="layui-icon " style="font-size: 15px;">&#xe61a;</i>
	      		<input type="hidden" value="${(Session.sessionInfo.otaUserId)!}"/>
	      	</div>
			<#if Session.sessionInfo.type ==0> 
				
		    	<div id="setting" style="display: none;">
					<i id="triangle" class="iconfont" >&#xe67c;</i> 
					<dl id="detail" class="layui-nav-child" style="display: block;">
				      <dd><a href="/perUser/accountBind">个人设置</a></dd>
				      <dd><a href="/perUser/${(Session.sessionInfo.otaUserId)}/listUserResource">后台管理</a></dd>
				      <dd><a onclick="logout()">安全退出</a></dd>
				    </dl> 
				</div>
				
			<#else>
				<div id="setting" style="display: none;">
					<i id="triangle" class="iconfont" >&#xe67c;</i> 
					<dl id="detail" class="layui-nav-child" style="display: block;">
				      <dd><a href="/perUser/accountBind">个人设置</a></dd>
				      <dd><a href="/index">后台管理</a></dd>
				      <dd><a href="/perUser/resIndex">资源首页</a></dd>
				      <dd><a onclick="logout()">安全退出</a></dd>
				    </dl> 
				</div>
			</#if>          
	       </div>
	       
	       <#else>
	       <div class="layui-row">
	      	  <div class="layui-col-md3">
		    	<span id="loginBtn" style="line-height: 80px;font-size: 16px;cursor: pointer;">登录</span>
		      </div>
		      <div  class="layui-col-md9">
		    	<span id="registBtn" style="line-height: 80px;font-size: 16px;cursor: pointer;">注册</span>
		      </div>
		   </div>
          </#if>
          
      </div>
    </div>
  </div>
</div>