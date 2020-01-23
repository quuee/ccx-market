package com.ccx.front.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccx.entity.biz.AppUserDO;
import com.ccx.front.commons.dto.AppUserDetailsDTO;
import com.ccx.front.services.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("AppPhoneDetailService")
public class AppPhoneDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AppPhoneDetailService.class);

    @Autowired
    private AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        QueryWrapper<AppUserDO> appUserDOQueryWrapper = new QueryWrapper<>();
        appUserDOQueryWrapper.eq("phone",phone).eq("enable",true);
        AppUserDO one = appUserService.getOne(appUserDOQueryWrapper);
        if(one==null){
            logger.error("找不到用户：{}",phone);
            throw new UsernameNotFoundException("用户" + phone + "不存在!");
        }

        AppUserDetailsDTO appUserDetailsDTO = new AppUserDetailsDTO(one.getNickName(), "", new HashSet<>(),
                one.getId(), one.getHeadImage(), one.getPhone(),
                one.getEmail(), one.getOpenId(), one.getIsVip());
        return appUserDetailsDTO;
    }
}
