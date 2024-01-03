package com;

public class ParkingLotOwner {
    private String name;

    public ParkingLotOwner(String name) {
        this.name = name;
    }

    public void notifyParkingLotHasSpace() {
        System.out.println(name + ": Parking lot has space again. Take in the full sign.");
    }
}
