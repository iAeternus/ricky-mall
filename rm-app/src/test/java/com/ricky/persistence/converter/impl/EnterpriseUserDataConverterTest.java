package com.ricky.persistence.converter.impl;

import com.ricky.domain.user.model.entity.EnterpriseUser;
import com.ricky.persistence.po.EnterpriseUserPO;
import com.ricky.types.user.Company;
import com.ricky.types.user.EnterpriseUserId;
import com.ricky.types.user.UserId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/1
 * @className EnterpriseUserDataConverterTest
 * @desc
 */
@SpringBootTest
public class EnterpriseUserDataConverterTest {

    @Autowired
    private EnterpriseUserDataConverter enterpriseUserDataConverter;

    @Test
    public void convert() {
        // Given
        EnterpriseUser enterpriseUser = new EnterpriseUser(
                new EnterpriseUserId(1L),
                new UserId(1L),
                new Company("xxx", "xxx", "xxx")
        );

        // When
        EnterpriseUserPO enterpriseUserPO = enterpriseUserDataConverter.convert(enterpriseUser);
        EnterpriseUser enterpriseUser1 = enterpriseUserDataConverter.convert(enterpriseUserPO);

        // Then
        assertThat(enterpriseUser).isEqualTo(enterpriseUser1);
    }

}
