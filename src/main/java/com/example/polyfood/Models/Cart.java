package com.example.polyfood.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartid")
    private int cartId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<CartItem> cartItems;

    @Column(name = "userid")
    private int userId;
    @ManyToOne
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updateat")
    private LocalDateTime updateAt;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
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
