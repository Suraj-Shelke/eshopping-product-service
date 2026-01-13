package com.sp.eshopping_product_service.service;

import com.sp.eshopping_product_service.payload.request.ProductRequest;
import com.sp.eshopping_product_service.payload.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);

    public void deleteProductById(long productId);

    public List<ProductResponse> getAllProducts();
}
