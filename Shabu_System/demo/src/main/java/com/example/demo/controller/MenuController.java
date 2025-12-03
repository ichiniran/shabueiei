package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.MenuItem;
import com.example.demo.repository.MenuItemRepository;
@Controller
public class MenuController {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping("/menu")
    public String menuPage(Model model) {
        // ดึงมาเฉพาะเมนูที่ Active (ของไม่หมด)
        List<MenuItem> items = menuItemRepository.findByIsActiveTrue();
        
        model.addAttribute("menuItems", items);
        return "menu";
    }
}

