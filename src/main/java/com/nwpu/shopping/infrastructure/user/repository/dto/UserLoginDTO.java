package com.nwpu.shopping.infrastructure.user.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @author: feoyang
 * @date: 2024/10/29 21:05
 * @description: TODO
 */

@Data
@SuperBuilder
public class UserLoginDTO extends UserDTO {
    private String encryptedPassword;
}
