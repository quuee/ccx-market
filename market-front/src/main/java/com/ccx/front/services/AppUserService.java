package com.ccx.front.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccx.entity.biz.AppUserDO;

public interface AppUserService extends IService<AppUserDO> {

    AppUserDO getOneByPhone(String phone);

    AppUserDO getOneByNickName(String nickName);
}
