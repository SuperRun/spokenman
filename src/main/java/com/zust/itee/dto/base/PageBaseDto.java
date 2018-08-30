package com.zust.itee.dto.base;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageBaseDto<T> {

    public static int ROWS_PER_PAGE = 10;

    @ApiModelProperty("当前页码，默认1")
    private Integer page;

    @ApiModelProperty("每页显示行数，默认10")
    private Integer rows;

    @ApiModelProperty("页数")
    private Integer pageNum;

    @ApiModelProperty("记录数")
    private Long sum;

    @ApiModelProperty("数据")
    private List<T> data;


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