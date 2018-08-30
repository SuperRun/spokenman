package com.zust.itee.otacore.domain;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页所需基本参数(当前页码、每页行数、总页数、搜索条件)
 *
 * @author sdy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageBaseDto {

    public static int ROWS_PER_PAGE = 10;

    // 当前页数
    private Integer page;

    // 每页显示行数
    private Integer rows;

    // 页码总数
    private Integer pageNum;

    // 记录总数
    private Long sum;

    /**
     * @author sdy
     * @why 保证pageBaseDto中内容不为空
     */
    public void ensureNotNull() {
        if (this.getPage() == null) {
            // 默认跳转第一页，每页显示十行
            this.setPage(1);
            this.setRows(PageBaseDto.ROWS_PER_PAGE);
        }
    }

    public void setSumAndPageNum(int rows, Long sum) {
        setSum(sum);
        if (rows == -1) {
            setPageNum(1);
        } else {
            double temp = sum * 1.0 / rows;
            setPageNum((int) Math.ceil(temp));
        }
    }

    public void setSumAndPageNum(Long sum) {
        setSum(sum);
        if (getRows() == -1) {
            setPageNum(1);
        } else {
            double temp = sum * 1.0 / getRows();
            setPageNum((int) Math.ceil(temp));
        }
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
