package com.example.polyfood.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "productreview")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productreviewid")
    private int productReviewId;

    @Column(name = "productid")
    private int productId;
    @ManyToOne
    @JoinColumn(name = "productid", insertable = false, updatable = false)
    @JsonBackReference
    private Product product;

    @Column(name = "userid")
    private int userId;
    @ManyToOne
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @Column(name = "contentrated")
    private String contentrated;

    @Column(name = "pointevaluation")
    private int pointEvaluation;

    @Column(name = "contentseen")
    private String contentSeen;

    @Column(name = "status")
    private int status;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updateat")
    private LocalDateTime updateAt;

    public int getProductReviewId() {
        return productReviewId;
    }

    public void setProductReviewId(int productReviewId) {
        this.productReviewId = productReviewId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContentrated() {
        return contentrated;
    }

    public void setContentrated(String contentrated) {
        this.contentrated = contentrated;
    }

    public int getPointEvaluation() {
        return pointEvaluation;
    }

    public void setPointEvaluation(int pointEvaluation) {
        this.pointEvaluation = pointEvaluation;
    }

    public String getContentSeen() {
        return contentSeen;
    }

    public void setContentSeen(String contentSeen) {
        this.contentSeen = contentSeen;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
