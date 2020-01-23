package com.ccx.front.web.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserAllCategoryVO {

    /**
     * 默认的分类
     */
    private List<UserCategoryVO> defaultUserCategoryList;

    /**
     * 用户自己添加的分类
     */
    private List<UserCategoryVO> userCategoryList;

}
