package com.ccx.mngt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccx.entity.sys.RoleDO;

import java.util.List;

public interface RoleDao extends BaseMapper<RoleDO> {

    List<RoleDO> selectRoleByUserId(Integer userId);
}
