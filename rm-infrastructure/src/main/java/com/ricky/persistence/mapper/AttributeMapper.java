package com.ricky.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ricky.persistence.po.AttributePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/25
 * @className AttributeMapper
 * @desc
 */
@Mapper
public interface AttributeMapper extends IMapper<AttributePO> {
}
