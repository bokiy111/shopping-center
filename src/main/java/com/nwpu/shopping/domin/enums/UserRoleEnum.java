package com.nwpu.shopping.domin.enums;

/**
 * @author: feoyang
 * @date: 2024/10/29 21:13
 * @description: TODO
 */
public enum UserRoleEnum {
    ADMIN(1, "admin"),
    SELLER(2, "seller"),
    CUSTOMER(3, "customer");

    private int code;
    private String role;

    UserRoleEnum(int code, String role) {
        this.code = code;
        this.role = role;
    }

    public int getCode() {
        return code;
    }

    public String getRole() {
        return role;
    }

    // 静态方法，通过 code 获取对应的枚举实例
    public static UserRoleEnum fromCode(int code) {
        for (UserRoleEnum roleEnum : UserRoleEnum.values()) {
            if (roleEnum.getCode() == code) {
                return roleEnum;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
