package com.nwpu.shopping.infrastructure.user.repository.dto.converter;

import com.nwpu.shopping.domin.enums.UserRoleEnum;
import com.nwpu.shopping.infrastructure.user.persistence.pojo.UserPO;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserDTO;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserLoginDTO;

/**
 * @author: feoyang
 * @date: 2024/10/29 21:25
 * @description: TODO
 */
public class DTOConverter {
    public static UserLoginDTO convertToUserLoginDTO(UserPO userPO) {
        return UserLoginDTO.builder()
                .account(userPO.getAccount())
                .encryptedPassword(userPO.getEncryptedPassword())
                .nickname(userPO.getNickname())
                .avatarUrl(userPO.getAvatarUrl())
                .role(UserRoleEnum.fromCode(userPO.getRole()))
                .createTime(userPO.getCreateTime())
                .updateTime(userPO.getUpdateTime())
                .phone(userPO.getPhone())
                .description(userPO.getDescription())
                .wechat(userPO.getWechat())
                .qq(userPO.getQq())
                .email(userPO.getEmail())
                .id(userPO.getId())
                .build();
    }

    public static UserDTO convertToUserDTO(UserPO userPO) {
        return UserDTO.builder()
                .account(userPO.getAccount())
                .nickname(userPO.getNickname())
                .avatarUrl(userPO.getAvatarUrl())
                .role(UserRoleEnum.fromCode(userPO.getRole()))
                .createTime(userPO.getCreateTime())
                .updateTime(userPO.getUpdateTime())
                .phone(userPO.getPhone())
                .description(userPO.getDescription())
                .wechat(userPO.getWechat())
                .qq(userPO.getQq())
                .email(userPO.getEmail())
                .id(userPO.getId())
                .build();
    }
}
