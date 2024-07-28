package com.ricky.domain.diff.entity.concrete;

import com.ricky.domain.diff.entity.FieldDifference;
import com.ricky.domain.diff.enums.DifferenceType;
import com.ricky.marker.Identifier;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/27
 * @className AggregateFieldDifference
 * @desc 聚合根字段差异
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AggregateFieldDifference extends FieldDifference {

    private Map<String, FieldDifference> fieldDifferences;

    private final Identifier identifier;

    public AggregateFieldDifference(String name, Type type, Object snapshotValue, Object tracValue, DifferenceType differenceType, Identifier identifier) {
        super(name, type, snapshotValue, tracValue, differenceType);
        this.identifier = identifier;
        this.fieldDifferences = new HashMap<>();
    }

}
