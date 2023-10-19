package com.example.polyfood.Repositories;

import com.example.polyfood.Models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
