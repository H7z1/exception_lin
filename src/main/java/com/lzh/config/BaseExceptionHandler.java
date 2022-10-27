package com.lzh.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.lzh.domain.dto.Result;
import com.lzh.domain.enums.ResultCode;
import com.lzh.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * WEB应用全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {


    /**
     * 服务间通信的异常处理
     *
     * @param e /
     * @return /
     */
    @ExceptionHandler({ApiException.class})
    public Result<String> handleApiException(ApiException e) {
        return Result.fail(e.getResultCode(), e.getMsg());
    }

    /**
     * 参数异常
     */
    @ExceptionHandler(value = {IllegalStateException.class})
    public Result<Void> handlerIllegalStateException(IllegalStateException e, HttpServletRequest request) {
        log.warn("请求参数异常 {} ", e.getLocalizedMessage());
        return this.getResponseEntity(request, e, ResultCode.GLOBAL_PARAM_ERROR.getCode(), null, null);
    }

    /**
     * 参数类型不匹配
     */
    @ExceptionHandler({TypeMismatchException.class})
    public Result<Void> requestTypeMismatch(TypeMismatchException e, HttpServletRequest request) {
        log.warn("参数类型不匹配 参数:{} 类型应该为{}", e.getPropertyName(), e.getRequiredType());
        return this.getResponseEntity(request, e, ResultCode.GLOBAL_PARAM_ERROR.getCode(), null, null);
    }

    /**
     * 参数验证不通过
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<Void> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e,HttpServletRequest request){
        return getVoidResultByValid(e,e.getBindingResult(), request);
    }

    /**
     * 参数异常
     */
    @ExceptionHandler(value = {BindException.class})
    public Result<Void> handlerBindException(BindException e, HttpServletRequest request) {
        return getVoidResultByValid(e, e.getBindingResult(), request);
    }


    private Result<Void> getVoidResultByValid(Exception e,BindingResult bindingResult, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        bindingResult.getAllErrors().stream().filter(allError -> allError instanceof FieldError).map(allError -> (FieldError) allError)
                .forEach(fieldError -> {
                    String field = fieldError.getField();
                    String message = fieldError.getDefaultMessage();
                    sb.append(String.format("参数：%s 值：%s 错误信息:%s; ", field, fieldError.getRejectedValue(), message));
                });
        log.warn("请求参数异常 {}", sb);
        return this.getResponseEntity(request, e, ResultCode.GLOBAL_PARAM_ERROR.getCode(), sb.toString(), null);
    }

    protected <T> Result<T> getResponseEntity(HttpServletRequest request, Throwable e, int code, String msg) {
        return this.getResponseEntity(request, e, code, msg, null);
    }

    protected <T> Result<T> getResponseEntity(HttpServletRequest request, Throwable e, Integer code, String msg, T data) {
        Arrays.stream(StrUtil.splitToArray(this.getHttpRequestInfo(request), StrUtil.CRLF)).forEach(log::error);
        log.error(e.getMessage(), e);
        if (StrUtil.isBlank(msg)) {
            msg = ResultCode.getResultCode(code).getMsg();
        }
        Result<T> voidResponseVO = Result.data(code, data, msg);
        return voidResponseVO;
    }

    protected String getHttpRequestInfo(HttpServletRequest request) {

        StringBuilder sb = StrUtil.builder();

        //请求URL
        String url = request.getRequestURL().toString();

        sb.append("Request URL: ").append(url).append(StrUtil.CRLF);

        //请求方式
        String method = request.getMethod();
        sb.append("Request Method: ").append(method).append(StrUtil.CRLF);

        //header请求参数
        Map<String, List<String>> headerMaps = this.getHeaderParameter(request);
        sb.append("Request Headers: ").append(StrUtil.CRLF);
        for (Map.Entry<String, List<String>> entry : headerMaps.entrySet()) {
            sb.append("    ").append(entry.getKey())
                    .append(": ").append(CollUtil.join(entry.getValue(), ","))
                    .append(StrUtil.CRLF);
        }

        //请求参数
        Map<String, List<String>> parameterMaps = this.getParameter(request);
        sb.append("Request Parameter: ").append(StrUtil.CRLF);
        for (Map.Entry<String, List<String>> entry : parameterMaps.entrySet()) {
            sb.append("    ").append(entry.getKey())
                    .append(": ").append(CollUtil.join(entry.getValue(), ","))
                    .append(StrUtil.CRLF);
        }

        try (ServletInputStream inputStream = request.getInputStream()) {
            //如果使用了@RequestBody，那么这里的流其实已经关闭了，在读取就会报错了！！！！
            if (!inputStream.isFinished()) {
                sb.append("Request Body: ").append(StrUtil.CRLF);
                sb.append("    ").append(StreamUtils.copyToString(inputStream, Charsets.UTF_8)).append(StrUtil.CRLF);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return sb.toString();
    }

    /**
     * 获取请求参数
     */
    private Map<String, List<String>> getParameter(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String, List<String>> parameterMaps = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] parameterValues = request.getParameterValues(paramName);
            if (parameterValues != null && parameterValues.length > 0) {
                parameterMaps.put(paramName, Arrays.asList(parameterValues));
            }
        }
        return parameterMaps;
    }

    /**
     * 获取Header
     */
    private Map<String, List<String>> getHeaderParameter(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, List<String>> parameterMaps = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String paramName = headerNames.nextElement();
            List<String> paramValue = Lists.newArrayList();
            Enumeration<String> headers = request.getHeaders(paramName);
            while (headers.hasMoreElements()) {
                paramValue.add(headers.nextElement());
            }
            parameterMaps.put(paramName, paramValue);
        }
        return parameterMaps;
    }
}
