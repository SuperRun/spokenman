
<div class="bg-other user-head-info" id="userInfo">
	<input id="userId" type="hidden" value="<#if Session.sessionInfo ? exists>${Session.sessionInfo.otaUserId}</#if>"/>
	<div class="user-info clearfix" style="padding-bottom: 0px;">
		<div class="user-pic" data-is-fans="" data-is-follows="">
			<div class="user-pic-bg">
				<!--<img class="img" src="//img4.mukewang.com/54584dd900014f6c02200220-140-140.jpg" alt="">-->
			</div><!--user-pic-big end-->
		</div>
		<div class="user-info-right">
			<h3 class="user-name clearfix">
				 <#if Session.sessionInfo?exists> 
					<span>${(Session.sessionInfo.name)!} </span>
				</#if>
			</h3>
			<p class="about-info">
				<span>保密</span>
				<span>学生</span>
				<a class="more-user-info"><i class="layui-icon" style="font-size: 12px;">&#xe61a</i>更多信息</a>
			</p>
		</div>
		<div class="user-sign hide">
			<p class="user-desc">这位同学很懒，木有签名的说～</p>
		</div>
		<div class="study-info clearfix">
            <div class="item follows">
                <div class="u-info-learn" style="cursor:pointer;">
                    <em>{{user.score}}</em>
                    <span>学分</span>
                </div>
            </div>
			<div class="item follows">
				<div class="u-info-learn" style="cursor:pointer;">
					<em>{{user.learnedHour}}h</em>
					<span>学习时长</span>
				</div>
			</div>
			<!--<div class="item follows">
				<a href="/u/2538682/experience"><em>956</em></a>
				<span>经验</span>
			</div>
			<div class="item follows">
				<a href="/u/2538682/credit"><em>0</em></a>
				<span>积分</span>
			</div>
			<div class="item follows">
				<a href="/u/2538682/follows"><em>1</em></a>
				<span>关注</span>
			</div>
			<div class="item follows">
				<a href="/u/2538682/fans"><em>0</em></a>
				<span>粉丝</span>
			</div>-->
			<div class="item follows"><a href="/user/setbindsns" class="set-btn"><i class="layui-icon">&#xe614;</i> 个人设置</a></div>
		</div><!--.study-info end-->
	</div><!-- .user-info end -->
</div>	
<script src="/js/userInfo.js"></script>