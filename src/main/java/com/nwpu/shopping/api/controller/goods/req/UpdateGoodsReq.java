package com.nwpu.shopping.api.controller.goods.req;


import com.nwpu.shopping.domin.enums.GoodsCategoryEnum;
import com.nwpu.shopping.domin.enums.GoodsStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateGoodsReq {
    private long id;
    private String name;
    private String description;
    private Double price;
    private long sellerId;
    private GoodsStatusEnum status;
    private GoodsCategoryEnum category;
    private String coverUrl;

}
