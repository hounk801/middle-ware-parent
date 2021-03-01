package com.xcc.shopping.service.impl;

import com.xcc.shopping.dao.ProductDescriptDao;
import com.xcc.shopping.dao.ProductInfoDao;
import com.xcc.shopping.entity.ProductDescript;
import com.xcc.shopping.entity.ProductInfo;
import com.xcc.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoDao productInfoDao;
    @Autowired
    ProductDescriptDao productDescriptDao;

    @Override
    @Transactional
    public int insert(ProductInfo productInfo) {
        productInfoDao.insert(productInfo);

        ProductDescript productDescript = new ProductDescript();
        productDescript.setDescript(productInfo.getDescript());
        productDescript.setProductInfoId(productInfo.getProductInfoId());
        productDescript.setStoreInfoId(productInfo.getStoreInfoId());
        productDescriptDao.insert(productDescript);

        return 0;
    }
}
