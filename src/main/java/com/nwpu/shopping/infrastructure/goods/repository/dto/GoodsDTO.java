package com.nwpu.shopping.infrastructure.goods.repository.dto;


import com.nwpu.shopping.domin.enums.GoodsCategoryEnum;
import com.nwpu.shopping.domin.enums.GoodsStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class GoodsDTO {

    private long id;
    private String name;
    private Double price;
    private String description;
    private long sellerId;
    private GoodsStatusEnum status;
    private GoodsCategoryEnum category;
    private String coverUrl;

}
