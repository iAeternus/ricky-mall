package com.ricky.domain.commodity.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ricky.exception.NullException;
import com.ricky.marker.Entity;
import com.ricky.types.commodity.RelatedCommodityId;
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
public class RelatedCommodity implements Entity<RelatedCommodityId> {

    private RelatedCommodityId id;
    private Long relatedCommodityId; // 关联商品id

    @JsonCreator
    public RelatedCommodity(RelatedCommodityId id, Long relatedCommodityId) {
        NullException.isNull(relatedCommodityId, "关联商品id不能为空");
        this.id = id;
        this.relatedCommodityId = relatedCommodityId;
    }
}
