package com.lzh.exception;

import com.lzh.domain.dto.IResultCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;


/**
 * 接口业务异常
 *
 * @author guoyongxiang
 * @version 1.0.0
 * @date 2021/10/21 14:18
 **/
@Getter
public class ApiException extends BaseException {

    private IResultCode resultCode;
    private String msg;
    private Object data;

    public ApiException(IResultCode resultCode){
        super(resultCode.getMsg());
        this.resultCode = resultCode;
        this.msg = resultCode.getMsg();
    }

    public <T> ApiException(IResultCode resultCode, T data){
        super(resultCode.getMsg());
        this.resultCode = resultCode;
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public ApiException(IResultCode resultCode, String msg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, msg);
        this.resultCode = resultCode;
        this.msg = msg;
    }

    public <T> ApiException(IResultCode resultCode, String msg, T data){
        super(msg);
        this.resultCode = resultCode;
        this.msg = msg;
        this.data = data;
    }

}
