package com.nwpu.shopping.infrastructure.user.repository;

import com.nwpu.shopping.api.controller.user.req.UpdateUserInfoReq;
import com.nwpu.shopping.domin.enums.UserRoleEnum;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserDTO;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserLoginDTO;

/**
 * @author: feoyang
 * @date: 2024/10/29 21:01
 * @description: TODO
 */
public interface UserRepository {
    UserLoginDTO getUserInfoWithEntryedPasswordByAccount(String account);

    UserDTO getUserInfoByAccount(String account);

    String checkIfAccountExisted(String account);

    void register(String account, String encryptedPassword);

    void updateUserInfo(String account,UpdateUserInfoReq updateUserInfoReq);

    void updatePassword(String account,String newPassword);
}
