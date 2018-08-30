var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;

function send(){
    if(!myreg.test($("#phone").val())){
        layer.msg("手机号填写错误！");
    }else{
        curCount = count;
        $(".send-btn").attr("disabled", "true");
        $(".send-btn").val(curCount + "秒后重新发送");
        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
        $.ajax({
            type : "POST",
            url: "/index.php?s=/Home/Index/phone_msg.html",
            data: {
                'phone':$("#phone").val()
            },
            success: function(res){
                console.log(res);
            }
        });
    }
}

//timer处理函数
function SetRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj);//停止计时器
        $(".send-btn").removeAttr("disabled");//启用按钮
        $(".send-btn").val("重新发送验证码");
    }
    else {
        curCount--;
        $(".send-btn").val(curCount + "秒后重新发送");
    }
}