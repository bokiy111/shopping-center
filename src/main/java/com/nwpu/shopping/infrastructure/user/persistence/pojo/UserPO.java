package com.nwpu.shopping.infrastructure.user.persistence.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: feoyang
 * @date: 2024/10/29 17:49
 * @description: TODO
 */

@Data
public class UserPO {
    private long id;
    private String account;
    private String encryptedPassword;
    private String nickname;
    private String avatarUrl;
    private int role;
    private String contact;
    private String createTime;
    private String updateTime;
    private String phone;
    private String description;
    private String wechat;
    private String qq;
    private String email;
}
