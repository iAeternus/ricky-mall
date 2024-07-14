package com.ricky.utils;

import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/6/18
 * @className SnapshotUtils
 * @desc
 */
public class SnapshotUtils {

    public static <T extends Aggregate<ID>, ID extends Identifier> T snapshot(T aggregate) {
        return null;
    }

}
