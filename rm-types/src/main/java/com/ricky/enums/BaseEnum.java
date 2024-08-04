package com.ricky.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className BaseEnum
 * @desc 基础枚举接口
 * @param <T> 枚举值code类型
 */
public interface BaseEnum<T> {

    /**
     * 初始化枚举
     *
     * @param code 枚举值
     * @param msg  枚举信息
     */
    default void initEnum(final T code, final String msg) {
        EnumContainer.putEnum(this, code, msg);
    }

    /**
     * 获取枚举的值
     *
     * @return 枚举值
     */
    default T getCode() {
        return EnumContainer.getEnum(this).getCode();
    }

    /**
     * 获取枚举的描述
     *
     * @return 描述
     */
    default String getMsg() {
        return EnumContainer.getEnum(this).getMsg();
    }

    @SuppressWarnings("unchecked")
    static <T, R extends BaseEnum<T>> R of(Class<? extends BaseEnum<T>> clazz, final T code) {
        return Stream.of(clazz.getEnumConstants())
                .filter(enumBean -> enumBean.getCode().equals(code))
                .map(baseEnum -> (R) baseEnum)
                .findAny()
                .orElse(null);
    }

    /**
     * 枚举容器，存储枚举值
     */
    class EnumContainer {
        private static final Map<BaseEnum<?>, EnumBean<?>> ENUM_MAP = new ConcurrentHashMap<>();

        public static <T> void putEnum(BaseEnum<T> baseEnum, T code, String msg) {
            ENUM_MAP.put(baseEnum, new EnumBean<T>(code, msg));
        }

        @SuppressWarnings("unchecked")
        public static <K extends BaseEnum<T>, T> EnumBean<T> getEnum(K dict) {
            return (EnumBean<T>) ENUM_MAP.get(dict);
        }
    }

    @Getter
    class EnumBean<T> {

        private final T code;
        private final String msg;

        public EnumBean(final T code, final String msg) {
            this.code = code;
            this.msg = msg;
        }

    }

}
























