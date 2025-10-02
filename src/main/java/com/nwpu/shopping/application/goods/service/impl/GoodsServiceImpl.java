package com.nwpu.shopping.application.goods.service.impl;


import com.nwpu.shopping.api.controller.goods.req.*;
import com.nwpu.shopping.api.controller.goods.vo.ImgUrlVO;
import com.nwpu.shopping.application.goods.service.GoodsService;
import com.nwpu.shopping.domin.enums.GoodsCategoryImgUrl;
import com.nwpu.shopping.infrastructure.common.paginate.PageResult;
import com.nwpu.shopping.infrastructure.goods.repository.GoodsRepository;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsBasicDTO;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsDTO;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public void addGoods(InsertGoodsReq insertGoodsReq) {goodsRepository.insertGoods(insertGoodsReq);}

    @Override
    public void deleteGoods(long id) {
        goodsRepository.deleteGoodsById(id);
    }

    @Override
    public void updateGoodsById(UpdateGoodsReq updateGoodsReq) {
        goodsRepository.updateGoods(updateGoodsReq);
    }

    @Override
    public PageResult<GoodsBasicDTO> getGoodsByPageAndSearch(PageGoodsReq pageGoodsReq) {
        return goodsRepository.getGoodsByPageAndSearch(pageGoodsReq);
    }

    @Override
    public PageResult<GoodsBasicDTO> getSellerAllGoodsByPageAndSearch(PageGoodsSellerReq pageGoodsSellerReq) {
        return goodsRepository.getSellerAllGoodsByPageAndSearch(pageGoodsSellerReq);
    }

    public GoodsDTO findInfoById(long id) {return goodsRepository.findInfoById(id);}

    @Override
    public void changeStatus(long id) {
        goodsRepository.changeStatus(id);
    }

    @Override
    public GoodsDetailsDTO getGoodsDetailsDTO(long id) {
        return goodsRepository.getGoodsDetailsDTO(id);
    }

    @Override
    public List<ImgUrlVO> getImgUrl() {
        ImgUrlVO bookImgUrlVO = new ImgUrlVO("book", GoodsCategoryImgUrl.BOOK_URL);
        ImgUrlVO sportsImgUrlVO = new ImgUrlVO("sports",GoodsCategoryImgUrl.SPORTS_URL);
        ImgUrlVO beautyImgUrlVO = new ImgUrlVO("beauty",GoodsCategoryImgUrl.BEAUTY_URL);
        ImgUrlVO electronicImgUrlVO = new ImgUrlVO("electronic",GoodsCategoryImgUrl.ELECTRONIC_URL);
        ImgUrlVO dailyImgUrlVO = new ImgUrlVO("daily",GoodsCategoryImgUrl.DAILY_URL);
        ImgUrlVO clothingImgUrlVO = new ImgUrlVO("clothing",GoodsCategoryImgUrl.CLOTHING_URL);
        ImgUrlVO foodImgUrlVO = new ImgUrlVO("food",GoodsCategoryImgUrl.FOOD_URL);
        ImgUrlVO otherImgUrlVO = new ImgUrlVO("other",GoodsCategoryImgUrl.OTHER_URL);

        List<ImgUrlVO> imgUrlVOList = new ArrayList<>();
        imgUrlVOList.add(bookImgUrlVO);
        imgUrlVOList.add(sportsImgUrlVO);
        imgUrlVOList.add(beautyImgUrlVO);
        imgUrlVOList.add(electronicImgUrlVO);
        imgUrlVOList.add(dailyImgUrlVO);
        imgUrlVOList.add(clothingImgUrlVO);
        imgUrlVOList.add(foodImgUrlVO);
        imgUrlVOList.add(otherImgUrlVO);
        return imgUrlVOList;
    }

    @Override
    public int getGoodsCountBySellerId(long id) {
        return goodsRepository.getGoodsCountBySellerId(id);
    }
}
