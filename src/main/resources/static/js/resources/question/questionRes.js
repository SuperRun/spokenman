var vue=new Vue({
	el:"#app",
	data:{
		resId:0,
		res:{
			name:"",
			userName:"",
			lecturerName:"",
			createTime:"",
			description:"",
			url:""
		}
		
	},
	mounted:function(){
		var self=this;
		var split = location.pathname.split('/');
        if (split.length > 2) {
            self.resId = split.pop();
            console.log("resId="+self.resId);
            self.getResource(self.resId);
            self.getResQuestion(self.resId);
        }
	},
	methods:{
		getResource:function(resId){
			var self=this;
			console.log("资源ID="+resId);
			$.get('/learning-resource/'+resId,function(res){
				self.res=res.data;
				self.res.url='../..'+res.data.url;
				self.res.createTime=self.formatDateTime(res.data.time);
				$('#video').html('');
				$('#video').append("<source src='"+self.res.url+"'></source>");
				console.log(self.res);
			});
		},
		getResQuestion:function(resId){
			layui.use('table', function(){
			    var table = layui.table;
			    table.render({
			        elem: '#question'
			        ,url:'/learning-resource/question/'+resId+'/list'
			        ,cols: [[ //标题栏
			            {field: 'content', title: '题目'}
			            ,{field: 'time', title: '时间点', width: 100}
			            ,{field: 'typeName', title: '类型', width: 100}
			            ,{field: 'statusName', title: '状态', width: 100}
			            ,{field: 'operate', title: '操作',align:'center',width:200, templet:function(d){
			            	if(d.status==1){
			            		return "<a class='layui-btn layui-btn-xs layui-btn-primary' onclick='editQuestion("+d.id+")')>修改</a>"+
				                	"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='delQuestion("+d.id+")'>删除</a>"
			            	}
			                
			            }}
			        ]]
			        ,page: true //是否显示分页
			        ,method: 'get'
				  	,request: {
				  		limitName: 'rows'
				  	}
				  	,done: function(res, curr, count){
				  	    
				  	 }
			    });
			});
		},
		addQuestion:function(){
			layui.use('layer', function(){ 
			    var layer = layui.layer; 
	            layer.open({
	                type: 2 //此处以iframe举例
	                ,title: '新增问题'
	                ,area: ['600px', '600px']
	                ,maxmin: true,
	                content: '/puUser/'+vue.resId+'/addQuestion'
	            });
			  
			});
		},
		formatDateTime:function(inputTime) {    
		    var date = new Date(inputTime);  
		    var y = date.getFullYear();    
		    var m = date.getMonth() + 1;    
		    m = m < 10 ? ('0' + m) : m;    
		    var d = date.getDate();    
		    d = d < 10 ? ('0' + d) : d;    
		    var h = date.getHours();  
		    h = h < 10 ? ('0' + h) : h;  
		    var minute = date.getMinutes();  
		    var second = date.getSeconds();  
		    minute = minute < 10 ? ('0' + minute) : minute;    
		    second = second < 10 ? ('0' + second) : second;   
		    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
		} 
	}
});

function editQuestion(id){
	layui.use('layer', function(){ 
	    var layer = layui.layer; 
        layer.open({
            type: 2 //此处以iframe举例
            ,title: '修改问题'
            ,area: ['750px', '600px']
            ,maxmin: true,
            content: '/puUser/'+id+'/editQuestion'
        });
	  
	});
}
function delQuestion(id){
	console.log("id="+id);
	layui.use('layer',function(){
		layer.confirm('确定要删除该问题吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
					url:"/learning-resource/question/"+id,
					type:"delete",
					success:function(res){
						console.log(res);
					}
				});
				layer.close(layer.index);  
	            window.parent.location.reload();
			}, function(){
				layer.close(layer.index);  
		});
	});
	
}
layui.use('element',function(){
	var $=layui.$;
    
    //菜单条事件
    $('#menu li').each(function(index){
    	
    	if(index==2){
    		$(this).addClass("layui-this");
    		$(this).find("dd").each(function(i){
    			if(i==0){
    				$(this).addClass("menu-this");
    				$(this).find('a').css('color','#fff');
    			}else{
    				$(this).removeClass("menu-this");
    				$(this).find('a').css('color','#000');
    			}
    		});
    		
    	}else{
    		$(this).removeClass("layui-this");
    	}
    });
});

