var modifyDepartment = {
	url : {
		modify : function(depId) {
			return path + "/department/" + depId ;
		}
	},
	funcs : {
		modify : function(depId, name, description) {
			$.ajax({
				type : 'POST',
				dataType : 'json',
				url : modifyDepartment.url.modify(depId),
				data : {
					name : name,
					description : description
				},
				success : function(r) {
					console.log(r);
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