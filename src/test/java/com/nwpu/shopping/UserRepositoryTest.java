package com.nwpu.shopping;

import com.nwpu.shopping.infrastructure.user.repository.UserRepository;
import com.nwpu.shopping.infrastructure.user.repository.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: feoyang
 * @date: 2024/11/16 10:50
 * @description: TODO
 */
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void test() {
        UserDTO userLoginDto = userRepository
                .getUserInfoWithEntryedPasswordByAccount("feoyang1");
        System.out.println("===============================");
        System.out.println(userLoginDto.toString());

    }
}
