package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    // ดึงเฉพาะเมนูที่ Active = true (สำหรับลูกค้า)
    List<MenuItem> findByIsActiveTrue();
}