package com.example.polyfood.Controllers;

import com.example.polyfood.Models.Product;
import com.example.polyfood.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product/")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductServices productServices;

    @RequestMapping(value = "getallproduct", method = RequestMethod.GET)
    public List<Product> getAllProduct() {
        return productServices.getAllProduct();
    }

    @RequestMapping(value = "getproductbyid", method = RequestMethod.GET)
    public Product getProductById(@RequestParam int id) {
        return productServices.getProductById(id);
    }

    @RequestMapping(value = "gettypeofproductbyproductid", method = RequestMethod.GET)
    public String getTypeOfProductByProductId(@RequestParam int id) {
        return productServices.getTypeOfProductByProductId(id);
    }

    @RequestMapping(value = "getproductdetailbyproductid", method = RequestMethod.GET)
    public List<String> getProductDetailByProductId(@RequestParam int id) {
        return productServices.getProductDetailByProductId(id);
    }

    @RequestMapping(value = "getproductorderbyview", method = RequestMethod.GET)
    public List<Product> getProductOrderByView() {
        return productServices.getProductOrderByView();
    }
}
