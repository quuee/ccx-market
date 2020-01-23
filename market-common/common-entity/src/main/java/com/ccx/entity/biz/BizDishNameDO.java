package com.ccx.entity.biz;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ccx.entity.base.Entity;
import lombok.*;

import java.math.BigDecimal;

/**
 * 买菜的菜名
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("biz_dish_name")
@ToString
@EqualsAndHashCode(callSuper = true)
public class BizDishNameDO extends Entity {

    /**
     * 菜名
     * 不能相同
     */
    private String dishName;

    private Long userCategoryId;

    private String categoryName;

    private BigDecimal price;

    /**
     * 组别
     */
    private Long groupId;
}
