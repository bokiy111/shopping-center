package com.nwpu.shopping.api.controller.user;

import com.nwpu.shopping.api.controller.user.req.LoginReq;
import com.nwpu.shopping.api.controller.user.req.RegisterUserReq;
import com.nwpu.shopping.api.controller.user.req.UpdateUserInfoReq;
import com.nwpu.shopping.api.controller.user.vo.UserLoginVO;
import com.nwpu.shopping.application.contact.service.ContactService;
import com.nwpu.shopping.application.user.service.UserService;
import com.nwpu.shopping.infrastructure.contact.repository.dto.ContactDTO;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author: feoyang
 * @date: 2024/10/29 18:50
 * @description: TODO
 */
@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    private static final String TOKEN_IN_HEADER = "authorization";


    @PostMapping("/login")
    @ResponseBody
    public UserLoginVO login(@RequestBody LoginReq loginReq, HttpServletResponse httpServletResponse) {
        UserLoginVO userLoginVO = userService.login(loginReq);
        httpServletResponse.setHeader(TOKEN_IN_HEADER, "Bearer " + userLoginVO.getToken());
        return userLoginVO;
    }

    @PostMapping("/register")
    @ResponseBody
    public void register(@RequestBody RegisterUserReq registerUserReq) {
        userService.register(registerUserReq);
    }

    @GetMapping("/seller/getUserInfo")
    @ResponseBody
    public UserDTO getUserInfo(@RequestHeader(TOKEN_IN_HEADER )String auth) {
        String[] elements = auth.split(" ");
        UserDTO userDTO = userService.getUserInfoByToken(elements[1]);
        return userDTO;
    }

    @Autowired
    private ContactService contactService;

    // 更新商家联系方式
    @PostMapping("/seller/updateContactInfo")
    public void  updateContactInfo(
            @RequestParam long id, // 使用 @RequestParam
            @RequestBody ContactDTO contactDTO) {
        contactService.updateContactInfo(id, contactDTO.getContactInfo());

    }

    // 用户获取商家信息
    @GetMapping("/getSellerContact")
    public ContactDTO getContactById(@RequestParam long id) { // 使用 @RequestParam
        return contactService.getContactById(id);
    }

    @PostMapping("/updateUserInfo")
    @ResponseBody
    public void updateUserInfo(@RequestHeader(TOKEN_IN_HEADER )String auth,@RequestBody UpdateUserInfoReq UpdateUserInfoReq) {
        String[] elements = auth.split(" ");
        userService.updateUserInfo(elements[1],UpdateUserInfoReq);
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public void updatePassword(@RequestHeader(TOKEN_IN_HEADER )String auth,
                               @Length(min = 6, max = 20, message = "密码长度要求在6-20")
                               @RequestParam String newPassword){
        String[] elements = auth.split(" ");
        userService.updatePassword(elements[1],newPassword);
    }

}
