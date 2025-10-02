package com.nwpu.shopping.domin.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GoodsStatusEnum {
    @JsonProperty("onsale")
    ONSALE(1, "上架"),
    @JsonProperty("offsale")
    OFFSALE(0, "下架");

    private int code;
    private String status;

    GoodsStatusEnum(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    // 静态方法，通过 code 获取对应的枚举实例
    public static GoodsStatusEnum fromCode(int code) {
        for (GoodsStatusEnum statusEnum : GoodsStatusEnum.values()) {
            if (statusEnum.getCode() == code) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
