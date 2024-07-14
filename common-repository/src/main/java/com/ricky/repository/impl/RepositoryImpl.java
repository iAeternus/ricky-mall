package com.ricky.repository.impl;

import com.ricky.entity.diff.EntityDiff;
import com.ricky.manager.AggregateManager;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import com.ricky.repository.Repository;
import com.ricky.support.RepositorySupport;

import java.awt.geom.RectangularShape;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className RepositoryImpl
 * @desc
 */
public class RepositoryImpl<T extends Aggregate<ID>, ID extends Identifier> extends RepositorySupport<T, ID> implements Repository<T, ID> {

    @Override
    protected void onInsert(T aggregate) {
        
    }

    @Override
    protected T onSelect(ID id) {
        return null;
    }

    @Override
    protected void onUpdate(T aggregate, EntityDiff diff) {

    }

    @Override
    protected void onDelete(T aggregate) {

    }
}
