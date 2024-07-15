package com.ricky.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ricky.persistence.po.CommodityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityMapper
 * @desc
 */
@Mapper
public interface CommodityMapper extends IMapper<CommodityPO> {
}
