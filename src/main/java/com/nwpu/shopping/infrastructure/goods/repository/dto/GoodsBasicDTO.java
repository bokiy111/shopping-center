package com.nwpu.shopping.infrastructure.goods.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author: feoyang
 * @date: 2024/11/8 13:19
 * @description: TODO
 */
@Data
@AllArgsConstructor
@ToString
public class GoodsBasicDTO {
    private Long id;
    private String name;
    private Double price;
    private String basicDescription;
    private String coverUrl;
}
