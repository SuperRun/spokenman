<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input id="__appRoot" type="hidden" value="${pageContext.request.contextPath}"/> 
   <div class="container" style="width:1190px;">
     
      <div class="row">
      
      </div>
       <!-- 移到各home中了
        <script type="text/javascript">        
        String.prototype.getRequestParameter = function (key) { 
        	var re = new RegExp(key + '=([^&]*)(?:&)?'); 
        	return this.match(re) && this.match(re)[1]; 
        }; 
        var searchString=$.url.decode(location.search);
        var authUrl=searchString.getRequestParameter('noAuthUrl');        
    	var authName=searchString.getRequestParameter('authName');
    	if(authUrl!=null){
	    	$('#noAuthInfo').html('<font><strong>您未被授权访问['+authName+']，如确实需要，请联系单位管理员！该权限链接为['+authUrl+']</strong></font>');
	    	$('#noAuthInfo').show();
    	}
        </script>
         -->
        <style type="text/css">
        	.addback{
        		background-color:orange;
        		color:white;
        	}
        </style>
        <!-- 
        <script type="text/javascript" src="../js/jquery/jquery-1.8.1.min.js"></script>
         
          <script type="text/javascript">
        	$(document).ready(function(){
        		$("#search1").click(function(){
        			$("#search2").removeClass("addback");
        			$("#search3").removeClass("addback");
        			$(this).addClass("addback");
        		});
        		$("#search2").click(function(){
        			$("#search1").removeClass("addback");
        			$("#search3").removeClass("addback");
        			$(this).addClass("addback");
        		});
        		$("#search3").click(function(){
        			$("#search1").removeClass("addback");
        			$("#search2").removeClass("addback");
        			$(this).addClass("addback");
        		});
        	});
        </script> -->