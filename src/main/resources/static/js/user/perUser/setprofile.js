 //$('.profile input').attr("readOnly",true);
layui.use('province',function(){
    var   province=layui.province;

    console.log("sada");
    
    //三级联动（搜索区域的select）
    var default1={
        s1: 'change-province',
        s2: 'change-city',
        s3: 'areaId',
        v1: null,
        v2: null,
        v3: null
    }
    province.linkage(default1);
});

function saveInfo(userId){
	console.log('userId='+userId+'userInfo='+$('#userInfo').serialize());
	$.ajax({
		url:'/user/'+userId,
		type:'put',
		data:$('#userInfo').serialize(),
		success:function(res){
			console.log(res);
		}
	});
}