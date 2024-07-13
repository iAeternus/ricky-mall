package com.ricky.types.commodity;

import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CategoryId
 * @desc 分类id
 */
@Value
public class CategoryId implements ValueObject {

    Long value;

    public CategoryId(Long value) {
        if(value == null) {
            throw new IllegalStateException("分类id不能为空");
        }
        this.value = value;
    }
}
