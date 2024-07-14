package com.ricky.entity.diff;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/6/18
 * @className EntityDiff
 * @desc
 */
public class EntityDiff {

    public static final EntityDiff EMPTY = new EntityDiff();

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public boolean isSelfModified() {
        return false;
    }
}
