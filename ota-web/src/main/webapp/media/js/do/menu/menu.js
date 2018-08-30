var menu = {
    url: {
        getMenu: function () {
            return path + '/menu';
        }
    },
    ids: {
        menu: 'nav_menu'
    },
    funcs: {
        getMemberMenu: function () {
            $.ajax({
                url: menu.url.getMenu(),
                success: function (r) {
                    if (r.success) {
                        menu.funcs.createMenu(menu.ids.menu, r.data);
                    } else {
                        alert("请求菜单失败");
                    }
                },
                error: function (r) {
                    console.log('请求菜单失败 ' + r.status + ' ' + r.statusText);
                }
            });
        },
        createMenu: function (ulId, jsonMenu) {
            appendStr = '';
            for (var i = 0; i < jsonMenu.items.length; i++) {
                appendStr += ('<li>');
                if (jsonMenu.items[i].url) {
                    appendStr += ('<a class="menu_underline" href="' + path + jsonMenu.items[i].url);
                } else {
                    appendStr += ('<a class="menu" href="javascript:(0);');
                }
                appendStr += '"+">';
                appendStr += jsonMenu.items[i].name;
                if (jsonMenu.items[i].num) {
                    appendStr += (' <span class="badge">' + jsonMenu.items[i].num + '</span>');
                }
                appendStr += ('</a>');
                if (jsonMenu.items[i].items) {

                    for (var j = 0; j < jsonMenu.items[i].items.length; j++) {
                        if (j == 0) {
                            appendStr += '<ul class="sub_menu">';
                        }
                        if (jsonMenu.items[i].items[j].num) {
                            appendStr += ('<li><a class="sub_list" href="' + path + jsonMenu.items[i].items[j].url + '">' + jsonMenu.items[i].items[j].name + ' <span class="badge">' + jsonMenu.items[i].items[j].num + '</span></a></li>');
                        } else {
                            appendStr += ('<li><a class="sub_list" href="' + path + jsonMenu.items[i].items[j].url + '">' + jsonMenu.items[i].items[j].name + '</a></li>');
                        }
                        if (j == jsonMenu.items[i].items.length - 1) {
                            appendStr += '</ul>';
                        }
                    }
                }
                appendStr += ('</li>');
            }
            $('#' + ulId).html(appendStr);
            menu.funcs.refreshBind();
        },
        refreshBind: function () {
            var menu = new Array();
            menu = $(".menu");
            var a_hover = $(".a_hover");
            for (var k = 0; k < a_hover.length; k++) {
                $(a_hover[k]).next().show();
            }
            for (var i = 0; i < menu.length; i++) {
                menu[i].onclick = function () {
                    var sub_menu = $(this).next();
                    for (var j = 0; j < menu.length; j++) {
                        if (j != i) {
                            $(menu[j]).removeClass("a_hover");
                            $(menu[j]).next().hide();
                        }

                    }
                    $(this).addClass("a_hover");

                    if (sub_menu.css("display") == "none") {
                        sub_menu.show();
                    } else if (sub_menu.css("display") != "none") {
                        sub_menu.hide();
                    }

                }
            }
            var sub_list = new Array();
            sub_list = $(".sub_list");
            for (var m = 0; m < sub_list.length; m++) {
                sub_list[m].onclick = function () {
                    for (var n = 0; n < sub_list.length; n++) {
                        if (n != m) {
                            $(sub_list[n]).removeClass("list_hover");
                        }
                    }

                    $(this).addClass("list_hover");
                }
            }
        }
    }

};