package com.nwpu.shopping.api.controller.goods;

import com.nwpu.shopping.api.controller.goods.req.*;
import com.nwpu.shopping.api.controller.goods.vo.ImgUrlVO;
import com.nwpu.shopping.application.goods.service.GoodsService;
import com.nwpu.shopping.infrastructure.common.paginate.PageResult;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsBasicDTO;

import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsDTO;

import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/seller/insert")
    public void addGoods(@RequestBody InsertGoodsReq insertGoodsReq){
        goodsService.addGoods(insertGoodsReq);
    }

    @DeleteMapping("/seller/deleteGoods")
    public void deleteGoods(@RequestParam("id") long id){
        goodsService.deleteGoods(id);
    }

    @PostMapping("/seller/updateGoods")
    public void updateGoods(@RequestBody UpdateGoodsReq updateGoodsReq){
        goodsService.updateGoodsById(updateGoodsReq);
    }

    @GetMapping("/getGoodsByPage")
    public PageResult<GoodsBasicDTO> getGoods(@ModelAttribute PageGoodsReq pageGoodsReq){
        return goodsService.getGoodsByPageAndSearch(pageGoodsReq);
    }

    @GetMapping("/seller/getSellerAllGoodsByPageAndSearch")
    public PageResult<GoodsBasicDTO> getSellerAllGoodsByPageAndSearch(@ModelAttribute PageGoodsSellerReq pageGoodsSellerReq){
        return goodsService.getSellerAllGoodsByPageAndSearch(pageGoodsSellerReq);
    }

    @GetMapping("/goodsInfo")
    //根据id查询商品信息
    public GoodsDTO getGoodsInfo(@RequestParam("id") long id){
        return goodsService.findInfoById(id);
    }

    @PostMapping("/seller/changeStatus")
    //更改商品的上下架状态
    public void changeStatus(@RequestParam("id") long id){
        goodsService.changeStatus(id);
    }

    @GetMapping("/getGoodsBasicDTO")
    public GoodsDetailsDTO getGoodsDetailsDTO(@RequestParam("id") long id){
        return goodsService.getGoodsDetailsDTO(id);

    }

    @GetMapping("/getImgUrl")
    public List<ImgUrlVO> getImgUrl(){
        return goodsService.getImgUrl();
    }

    @GetMapping("/seller/getGoodsCountBySellerId")
    public int getGoodsCountBySellerId(@RequestParam("id") long id){
        return goodsService.getGoodsCountBySellerId(id);
    }
}

