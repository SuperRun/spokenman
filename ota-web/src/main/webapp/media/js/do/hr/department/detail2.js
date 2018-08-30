var departmemtDetail = {
	url : {
		delDep : function(depId) {
			return path + "/department/" + depId;
		},
		disableDep : function(depId) {
			return path + "/department/" + depId+"/disable";
		},
		enableDep : function(depId) {
			return path + "/department/" + depId+"/enable";
		}
	},
	funcs : {
		delDep : function(depId) {
			$.ajax({
				type : 'delete',
				url : departmemtDetail.url.delDep(depId),
				success : function(r) {
					if (r.success) {
						alert(r.data);
					} else {
						alert(r.error);
					}
				},
				error : function(r) {
					alert(r.status + " " + r.statusText);
				}
			});
		},
		disableDep : function(depId) {
			$.ajax({
				type : 'get',
				url : departmemtDetail.url.disableDep(depId),
				success : function(r) {
					if (r.success) {
						alert(r.data);
					} else {
						alert(r.error);
					}
				},
				error : function(r) {
					alert(r.status + " " + r.statusText);
				}
			});
		},
		enableDep : function(depId) {
			$.ajax({
				type : 'get',
				url : departmemtDetail.url.enableDep(depId),
				success : function(r) {
					if (r.success) {
						alert(r.data);
					} else {
						alert(r.error);
					}
				},
				error : function(r) {
					alert(r.status + " " + r.statusText);
				}
			});
		}
	}
};