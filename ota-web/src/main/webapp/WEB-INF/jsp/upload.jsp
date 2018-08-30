<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="taglib_includes.jsp"%>
<html>
<head>
<title>upload</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />-->
<!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>


			<form class="form-horizontal" role="form" id="addAuthForm">
                  <input id="pid" name="pid" type="hidden"/>
                  <input id="id" name="id" type="hidden"/>
                  <div class="form-group">
                    <label for="pname" class="col-sm-3 control-label">父权限名称</label>
                    <div class="col-sm-9">
                      <p id="pname" class="form-control-static"></p>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="name" class="col-sm-3 control-label">权限名称</label>
                    <div class="col-sm-9">
                      <input name="name" id="name" class="form-control" placeholder="权限名称"/>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="description" class="col-sm-3 control-label">权限描述</label>
                    <div class="col-sm-9">
                       <textarea name="description" id="description" class="form-control" rows="3" placeholder="权限描述"></textarea>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="url" class="col-sm-3 control-label">权限链接</label>
                    <div class="col-sm-9">
                       <input name="url" id="url" class="form-control" placeholder="权限链接"/>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="seq" class="col-sm-3 control-label">顺序号</label>
                    <div class="col-sm-9">
                      <input name="seq" id="seq" class="form-control"  placeholder="顺序号"/>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="isMenu" class="col-sm-3 control-label">菜单出现</label>
                    <div class="col-sm-9">
                       <select name="isMenu" id="isMenu" class="form-control">
                          <option value="1">是</option>
                          <option value="0" selected="selected">否</option>
                        </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="type" class="col-sm-3 control-label">权限分类</label>
                    <div class="col-sm-9">
                        <select name="type" id="type" class="form-control">
                          <option value="1">管理平台</option>
                          <option value="2">机构会员</option>
                          <option value="3">企业会员</option>
                        </select>                                           
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="uploadFile" class="col-sm-3 control-label">权限图片</label>
                    <div class="col-sm-9">
                    	<input type="hidden" name="pictureName">
                    	<input type="text" id="photoCover" class="form-control"  placeholder="点击选择上传照片" 
					        	onclick="$('input[id=photoFile]').click();" onkeydown="return false;">
                        <!-- 
                        <div class="input-group">
							
					        <input type="text" id="photoCover" class="form-control"  placeholder="点击选择上传照片" 
					        	onclick="$('input[id=photoFile]').click();" onkeydown="return false;">
							
					        <span class="input-group-btn">
								<button class="btn btn-primary" type="button" onclick="uploadPicture();">上传</button>
					        </span>
					    </div>   
					     -->                                     
                    </div>
                  </div>
                  <div class="form-group" style="display:none;" >
                    <label for="preview" class="col-sm-3 control-label">图片预览</label>
                    <div class="col-sm-9">
                    	<img id="imgPreview" alt="上传图片预览" src=""/>                           
                    </div>
                  </div>
                  
                </form>
	
	
			<form class="form-horizontal" id="picUploadForm" action="test.html" role="form" method="post" enctype="multipart/form-data">
				<!-- 实际的file控件 -->
				<input id="photoFile" type="file" name="photoFile" style="display:none">
			</form>
	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="../js/jquery.bootstrap.min.js"></script>
	<script src="../js/jquery.form.js"></script>	 
	<script type="text/javascript">
		$('input[id=photoFile]').change(function() {
			$('#photoCover').val($(this).val());
			$('#picUploadForm').ajaxSubmit({
				success: function (data) {
					if(typeof(data)!='object') data=JSON.parse(data);
					console.info(data.errorInfo);
	                $('#addAuthForm').find('input[name=pictureName]').val(data.files);
	                console.info(data.files);
	                $('#imgPreview').attr('src','${pageContext.request.contextPath}/'+data.files);
	                $('#imgPreview').parent().parent().show();
	                //alert($('#addAuthForm').find('input[name=pictureName]').val());
	            }
			});
		});
	</script>
	 
</body>
</html>

