package com.battcn.order.config;

import com.battcn.framework.security.result.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Levin
 * @since 2019-04-03
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerResolver extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleGlobalException(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return ErrorResult.builder()
                .message(e.getLocalizedMessage())
                .status(false)
                .code(500)
                .build();
    }


    @ExceptionHandler(ResourceAccessException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResult handleGlobalException1(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return ErrorResult.builder()
                .message(e.getLocalizedMessage())
                .status(false)
                .code(500)
                .build();
    }

    /**
     * 通用的接口映射异常处理方
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            ErrorResult errorResult = ErrorResult.builder().code(400).message(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()).status(false).build();
            return new ResponseEntity<>(errorResult, status);
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
            logger.error("参数转换失败，方法：" + exception.getParameter().getMethod().getName() + "，参数：" + exception.getName()
                    + ",信息：" + exception.getLocalizedMessage());

            return new ResponseEntity<>(ErrorResult.builder().code(status.value()).message("参数转换失败").build(), status);
        }
        return new ResponseEntity<>(ErrorResult.builder().code(status.value()).message("参数转换失败").build(), status);
    }
}
