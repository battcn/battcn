package com.battcn.auth.config.integration.sms;

import com.battcn.auth.config.integration.AbstractPreparedIntegrationAuthenticator;
import com.battcn.auth.config.integration.IntegrationAuthentication;
import com.battcn.auth.config.integration.sms.event.SmsAuthenticateBeforeEvent;
import com.battcn.auth.config.integration.sms.event.SmsAuthenticateSuccessEvent;
import com.battcn.auth.entity.AuthInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.core.Ordered;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 短信验证码集成认证
 *
 * @author LIQIU
 * @date 2018-3-31
 **/
@Slf4j
@Component
public class SmsIntegrationAuthenticator extends AbstractPreparedIntegrationAuthenticator implements ApplicationEventPublisherAware {


    @Autowired
    private PasswordEncoder passwordEncoder;
    private ApplicationEventPublisher applicationEventPublisher;

    private final static String SMS_AUTH_TYPE = "sms";

    @Override
    public AuthInfo authenticate(IntegrationAuthentication integrationAuthentication) {

        //获取密码，实际值是验证码
        String password = integrationAuthentication.getAuthParameter("password");
        //获取用户名，实际值是手机号
        String username = integrationAuthentication.getUsername();
        //发布事件，可以监听事件进行自动注册用户
        this.applicationEventPublisher.publishEvent(new SmsAuthenticateBeforeEvent(integrationAuthentication));
        //通过手机号码查询用户
        //AuthInfo sysUserAuthentication = this.sysUserClient.findUserByPhoneNumber(username);
        AuthInfo authInfo = new AuthInfo();
        Collection<GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList("inner:client", "USER", "admin", "admin:query", "order:admin");
        authInfo.setUserId(1L);
        authInfo.setUsername(username);
        //authInfo.setPassword(BCRYPT + "$2a$10$RpFJjxYiXdEsAGnWp/8fsOetMuOON96Ntk/Ym2M/RKRyU0GZseaDC");
        authInfo.setNickName("枫丶无痕");
        authInfo.setPhone("13002171912");
        authInfo.setGrantedAuthorities(authorities);
        authInfo.setQqOpenId("wx—openId-test");
        authInfo.setWxOpenId("qq—openId-test");
        authInfo.setTenantId(1L);
        authInfo.setEnabled(true);
        if (authInfo != null) {
            //将密码设置为验证码
            authInfo.setPassword(passwordEncoder.encode(password));
            //发布事件，可以监听事件进行消息通知
            this.applicationEventPublisher.publishEvent(new SmsAuthenticateSuccessEvent(integrationAuthentication));
        }
        return authInfo;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {
        String smsToken = integrationAuthentication.getAuthParameter("sms_token");
        String smsCode = integrationAuthentication.getAuthParameter("password");
        String username = integrationAuthentication.getAuthParameter("username");
        log.info("[token] - [{}] - [code] - [{}] - [username] - [{}]", smsToken, smsCode, username);
//        Result<Boolean> result = verificationCodeClient.validate(smsToken, smsCode, username);
//        if (!result.getData()) {
//            throw new OAuth2Exception("验证码错误或已过期");
//        }
    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return SMS_AUTH_TYPE.equals(integrationAuthentication.getAuthType());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public int getOrder() {
        return 101;
    }
}
