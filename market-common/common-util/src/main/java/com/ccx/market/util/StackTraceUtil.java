package com.ccx.market.util;

import lombok.experimental.UtilityClass;

import java.util.stream.Stream;

/**
 * 记录栈信息工具类
 */
@UtilityClass
public class StackTraceUtil {

    public String getTrace(Throwable throwable){
        StringBuilder builder = new StringBuilder();
        Stream.of(throwable.getStackTrace()).forEach(
                msg-> builder.append("\t\r\n").append(msg)
        );
        return builder.toString();
    }
}
