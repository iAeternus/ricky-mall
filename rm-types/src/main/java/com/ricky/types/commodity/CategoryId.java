package com.ricky.types.commodity;

import com.ricky.exception.NullException;
import com.ricky.marker.Identifier;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CategoryId
 * @desc 分类id
 */
@Value
public class CategoryId implements Identifier {

    Long value;

    public CategoryId(Long value) {
        NullException.isNull(value, "分类id不能为空");
        this.value = value;
    }
}
