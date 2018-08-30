
//将选择的orgitem转换成ids
function countOrgitemIds(){
	ids="";
	first=true;
	$('#serviceOrgitemTable tr:gt(0)').each(function(){
		trCheckbox=$(this).children(":eq(0)").find("input[name='check1']");
			if(first==true){
		  		first=false;
		  		ids+=trCheckbox.val();
		  	}
		  	else ids+=","+trCheckbox.val();
	  });
	  return ids;
}


//以下是大文本js
function pictureAreaSelect(){
	alert('待机构图片空间功能完成后实现');
  }
  function localUpload(){
	$('input[id=localUploadFileInput]').click();
  }
  //http://stackoverflow.com/questions/6690752/insert-html-at-caret-in-a-contenteditable-div/6691294#6691294
  function pasteHtmlAtCaret(html, selectPastedContent) {
    var sel, range;
    if (window.getSelection) {
        // IE9 and non-IE
        sel = window.getSelection();
        if (sel.getRangeAt && sel.rangeCount) {
            range = sel.getRangeAt(0);
            range.deleteContents();

            // Range.createContextualFragment() would be useful here but is
            // only relatively recently standardized and is not supported in
            // some browsers (IE9, for one)
            var el = document.createElement("div");
            el.innerHTML = html;
            var frag = document.createDocumentFragment(), node, lastNode;
            while ( (node = el.firstChild) ) {
                lastNode = frag.appendChild(node);
            }
            var firstNode = frag.firstChild;
            range.insertNode(frag);
            
            // Preserve the selection
            if (lastNode) {
                range = range.cloneRange();
                range.setStartAfter(lastNode);
                if (selectPastedContent) {
                    range.setStartBefore(firstNode);
                } else {
                    range.collapse(true);
                }
                sel.removeAllRanges();
                sel.addRange(range);
            }
        }
    } else if ( (sel = document.selection) && sel.type != "Control") {
        // IE < 9
        var originalRange = sel.createRange();
        originalRange.collapse(true);
        sel.createRange().pasteHTML(html);
        if (selectPastedContent) {
            range = sel.createRange();
            range.setEndPoint("StartToStart", originalRange);
            range.select();
        }
    }
	}
  $(function(){
	  $('#localUploadFileInput').change(function(){
		  var action=$('#__appRoot').val()+'/up/uploadServicePicture.html';
		  //$('#localPictureUploadForm').attr('action',action);
			$('#localPictureUploadForm').ajaxSubmit({
					url:action,
					success: function (data) {
						if(typeof(data)!='object') data=JSON.parse(data);
						var args="<img src='"+$('#__appRoot').val()+"/"+data.files+"' />";
						pasteHtmlAtCaret(args,false);
						$('#localPictureUploadForm')[0].reset();
		            }
				});
		});
	  
    function initToolbarBootstrapBindings() {
      var fonts = ['宋体', '黑体', '楷体', '隶属', '幼圆','Serif', 'Sans', 'Arial', 'Arial Black', 'Courier', 
            'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
            'Times New Roman', 'Verdana'],
            fontTarget = $('[title=Font]').siblings('.dropdown-menu');
      $.each(fonts, function (idx, fontName) {
          fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'+fontName + '</a></li>'));
      });
      //$('a[title]').tooltip();//{container:'body'}
    	$('.dropdown-menu input').click(function() {return false;})
		    .change(function () {$(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');})
        .keydown('esc', function () {this.value='';$(this).change();});

      $('[data-role=magic-overlay]').each(function () { 
        var overlay = $(this), target = $(overlay.data('target')); 
        overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
      });
      if ("onwebkitspeechchange"  in document.createElement("input")) {
        var editorOffset = $('#editor').offset();
        $('#voiceBtn').css('position','absolute').offset({top: editorOffset.top, left: editorOffset.left+$('#editor').innerWidth()-35});
      } else {
        $('#voiceBtn').hide();
      }
	};
	function showErrorAlert (reason, detail) {
		var msg='';
		if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
		else {
			console.log("error uploading file", reason, detail);
		}
		$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+ 
		 '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
	};
    initToolbarBootstrapBindings();  
	$('#editor').wysiwyg({ 
		fileUploadError: showErrorAlert
	});
	$('#editor2').wysiwyg({ 
		fileUploadError: showErrorAlert
	});
    window.prettyPrint && prettyPrint();
  });

  $(document).ready(function(){
	//项目类别选择和查询项目部分
		$('#itemtype1').change(function(){
			$.ajax({
				type:'GET',
	  			url:$('#__appRoot').val()+'/item/getChildren.html?pid='+$('#itemtype1').val(),
	  			success:function(data){
	  				if(typeof(data)!="object") data=JSON.parse(data);
	  				if(data&&data.success==true){
	  					$('#itemtype2').empty();
	  					var option=$("<option>").val("").text("");
	  					$('#itemtype2').append(option);
	  					$.each(data.data,function(i,itemtype){
	  						var option=$("<option>").val(itemtype.id).text(itemtype.name);
	  						$('#itemtype2').append(option);
	  					});
	  				}
	  			}
			});
		});
		function notNullString(str){
			if(str==null)
				return "";
			return str;
		}
		
		//全选按钮 table2的checkbox
		$('#checkall').click(function(){
			if($('#checkall').prop("checked")){
				$('#orgItemsTable').find("input[name='check1']").each(function(){
					$(this).prop("checked",true);
				});
	  		}else{
	  			$('#orgItemsTable').find("input[name='check1']").each(function(){
	  				$(this).prop("checked",false);
	  			});
	  		}
	    });
		
	    //table1的checkbox
	    $('#service_checkall').click(function(){
	    	if($(this).prop("checked")){
	    		$('#serviceOrgitemTable').find("input[name='check1']").each(function(){
	    			$(this).prop("checked",true);
	    		});
	    	}else{
	    		$('#serviceOrgitemTable').find("input[name='check1']").each(function(){
	    			$(this).prop("checked",false);
	    		});
	    	}
	    });
	    
	    //检查是否已经被选择
	    function noSelected(id){
	    	ret=true;
	    	$('#serviceOrgitemTable tr:gt(0)').each(function(){
	    		trCheckbox=$(this).children(":eq(0)").find("input[name='check1']");
	    		if(trCheckbox.val()==id){
	    			ret=false;
	    			return false;
	    		}
	    	});
	    	return ret;
	    }
	      
	    //table2的添加按钮
	    $('#addItemBtn').click(function(){
	    	num=0;num2=0;
	    	$('#orgItemsTable tr:gt(0)').each(function(){
	    		//$(this).children(":eq(2)").text("haha");
	    		trCheckbox=$(this).children(":eq(0)").find("input[name='check1']");
	    		if(trCheckbox.prop("checked")){
	    			if(noSelected(trCheckbox.val())==true){
	    				tr=$(this).clone();
	    				$('#serviceOrgitemTable').append(tr);
	    				num2++;
	    			}
	    			num++;
	    			trCheckbox.prop("checked",false)
	    		}
	    	});
	    	if(num==0) alert("请选择要添加的项目");
	    	else if(num2!=num) alert("成功添加"+num2+"个项目,还有"+(num-num2)+"个已经存在。");
	    });
	    
	    //table1的移除按钮
	    $('#delItemBtn').click(function(){
	    	num=0;
	    	$('#serviceOrgitemTable tr:gt(0)').each(function(){
	    		trCheckbox=$(this).children(":eq(0)").find("input[name='check1']");
	    		if(trCheckbox.prop("checked")){
	    			$(this).remove();
	    		  	num++;
	    		}
	    	});
	    	if(num==0) alert("请选择要移除的项目");
	    });
  });