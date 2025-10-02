package com.nwpu.shopping.api.controller.user.req;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserInfoReq {
    String nickname;
    String avatarUrl;
    String phone;
    String description;
    String wechat;
    String qq;
    String email;
}
