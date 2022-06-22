package com.wz.api.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    private String _id;             //主键
    private String userName;        //用户名
    private String name;            //商品名
    private Integer price;
    private String image;
    private String skuId;
    private Integer num;
}
