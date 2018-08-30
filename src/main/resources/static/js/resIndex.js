new Vue({
	el:"#app",
	data:{
		resources1:[],
		resources2:[],
		training1:[],
		training2:[]
	},
	mounted:function(){
		var self=this;
		self.fetch();
	},
	methods:{
		
		fetch:function(){
			var self=this;
			$.get('/learning-resource?rows=9&page=1',function(res){
				console.log(res);
				if(res.count>=5){
					for(var i=0;i<5;i++){
						self.resources1.push(res.data[i]);
					}
					for(var i=5;i<9;i++){
						self.resources2.push(res.data[i]);
					}
				}else{
					for(var i=0;i<res.data.length;i++){
						self.resources1.push(res.data[i]);
					}
				}
				
			});
			$.get('/training?rows=9&page=1',function(res){
				console.log(res.data);
				if(res.count>5){
					for(var i=0;i<5;i++){
						self.training1.push(res.data[i]);
					}
					for(var i=5;i<9;i++){
						self.training2.push(res.data[i]);
					}
				}else{
					for(var i=0;i<res.data.length;i++){
						self.training1.push(res.data[i]);
					}
				}
				console.log("training1");
				console.log(self.training1);
				console.log("training2");
				console.log(self.training2);
				
			});
		}
	}
})
layui.use('carousel', function(){
    var carousel = layui.carousel;
    //建造实例
    carousel.render({
        elem: '#test1'
        ,width: '75%' //设置容器宽度
        ,height: '440px'
        ,arrow: 'always' //始终显示箭头
        //,anim: 'updown' //切换动画方式
    });
});