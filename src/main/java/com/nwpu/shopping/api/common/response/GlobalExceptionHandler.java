package com.nwpu.shopping.api.common.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nwpu.shopping.domin.common.exception.BusinessException;
import com.nwpu.shopping.domin.common.exception.ExceptionType;
import com.nwpu.shopping.domin.common.exception.ForbiddenException;
import com.nwpu.shopping.domin.common.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器
 * 提示：它无法捕获过滤器抛出的异常
 *
 * @author 廖菁璞
 */
@RestControllerAdvice
@Slf4j
@Data
public class GlobalExceptionHandler {

    @Autowired
    private ExceptionFormatter formatter;


    /**
     * 把异常信息格式化成自己喜欢的格式，这个方法用于格式化BindException
     */
    public static String formatBindException(BindException e) {
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(
                error -> {
                    //提示：error.getField()得到的是校验失败的字段名字，error.getDefaultMessage()得到的是校验失败的原因
                    sb.append(error.getDefaultMessage()).append("  ");
                }
        );
        return sb.toString();
    }

    /**
     * 把异常信息格式化成自己喜欢的格式，这个方法用于格式化BindException
     */
    public static String formatConstraintViolationException(ConstraintViolationException e) {
        StringBuilder sb = new StringBuilder();
        e.getConstraintViolations().forEach(
                violation -> {
                    //提示：((PathImpl) violation.getPropertyPath()).getLeafNode().getName()得到的是校验失败的字段名字，violation
                    // .getMessage()得到的是校验失败的原因
                    sb.append(((PathImpl) violation.getPropertyPath()).getLeafNode().getName()).append("：").append(violation.getMessage()).append("  ");
                });
        return sb.toString();
    }

    /**
     * 参数校验失败【针对于请求中Body部分的校验失败，方法形参要加@RequestBody来指明处理的Body部分，
     * 别和下面Path部分和Query部分搞混，这俩抛出的异常是不一样的】
     * 校验Body的Controller方法样例：search(@RequestBody @Valid UserParam userParam)
     * 提示：想校验Body参数，就算不在Controller类上加@Validated注解也能生效（当然加了也不碍事，为了省事，干脆把所有Controller都加上@Validated）
     */
    @ExceptionHandler(BindException.class)
    public CommonResult handleBindException(BindException e) throws JsonProcessingException {
        String message = formatBindException(e);
        log.warn(formatter.formatException(e, message));
        return CommonResult.failure(ExceptionType.PARAM_VALIDATE_FAILED.getCode(), message);
    }

    /**
     * 参数校验失败【针对于请求中Path部分和Query部分的校验失败，方法形参不加@RequestBody
     * （如果是Path参数则要加@PathVariable，Query参数则不用额外加注解），别和上面Body部分搞混，这俩抛出的异常是不一样的】
     * 校验Query的Controller方法样例：search(@Valid @NotBlank(message= "姓名不能为空") String name)，并且务必在Controller类上加@Validated
     * 校验Path的Controller方法样例：search(@PathVariable @Valid @Min(value = 1,message = "用户ID最小是1")  Long userId)
     * ，并且务必在Controller类上加@Validated
     * 特别注意：如果你想校验Query参数或者Path参数，必须要在Controller类上加@Validated注解，否则校验不生效
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult handleBindException(ConstraintViolationException e) throws JsonProcessingException {
        String message = formatConstraintViolationException(e);
        log.warn(formatter.formatException(e, message));
        return CommonResult.failure(ExceptionType.PARAM_VALIDATE_FAILED.getCode(), message);
    }

    /**
     * 请求方式不支持
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult handleMethodNotAllowed(Exception e) throws JsonProcessingException {
        log.warn(formatter.formatException(e, null));
        return CommonResult.failure("请求方式不支持");
    }

    /**
     * 请求格式不对
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ServletRequestBindingException.class, MissingRequestHeaderException.class,
            MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public CommonResult handleBadRequest(Exception e) throws JsonProcessingException {
        log.warn(formatter.formatException(e, null));
        return CommonResult.failure("请求格式不对");
    }

    /**
     * 请求的操作非法
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public CommonResult handleForbiddenException(Exception e) throws JsonProcessingException {
        log.warn(formatter.formatException(e, null));
        return CommonResult.failure(e.getMessage());
    }

    /**
     * 请求URL有误，无法解析这个URL该对应Controller中哪个方法
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public CommonResult handleNotFoundException(NoHandlerFoundException e) throws JsonProcessingException {
        log.warn(formatter.formatException(e, null));
        return CommonResult.failure("请求URL不存在");
    }

    /**
     * 请求资源不存在
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotFoundException.class)
    public CommonResult handleNotFoundException(Exception e) throws JsonProcessingException {
        log.warn(formatter.formatException(e, null));
        return CommonResult.failure(e.getMessage());
    }

    /**
     * 业务异常，可细分为多种情况，可见ExceptionType
     */
    @ExceptionHandler(BusinessException.class)
    public CommonResult handleBusinessException(BusinessException e) throws JsonProcessingException {
        log.warn(formatter.formatException(e, null));
        return CommonResult.failure(e.getCode(), e.getMessage());
    }


    /**
     * 如果前面的处理器都没拦截住，最后兜底
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) throws JsonProcessingException {
        log.error(formatter.formatException(e, null));
        //monitorEventPublisher.publishExceptionMessage(e);
        return CommonResult.failure("服务器内部错误，请稍后重试");
    }


}
