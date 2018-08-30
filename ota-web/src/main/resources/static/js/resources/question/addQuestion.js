new Vue({
	el:"#app",
	data:{
		pausetime:"",
		addQuestion:{
			resourceId:"",
			time:"",
			content:"",
			answer:"",
			typeId:"",
			items:[]
		},
		item:{
			"content":"",
			"pic":"",
			"sequence":1
		}
	},
	mounted:function(){
		var self=this;
		var split = location.pathname.split('/');
        self.addQuestion.resourceId = split[2];
        console.log("resourceId="+self.addQuestion.resourceId);
	},
	methods:{
		subQuestion:function(){
			var self=this;
			self.addQuestion.typeId=$('input:radio[name="choice"]:checked').val();
			console.log("typeId="+self.addQuestion.typeId);
			
			//将时间转化成秒
			var time=$('#time').val().split(':');
			for(var i=0;i<time.length;i++){
				if(i==0){
					self.pausetime=Number(time[i])*60*60;
				}else if(i==1){
					self.pausetime+=Number(time[i])*60;
				}else{
					self.pausetime+=Number(time[i]);
				}
			}
			console.log("self.addQuestion.time="+self.addQuestion.time);
			
			//验证时间是否超出视频总长度
			//验证单选题型用户是否多选
			var videoDuration=window.parent.document.getElementById("video").duration;
			console.log("视频长度");
			console.log(videoDuration);
			if(self.pausetime>videoDuration){
				layui.use('layer',function(){
					layer.msg('您选择的时间超出视频时间', {
                        time: 3000
                     });
				});
			}else if(self.addQuestion.typeId==1&&$("input[name='answer']:checked").length>1){
				layui.use('layer',function(){
					layer.msg('您选择的题型是单选题只能有一个答案', {
                        time: 3000
                     });
				});
			}else{
				//获取时间
				self.addQuestion.time=$('#time').val();
				
				//获取答案
				var str;
				$("input[name='answer']:checked").each(function(j) {  
				    if (j==0) {  
				    	str= $(this).val();  
				    }else if(j>0){
				    	str += "&&"+$(this).val() ;  
				    }
				}); 
				self.addQuestion.answer=str;
				
				//获取问题选项
				var itemStr="";
				$("input[name='content']").each(function(index){  
					self.item.content=$(this).val();
					self.item.sequence=index+1;
					if(index==0){
						itemStr="["+JSON.stringify(self.item);
					}else{
						itemStr+=","+JSON.stringify(self.item);
					}
					
				});
				itemStr+="]";
				self.addQuestion.items=itemStr;
				console.log("addQuestion="+JSON.stringify(self.addQuestion.items));
				
				//提交数据
				$.ajax({
					url:"/learning-resource/question",
					type:"post",
					data:self.addQuestion,
					success:function(res){
						console.log(res);
						layer.close(layer.index);  
	    	            window.parent.location.reload();
					}
				});
			}
			
			
		}
	}
});

layui.use(['element', 'form'],function(){});

layui.use('laydate', function(){
    var laydate = layui.laydate;
    //时间选择器
    laydate.render({
        elem: '#time'
        ,type: 'time'
    });
   
});
function add_ask($this)
{
    var Word ="";
    var last_children_name = $("#checklist .layui-form-label").last().html();
    if(last_children_name.charCodeAt(0)<=90) {
        var html = "<div class=\"layui-form-item\">\n" +
            "        <label class=\"layui-form-label\">" + String.fromCharCode(last_children_name.charCodeAt(0) + 1) + "</label>\n" +
            "        <div class=\"layui-inline\">\n" +
            "            <input type=\"text\" name=\"content\"  class=\"layui-input\">\n" +
            "        </div>\n" +
            "        <div class=\"layui-inline\">\n" +
            "            <input type=\"checkbox\" name=\"answer\" value="+ String.fromCharCode(last_children_name.charCodeAt(0) + 1) +" lay-skin=\"primary\">\n" +
            "        </div>\n" +
            "    </div>"

        $("#checklist").children().eq(-2).before(html);
    }
    layui.use('form', function(){
        var form = layui.form;
        //根据的type类型修改
        form.render('checkbox');
    });
}
function del_ask($this){

    var last_children_name = $("#checklist .layui-form-label").last();
    last_children_name.parent().remove();

}