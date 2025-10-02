package com.nwpu.shopping.api.controller.user.req;

import com.nwpu.shopping.domin.enums.UserRoleEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class RegisterUserReq {
    @Length(min = 6, max = 18, message = "用户名长度要求在6-20")
    private String account;
    @Length(min = 6, max = 20, message = "密码长度要求在6-20")
    private String password;
}
