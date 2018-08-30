new Vue({
	el:"#app",
	data:{
		userId:"",
		page:1,
		rows:5,
		sum:"",
		resList:[{
			createTime:"",
			description:"",
			id:"",
			name:"",
			resourceId:"",
			score:"",
			status:"",
			statusName:"",
			time:"",
			type:"",
			typeName:"",
			updateTime:"",
			url:"",
			userId:"",
		    sameTimeList:[{
				createTime:"",
				description:"",
				id:"",
				name:"",
				resourceId:"",
				score:"",
				status:"",
				statusName:"",
				time:"",
				type:"",
				typeName:"",
				updateTime:"",
				url:"",
				userId:"",
			}]
		}]
	},
	created:function(){
    	var self=this;
        var split = location.pathname.split('/');
        if (split.length>2) {
            self.userId = split[2];
            console.log("用户ID="+self.userId);
        }
    },
	mounted:function(){4
		var self=this;
		self.fetch();
	},
	methods:{
		fetch:function(){
			
			//左侧导航栏样式
			$("#slider a").each(function(index){
				if(index==1){
					$(this).addClass("active");
				}else{
					$(this).removeClass("active");
				}
			});
			//构造resList数据
			var self=this;
			$.get('/user-resource/'+self.userId+'/list?page='+self.page+'&&rows='+self.rows,function(res){
				console.log(res);
				self.sum=res.data.sum;
				console.log("记录总数="+self.sum);
				var resource=res.data.data;
				for(var i=0;i<resource.length;i++){
					var updateTime=self.formatDateTime(resource[i].updateTime);
					resource[i].updateTime=updateTime.split(' ')[0];
				}
				self.resList=[];
				var j=1;
				var count=0;//记录resList长度
				for(var i=0;i<resource.length;i++){
					var temp=false;
					console.log("i="+i);
					resource[i].sameTimeList=[];
					self.resList.push(resource[i]);
					console.log("count="+count);
					console.log(self.resList[count]);
					if(j<resource.length){
						console.log("J1="+j);
						//self.resList[i].sameTimeList=[];
						while(resource[i].updateTime==resource[j++].updateTime&&i!=j){
							console.log("while...");
							console.log("resource[i].updateTime"+resource[i].updateTime);
							console.log("resource[j-1].updateTime"+resource[j-1].updateTime);
							//console.log("resource[j].updateTime"+resource[j].updateTime);
							self.resList[count].sameTimeList.push(resource[j-1]);
							console.log("i1="+i);
							console.log("j="+j);
							temp=true;
							if(j>=resource.length){
								console.log("resource.length="+resource.length);
								i=j-1;
								break;
							}
						}
						console.log("i2="+i);
						console.log("J2="+j);
						if(temp){
							i=j-1;
						}
					}
					count++;
				}
				console.log(self.resList);
				
				
				//用户资源分页
				layui.use('laypage',function(){
					var laypage=layui.laypage;
					laypage.render({
					    elem: 'paging'
					    ,limit:res.data.rows
					    ,curr:res.data.page
					    ,count:res.data.sum//数据总数
					    ,jump: function(obj,first){
					    	//首次不执行
	                        if(!first){
	                            self.page= obj.curr;
	                            console.log("当前页"+self.page);
	                            self.fetch();
	                        }
					    }
					});
				});
				
					
			});
			
		},
		formatDateTime:function(inputTime) {//时间戳转换  
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
	
})