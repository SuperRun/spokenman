
var vue=new Vue({
        el: "#app",
        data: {
        	userId:"",
        	url:"/lecturer"
        },
        created:function(){
        	var self=this;
            var split = location.pathname.split('/');
            if (split.length>2) {
                self.userId = split.pop();
                console.log("讲师ID="+self.userId);
                self.url="/lecturer/user/"+self.userId;
                console.log("url="+self.url);
            }
        },
        mounted:function () {
            var self = this;
            self.fetch();
        },
        methods:{
            fetch: function () {
            	layui.use('table',function(){
            	    /*对表格进行渲染*/
				    var table=layui.table;
				    table.render({ 
						elem:'#per-user-table'
						,height :'471'
						,page: true
						, limit:10 
						,cols:[[
					          {field:'name', title:'讲师经历名称', width:140, unresize: true}
					          ,{field:'introduction', title:'讲师介绍'}
					          ,{field:'levelName', title:'等级',width:100,sort:true}
					          ,{field:'typeName', title: '条线', width:100, sort: true}
					          ,{field:'orgName', title:'所属组织', width:130, unresize: true}
					          ,{field:'statusName', title:'状态', width:70,sort: true,unresize: true}
					          ,{field:'operate', title:'操作',width:200,templet:function(d){
								    if(d.statusName=='正常'){
								    	return "<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='editLecturer("+d.id+")'>修改</a>"
											   +"<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserStatus("+d.id+",-1)'>删除</a>";
								    }else if(d.statusName=='删除'){
								    	return "<a class='layui-btn layui-btn-primary layui-btn-xs' onclick='updateUserStatus("+d.id+",1)'>恢复账号</a>"
								    	
								    }
							        
						      }}
					    ]] ,
					  	url: vue.url,
					  	method: 'get', 
					  	request: {
					  		limitName: 'rows'
					  	},
					  	done: function(res, curr, count){
					  	    console.log(res);
					  	    console.log(curr); 
					  	    console.log(count);
					  	 }
				    		 
				   });
            	});
            	
            	
            },
            search:function(){//查询用户 
            	layui.use(['table','form'],function(){
            		var table=layui.table;
            		var form=layui.form;
            		table.reload('per-user-table', {
            			  url:vue.url
            			  ,method:'get'
            			  ,where: {
            				  name:$('#userName').val()
            			      ,level:$('#searchLevel').val()
            			      ,status:$('#searchStatus').val()
            			      ,typeId:$('#searchTypeId').val()
            			  }
            			  ,page: {
            				   curr: 1
            			  },done: function(res, curr, count){
					  	    console.log(res);
					  	    console.log(curr);					  	  
					  	    console.log(count);
					  	 }
            			  
            		});
            	});
            },
            insert:function(){
	            layer.open({
	                type: 2 //此处以iframe举例
	                ,title: '新增讲师经历'
	                ,area: ['550px', '550px']
	                ,maxmin: true
	                ,content: '/platform/addLecturer'
	                
	            });
            	
            }
                
        }
});
function editLecturer(id){
	
	layui.use('layer',function(){
		var layer=layui.layer;
        layer.open({
            title:'修改讲师经历',
            type: 2,
            skin: 'layui-layer-rim', 
            area: ['500px', '560px'], 
            content: '/platform/editLecturer/'+id
        });
	});

}

layui.use('element',function(){
	var $=layui.$;
    
    //菜单条事件
    $('#menu li').each(function(index){
    	
    	if(index==1){
    		$(this).addClass("layui-this");
    		$(this).find("dd").each(function(i){
    			if(i==2){
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


