package com.ricky.persistence.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className EnterpriseUserPO
 * @desc
 */
@Data
@TableName("enterprise_user")
@EqualsAndHashCode(callSuper = true)
public class EnterpriseUserPO extends BasePO {

    @TableId
    private Long id;
    private Long userId;
    private String recordNumber;
    private String companyName;
    private String ceo;

}
