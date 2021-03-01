package com.xcc.dbsharding.dao;

import com.xcc.dbsharding.bean.OrderBean;
import com.xcc.dbsharding.bean.UserBean;
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
public interface UserDAO {

    /**
     * 插入数据
     */
    @Insert("insert into t_user(user_id,fullname,user_type) values(#{userId},#{fullname},#{userType}")
    int insertOrder(@Param("userId") long userId, @Param("fullname") String fullname, @Param("userType") String userType);

    /**
     * 根据用户id集合，查询具体数据集合
     *
     */
    @Select({"<script> select * from t_user " +
            "where user_id in " +
            "<foreach collection='userIds' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>"})
    List<UserBean> selectByUserIds(@Param("userIds") List<Long> userIds);

}
