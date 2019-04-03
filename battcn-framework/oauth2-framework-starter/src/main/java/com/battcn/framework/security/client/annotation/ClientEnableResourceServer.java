package com.battcn.framework.security.client.annotation;

import com.battcn.framework.security.client.SecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * 资源服务注解
 *
 * @author Levin
 * @since 2019-04-03
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({SecurityBeanDefinitionRegistrar.class})
public @interface ClientEnableResourceServer {

    /**
     * false 选用     UserInfoTokenServices
     * true  选用     RemoteTokenServices
     *
     * @return true | false
     */
    boolean preferTokenInfo() default false;

}
