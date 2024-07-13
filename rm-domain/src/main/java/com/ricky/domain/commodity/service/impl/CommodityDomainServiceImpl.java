package com.ricky.domain.commodity.service.impl;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.domain.commodity.repsitory.CommodityRepository;
import com.ricky.domain.commodity.service.CommodityDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
