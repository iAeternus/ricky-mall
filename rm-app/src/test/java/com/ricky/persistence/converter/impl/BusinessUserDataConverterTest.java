package com.ricky.persistence.converter.impl;

import com.ricky.domain.user.model.entity.BusinessUser;
import com.ricky.enums.impl.StoreType;
import com.ricky.persistence.po.BusinessUserPO;
import com.ricky.types.user.BusinessUserId;
import com.ricky.types.user.Store;
import com.ricky.types.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/1
 * @className BusinessUserDataConverterTest
 * @desc
 */
@SpringBootTest
public class BusinessUserDataConverterTest {

    @Autowired
    private BusinessUserDataConverter businessUserDataConverter;

    @Test
    public void convert() {
        // Given
        BusinessUser  businessUser = new BusinessUser(
                new BusinessUserId(1L),
                new UserId(1L),
                new Store("xxx", "xxx", "xxx"),
                StoreType.SELF_OPERATED_STORE
        );

        // When
        BusinessUserPO businessUserPO = businessUserDataConverter.convert(businessUser);
        BusinessUser businessUser1 = businessUserDataConverter.convert(businessUserPO);

        // Then
        assertThat(businessUser).isEqualTo(businessUser1);
    }

}
