package com.nwpu.shopping.infrastructure.goods.repository;

import com.nwpu.shopping.api.controller.goods.req.*;
import com.nwpu.shopping.infrastructure.common.paginate.PageResult;
import com.nwpu.shopping.infrastructure.goods.persistence.pojo.GoodsPO;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsBasicDTO;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsDTO;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsDetailsDTO;

public interface GoodsRepository{

    public void insertGoods(InsertGoodsReq insertGoodsReq);

    public void deleteGoodsById(long id);

    public void updateGoods(UpdateGoodsReq updateGoodsReq);


    public PageResult<GoodsBasicDTO> getGoodsByPageAndSearch(PageGoodsReq pageGoodsReq);
    public GoodsDTO findInfoById(long id);

    public void changeStatus(long id);

    public PageResult<GoodsBasicDTO> getSellerAllGoodsByPageAndSearch(PageGoodsSellerReq pageGoodsSellerReq);

    public GoodsDetailsDTO getGoodsDetailsDTO(long id);

    public int getGoodsCountBySellerId(long id);

}
