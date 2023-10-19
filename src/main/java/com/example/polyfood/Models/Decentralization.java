package com.example.polyfood.Models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "decentralization")
public class Decentralization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "decentralizationid")
    private int decentralizationId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "decentralization", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Account> accounts;

    @Column(name = "authorityname")
    private String authorityName;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updateat")
    private LocalDateTime updateAt;

    public int getDecentralizationId() {
        return decentralizationId;
    }

    public void setDecentralizationId(int decentralizationId) {
        this.decentralizationId = decentralizationId;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
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
