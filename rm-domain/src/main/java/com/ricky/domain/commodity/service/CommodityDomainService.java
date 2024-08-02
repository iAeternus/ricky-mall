package com.ricky.domain.commodity.service;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityDomainService
 * @desc
 */
@Service
public interface CommodityDomainService {
    void saveCommodity(Commodity commodity);

    void modifyCommodity(Commodity commodity);

    Commodity getById(Long id);

    void removeCommodity(Commodity commodity);

    void reduceStock(Long commodityId, Integer delta);

    void modifyPrice(Long commodityId, BigDecimal delta);
}
