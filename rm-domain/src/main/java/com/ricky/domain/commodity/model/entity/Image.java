package com.ricky.domain.commodity.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ricky.exception.NullException;
import com.ricky.marker.Entity;
import com.ricky.types.commodity.ImageId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/28
 * @className Image
 * @desc 图片
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image implements Entity<ImageId> {

    private ImageId id;
    private String name; // 图片名
    private String url; // 图片url
    private Long sizeInBytes; // 图片大小，以字节为单位

    @JsonCreator
    public Image(String name, String url, Long sizeInBytes) {
        NullException.isNull(url, "图片url不能为空");
        if (sizeInBytes == null || sizeInBytes <= 0) {
            throw new IllegalArgumentException("图片大小必须为正数");
        }

        this.name = name;
        this.url = url;
        this.sizeInBytes = sizeInBytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) &&
                Objects.equals(name, image.name) &&
                Objects.equals(url, image.url) &&
                Objects.equals(sizeInBytes, image.sizeInBytes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, sizeInBytes);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", sizeInBytes=" + sizeInBytes +
                '}';
    }

}
