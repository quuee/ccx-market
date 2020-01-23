package com.ccx.front.web.controller;

import com.ccx.entity.base.SuperEntity;
import com.ccx.entity.biz.AppUserDO;
import com.ccx.security.conts.RedisKey;
import com.ccx.security.util.RedisUtil;
import com.ccx.front.services.AppUserService;
import com.ccx.market.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ccx.market.emun.ResultEnum.ExistResult;
import static com.ccx.market.emun.ResultEnum.ParamErrResult;


@Api("appuser-api")
@RequestMapping("appuser")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppUserRegisterController {

    private final AppUserService appUserService;

    private final RedisUtil redisUtil;


    @ApiOperation("获取手机验证码")
    @RequestMapping(value = "getPhoneCode",method = RequestMethod.GET)
    public R getPhoneCode(@RequestParam("phone") String phone){

        if(StringUtils.isEmpty(phone)){
            return R.error(ParamErrResult.getCode(),"手机号不能为空！");
        }
        String regex="^1[3456789]\\d{9}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();
        if(!isMatch){
            return R.error(ParamErrResult.getCode(),"手机号格式错误！");
        }

        redisUtil.set(RedisKey.APP_USER_PHONE+phone,"123456",30*60*1000);
        return R.ok(123456);
    }

    @ApiOperation(value = "用户手机注册",notes = "注册完成后，跳转到登录")
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public R register(@RequestBody @Validated(SuperEntity.Save.class) AppUserDO appUserDO){


        AppUserDO old = appUserService.getOneByPhone(appUserDO.getPhone());
        if(old!=null){
            return R.error(ExistResult.getCode(),"手机号码已注册");
        }

        AppUserDO old2 = appUserService.getOneByNickName(appUserDO.getNickName());
        if(old2!=null){
            return R.error(ExistResult.getCode(),"用户名已被使用");
        }

        String code = String.valueOf(redisUtil.get(RedisKey.APP_USER_PHONE + appUserDO.getPhone()));
        if(!code.equals(appUserDO.getCode())){
            return R.error(ParamErrResult.getCode(),"验证码错误");
        }

        appUserDO.setEnable(true);
        appUserDO.setCreateDate(new Date());
        appUserDO.setCreator(appUserDO.getNickName());
        appUserDO.setIsVip((short)0);
        appUserService.save(appUserDO);
        return R.ok(appUserDO);
    }


}
