package com.example.polyfood.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private int productId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<ProductImage> productImages;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<ProductReview> productReviews;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<CartItem> cartItems;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<OrderDetail> orderDetails;

    @Column(name = "producttypeid")
    private int productTypeId;
    @ManyToOne
    @JoinColumn(name = "producttypeid", insertable = false, updatable = false)
    @JsonBackReference
    private ProductType producttype;

    @Column(name = "nameproduct")
    private String nameProduct;

    @Column(name = "price")
    private double price;

    @Column(name = "avatarimageproduct")
    private String avatarImageProduct;

    @Column(name = "title")
    private String title;

    @Column(name = "discount")
    private int discount;

    @Column(name = "status")
    private int status;

    @Column(name = "numberofviews")
    private int numberOfViews;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updateat")
    private LocalDateTime updateAt;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public List<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public ProductType getProducttype() {
        return producttype;
    }

    public void setProducttype(ProductType producttype) {
        this.producttype = producttype;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvatarImageProduct() {
        return avatarImageProduct;
    }

    public void setAvatarImageProduct(String avatarImageProduct) {
        this.avatarImageProduct = avatarImageProduct;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumberOfViews() {
        return numberOfViews;
    }

    public void setNumberOfViews(int numberOfViews) {
        this.numberOfViews = numberOfViews;
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
