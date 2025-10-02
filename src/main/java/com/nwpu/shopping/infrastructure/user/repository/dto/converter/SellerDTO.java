package com.nwpu.shopping.infrastructure.user.repository.dto.converter;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@ToString
public class SellerDTO {
    private long id;
    private String nickname;
    private String avatarUrl;
    private String phone;
    private String description;
    private String wechat;
    private String qq;
    private String email;
}
