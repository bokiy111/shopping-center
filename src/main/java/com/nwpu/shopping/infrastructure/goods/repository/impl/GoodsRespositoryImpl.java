package com.nwpu.shopping.infrastructure.goods.repository.impl;

import com.github.pagehelper.Page;
import com.nwpu.shopping.api.controller.goods.req.*;

import com.nwpu.shopping.api.controller.goods.req.InsertGoodsReq;
import com.nwpu.shopping.api.controller.goods.req.PageGoodsReq;
import com.nwpu.shopping.api.controller.goods.req.UpdateGoodsReq;
import com.nwpu.shopping.infrastructure.common.paginate.PageResult;
import com.nwpu.shopping.infrastructure.goods.persistence.mapper.GoodsMapper;
import com.nwpu.shopping.infrastructure.goods.persistence.pojo.GoodsPO;
import com.nwpu.shopping.infrastructure.goods.repository.GoodsRepository;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsBasicDTO;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsDetailsDTO;
import com.nwpu.shopping.infrastructure.goods.repository.dto.converter.DTOConverter;
import com.nwpu.shopping.infrastructure.user.persistence.mapper.UserMapper;
import com.nwpu.shopping.infrastructure.user.persistence.pojo.UserPO;
import com.nwpu.shopping.infrastructure.user.repository.dto.converter.SellerDTO;

import com.nwpu.shopping.domin.enums.GoodsCategoryEnum;
import com.nwpu.shopping.domin.enums.GoodsStatusEnum;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodsRespositoryImpl implements GoodsRepository {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertGoods(InsertGoodsReq insertGoodsReq){

        String name = insertGoodsReq.getName();
        String description = insertGoodsReq.getDescription();
        Double price = insertGoodsReq.getPrice();
        long sellerId = insertGoodsReq.getSellerId();
        GoodsCategoryEnum category = insertGoodsReq.getCategory();
        String coverUrl = insertGoodsReq.getCoverUrl();
        goodsMapper.addGoods(
                name,
                price,
                description,
                sellerId,
                category.getCode(),
                coverUrl
        );

    }

    @Override
    public void deleteGoodsById(long id){

        goodsMapper.deleteGoodsById(id);

    }

    @Override
    public void updateGoods(UpdateGoodsReq updateGoodsReq) {
        long id = updateGoodsReq.getId();
        String name = updateGoodsReq.getName();
        String description = updateGoodsReq.getDescription();
        Double price = updateGoodsReq.getPrice();
        long sellerId = updateGoodsReq.getSellerId();
        GoodsStatusEnum status = updateGoodsReq.getStatus();
        GoodsCategoryEnum category = updateGoodsReq.getCategory();
        String coverUrl = updateGoodsReq.getCoverUrl();
        goodsMapper.updateGoodsById(
                id,name,description,price,sellerId,
                status.getCode(),
                category.getCode(),
                coverUrl
        );

    }

    @Override
    public GoodsDTO findInfoById(long id) {

        GoodsPO goodsPO = goodsMapper.findInfoById(id);
        GoodsDTO goodsDTO = DTOConverter.convertToGoodsDTO(goodsPO);
        return goodsDTO;

    }

    @Override
    public void changeStatus(long id) {
        goodsMapper.changeStatus(id);
    }


    @Override
    public PageResult<GoodsBasicDTO> getSellerAllGoodsByPageAndSearch(PageGoodsSellerReq pageGoodsSellerReq) {
        Page<GoodsPO> page = goodsMapper.getSellerAllGoodsByPageAndSearch(
                (pageGoodsSellerReq.getCurrent()-1)*pageGoodsSellerReq.getPageSize(),
                pageGoodsSellerReq.getPageSize(), pageGoodsSellerReq.getSearch(),
                pageGoodsSellerReq.getSellerId());
        List<GoodsBasicDTO> list = page.getResult().stream().map(DTOConverter::convertGoodsPOToGoodsBasicDTO).toList();
        return PageResult.of(page, list);
    }

    @Override
    public GoodsDetailsDTO getGoodsDetailsDTO(long id) {
        GoodsPO goodsPO = goodsMapper.findInfoById(id);
        long sellerId = goodsPO.getSellerId();

        UserPO seller = userMapper.findUserById(sellerId);
        SellerDTO sellerDTO = DTOConverter.convertUserPOToSellerDTO(seller);

        GoodsDetailsDTO goodsDetailsDTO = DTOConverter.convertToGoodsDetailsDTO(goodsPO,sellerDTO);

        return goodsDetailsDTO;
    }

    @Override
    public PageResult<GoodsBasicDTO> getGoodsByPageAndSearch(PageGoodsReq pageGoodsReq){
        GoodsCategoryEnum category = pageGoodsReq.getCategory();

        if(pageGoodsReq.getCategory()!=null) {
            Page<GoodsPO> page = goodsMapper.getGoodsByPageAndSearch(
                    (pageGoodsReq.getCurrent() - 1) * pageGoodsReq.getPageSize(),
                    pageGoodsReq.getPageSize(),
                    pageGoodsReq.getSearch(),
                    category.getCode());
            List<GoodsBasicDTO> list = page.getResult().stream().map(DTOConverter::convertGoodsPOToGoodsBasicDTO).toList();
            return PageResult.of(page, list);
        }
        else {
            Page<GoodsPO> page = goodsMapper.getGoodsByPageAndSearch(
                    (pageGoodsReq.getCurrent() - 1) * pageGoodsReq.getPageSize(),
                    pageGoodsReq.getPageSize(),
                    pageGoodsReq.getSearch(),
                    null);
            List<GoodsBasicDTO> list = page.getResult().stream().map(DTOConverter::convertGoodsPOToGoodsBasicDTO).toList();
            return PageResult.of(page, list);
        }
    }

    @Override
    public int getGoodsCountBySellerId(long id){
        return goodsMapper.getGoodsCountBySellerId(id);
    }
}