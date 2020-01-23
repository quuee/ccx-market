package com.ccx.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ccx.entity.base.Entity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu")
@ToString
@EqualsAndHashCode(callSuper = true)
public class MenuDO extends Entity {

    private String menuName;

    private String permission;

    private String path;

    private Integer parentMenuId;

    private Integer menuType;
}
