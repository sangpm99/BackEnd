package com.example.polyfood.Services;

import com.example.polyfood.Models.Product;
import com.example.polyfood.Models.ProductDetail;
import com.example.polyfood.Models.ProductType;
import com.example.polyfood.Repositories.ProductDetailRepository;
import com.example.polyfood.Repositories.ProductRepository;
import com.example.polyfood.Repositories.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            return product.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại id sản phẩm");
    }

    public String getTypeOfProductByProductId(int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            Optional<ProductType> productType = productTypeRepository.findById(product.get().getProductTypeId());
            if(productType.isPresent()) {
                return productType.get().getNameProductType();
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại id sản phẩm");
    }

    public List<String> getProductDetailByProductId(int id) {
        List<ProductDetail> productDetails = productDetailRepository.findAll();
        List<String> details = new ArrayList<>();
        for(ProductDetail productDetail: productDetails) {
            if(productDetail.getProductId() == id) {
                details.add(productDetail.getDetail());
            }
        }
        return details;
    }

    public List<Product> getProductOrderByView() {
        List<Product> products = productRepository.findAll();
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return Integer.compare(product2.getNumberOfViews(), product1.getNumberOfViews());
            }
        });
        return products;
    }
}
