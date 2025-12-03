package com.example.demo; // (ชื่อ package ตามโปรเจคคุณ)

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/") // 1. เมื่อเข้าเว็บหน้าแรก (http://localhost:8080)
    public String index(Model model) {
        // 2. เตรียมของใส่กล่อง (Model) ส่งไปให้ HTML
        String shopName = "Shabu Number One";
        int tableCount = 15;
        
        // "name" คือชื่อป้ายแปะกล่อง, shopName คือของข้างใน
        model.addAttribute("shopName", shopName); 
        model.addAttribute("tables", tableCount); 

        // 3. บอกให้ไปเปิดไฟล์ชื่อ "home.html"
        return "home"; 
    }
}