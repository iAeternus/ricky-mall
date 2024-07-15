package com.ricky.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ricky.persistence.po.BasePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/15
 * @className IMapper
 * @desc
 */
@Mapper
public interface IMapper<PO extends BasePO> extends BaseMapper<PO> {
}
