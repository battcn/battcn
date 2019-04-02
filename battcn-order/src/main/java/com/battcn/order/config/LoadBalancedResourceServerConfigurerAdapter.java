package com.battcn.order.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 带负载均衡的请求
 *
 * @author Levin
 * @since 2019-03-30
 */
@AllArgsConstructor
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LoadBalancedResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {


    private final RestTemplate lbRestTemplate;
    //@Setter
    //private boolean details = true;
    //private final UserDetailsService userDetailsService;
    //private final UserDetailsService userDetailsService;

    // 如果配置了 RemoteTokenServices 说明不会自动访问 user-info-uri
    //private final RemoteTokenServices remoteTokenServices;
    private final ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        canGetUser(resources);
    }

    /**
     * 没有自己定义的属性信息
     *
     * @param resources resources
     */
    private void notGetUser(ResourceServerSecurityConfigurer resources) {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        UserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter);

        //remoteTokenServices.setRestTemplate(lbRestTemplate);
        //remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        //resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint).tokenServices(remoteTokenServices);
    }


    /**
     * 上下文中获取用户全部信息，两次调用userDetailsService，影响性能
     *
     * @param resources resources
     */
    private void canGetUser(ResourceServerSecurityConfigurer resources) {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        DefaultUserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter();
        accessTokenConverter.setUserTokenConverter(userTokenConverter);
        resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        http.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>
                .ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();
        registry.anyRequest().authenticated()
                .and().csrf().disable();

    }
}