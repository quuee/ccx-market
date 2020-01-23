package com.ccx.mngt.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ccx.entity.sys.SysDefaultCategoryDO;
import com.ccx.market.util.R;
import com.ccx.mngt.services.SysDefaultCategoryService;
import com.ccx.mngt.web.param.category.DefaultCategoryParam;
import com.ccx.mngt.web.param.category.SaveDefaultCategoryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@RestController
@Api("默认分类-api")
@RequestMapping("default/category")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultCategoryController {

    private final SysDefaultCategoryService defaultCategoryService;


    @ApiOperation("查询默认分类")
    @RequestMapping(value = "queryPage",method = RequestMethod.POST)
    public R query(@RequestBody DefaultCategoryParam param){
//        param.setWrap();
        IPage<SysDefaultCategoryDO> page = defaultCategoryService.page(param.getIPage(), param.getWrapper());
        return R.ok(page);
    }

    @ApiOperation("新增默认分类")
    @PostMapping("save")
    public R save(@RequestBody @Validated SaveDefaultCategoryParam saveParam){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysDefaultCategoryDO sysDefaultCategoryDO = new SysDefaultCategoryDO();
        BeanUtils.copyProperties(saveParam,sysDefaultCategoryDO);
        sysDefaultCategoryDO.setEnable(true);//设置可用
        sysDefaultCategoryDO.setCreateDate(new Date());
        sysDefaultCategoryDO.setCreator(authentication.getName());
        defaultCategoryService.save(sysDefaultCategoryDO);
        return R.ok(sysDefaultCategoryDO);
    }

    @ApiOperation("启用/禁用")
    @PostMapping("enable")
    public R enable(@NotNull(message = "id不能为空") Long defaultCategoryId){
        SysDefaultCategoryDO byId = defaultCategoryService.getById(defaultCategoryId);
        SysDefaultCategoryDO aDo = new SysDefaultCategoryDO();
        aDo.setId(defaultCategoryId);
        aDo.setEnable(!byId.getEnable());
        aDo.setUpdateDate(new Date());
        aDo.setUpdateMan(SecurityContextHolder.getContext().getAuthentication().getName());
        defaultCategoryService.updateById(aDo);
        return R.ok(aDo);
    }

}
