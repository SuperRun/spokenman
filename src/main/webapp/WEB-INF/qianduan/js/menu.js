var menu = {
    "items": [
        {
            "name": "首页",
            "url": "http://baidu.com/ksbm"
        },
        {
            "name": "考试管理",
            "items": [
                {
                    "name": "考试报名",
                    "url": "http://baidu.com/ksbm"
                },
                {
                    "name": "我的考试",
                    "url": "http://baidu.com/wdks"
                },
                {
                    "name": "模拟考试",
                    "url": "http://baidu.com/wdks"
                }
            ]
        },
        {
            "name": "我的证书",
            "url": "http://baidu.com/ksbm"
        }
    ]
};

function createMenu(ulId, jsonMenu) {
    appendStr = '';
    for (var i = 0; i < jsonMenu.items.length; i++) {
        appendStr += ('<li>');
        if (jsonMenu.items[i].url) {
            appendStr += ('<a class="menu_underline" href="' + jsonMenu.items[i].url);
        }else{
            appendStr+=('<a class="menu" href="#');
        }
        appendStr+='"+">';
        appendStr += jsonMenu.items[i].name;
        if (jsonMenu.items[i].num) {
            appendStr += ('<span class="badge">' + jsonMenu.items[i].num + '</span>');
        }
        appendStr += ('</a>');
        if (jsonMenu.items[i].items) {

            for (var j = 0; j < jsonMenu.items[i].items.length; j++) {
                if (j == 0) {
                    appendStr += '<ul class="sub_menu">';
                }
                if (jsonMenu.items[i].items[j].num) {
                    appendStr += ('<li><a class="sub_list" href="' + jsonMenu.items[i].items[j].url + '">' + jsonMenu.items[i].items[j].name + '<span class="badge">' + jsonMenu.items[i].items[j].num + '</span></a></li>');
                }else{
                    appendStr += ('<li><a class="sub_list" href="' + jsonMenu.items[i].items[j].url + '">' + jsonMenu.items[i].items[j].name + '</a></li>');
                }
                if (j == jsonMenu.items[i].items.length - 1) {
                    appendStr += '</ul>';
                }
            }
        }
        appendStr += ('</li>');
    }
    $('#' + ulId).html(appendStr);
}
   