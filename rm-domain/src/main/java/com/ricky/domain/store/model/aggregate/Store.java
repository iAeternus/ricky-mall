package com.ricky.domain.store.model.aggregate;

import com.ricky.enums.impl.StoreHeat;
import com.ricky.marker.Aggregate;
import com.ricky.types.store.StoreDesc;
import com.ricky.types.store.StoreId;
import com.ricky.types.store.StoreName;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/12
 * @className Store
 * @desc 店铺
 */
@Data
public class Store implements Aggregate<StoreId> {

    /**
     * 店铺id
     */
    private StoreId id;

    /**
     * 店铺名称
     */
    private StoreName name;

    /**
     * 店铺描述
     */
    private StoreDesc desc;

    /**
     * 店铺热度
     */
    private StoreHeat heat;

    public Store(StoreId id, StoreName name, StoreDesc desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }
}
