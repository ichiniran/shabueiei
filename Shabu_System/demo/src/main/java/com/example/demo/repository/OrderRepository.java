package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // แค่นี้พอครับ เดี๋ยว JpaRepository จะแถมคำสั่ง save() มาให้เอง
}