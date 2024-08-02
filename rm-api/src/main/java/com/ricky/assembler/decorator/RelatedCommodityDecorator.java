package com.ricky.assembler.decorator;

import com.ricky.domain.commodity.model.entity.RelatedCommodity;
import com.ricky.utils.CollUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/2
 * @className RelatedCommodityDecorator
 * @desc
 */
@Service
public class RelatedCommodityDecorator {

    public List<Long> map(List<RelatedCommodity> value) {
        return CollUtils.listConvert(value, RelatedCommodity::getRelatedCommodityId);
    }

}
