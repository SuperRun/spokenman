var driverSearch = {
    url: {
        searchSfz: function (sfzNo) {
            return path + "/driver/sfz/" + sfzNo;
        }
    },
    ids: {
        sfzNo: 'sfzNo'
    }
};
function submit() {
    if (check_sfz_no()) {
        console.log("---4---");

    }
}

function checkEntry() {
    var sfz_no = $("#sfzNo").val();
    var success_info = $("#successInfo");
    var false_info = $("#falseInfo");
    var suchref = $("#suchref");
    var falhref = $("#falhref");

    console.log("---5---");
    $.ajax({
        url: 'entry/' + sfz_no,
        type:'get',
        dataType:'json',
        //data:{sfzNo:sfz_no},
        success : function(rd) {
            if (!rd) {
                console.log("---6---");
                success_info.show();
                $("#sfz_no_hint").hide();
                $("#falseInfo").hide();
                suchref.attr("href", path + "/driver/doEntry/" + sfz_no);

            } else {
                console.log("---7---");
                false_info.show();
                $("#sfz_no_hint").hide();
                $("#successInfo").hide();
                falhref.attr("href", path + "/driver/create");

            }
        },
        error : function(r){

        }
    });
}

function check_sfz_no(){
    var sfz_no = $("#sfzNo").val();
    var sfz_no_hint = $("#sfz_no_hint");
    var check = true;
    if(sfz_no.length != 18 && sfz_no.length != 15){
        sfz_no_hint.html("请输入正确位数身份证号");
        $("#successInfo").hide();
        $("#falseInfo").hide();
        sfz_no_hint.show();
        console.log("jinru check_mobile");
        check = false;
        return false;
    } else {
        //var j = 1;
        for (var i = 0; i < sfz_no.length; i++) {
            if(sfz_no.charAt(i)<"0" || sfz_no.charAt(i)>"9"){
                sfz_no_hint.html("身份证号必须为纯数字");
                $("#successInfo").hide();
                $("#falseInfo").hide();
                sfz_no_hint.show();
                check = false;
                return false;
            }
        }
    }
    if (check){

        console.log("---1---");
            $.ajax({
                url: 'check/sfz/' + sfz_no,
                type:'get',
                dataType:'json',
                //data:{sfzNo:sfz_no},
                success : function(rd) {
                    if (rd) {
                        console.log("---2---");
                        sfz_no_hint.hide();
                        checkEntry();
                        return true;
                    } else {
                        console.log("---3---");
                        sfz_no_hint.html("该驾驶员是其他公司还未离职的驾驶员，如果入职有意向，请联系该驾驶员相关单位进行离职，再办理入职");
                        $("#successInfo").hide();
                        $("#falseInfo").hide();
                        sfz_no_hint.show();
                    }
                },
                error : function(r){

                }
            });

    }
    //sfz_no_hint.hide();

}