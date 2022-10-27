package com.lzh.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author linzhihao
 * @Date 2022/7/15 8:42 下午
 * @Description 通用响应对象定义
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> implements Serializable {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 泛型响应数据
     */
    private T data;

    public CommonResponse(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
