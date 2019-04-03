package com.battcn.auth.config.integration.primary;

import com.battcn.auth.config.integration.AbstractPreparedIntegrationAuthenticator;
import com.battcn.auth.config.integration.IntegrationAuthentication;
import com.battcn.auth.entity.AuthInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import static com.battcn.auth.config.SecurityConstants.BCRYPT;

/**
 * 默认登录处理
 *
 * @author Levin
 * @since 2019-04-03
 **/
@Component
@Primary
@Slf4j
public class UsernamePasswordAuthenticator extends AbstractPreparedIntegrationAuthenticator {

    private static final String FIND_USERNAME = "SELECT * FROM sys_user WHERE username = ?";
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int getOrder() {
        return 100;
    }

    public class UserDetailRowMapper implements RowMapper<AuthInfo> {
        @Override
        public AuthInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            long userId = rs.getLong("user_id");
            long tenantId = rs.getLong("tenant_id");
            String qqOpenId = rs.getString("qq_open_id");
            String wxOpenId = rs.getString("wx_open_id");
            String phone = rs.getString("phone");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String nickName = rs.getString("nick_name");
            boolean enabled = rs.getBoolean("enabled");
            AuthInfo authInfo = new AuthInfo();
            Collection<GrantedAuthority> authorities
                    = AuthorityUtils.createAuthorityList("inner:client", "USER", "admin", "admin:query", "order:admin");
            authInfo.setUserId(userId);
            authInfo.setUsername(username);
            authInfo.setPassword(BCRYPT + password);
            authInfo.setNickName(nickName);
            authInfo.setPhone(phone);
            authInfo.setGrantedAuthorities(authorities);
            authInfo.setQqOpenId(qqOpenId);
            authInfo.setWxOpenId(wxOpenId);
            authInfo.setTenantId(tenantId);
            authInfo.setEnabled(enabled);
            return authInfo;
        }
    }


    @Override
    public AuthInfo authenticate(IntegrationAuthentication integrationAuthentication) {
        String username = integrationAuthentication.getUsername();
        return jdbcTemplate.queryForObject(FIND_USERNAME, new Object[]{username}, new UserDetailRowMapper());
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {
        log.info("[用户密码登陆] - [{}]", integrationAuthentication);
    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        String authType = integrationAuthentication.getAuthType();
        return StringUtils.isEmpty(authType);
    }
}
