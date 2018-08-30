/**
 * 搜索区域选择省市区弹出框事件
 * 
 * @returns
 */
layui.use(['form','province'],function(){
	var $=layui.$;
    var form=layui.form;
    var province=layui.province;

	  /*搜索区域选择省市区弹出框事件*/
		
    $('#area').click(function(e){
        $('.choose-area').slideDown(300);
        e.stopPropagation();
        var default1={
                s1: 'province',
                s2: 'city',
                s3: 'county',
                v1: null,
                v2: null,
                v3: null      
         }
         province.linkage(default1);
    });
    $('.choose-area').click(function(e){
        e.stopPropagation();
    });
    $('body').click(function(e){
        $('.choose-area').slideUp(300);
        e.stopPropagation();
    });
    $('#area-cancel').click(function(e){
        $('.choose-area').slideUp(300);
        e.stopPropagation();
        //清空select
        $("#province").empty();
  	    $("#city").empty();
  	    $("#county").empty();
  	    form.render('select','choose-form');
    });
    $('#area-confirm').click(function(e){
    	var province=$("#province option:selected").text();
        var city=$("#city option:selected").text();
        var county=$("#county option:selected").text();
        $('#area').css('color','#000');
        $('#area').val(province+'-'+city+'-'+county);
        $('.choose-area').slideUp(300);
        e.stopPropagation();
    });
    
});