package com.ccx.front.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccx.entity.biz.BizUserCategoryDO;
import com.ccx.front.dao.BizUserCategoryDao;
import com.ccx.front.services.UserCategoryService;
import org.springframework.stereotype.Service;

@Service
public class UserCategoryServiceImpl extends ServiceImpl<BizUserCategoryDao,BizUserCategoryDO> implements UserCategoryService {
}
