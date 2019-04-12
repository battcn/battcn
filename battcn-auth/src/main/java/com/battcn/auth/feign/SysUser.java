package com.battcn.auth.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Levin
 * @since 2019-04-11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements java.io.Serializable {

    private Long userId;
    private String username;
    private String password;
    private String nickName;
    private String salt;
    private String phone;
    private String avatar;
    private Long tenantId;
    private Long deptId;
    private Boolean enabled;
    private Boolean status;
    private String wxOpenId;
    private String qqOpenId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
