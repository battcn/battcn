package com.battcn.framework.security.client;

import com.battcn.framework.security.result.ErrorResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Vivian
 * @date 2018/7/8
 * 客户端异常处理
 * 1. 可以根据 AuthenticationException 不同细化异常处理
 */
@Slf4j
@AllArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.error("[客户端异常] - [{}]", authException);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ErrorResult result = new ErrorResult();
        result.setStatus(false);
        if (authException != null) {
            if (authException.getCause() instanceof InvalidTokenException) {
                InvalidTokenException invalidTokenException = (InvalidTokenException) authException.getCause();
                String oAuth2ErrorCode = invalidTokenException.getOAuth2ErrorCode();
                int httpErrorCode = invalidTokenException.getHttpErrorCode();
                result.setCode(httpErrorCode);
                result.setMessage(oAuth2ErrorCode);
            } else if (authException instanceof InsufficientAuthenticationException) {
                InsufficientAuthenticationException insufficientAuthenticationException = (InsufficientAuthenticationException) authException;
                String oAuth2ErrorCode = insufficientAuthenticationException.getLocalizedMessage();
                int httpErrorCode = HttpStatus.UNAUTHORIZED.value();
                result.setCode(httpErrorCode);
                result.setMessage(oAuth2ErrorCode);
            }
        } else {
            result.setCode(HttpStatus.UNAUTHORIZED.value());
            result.setMessage(HttpStatus.UNAUTHORIZED.name());
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(result));
    }
}
