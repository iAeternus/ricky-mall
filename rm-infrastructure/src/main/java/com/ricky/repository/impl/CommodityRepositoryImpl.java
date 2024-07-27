package com.ricky.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.domain.commodity.repsitory.CommodityRepository;
import com.ricky.persistence.converter.impl.CommodityDataConverter;
import com.ricky.persistence.mapper.AssociatedCommodityMapper;
import com.ricky.persistence.mapper.AttributeMapper;
import com.ricky.persistence.mapper.GalleryImageMapper;
import com.ricky.persistence.po.*;
import com.ricky.types.commodity.CommodityId;
import lombok.NonNull;
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

    // private final CommodityDataConverter commodityDataConverter;
    private final AssociatedCommodityMapper associatedCommodityMapper;
    private final AttributeMapper attributeMapper;
    private final GalleryImageMapper galleryImageMapper;

    @Override
    public void saveCommodity(Commodity commodity) {
        save(commodity);
    }

    @Override
    public Commodity getById(Long id) {
        return find(new CommodityId(id));
    }

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
    //             (List<AssociatedCommodityPO>) relatedPOLists.get(AssociatedCommodityPO.class));
    // }

    @Override
    protected Map<Class<?>, List<? extends BasePO>> selectRelatedObjects(CommodityPO po) {
        Map<Class<?>, List<? extends BasePO>> map = new HashMap<>();
        map.put(GalleryImagePO.class, galleryImageMapper.selectList(new QueryWrapper<GalleryImagePO>().lambda()
                .eq(GalleryImagePO::getCommodityId, po.getId())));
        map.put(AttributePO.class, attributeMapper.selectList(new QueryWrapper<AttributePO>().lambda()
                .eq(AttributePO::getCommodityId, po.getId())));
        map.put(AssociatedCommodityPO.class, associatedCommodityMapper.selectList(new QueryWrapper<AssociatedCommodityPO>().lambda()
                .eq(AssociatedCommodityPO::getCommodityId, po.getId())));
        return map;
    }

}
