package com.nwpu.shopping.api.controller.user.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class LoginReq {
    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, max = 20, message = "用户名长度要求在4-20")
    private String account;
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度要求在6-20")
    private String password;
}