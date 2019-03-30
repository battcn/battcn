//package com.battcn.auth.service;
//
//import com.battcn.auth.entity.AuthInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//
//@Slf4j
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    static final String BCRYPT = "{bcrypt}";
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Collection<? extends GrantedAuthority> authorities
//                = AuthorityUtils.createAuthorityList("admin","admin:query", "order:admin");
//        return new AuthInfo(1L, 1L, "wx—openId", 1L, "admin", new BCryptPasswordEncoder().encode("123456"), true, true, true, true, authorities);
//    }
//
//
//}
