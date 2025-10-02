package com.nwpu.shopping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwpu.shopping.api.controller.user.UserController;
import com.nwpu.shopping.api.controller.user.req.LoginReq;
import com.nwpu.shopping.api.controller.user.req.RegisterUserReq;
import com.nwpu.shopping.application.user.service.UserService;
import com.nwpu.shopping.domin.enums.UserRoleEnum;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author: feoyang
 * @date: 2024/10/29 19:38
 * @description: TODO
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    public void testRegister() {
        userController.register(
            new RegisterUserReq(
                "userYang",
                "test"
        ));
        System.out.println("register success");
    }


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testLogin() throws Exception {
        // 创建请求对象
        LoginReq loginReq = new LoginReq(
                "1123455",
                "123456"
        );

        // 将请求对象转换为 JSON 格式
        String requestBody = objectMapper.writeValueAsString(loginReq);

        // 使用 MockMvc 模拟调用接口
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserInfo() throws Exception {
        String mockAuth = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1c2VyWWFuZyIsImlhdCI6MTczMDIxODUyOSwiZXhwIjo0ODg1ODkyMTI5fQ.vYLGTslh8OqtpXn4xY4vDHTOgJ0eUCjfweyZARV8vZM";  // 模拟的token

        mockMvc.perform(get("/getUserInfo")
                        .header("authorization", mockAuth))
                .andExpect(status().isOk());
    }

    @Autowired
    UserService userService;

    @Test
    public void testUserServiceGetUserInfoByToken() {
        UserDTO userDTO = userService.getUserInfoByToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1c2VyWWFuZyIsImlhdCI6MTczMDIxODUyOSwiZXhwIjo0ODg1ODkyMTI5fQ.vYLGTslh8OqtpXn4xY4vDHTOgJ0eUCjfweyZARV8vZM");
        System.out.println(userDTO);
    }
}
