package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CartItemRequest;
import com.example.demo.entity.MenuItem;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired private OrderRepository orderRepository;
    @Autowired private MenuItemRepository menuItemRepository;
    @Autowired private OrderItemRepository orderItemRepository;

    @PostMapping("/submit")
    public ResponseEntity<String> submitOrder(@RequestBody List<CartItemRequest> cartItems) {
        
        // --- 1. เช็คว่า Request มาถึงไหม ---
        System.out.println("========================================");
        System.out.println(">>> 1. Controller ถูกเรียกแล้ว!");
        System.out.println(">>> จำนวนรายการที่ส่งมา: " + (cartItems != null ? cartItems.size() : "null"));

        if (cartItems == null || cartItems.isEmpty()) {
            return ResponseEntity.badRequest().body("ไม่พบรายการอาหาร");
        }

        // --- 2. สร้าง Order หลัก ---
        Order newOrder = new Order();
        newOrder.setTableNo("A1"); 
        newOrder.setOrderTime(LocalDateTime.now());
        newOrder.setStatus("PENDING");
        
        Order savedOrder = orderRepository.save(newOrder);
        System.out.println(">>> 2. สร้าง Order หลักสำเร็จ ID: " + savedOrder.getId());

        List<OrderItem> itemsToSave = new ArrayList<>();
        
        for (CartItemRequest itemReq : cartItems) {
            System.out.println("   -> กำลังประมวลผลเมนู ID: " + itemReq.getId() + ", Qty: " + itemReq.getQty());
            
            // ค้นหาเมนู
            MenuItem menu = menuItemRepository.findById(itemReq.getId()).orElse(null);
            
            if (menu != null) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(savedOrder);
                orderItem.setMenuItem(menu); 
                orderItem.setQuantity(itemReq.getQty());
                itemsToSave.add(orderItem);
                System.out.println("      (เจอเมนู: " + menu.getName() + " -> เตรียมบันทึก)");
            } else {
                // --- จุดนี้สำคัญ! ถ้าเข้าตรงนี้แปลว่า ID ผิด หรือ Database เมนูว่างเปล่า ---
                System.out.println("      (!!! ไม่เจอเมนู ID นี้ใน Database !!!)"); 
            }
        }

        // --- 3. บันทึกรายการย่อย ---
        if (!itemsToSave.isEmpty()) {
            orderItemRepository.saveAll(itemsToSave);
            System.out.println(">>> 3. บันทึก OrderItem ลง DB ทั้งหมด " + itemsToSave.size() + " รายการ");
        } else {
            System.out.println(">>> 3. ไม่มีการบันทึก OrderItem (อาจเพราะไม่เจอเมนู)");
        }
        System.out.println("========================================");

        return ResponseEntity.ok("สั่งอาหารเรียบร้อย!");
    }
}