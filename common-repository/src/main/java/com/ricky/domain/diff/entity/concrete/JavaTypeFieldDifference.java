package com.ricky.domain.diff.entity.concrete;

import com.ricky.domain.diff.entity.FieldDifference;
import com.ricky.domain.diff.enums.DifferenceType;
import lombok.Data;

import java.lang.reflect.Type;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/27
 * @className JavaTypeFieldDifference
 * @desc java原生类型差异
 */
public class JavaTypeFieldDifference extends FieldDifference {

    public JavaTypeFieldDifference(String filedName, Type type, Object snapshotValue, Object aggregateValue, DifferenceType differenceType) {
        super(filedName, type, snapshotValue, aggregateValue, differenceType);
    }

}
