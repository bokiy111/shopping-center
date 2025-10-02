package com.nwpu.shopping.infrastructure.goods.persistence.mapper;

import com.github.pagehelper.Page;
import com.nwpu.shopping.api.controller.goods.req.InsertGoodsReq;
import com.nwpu.shopping.api.controller.goods.req.UpdateGoodsReq;
import com.nwpu.shopping.infrastructure.goods.persistence.pojo.GoodsPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsMapper {

    public void addGoods(InsertGoodsReq insertGoodsReq);

    public void deleteGoodsById(long id);

    public void updateGoodsById(UpdateGoodsReq updateGoodsReq);

    public Page<GoodsPO> getGoodsByPageAndSearch(@Param("offset") int offset, @Param("limit") int limit, @Param("search") String search,@Param("category") Integer category);

    public void addGoods(String name,Double price,String description,long sellerId,int category,String coverUrl);

    public void updateGoodsById(long id,String name,String description,Double price ,long sellerId,int status,int category,String coverUrl);

    public GoodsPO findInfoById(long id);

    public void changeStatus(long id);

    public Page<GoodsPO> getSellerAllGoodsByPageAndSearch(@Param("offset") int offset, @Param("limit") int pageSize, @Param("search") String search,@Param("sellerId") long sellerId);

    public String findPhoneById(long id);

    int getGoodsCountBySellerId(@Param("id") long id);

}
