package com.ccx.entity.biz;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ccx.entity.base.Entity;
import lombok.*;

import java.math.BigDecimal;

/**
 * 购物清单记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("biz_dish_account")
@ToString
@EqualsAndHashCode(callSuper = true)
public class BizDishAccountDO extends Entity {

    private String dishName;

    private Long userCategoryId;

    private String categoryName;

    private BigDecimal expectPrice;

    private BigDecimal realPrice;

}
