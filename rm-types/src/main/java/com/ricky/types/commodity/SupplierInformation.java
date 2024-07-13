package com.ricky.types.commodity;

import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className SupplierInformation
 * @desc 供应商信息
 */
@Value
public class SupplierInformation implements ValueObject {

    Long supplierId; // 供应商ID

    public SupplierInformation(Long supplierId) {
        this.supplierId = supplierId;
    }

}
