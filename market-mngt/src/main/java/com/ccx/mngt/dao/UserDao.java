package com.ccx.mngt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccx.entity.sys.UserDO;
import org.apache.ibatis.annotations.Param;

public interface UserDao extends BaseMapper<UserDO> {

    UserDO selectUserByName(@Param("username")String username);
}
