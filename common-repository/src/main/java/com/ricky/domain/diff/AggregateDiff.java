package com.ricky.domain.diff;

import cn.hutool.core.collection.CollUtil;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className AggregateDiff
 * @desc 聚合根diff
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class AggregateDiff<T extends Aggregate<ID>, ID extends Identifier> {

    private DiffNode.State state;
    private List<Diff> diffs = new ArrayList<>();

    public static final AggregateDiff<Aggregate<Identifier>, Identifier> EMPTY = new AggregateDiff<>();

    public AggregateDiff(DiffNode.State state) {
        this.state = state;
    }

    public static <T extends Aggregate<ID>, ID extends Identifier> AggregateDiff<T, ID> newInstance() {
        return new AggregateDiff<>();
    }

    /**
     * 过滤合适状态的diff
     */
    public AggregateDiff<T, ID> filter(DiffNode.State state) {
        if (CollUtil.isEmpty(diffs)) {
            return null;
        }
        return new AggregateDiff<>(state, diffs.stream()
                .filter(entityDiff -> entityDiff.getState() == state)
                .toList());
    }

    /**
     * 过滤出diff中所有属于更改的字段
     */
    public AggregateDiff<T, ID> filterChanged() {
        return filter(DiffNode.State.CHANGED);
    }

    public AggregateDiff<T, ID> diff(T working, T base) {
        AggregateDiff<T, ID> aggregateDiff = new AggregateDiff<>();
        DiffNode diff = ObjectDifferBuilder.buildDefault().compare(working, base);
        if (!diff.hasChanges()) {
            // return EMPTY; // TODO
        }
        aggregateDiff.setState(diff.getState());
        diff.visitChildren(((diffNode, visit) -> {
            if (isValidDiffNode(diffNode)) {
                aggregateDiff.getDiffs().add(Diff.builder()
                        .fieldName(diffNode.getPropertyName())
                        .state(diffNode.getState())
                        .oldValue(diffNode.canonicalGet(base))
                        .newValue(diffNode.canonicalGet(working))
                        .build());
            }
        }));
        return aggregateDiff;
    }

    private static boolean isValidDiffNode(DiffNode diffNode) {
        return diffNode.getParentNode().isRootNode() && !diffNode.getValueType().getSimpleName().contains("List")
                || diffNode.getParentNode().getValueType().getSimpleName().contains("List");
    }

    public boolean isEmpty() {
        return diffs.isEmpty();
    }

    /**
     * 根据diff更新对象
     */
    public void update(@NotNull T base, DiffNode.State state) throws IllegalAccessException {
        Field[] fields = base.getClass().getDeclaredFields();
        AggregateDiff<T, ID> aggregateDiff = filter(state);
        if (CollUtil.isEmpty(aggregateDiff.getDiffs())) {
            return;
        }
        for (Field field : fields) {
            Diff diff = aggregateDiff.getFieldDiff(field);
            if (diff != null) {
                field.setAccessible(true);
                field.set(base, diff.getNewValue()); // 设置新的值
            }
        }
    }

    public void updateChangedOnly(@NotNull T base) throws IllegalAccessException {
        update(base, DiffNode.State.CHANGED);
    }

    private Diff getFieldDiff(Field field) {
        if (Modifier.isFinal(field.getModifiers())) {
            return null;
        }

        String fieldName = field.getName();
        for (Diff diff : diffs) {
            if (fieldName.equals(diff.getFieldName())) {
                return diff;
            }
        }
        return null;
    }

    public boolean isSelfModified() {
        return false;
    }
}
