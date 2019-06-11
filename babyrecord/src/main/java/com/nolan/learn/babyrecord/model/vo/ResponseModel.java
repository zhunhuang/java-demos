package com.nolan.learn.babyrecord.model.vo;

import lombok.Builder;
import lombok.Data;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
@Data
@Builder
public class ResponseModel<T> {

    private int status;

    private String msg;

    private T data;


    public static <T> ResponseModel<T> fail(int status, String msg) {
        return ResponseModel.<T>builder().msg(msg)
                .status(status).build();
    }

    public static <T> ResponseModel<T> success(T data) {
        return ResponseModel.<T>builder().data(data).build();
    }
}
