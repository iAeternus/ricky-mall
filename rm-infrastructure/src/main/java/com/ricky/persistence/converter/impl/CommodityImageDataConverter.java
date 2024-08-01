package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.entity.Image;
import com.ricky.persistence.converter.AssociationDataConverter;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.po.CommodityImagePO;
import com.ricky.types.commodity.ImageId;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/29
 * @className CommodityImageDataConverter
 * @desc
 */
@Service
public class CommodityImageDataConverter implements AssociationDataConverter<Image, ImageId, CommodityImagePO> {
    @Override
    public CommodityImagePO toPO(@NonNull Image entity) {
        return CommodityImagePO.builder()
                .name(entity.getName())
                .imageUrl(entity.getUrl())
                .sizeInBytes(entity.getSizeInBytes())
                .build();
    }

    @Override
    public Image toEntity(@NonNull CommodityImagePO po) {
        return Image.builder()
                .id(new ImageId(po.getId()))
                .name(po.getName())
                .url(po.getImageUrl())
                .sizeInBytes(po.getSizeInBytes())
                .build();
    }

    @Override
    public CommodityImagePO convert(@NonNull Image entity, Serializable foreignKey) {
        CommodityImagePO po = toPO(entity);
        po.setCommodityId((Long) foreignKey);
        return po;
    }
}
