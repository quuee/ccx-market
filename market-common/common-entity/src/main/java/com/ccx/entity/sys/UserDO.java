package com.ccx.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ccx.entity.base.Entity;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserDO extends Entity {

    private String username;

    private String password;

    private String phone;

    private Boolean enable;//是否激活 1激活 0 未激活

    private Boolean superUser;

//    @Valid
    @TableField(exist = false)
    private Set<RoleDO> roleSet;
}
