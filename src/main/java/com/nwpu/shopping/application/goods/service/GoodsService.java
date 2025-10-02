package com.nwpu.shopping.application.goods.service;

import com.nwpu.shopping.api.controller.goods.req.*;

import com.nwpu.shopping.api.controller.goods.req.InsertGoodsReq;
import com.nwpu.shopping.api.controller.goods.req.PageGoodsReq;
import com.nwpu.shopping.api.controller.goods.req.UpdateGoodsReq;
import com.nwpu.shopping.api.controller.goods.vo.ImgUrlVO;
import com.nwpu.shopping.infrastructure.common.paginate.PageResult;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsBasicDTO;

import com.nwpu.shopping.infrastructure.goods.persistence.pojo.GoodsPO;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsDTO;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsDetailsDTO;

import java.util.List;


/**
 * @author: feoyang
 * @date: 2024/10/29 18:57
 * @description: TODO
 *

 */
public interface GoodsService {

        public void addGoods(InsertGoodsReq insertGoodsReq);

        public void deleteGoods(long id);

        public void updateGoodsById(UpdateGoodsReq updateGoodsReq);


        public PageResult<GoodsBasicDTO> getGoodsByPageAndSearch(PageGoodsReq pageGoodsReq);

        public PageResult<GoodsBasicDTO> getSellerAllGoodsByPageAndSearch(PageGoodsSellerReq pageGoodsSellerReq);

        public GoodsDTO findInfoById(long id);

        public void changeStatus(long id);

        public GoodsDetailsDTO getGoodsDetailsDTO(long id);

        public List<ImgUrlVO> getImgUrl();

        public int getGoodsCountBySellerId(long id);
}
