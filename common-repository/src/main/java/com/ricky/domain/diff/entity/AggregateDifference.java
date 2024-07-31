package com.ricky.domain.diff.entity;

import cn.hutool.core.collection.CollUtil;
import com.ricky.domain.diff.enums.DifferenceType;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/27
 * @className AggregateDifference
 * @desc 聚合根差异
 */
@Data
public class AggregateDifference<T extends Aggregate<ID>, ID extends Identifier> {

    /**
     * 快照对象
     */
    private T snapshot;

    /**
     * 追踪对象
     */
    private T aggregate;

    /**
     * 差异类型
     */
    private DifferenceType differenceType;

    /**
     * 字段差异
     */
    private Map<String, FieldDifference> fieldDifferences;

    public AggregateDifference(T snapshot, T aggregate, DifferenceType differenceType) {
        this.snapshot = snapshot;
        this.aggregate = aggregate;
        this.differenceType = differenceType;
        this.fieldDifferences = new HashMap<>();
    }

    /**
     * @return 若聚合根被修改则返回true，否则返回false
     */
    public boolean isSelfModified(Class<?> clazz) {
        // if(CollUtil.isEmpty(fieldDifferences)) {
        //     return true;
        // }
        //
        // Set<String> fieldNames = fieldDifferences.keySet();
        // for (String fieldName : fieldNames) {
        //     FieldDifference fieldDifference = fieldDifferences.get(fieldName);
        //     if (isUntouched(fieldDifference)) {
        //         continue;
        //     }
        //     String typeName = fieldDifference.getType().getTypeName();
        //     if(List.class.getName().equals(typeName)) {
        //         return true;
        //     }
        // }
        // return false;

        if (CollUtil.isEmpty(fieldDifferences)) {
            return false;
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            FieldDifference fieldDifference = fieldDifferences.get(fieldName);

            if (fieldDifference == null || isUntouched(fieldDifference) || fieldDifference.getDifferenceType() != DifferenceType.MODIFIED) {
                continue;
            }

            if (fieldDifferences.containsKey(fieldName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 筛选出关联对象的字段差异
     */
    public List<FieldDifference> filtrateRelated() {
        if (CollUtil.isEmpty(fieldDifferences)) {
            return Collections.emptyList();
        }

        return fieldDifferences.keySet().stream()
                .filter(fieldName -> {
                    FieldDifference fieldDifference = fieldDifferences.get(fieldName);
                    // 筛选出被修改的
                    if (fieldDifference.getDifferenceType() != DifferenceType.MODIFIED) {
                        return false;
                    }
                    // 筛选出关联对象
                    return isRelated(fieldDifference.getTracValue());
                })
                .map(fieldName -> fieldDifferences.get(fieldName))
                .toList();
    }

    public boolean isEmpty() {
        return fieldDifferences.isEmpty() || differenceType == DifferenceType.UNTOUCHED;
    }

    private boolean isUntouched(@NotNull FieldDifference fieldDifference) {
        return DifferenceType.UNTOUCHED == fieldDifference.getDifferenceType();
    }

    private boolean isRelated(Object obj) {
        return obj instanceof List;
    }

}
