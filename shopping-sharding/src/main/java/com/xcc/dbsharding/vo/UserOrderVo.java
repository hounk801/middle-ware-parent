package com.xcc.dbsharding.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class UserOrderVo {

    private Long orderId;

    private BigDecimal price;

    private Long userId;

    private String status;

    private String fullname;

    private String userType;

}
