package com.ccx.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ccx.entity.base.Entity;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role")
@ToString
@EqualsAndHashCode(callSuper = true)
public class RoleDO extends Entity {


    private String roleName;

    private Boolean enable;

    @TableField(exist = false)
    private Set<MenuDO> menuSet;
}
