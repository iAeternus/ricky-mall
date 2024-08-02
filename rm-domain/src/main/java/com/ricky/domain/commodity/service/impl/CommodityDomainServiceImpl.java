package com.ricky.domain.commodity.service.impl;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.domain.commodity.repsitory.CommodityRepository;
import com.ricky.domain.commodity.service.CommodityDomainService;
import com.ricky.types.commodity.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityDomainServiceImpl
 * @desc
 */
@Service
@RequiredArgsConstructor
public class CommodityDomainServiceImpl implements CommodityDomainService {

    private final CommodityRepository commodityRepository;

    @Override
    public void saveCommodity(Commodity commodity) {
        commodityRepository.saveCommodity(commodity);
    }

    @Override
    public void modifyCommodity(Commodity commodity) {
        commodityRepository.modifyCommodity(commodity);
    }

    @Override
    public Commodity getById(Long id) {
        return commodityRepository.getById(id);
    }

    @Override
    public void removeCommodity(Commodity commodity) {
        commodityRepository.removeCommodity(commodity);
    }

    @Override
    public void reduceStock(Long commodityId, Integer delta) {
        Commodity commodity = commodityRepository.getSelfById(commodityId);
        commodity.reduceStock(delta);
        commodityRepository.modifyCommodity(commodity);
    }

    @Override
    public void modifyPrice(Long commodityId, BigDecimal delta) {
        Commodity commodity = commodityRepository.getSelfById(commodityId);
        commodity.updatePrice(delta);
        commodityRepository.modifyCommodity(commodity);
    }

}
