package com.example.polyfood.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "producttype")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producttypeid")
    private int productTypeId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producttype", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Product> products;

    @Column(name = "nameproducttype")
    private String nameProductType;

    @Column(name = "imagetypeproduct")
    private String imageTypeProduct;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updateat")
    private LocalDateTime updateAt;

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getNameProductType() {
        return nameProductType;
    }

    public void setNameProductType(String nameProductType) {
        this.nameProductType = nameProductType;
    }

    public String getImageTypeProduct() {
        return imageTypeProduct;
    }

    public void setImageTypeProduct(String imageTypeProduct) {
        this.imageTypeProduct = imageTypeProduct;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
