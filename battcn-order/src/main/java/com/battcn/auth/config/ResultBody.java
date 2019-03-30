package com.battcn.auth.config;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 返回体
 *
 * @author Levin
 * @since 2019-03-13
 */
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class ResultBody<T> implements java.io.Serializable {

    private static final long serialVersionUID = -871674258394065889L;

    private static final int DEFAULT_CODE = 200;
    private static final String DEFAULT_MESSAGE = "请求成功";

    /**
     * code 状态码，可根据具体业务而定，默认 200
     */
    @Getter
    @Setter
    private int code = DEFAULT_CODE;
    /**
     * 相应消息
     */
    @Getter
    @Setter
    private String message = DEFAULT_MESSAGE;
    /**
     * 返回数据
     */
    @Getter
    @Setter
    private T data;

    /**
     * 请求结果（true=代表成功 false=代表请求失败）
     */
    @Getter
    @Setter
    private boolean status = true;

    public ResultBody() {
        super();
    }

    public ResultBody(T data) {
        super();
        this.data = data;
    }

    public ResultBody(boolean status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public ResultBody(boolean status, String message, T data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
