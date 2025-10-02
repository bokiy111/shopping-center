package com.nwpu.shopping.api.controller.goods.req;

import com.nwpu.shopping.api.common.searchPage.CommonListReq;
import com.nwpu.shopping.domin.enums.GoodsCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.checkerframework.checker.units.qual.A;

/**
 * @author: feoyang
 * @date: 2024/11/8 13:13
 * @description: TODO
 */
@Data
@AllArgsConstructor
@SuperBuilder
public class PageGoodsReq extends CommonListReq {
    private String search;
    private GoodsCategoryEnum category;
}
