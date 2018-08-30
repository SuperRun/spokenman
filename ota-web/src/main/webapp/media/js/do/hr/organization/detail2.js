var organizationDetail = {
    url: {
        deleteUrl: function (orgId) {
            return path + "/organization/" + orgId;
        },
        enable: function (orgId) {
            return path + "/organization/" + orgId;
        },
        verifyFail: function (orgId) {
            return path + "/organization/" + orgId + "/verifyfail";
        }
    },

    funcs: {
        deleteOrg: function (orgId) {
            $.ajax({
                type: 'DELETE',
                url: organizationDetail.url.deleteUrl(orgId),
                dataType: 'json',
                success: function (r) {
                    console.log(r);
                }
            });
        }
    }
};