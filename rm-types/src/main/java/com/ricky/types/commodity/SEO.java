package com.ricky.types.commodity;

import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className SEO
 * @desc SEO信息
 * Search Engine Optimization 搜索引擎优化
 */
@Value
public class SEO implements ValueObject {

    String metaTitle; // SEO标题
    String metaKeywords; // SEO关键词
    String metaDescription; // SEO描述

    public SEO(String metaTitle, String metaKeywords, String metaDescription) {
        this.metaTitle = metaTitle;
        this.metaKeywords = metaKeywords;
        this.metaDescription = metaDescription;
    }

}
