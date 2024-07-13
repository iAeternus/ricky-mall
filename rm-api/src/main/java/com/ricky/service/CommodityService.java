package com.ricky.service;

import com.ricky.dto.command.SaveCommodityCommand;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityService
 * @desc
 */
@Service
public interface CommodityService {
    void saveCommodity(SaveCommodityCommand command);
}