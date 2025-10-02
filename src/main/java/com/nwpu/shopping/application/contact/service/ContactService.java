package com.nwpu.shopping.application.contact.service;


import com.nwpu.shopping.infrastructure.contact.repository.dto.ContactDTO;

public interface ContactService {
    void updateContactInfo(long id, String contactInfo);
    ContactDTO getContactById(long id);
}
