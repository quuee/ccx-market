package com.ccx.front.web.controller;

import com.ccx.front.services.UserCategoryService;
import com.ccx.front.web.vo.UserAllCategoryVO;
import com.ccx.front.web.vo.UserCategoryVO;
import com.ccx.market.util.R;
import com.ccx.security.conts.RedisKey;
import com.ccx.security.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("category-api")
@RequestMapping("category")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {

    private final RedisUtil redisUtil;

    private final UserCategoryService userCategoryService;

    @ApiOperation("获取分类")
    @RequestMapping(value = "queryCategoryList",method = RequestMethod.POST)
    public R queryCategoryList(@RequestParam(defaultValue = "1") Integer current,
                               @RequestParam(defaultValue = "10")Integer limit){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        UserAllCategoryVO userAllCategoryVO = new UserAllCategoryVO();

//        List<UserCategoryVO> userCategoryVOS = userCategoryService.getUserCategoryList(name);
//        userAllCategoryVO.setUserCategoryList(userCategoryVOS);
//
//        long size = redisUtil.lGetListSize(RedisKey.APP_DEFAULT_CATEGORY);
//        List<Object> objects =  redisUtil.lGet(RedisKey.APP_DEFAULT_CATEGORY, 0, size);


        return R.ok(userAllCategoryVO);
    }
}
