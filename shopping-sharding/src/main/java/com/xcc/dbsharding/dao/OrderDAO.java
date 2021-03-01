package com.xcc.dbsharding.dao;

import com.xcc.dbsharding.bean.OrderBean;
import com.xcc.dbsharding.vo.UserOrderVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface OrderDAO {

    /**
     * 插入数据
     * @param price     价格
     * @param userId    用户id
     * @param status    状态
     */
    @Insert("insert into t_order(price,user_id,status) values(#{price},#{userId},#{status})")
    int insertOrder(@Param("price") BigDecimal price, @Param("userId") long userId, @Param("status") String status);

    /**
     * 根据订单id集合，查询具体数据集合
     * @param orderIds 订单id集合
     */
    @Select({"<script> select * from t_order " +
            "where order_id in " +
            "<foreach collection='orderIds' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>"})
    List<Map<String, Object>> selectByOrderIds(@Param("orderIds") List<Long> orderIds);

    /**
     * 根据用户id查询订单
     * @param userId    查询订单
     */
    @Select("select * from t_order where user_id = #{userId}")
    List<OrderBean> selectByuserId(@Param("userId") long userId);

    //TODO 暂且保留，跨库查询还没有搞定
    /**
     * 根据用户id查询订单
     * @param userId    查询订单
     */
    @Select({"<script>" +
            "select o.order_id,o.price,o.user_id,o.status,u.fullname,u.user_type " +
            "from t_order o " +
            "join t_user u on o.`user_id` = u.`user_id`  " +
            "where o.order_id in" +
            "<foreach collection='orderIds' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>"})
    List<UserOrderVo> selectUserOrderByOrderIds(@Param("orderIds") List<Long> orderIds);

}
