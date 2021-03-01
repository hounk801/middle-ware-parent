package com.xcc.shopping.dao;

import com.xcc.shopping.entity.ProductDescript;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ProductDescriptDao {

    @Insert("insert into product_descript(product_info_id,descript,store_info_id) " +
            "values(#{productInfoId},#{descript},#{storeInfoId})")
    @Options(useGeneratedKeys = true,keyProperty="id",keyColumn = "id")
    int insert(ProductDescript productDescript);


}
