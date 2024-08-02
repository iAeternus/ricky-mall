package com.ricky.domain.commodity.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ricky.exception.NullException;
import com.ricky.marker.Entity;
import com.ricky.types.commodity.SupplierId;
import lombok.Builder;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className Supplier
 * @desc 供应商信息
 */
@Data
@Builder
public class Supplier implements Entity<SupplierId> {

    private SupplierId id; // 供应商ID
    private String name; // 供应商名称
    private String contact; // 联系方式
    private String address; // 地址

    @JsonCreator
    public Supplier(SupplierId id, String name, String contact, String address) {
        NullException.isNull(name, "供应商名称不能为空");

        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

}
