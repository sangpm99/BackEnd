package com.example.polyfood.Services;

import com.example.polyfood.Models.Product;
import com.example.polyfood.Models.ProductType;
import com.example.polyfood.Repositories.ProductRepository;
import com.example.polyfood.Repositories.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;

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
}
