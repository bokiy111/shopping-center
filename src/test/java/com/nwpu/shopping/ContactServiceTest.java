package com.nwpu.shopping;

import com.nwpu.shopping.application.contact.service.ContactService;
import com.nwpu.shopping.domin.enums.UserRoleEnum;
import com.nwpu.shopping.infrastructure.user.persistence.mapper.UserMapper;
import com.nwpu.shopping.infrastructure.user.persistence.pojo.UserPO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @MockBean
    private UserMapper userMapper;

    // 定义测试所需的用户ID
    private final long nonExistentUserId = 9999;
    private final long nonSellerUserId = 1001;
    private final long sellerUserId = 1002;

    @BeforeEach
    public void setup() {
        // 模拟找不到的用户返回 null
        when(userMapper.findUserById(nonExistentUserId)).thenReturn(null);

        // 模拟存在但不是商家的用户（比如角色为 CUSTOMER）
        UserPO nonSellerUser = new UserPO();
        nonSellerUser.setId(nonSellerUserId);
        nonSellerUser.setRole(UserRoleEnum.CUSTOMER.getCode()); // 非商家角色
        when(userMapper.findUserById(nonSellerUserId)).thenReturn(nonSellerUser);

        // 模拟存在并且是商家的用户
        UserPO sellerUser = new UserPO();
        sellerUser.setId(sellerUserId);
        sellerUser.setRole(UserRoleEnum.SELLER.getCode()); // 商家角色
        when(userMapper.findUserById(sellerUserId)).thenReturn(sellerUser);
    }

    @Test
    public void testGetContactById_UserNotSeller() {
        // 测试当用户存在但不是商家时的行为
        Exception exception = assertThrows(RuntimeException.class, () -> {
            contactService.getContactById(nonSellerUserId);
        });
        assertEquals("用户不是商家", exception.getMessage());
    }

    @Test
    public void testGetContactById_UserDoesNotExist() {
        // 测试当用户不存在时的行为
        Exception exception = assertThrows(RuntimeException.class, () -> {
            contactService.getContactById(nonExistentUserId);
        });
        assertEquals("用户不存在", exception.getMessage());
    }

    @Test
    public void testUpdateContactInfo_UserNotSeller() {
        // 测试当用户存在但不是商家时更新联系方式的行为
        Exception exception = assertThrows(RuntimeException.class, () -> {
            contactService.updateContactInfo(nonSellerUserId, "New Contact Info");
        });
        assertEquals("用户不是商家，无法更新联系方式", exception.getMessage());
    }

    @Test
    public void testUpdateContactInfo_UserDoesNotExist() {
        // 测试当用户不存在时更新联系方式的行为
        Exception exception = assertThrows(RuntimeException.class, () -> {
            contactService.updateContactInfo(nonExistentUserId, "New Contact Info");
        });
        assertEquals("用户不存在", exception.getMessage());
    }

    @Test
    public void testUpdateContactInfo_Success() {
        // 测试当用户是商家时成功更新联系方式
        assertDoesNotThrow(() -> {
            contactService.updateContactInfo(sellerUserId, "New Contact Info");
        });
    }
}
