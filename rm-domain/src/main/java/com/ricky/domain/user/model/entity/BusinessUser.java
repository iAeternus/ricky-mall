package com.ricky.domain.user.model.entity;

import com.ricky.enums.StoreType;
import com.ricky.marker.Entity;
import com.ricky.types.BusinessUserId;
import com.ricky.types.Store;
import com.ricky.types.UserId;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/12
 * @className BusinessUser
 * @desc 商家
 */
@Data
public class BusinessUser implements Entity<BusinessUserId> {

    private BusinessUserId id;
    private UserId userId; // 用户id
    private Store store; // 店铺
    private StoreType storeType; // 店铺类型

}
