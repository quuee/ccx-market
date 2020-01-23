package com.ccx.entity.biz;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ccx.entity.base.Entity;
import lombok.*;

/**
 * 用户自己的分类表
 * 删除时注意
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("biz_user_category")
@ToString
@EqualsAndHashCode(callSuper = true)
public class BizUserCategoryDO extends Entity {

    private String categoryName;

    private Long userId;

}
