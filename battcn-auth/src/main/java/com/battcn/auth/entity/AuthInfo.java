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
    private Long deptId;
    private String openId;
    private Long tenantId;

    public AuthInfo(Long userId, Long deptId, String openId, Long tenantId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.deptId = deptId;
        this.openId = openId;
        this.tenantId = tenantId;
    }
}
