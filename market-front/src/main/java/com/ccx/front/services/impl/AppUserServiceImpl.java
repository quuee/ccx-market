package com.ccx.front.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccx.entity.biz.AppUserDO;
import com.ccx.front.dao.AppUserDao;
import com.ccx.front.services.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserDao, AppUserDO> implements AppUserService {

    @Override
    public AppUserDO getOneByPhone(String phone) {
        QueryWrapper<AppUserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        AppUserDO one = getOne(wrapper);
        return one;
    }

    @Override
    public AppUserDO getOneByNickName(String nickName) {
        QueryWrapper<AppUserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("nick_name",nickName);
        AppUserDO one = getOne(wrapper);
        return one;
    }
}
