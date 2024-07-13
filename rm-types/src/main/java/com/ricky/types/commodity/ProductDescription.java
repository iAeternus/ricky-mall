package com.ricky.types.commodity;

import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className ProductDescription
 * @desc 商品描述
 */
@Value
public class ProductDescription implements ValueObject {

    String value;

    public ProductDescription(String value) {
        this.value = value;
    }

    /**
     * 统计字符串中字母的数量（包括多语言）
     * @return 字符串中字母的总数
     */
    public int countLetters() {
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (Character.isLetter(ch)) {
                count++;
            }
        }
        return count;
    }

}
