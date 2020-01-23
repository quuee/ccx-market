package com.ccx.front.runner;

import com.ccx.front.services.SysDefaultCategoryService;
import com.ccx.front.web.vo.UserCategoryVO;
import com.ccx.security.conts.RedisKey;
import com.ccx.security.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryCommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private SysDefaultCategoryService defaultCategorySerice;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 将默认分类加载到redis
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        List<UserCategoryVO> userCategoryVOS = defaultCategorySerice.queryAll();
        redisUtil.lSet(RedisKey.APP_DEFAULT_CATEGORY,userCategoryVOS);
    }
}
