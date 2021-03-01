package com.xcc.dbsharding.bean;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;


@Data
@ToString
public class OrderBean {

    private Long orderId;

    private BigDecimal price;

    private Long userId;

    private String status;

}
