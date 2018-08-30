$.ajaxSetup({cache:false});
/**
 * 显示非空的字符串
 * 如果str==null则显示""
 */
function notNullString(str){
	if(str==null)
		return "";
	return str;
}
function notNullStr(str){
	if(str==null)
		return "";
	return str;
}
/** 
 * 时间对象的格式化; 
 */  
Date.prototype.format = function(format) {  
    /* 
     * eg:format="yyyy-MM-dd hh:mm:ss"; 
     */  
    var o = {  
        "M+" : this.getMonth() + 1, // month  
        "d+" : this.getDate(), // day  
        "h+" : this.getHours(), // hour  
        "m+" : this.getMinutes(), // minute  
        "s+" : this.getSeconds(), // second  
        "q+" : Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S" : this.getMilliseconds()  
        // millisecond  
    }  
  
    if (/(y+)/.test(format)) {  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4  
                        - RegExp.$1.length));  
    }  
  
    for (var k in o) {  
        if (new RegExp("(" + k + ")").test(format)) {  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1  
                            ? o[k]  
                            : ("00" + o[k]).substr(("" + o[k]).length));  
        }  
    }  
    return format;  
} 

/**
 * 回到id为pos的位置
 */
function click_scroll() {
	var scroll_offset = $("#pos").offset();
	$("body,html").animate({
		scrollTop:scroll_offset.top
	},0);
}

/**
 * 全选按钮
 */
$('#checkall').click(function(){
	if($('#checkall').prop("checked")){
		$("input[name='checkbox1']").each(function(){
			$(this).prop("checked",true);
		});
	}
	else{
		$("input[name='checkbox1']").each(function(){
			//this.checked=!this.checked;
			$(this).prop("checked",false);
		});
	}
});
/**
 * 把Timestamp转换成Date年-月-日的格式
 */
function formatDate(time){
	if(time==null) return "";
	return new Date(time).format("yyyy-MM-dd");
}
/**生成<a url="url">text</a>
 * target==true是新弹出一个窗口
 */
function setHref(url,text,target){
	var ret="<a ";
	if(target==true)
		ret+="target='_blank' ";
	ret+="href='"+$('#__appRoot').val()+"/"+url+"'>"+text+"</a>";
	return ret;
}
/**
 * 初始化分页条
 * 传入总页数和搜索函数
 */
function initTotalPages(totalPages,searchFunction){
	$('#Fenye').html('<div style="text-align:center;"id="jqPagination"></div>');
	$('#jqPagination').twbsPagination({
		   totalPages: parseInt(totalPages),
		   startPage:parseInt($('#pageNo').val()),
		   visiblePages: 7,
		   prev:'上一页',
		   next:'下一页',
		   onPageClick: function (event, page) {
		       $('#pageNo').val(page);
		       searchFunction();//用ajax获取json数据填充table
		       //click_scroll();
		   }
	});
}

function initTotalPages2(totalPages,searchFunction,visibleNum){
	$('#Fenye2').html('<div style="text-align:center;"id="jqPagination"></div>');
	$('#jqPagination').twbsPagination({
		   totalPages: parseInt(totalPages),
		   startPage:parseInt($('#pageNo').val()),
		   visiblePages:visibleNum,
		   prev:'上一页',
		   next:'下一页',
		   onPageClick: function (event, page) {
		       $('#pageNo').val(page);
		       searchFunction();//用ajax获取json数据填充table
		       //click_scroll();
		   }
	});
}

function initFenye(fenyeId,totalPages,searchFunction,pageNo){
	$("#"+fenyeId).html('<div style="text-align:center;"id="'+fenyeId+'_jqPagination"></div>');
	$('#'+fenyeId+'_jqPagination').twbsPagination({
		   totalPages: parseInt(totalPages),
		   startPage:parseInt($('#'+pageNo).val()),
		   visiblePages:7,
		   onPageClick: function (event, page) {
		       $('#'+pageNo).val(page);
		       searchFunction();//用ajax获取json数据填充table
		   }
	});
}