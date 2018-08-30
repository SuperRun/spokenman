var rpstar = (function($){
	
	function getProp(obj){
		var prop="";
		for(var i in obj){
			if(obj[i]){
				prop+="&"+i+"="+obj[i];
			}
		}
		return prop;
	}

	var _basepath = "";//"http://localhost/jianlaweb";
	
	//create star object
	var createStar = function createStar(orgId,num,type,link){
		var star = new Object();
		star.orgId = orgId;
		star.number = num;
		star.type = type;
		star.link = link;
		star.name = arguments[4] ? arguments[4] : "质检报告:"+num;
		return star;
	}
	
	// people do star this report
	var doStar = function(star,callback){
		var url = _basepath+"rpstar/star.json?callback=?";
		$.getJSON(url,getProp(star),callback);
	}
	// people un star this report
	var unStar = function(orgId,num,callback){
		var url = _basepath+"rpstar/unstar.json?callback=?";
		var star = new Object();
		star.orgId=orgId;
		star.number=num;
		$.getJSON(url,getProp(star),callback);
	}
	// judge if star this report
	var isStar = function(orgId,num,callback){
		var url = _basepath+"rpstar/is_star.json?callback=?";
		var star = new Object();
		star.orgId=orgId;
		star.number=num;
		$.getJSON(url,getProp(star),callback);
	}
	// get list of report 
	var  listStar = function(data,callback){
		var url = _basepath+"rpstar/list_star.json?callback=?";
		$.getJSON(url,data,callback);
	}
	return {
		create_star : createStar,
		star : doStar,
		un_star : unStar,
		is_star : isStar,
		list_star : listStar
	}
})(jQuery);
/*
 * 收藏表字段：
 * ordId,uniqueId,type,gmtCreate,link,name
 * 
 * 封装成对象jlstar，
 * 有方法star(orgId,uniqueId,type,link,name)
 * 有方法unStar(orgId,uniqueId)
 * 有方法isStar(orgId,uniqueId)
 * 有方法listStar(orgId,pageNo,pageSize)
 */
