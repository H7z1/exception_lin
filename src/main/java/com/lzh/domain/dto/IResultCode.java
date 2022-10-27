package com.lzh.domain.dto;

/**
 * 返回码接口
 *
 */
public interface IResultCode {

    /**
     * 返回码
     *
     * @return int
     */
    int getCode();

    /**
     * 返回消息
     *
     * @return String
     */
    String getMsg();
}
