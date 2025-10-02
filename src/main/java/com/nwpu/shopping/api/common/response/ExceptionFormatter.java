package com.nwpu.shopping.api.common.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoheng
 * @date 2023/2/5
 */
@Component
@Slf4j
@Data
public class ExceptionFormatter {
    @Autowired
    private ObjectMapper objectMapper;

    public String formatException(Exception e, String message) throws JsonProcessingException {

        ExceptionInfo info = new ExceptionInfo();
        info.setExceptionName(e.getClass().toString());
        info.setMessage(message != null ? message : e.getMessage());
        List<String> stackTraceList = new ArrayList<>();
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement ele : stackTrace) {
            stackTraceList.add(MessageFormat.format("at {0}.{1}({2}:{3})",
                    ele.getClassName(), ele.getMethodName(), ele.getFileName(), ele.getLineNumber()));
        }
        info.setStackTrace(stackTraceList);
        return objectMapper.writeValueAsString(info);
    }

    @Data
    public static class ExceptionInfo {
        private String exceptionName;
        private String message;
        private List<String> stackTrace;
    }

}
