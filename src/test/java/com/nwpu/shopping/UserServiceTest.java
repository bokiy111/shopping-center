package com.nwpu.shopping;

import com.nwpu.shopping.api.controller.user.req.LoginReq;
import com.nwpu.shopping.api.controller.user.req.RegisterUserReq;
import com.nwpu.shopping.application.user.service.UserService;
import com.nwpu.shopping.domin.enums.UserRoleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;


    @Test
    public void registerUser() {
        RegisterUserReq req = new RegisterUserReq("1123455","123456");
        userService.register(req);
        System.out.println("=============================");
    }

    @Test
    public void test() {

        LoginReq loginReq = new LoginReq("1123455","123456");
        userService.login(loginReq);
        System.out.println("=============================");
    }

}
