package com.example.demo.dto; // ต้องอยู่ใน package dto

import lombok.Data;

@Data
public class CartItemRequest {
    private Integer id;  // รับ id อาหาร
    private Integer qty; // รับจำนวนที่สั่ง
}