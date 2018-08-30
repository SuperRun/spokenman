package com.zust.itee.dto.base;

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
public class JsonResponse<T> {

    private static int DEFAULT_SUCCESS_CODE = 1;

    private static int DEFAULT_ERROR_CODE = 0;

    private static String DEFAULT_ERROR_MSG = "系统繁忙，请稍后再试";

    @ApiModelProperty(value = "响应码。1：成功，0：失败。失败码后续可扩展")
    private Integer code;

    @ApiModelProperty("错误描述")
    private String msg;

    @ApiModelProperty("响应数据")
    private T data;

    public static <T> JsonResponse<T> success(T data) {
        JsonResponse<T> response = new JsonResponse<>();
        response.setData(data);
        response.setCode(DEFAULT_SUCCESS_CODE);
        return response;
    }

    public static <T> JsonResponse<T> error(String msg, Integer code) {
        //noinspection unchecked
        return (JsonResponse<T>) JsonResponse.builder()
                .msg(msg)
                .code(code)
                .build();
    }

    public static <T> JsonResponse<T> error(String msg) {
        //noinspection unchecked
        return (JsonResponse<T>) JsonResponse.builder()
                .msg(msg)
                .code(DEFAULT_ERROR_CODE)
                .build();
    }

    public static <T> JsonResponse<T> error() {
        //noinspection unchecked
        return (JsonResponse<T>) JsonResponse.builder()
                .msg(DEFAULT_ERROR_MSG)
                .code(DEFAULT_ERROR_CODE)
                .build();
    }
}
