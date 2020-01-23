package com.ccx.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ccx.entity.base.Entity;
import lombok.*;

/**
 * 默认分类
 * 相当于字典表
 * 初始化后基本很少增加
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_default_category")
@ToString
@EqualsAndHashCode(callSuper = true)
public class SysDefaultCategoryDO extends Entity {

    private String categoryName;

    private Boolean enable;

}
