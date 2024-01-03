package com.example;

public class ParkingLotSystem {
    private static final int MAX_CAPACITY = 100;
    int curCapacity;

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
        return null;
    }

}