package com.lzh.config;

import com.lzh.annotation.IgnoreResponseAdvice;
import com.lzh.domain.dto.CommonResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author linzhihao
 * @Date 2022/7/15 10:14 下午
 * @Description 实现统一响应
 */
// ResponseBodyAdvice 接口：对 ResponseBody 进行拦截
//value 限制生效范围
@ConditionalOnProperty(value = "response.packing",havingValue = "true" )
@RestControllerAdvice(value = "com.lzh")
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {


    /**
     * 判断是否需要对响应进行处理
     * false：不处理
     */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //获取方法对应的Controller类,去判断是否标注了对应的注解
        if(methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        //获取方法,去判断是否标注了对应的注解
        if(methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        return true;
    }

    /**
     * 响应之前修改响应体的方法
     * Object:响应体
     */
    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        //定义最终的返回对象
        CommonResponse<Object> commonResponse = new CommonResponse<>(0, "");

        if(null == o){
            return commonResponse;
        }else if(o instanceof CommonResponse){
            commonResponse = ((CommonResponse<Object>) o);
        }else {
            commonResponse.setData(o);
        }
        return commonResponse;
    }
}
