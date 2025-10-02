package com.nwpu.shopping.api.controller.user.vo;

import com.nwpu.shopping.domin.enums.UserRoleEnum;
import com.nwpu.shopping.infrastructure.user.persistence.pojo.UserPO;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserDTO;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserLoginDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @author: feoyang
 * @date: 2024/10/29 18:54
 * @description: TODO
 */

@Data
@SuperBuilder
public class UserLoginVO extends UserDTO {
    private String token;
}
