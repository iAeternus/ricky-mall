package com.ricky.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtQueryChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifiable;
import com.ricky.marker.Identifier;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.po.BasePO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className Repository
 * @desc
 */
@Service
@RequiredArgsConstructor
public abstract class Repository<T extends Aggregate<ID>, ID extends Identifier, PO extends BasePO> {

    private final DataConverter<T, ID, PO> dataConverter;

    protected abstract ID onInsert(PO po);


    public ID save(T t) {
        if(t.getId() == null) {
            PO po = dataConverter.toPO(t);
            return onInsert(po);
        }

    }

}
