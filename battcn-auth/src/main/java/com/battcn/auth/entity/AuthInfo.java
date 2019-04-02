package com.battcn.auth.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthInfo extends User implements java.io.Serializable {

    private static final long serialVersionUID = 666236878598344789L;

    private Long userId;
    private String mobile;
    private String openId;
    private Long tenantId;
    private String nickName;

    public AuthInfo(Long userId, String nickName, String openId, String mobile, Long tenantId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.nickName = nickName;
        this.openId = openId;
        this.mobile = mobile;
        this.tenantId = tenantId;
    }
}
