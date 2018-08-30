/**
 * 表单验证
 * phoneVerify
 * emailVerify
 * isPoneAvailable
 * 
 */
//在输入过程判断是否只输入了数字
function phoneVerify(t){
	var num = t.value;  
    console.log(num);  
    var re = /^\d*$/;   //^匹配字符串开始位置   \d 匹配任意一个十进制数字，等价于[0-9]  * 匹配0次 1次或者多次前面的字符  $匹配字符串的结束位置  
    if(!re.test(num)){  
    	layui.use('layer', function () {
            var layer = layui.layer;
            layer.msg('手机号只能为数字');
        }); 
    }
     	
}
//判断邮箱格式是否正确
function emailVerify(t){
	var num = t.value;  
    console.log(num);  
    var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");  
    if(!reg.test(num)){  
    	layui.use('layer', function () {
            var layer = layui.layer;
            layer.msg('邮箱格式不对');
        }); 
    }
       	
}
//判断手机号是否为11位
function isPoneAvailable(pone) { 
   var re = /^1\d{10}$/ ;
   var num=pone.value;
   if (!re.test(num)) {
	  layui.use('layer', function () {
           var layer = layui.layer;
           layer.msg('手机号只能是11位数字');
      });
	 
   }
}

function submitVerify(phone,email){
	var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
	var  re = /^1\d{10}$/;
	if(!re.test(phone)){

		return -1;
	}else if(!reg.test(email)){

		return 1;
	}else{
		return 0;
	}
}
function registVerify(phone){
	var  re = /^1\d{10}$/;
	if(!re.test(phone)){
		return 1;
	}else{
		return 0;
	}
}