package com.nwpu.shopping.api.common.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author: feoyang
 * @date: 2024/1/16 15:43
 * @description: TODO
 */
@RestControllerAdvice
@Slf4j
@Data
public class CommonResultWrapper implements ResponseBodyAdvice<Object> {

        @Autowired
        private ObjectMapper objectMapper;

        /**
         * 设置这个“包装器”是否启用。return true表示启用。
         */
        @Override
        public boolean supports(MethodParameter returnType, Class converterType) {
                return true;
        }

        /**
         * 自定义包装方法，根据Controller层返回的内容不同，有不同的包装方法。
         * 不过最终要给前端返回的是标准返回模板————CommonResult
         */
        @Override
        public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
                if (body == null) {
                        return CommonResult.success();
                }
                try {
                        if (body instanceof String) {
                                return objectMapper.writeValueAsString(CommonResult.success(body));
                        }  
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

            if (body instanceof CommonResult) {
                        return body;
                }
                return CommonResult.success(body);
        }
}
