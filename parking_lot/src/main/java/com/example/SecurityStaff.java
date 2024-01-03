package com.example;

public class SecurityStaff {

    String name;

    SecurityStaff(String name) {
        this.name = name;
    }

    void notifyParkingFull() {
        System.out.println("Parking is full. Redirect...");
    }
}
