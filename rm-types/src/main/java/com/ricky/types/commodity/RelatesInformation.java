package com.ricky.types.commodity;

import cn.hutool.core.collection.CollUtil;
import com.ricky.marker.ValueObject;
import lombok.Value;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className RelatesInformation
 * @desc 关联信息
 * SPU = Standard Product Unit （标准化产品单元）
 */
@Value
public class RelatesInformation implements ValueObject {

    List<Long> relatedProducts; // 相关商品ID列表
    List<Long> skuIds; // 对应的SKU ID列表

    public RelatesInformation(List<Long> relatedProducts, List<Long> skuIds) {
        if(CollUtil.isEmpty(relatedProducts) || CollUtil.isEmpty(skuIds)
                || relatedProducts.size() != skuIds.size()) {
            throw new IllegalArgumentException("不正确的关联信息");
        }
        this.relatedProducts = relatedProducts;
        this.skuIds = skuIds;
    }

}
