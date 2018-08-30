var Tuisong = (function($){
	
	var _jianlapath="";
	var _jianla="jianla";
	var _yanzhen="yanzhen";
	var _qaqc="qaqc";
	
	function getProp(obj){
		var prop="";
		for(var i in obj){
			prop+="&"+i+"="+obj[i];
		}
		return prop;
	}
	
	var createObject = function(send_orgid,rece_orgid,type,report_id,report_number){
		var tuisong = new Object();
		tuisong.send_orgid = send_orgid;
		tuisong.rece_orgid = rece_orgid;
		tuisong.type = type;
		tuisong.report_id = report_id;
		tuisong.report_number = report_number;
		return tuisong;
	}
	
	var add = function(data,callback){
		var url=_jianlapath+"tuisong/add.json?callback=?";
		$.getJSON(url,data,callback);
	}
	
	var list = function(data,callback){
		var url=_jianlapath+"tuisong/list.json";
		$.get(url,data,callback);
	}
	
	var getAllOrgs = function(callback){
		var url=_jianlapath+"tuisong/getAllOrgs.json?callback=?";
		$.getJSON(url,callback);
	}
	
	return {
		list : list,
		add : add ,
		create : createObject,
		get_all_orgs : getAllOrgs
	}
})(jQuery);