package com.ricky.repository.impl;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.domain.commodity.repsitory.CommodityRepository;
import com.ricky.enums.impl.CommodityType;
import com.ricky.marker.Entity;
import com.ricky.marker.Identifier;
import com.ricky.persistence.converter.*;
import com.ricky.persistence.mapper.*;
import com.ricky.persistence.po.*;
import com.ricky.types.commodity.CommodityId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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

    private final CommodityMapper commodityMapper;
    private final RelatedCommodityMapper relatedCommodityMapper;
    private final AttributeMapper attributeMapper;
    private final CommodityImageMapper commodityImageMapper;
    private final SupplierMapper supplierMapper;

    private final CommodityDataConverter commodityDataConverter;
    private final RelatedCommodityDataConverter relatedCommodityDataConverter;
    private final AttributeDataConverter attributeDataConverter;
    private final CommodityImageDataConverter commodityImageDataConverter;
    private final SupplierDataConverter supplierDataConverter;

    @Override
    @Transactional
    public void saveCommodity(Commodity commodity) {
        // 新增的商品默认未上架
        commodity.setType(CommodityType.NOT_LISTED);
        save(commodity);
    }

    @Override
    @Transactional
    public void modifyCommodity(Commodity commodity) {
        save(commodity);
    }

    @Override
    public Commodity getById(Long id) {
        return find(new CommodityId(id));
    }

    @Override
    @Transactional
    public void removeCommodity(Commodity commodity) {
        remove(commodity);
    }

    @Override
    public Commodity getSelfById(Long commodityId) {
        CommodityPO commodityPO = commodityMapper.selectById(commodityId);
        return commodityDataConverter.convert(commodityPO, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <M extends IMapper<P>, P extends BasePO> Map<String, M> relatedMappers() {
        Map<String, M> map = new HashMap<>();
        map.put(Commodity.RELATED_IMAGES, (M) commodityImageMapper);
        map.put(Commodity.RELATED_ATTRIBUTES, (M) attributeMapper);
        map.put(Commodity.RELATED_SUPPLIERS, (M) supplierMapper);
        map.put(Commodity.RELATED_COMMODITIES, (M) relatedCommodityMapper);
        return map;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <E extends Entity<I>, I extends Identifier, P extends BasePO> Map<String, AssociationDataConverter<E, I, P>> relatedDataConverters() {
        Map<String, AssociationDataConverter<E, I, P>> map = new HashMap<>();
        map.put(Commodity.RELATED_IMAGES, (AssociationDataConverter<E, I, P>) commodityImageDataConverter);
        map.put(Commodity.RELATED_ATTRIBUTES, (AssociationDataConverter<E, I, P>) attributeDataConverter);
        map.put(Commodity.RELATED_SUPPLIERS, (AssociationDataConverter<E, I, P>) supplierDataConverter);
        map.put(Commodity.RELATED_COMMODITIES, (AssociationDataConverter<E, I, P>) relatedCommodityDataConverter);
        return map;
    }

    @Override
    protected Map<String, String> relatedColumnNames() {
        Map<String, String> map = new HashMap<>();
        map.put(Commodity.RELATED_IMAGES, "commodity_id");
        map.put(Commodity.RELATED_ATTRIBUTES, "commodity_id");
        map.put(Commodity.RELATED_SUPPLIERS, "commodity_id");
        map.put(Commodity.RELATED_COMMODITIES, "commodity_id");
        return map;
    }

}
