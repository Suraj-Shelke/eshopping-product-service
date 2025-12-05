package com.sp.eshopping_product_service.service.impl;

import com.sp.eshopping_product_service.payload.request.ProductRequest;
import com.sp.eshopping_product_service.payload.response.ProductResponse;
import com.sp.eshopping_product_service.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public long addProduct(ProductRequest productRequest) {
        return 0;
    }

    @Override
    public ProductResponse getProductById(long productId) {
        return null;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {

    }

    @Override
    public void deleteProductById(long productId) {

    }
}
