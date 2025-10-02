package com.nwpu.shopping.api.controller.goods.req;

import com.nwpu.shopping.api.common.searchPage.CommonListReq;
import com.nwpu.shopping.domin.enums.GoodsCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class PageGoodsAndCategoryReq extends CommonListReq {
    private String search;
    private GoodsCategoryEnum category;
}
