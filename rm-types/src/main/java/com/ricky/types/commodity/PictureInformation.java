package com.ricky.types.commodity;

import cn.hutool.core.util.StrUtil;
import com.ricky.marker.ValueObject;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className PictureInformation
 * @desc 图片信息
 */
@Value
public class PictureInformation implements ValueObject {

    String mainImageUrl; // 主图URL
    List<String> galleryImages; // 商品图片列表URL

    public PictureInformation(String mainImageUrl, List<String> galleryImages) {
        if(StrUtil.isBlank(mainImageUrl)) {
            throw new IllegalArgumentException("主图URL不能为空");
        }
        this.mainImageUrl = mainImageUrl;
        this.galleryImages = galleryImages;
    }

}
