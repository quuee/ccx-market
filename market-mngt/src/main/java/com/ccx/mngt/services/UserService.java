package com.ccx.mngt.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccx.entity.sys.UserDO;

public interface UserService extends IService<UserDO> {

    UserDO queryUserByUsername(String username);
}
