package com.ccx.mngt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccx.entity.sys.MenuDO;

import java.util.List;

public interface MenuDao extends BaseMapper<MenuDO> {

    List<MenuDO> selectMenuByRoleId(Integer roleId);
}
