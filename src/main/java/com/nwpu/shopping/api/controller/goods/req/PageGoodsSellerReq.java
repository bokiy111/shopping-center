package com.nwpu.shopping.api.controller.goods.req;

import com.nwpu.shopping.api.common.searchPage.CommonListReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class PageGoodsSellerReq extends CommonListReq {
    private String search;
    private long sellerId;
}
