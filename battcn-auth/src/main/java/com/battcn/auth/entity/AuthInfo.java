package com.battcn.auth.entity;

import lombok.Data;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Levin
 * @since 2019-04-03
 */
@Data
public class AuthInfo implements UserDetails, CredentialsContainer, java.io.Serializable {

    private static final long serialVersionUID = 666236878598344789L;

    private Long userId;
    private String phone;
    private String qqOpenId;
    private String wxOpenId;
    private Long tenantId;
    private String nickName;

    private String username;
    private String password;
    private Boolean enabled;
//    private Boolean accountNonLocked;
//    private Boolean credentialsNonExpired;
//    private Boolean accountNonExpired;
    private Collection<String> resources = new ArrayList<>();
    private Collection<String> roles = new ArrayList<>();
    private Collection<GrantedAuthority> grantedAuthorities;

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
