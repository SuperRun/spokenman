layui.use(['element', 'form'],function(){});
layui.use('table', function(){
    var table = layui.table;

    //展示已知数据
    table.render({
        elem: '#question'
        ,id:'vquestion'
        ,cols: [[ //标题栏
            {field: 'name', title: '题目',templet: '#usernameTpl'}
            ,{field: 'tpoint', title: '时间点', width: 100}
            ,{field: 'type', title: '类型', width: 100}
            ,{fixed: 'right', title: '操作',width: 165, align:'center', toolbar: '#barQuestion'}
        ]]
        ,data: [{
            "name": "**新闻发布会"
            ,"tpoint": " 00:20"
            ,"type": "单选"
        }]
        ,page: true //是否显示分页
    });
    //监听工具条
    table.on('tool(tablefilter)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值

         if(layEvent === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if(layEvent === 'edit'){

           // layer.alert('编辑行：<br>'+ JSON.stringify(ids));

            //修改
            layer.open({
                type: 2,
                title: '修改',
                maxmin: true,
                area: ['750px', '600px'],
                //content: '/resources/edit.html?id='+data,
                content: 'editDetails.html',
                success: function(layero, index){
                    var body = layer.getChildFrame('body',index);
                     var iframeWin = window[layero.find('iframe')[0]['name']];
                    var inputList = body.find('input');
                    var text = body.find('textarea');
                    var type = body.find('select');
                    var revisetime = body.find('input');
                    var status = body.find('input');
                    $(inputList[0]).val(data.name);
                  },
                cancel: function(){ //刷新网页
                    table.reload('idTest');//重新加载表格数据
                }
            });
        }
    });
});
layui.use('laydate', function(){
    var laydate = layui.laydate;
    //时间选择器
    laydate.render({
        elem: '#time'
        ,type: 'datetime'
    });


});

layui.use('layer', function(){ //独立版的layer无需执行这一句
    var layer = layui.layer; //独立版的layer无需执行这一句

    //触发事件
    var active = {
        add: function(){
            var that = this;
            //多窗口模式，层叠置顶
            layer.open({
                type: 2 //此处以iframe举例
                ,title: '新增'
                ,area: ['750px', '600px']
                ,maxmin: true,
                content: 'addDetails.html'
            });
        },

    };

    $('#addbtn').on('click', function(){
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
});