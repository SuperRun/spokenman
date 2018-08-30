/**
 * 分页信息提交
 */
function submit() {
	// 获取上一次的每页行数
	var rowsPre = $("#rows").val();

	// 获取当前每页行数
	var rows = $("#rows_input").val();

	$("#rows").val(rows);

	// 如果每页行数变化，跳转到第一页
	if (rowsPre != rows) {
		$("#page").val(1);
	}

	// 获取搜索关键词
	var searchKey = $("#search_text").val();
	$("#searchKey").val(searchKey);

	// 表单提交
	$("#data").submit();
}

/**
 * 跳页
 * @param page
 * @returns {Boolean}
 */
function changePage(page) {
	if (page == -1) {
		// 用户输入页面
		page = $("#page_text").val();
		pageNum = $("#pageNum").val();
		if (page > pageNum || page <= 0) {
			// 跳页不合法
			return false;
		}
	}
	$("#page").val(page);

	// 提交表单，不再次赋值搜索关键字，不然跳页可能不正常
	$("#data").submit();
}