package com.nwpu.shopping.infrastructure.goods.repository.dto;

import com.nwpu.shopping.domin.enums.GoodsCategoryEnum;
import com.nwpu.shopping.domin.enums.GoodsStatusEnum;
import com.nwpu.shopping.infrastructure.user.persistence.pojo.UserPO;
import com.nwpu.shopping.infrastructure.user.repository.dto.converter.SellerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoodsDetailsDTO {
    private long id;
    private String name;
    private Double price;
    private String description;
    private String createTime;
    private String updateTime;
    private GoodsStatusEnum status;
    private GoodsCategoryEnum category;
    private String coverUrl;
    private SellerDTO seller;
}
