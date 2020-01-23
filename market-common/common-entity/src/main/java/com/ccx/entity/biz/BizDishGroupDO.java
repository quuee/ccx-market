package com.ccx.entity.biz;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ccx.entity.base.Entity;
import lombok.*;

/**
 * 菜名的组
 * 删除时注意
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("biz_dish_group")
@ToString
@EqualsAndHashCode(callSuper = true)
public class BizDishGroupDO extends Entity {

    private String groupName;

    private Integer sort;


}
