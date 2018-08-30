package com.zust.itee.exam.dto.exam;

public class PageBaseDtoForSignup {

	private static Integer ROWS_PER_PAGE=5;

	// 当前页数
	private Integer pageForSignup;

	// 每页显示行数
	private Integer rowsForSignup;

	// 页码总数
	private Integer pageNumForSignup;

	// 记录总数
	private Long sumForSignup;

	// 搜索条关键字
	private String searchKeyForSignup;

	public Integer getPageForSignup() {
		return pageForSignup;
	}

	public void setPageForSignup(Integer pageForSignup) {
		this.pageForSignup = pageForSignup;
	}

	public Integer getRowsForSignup() {
		return rowsForSignup;
	}

	public void setRowsForSignup(Integer rowsForSignup) {
		this.rowsForSignup = rowsForSignup;
	}

	public Integer getPageNumForSignup() {
		return pageNumForSignup;
	}

	public void setPageNumForSignup(Integer pageNumForSignup) {
		this.pageNumForSignup = pageNumForSignup;
	}

	public Long getSumForSignup() {
		return sumForSignup;
	}

	public void setSumForSignup(Long sumForSignup) {
		this.sumForSignup = sumForSignup;
	}

	public String getSearchKeyForSignup() {
		return searchKeyForSignup;
	}

	public void setSearchKeyForSignup(String searchKeyForSignup) {
		this.searchKeyForSignup = searchKeyForSignup;
	}

	public void setPageNum(int rows, Long sum) {
		if (rows == -1) {
			setPageNumForSignup(1);
		} else {
			double temp = sum * 1.0 / rows;
			setPageNumForSignup((int) Math.ceil(temp));
		}
	}

	/**
	 * @param pageBaseDto
	 * @return
	 * @author sdy
	 * @why 保证pageBaseDto中内容不为空
	 */
	public static PageBaseDtoForSignup ENSURENOTNULL(
			PageBaseDtoForSignup pageBaseDto) {
		if (pageBaseDto.getPageForSignup() == null
				|| pageBaseDto.getRowsForSignup() == null
				|| pageBaseDto.getSearchKeyForSignup() == null) {
			PageBaseDtoForSignup trans = new PageBaseDtoForSignup();
			// 默认跳转第一页，每页显示五行
			trans.setPageForSignup(1);
			trans.setRowsForSignup(PageBaseDtoForSignup.ROWS_PER_PAGE);
			trans.setSearchKeyForSignup("");
			return trans;
		} else {
			return pageBaseDto;
		}
	}

}
