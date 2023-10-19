package com.example.polyfood.Repositories;

import com.example.polyfood.Models.Decentralization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecentralizationRepository extends JpaRepository<Decentralization, Integer> {
}
