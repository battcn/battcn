package com.battcn.auth.service;

import com.battcn.auth.entity.AuthInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import static com.battcn.auth.config.SecurityConstants.BCRYPT;

/**
 * 重写支持 Redis 的 UserDetails
 *
 * @author Levin
 * @since 2019-04-03
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String FIND_USERNAME = "SELECT * FROM sys_user WHERE username = ?";
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return jdbcTemplate.queryForObject(FIND_USERNAME, new Object[]{username}, new UserDetailRowMapper());
    }

    public class UserDetailRowMapper implements RowMapper<AuthInfo> {
        @Override
        public AuthInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            long userId = rs.getLong("user_id");
            long tenantId = rs.getLong("tenant_id");
            String qqOpenId = rs.getString("qq_openid");
            String wxOpenId = rs.getString("wx_openid");
            String phone = rs.getString("phone");
            String username = rs.getString("username");
            String password = rs.getString("password");
            boolean enabled = rs.getBoolean("enabled");
            // BCRYPT + new BCryptPasswordEncoder().encode("123456")
            Collection<? extends GrantedAuthority> authorities
                    = AuthorityUtils.createAuthorityList("inner:client", "USER", "admin", "admin:query", "order:admin");
            return new AuthInfo(userId, "枫丶无痕", wxOpenId,
                    phone, tenantId, username, BCRYPT + password,
                    enabled, true, true, true, authorities);
        }
    }


}
