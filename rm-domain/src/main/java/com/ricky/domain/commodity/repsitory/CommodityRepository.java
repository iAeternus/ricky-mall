package com.ricky.domain.commodity.repsitory;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.types.commodity.Stock;
import org.springframework.stereotype.Repository;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityRepository
 * @desc 商品基本信息仓储接口
 */
@Repository
public interface CommodityRepository {
    void saveCommodity(Commodity commodity);

    void modifyCommodity(Commodity commodity);

    Commodity getById(Long id);

    void removeCommodity(Commodity commodity);

    Commodity getSelfById(Long commodityId);

}
