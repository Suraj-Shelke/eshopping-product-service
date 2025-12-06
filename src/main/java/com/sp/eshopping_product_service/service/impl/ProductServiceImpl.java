package com.sp.eshopping_product_service.service.impl;

import com.sp.eshopping_product_service.entitiy.Product;
import com.sp.eshopping_product_service.exception.ProductServiceCustomException;
import com.sp.eshopping_product_service.payload.request.ProductRequest;
import com.sp.eshopping_product_service.payload.response.ProductResponse;
import com.sp.eshopping_product_service.repository.ProductRepository;
import com.sp.eshopping_product_service.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("ProductServiceImpl | addProduct is called");
        Product product=new Product(productRequest.getName(),productRequest.getPrice(),productRequest.getQuantity());
        product= productRepository.save(product);
        log.info("ProductServiceImpl | addProduct | Product Created");
        log.info("ProductServiceImpl | addProduct | Product Id : " + product.getProductId());
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("ProductServiceImpl | getProductById is called");
        log.info("ProductServiceImpl | getProductById | Get the product for productId: {}", productId);
        Product product=productRepository.findById(productId)
                .orElseThrow(()->new ProductServiceCustomException("Product with given Id not found","PRODUCT_NOT_FOUND"));
        ProductResponse productResponse=new ProductResponse(product.getProductName(),
                product.getProductId(),product.getQuantity(),product.getPrice());
        log.info("ProductServiceImpl | getProductById | productResponse :" + productResponse.toString());
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity,productId);
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
        log.info("Product Quantity updated Successfully");
    }

    @Override
    public void deleteProductById(long productId) {
        log.info("Product id: {}", productId);
        if(!productRepository.existsById(productId)){
            throw new ProductServiceCustomException("Product with given with Id: " + productId + " not found:",
                    "PRODUCT_NOT_FOUND");
        }
        log.info("Deleting Product with id: {}", productId);
        productRepository.deleteById(productId);
    }
}
