$('.auth-list i').on('mouseover',function(){
    var index=$('.auth-list i').index(this);
    var mess;
    if(index==0){
        mess="未验证身份";
    } else if(index==1) {
        mess="未验证单位"
    }
    else if(index==2) {
        mess="未验证手机"
    } else if(index==3) {
        mess="未验证邮箱"
    }
    layer.tips(mess, '.auth-list i:eq('+index+')', {
        tips: 3,
    });
});

$('.avator-img,.update-avator').mouseover(function(){
    // $('.update-avator').css("bottom","0px");
    $('.update-avator').css( {"bottom":"0px","transition":"bottom 0.5s"});

});
$(".avator-img,.update-avator").mouseout(function(){
    $(".update-avator").css({"bottom":"-30px","transition":"bottom 0.5s"});
});
layui.use(['form','layer'], function(){ //独立版的layer无需执行这一句
    var $ = layui.jquery, form = layui.form,layer = layui.layer; //独立版的layer无需执行这一句
    //自定义验证规则
    form.verify({
        pass: [/(.+){6,12}$/, '密码必须6到12位']
        ,surepwd:function (value) {
            if(value !=$(newpwd).val()){
                return layer.msg("两次密码不一致");
            }
        }
        ,content: function(value){
            layedit.sync(editIndex);
        }
    });

    //监听提交
    form.on('submit(demo1)', function(data){
        layer.alert(JSON.stringify(data.field), {
            title: '最终的提交信息'
        })
        return false;
    });

    //触发事件
    var active = {
        setTop: function(){
            var that = this;
            //多窗口模式，层叠置顶
            layer.open({
                type: 1 //此处以iframe举例
                ,title: '更换头像'
                ,area: ['430px', '450px']
                ,content: $('#ch-portrait')
                ,success: function(layero){
                    layer.setTop(layero); //重点2
                }
            });
        },
        setPhone: function(){
            var that = this;
            //多窗口模式，层叠置顶
            layer.open({
                type: 1 //此处以iframe举例
                ,title: '更换手机'
                ,area: ['430px', '450px']
                ,content: $('#ch-phone')
                ,success: function(layero){
                    layer.setTop(layero); //重点2
                }
            });
        },
        setPwd: function(){
            var that = this;
            //多窗口模式，层叠置顶
            layer.open({
                type: 1 //此处以iframe举例
                ,title: '更换密码'
                ,area: ['450px', '380px']
                ,content: $('#ch-pwd')
                ,success: function(layero){
                    layer.setTop(layero); //重点2
                }
            });
        },
        setEmail: function(){
        var that = this;
        //多窗口模式，层叠置顶
        layer.open({
            type: 1 //此处以iframe举例
            ,title: '更换邮箱'
            ,area: ['450px', '380px']
            ,content: $('#ch-email')
            ,success: function(layero){
                layer.setTop(layero); //重点2
            }
        });
    }
    };

    $('#notices .change-pr').on('click', function(){
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
    $('#binding-phone').on('click', function(){
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
    $('#binding-email').on('click', function(){
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
    $('#binding-pwd').on('click', function(){
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });

});
layui.use('upload', function(){
    var $ = layui.jquery
        ,upload = layui.upload;

    //普通图片上传
    var uploadInst = upload.render({
        elem: '#test1'
        ,url: '/upload/'
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#demo1').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            //上传成功
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });



});

