package com.ricky.utils;

import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/6/18
 * @className ReflectionUtils
 * @desc
 */
public class ReflectionUtils {
    public static <T extends Aggregate<ID>, ID extends Identifier> void writeField(
            @NotNull String fieldName,
            @NotNull T aggregate,
            @NotNull Object newValue) {
        try {
            Field[] fields = aggregate.getClass().getFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    field.setAccessible(true);
                    field.set(aggregate, newValue); // TODO
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
    }
}
