package com.ricky.domain.diff.utils;

import cn.hutool.core.collection.CollUtil;
import com.ricky.domain.diff.entity.AggregateDifference;
import com.ricky.domain.diff.entity.FieldDifference;
import com.ricky.domain.diff.entity.concrete.AggregateFieldDifference;
import com.ricky.domain.diff.entity.concrete.CollectionFieldDifference;
import com.ricky.domain.diff.entity.concrete.JavaTypeFieldDifference;
import com.ricky.domain.diff.enums.DifferenceType;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Entity;
import com.ricky.marker.Identifier;
import com.ricky.utils.ReflectionUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/27
 * @className DifferenceUtils
 * @desc 差异工具类
 */
@Slf4j
public class DifferenceUtils {

    private DifferenceUtils() {
    }

    /**
     * 对比差异
     */
    public static <T extends Aggregate<ID>, ID extends Identifier> AggregateDifference<T, ID> different(T snapshot, T aggregate) {
        DifferenceType differenceType = DifferenceType.basicDifferenceType(snapshot, aggregate);
        if (differenceType != DifferenceType.MODIFIED) {
            return new AggregateDifference<>(snapshot, aggregate, differenceType);
        }

        Field[] fields = ReflectionUtils.getFields(aggregate);
        // 标记Aggregate
        DifferenceType aggregateDifferenceType;
        try {
            aggregateDifferenceType = aggregateDifferenceType(fields, snapshot, aggregate);
            // 构建AggregateDifference
            AggregateDifference<T, ID> aggregateDifference = new AggregateDifference<>(snapshot, aggregate, aggregateDifferenceType);
            Map<String, FieldDifference> fieldDifferences = aggregateDifference.getFieldDifferences();
            // 对比字段差异
            setDifferences(snapshot, aggregate, fields, fieldDifferences);
            return aggregateDifference;
        } catch (IllegalAccessException e) {
            log.error("Failed to access fields during aggregation difference calculation", e);
            log.error("Snapshot ID: {}", snapshot.getId());
            log.error("Aggregate ID: {}", aggregate.getId());
        }
        return null;
    }

    /**
     * 标记aggregate
     */
    private static <T extends Aggregate<ID>, ID extends Identifier> DifferenceType aggregateDifferenceType(Field[] fields, T snapshot, T aggregate) throws IllegalAccessException {
        DifferenceType differenceType = DifferenceType.basicDifferenceType(snapshot, aggregate);
        if (differenceType != DifferenceType.MODIFIED) {
            return differenceType;
        }

        boolean unchanged = true;
        for (Field field : fields) {
            field.setAccessible(true);

            // 处理需要跳过的情形
            if (shouldSkipClass(field.getType())) {
                continue;
            }

            if (Collection.class.isAssignableFrom(field.getType())) {
                ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                Class<?> parameterizedClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                if (Aggregate.class.isAssignableFrom(parameterizedClass) || Map.class.isAssignableFrom(parameterizedClass)) {
                    continue;
                }
            }

            // 对比字段差异
            Object aggregateValue = field.get(aggregate);
            Object snapshotValue = field.get(snapshot);
            if (snapshotValue == null && aggregateValue == null) {
                continue;
            } else if (snapshotValue == null) {
                unchanged = false;
                continue;
            }
            unchanged = snapshotValue.equals(aggregateValue) & unchanged;
        }
        return unchanged ? DifferenceType.UNTOUCHED : DifferenceType.MODIFIED;
    }

