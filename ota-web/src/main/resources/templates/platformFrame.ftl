<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">
    	<img id="logo" src="/images/logo.png" style="margin-top:0">
    </div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <h1 id="title" class="layui-layout-left" style="line-height: 60px;color:white">新闻发言人学习培训与测试平台</h1>
    <#if Session.sessionInfo?exists> 
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="/images/head.jpg" class="layui-nav-img">
          ${(Session.sessionInfo.name)!}
        </a>
        <dl class="layui-nav-child">
          <dd><a href="/perUser/accountBind">个人设置</a></dd>
          <dd><a href="/perUser/${(Session.sessionInfo.otaUserId)}/listUserResource">后台管理</a></dd>
		  <dd><a onclick="logout()">安全退出</a></dd>
        </dl>
      </li>
    </ul>
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
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll" id="menu">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="platform-frame">
      	<li class="layui-nav-item"><a href="/index">首页</a></li>
        <li class="layui-nav-item layui-nav-itemed">
          <a>用户管理</a>
          <dl class="layui-nav-child">
            <dd><a href="/platform/perUser">个人用户</a></dd>
            <dd><a href="/platform/unitUser">单位用户</a></dd>
        	<dd><a href="/platform/lecUser">讲师管理</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a>培训资源管理</a>
          <dl class="layui-nav-child">
            <dd><a href="/platform/resourcesManage">查看资源</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
        	<a>培训管理</a>
        	<dl class="layui-nav-child">
				<dd><a href="/puUser/trainManage">查看培训</a></dd>
			</dl>
        </li>
      </ul>
    </div>
  </div>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
</script>