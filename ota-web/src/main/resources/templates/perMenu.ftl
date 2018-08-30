	<div id="menu">
        <div class="layui-inline logo">
            <img id="logo" src="../../images/logo.png">
        </div>
		<ul class="layui-nav platform-nav" lay-filter="platform-menu">

		  <li class="layui-nav-item"><a href="/index">首页</a></li>
		  <li class="layui-nav-item ">
		  	<a>用户管理</a>
		  	<dl class="layui-nav-child"> <!-- 二级菜单 -->
		      <dd><a href="">个人用户管理</a></dd>
		      <dd><a href="">单位用户管理</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item"><a>培训资源管理</a></li>
		  <!--<li class="layui-nav-item"><a>案例库管理</a></li>-->
		  <!--<li class="layui-nav-item"><a>学习管理</a></li>-->
		  <li class="layui-nav-item"><a>培训管理</a></li>
		  <li class="layui-nav-item"><a>题库管理</a></li>
		  <li class="layui-nav-item"><a>考试管理</a></li>
		  <!--<li class="layui-nav-item">
		    <a href="javascript:;">证书管理</a>
		    <dl class="layui-nav-child"> <!-- 二级菜单 -->
		      <dd><a href="">移动模块</a></dd>
		      <dd><a href="">后台模版</a></dd>
		      <dd><a href="">电商平台</a></dd>
		    </dl>
		  </li>-->
		</ul>
        <div id="usermsg" class="layui-inline">
          <div  class="layui-row">
              <div class="layui-col-md3">
                  <img id="headImg" src="../../images/head.jpg">
              </div>
              <div  class="layui-col-md9">
                  <span id="username">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                  <i id="arrows" class="layui-icon "  style="font-size: 15px;color: #fff">&#xe61a;</i>
              </div>
              <div id="setting" style="display: none;">
                  <i id="triangle" class="iconfont" >&#xe67c;</i>
                  <dl id="detail" class="layui-nav-child" style="display: block;">
                      <dd><a href="javascript:;">个人资料</a></dd>
                      <dd><a href="javascript:;">设置密码</a></dd>
                      <dd><a href="javascript:;">电商平台</a></dd>
                      <dd><a href="">退出</a></dd>
                  </dl>
              </div>
          </div>
        </div>
	</div>