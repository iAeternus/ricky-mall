package com.ricky.utils;

import com.ricky.enums.BaseEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className EnumUtils
 * @desc 枚举常用方法
 */
public class EnumUtils {

    private EnumUtils() {
    }

    /**
     * 根据给定的代码从枚举中查找对应的枚举实例
     *
     * @param <T>       枚举类型
     * @param enumClass 枚举类型的Class对象
     * @param code      要查找的枚举实例的代码
     * @return 对应的枚举实例，如果未找到则抛出异常
     */
    public static <T extends Enum<T>> T fromCode(Class<T> enumClass, Object code) {
        for (T t : enumClass.getEnumConstants()) {
            if (t instanceof BaseEnum && ((BaseEnum) t).getKey().equals(code)) {
                return t;
            }
        }
        throw new IllegalArgumentException("No matching enum found for code: " + code);
    }

    /**
     * 根据给定的描述从枚举中查找对应的枚举实例
     *
     * @param <T>         枚举类型
     * @param enumClass   枚举类型的Class对象
     * @param description 要查找的枚举实例的描述
     * @return 对应的枚举实例，如果未找到则抛出异常
     */
    public static <T extends Enum<T>> T fromDescription(Class<T> enumClass, String description) {
        for (T t : enumClass.getEnumConstants()) {
            if (t instanceof BaseEnum && ((BaseEnum) t).getVal().equals(description)) {
                return t;
            }
        }
        throw new IllegalArgumentException("No matching weight unit found for description: " + description);
    }

}
