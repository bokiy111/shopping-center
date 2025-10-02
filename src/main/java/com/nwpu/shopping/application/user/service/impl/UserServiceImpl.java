package com.nwpu.shopping.application.user.service.impl;

import com.nwpu.shopping.api.common.security.UserAuthProvider;
import com.nwpu.shopping.api.controller.user.req.LoginReq;
import com.nwpu.shopping.api.controller.user.req.RegisterUserReq;
import com.nwpu.shopping.api.controller.user.req.UpdateUserInfoReq;
import com.nwpu.shopping.api.controller.user.vo.UserLoginVO;
import com.nwpu.shopping.application.user.converter.UserVOConverter;
import com.nwpu.shopping.application.user.service.UserService;
import com.nwpu.shopping.domin.common.exception.BusinessException;
import com.nwpu.shopping.domin.common.exception.ExceptionType;
import com.nwpu.shopping.infrastructure.user.repository.UserRepository;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserDTO;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author: feoyang
 * @date: 2024/10/29 18:57
 * @description: TODO
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthProvider userAuthProvider;
    private final BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();


    @Override
    public UserLoginVO login (LoginReq loginReq) {
        UserLoginDTO userLoginDto;
        try {
            userLoginDto = userRepository
                    .getUserInfoWithEntryedPasswordByAccount(loginReq.getAccount());
        } catch (NullPointerException e) {
            throw new BusinessException(ExceptionType.USER_NOT_FOUND);
        }

        // 验证输入密码和加密密码是否匹配
        if (!bcryptPasswordEncoder.matches(loginReq.getPassword(), userLoginDto.getEncryptedPassword())) {
            throw new BusinessException(ExceptionType.PASSWORD_ERROR);
        }
        UserLoginVO userLoginVO = UserVOConverter.convertToUserLoginVO(
                userLoginDto,
                userAuthProvider.createToken(loginReq.getAccount())
        );
        return userLoginVO;
    }

    @Override
    public void register(RegisterUserReq registerUserReq){
        if (!userRepository.checkIfAccountExisted(registerUserReq.getAccount()).equals("0")) {
            throw new BusinessException(ExceptionType.USERNAME_EXISTED);
        }
        userRepository.register(
                registerUserReq.getAccount(),
                bcryptPasswordEncoder.encode(registerUserReq.getPassword())
        );
    }

    @Override
    public void updateUserInfo(String token,UpdateUserInfoReq updateUserInfoReq) {
        String account = userAuthProvider.getAccountByToken(token);
        userRepository.updateUserInfo(account,updateUserInfoReq);
    }

    @Override
    public void updatePassword(String token, String newPassword) {

        userRepository.updatePassword(
                userAuthProvider.getAccountByToken(token),
                bcryptPasswordEncoder.encode(newPassword)
        );
    }

    public UserDTO getUserInfoByToken(String token) {
        String account = userAuthProvider.getAccountByToken(token);
        return userRepository.getUserInfoByAccount(account);
    }
}