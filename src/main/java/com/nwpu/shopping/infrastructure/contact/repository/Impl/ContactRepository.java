package com.nwpu.shopping.infrastructure.contact.repository.Impl;


import com.nwpu.shopping.domin.enums.UserRoleEnum;
import com.nwpu.shopping.infrastructure.contact.repository.dto.ContactDTO;
import com.nwpu.shopping.infrastructure.user.persistence.mapper.UserMapper;
import com.nwpu.shopping.infrastructure.user.persistence.pojo.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {

    @Autowired
    private UserMapper userMapper;

    public UserRoleEnum findUserRoleById(long id) {
        UserPO user = userMapper.findUserById(id);

        // 用户不存在
        if (user == null) {
            return null; // 返回 null 表示用户不存在
        }

        // 用户存在，返回其角色代码对应的 UserRoleEnum
        return UserRoleEnum.fromCode(user.getRole());
    }


    public ContactDTO findUserContactById(long id) {
        UserPO user = userMapper.findUserById(id);
        if (user == null) {
            return null;
        }

        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(user.getId());
        contactDTO.setAccount(user.getAccount());
        contactDTO.setNickname(user.getNickname());
        contactDTO.setContactInfo(user.getContact());
        return contactDTO;
    }

    public int updateContactInfo(long id, String contactInfo) {
        return userMapper.updateContactInfo(id, contactInfo);
    }
}


