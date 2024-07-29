package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.entity.RelatedCommodity;
import com.ricky.marker.Entity;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.po.BasePO;
import com.ricky.persistence.po.RelatedCommodityPO;
import com.ricky.types.commodity.CommodityId;
import com.ricky.types.commodity.RelatedCommodityId;
import lombok.NonNull;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/29
 * @className RelatedCommodityDataConverter
 * @desc
 */
@Service
public class RelatedCommodityDataConverter implements DataConverter<RelatedCommodity, RelatedCommodityId, RelatedCommodityPO> {
    @Override
    public RelatedCommodityPO toPO(@NonNull RelatedCommodity entity) {
        return RelatedCommodityPO.builder()
                .relatedCommodityId(entity.getRelatedCommodityId())
                .build();
    }

    @Override
    public RelatedCommodity toEntity(@NonNull RelatedCommodityPO po) {
        return RelatedCommodity.builder()
                .id(new RelatedCommodityId(po.getId()))
                .relatedCommodityId(po.getCommodityId())
                .build();
    }
}
