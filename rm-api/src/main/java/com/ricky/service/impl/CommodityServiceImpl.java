package com.ricky.service.impl;

import com.ricky.assembler.CommodityAssembler;
import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.domain.commodity.service.CommodityDomainService;
import com.ricky.dto.command.ModifyCommodityCommand;
import com.ricky.dto.command.SaveCommodityCommand;
import com.ricky.dto.response.GetCommodityByIdResponse;
import com.ricky.service.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityServiceImpl
 * @desc
 */
@Service
@RequiredArgsConstructor
public class CommodityServiceImpl implements CommodityService {

    private final CommodityAssembler commodityAssembler;
    private final CommodityDomainService commodityDomainService;

    @Override
    public void saveCommodity(SaveCommodityCommand command) {
        Commodity commodity = commodityAssembler.convert(command);
        commodityDomainService.saveCommodity(commodity);
    }

    @Override
    public void modifyCommodity(ModifyCommodityCommand command) {
        Commodity commodity = commodityAssembler.convert(command);
        commodityDomainService.modifyCommodity(commodity);
    }

    @Override
    public GetCommodityByIdResponse getCommodityById(Long id) {
        Commodity commodity = commodityDomainService.getById(id);
        return commodityAssembler.convert(commodity);
    }


}
