package com.ccx.market;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * mybatis plus # queryWrapper 的自定义 简化操作符
 */
public interface WrapperOperation<T> {

    QueryWrapper<T> getWrapper();

    default WrapperOperation<T> eq(String key, String value){
        if (StringUtils.isNotEmpty(value)){
            getWrapper().eq(key,value);
        }
        return this;
    }

    default WrapperOperation<T> like(String key, String value){
        if (StringUtils.isNotEmpty(value)){
            getWrapper().like(key,value);
        }
        return this;
    }

    default WrapperOperation<T> bet(String key, String v1, String v2){
        if (StringUtils.isNotEmpty(v1)&& StringUtils.isNotEmpty(v2)){
            getWrapper().between(key,v1,v2);
        }
        return this;
    }

    default WrapperOperation<T> or(String key){
        if (StringUtils.isNotEmpty(key))
            getWrapper().or(true);
        else
            getWrapper().or(false);
        return this;
    }

    default WrapperOperation<T> orderBy(String order){
        if (StringUtils.isNotEmpty(order))
            getWrapper().orderByDesc(order);
        return this;
    }

    default WrapperOperation<T> setField(String... fields){
        if (fields != null && fields.length > 0){
            getWrapper().select(fields);
        }
        return this;
    }

    default WrapperOperation<T> in(String key, Object... value){
        if (StringUtils.isNotEmpty(key) && value!= null && value.length > 0){
            getWrapper().in(key,value);
        }
        return this;
    }

}
