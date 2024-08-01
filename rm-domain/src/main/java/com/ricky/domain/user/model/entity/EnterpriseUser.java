package com.ricky.domain.user.model.entity;

import com.ricky.marker.Entity;
import com.ricky.types.user.Company;
import com.ricky.types.user.EnterpriseUserId;
import com.ricky.types.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className EnterpriseUser
 * @desc 企业用户
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseUser implements Entity<EnterpriseUserId> {

    private EnterpriseUserId id;
    private UserId userId;
    private Company company; // 公司

}
