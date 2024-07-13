package com.ricky.types.commodity;

import com.ricky.exception.NullException;
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

    public RelatesInformation() {
        this.relatedProducts = this.skuIds = null;
    }

    public RelatesInformation(List<Long> relatedProducts, List<Long> skuIds) {
        NullException.isNull(relatedProducts, "相关商品ID列表不能为空");
        NullException.isNull(skuIds, "SKU ID列表不能为空");
        if (relatedProducts.size() != skuIds.size()) {
            throw new IllegalArgumentException("不正确的关联信息");
        }
        this.relatedProducts = relatedProducts;
        this.skuIds = skuIds;
    }

}
