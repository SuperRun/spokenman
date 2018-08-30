var vue=new Vue({
	el:"#regist",
    data:{
    	perForm:{
        	phone:"",
        	password:""
           
        },
        unitForm:{
        	name:"",
        	phone:"",
        	orgName:"",
        	level:"",
        	typeId:"",
        	areaId:"",
        	password:""
        }		
	},
	mounted:function(){
		
	},
	methods:{
		perRegist:function(){
			var pwd1=$("#password1").val();
			var pwd2=$("#password2").val();
			var phone=$("#phone").val();
			var temp=registVerify(phone);
			if(pwd1!=pwd2){
				layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('两次输入的密码不一致');
                });   
        		return false;
            }else if(temp==1){
            	layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('手机号只能为11位数字');
                }); 
            	return false;
            }else if(pwd1==""){
            	layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('密码不能为空');
                });   
        		return false;
            }else{
            	$.ajax({
                    type : "post",
                    url : "/user-apply/personal",
                    data:this.perForm,
                    success : function(res) {
                    	console.log("注册成功");
                    	$.ajax({
                            type : "post",
                            url : "/user/login",
                            data:{"name":phone,"password":pwd1},
                            success : function(res) {
                            	console.log("登陆成功");
                            	layui.use('layer',function(){
                            		layer.close(layer.index);  
                            		parent.window.location.href="/perUser/applyTip";
                            	});
                            }
                                
                       });
                    }
                        
               });            	
            }
		}
		,unitRegist:function(){
			var self=this;
			var pwd1=$("#pass1").val();
			var pwd2=$("#pass2").val();
			self.unitForm.level=$("#level").val();
			self.unitForm.typeId=$("#type").val();
			self.unitForm.areaId=$("#regist-county").val();
			var data=self.unitForm;
			console.log("单位用户注册"+data);
			var temp=registVerify(data.phone);
			if(data.name==""||data.phone==""||data.orgName==""||data.level==""||data.typeId==""){
        		layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('信息不能为空');
                });   
        		return false;
        	}else if(temp==1){
        		layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('手机号只能为11位数字');
                });   
        		return false;
        	}else if(pwd1!=pwd2){
				layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('两次输入的密码不一致');
                });   
        		return false;
            }else if(pwd1==""){
            	layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('密码不能为空');
                });   
        		return false;
            }else if(temp==0){
            	$.ajax({
                    type : "post",
                    url : "/user-apply/org",
                    data:data,
                    success : function(res) {
                    	console.log("注册成功");
                    	$.ajax({
                            type : "post",
                            url : "/user/login",
                            data:{"name":data.name,"password":data.password},
                            success : function(res) {
                            	console.log("登陆成功");
                            	layui.use('layer',function(){
                            		layer.close(layer.index);  
                            		parent.window.location.href="/perUser/applyTip";
                            	});
                            }
                                
                       });
                    }
                        
               });
            }
		}
		
	}			

});
layui.use(['element', 'province','layer'],function(){
  var $=layui.$;
  var province=layui.province;

  /*更改注册页面左边区域的样式*/
  $('.layui-tab-title li').click(function(){
    var i=$(this).index();
    if(i==0){
      $('#regist-left').css({'height':'305px'});

    }else if(i==1){
      $('#regist-left').css({'height':'562px'});
    }
  });
  
  var default1={
            s1: 'regist-province',
            s2: 'regist-city',
            s3: 'regist-county',
            v1: null,
            v2: null,
            v3: null      
    };
	province.linkage(default1);


});