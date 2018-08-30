<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>注册界面</title>
	<link rel="stylesheet" type="text/css" href="../layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../css/regist.css">
</head>
<body>
<div id="regist">

	<div class="layui-fluid regist">
		<div class="layui-container">
			<div class="layui-row">
				<div class="layui-col-xs6">
					<div id="regist-left" class="regist-left1">
					
					</div>
				</div>
				<div class="layui-col-xs6">
					<div class="wrap">
						<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
						  <ul class="layui-tab-title">
						    <li class="layui-this">个人注册</li>
						    <li>单位注册</li>
						  </ul>
						  <div class="layui-tab-content">
						     <div class="layui-tab-item layui-show"> 
						    	<form class="layui-form" action="">

						    		<div class="layui-form-item">
										<div class="wrap-item">
                                    		<i class="layui-icon" style="font-weight: bold;">&#xe63b;</i>
                                    		<input type="text" id="phone" v-model="perForm.phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
                                		</div>
									</div>		

									<!--<div class="layui-form-item">
										<div class="layui-row">
											<div class="layui-col-xs6">
                                       			<div class="wrap-item" style="margin-top: 0px">
                                            		<i class="layui-icon">&#xe611;</i>
                                            		<input name="code" id="code" type="text" lay-verify="required|number" maxlength="6" placeholder="6位数字">
                                        		</div>
                                    		</div>
                                    		<div class="layui-col-xs6">
                                        		<div class="wrap-item2" style="margin-top: 0px">
                                            		<input class="wrap-btn send-btn" type="button" onclick="send()" value="发送短信">
                                        		</div>
                                        	</div>	
										</div>                                   
                                    </div>-->

						    		<div class="layui-form-item">
										<div class="wrap-item">
                                    		<i class="layui-icon">&#xe64c;</i>
                                    		<input type="password" id="password1" v-model="perForm.password" placeholder="请输入密码" autocomplete="off" class="layui-input">
                                		</div>
									</div>

                          				
                          			<div class="layui-form-item">
										<div class="wrap-item">
                                    		<i class="layui-icon">&#xe64c;</i>
                                    		<input type="password" id="password2" placeholder="请确认密码" autocomplete="off" class="layui-input">
                                		</div>
									</div>	                                                        				
								    <button type="button" class="layui-btn layui-btn-fluid regist-btn" @click="perRegist">注册</button>				
						    	</form>
						     </div>
							<div class="layui-tab-item">
						    	<form class="layui-form" action="">

						    		<div class="layui-form-item">
										<div class="wrap-item">
                                    		<i class="layui-icon">&#xe612;</i>
                                    		<input v-model="unitForm.name" type="text"  placeholder="请输入管理员姓名">
                                		</div>
									</div>                   					
								                         					
						    		<div class="layui-form-item">
										<div class="wrap-item">
                                    		<i class="layui-icon" style="font-weight: bold;">&#xe63b;</i>
                                    		<input type="text" v-model="unitForm.phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
                                		</div>
									</div>												
								<!--	<div class="layui-form-item">
										<div class="layui-row">
											<div class="layui-col-xs6">
                                       			<div class="wrap-item" style="margin-top: 0px">
                                            		<i class="layui-icon">&#xe611;</i>
                                            		<input name="code" id="code" type="text" lay-verify="required|number" maxlength="6" placeholder="6位数字">
                                        		</div>
                                    		</div>
                                    		<div class="layui-col-xs6">
                                        		<div class="wrap-item2" style="margin-top: 0px">
                                            		<input class="wrap-btn send-btn" type="button" onclick="send()" value="发送短信">
                                         			
                                        		</div>
                                        	</div>	
										</div>                                   
                                    </div>	-->

						    		<div class="layui-form-item">
										<div class="wrap-item">
                                    		<i class="layui-icon">&#xe64c;</i>
                                    		<input type="password" id="pass1" v-model="unitForm.password" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
                                		</div>
									</div>

                          				
                          			<div class="layui-form-item">
										<div class="wrap-item">
                                    		<i class="layui-icon">&#xe64c;</i>
                                    		<input type="password" id="pass2" lay-verify="pass" placeholder="请确认密码" autocomplete="off" class="layui-input">
                                		</div>
									</div>

									<fieldset class="layui-elem-field layui-field-title">
									  <legend style="font-size: 14px">请选择属性</legend>
									</fieldset>	

						    		<div class="layui-form-item">
										<div class="wrap-item">
                                    		<i class="layui-icon" style="font-weight: bold;">&#xe613;</i>
                                    		<input v-model="unitForm.orgName" placeholder="请输入单位名称" autocomplete="off" class="layui-input">
                                		</div>
									</div>

									<div class="layui-form-item">
										<div  class="layui-input-inline choose-select" style="width: 30%;margin-right: 15px;">
										  <select id="regist-province" lay-filter="regist-province" name="regist-province">
										  </select>
										</div> 
										<div class="layui-input-inline choose-select" style="width: 29%;margin-right: 15px;">
										  <select id="regist-city" lay-filter="regist-city" name="regist-city">
										  </select>
										</div>
										<div class="layui-input-inline choose-select" style="width:30%;margin-right: 0px;">
										  <select id="regist-county" lay-filter="regist-county" name="regist-county">
										  </select>
										</div>										
									</div>	
									
									<div class="layui-form-item">
								        <select id="level" lay-verify="required" lay-search="">
								          <option value="">请选择级别</option>
								          <option value="1">国家级</option>
								          <option value="2">省部级</option>
								          <option value="3">司厅局级</option>
								          <option value="4">县处级</option>
								          <option value="5">乡镇科级</option>
								        </select>	
									</div>

                                    <div class="layui-form-item">
								        <select class="getType" id="type" lay-verify="required" lay-search="">
								          <option value="">请选择条线</option>
								        </select>	
								    </div>									    					
								    <button type="button" class="layui-btn layui-btn-fluid regist-btn" @click="unitRegist">注册</button>				
						    	</form>								
							</div>
						  </div>
						</div> 
					</div>
				</div>
			</div>			
		</div>
	</div>

</div>
	<script src="../layui/layui.js"></script>
	<script src="../js/vue.js"></script>
	<#include "linkage.ftl">
	<script src="../js/getOrgType.js"></script>
	<script src="../js/common/formVerify.js"></script>
	<script src="../js/regist.js"></script>
    <script src="../js/jquery-2.1.0.js"></script>
	 
</body>
</html>