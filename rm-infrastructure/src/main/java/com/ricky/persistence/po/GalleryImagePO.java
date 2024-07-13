package com.ricky.persistence.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className GalleryImagePO
 * @desc 商品图片PO实体
 */
@Data
@TableName("gallery_image")
@EqualsAndHashCode(callSuper = true)
public class GalleryImagePO extends BasePO {

    @TableId
    private Long id;
    private Long commodityId; // 商品id
    private String imageUrl; // 图片URL

}
