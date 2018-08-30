package com.zust.itee.dto.base;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json 响应
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LayuiResponse<T> {

    private static int DEFAULT_SUCCESS_CODE = 0;

    private static int DEFAULT_ERROR_CODE = 1;

    private static String DEFAULT_ERROR_MSG = "系统繁忙，请稍后再试";

    @ApiModelProperty(value = "响应码。0：成功，1：失败。失败码后续可扩展")
    private Integer code;

    @ApiModelProperty("错误描述")
    private String msg;

    @ApiModelProperty("总记录数")
    private long count;

    @ApiModelProperty("响应数据")
    private List<T> data;

    public static <T> LayuiResponse<T> success(List<T> data) {
        LayuiResponse<T> response = new LayuiResponse<>();
        response.setData(data);
        response.setMsg("");
        response.setCount(data.size());
        response.setCode(DEFAULT_SUCCESS_CODE);
        return response;
    }

    public static <T> LayuiResponse<T> success(PageBaseDto<T> pageBaseDto) {
        LayuiResponse<T> response = new LayuiResponse<>();
        response.setData(pageBaseDto.getData());
        response.setMsg("");
        response.setCount(pageBaseDto.getSum());
        response.setCode(DEFAULT_SUCCESS_CODE);
        return response;
    }

    public static <T> LayuiResponse<T> error(String msg, Integer code) {
        //noinspection unchecked
        return (LayuiResponse<T>) LayuiResponse.builder()
                .msg(msg)
                .code(code)
                .build();
    }

    public static <T> LayuiResponse<T> error(String msg) {
        //noinspection unchecked
        return (LayuiResponse<T>) LayuiResponse.builder()
                .msg(msg)
                .code(DEFAULT_ERROR_CODE)
                .build();
    }

    public static <T> LayuiResponse<T> error() {
        //noinspection unchecked
        return (LayuiResponse<T>) LayuiResponse.builder()
                .msg(DEFAULT_ERROR_MSG)
                .code(DEFAULT_ERROR_CODE)
                .build();
    }
}
