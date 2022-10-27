package com.lzh.config;

import com.lzh.domain.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author linzhihao
 * @Date 2022/7/16 11:13 上午
 * @Description 全局异常捕获处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    public CommonResponse<String> handlerCommerceException(
            HttpServletRequest request,
            Exception e
    ){
        CommonResponse<String> response = new CommonResponse<>(-1,"business error");
        response.setData(e.getMessage());
        log.error("commerce service has error:[{}]",e.getMessage(),e);
        return response;
    }
}
