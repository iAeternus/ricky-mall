package com.ricky.domain.commodity.service;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import org.springframework.stereotype.Service;

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
}
