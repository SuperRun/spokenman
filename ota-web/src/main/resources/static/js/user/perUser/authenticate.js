new Vue({
	el:"#app",
	data:{
		userId:"",
		userApply:{
			name:"",
			idCard:"",
			picFront:"",
			picBack:"",
			typeId:"",
			level:"",
			areaId:"",
			orgName:"",
			picOrg:"",
			status:""
		},
		imgSrc:{
			front:"",
			back:"",
			org:""
		},
		uploadPic:[{
			code:0,
			path:""
		}]
	},
	mounted:function(){
		var self=this;
		self.fetch();
		self.userId=$("#userId").val();
		self.getPerApplyApproval();
	},
	methods:{
		fetch:async function(){
			var self=this
			//上传图片
			$('#front').click(function(){
				$('#uploadFront').click();
			});
			$('#uploadFront').change(function(){
				var file=$(this)[0].files[0];
				var url=self.scanPic(file);
				$("#frontPic").css('display','inline');
				self.imgSrc.front=url;
				//$("#frontPic").attr('src',url);
			});
			$('#back').click(function(){
				$('#uploadBack').click();
			});
			$('#uploadBack').change(function(){
				var file=$(this)[0].files[0];
				var url=self.scanPic(file);
				$("#backPic").css('display','inline');
				self.imgSrc.back=url;
				//$("#backPic").attr('src',url);
			});
			$('#org').click(function(){
				$('#uploadOrg').click();
			});
			$('#uploadOrg').change(function(){
				var file=$(this)[0].files[0];
				var url=self.scanPic(file);
				$("#orgPic").css('display','inline');
				self.imgSrc.org=url;
				//$("#orgPic").attr('src',url);
			});
			
        	layui.use('province',function(){
        		var province=layui.province;
      			//增加表单部分的select
      		    var default2={
      		            s1: 'province',
      		            s2: 'city',
      		            s3: 'county',
      		            v1: null,
      		            v2: null,
      		            v3: null      
      		    }
      			province.linkage(default2);
      		   // form.render();
        	});
        	
        	    
          //从后台获取条线
    	   await $.get('/data-dictionary/org-types',function(res){
    	    	var names=res.data;
    	    	console.log(names);
    	    	for(var i=0;i<names.length;i++){
    	    		$(".getType").each(function(){
    	     			$(this).append("<option value='"+names[i].id+"'>"+names[i].remark+"</option>");
    	     		});
    	    	}
    	    	layui.use('form',function(){
    	    		var form=layui.form;
    	    		form.render();
    	    	});
    	    });

		},
		scanPic:function(file){
			var url = null ;
		    if (window.createObjectURL!=undefined){ // basic
		      url = window.createObjectURL(file) ;
		    }else if (window.URL!=undefined){
		      // mozilla(firefox)
		      url = window.URL.createObjectURL(file) ;
		    }else if (window.webkitURL!=undefined) {
		      // webkit or chrome
		      url = window.webkitURL.createObjectURL(file) ;
		    }
		    return url 
		},
		subApplyApproval:async function(){
			var self=this;
			var uploadBtn=['#uploadFront','#uploadBack','#uploadOrg'];
			var picPath=[];
			var note=self.verifyPerInfo();
			if(note.code===1){
				layui.use('layer',function(){
					var layer=layui.layer;
					layer.msg(note.tip);
				});
			}else if(note.code===0){
				var temp=0;//判断上传了几张图
				for(var k=0;k<uploadBtn.length;k++){
					if(($(uploadBtn[k])[0].files[0])!=null){
						temp++;
					}
				}
				console.log("temp="+temp);
				for(var i=0;i<uploadBtn.length;i++){
					
					if(($(uploadBtn[i])[0].files[0])!=null){
						var formData = new FormData();
						var pic={'code':0,'path':''};
			            pic.code=i;
						formData.append('file',$(uploadBtn[i])[0].files[0]);
						//console.log($(uploadBtn[i])[0].files[0]);
						formData.append('filename',$(uploadBtn[i])[0].files[0].name);
						//console.log("i="+i);
						await $.ajax({
							url:"/file-upload/pic",
							type:'post',
							data:formData,
							cache: false,//上传文件无需缓存
			                processData: false,//用于对data参数进行序列化处理 这里必须false
			                contentType: false, //必须
							success:function(res){
				               console.log(res);
				               //保存文件路径
				               pic.path=res.data;
				               console.log(pic);
				               picPath.push(pic);
				               if(picPath.length==temp){
				            	   console.log("picPath.length="+picPath.length);
				            	   for(var j=0;j<picPath.length;j++){
				            		   console.log(picPath[j]);
				            		   switch(picPath[j].code){
							               case 0:
							                 self.userApply.picFront=picPath[j].path;
							                 break;
							               case 1:
							            	 self.userApply.picBack=picPath[j].path;
							                 break;
							               case 2:
							            	 self.userApply.picOrg=picPath[j].path;
							            	 break;
				            		   }
				            	   }
				            	   self.insertApply();
				               }
				            }
				        });
					}else if(i==2){//用户未重新上传图片
						console.log("图片未更改。");
						self.insertApply();
					}
						
				}
			}
		},
		insertApply:function(){
			var self=this;
			self.userApply.typeId=$("#typeId").val();
			self.userApply.level=$("#level").val();
			self.userApply.areaId=$("#county").val();
			console.log("上传");
			console.log(self.userApply);
			$.ajax({
				url:"/user-apply/personal/approval",
				type:'post',
				data:self.userApply,
				success:function(res){
					layui.use('layer',function(){
						var layer=layui.layer;
						layer.msg('提交成功！',function(){
							//location.reload();
						});
					});
					
					//self.setProgress();
				}
    	   });
		},
		getPerApplyApproval:function(){
			var self=this;
			$.get('/user/'+self.userId,function(res){
				self.userApply=res.data;
				console.log(res);
				if(res.data.picFront!=null){
					$("#frontPic").css('display','inline');
					//$("#frontPic").attr('src', res.data.picFront);
					self.imgSrc.front=res.data.picFront;
				}
				if(res.data.picBack!=null){
					$("#backPic").css('display','inline');
					//$("#backPic").attr('src', res.data.picBack);
					self.imgSrc.back=res.data.picBack;
				}
				if(res.data.picOrg!=null){
					$("#orgPic").css('display','inline');
					//$("#orgPic").attr('src', res.data.picOrg);
					self.imgSrc.org=res.data.picOrg;
				}
				
				layui.use(['province','form'],function(){
					var form=layui.form;
					var province=layui.province;
					var areaCode=new Array();
					var areaId=self.userApply.areaId;
					console.log("条线");
                	console.log($("#typeId").html());
					$("#typeId").find("option[value='"+self.userApply.typeId+"']").attr("selected",true);
					$("#level").find("option[value='"+self.userApply.level+"']").attr("selected",true);
		            	
	            	if(areaId!=null){
	            		console.log(areaId);
	                	areaCode[0]=areaId.toString().substring(0,2)+'0000';
	                	areaCode[1]=areaId.toString().substring(0,3)+'000';
	                	areaCode[2]=areaId;
	                	console.log("省");
	                	console.log(areaCode[0]);
	                	console.log("市");
	                	console.log(areaCode[1]);
	                	console.log("区");
	                	console.log(areaCode[2]);
	                	var default3={
	                            s1: 'province',
	                            s2: 'city',
	                            s3: 'county',
	                            v1: areaCode[0],
	                            v2: areaCode[1],
	                            v3: areaCode[2]
	                   };
	                   province.linkage(default3);
	            	}
					//组织部分信息  单位下的用户无法修改
					if(res.data.orgId!=1){
						$("#typeId").attr('disabled','disabled');
						$("#level").attr('disabled','disabled');
						$("#province").attr('disabled','disabled');
						$("#city").attr('disabled','disabled');
						$("#county").attr('disabled','disabled');
						$("#orgText").attr('readOnly',true);
					}
					form.render();
				});
				
				console.log(self.userApply.status);
				self.setProgress();
				
			});
		},
		verifyPerInfo:function(){
			var self=this;
			var name=self.userApply.name;
			var idCard=self.userApply.idCard;
			var picFront=self.imgSrc.front;
			var picBack=self.imgSrc.back;
			var typeId=$("#typeId").val();
			var level=$("#level").val();
			var orgName=self.userApply.orgName;
			var picOrg=self.imgSrc.org;
			var msg={'code':0,'tip':''};
			console.log("信息");
			console.log(self.imgSrc);
			if(name==''||idCard==''||picFront==''||picBack==''){
				msg.code=1;
				msg.tip="身份认证信息不完整";
				return msg;
			}else if(!(self.idCodeValid(idCard).pass)){
				msg.code=1;
				msg.tip=self.idCodeValid(idCard).msg;
				return msg;
			}else if(typeId==''||level==''||orgName==''||picOrg=='' && self.userApply.orgId!=1){
				msg.code=1;
				msg.tip="组织认证信息不完整";
				return msg;
			}
			return msg;
		},
		idCodeValid:function(code){  
		    //身份证号合法性验证  
		    //支持15位和18位身份证号  
		    //支持地址编码、出生日期、校验位验证  
		    var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};  
		    var row={  
		        'pass':true,  
		        'msg':'验证成功'  
		    };  
		    if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|[xX])$/.test(code)){  
		        row={  
		            'pass':false,  
		            'msg':'身份证号格式错误'  
		        };  
		    }else if(!city[code.substr(0,2)]){  
		        row={  
		            'pass':false,  
		            'msg':'身份证号地址编码错误'  
		        };  
		    }else{  
		        //18位身份证需要验证最后一位校验位  
		        if(code.length == 18){  
		            code = code.split('');  
		            //∑(ai×Wi)(mod 11)  
		            //加权因子  
		            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];  
		            //校验位  
		            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];  
		            var sum = 0;  
		            var ai = 0;  
		            var wi = 0;  
		            for (var i = 0; i < 17; i++)  
		            {  
		                ai = code[i];  
		                wi = factor[i];  
		                sum += ai * wi;  
		            }  
		            if(parity[sum % 11] != code[17].toUpperCase()){  
		                row={  
		                    'pass':false,  
		                    'msg':'身份证号校验位错误'  
		                };  
		            }  
		        }  
		    }  
		    return row;  
		},
		setProgress:function(){
			var self=this;
			if(self.userApply.status===1){
				$("#crumbs ul li").find(function(){
					$(this).addClass("status2");
				});
			}else if(self.userApply.picFront==null){
				$("#crumbs ul li").find(function(){
					$(this).removeClass("status2");
				});
			}else if(self.userApply.picFront!=null){
				$("#crumbs ul li").each(function(index){
					if(index===2){
						$(this).removeClass("status2");
					}else{
						$(this).addClass("status2");
					}
					
				});
			}
		}
	}
});

