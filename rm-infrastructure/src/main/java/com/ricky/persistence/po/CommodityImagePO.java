package com.ricky.persistence.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/28
 * @className CommodityImagePO
 * @desc
 */
@Data
@TableName("commodity_image")
@EqualsAndHashCode(callSuper = true)
public class CommodityImagePO extends BasePO {

    @TableId
    private Long id;
    private Long commodityId; // 商品id
    private String name; // 图片名
    private String imageUrl; // 图片url
    private Long sizeInBytes; // 图片大小，以字节为单位

}
