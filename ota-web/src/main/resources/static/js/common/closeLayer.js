/**
* 
*关闭弹出框事件（应用取消按钮）
*
**/
function closeLayer(){
	layui.use('layer',function(){
		layer.close(layer.index); 
	})
}