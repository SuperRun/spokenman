new Vue({
	el:"#app",
	data:{
		id:"",
		option:"A",
		getQuestion:{
			resourceId:"",
			time:"",
			content:"",
			answer:"",
			typeId:"",
			items:[]
		},
		editQuestion:{
			resourceId:"",
			time:"",
			content:"",
			answer:"",
			typeId:"",
			items:""
		},
		optionItem:{
			"content": "",
	        "id": 0,
	        "pic": "",
	        "questionId": 0,
	        "resourceQuestionId": 0,
	        "sequence": 0,
	        "sequenceStr": ""
		}
	},
	mounted:function(){
		var self=this;
		var split = location.pathname.split('/');
        self.id = split[2];
        console.log("quesId="+self.id);
        self.fetch();
	},
	watch:{    
		getQuestion:function(){        
			this.$nextTick(function(){
				var self=this;
				var str=self.getQuestion.answer.split('&&');
				var temp=0;
				$("input[name='answer']").each(function(j) { 
				    if (str[temp]==$(this).val()) {
				    	console.log("str[temp]="+str[temp]);
				    	$(this).attr("checked",true);
				    	temp++;
				    }
				});
				console.log("render");
				layui.use('form',function(){
					var form=layui.form;
					form.render();
				});
			});    
		}
	},
	methods:{
		fetch:function(){
			var self=this;
			layui.use('laydate', function(){
		        var laydate = layui.laydate;
		        //时间选择器
		        laydate.render({
		            elem: '#time'
		            ,type: 'time'
		        });
		    });
			$.get("/learning-resource/question/"+self.id,function(res){
				console.log(res.data.answer);
				self.getQuestion=res.data;
				self.editQuestion.resourceId=res.data.resourceId;
				$('input:radio[name="choice"]').each(function(){
					if($(this).val()==res.data.typeId){
						$(this).attr("checked",true);
					}
				});
				$("#time").val(self.getQuestion.time);
				console.log("res.data.answer="+res.data.answer);
			});
		},
		editForm:function(){
			var self=this;
			var ans;
			var itemStr="";
			$(".content").each(function(index){
				self.optionItem=self.getQuestion.items[index];
				self.optionItem.content=$(this).val();
				self.optionItem.sequence=index+1;
				if(index==0){
					itemStr="["+JSON.stringify(self.optionItem);
				}else{
					itemStr+=","+JSON.stringify(self.optionItem);
				}
				//self.editQuestion.items.push(self.optionItem);
			});
			itemStr+="]";
			console.log(itemStr);
			console.log(JSON.stringify(self.editQuestion.items));
			self.editQuestion.items=itemStr;
			$("input[name='answer']:checked").each(function(j) {  
			    if (j==0) {  
			    	ans= $(this).val();  
			    }else if(j>0){
			    	ans += "&&"+$(this).val() ;  
			    }
			});
			
			console.log("answer="+ans);
			self.editQuestion.answer=ans;
			self.editQuestion.time=$("#time").val();
			self.editQuestion.content=self.getQuestion.content;
			self.editQuestion.typeId=$('input:radio[name="choice"]:checked').val();
			var str="resourceId="+self.editQuestion.resourceId+"&content="+self.editQuestion.content+'&time='+self.editQuestion.time+'&typeId='+self.editQuestion.typeId
			+'&answer='+encodeURIComponent(self.editQuestion.answer)+'&items='+encodeURIComponent(self.editQuestion.items);
			
			$.ajax({
                type : "put",
                url : "/learning-resource/question/"+self.id+"?"+str,
                success : function(res) {
                	console.log(res);
                	layui.use('layer',function(){
                		layer.close(layer.index);  
        	            window.parent.location.reload();
                	});
                }
        	});
		}
	}
});

layui.use(['element', 'form'],function(){});

function add_ask($this)
{
    var Word ="";
    var last_children_name = $("#checklist .layui-form-label").last().html();
    if(last_children_name.charCodeAt(0)<=90) {
        var html = "<div class=\"layui-form-item\">\n" +
            "        <label class=\"layui-form-label\">" + String.fromCharCode(last_children_name.charCodeAt(0) + 1) + "</label>\n" +
            "        <div class=\"layui-inline\">\n" +
            "            <input type=\"text\"  class=\"layui-input content\">\n" +
            "        </div>\n" +
            "        <div class=\"layui-inline\">\n" +
            "            <input type=\"checkbox\" name=\"answer\" value="+ String.fromCharCode(last_children_name.charCodeAt(0) + 1) +" lay-skin=\"primary\">\n" +
            "        </div>\n" +
            "        <div class=\"layui-inline\">\n" +
            "            <button  class=\"layui-btn layui-btn-primary delbtn\">删除</button>\n" +
            "        </div>\n" +
            "    </div>"

        $("#checklist").children().eq(-2).before(html);
    }
    layui.use('laydate', function(){
        var form = layui.form;
        form.render('checkbox');
        
    });
}
function del_ask($this){

    var last_children_name = $("#checklist .layui-form-label").last();
    last_children_name.parent().remove();

}