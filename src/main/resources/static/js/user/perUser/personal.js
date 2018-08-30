$(".more-user-info").click(function(){
    $(".user-sign").toggle();
    if($(".user-info").css("padding-bottom","48px")==true){
        $(".user-info").css("padding-bottom","0px");
    }else{
        $(".user-info").css("padding-bottom","48px");
    }

});
$(document).ready(function(){
    var oDiv = document.getElementById("slider"),
    H = 0,
    Y = oDiv;
    while (Y) {H += Y.offsetTop; Y = Y.offsetParent}
    $(window).scroll(function (){

        if ($(window).scrollTop() >H) {
            $("#slider").css({"position":"fixed","top":"20px"});
        }else{
            $("#slider").css({"position":"absolute","top":"0"});
        }
    });
    if($("#slider a").hasClass("active")==true){
        $("#slider  .icon-drop_right").hide();
        $("#slider .active .icon-drop_right").show();

    }else{
        $("#slider  .icon-drop_right").css("dispaly","none");
    }
});
