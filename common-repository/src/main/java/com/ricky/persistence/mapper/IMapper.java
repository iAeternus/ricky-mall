package com.ricky.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ricky.persistence.po.BasePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/15
 * @className IMapper
 * @desc
 */
@Mapper
public interface IMapper<PO extends BasePO> extends BaseMapper<PO> {

    /**
     * 自定义批量插入
     * 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     */
    int insertBatch(@Param("list") List<PO> list);

    /**
     * 自定义批量更新，条件为主键
     * 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     */
    int updateBatch(@Param("list") List<PO> list);

}
