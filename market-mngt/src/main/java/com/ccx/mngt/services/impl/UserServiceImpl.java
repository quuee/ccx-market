package com.ccx.mngt.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccx.entity.sys.UserDO;
import com.ccx.mngt.dao.UserDao;
import com.ccx.mngt.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserDO> implements UserService {

    @Override
    public UserDO queryUserByUsername(String username) {
        return baseMapper.selectUserByName(username);
    }
}
