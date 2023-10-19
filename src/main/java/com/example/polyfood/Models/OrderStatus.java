package com.example.polyfood.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orderstatus")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderstatusid")
    private int orderStatusId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderstatus", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Orders> orders;

    @Column(name = "statusname")
    private String statusName;

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
