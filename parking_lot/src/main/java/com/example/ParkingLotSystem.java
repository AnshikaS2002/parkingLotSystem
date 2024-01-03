package com.example;

import java.util.List;

public class ParkingLotSystem {
    private static final int MAX_CAPACITY = 100;
    int curCapacity;
    private List<SecurityStaff> securityStaffList;

    public ParkingLotSystem() {
        this.curCapacity = 0;
    }

    public String parkCar(String carNum) {
        curCapacity++;
        return "parked car " + carNum;
    }

    public String unparkCar(String carNum) {
        curCapacity--;
        return "unparked car " + carNum;
    }

    public Boolean isParkingFull() {
        if (this.curCapacity >= MAX_CAPACITY)
            return true;
        return false;
    }

    public void notifySecurityStaff() {
        if (isParkingFull()) {
            for (SecurityStaff securityStaff : securityStaffList) {
                securityStaff.notifyParkingFull();
            }
        }
    }

    public void addSecurityStaff(SecurityStaff securityStaff) {
        securityStaffList.add(securityStaff);
    }

}