package com.sp.eshopping_product_service.service.impl;

import com.sp.eshopping_product_service.entitiy.Product;
import com.sp.eshopping_product_service.exception.ProductServiceCustomException;
import com.sp.eshopping_product_service.payload.request.ProductRequest;
import com.sp.eshopping_product_service.payload.response.ProductResponse;
import com.sp.eshopping_product_service.repository.ProductRepository;
import com.sp.eshopping_product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public long addProduct(ProductRequest productRequest) {
        Product product=new Product(productRequest.getName(),productRequest.getPrice(),productRequest.getQuantity());
        product= productRepository.save(product);
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {

        Product product=productRepository.findById(productId)
                .orElseThrow(()->new ProductServiceCustomException("Product with given Id not found","PRODUCT_NOT_FOUND"));
        ProductResponse productResponse=new ProductResponse(product.getProductName(),
                product.getProductId(),product.getQuantity(),product.getPrice());
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {

        Product product=productRepository.findById(productId)
                .orElseThrow(()-> new ProductServiceCustomException("Product with given Id not found", "PRODUCT_NOT_FOUND"));

        if(product.getQuantity()<quantity){
            throw new ProductServiceCustomException(
                    "Product does not have sufficient Quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(long productId) {

        if(!productRepository.existsById(productId)){
            throw new ProductServiceCustomException("Product with given with Id: " + productId + " not found:",
                    "PRODUCT_NOT_FOUND");
        }

        productRepository.deleteById(productId);
    }
}
