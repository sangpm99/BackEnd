package com.example.polyfood.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orderdetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderdetailid")
    private int orderDetailId;

    @Column(name = "orderid")
    private int orderid;
    @ManyToOne
    @JoinColumn(name = "orderid", insertable = false, updatable = false)
    @JsonBackReference
    private Orders orders;

    @Column(name = "productid")
    private int productId;
    @ManyToOne
    @JoinColumn(name = "productid", insertable = false, updatable = false)
    @JsonBackReference
    private Product product;

    @Column(name = "pricetotal")
    private double priceTotal;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updateat")
    private LocalDateTime updateAt;

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
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

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
