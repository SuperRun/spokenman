var allreport = (function($){

	var _qaqcpath="http://localhost:8080/qaqc";
	var _jianlapath="http://localhost:8080/jianlaweb";
	
	var listReport = function(data,callback){
		var url=_jianlapath+"/report/search_report.json";
		$.get(url,data,callback);
	}
	
	var listYanzhen  = function(data,callback){
		var url=_jianlapath+"/reportConfirm/list.json";
		$.get(url,data,callback);
	}
	
	var listQaqc = function(data,callback){
		var url=_qaqcpath+"/inspect/list2.json";
		$.get(url,data,callback);
	}
	
	return {
		list_report : listReport,
		list_yanzhen : listYanzhen,
		list_qaqc : listQaqc
	}
	
})(jQuery);