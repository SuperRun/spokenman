var orOtherResizableDiv;
var iframeTree;
var iframeContent;
$(document).ready(function() {
	iframeTree = $( "#iframeAuthTree" );
	iframeContent=$("#iframeSubAuth");
	/*
	orOtherResizableDiv = $( "#other-resizable-div" ).width();
	$( "#resizable-div" ).resizable({ 
		animate: false,
		resize: function( event, ui ) {
			$( "#resizable-div" ).width(ui.size.width);
			$( "#other-resizable-div" ).width(orOtherResizableDiv - (ui.size.width - ui.originalSize.width));
		},
		handles:"e",
		autoHide:true,
	});
	*/
	$('.cus-add').click(function(e) {
		if(iframeTree != null) {
			var obj = iframeTree[0].contentWindow;
			try {
				if(typeof(eval(obj.addNode))=="function") {
					obj.addNode();
				}
			 }catch(e){
			}
		}
	});
	
	$('.cus-delete').click(function(e) {
		if(iframeTree != null) {
			var obj = iframeTree[0].contentWindow;
			try {
				if(typeof(eval(obj.removeNode))=="function") {
					obj.removeNode();
				}
			 }catch(e){
			}
		}
	});
	
	$('.cus-arrow-up').click(function(e) {
		if(iframeTree != null) {
			var obj = iframeTree[0].contentWindow;
			try {
				if(typeof(eval(obj.prevNode))=="function") {
					obj.prevNode();
				}
			 }catch(e){
			}
		}
	});
	
	$('.cus-arrow-down').click(function(e) {
		if(iframeTree != null) {
			var obj = iframeTree[0].contentWindow;
			try {
				if(typeof(eval(obj.nextNode))=="function") {
					obj.nextNode();
				}
			 }catch(e){
			}
		}
	});
	
	$('.cus-arrow-undo').click(function(e) {
		if(iframeTree != null) {
			var obj = iframeTree[0].contentWindow;
			try {
				if(typeof(eval(obj.undo))=="function") {
					obj.undo();
				}
			 }catch(e){
			}
		}
	});
	
	$('.cus-arrow-redo').click(function(e) {
		if(!$(this).parent().hasClass('disabled')) {
			if(iframeTree != null) {
				var obj = iframeTree[0].contentWindow;
				try {
					if(typeof(eval(obj.redo))=="function") {
						obj.redo();
					}
				 }catch(e){
				}
			}
		}
	});
	
	changeTreeUrl = function(url, el) {
		if(iframeTree != null) {
			$('#pills').find('li').removeClass('active');
			aciveLi($(el));
			var obj = iframeTree[0].contentWindow;
			try {
				if(typeof(eval(obj.changeUrl))=="function") {
					obj.changeUrl(url);
				}
			 }catch(e){
			}
		}
	}

	aciveLi = function(el) {
		console.info(el);
		if($(el).is('li')) {
			$(el).addClass('active');
		}
		var parEl = $(el).parent();
		if(parEl != null && parEl.attr('id') != 'pills') {
			aciveLi(parEl);
		} else {
			return;
		}
	}
	
	var $j=function(id){return document.getElementById(id);};
	var getMouseP=function (e){
		//��ȡ������ �봫��evnet����
		e = e || window.event;
		var m=(e.pageX || e.pageY)?{ x:e.pageX, y:e.pageY } : { x:e.clientX + document.body.scrollLeft - document.body.clientLeft, y:e.clientY + document.body.scrollTop  - document.body.clientTop };
		return m;
	};

	move=function(o,t){
		o=$j(o);
		t=$j(t);
		o.onmousedown=function(ev){
			var mxy=getMouseP(ev);//��ȡ��ǰ������
			var by={x:mxy.x-(t.offsetLeft),y:mxy.y-(t.offsetTop)};
			o.style.cursor="move";
			document.onmousemove=function(ev){
				var mxy=getMouseP(ev);
				t.style.left=mxy.x-by.x+"px";
				t.style.top=mxy.y-by.y+"px";
			};
			document.onmouseup=function(){
				window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
				this.onmousemove=null;
			}
		}
	}
	
	move("toolBars","toolBars");

	$("body").append("<div style='position:absolute' id='fly'></div>");  
	$("body").mousemove(function(e) {  
		var f=$("#fly:visible");  
		if(f[0]) {
			f.css("left",e.pageX+10);  
			f.css("top",e.pageY+10);  
		}
	});
	$("body").mouseup(function(){$("#fly").hide();});

	iframeTree[0].onload=function() {
		//var iframeContent = $("#iframeContent");
		//var iframeTree = $("#iframeTree");
		var iframeContent_body = $(iframeContent[0].contentWindow.document.body);
		var iframeTree_body = $(iframeTree[0].contentWindow.document.body);
		
		iframeContent_body.mousemove(function(e) {
			var f = $("#fly:visible");  
			f.css("left",e.pageX+iframeContent.offset().left+10);  
			f.css("top",e.pageY+iframeContent.offset().top+10);  
		}).mouseup(function(){$("#fly").hide();});

		iframeContent_body.find(".dropable").mouseup(function(e) {
			$(this).val($("#fly:visible").text());//��iframe������clone�Ķ���append,���ܿ�¡�Ķ����������document�±���  
			$("#fly:visible div").empty();  
		}).hover(function(){  
			
		},function(){  
			
		});
		
		iframeTree_body.mousemove(function(e){  
			var f=$("#fly:visible");  
			if(f[0]){  
				f.css("left",e.pageX+iframeTree.offset().left+10);  
				f.css("top",e.pageY+iframeTree.offset().top+10);				
			}  
		}).mouseup(function(){$("#fly").hide();}).bind("selectstart",function(e){e.preventDefault();});  

		iframeTree_body.find(".dynatree-title").mousedown(function(e) {
			e.preventDefault();  
			$("#fly").empty().append($(this).clone()).css({left:(iframeTree.offset().left+e.pageX+10)+"px",top:(iframeTree.offset().top+e.pageY+10)+"px"}).show();  
		}).mouseup(function(){$("#fly").hide();}); 
	};
	
	reBindMousedown = function() {
		//var iframeTree = $("#iframeTree");
		var iframeTree_body = $(iframeTree[0].contentWindow.document.body);
		iframeTree_body.find(".dynatree-title").mousedown(function(e) {
			e.preventDefault();  
			$("#fly").empty().append($(this).clone()).css({left:(iframeTree.offset().left+e.pageX+10)+"px",top:(iframeTree.offset().top+e.pageY+10)+"px"}).show();  
		}).mouseup(function(){$("#fly").hide();}); 
	}
});
function showContent(node){
	iframeContent.attr('src','authContents.html?pid='+node.data.key);
}
function freshContent(){
	iframeContent.attr('src',iframeContent.attr('src'));
}
function freshTree(){
	if(iframeTree != null) {
		var obj = iframeTree[0].contentWindow;
		try {
			if(typeof(eval(obj.reloadTree))=="function"){
				obj.reloadTree();
			}
    	}catch(e){
    		alert("freshTree Error！");
    	}
		
	}
}
function addNode(node){
	console.info("add node key:"+node.data.key+"title:"+node.data.title);
	jQuery.ajax( {  
	        type : 'GET',  
	        contentType : 'application/json',  
	        url : 'addAuthAjax.json?pid='+node.getParent().data.key+'&name='+node.data.title,  
	        dataType : 'json',  
	        success : function(data) {  
	          if (data) {  
				  console.info(data);
	        	  node.data.key=data;
	          }
	        },  
	        error : function() {  
	          console.log("auth tree add error");
	        }  
	      });
	
}
function editNode(node){
	console.info("edit node key:"+node.data.key+"title:"+node.data.title);
	jQuery.ajax( {  
	        type : 'GET',  
	        //contentType : 'application/json',  
	        url : 'editAuthAjax.json?id='+node.data.key+'&name='+node.data.title  
	        //dataType : 'json',  
	        //success : function(data) {  
	        //  console.log("auth tree edit success");
	        //},  
	        //error : function() {  
	        //  console.log("auth tree edit error");
	        //}  
	      });
}

