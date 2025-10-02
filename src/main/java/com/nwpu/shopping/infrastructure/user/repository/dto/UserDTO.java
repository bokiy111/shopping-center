package com.nwpu.shopping.infrastructure.user.repository.dto;

import com.nwpu.shopping.domin.enums.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @author: feoyang
 * @date: 2024/10/29 22:11
 * @description: TODO
 */

@Data
@AllArgsConstructor
@SuperBuilder
public class UserDTO {
    private long id;
    private String account;
    private String nickname;
    private String avatarUrl;
    private UserRoleEnum role;
    private String createTime;
    private String updateTime;
    private String phone;
    private String description;
    private String wechat;
    private String qq;
    private String email;


}
