package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.ShabuTable;

@Controller
public class TableController {
    @GetMapping("/tables")
    public String viewTables(Model model) {
        // Mock Data
        List<ShabuTable> zoneA = Arrays.asList(
            new ShabuTable("A1", "BUSY", 3, "00:30:15", null),
            new ShabuTable("A2", "FREE", 0, "", null),
            new ShabuTable("A3", "FREE", 0, "", null),
            new ShabuTable("A4", "BUSY", 4, "00:45:00", null),
            new ShabuTable("A5", "BUSY", 2, "00:10:00", null)
        );
        List<ShabuTable> zoneB = Arrays.asList(
            new ShabuTable("B1", "ALERT", 0, "", "เรียกเก็บเงิน"),
            new ShabuTable("B2", "FREE", 0, "", null),
            new ShabuTable("B3", "FREE", 0, "", null),
            new ShabuTable("B4", "ALERT", 0, "", "เติมน้ำซุป"),
            new ShabuTable("B5", "BUSY", 6, "01:00:00", null)
        );
        model.addAttribute("zoneA", zoneA);
        model.addAttribute("zoneB", zoneB);
        return "table-management";
    }
}