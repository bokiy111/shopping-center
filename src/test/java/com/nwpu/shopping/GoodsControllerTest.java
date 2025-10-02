package com.nwpu.shopping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwpu.shopping.api.controller.goods.req.InsertGoodsReq;
import com.nwpu.shopping.api.controller.goods.req.PageGoodsReq;
import com.nwpu.shopping.api.controller.goods.req.UpdateGoodsReq;
import com.nwpu.shopping.domin.enums.GoodsCategoryEnum;
import com.nwpu.shopping.domin.enums.GoodsStatusEnum;
import com.nwpu.shopping.infrastructure.common.paginate.PageResult;
import com.nwpu.shopping.infrastructure.goods.repository.dto.GoodsBasicDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GoodsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void testInsert() throws Exception {
        String mockAuth = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiIxMTIzNDU1IiwiaWF0IjoxNzMwNTMzMDY0LCJleHAiOjQ4ODYyMDY2NjR9.FWzfSj-llKTXwZsHV8cpO67AJg5RdaIHPXkzX2XMk2s";

        InsertGoodsReq insertGoodsReq = new InsertGoodsReq("玩具","一个普通的玩具",101.1,5l, GoodsCategoryEnum.BOOK, " ");

        String requestBody = objectMapper.writeValueAsString(insertGoodsReq);

        mockMvc.perform(post("/api/insert")
                        .header("authorization", mockAuth)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());

        System.out.println("++++++++++++++++++++++===");
    }


    @Test
    //测试更新商品信息
    public void testUpdate() throws Exception {
        String mockAuth = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJpc3MiOiIxMTIzNDU1IiwiaWF0IjoxNzMwOTc5NDI2LCJleHAiOjQ4ODY2NTMwMjZ9." +
                "hdd9BJ86MUFpsPxwy7ZMUrwu7eZ7IC74a4Kyt6VFXrs";

        UpdateGoodsReq updateGoodsReq = new UpdateGoodsReq(48l,"毛笔","一只普通的毛笔",15.0,5, GoodsStatusEnum.OFFSALE, GoodsCategoryEnum.BOOK, " ");

        String requestBody = objectMapper.writeValueAsString(updateGoodsReq);

        mockMvc.perform(post("/api/updateGoods")
                        .header("authorization", mockAuth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        System.out.println("=========================");
    }


    @Test
    //测试删除商品信息
    public void testDelete() throws Exception {
        String mockAuth = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJpc3MiOiIxMTIzNDU1IiwiaWF0IjoxNzMwOTgwNTg5LCJleHAiOjQ4ODY2NTQxODl9." +
                "Fjh4EScvxY7C7wPH7OtooZojZpgZlna9TtQhzTpRaTo";

        Long id = 51l;

        mockMvc.perform(delete("/api/deleteGoods")
                        .header("authorization", mockAuth)
                        .param("id", String.valueOf(id)))
                .andExpect(status().isOk());

        System.out.println("===========================");

    }

    @Test
    //测试根据id查询商品详细信息
    public void testFindInfoById() throws Exception {

        String mockAuth = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJpc3MiOiIxMTIzNDU1IiwiaWF0IjoxNzMwOTgwNTg5LCJleHAiOjQ4ODY2NTQxODl9." +
                "Fjh4EScvxY7C7wPH7OtooZojZpgZlna9TtQhzTpRaTo";

        Long id = 65l;

        mockMvc.perform(get("/api/goodsInfo")
                        .header("authorization", mockAuth)
                        .param("id", String.valueOf(id)))
                .andExpect(status().isOk());

        System.out.println("===========================");

    }

    @Test
    //测试上下架商品
    public void textChangeStatus() throws Exception {
        String mockAuth = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJpc3MiOiIxMTIzNDU1IiwiaWF0IjoxNzMwOTgwNTg5LCJleHAiOjQ4ODY2NTQxODl9." +
                "Fjh4EScvxY7C7wPH7OtooZojZpgZlna9TtQhzTpRaTo";

        Long id = 95l;

        mockMvc.perform(post("/api/changeStatus")
                        .header("authorization", mockAuth)
                        .param("id", String.valueOf(id)))
                .andExpect(status().isOk());

        System.out.println("===========================");

    }
}
