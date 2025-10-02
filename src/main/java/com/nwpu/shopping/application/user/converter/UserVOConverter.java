package com.nwpu.shopping.application.user.converter;

import com.nwpu.shopping.api.controller.user.vo.UserLoginVO;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserLoginDTO;

/**
 * @author: feoyang
 * @date: 2024/10/29 19:03
 * @description: TODO
 */
public class UserVOConverter {
    public static UserLoginVO convertToUserLoginVO(UserLoginDTO userLoginDto, String token) {
        return UserLoginVO.builder()
            .account(userLoginDto.getAccount())
            .nickname(userLoginDto.getNickname())
            .avatarUrl(userLoginDto.getAvatarUrl())
            .role(userLoginDto.getRole())
            .token(token)
            .id(userLoginDto.getId())
            .createTime(userLoginDto.getCreateTime())
            .updateTime(userLoginDto.getUpdateTime())
            .phone(userLoginDto.getPhone())
            .description(userLoginDto.getDescription())
            .wechat(userLoginDto.getWechat())
            .qq(userLoginDto.getQq())
            .email(userLoginDto.getEmail())
            .build();
    }
}
