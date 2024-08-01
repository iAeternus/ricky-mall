package com.ricky.utils;

import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/6/18
 * @className ReflectionUtils
 * @desc
 */
@Slf4j
public class ReflectionUtils {

    private ReflectionUtils() {
    }

    public static <T extends Aggregate<ID>, ID extends Identifier> void writeField(
            @NotNull String fieldName,
            @NotNull T aggregate,
            @NotNull Object newValue) {
        try {
            Field[] fields = aggregate.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equalsIgnoreCase(field.getName())) {
                    field.setAccessible(true);
                    field.set(aggregate, newValue);
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            log.error("Failed to set field " + fieldName + " on object of type " + aggregate.getClass().getName(), e);
        }
    }

    public static Field[] getFields(Object obj) {
        return obj.getClass().getDeclaredFields();
    }

    public static String getFieldName(Field field) {
        return field.getName();
    }

    /**
     * 判断字段是否为常量（static final）
     * @param field 字段
     * @return 如果是常量则返回true，否则返回false
     */
    public static boolean isConstant(Field field) {
        return Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers());
    }

}
