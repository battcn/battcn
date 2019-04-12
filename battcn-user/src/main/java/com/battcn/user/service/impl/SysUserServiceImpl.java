package com.battcn.user.service.impl;

import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;
import com.battcn.user.entity.SysUser;
import com.battcn.user.mapper.SysUserMapper;
import com.battcn.user.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    private static final String PHONE_REGEX = "^[1][0-9]{10}$";

    private final SysUserMapper sysUserMapper;

    @Override
    public SysUser loadUserByUsername(String username) {
        SysUser sysUser;
        if (username == null) {
            return null;
        }
        if (username.matches(PHONE_REGEX)) {
            sysUser = sysUserMapper.selectOne(SysUser.builder().phone(username).build());
        } else {
            sysUser = sysUserMapper.selectOne(SysUser.builder().username(username).build());
        }
        return sysUser;
    }


}
