var createDepartment = {
	url : {
		create : path + '/department/create'
	},
	funcs : {
		create : function(name, description) {
			$.ajax({
				url : createDepartment.url.create,
				type : 'post',
				dataType : 'json',
				data : {
					name : name,
					description : description,
					orgId : orgId
				},
				success : function(r) {
					if (r.success)
						alert("添加成功");
				}
			});
		}
	}

};