package com.ricky.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ricky.persistence.po.EnterpriseUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className EnterpriseUserMapper
 * @desc
 */
@Mapper
public interface EnterpriseUserMapper extends BaseMapper<EnterpriseUserPO> {
}
