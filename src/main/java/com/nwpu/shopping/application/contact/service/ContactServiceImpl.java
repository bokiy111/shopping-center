package com.nwpu.shopping.application.contact.service;

import com.nwpu.shopping.domin.enums.UserRoleEnum;
import com.nwpu.shopping.infrastructure.contact.repository.Impl.ContactRepository;
import com.nwpu.shopping.infrastructure.contact.repository.dto.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void updateContactInfo(long id, String contactInfo) {
        UserRoleEnum role = contactRepository.findUserRoleById(id);

        if (role == null) {
            throw new RuntimeException("用户不存在");
        }

        if (role != UserRoleEnum.SELLER) {
            throw new RuntimeException("用户不是商家，无法更新联系方式");
        }

        contactRepository.updateContactInfo(id, contactInfo);

    }

    @Override
    public ContactDTO getContactById(long id) {
        UserRoleEnum role = contactRepository.findUserRoleById(id);

        if (role == null) {
            throw new RuntimeException("用户不存在");
        }

        if (role != UserRoleEnum.SELLER) {
            throw new RuntimeException("用户不是商家");
        }

        return contactRepository.findUserContactById(id);
    }


}
