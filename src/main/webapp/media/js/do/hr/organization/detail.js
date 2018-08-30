var organizationDetail = {
    url: {
        deleteOrg:function(orgId){
            return path+"/organization/"+orgId;
        },
        enableOrg:function(orgId){
            return path+"/organization/"+orgId;
        }
    },
    funcs: {
        enableOrg: function (orgId) {
        //    启用org
            $.ajax({
                type:'PUT',
                url:organizationDetail.url.enableOrg(orgId),
                dataType:'json',
                success:function(r){
                    console.log(r);
                    if (r.success){
                        location.reload(true);
                    }else {
                        alert(r.error);
                    }
                },
                error: function (r) {
                    alert('失败(' + r.status + ' ' + r.statusText + ')');
                }
            });
        },

        deleteOrg: function (orgId) {
            $.ajax({
                type: 'DELETE',
                url: organizationDetail.url.deleteOrg(orgId),
                dataType: 'json',
                success:function(r){
                    console.log(r);
                    if (r.success){
                        location.reload(true);
                    }else {
                        alert(r.error);
                    }
                },
                error: function (r) {
                    alert('失败(' + r.status + ' ' + r.statusText + ')');
                }
            });
        }


    }
};
