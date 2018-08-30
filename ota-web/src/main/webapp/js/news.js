function loadNews(){
	var allColumnNames='';
	$('.newsColumn').each(function(){
		allColumnNames+=$(this).attr('title')+",";
	});
	//console.log(allColumnNames);
	//平台公告,平台规则,招商,保障,社会新闻,行业资讯,机构动态, 
	 var url='/news/getAllColumnNewsList.html?columnNames='+allColumnNames;
	  //url+="&pageNo=1&pageSize=10";
	  var obj=$(this);
	  //console.log(url);
	  url=encodeURI(url);
	  $.ajax({
	    type:'post',
	    url:url,
	    success:function(data){
	    	//console.log(data);
	      if(typeof(data)!='Object') data=JSON.parse(data);
	      if(data&&data.success==true){
	    	  	parseNews(data);
	      }
	    },
	    error:function(textStatus,errorThrown){
	      console.log(textStatus);
	      console.log(errorThrown);
	    }
	  });
}
function parseNews(data){	
	$('.newsColumn').each(function(){
		//console.log(data[$(this).attr('title')]);
		
		var newsList=data[$(this).attr('title')];
		var obj=$(this);
        //console.info($(obj).html());
        var content='';
        // content+='<div class="col-md-9"><h4 style="margin-bottom: 2px;"><strong>'+columnName+'</strong></h4></div>'; 
        var listUrl='#';
        if(newsList.length>0){
          listUrl='/news/showNewsList.html?ddTypeId='+newsList[0].ddTypeId+'&status=3';
        }
        if(obj.hasClass("topMore"))
          content+='<div class="col-md-12"><a target="_blank" style="color:#a0a2a1;font-weight:bold;font-size:12px;float:right;" href="'+listUrl+'">更多...</a></div><br>';
        // content+='<hr class="col-md-12">'; 
        for(var i=1;i<=newsList.length;i++){
          content+='<div width="100%">';
          content+='<div>';
          content+='<a style="color:black;" href="/news/showNewsDetail.html?id='+newsList[i-1].id+'"><h5 class="news_h5 no_line">';
          // content+=i+'.'+newsList[i-1].title+'</h5></a></div></div>';  
          if(i<4){
            content+='<div class="news_a news_a_orange" style="margin-top:3px;">'+i+'</div>'+newsList[i-1].title+'</h5></a></div></div>';
          }
          else{
            content+='<div class="news_a news_a_gray" style="margin-top:3px;">'+i+'</div>'+newsList[i-1].title+'</h5></a></div></div>';
          }
          content+='<hr style="border:1px dashed #ccc;"/>';
          if(obj.hasClass("bottomMore")&&i==3)
            break;
        }
        if(obj.hasClass("bottomMore"))
          content+='<div class="col-md-12"><a target="_blank" style="color:#a0a2a1;font-weight:bold;font-size:12px;float:right;" href="'+listUrl+'">更多...</a></div><br>';
        
        //console.info(content);
        $(obj).html(content);
	});

	
}