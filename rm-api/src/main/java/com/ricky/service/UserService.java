package com.ricky.service;

import com.ricky.dto.command.ApplyEnterpriseUserCommand;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className UserService
 * @desc
 */
@Service
public interface UserService {
    void applyForEnterpriseUsers(ApplyEnterpriseUserCommand command);
}
