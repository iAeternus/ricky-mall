package com.ricky.utils;

import cn.hutool.core.collection.CollUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CollUtils
 * @desc hutool CollUtil防腐层
 */
public class CollUtils {

    private CollUtils() {
    }

    public static boolean isEmpty(Collection<?> collection) {
        return CollUtil.isEmpty(collection);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return CollUtil.isNotEmpty(collection);
    }

    /**
     * 转换列表
     *
     * @param list     列表
     * @param function 转换方法
     * @return 转换后的列表
     */
    public static <T, R> List<R> listConvert(List<T> list, Function<T, R> function) {
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(function).toList();
    }

}
