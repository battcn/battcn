package com.battcn.framework.security.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 返回体
 *
 * @author Levin
 * @since 2019-03-13
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResult implements java.io.Serializable {


    private static final int DEFAULT_CODE = 400;
    private static final String DEFAULT_MESSAGE = "请求失败";

    /**
     * code 状态码，可根据具体业务而定，默认 200
     */
    private int code = DEFAULT_CODE;
    /**
     * 相应消息
     */
    private String message = DEFAULT_MESSAGE;

    /**
     * 请求结果（true=代表成功 false=代表请求失败）
     */
    private boolean status = false;


}
