package com.ccx.mngt.web.param.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SaveDefaultCategoryParam {

    @NotBlank(message = "默认分类名称不能为空")
    private String categoryName;

}
