package com.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.ParkingLotOwner;

public class ParkingLotSystem {
    private static final int MAX_CAPACITY = 100;
    int curCapacity;
    private List<SecurityStaff> securityStaffList;
    private List<ParkingLotOwner> parkingLotOwnerList;
    private List<ParkingLotAttendant> parkingLotAttendantList;
    private Map<String, String> parkedCars;
    private Map<String, LocalDateTime> parkingTimestamps;

    public ParkingLotSystem() {
        this.curCapacity = 0;
        this.securityStaffList = new ArrayList<>();
        this.parkingLotOwnerList = new ArrayList<>();
        this.parkingLotAttendantList = new ArrayList<>();
        this.parkedCars = new HashMap<>();
        this.parkingTimestamps = new HashMap<>();
    }

    public String parkCar(String carNum) {
        if (isParkingFull()) {
            return "Parking is full. Cannot park car.";
        }
        curCapacity++;
        String parkingSpot = "Spot" + curCapacity;
        parkedCars.put(carNum, parkingSpot);
        parkingTimestamps.put(carNum, LocalDateTime.now());
        return "parked car " + carNum;
    }

    public String unparkCar(String carNum) {
        if (!parkedCars.containsKey(carNum)) {
            return "Car " + carNum + " not found in the parking lot.";
        }
        curCapacity--;
        String parkingSpot = parkedCars.remove(carNum);
        parkingTimestamps.remove(carNum);
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
        if (parkedCars.containsKey(carNum)) {
            return "Car " + carNum + " is parked at " + parkedCars.get(carNum);
        } else {
            return "Car " + carNum + " not found in the parking lot.";
        }
    }

    public LocalDateTime getParkingTimestamp(String carNum) {
        return parkingTimestamps.get(carNum);
    }

}