package com.battcn.auth.service;

import com.battcn.auth.entity.AuthInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList("inner:client", "USER", "admin", "admin:query", "order:admin");
        return new AuthInfo(1L, "枫丶无痕", "wx—openId-test", "13002171912", 1L, "admin", BCRYPT + new BCryptPasswordEncoder().encode("123456"), true, true, true, true, authorities);
    }


}
