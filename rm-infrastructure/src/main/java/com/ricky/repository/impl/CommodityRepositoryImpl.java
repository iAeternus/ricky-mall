package com.ricky.repository.impl;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.domain.commodity.repsitory.CommodityRepository;
import com.ricky.manager.AggregateManager;
import com.ricky.persistence.converter.impl.CommodityDataConverter;
import com.ricky.persistence.mapper.CommodityMapper;
import com.ricky.persistence.po.CommodityPO;
import com.ricky.types.commodity.CommodityId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityRepositoryImpl
 * @desc
 */
@Repository
public class CommodityRepositoryImpl extends RepositoryImpl<Commodity, CommodityId, CommodityPO> implements CommodityRepository {

    private final CommodityDataConverter commodityDataConverter;

    /**
     * 傻逼lombok，非要我手写构造
     */
    public CommodityRepositoryImpl(
            AggregateManager<Commodity, CommodityId> aggregateManager,
            CommodityDataConverter commodityDataConverter,
            CommodityMapper commodityMapper) {
        super(aggregateManager, commodityMapper);
        this.commodityDataConverter = commodityDataConverter;
    }

    @Override
    public void saveCommodity(Commodity commodity) {
        save(commodity);
    }

    @Override
    public CommodityPO toPO(@NonNull Commodity aggregate) {
        return commodityDataConverter.toPO(aggregate);
    }

    @Override
    public Commodity toAggregate(@NonNull CommodityPO po) {
        return commodityDataConverter.toEntity(po);
    }
}
