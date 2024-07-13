package com.ricky.domain.commodity.repsitory;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import org.springframework.stereotype.Repository;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityRepository
 * @desc
 */
@Repository
public interface CommodityRepository {
    void saveCommodity(Commodity commodity);
}
