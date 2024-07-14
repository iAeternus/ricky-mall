package com.ricky.context;

import com.ricky.entity.diff.EntityDiff;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import com.ricky.utils.ReflectionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/6/18
 * @className AggregateContext
 * @desc
 */
public abstract class AggregateContext<T extends Aggregate<ID>, ID extends Identifier> {

    public abstract void attach(T aggregate);
    public abstract void detach(T aggregate);
    public abstract T find(ID id);
    public abstract EntityDiff detectChanges(T aggregate);
    public abstract void merge(T aggregate);

    public void setId(T aggregate, ID id) {
        ReflectionUtils.writeField("id", aggregate, id);
    }

}
