package com.nwpu.shopping.domin.enums;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum GoodsCategoryEnum {
    @JsonProperty("BOOK")
    BOOK(1, "书籍教材"),
    @JsonProperty("SPORTS")
    SPORTS(2, "体育运动"),
    @JsonProperty("BEAUTY")
    BEAUTY(3, "美妆"),
    @JsonProperty("ELECTRONIC")
    ELECTRONIC(4, "电子产品"),
    @JsonProperty("DAILY")
    DAILY(5, "日用百货"),
    @JsonProperty("CLOTHING")
    CLOTHING(6, "服装鞋饰"),
    @JsonProperty("FOOD")
    FOOD(7, "饮料食品"),
    @JsonProperty("OTHER")
    OTHER(0, "其他");



    private int code;
    private String category;

    GoodsCategoryEnum(int code, String category) {
        this.code = code;
        this.category = category;
    }

    public int getCode() {
        return code;
    }


    public String getCategory() {
        return category;
    }

    // 静态方法，通过 code 获取对应的枚举实例
    public static GoodsCategoryEnum fromCode(int code) {
        for (GoodsCategoryEnum categoryEnum : GoodsCategoryEnum.values()) {
            if (categoryEnum.getCode() == code) {
                return categoryEnum;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
