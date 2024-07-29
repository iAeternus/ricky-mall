package com.ricky.domain.commodity.model.entity;

import com.ricky.marker.Entity;
import com.ricky.types.commodity.RelatedCommodityId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/29
 * @className RelatedCommodity
 * @desc
 */
@Data
@Builder
@AllArgsConstructor
public class RelatedCommodity implements Entity<RelatedCommodityId> {

    private RelatedCommodityId id;
    private Long relatedCommodityId; // 关联商品id

    public RelatedCommodity(Long relatedCommodityId) {
        this.relatedCommodityId = relatedCommodityId;
    }

}
