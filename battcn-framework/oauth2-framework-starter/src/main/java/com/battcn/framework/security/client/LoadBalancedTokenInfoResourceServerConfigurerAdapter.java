package com.battcn.framework.security.client;

import com.battcn.framework.security.client.annotation.IgnoreAuthorize;
import com.battcn.framework.security.client.properties.SecurityIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.Map;

/**
 * 带负载均衡的请求
 *
 * @author Levin
 * @since 2019-03-30
 */
@Slf4j
@AllArgsConstructor
@Import({ResourceAuthExceptionEntryPoint.class, LoadBalancedRestTemplateAutoConfigurer.class})
@EnableConfigurationProperties(SecurityIgnoreProperties.class)
public class LoadBalancedTokenInfoResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

    private final RestTemplate lbRestTemplate;
    private final RemoteTokenServices remoteTokenServices;
    private final ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;
    private final SecurityIgnoreProperties securityIgnoreProperties;
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        UserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        remoteTokenServices.setRestTemplate(lbRestTemplate);
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint).tokenServices(remoteTokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        http.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethodEntry : handlerMethods.entrySet()) {
            RequestMappingInfo key = handlerMethodEntry.getKey();
            HandlerMethod value = handlerMethodEntry.getValue();
            IgnoreAuthorize annotation = value.getMethodAnnotation(IgnoreAuthorize.class);
            log.info("[key] - [{}] - [value] - [{}]", key, value);
            if (annotation != null) {
                key.getPatternsCondition().getPatterns().forEach(url -> registry.antMatchers(url).permitAll());
            }
        }
        List<String> ignoreUrls = securityIgnoreProperties.getIgnoreUrls();
        if (!CollectionUtils.isEmpty(ignoreUrls)) {
            String[] arr = ignoreUrls.toArray(new String[0]);
            registry.antMatchers(arr).permitAll();
        }
        registry.anyRequest().authenticated()
                .and().csrf().disable();

    }
}