package com.ccx.mngt.web.param.category;

import com.ccx.entity.sys.SysDefaultCategoryDO;
import com.ccx.market.AbsWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultCategoryParam extends AbsWrapper<SysDefaultCategoryDO> {

    private Integer current=1;

    private Integer limit=20;

    private String categoryName;

    @Override
    public void setWrap() {
        like("category_name",categoryName);
    }
}
