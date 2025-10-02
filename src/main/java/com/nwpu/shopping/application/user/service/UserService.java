package com.nwpu.shopping.application.user.service;

import com.nwpu.shopping.api.controller.user.req.LoginReq;
import com.nwpu.shopping.api.controller.user.req.RegisterUserReq;
import com.nwpu.shopping.api.controller.user.req.UpdateUserInfoReq;
import com.nwpu.shopping.api.controller.user.vo.UserLoginVO;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserDTO;

/**
 * @author: feoyang
 * @date: 2024/10/29 18:57
 * @description: TODO
 *<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
 *
 *
 * >>>>>>>>>>>>>>>>>>>>>>>>
 * 1
 *
 *
 *
 *
 *
 *
 *
 *
 * <<<<<<<<<<<<<<<<<<<<,
 */
public interface UserService {
    UserDTO getUserInfoByToken(String token);

    UserLoginVO login(LoginReq loginReq);

    void register(RegisterUserReq registerUserReq);

    void updateUserInfo(String token,UpdateUserInfoReq updateUserInfoReq);

    void updatePassword(String token,String newPassword);
}
