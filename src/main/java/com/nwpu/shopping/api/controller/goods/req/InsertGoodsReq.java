package com.nwpu.shopping.api.controller.goods.req;

import com.nwpu.shopping.domin.enums.GoodsCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InsertGoodsReq {
    private String name;
    private String description;
    private Double price;
    private long sellerId;
    private GoodsCategoryEnum category;
    private String coverUrl;

}
