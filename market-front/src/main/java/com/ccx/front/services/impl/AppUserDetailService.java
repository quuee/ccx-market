package com.ccx.front.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccx.entity.biz.AppUserDO;
import com.ccx.front.commons.dto.AppUserDetailsDTO;
import com.ccx.front.services.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("appUserDetailService")
public class AppUserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AppUserDetailService.class);

    @Autowired
    private AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {
        QueryWrapper<AppUserDO> appUserDOQueryWrapper = new QueryWrapper<>();
        appUserDOQueryWrapper.eq("nick_name",nickName).eq("enable",true);
        AppUserDO one = appUserService.getOne(appUserDOQueryWrapper);

        if(one==null){
            logger.error("找不到用户：{}",nickName);
            throw new UsernameNotFoundException("用户" + nickName + "不存在!");
        }

        AppUserDetailsDTO appUserDetailsDTO = new AppUserDetailsDTO(one.getNickName(), null, null,
                one.getId(), one.getHeadImage(), one.getPhone(),
                one.getEmail(), one.getOpenId(), one.getIsVip());
        return appUserDetailsDTO;
    }

}
