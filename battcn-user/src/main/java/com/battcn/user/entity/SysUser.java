package com.battcn.user.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author Levin
 * @since 2019-04-11
 */
@Data
@Table(name = "sys_user")
public class SysUser implements java.io.Serializable {

    @Id
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
