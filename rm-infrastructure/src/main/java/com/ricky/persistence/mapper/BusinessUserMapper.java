package com.ricky.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ricky.persistence.po.BusinessUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/12
 * @className BusinessUserMapper
 * @desc
 */
@Mapper
public interface BusinessUserMapper extends BaseMapper<BusinessUserPO> {
}
