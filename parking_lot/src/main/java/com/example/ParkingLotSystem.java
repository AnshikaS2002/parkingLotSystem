package com.example;

import java.util.ArrayList;
import java.util.List;

import com.ParkingLotOwner;

public class ParkingLotSystem {
    private static final int MAX_CAPACITY = 100;
    int curCapacity;
    private List<SecurityStaff> securityStaffList;
    private List<ParkingLotOwner> parkingLotOwnerList;
    private List<ParkingLotAttendant> parkingLotAttendantList;

    public ParkingLotSystem() {
        this.curCapacity = 0;
        this.securityStaffList = new ArrayList<>();
        this.parkingLotOwnerList = new ArrayList<>();
        this.parkingLotAttendantList = new ArrayList<>();
        parkingLotOwnerList.add(new ParkingLotOwner("abc"));
    }

    public String parkCar(String carNum) {
        curCapacity++;
        // notifyOwner();
        return "parked car " + carNum;
    }

    public String unparkCar(String carNum) {
        curCapacity--;
        notifyOwner();
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

    public void notifyOwner() {
        if (!isParkingFull()) {
            for (ParkingLotOwner owner : parkingLotOwnerList) {
                owner.notifyParkingLotHasSpace();
            }
        }
    }

    public void addParkingLotOwner(ParkingLotOwner owner) {
        parkingLotOwnerList.add(owner);
    }

    public void addParkingLotAttendant(ParkingLotAttendant attendant) {
        parkingLotAttendantList.add(attendant);
    }

    public String parkCarForOwner(String carNum) {
        return "Attendant parked car " + carNum;
    }

    public String findCar(String carNum) {
        return null;
    }

}