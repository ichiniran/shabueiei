package com.example.demo.model;

public class ShabuTable {
    private final String number;
    private final String status;
    private final int pax;
    private final String time;
    private final String alertMsg;

    public ShabuTable(String number, String status, int pax, String time, String alertMsg) {
        this.number = number;
        this.status = status;
        this.pax = pax;
        this.time = time;
        this.alertMsg = alertMsg;
    }
    // Getters
    public String getNumber() { return number; }
    public String getStatus() { return status; }
    public int getPax() { return pax; }
    public String getTime() { return time; }
    public String getAlertMsg() { return alertMsg; }
}