package com.nwpu.shopping.infrastructure.goods.repository.dto.converter;

import com.nwpu.shopping.domin.enums.GoodsCategoryEnum;
import com.nwpu.shopping.domin.enums.GoodsStatusEnum;
import com.nwpu.shopping.infrastructure.goods.persistence.pojo.GoodsPO;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsBasicDTO;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsDTO;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsDetailsDTO;
import com.nwpu.shopping.infrastructure.user.persistence.pojo.UserPO;
import com.nwpu.shopping.infrastructure.user.repository.dto.converter.SellerDTO;

/**
 * @author: feoyang
 * @date: 2024/11/8 13:43
 * @description: TODO
 */

public class DTOConverter {
    public static GoodsDTO convertToGoodsDTO(GoodsPO goodsPO){
        return GoodsDTO.builder()
                .id(goodsPO.getId())
                .name(goodsPO.getName())
                .price(goodsPO.getPrice())
                .description(goodsPO.getDescription())
                .sellerId(goodsPO.getSellerId())
                .status(GoodsStatusEnum.fromCode(goodsPO.getStatus()))
                .category(GoodsCategoryEnum.fromCode(goodsPO.getCategory()))
                .coverUrl(goodsPO.getCoverUrl())
                .build();

    }

    public static GoodsBasicDTO convertGoodsPOToGoodsBasicDTO(GoodsPO goodsPO) {
        String description = goodsPO.getDescription();
        String basicDescription = description.substring(0, Math.min(20, description.length()));
        return new GoodsBasicDTO(
                goodsPO.getId(),
                goodsPO.getName(),
                goodsPO.getPrice(),
                basicDescription,
                goodsPO.getCoverUrl()
        );
    }



    public static GoodsDetailsDTO convertToGoodsDetailsDTO(GoodsPO goodsPO, SellerDTO seller) {
        return new GoodsDetailsDTO(
                goodsPO.getId(),
                goodsPO.getName(),
                goodsPO.getPrice(),
                goodsPO.getDescription(),
                goodsPO.getCreateTime(),
                goodsPO.getUpdateTime(),
                GoodsStatusEnum.fromCode(goodsPO.getStatus()),
                GoodsCategoryEnum.fromCode(goodsPO.getCategory()),
                goodsPO.getCoverUrl(),
                seller
        );
    }

    public static SellerDTO convertUserPOToSellerDTO(UserPO userPO) {
        return new SellerDTO(
                userPO.getId(),
                userPO.getNickname(),
                userPO.getAvatarUrl(),
                userPO.getPhone(),
                userPO.getDescription(),
                userPO.getWechat(),
                userPO.getQq(),
                userPO.getEmail()
        );


    }



}
