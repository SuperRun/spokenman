package com.zust.itee.exam.dto.base;

import java.util.List;

/**
 * 分页所需基本参数(当前页码、每页行数、总页数、搜索条件)
 *
 * @author sdy
 */
public class PageBaseDto {

    public static int ROWS_PER_PAGE = 5;

    // 当前页数
    private Integer page;

    // 每页显示行数
    private Integer rows;

    // 页码总数
    private Integer pageNum;

    // 记录总数
    private Long sum;

    // 搜索条关键字
    private String searchKey;

    // 筛选条件
    private Short status;

    private List<? extends Object> list;


    /**
     * @param pageBaseDto
     * @return
     * @author sdy
     * @why 保证pageBaseDto中内容不为空
     */
    public static PageBaseDto ENSURENOTNULL(PageBaseDto pageBaseDto) {
        if (pageBaseDto.getPage() == null || pageBaseDto.getRows() == null || pageBaseDto.getSearchKey() == null
                || pageBaseDto.getStatus() == null) {
            PageBaseDto trans = new PageBaseDto();
            // 默认跳转第一页，每页显示五行
            trans.setPage(1);
            trans.setRows(PageBaseDto.ROWS_PER_PAGE);
            trans.setSearchKey("");
            trans.setStatus((short) -1);
            return trans;
        } else {
            return pageBaseDto;
        }
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public List<? extends Object> getList() {
        return list;
    }

    public void setList(List<? extends Object> list) {
        this.list = list;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PageBaseDto{" +
                "page=" + page +
                ", rows=" + rows +
                ", pageNum=" + pageNum +
                ", sum=" + sum +
                ", searchKey='" + searchKey + '\'' +
                ", status=" + status +
                '}';
    }


    public void setPageNum(int rows, Long sum) {
        if (rows == -1) {
            setPageNum(1);
        } else {
            double temp = sum * 1.0 / rows;
            setPageNum((int) Math.ceil(temp));
        }
    }


}
