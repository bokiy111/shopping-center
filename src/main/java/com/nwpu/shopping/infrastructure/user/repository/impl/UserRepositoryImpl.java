package com.nwpu.shopping.infrastructure.user.repository.impl;

import com.nwpu.shopping.api.controller.user.req.UpdateUserInfoReq;
import com.nwpu.shopping.domin.enums.UserRoleEnum;
import com.nwpu.shopping.infrastructure.user.persistence.mapper.UserMapper;
import com.nwpu.shopping.infrastructure.user.persistence.pojo.UserPO;
import com.nwpu.shopping.infrastructure.user.repository.UserRepository;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserDTO;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserLoginDTO;
import com.nwpu.shopping.infrastructure.user.repository.dto.converter.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: feoyang
 * @date: 2024/10/29 21:00
 * @description: TODO
 */

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserLoginDTO getUserInfoWithEntryedPasswordByAccount(String account) {
        UserPO userPo = userMapper.getUserAllInfoByAccount(account);
        UserLoginDTO userLoginDto = DTOConverter.convertToUserLoginDTO(userPo);
        return userLoginDto;
    }

    @Override
    public UserDTO getUserInfoByAccount(String account) {
        UserPO userPo = userMapper.getUserAllInfoByAccount(account);
        UserDTO userDTO = DTOConverter.convertToUserDTO(userPo);
        return userDTO;
    }

    @Override
    public String checkIfAccountExisted(String account) {
        return userMapper.checkIfAccountExisted(account);
    }

    @Override
    public void register(String account, String encryptedPassword) {
        String nickname = "一位卖家";
        String avater = "https://img1.baidu.com/it/u=1672450218,1606872685&fm=253&fmt=auto&app=120&f=JPEG?w=800&h=800";
        String phone = "";
        String description = "风一般的男子,没有留下一点痕迹";
        userMapper.register(
            account,
            encryptedPassword,
            nickname,
            avater,
            UserRoleEnum.SELLER.getCode(),
            phone,
            description
        );
    }

    @Override
    public void updateUserInfo(String account,UpdateUserInfoReq updateUserInfoReq) {
        userMapper.updateUserInfo(
                account,
                updateUserInfoReq.getNickname(),
                updateUserInfoReq.getAvatarUrl(),
                updateUserInfoReq.getPhone(),
                updateUserInfoReq.getDescription(),
                updateUserInfoReq.getWechat(),
                updateUserInfoReq.getQq(),
                updateUserInfoReq.getEmail()
        );
    }

    @Override
    public void updatePassword(String account, String newPassword) {
        userMapper.updatePassword(account,newPassword);
    }
}
