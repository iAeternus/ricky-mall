package com.ricky.persistence.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className BasePO
 * @desc persistence object(PO) 基类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BasePO {

    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime createTime; // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime; // 修改时间

    /**
     * 获取Long类型的ID
     *
     * @return ID
     */
    public abstract Long getId();

    /**
     * 设置Long类型的ID
     *
     * @param id 标识符
     */
    public abstract void setId(Long id);

}
