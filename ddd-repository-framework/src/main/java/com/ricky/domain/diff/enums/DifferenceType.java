package com.ricky.domain.diff.enums;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/27
 * @className DifferenceType
 * @desc 差异状态
 */
public enum DifferenceType {

    /**
     * 新增
     */
    ADDED(),

    /**
     * 删除
     */
    REMOVED(),

    /**
     * 修改
     */
    MODIFIED(),

    /**
     * 无变化
     */
    UNTOUCHED(),
    ;

    public static DifferenceType basicDifferenceType(Object snapshot, Object aggregate) {
        if (snapshot == null && aggregate == null) {
            return UNTOUCHED;
        }
        if (snapshot == null) {
            return ADDED;
        }
        if (aggregate == null) {
            return REMOVED;
        }
        return null;
    }

}
