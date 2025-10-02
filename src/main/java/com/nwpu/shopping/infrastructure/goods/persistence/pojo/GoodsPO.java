package com.nwpu.shopping.infrastructure.goods.persistence.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsPO {
    private long id;
    private String name;
    private Double price;
    private String description;
    private long sellerId;
    private String createTime;
    private String updateTime;
    private int status;
    private int category;
    private String coverUrl;
}
