package com.nwpu.shopping.infrastructure.user.persistence.mapper;

import com.nwpu.shopping.api.controller.user.req.UpdateUserInfoReq;
import com.nwpu.shopping.infrastructure.user.persistence.pojo.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author: feoyang
 * @date: 2024/10/29 17:56
 * @description: TODO
 */

@Mapper
public interface UserMapper {

    UserPO getUserAllInfoByAccount(String account);

    String checkIfAccountExisted(String account);

    void register(String account, String encryptedPassword, String nickname, String avatarUrl, int role,String phone, String description);

    UserPO findUserById(@Param("id") long id);

    int updateContactInfo(@Param("id") long id, @Param("contactInfo") String contactInfo);

    void updateUserInfo(@Param("account") String account,@Param("nickname") String nickname,@Param("avatarUrl") String avatarUrl,
                        @Param("phone") String phone,@Param("description") String description,@Param("wechat") String wechat,
                        @Param("qq") String qq,@Param("email") String email);

    void updatePassword(@Param("account") String account, @Param("password") String encryptedPassword);
}
