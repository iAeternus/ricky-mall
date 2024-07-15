package com.ricky.utils;

import com.ricky.entity.diff.Diff;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/6/18
 * @className DiffUtils
 * @desc
 */
public class DiffUtils {

    public static <T extends Aggregate<ID>, ID extends Identifier> Diff diff(T snapshot, T aggregate) {
        return null;
    }

}
