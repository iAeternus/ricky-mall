package com.ricky.enums;

import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @param <K> 枚举键key类型
 * @param <V> 枚举值value类型
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className BaseEnum
 * @desc 基础枚举接口
 */
public interface BaseEnum<K, V> {

    /**
     * 初始化枚举
     *
     * @param code 枚举值
     * @param msg  枚举信息
     */
    default void initEnum(final K code, final V msg) {
        EnumContainer.putEnum(this, code, msg);
    }

    /**
     * 获取枚举的值
     *
     * @return 枚举值
     */
    default K getKey() {
        return EnumContainer.getEnum(this).getKey();
    }

    /**
     * 获取枚举的描述
     *
     * @return 描述
     */
    default V getVal() {
        return EnumContainer.getEnum(this).getVal();
    }

    @SuppressWarnings("unchecked")
    static <R extends BaseEnum<K, V>, K, V> R of(Class<? extends BaseEnum<K, V>> clazz, final K key) {
        return Stream.of(clazz.getEnumConstants())
                .filter(baseEnum -> baseEnum.getKey().equals(key))
                .map(baseEnum -> (R) baseEnum)
                .findAny()
                .orElse(null);
    }

    /**
     * 枚举容器，存储枚举值
     */
    class EnumContainer {
        private static final Map<BaseEnum<?, ?>, EnumBean<?, ?>> ENUM_MAP = new ConcurrentHashMap<>();

        public static <K, V> void putEnum(BaseEnum<K, V> baseEnum, K key, V val) {
            ENUM_MAP.put(baseEnum, new EnumBean<K, V>(key, val));
        }

        @SuppressWarnings("unchecked")
        public static <D extends BaseEnum<K, V>, K, V> EnumBean<K, V> getEnum(D dict) {
            return (EnumBean<K, V>) ENUM_MAP.get(dict);
        }
    }

    @Getter
    class EnumBean<K, V> {

        private final K key;
        private final V val;

        public EnumBean(final K key, final V val) {
            this.key = key;
            this.val = val;
        }

    }

}
























