package com.nwpu.shopping;

import com.nwpu.shopping.api.controller.goods.req.InsertGoodsReq;
import com.nwpu.shopping.api.controller.goods.req.PageGoodsReq;
import com.nwpu.shopping.application.goods.service.GoodsService;
import com.nwpu.shopping.domin.enums.GoodsCategoryEnum;
import com.nwpu.shopping.infrastructure.common.paginate.PageResult;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsBasicDTO;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.nwpu.shopping.domin.enums.GoodsCategoryEnum.BOOK;

@SpringBootTest
public class GoodsServiceTest {

    @Autowired
    private GoodsService goodsService;

    @Test
    //测试添加商品
    public void test() {
        InsertGoodsReq insertGoodsReq = new InsertGoodsReq(
                "铅笔", "测试", 10.1, 5l, BOOK, " "
        );
        goodsService.addGoods(insertGoodsReq);
        System.out.println("已完成");
    }

    @Test
    //测试删除商品
    public void test2() {

        goodsService.deleteGoods(46l);
        System.out.println("已完成deleteGoods");
    }

//    @Test
//    //测试更新商品
//    public void test3() {
//        UpdateGoodsReq updateGoodsReq = new UpdateGoodsReq(47l,"钢笔","一只普通的钢笔",15.0,5, GoodsStatusEnum.OFFSALE, BOOK);
//        goodsService.updateGoodsById(updateGoodsReq);
//
//        System.out.println("已完成updateGoods");
//
//    }

    @Test
    public void testGetGoodsByPage(){
        PageResult<GoodsBasicDTO> pageResult = goodsService.getGoodsByPageAndSearch(
                PageGoodsReq.builder().current(2).pageSize(10).build()
        );
        for (GoodsBasicDTO goodsBasicDTO : pageResult.getList()) {
            System.out.println(goodsBasicDTO.toString());
        }
    }

    @Test
//    @Test
//    public void testGetGoodsAndCategory(){
//
//        PageGoodsAndCategoryReq pageGoodsAndCategoryReq = PageGoodsAndCategoryReq.builder().current(1).pageSize(10).search("").goodsCategoryEnum(STATIONERY).build();
//        PageResult<GoodsBasicDTO> pageResult = goodsService.getGoodsAndCategoryByPageAndSearch(pageGoodsAndCategoryReq);
//        for (GoodsBasicDTO goodsBasicDTO : pageResult.getList()) {
//            System.out.println(goodsBasicDTO.getId());
//        }
//    }
    //测试根据id查询商品
    public void test4() {

        GoodsDTO goodsDTO =  goodsService.findInfoById(70);
        System.out.println(goodsDTO);

    }

    @Test
    //测试上下架商品
    public void test5() {

        goodsService.changeStatus(95);
        System.out.println("已完成changeStatus");
    }

    PageGoodsReq pq = new PageGoodsReq(null,GoodsCategoryEnum.fromCode(1));
    @Test
    public void testGetGoods() {
        PageResult<GoodsBasicDTO> pageResult = goodsService.getGoodsByPageAndSearch(
                pq
        );
        for (GoodsBasicDTO goodsBasicDTO : pageResult.getList()) {
            System.out.println(goodsBasicDTO.toString());
        }
    }

    @Test
    public void testCount() {
        int count = goodsService.getGoodsCountBySellerId(1l);
        System.out.println("========================");
        System.out.println(count);
    }
}
