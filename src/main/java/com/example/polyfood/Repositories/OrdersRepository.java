package com.example.polyfood.Repositories;

import com.example.polyfood.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
