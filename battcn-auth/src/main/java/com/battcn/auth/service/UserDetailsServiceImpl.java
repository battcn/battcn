package com.battcn.auth.service;

import com.battcn.auth.config.integration.IntegrationAuthentication;
import com.battcn.auth.config.integration.IntegrationAuthenticationContext;
import com.battcn.auth.config.integration.IntegrationAuthenticator;
import com.battcn.auth.entity.AuthInfo;
import com.battcn.auth.entity.Authorize;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 重写支持 Redis 的 UserDetails
 *
 * @author Levin
 * @since 2019-04-03
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private List<IntegrationAuthenticator> authenticators;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        IntegrationAuthentication integrationAuthentication = IntegrationAuthenticationContext.get();
        //判断是否是集成登录
        if (integrationAuthentication == null) {
            integrationAuthentication = new IntegrationAuthentication();
        }
        integrationAuthentication.setUsername(username);
        AuthInfo authInfo = this.authenticate(integrationAuthentication);

        if (authInfo == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        this.setAuthorize(authInfo);
        return authInfo;
    }

    /**
     * 设置授权信息
     *
     * @param user
     */
    public void setAuthorize(AuthInfo user) {
        //Authorize authorize = this.sysAuthorizeClient.getAuthorize(user.getId());
        Authorize authorize = new Authorize();
        authorize.setRoles(Lists.newArrayList("ROLE_ADMIN", "ROLE_USER"));
        authorize.setResources(Lists.newArrayList("/test"));
        user.setRoles(authorize.getRoles());
        user.setResources(authorize.getResources());
    }

    private AuthInfo authenticate(IntegrationAuthentication integrationAuthentication) {
        if (this.authenticators == null) {
            return null;
        }
        for (IntegrationAuthenticator authenticator : authenticators) {
            if (authenticator.support(integrationAuthentication)) {
                return authenticator.authenticate(integrationAuthentication);
            }
        }
        return null;
    }

}
