package com.ricky.domain.diff.entity.concrete;

import com.ricky.domain.diff.entity.FieldDifference;
import com.ricky.domain.diff.enums.DifferenceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/27
 * @className CollectionFieldDifference
 * @desc 集合类型差异
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CollectionFieldDifference extends FieldDifference {

    /**
     * 集合元素差异
     */
    private List<FieldDifference> elementDifference;

    // /**
    //  * 集合差异类型
    //  */
    // private DifferenceType type;

    public CollectionFieldDifference(String name, Type type, Object snapshotValue, Object tracValue) {
        super(name, type, snapshotValue, tracValue);
        this.elementDifference = new ArrayList<>();
    }

    public CollectionFieldDifference(String name, Type type, Object snapshotValue, Object tracValue, DifferenceType differenceType) {
        super(name, type, snapshotValue, tracValue, differenceType);
        this.elementDifference = new ArrayList<>();
    }

    public CollectionFieldDifference(String name, Type type, Object snapshotValue, Object tracValue, DifferenceType differenceType, List<FieldDifference> elementDifference) {
        super(name, type, snapshotValue, tracValue, differenceType);
        this.elementDifference = elementDifference;
    }

}
