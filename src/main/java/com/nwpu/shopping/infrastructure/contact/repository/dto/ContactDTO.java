package com.nwpu.shopping.infrastructure.contact.repository.dto;


import lombok.Data;

@Data
public class ContactDTO {
    private long id;
    private String account;
    private String nickname;
    private String contactInfo;
}
