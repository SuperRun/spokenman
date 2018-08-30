
var organizationList = {
    url : {
        getChildList : function(parentOrgId) {
            return path + '/organization/' + parentOrgId + '/childs';
        }
    },
    funcs : {
        getChildList : function(parentOrgId, liElement) {
            $.ajax({
                type : 'GET',
                dataType : 'json',
                url : organizationList.url.getChildList(parentOrgId),
                success : function(r) {
                    console.log(r);
                    if (r.success) {
                        console.log('return :' + r.data);
                        organizationList.funcs.showList(r.data, liElement);
                    }
                },
                error : function(r) {
                    console.log("error");
                    console.log(r);
                }
            });
        },
        getOrgId : function(btnElement) {
            // 取出存放在btn中存放的id
            var btnId = btnElement.attr('id');
            return btnId.replace('btn', '');
        },
        showList : function(l, liElement) {
            console.log('l:' + l);
            var appendStr = '<ul>';
            for (var i = 0; i < l.length; i++) {
                var item = l[i];
                appendStr += ('<li><a target="_blank" href="' + basePath
                + 'organization/' + item.id + '">' + item.shortName
                + '(' + item.name
                + ')</a> ');
                if(item.type!='COMPANY'){
                    appendStr+=('<button  class="btn btn-primary showListBtn showBtn" id="btn' + item.id + '" >展开</button>');
                }
                appendStr+= ('</li>');
            }
            if(l.length ==0){
                appendStr+='<li><span style="color: grey;">无</span></li>'
            }

            appendStr += '</ul>';
            liElement.append(appendStr);
            organizationList.funcs.ready();
        },
        ready:function(){
            var btns=$('.showBtn');
            btns.unbind("click");
            btns.click(function () {
                var btn = $(this);
                btn.hide();
                var orgId = organizationList.funcs.getOrgId(btn);
                organizationList.funcs.getChildList(orgId,btn.parent());
            });
        }
    }

};
