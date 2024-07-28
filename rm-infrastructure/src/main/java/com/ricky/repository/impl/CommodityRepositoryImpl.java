package com.ricky.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.domain.commodity.repsitory.CommodityRepository;
import com.ricky.enums.impl.CommodityType;
import com.ricky.marker.Entity;
import com.ricky.marker.Identifier;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.converter.impl.CommodityDataConverter;
import com.ricky.persistence.mapper.*;
import com.ricky.persistence.po.*;
import com.ricky.types.commodity.CommodityId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityRepositoryImpl
 * @desc
 */
@Repository
@RequiredArgsConstructor
public class CommodityRepositoryImpl extends RepositoryImpl<Commodity, CommodityId, CommodityPO> implements CommodityRepository {

    private final CommodityDataConverter commodityDataConverter;
    private final CommodityMapper commodityMapper;
    private final RelatedCommodityMapper relatedCommodityMapper;
    private final AttributeMapper attributeMapper;
    private final CommodityImageMapper commodityImageMapper;
    private final SupplierMapper supplierMapper;

    @Override
    public void saveCommodity(Commodity commodity) {
        // 新增的商品默认未上架
        commodity.setType(CommodityType.NOT_LISTED);
        save(commodity);
    }

    @Override
    public Commodity getById(Long id) {
        return find(new CommodityId(id));
    }


    // @Override
    // protected void doUpdate(Commodity aggregate, AggregateDifference<Commodity, CommodityId> difference) {
    //     if (difference.isSelfModified(aggregate.getClass())) {
    //         CommodityPO commodityPO = commodityDataConverter.toPO(aggregate);
    //         commodityMapper.updateById(commodityPO);
    //     }
    //
    //
    // }


    // @Override
    // public CommodityPO toPO(@NonNull Commodity aggregate) {
    //     return commodityDataConverter.toPO(aggregate);
    // }
    //
    // @Override
    // @SuppressWarnings("unchecked")
    // public Commodity toAggregate(@NonNull CommodityPO po, Map<Class<?>, List<? extends BasePO>> relatedPOLists) {
    //     return commodityDataConverter.toEntity(po,
    //             (List<GalleryImagePO>) relatedPOLists.get(GalleryImagePO.class),
    //             (List<AttributePO>) relatedPOLists.get(AttributePO.class),
    //             (List<RelatedCommodityPO>) relatedPOLists.get(RelatedCommodityPO.class));
    // }

    @Override
    protected Map<String, List<? extends BasePO>> selectRelatedLists(CommodityPO po) {
        Map<String, List<? extends BasePO>> map = new HashMap<>();
        map.put(Commodity.RELATED_IMAGES, commodityImageMapper.selectList(new QueryWrapper<CommodityImagePO>().lambda()
                .eq(CommodityImagePO::getCommodityId, po.getId())));
        map.put(Commodity.RELATED_ATTRIBUTES, attributeMapper.selectList(new QueryWrapper<AttributePO>().lambda()
                .eq(AttributePO::getCommodityId, po.getId())));
        map.put(Commodity.RELATED_SUPPLIERS, supplierMapper.selectList(new QueryWrapper<SupplierPO>().lambda()
                .eq(SupplierPO::getCommodityId, po.getId())));
        map.put(Commodity.RELATED_COMMODITY_IDS, relatedCommodityMapper.selectList(new QueryWrapper<RelatedCommodityPO>().lambda()
                .eq(RelatedCommodityPO::getCommodityId, po.getId())));
        return map;
    }

    @Override
    protected Map<String, DataConverter<Entity<Identifier>, Identifier, BasePO>> relatedEntityDataConverter() {
        return null; // TODO
    }

}
