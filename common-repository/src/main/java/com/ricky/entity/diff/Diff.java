package com.ricky.entity.diff;

import de.danielbechler.diff.node.DiffNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/6/18
 * @className Diff
 * @desc 数据差异对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diff {

    private String fieldName; // 字段名
    private DiffNode.State state; // diff状态
    private Object oldValue; // 旧值
    private Object newValue; // 新值

    public Diff(String fieldName, DiffNode.State state) {
        this(fieldName, state, null, null);
    }

    public boolean isEmpty() {
        return false;
    }

}