package com.example.polyfood.Repositories;

import com.example.polyfood.Models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
}
