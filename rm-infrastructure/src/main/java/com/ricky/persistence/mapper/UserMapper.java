package com.ricky.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ricky.persistence.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className UserMapper
 * @desc
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {


}
