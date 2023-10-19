package com.example.polyfood.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountid")
    private int accountId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<User> users;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private int status;

    @Column(name = "decentralizationid")
    private int decentralizationId;
    @ManyToOne
    @JoinColumn(name = "decentralizationid", insertable = false, updatable = false)
    @JsonBackReference
    private Decentralization decentralization;

    @Column(name = "resetpasswordtoken")
    private String resetPasswordToken;

    @Column(name = "resetpasswordtokenexpiry")
    private String resetPasswordTokenExpiry;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updateat")
    private LocalDateTime updateAt;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDecentralizationId() {
        return decentralizationId;
    }

    public void setDecentralizationId(int decentralizationId) {
        this.decentralizationId = decentralizationId;
    }

    public Decentralization getDecentralization() {
        return decentralization;
    }

    public void setDecentralization(Decentralization decentralization) {
        this.decentralization = decentralization;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getResetPasswordTokenExpiry() {
        return resetPasswordTokenExpiry;
    }

    public void setResetPasswordTokenExpiry(String resetPasswordTokenExpiry) {
        this.resetPasswordTokenExpiry = resetPasswordTokenExpiry;
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
