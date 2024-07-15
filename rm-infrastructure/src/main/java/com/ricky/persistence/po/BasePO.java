package com.ricky.persistence.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ricky.marker.Identifiable;
import com.ricky.marker.Identifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className BasePO
 * @desc persistence object(PO) 基类
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BasePO {

    @TableId
    protected Long id;

    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime createTime; // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime; // 修改时间

}
