package com.xcc.shopping.dao;

import com.xcc.shopping.entity.ProductInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ProductInfoDao {

    @Insert("insert into product_info(store_info_id,product_name,spec,region_code,price,image_url) " +
            "values(#{storeInfoId},#{productName},#{spec},#{regionCode},#{price},#{imageUrl})")
    @Options(useGeneratedKeys = true,keyProperty="productInfoId",keyColumn = "product_info_id")
    int insert(ProductInfo productInfo);
    @Select("select i.*,d.descript,r.region_name placeOfOrigin from product_info i join product_descript d on i.product_info_id = d.product_info_id " +
            " join region r on i.region_code = r.region_code order by product_info_id desc limit #{start},#{pageSize}")
    List<ProductInfo> selectProductList(@Param("start")int start, @Param("pageSize") int pageSize);

    //商品总数
    @Select("select count(1) from product_info")
    int selectCount();

    //商品分组统计
    @Select("select t.region_code,count(1) as num from product_info t group by t.region_code having num > 1 order by region_code ")
    List<Map> selectProductGroupList();
}
