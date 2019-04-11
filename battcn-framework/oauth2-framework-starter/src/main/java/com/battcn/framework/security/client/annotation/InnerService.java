package com.battcn.framework.security.client.annotation;


import java.lang.annotation.*;

/**
 * 加上该注解，接口地址将允许内部服务之间调用（外部服务依旧会拦截）
 *
 * @author Levin
 * @since 2019-04-11
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface InnerService {


}