    private static boolean shouldSkipClass(Class<?> clazz) {
        return Identifier.class.isAssignableFrom(clazz) || Aggregate.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz);
    }

    /**
     * 遍历Aggregate的字段，并对比每个字段的差异
     */
    private static <T extends Aggregate<ID>, ID extends Identifier> void setDifferences(T snapshot, T aggregate, Field[] fields, Map<String, FieldDifference> fieldDifferences) throws IllegalAccessException {
        for (Field field : fields) {
            if (Identifier.class.isAssignableFrom(field.getType())) {
                continue;
            }

            String filedName = ReflectionUtils.getFieldName(field);
            field.setAccessible(true);

            Object snapshotValue = snapshot == null ? null : field.get(snapshot);
            Object aggregateValue = aggregate == null ? null : field.get(aggregate);
            if (snapshotValue == null && aggregateValue == null) {
                continue;
            }
            // 对比每个字段的差异
            FieldDifference fieldDifference = compareField(field, snapshotValue, aggregateValue);
            fieldDifferences.put(filedName, fieldDifference);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Aggregate<ID>, ID extends Identifier> FieldDifference compareField(Field field, Object snapshotValue, Object aggregateValue) throws IllegalAccessException {
        ComparableType comparableType = ComparableType.of(aggregateValue == null ? snapshotValue : aggregateValue);
        if (ComparableType.AGGREGATE_TYPE.equals(comparableType)) {
            return compareAggregateType(field, (T) snapshotValue, (T) aggregateValue);
        } else if (ComparableType.COLLECTION_TYPE.equals(comparableType)) {
            return compareCollectionType(field, snapshotValue, aggregateValue);
        } else if (ComparableType.JAVA_TYPE.equals(comparableType)) {
            return compareJavaType(field, snapshotValue, aggregateValue);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * 可比较的字段类型
     */
    enum ComparableType {

        /**
         * 聚合根类型
         */
        AGGREGATE_TYPE(),

        /**
         * 集合类型
         */
        COLLECTION_TYPE(),

        /**
         * java基本数据类型
         */
        JAVA_TYPE(),

        /**
         * 其他类型
         */
        OTHER_TYPE(),
        ;

        public static ComparableType of(@NonNull Object obj) {
            if (obj instanceof Aggregate) {
                return AGGREGATE_TYPE;
            } else if (obj instanceof Collection) {
                return COLLECTION_TYPE;
            } else if (obj instanceof Map) {
                return OTHER_TYPE;
            } else {
                return JAVA_TYPE;
            }
        }
    }

    /**
     * 比较java基本类型差异
     *
     * @param field          字段
     * @param snapshotValue  快照值
     * @param aggregateValue 聚合根值
     * @return 返回字段差异
     */
    private static FieldDifference compareJavaType(Field field, Object snapshotValue, Object aggregateValue) {
        String fieldName = ReflectionUtils.getFieldName(field);
        Type type = field.getGenericType();
        DifferenceType differenceType = javaDifferentType(snapshotValue, aggregateValue);
        return new JavaTypeFieldDifference(fieldName, type, snapshotValue, aggregateValue, differenceType);
    }

    private static DifferenceType javaDifferentType(Object snapshot, Object aggregate) {
        DifferenceType differenceType = DifferenceType.basicDifferenceType(snapshot, aggregate);
        if (differenceType != DifferenceType.MODIFIED) {
            return differenceType;
        }

        if (snapshot.equals(aggregate)) {
            return DifferenceType.UNTOUCHED;
        } else {
            return DifferenceType.MODIFIED;
        }
    }

    /**
     * 比较aggregate差异
     *
     * @param field          字段
     * @param snapshotValue  快照值
     * @param aggregateValue 聚合根值
     * @return 返回字段差异
     */
    private static <T extends Aggregate<ID>, ID extends Identifier> FieldDifference compareAggregateType(Field field, T snapshotValue, T aggregateValue) throws IllegalAccessException {
        String filedName = ReflectionUtils.getFieldName(field);
        Type type = field.getGenericType();

        Aggregate<?> notNullValue = snapshotValue == null ? aggregateValue : snapshotValue;
        Field[] entityFields = ReflectionUtils.getFields(notNullValue);
        Identifier id = notNullValue.getId();

        DifferenceType differenceType = aggregateDifferenceType(entityFields, snapshotValue, aggregateValue);
        AggregateFieldDifference aggregateFieldDifference = new AggregateFieldDifference(filedName, type, snapshotValue, aggregateValue, differenceType, id);
        Map<String, FieldDifference> fieldDifferences = aggregateFieldDifference.getFieldDifferences();
        setDifferences(snapshotValue, aggregateValue, entityFields, fieldDifferences);
        return aggregateFieldDifference;
    }

    /**
     * 比较集合类型差异
     * 不能处理Map类型
     *
     * @param field          字段
     * @param snapshotValue  快照值
     * @param aggregateValue 聚合根值
     * @return 返回字段差异
     */
    @SuppressWarnings("unchecked")
    private static <T extends Aggregate<ID>, ID extends Identifier> FieldDifference compareCollectionType(Field field, Object snapshotValue, Object aggregateValue) throws IllegalAccessException {
        String filedName = ReflectionUtils.getFieldName(field);
        Type type = field.getGenericType();

        ParameterizedType parameterizedType = (ParameterizedType) type;
        Class<?> genericityClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];

        // 处理泛型为实现Entity接口的类型的集合
        if (Entity.class.isAssignableFrom(genericityClass)) {
            Collection<Entity<Identifier>> snapshotValues = (Collection<Entity<Identifier>>) snapshotValue;
            Collection<Entity<Identifier>> entityValues = (Collection<Entity<Identifier>>) aggregateValue;
            DifferenceType differenceType = collectionDifferentType(genericityClass, snapshotValues, entityValues);
            List<FieldDifference> fieldDifferences = entityCollectionDifference(snapshotValues, entityValues);
            return new CollectionFieldDifference(filedName, type, snapshotValue, aggregateValue, differenceType, fieldDifferences);
        }

        // 处理泛型为Java类型的集合
        if (!Aggregate.class.isAssignableFrom(genericityClass) && !Map.class.isAssignableFrom(genericityClass)) {
            Collection<?> snapshotValues = (Collection<?>) snapshotValue;
            Collection<?> aggregateValues = (Collection<?>) aggregateValue;
            DifferenceType differenceType = collectionDifferentType(genericityClass, snapshotValues, aggregateValues);
            return new CollectionFieldDifference(filedName, type, snapshotValue, aggregateValue, differenceType);
        }

        // 处理泛型为实现Aggregate接口的类型的集合
        Collection<T> snapshotValues = (Collection<T>) snapshotValue;
        Collection<T> aggregateValues = (Collection<T>) aggregateValue;

        Map<Serializable, T> snapshotMap = snapshotValues.stream()
                .collect(Collectors.toMap(snapshot -> snapshot.getId().getValue(), snapshot -> snapshot));
        Map<Serializable, T> aggregateMap = aggregateValues.stream()
                .collect(Collectors.toMap(aggregate -> aggregate.getId().getValue(), aggregate -> aggregate));

        CollectionFieldDifference collectionFieldDifference = new CollectionFieldDifference(filedName, type, snapshotValue, aggregateValue);

        boolean unchanged = true;
        // snapshotMap与aggregateMap的交集，snapshotMap对aggregateMap的补集
        for (Serializable key : snapshotMap.keySet()) {
            T snapshotElement = snapshotMap.get(key);
            T aggregateElement = aggregateMap.get(key);
            FieldDifference fieldDifferent = compareField(field, snapshotElement, aggregateElement);
            unchanged = DifferenceType.UNTOUCHED.equals(fieldDifferent.getDifferenceType()) & unchanged;
            collectionFieldDifference.getElementDifference().add(fieldDifferent);
        }
        // aggregateMap对snapshotMap的补集
        for (Serializable key : aggregateMap.keySet()) {
            if (snapshotMap.get(key) != null) {
                continue;
            }
            T aggregateElement = aggregateMap.get(key);
            FieldDifference fieldDifferent = compareField(field, null, aggregateElement);
            unchanged = DifferenceType.UNTOUCHED.equals(fieldDifferent.getDifferenceType()) & unchanged;
            collectionFieldDifference.getElementDifference().add(fieldDifferent);
        }
        if (unchanged) {
            collectionFieldDifference.setDifferenceType(DifferenceType.UNTOUCHED);
        } else {
            collectionFieldDifference.setDifferenceType(DifferenceType.MODIFIED);
        }
        return collectionFieldDifference;
    }

    private static DifferenceType collectionDifferentType(Class<?> typeArguments, Collection<?> snapshot, Collection<?> aggregate) {
        if (CollUtil.isEmpty(snapshot) && CollUtil.isEmpty(aggregate)) {
            return DifferenceType.UNTOUCHED;
        }
        if (CollUtil.isEmpty(snapshot)) {
            return DifferenceType.ADDED;
        }
        if (CollUtil.isEmpty(aggregate)) {
            return DifferenceType.REMOVED;
        }
        if (specialHandingClass(typeArguments)) {
            return snapshot.size() == aggregate.size() ? DifferenceType.UNTOUCHED : DifferenceType.MODIFIED;
        }
        return snapshot.equals(aggregate) ? DifferenceType.UNTOUCHED : DifferenceType.MODIFIED;
    }

    /**
     * 计算集合的差异，注意这里的差异类型
     * ADDED表示aggregate存在snapshot不存在的对象
     * REMOVED表示snapshot存在aggregate不存在的对象
     * MODIFIED表示存在属性变更的对象
     *
     * @param snapshotValues 快照对象
     * @param entityValues   聚合根对象
     * @return 返回差异
     */
    private static List<FieldDifference> entityCollectionDifference(Collection<Entity<Identifier>> snapshotValues, Collection<Entity<Identifier>> entityValues) {
        List<FieldDifference> differences = new ArrayList<>();
        Map<Identifier, Entity<Identifier>> snapshotMap = new HashMap<>();

        // 填充 snapshotMap
        for (Entity<Identifier> item : snapshotValues) {
            snapshotMap.put(item.getId(), item);
        }

        // 处理 ADDED 和 MODIFIED
        for (Entity<Identifier> item : entityValues) {
            if (snapshotMap.containsKey(item.getId())) {
                if (isModified(snapshotMap.get(item.getId()), item)) {
                    differences.add(FieldDifference.builder()
                            .snapshotValue(snapshotMap.get(item.getId()))
                            .tracValue(item)
                            .differenceType(DifferenceType.MODIFIED)
                            .build());
                }
                snapshotMap.remove(item.getId()); // 移除已处理的元素
            } else {
                differences.add(FieldDifference.builder()
                        .snapshotValue(null) // 添加，快照值不存在
                        .tracValue(item)
                        .differenceType(DifferenceType.ADDED)
                        .build());
            }
        }

        // 处理 REMOVED
        for (Object item : snapshotMap.values()) {
            differences.add(FieldDifference.builder()
                    .snapshotValue(item)
                    .tracValue(null) // 删除，聚合根值不存在
                    .differenceType(DifferenceType.REMOVED)
                    .build());
        }
        return differences;
    }

    private static boolean isModified(Entity<Identifier> original, Entity<Identifier> current) {
        return original.getId().getValue().equals(current.getId().getValue()) && !Objects.equals(original, current);
    }

    private static boolean specialHandingClass(Class<?> clazz) {
        return shouldSkipClass(clazz) || Collection.class.isAssignableFrom(clazz);
    }

}
